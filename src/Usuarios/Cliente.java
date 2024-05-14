package Usuarios;
import Banco.Banco;
import Tarjetas.Credito;
import Tarjetas.Debito;
import Tarjetas.Utils.TiposCredito;
import Usuarios.Utils.Rol;
import Usuarios.Utils.Sucursales;
import Tarjetas.Tarjeta;
import java.time.LocalDate;
import java.util.*;
import static Banco.Banco.listaUsuarios;
import Banco.ValidacionesYRegistros;


public class Cliente extends Usuario {
    Random ran = new Random();
    ValidacionesYRegistros validacionesYRegistros= new ValidacionesYRegistros();
    private Banco banco;
    private LocalDate fechaDeRegistro;
    public Debito debito;
    Rol rol;
    private int id;
    private String status="En proceso";
    ArrayList<String> Solicitudes = new ArrayList<>();
    Scanner scan=new Scanner(System.in);
    ArrayList<Tarjeta> tarjetasCredito;
    ArrayList<Integer>idsGenerados = new ArrayList<>();
    public Cliente(String usuario, String password, String nombre, String apellidos, LocalDate fechaNacimiento, String ciudad, String estado, String RFC, String Curp, String direccion, Sucursales sucursales, Rol rol) {
        super(usuario, password, nombre, apellidos, fechaNacimiento, ciudad, estado , RFC, Curp, direccion, sucursales, Rol.Cliente);//Asigno de una vez el Rol Cliente
        this.rol=rol;
        this.tarjetasCredito=new ArrayList<>();
        this.debito= new Debito(usuario,password, TiposCredito.debito);
        this.id=generarId();
    }
    public void RegistrarCliente(Empleado capturistaOgerente) {
        Banco banco = new Banco();
        ArrayList<String> datosComunes = banco.RegistrarDatosComunes();
        String nombre = datosComunes.get(0);
        String apellido = datosComunes.get(1);
        LocalDate fechaNacimiento = LocalDate.parse(datosComunes.get(2));
        String ciudad = datosComunes.get(3);
        String estado = datosComunes.get(4);
        String RFC = datosComunes.get(5);
        String Curp = datosComunes.get(6);
        String direccion = datosComunes.get(7);
        String usuario = datosComunes.get(8);
        String contraseña = datosComunes.get(9); // Aquí deberías validar la contraseña
        Sucursales sucursales = capturistaOgerente.getSucursales();

        Cliente cliente = new Cliente(usuario, contraseña, nombre, apellido, fechaNacimiento, ciudad, estado, RFC, Curp, direccion, sucursales, Rol.Cliente);
        if (!banco.listaCliente.containsKey(cliente.getUsuario())) {
            banco.listaCliente.put(cliente.getUsuario(), cliente);
            listaUsuarios.get(cliente.getRol()).add(cliente);
        } else {
            System.out.println("El cliente ya está registrado.");
        }
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void solicitarTarjetaCredito() {
        boolean band = true;
        String solicitud;
        System.out.println("Bienvenido al proceso de Solicitud de credito");
        System.out.println("Las siguientes opciones son a las que eres apto para solicitar");
        while (band) {
            if (debito.getSaldo() >= 200000) {
                System.out.println("1.-Simplicity");
                System.out.println("2.-Platino");
                System.out.println("3.-Oro");
                System.out.println("Ingrese la tarjeta a solicitar:");
                String tarjeta = scan.nextLine();
                if (tarjeta.equals("1") || tarjeta.equals("2") || tarjeta.equals("3")) {
                    if (tarjeta.equals("1")) {
                        solicitud=getStatus()+creadorSolicitudesString()+"Simplicity";
                    }else if (tarjeta.equals("2")) {
                        solicitud=getStatus()+creadorSolicitudesString()+"Platino";
                    }else{
                        solicitud=getStatus()+creadorSolicitudesString()+"Oro";
                    }
                    Solicitudes.add(solicitud);
                    band = false;
                } else {
                    System.out.println("Ingrese una opcion disponible");
                }
            }else if (debito.getSaldo() >= 100000) {
                System.out.println("1.-Simplicity");
                System.out.println("2.-Platino");
                System.out.println("Ingrese la tarjeta a solicitar:");
                String tarjeta = scan.nextLine();
                if (tarjeta.equals("1") || tarjeta.equals("2")) {
                    if (tarjeta.equals("1")) {
                        solicitud=getStatus()+creadorSolicitudesString()+"Simplicity";
                    }else{
                        solicitud=getStatus()+creadorSolicitudesString()+"Platino";
                    }
                    Solicitudes.add(solicitud);
                    band = false;
                } else {
                    System.out.println("Ingrese una opcion disponible");
                }
            }else if (debito.getSaldo() >= 50000) {
                System.out.println("1.-Simplicity");
                System.out.println("Ingrese la tarjeta a solicitar:");
                String tarjeta = scan.nextLine();
                if (tarjeta.equals("1")) {
                    solicitud=getStatus()+creadorSolicitudesString()+"Simplicity";
                    Solicitudes.add(solicitud);
                    band = false;
                } else {
                    System.out.println("Ingrese una opcion disponible");
                }
            }


        }



    }
    public int generarId() {
        int id;
        do {
            id = ran.nextInt(10000);
        } while (idsGenerados.contains(id));
        idsGenerados.add(id);
        return id;
    }

    public int getId() {
        return id;
    }
    public String creadorSolicitudesString(){
        return getUsuario()+LocalDate.now()+String.valueOf(debito.getSaldo())+String.valueOf(getId());
    }
    public void versolicitudespropias(){
        System.out.println("Las solicitudes hechas son:");
        for (int i = 0; i < Solicitudes.size(); i++) {
            if(Solicitudes.get(i).contains(getUsuario()))System.out.println((i+1)+" "+Solicitudes.get(i));
        }
    }
    public Cliente modificarNombre(String nuevoNombre) {
        nuevoNombre= validacionesYRegistros.validarNombre(nuevoNombre);
        this.nombre = nuevoNombre;
        return this;
    }

    public Cliente modificarApellidos(String nuevosApellidos) {
        this.apellidos = nuevosApellidos;
        nuevosApellidos = validacionesYRegistros.validarApellido(nuevosApellidos);
        return this;
    }

    public Cliente modificarFechaNacimiento() {
        this.fechaNacimiento = validacionesYRegistros.RegistrarFechaDeNacimiento();
        return this;
    }

    public Cliente modificarCiudad(String nuevaCiudad) {
        this.ciudad = nuevaCiudad;
        nuevaCiudad=validacionesYRegistros.validarCiudad(nuevaCiudad);
        return this;
    }

    public Cliente modificarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
        nuevoEstado=validacionesYRegistros.validarEstado(nuevoEstado);
        return this;
    }

    public Cliente modificarCurp(String nuevaCurp) {
        this.Curp = nuevaCurp;
        return this;
    }

    public Cliente modificarDireccion(String nuevaDireccion) {
        this.direccion = nuevaDireccion;
        nuevaDireccion=validacionesYRegistros.validarDireccion(nuevaDireccion);
        return this;
    }

    public Cliente modificarUsuario(String nuevoUsuario) {
        this.usuario = nuevoUsuario;
        nuevoUsuario=validacionesYRegistros.validarUsuario(nuevoUsuario);
        return this;
    }

    public Cliente modificarContraseña(String nuevaContraseña) {
        this.password = nuevaContraseña;
        return this;
    }
    public Cliente buscarClientePorUsuarioYRFC (String Usuario, String RFC) {
        for (Cliente cliente : banco.listaCliente.values()) {
            if (cliente.getUsuario().equals(Usuario) && cliente.getRFC().equals(RFC) && cliente.getRol().equals(Rol.Cliente)) {
                return cliente;
            }
        }
        System.out.println("no se ha econtrado el cliente");
        return null;
    }
    public void mostrarMenuModificacion(Cliente cliente) {
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
        System.out.println("10. Salir");

        int opcion;
        do {
            System.out.print("Seleccione el número de la opción que desea modificar: ");
            Scanner leer =new Scanner(System.in);
            opcion = leer.nextInt();
            leer.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nuevo nombre: ");
                    cliente.modificarNombre(leer.nextLine());
                    break;
                case 2:
                    System.out.print("Ingrese los nuevos apellidos: ");
                    cliente.modificarApellidos(leer.nextLine());
                    break;
                case 3:
                    cliente.modificarFechaNacimiento();
                    break;
                case 4:
                    cliente.modificarCiudad(leer.nextLine());
                    break;
                case 5:
                    cliente.modificarEstado(leer.nextLine());
                    break;
                case 6:
                    cliente.modificarCurp(leer.nextLine());
                    break;
                case 7:
                    cliente.modificarDireccion(leer.nextLine());
                    break;
                case 8:
                    cliente.modificarUsuario(leer.nextLine());
                    break;
                case 9:
                    cliente.modificarContraseña(leer.nextLine());
                    break;
                case 10:
                    System.out.println("Saliendo del menú de modificación...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        } while (opcion != 10);
    }
}

