package Usuarios;
import Banco.Banco;
import Banco.Utils.Herramientas;
import Tarjetas.Credito;
import Tarjetas.Utils.TiposCredito;
import Usuarios.Utils.Rol;
import Usuarios.Utils.Sucursales;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import Banco.ValidacionesYRegistros;
import Banco.Utils.DatosComun;
import static Banco.Banco.listaUsuarios;


public class Empleado extends Usuario {
    private Banco banco;
    double Salario;
    private LocalDate fechaDeInicioLaboral;
    private static Empleado empleado;
    private static Cliente cliente;

    public Empleado(String usuario, String password, String nombre, String apellidos, LocalDate fechaNacimiento, String ciudad, String estado, String RFC, String Curp, String direccion, Sucursales sucursales, Rol rol, double Salario, LocalDate fechaDeInicioLaboral) {
        super(usuario, password, nombre, apellidos, fechaNacimiento, ciudad, estado, RFC, Curp, direccion, sucursales, rol);
        this.Salario = Salario;
        this.fechaDeInicioLaboral = fechaDeInicioLaboral;
    }

    @Override
    public String toString() {
        return String.format("%s, Salario: %f, Inicio Laboral: ", super.toString(), Salario);
    }

    public static void RegistrarEjecutivo() {
        ArrayList<String> datosComunes = DatosComun.RegistrarDatosComunes(Rol.Ejecutivos);
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
        ;
        double Salario = ValidacionesYRegistros.RegistrarSalario();


    }

    public static void RegistrarCapturista() {
        ArrayList<String> datosComunes = DatosComun.RegistrarDatosComunes(Rol.Capturista);
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

        double Salario = ValidacionesYRegistros.RegistrarSalario();


    }

    public static void MostrarCapturistas(Sucursales sucursales) {
        for (Usuario usuario : listaUsuarios.get(Rol.Capturista)) {
            Empleado empleado = (Empleado) usuario;
            if (empleado.getSucursales().equals(sucursales)) {
                System.out.println(empleado.getNombre());
            }
        }
    }

    public void revisarSolicitudes() {
        boolean band = true;
        System.out.println("Estas son las solicitudes hasta ahora");
        for (int i = 0; i < cliente.Solicitudes.size(); i++) {
            System.out.println((i + 1) + " " + cliente.Solicitudes.get(i));
        }
        while (band) {
            try {


                System.out.print("Ingresa el numero de la solicitud a revisar:");
                int opcion = Herramientas.nextInt();
                System.out.println("Esta fue la solicitud que escogiste:" + cliente.Solicitudes.get(opcion - 1));
                System.out.println("Si la apruebas ingresa 1 si no 2");
                opcion = Herramientas.nextInt();
                if (opcion == 1) {
                    String cadenaOriginal = cliente.Solicitudes.get(opcion - 1);
                    String subcadenaExtraida = cadenaOriginal.substring(0, 10); //
                    String cadenaModificada = cadenaOriginal.replace(subcadenaExtraida, "aprobada");
                    cliente.Solicitudes.set(opcion - 1, cadenaModificada);
                    if (cadenaModificada.equals("Simplicity")) {
                        Credito credito = new Credito(getUsuario(), getPassword(), TiposCredito.simplicity);
                        cliente.tarjetasCredito.add(credito);
                    }

                    band = false;
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
}