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


public class Cliente extends Usuario {
    Random ran = new Random();
    private LocalDate fechaDeRegistro;
    Debito debito;
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
        Banco banco = new Banco(); // Esto podría no ser necesario si ya tienes una instancia de Banco
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

        // Comprueba si el cliente ya existe
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
}

