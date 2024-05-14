package Usuarios;
import Banco.Banco;
import Tarjetas.Credito;
import Tarjetas.Utils.TiposCredito;
import Usuarios.Utils.Rol;
import Usuarios.Utils.Sucursales;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import Banco.ValidacionesYRegistros;

public class Empleado extends Usuario{
    private Banco banco;
    Scanner scan=new Scanner(System.in);
    ValidacionesYRegistros validacionesYRegistros=new ValidacionesYRegistros();
    double Salario;
    LocalDate fechaDeInicioLaboral;
    Cliente cliente=new Cliente("","","","", LocalDate.now(),"","","", "","", Sucursales.Madero, Rol.Cliente);

    public Empleado(String usuario, String password, String nombre, String apellidos, LocalDate fechaNacimiento, String ciudad, String estado, String RFC, String Curp, String direccion, Sucursales sucursales, Rol rol, double Salario,LocalDate fechaDeInicioLaboral){
        super(usuario,password,nombre,apellidos,fechaNacimiento,ciudad,estado,RFC,Curp,direccion,sucursales,rol);
        this.Salario=Salario;
        this.fechaDeInicioLaboral=fechaDeInicioLaboral;
    }

    public void RegistrarEjecutivo(Empleado gerente){
        ArrayList<String> datosComunes= banco.RegistrarDatosComunes();
        String nombre = datosComunes.get(0);
        String apellido = datosComunes.get(1);
        LocalDate fechaNacimiento = LocalDate.parse(datosComunes.get(2));
        String ciudad = datosComunes.get(3);
        String estado = datosComunes.get(4);
        String RFC = datosComunes.get(5);
        String Curp = datosComunes.get(6);
        String direccion = datosComunes.get(7);
        String usuario = datosComunes.get(8);
        String contraseña = datosComunes.get(9);
        Sucursales sucursales = gerente.getSucursales();
        double Salario= ValidacionesYRegistros.RegistrarSalario();

        Empleado empleado=new Empleado(usuario,contraseña,nombre,apellido,fechaNacimiento, ciudad,estado,RFC,Curp,direccion,sucursales,Rol.Ejecutivos,Salario,LocalDate.now());
        banco.listaEmpleado.put(empleado.getUsuario(), empleado);
        Banco.listaUsuarios.get(empleado.rol).add(empleado);
    }
    public void RegistrarCapturista(Empleado gerente){
        ArrayList<String> datosComunes= banco.RegistrarDatosComunes();
        String nombre = datosComunes.get(0);
        String apellido = datosComunes.get(1);
        LocalDate fechaNacimiento = LocalDate.parse(datosComunes.get(2));
        String ciudad = datosComunes.get(3);
        String estado = datosComunes.get(4);
        String RFC = datosComunes.get(5);
        String Curp = datosComunes.get(6);
        String direccion = datosComunes.get(7);
        String usuario = datosComunes.get(8);
        String contraseña = datosComunes.get(9);
        Sucursales sucursales = gerente.getSucursales();
        double Salario= ValidacionesYRegistros.RegistrarSalario();

        Empleado empleado=new Empleado(usuario,contraseña,nombre,apellido,fechaNacimiento, ciudad,estado,RFC,Curp,direccion,sucursales,Rol.Capturista,Salario,LocalDate.now());
        banco.listaEmpleado.put(empleado.getUsuario(), empleado);
        Banco.listaUsuarios.get(empleado.rol).add(empleado);
    }

    public void revisarSolicitudes(){
        boolean band=true;
        System.out.println("Estas son las solicitudes hasta ahora");
        for (int i = 0; i < cliente.Solicitudes.size(); i++) {
            System.out.println((i+1)+" "+cliente.Solicitudes.get(i));
        }
        while(band) {
            try {


                System.out.print("Ingresa el numero de la solicitud a revisar:");
                int opcion = scan.nextInt();
                System.out.println("Esta fue la solicitud que escogiste:" + cliente.Solicitudes.get(opcion - 1));
                System.out.println("Si la apruebas ingresa 1 si no 2");
                opcion = scan.nextInt();
                if (opcion == 1) {
                    String cadenaOriginal = cliente.Solicitudes.get(opcion - 1);
                    String subcadenaExtraida = cadenaOriginal.substring(0, 10); //
                    String cadenaModificada = cadenaOriginal.replace(subcadenaExtraida, "aprobada");
                    cliente.Solicitudes.set(opcion - 1, cadenaModificada);
                    if(cadenaModificada.equals("Simplicity")){Credito credito=new Credito(getUsuario(),getPassword(), TiposCredito.simplicity);
                        cliente.tarjetasCredito.add(credito);}

                    band=false;
                }
                if (opcion == 2) {
                    String cadenaOriginal = cliente.Solicitudes.get(opcion - 1);
                    String subcadenaExtraida = cadenaOriginal.substring(0, 10); //
                    String cadenaModificada = cadenaOriginal.replace(subcadenaExtraida, "no aprobada");
                    cliente.Solicitudes.set(opcion - 1, cadenaModificada);
                    band = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingrese un numero");
            }
        }
    }
    public Empleado modificarNombre(String nuevoNombre) {
        nuevoNombre = validacionesYRegistros.validarNombre(nuevoNombre);
        this.nombre = nuevoNombre;
        return this;
    }

    public Empleado modificarApellidos(String nuevosApellidos) {
        this.apellidos = nuevosApellidos;
        nuevosApellidos = validacionesYRegistros.validarApellido(nuevosApellidos);
        return this;
    }

    public Empleado modificarFechaNacimiento() {
        this.fechaNacimiento = validacionesYRegistros.RegistrarFechaDeNacimiento();
        return this;
    }

    public Empleado modificarCiudad(String nuevaCiudad) {
        this.ciudad = nuevaCiudad;
        nuevaCiudad = validacionesYRegistros.validarCiudad(nuevaCiudad);
        return this;
    }

    public Empleado modificarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
        nuevoEstado = validacionesYRegistros.validarEstado(nuevoEstado);
        return this;
    }

    public Empleado modificarCurp(String nuevaCurp) {
        this.Curp = nuevaCurp;
        return this;
    }

    public Empleado modificarDireccion(String nuevaDireccion) {
        this.direccion = nuevaDireccion;
        nuevaDireccion = validacionesYRegistros.validarDireccion(nuevaDireccion);
        return this;
    }

    public Empleado modificarUsuario(String nuevoUsuario) {
        this.usuario = nuevoUsuario;
        nuevoUsuario = validacionesYRegistros.validarUsuario(nuevoUsuario);
        return this;
    }

    public Empleado modificarContraseña(String nuevaContraseña) {
        this.password = nuevaContraseña;
        return this;
    }
    public Empleado modificarSalario(Double nuevoSalario){
        this.Salario=nuevoSalario;
        return this;
    }
    // Método para buscar un empleado por usuario, rol y RFC
    public Empleado buscarEmpleadoPorUsuarioYRFC(Usuario usuarioactivo) {
        for (Empleado empleado : banco.listaEmpleado.values()) {
            if (usuarioactivo.getUsuario().equals(empleado.getUsuario()) && usuarioactivo.getRol().equals(empleado.getRol()) && usuarioactivo.getRFC().equals(empleado.getRFC())) {
                return empleado;
            }
        }
        System.out.println("El usuario activo no es tipo empleado");
        return null;
    }
    public void mostrarMenuModificacion(Empleado empleado) {
        System.out.println("Menú de Modificación:");
        System.out.println("1. Nombre");
        System.out.println("2. Apellidos");
        System.out.println("3. Fecha de Nacimiento");
        System.out.println("4. Ciudad");
        System.out.println("5. Estado");
        System.out.println("6. Curp");
        System.out.println("7. Dirección");
        System.out.println("8. Usuario");
        System.out.println("9. Contraseña");
        System.out.println("10. Salario");
        System.out.println("11. Salir");

        int opcion;
        do {
            System.out.print("Seleccione el número de la opción que desea modificar: ");
            Scanner leer = new Scanner(System.in);
            opcion = leer.nextInt();
            leer.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nuevo nombre: ");
                    empleado.modificarNombre(leer.nextLine());
                    break;
                case 2:
                    System.out.print("Ingrese los nuevos apellidos: ");
                    empleado.modificarApellidos(leer.nextLine());
                    break;
                case 3:
                    empleado.modificarFechaNacimiento();
                    break;
                case 4:
                    empleado.modificarCiudad(leer.nextLine());
                    break;
                case 5:
                    empleado.modificarEstado(leer.nextLine());
                    break;
                case 6:
                    empleado.modificarCurp(leer.nextLine());
                    break;
                case 7:
                    empleado.modificarDireccion(leer.nextLine());
                    break;
                case 8:
                    empleado.modificarUsuario(leer.nextLine());
                    break;
                case 9:
                    empleado.modificarContraseña(leer.nextLine());
                    break;
                case 10:
                    System.out.print("Ingrese el nuevo salario: ");
                    empleado.modificarSalario(leer.nextDouble());
                    break;
                case 11:
                    System.out.println("Saliendo del menú de modificación...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        } while (opcion != 11);
    }
}