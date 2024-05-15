package Usuarios;
import Banco.Banco;
import Banco.Utils.DatosComun;
import Tarjetas.Credito;
import Tarjetas.Debito;
import Tarjetas.Utils.Solicitud;
import Tarjetas.Utils.TiposCredito;
import Usuarios.Utils.Rol;
import Usuarios.Utils.Sucursales;
import Tarjetas.Tarjeta;
import Usuarios.Utils.UsuarioActivo;
import java.time.LocalDate;
import java.util.*;
import static Banco.Banco.listaUsuarios;


public class

Cliente extends Usuario {
    public Debito debito;
    private Rol rol;
    private int id;
    private static Scanner scan = new Scanner(System.in);
    private static Random ran = new Random();
    private String status = "En proceso";
    public ArrayList<Solicitud> Solicitudes = new ArrayList<>();
    public ArrayList<Credito> tarjetasCredito;
    private ArrayList<Integer> idsGenerados = new ArrayList<>();

    public Cliente(String nombre, String apellidos, LocalDate fechaNacimiento, String usuario, String password, String ciudad, String estado, String RFC, String Curp, String direccion, Sucursales sucursales, Rol rol) {
        super(usuario, password, nombre, apellidos, fechaNacimiento, ciudad, estado, RFC, Curp, direccion, sucursales, Rol.Cliente);
        this.rol = rol;
        this.tarjetasCredito = new ArrayList<>();
        this.debito = new Debito(usuario, password, TiposCredito.debito);
        this.id = generarId();
    }

    public static void registrarClienteEnSucursal(Usuario usuario) {
        if (usuario.getRol() == Rol.Gerente || (usuario.getRol() == Rol.Ejecutivos && usuario.getSucursales() == Sucursales.Acueducto) || (usuario.getRol() == Rol.Ejecutivos && usuario.getSucursales() == Sucursales.Madero)) {
            ArrayList<String> datosCliente = DatosComun.RegistrarDatosComunes(Rol.Cliente);
            String nombre = datosCliente.get(0);
            String apellido = datosCliente.get(1);
            LocalDate fechaNacimiento = LocalDate.parse(datosCliente.get(2));
            String nombreUsuario = datosCliente.get(3);
            String contraseña = datosCliente.get(4);
            String ciudad = datosCliente.get(5);
            String estado = datosCliente.get(6);
            String RFC = datosCliente.get(7);
            String Curp = datosCliente.get(8);
            String direccion = datosCliente.get(9);

            Sucursales sucursal = usuario.getSucursales();

            Cliente cliente = new Cliente(nombre,apellido,fechaNacimiento,nombreUsuario,contraseña,ciudad,estado,RFC,Curp,direccion,sucursal,Rol.Cliente);
            if (!Banco.listaUsuarios.containsKey(Rol.Cliente)) {
                Banco.listaUsuarios.put(Rol.Cliente, new ArrayList<>());
            }
            Banco.listaUsuarios.get(Rol.Cliente).add(cliente);
            System.out.println("Cliente registrado exitosamente.");
            Banco.ListaClientes.add(cliente);
        }
    }

    public static void MostrarClientes(Sucursales sucursales) {
        synchronized (listaUsuarios) {
            boolean clientesEncontrados = false;
            System.out.println("Clientes de la sucursal " + sucursales + ":");
            for (ArrayList<Usuario> userList : listaUsuarios.values()) {
                for (Usuario usuario : userList) {
                    if (usuario instanceof Cliente) {
                        Cliente cliente = (Cliente) usuario;
                        if (cliente.getSucursales().equals(sucursales)) {
                            System.out.println("- Nombre: " + cliente.getNombre() + " " + cliente.getApellidos() +
                                    ", Usuario: " + cliente.getUsuario() + ", Contraseña: " + cliente.getPassword());
                            clientesEncontrados = true;
                        }
                    }
                }
            }
            if (!clientesEncontrados) {
                System.out.println("No se encontraron clientes en la sucursal " + sucursales + ".");
            }
        }
    }
    public static void eliminarCliente(String nombreUsuario) {
        Usuario usuarioActual = UsuarioActivo.getInstance().getUsuarioActual();

        if (usuarioActual.getRol() != Rol.Gerente && usuarioActual.getRol() != Rol.Ejecutivos) {
            System.out.println("No tienes permiso para eliminar clientes.");
            return;
        }

        Sucursales sucursalActual = usuarioActual.getSucursales();

        for (Usuario usuario : listaUsuarios.get(Rol.Cliente)) {
            Cliente cliente = (Cliente) usuario;
            if (cliente.getUsuario().equals(nombreUsuario)) {
                if ((usuarioActual.getRol() == Rol.Ejecutivos || usuarioActual.getRol() == Rol.Gerente) && cliente.getSucursales() != sucursalActual) {
                    System.out.println("No tienes permiso para eliminar clientes de otra sucursal.");
                    return;
                }
                listaUsuarios.get(Rol.Cliente).remove(cliente);
                System.out.println("El cliente con nombre de usuario '" + nombreUsuario + "' ha sido eliminado exitosamente.");
                return;
            }
        }

        System.out.println("No se encontró ningún cliente con el nombre de usuario '" + nombreUsuario + "'.");
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String creadorSolicitudesString() {
        return getUsuario() + LocalDate.now() + String.valueOf(debito.getSaldo()) + String.valueOf(getId());
    }


     public Debito getDebito() {
        return debito;
    }
    public void mostrarTarjetas(){
        System.out.println();
        if (tarjetasCredito.isEmpty()){
            System.out.println("No tienes tarjetas de credito");
        }else{
        for (Credito credito : tarjetasCredito) {
            System.out.println();
            credito.mostrarTarjeta();
        }
        }
    }
    public void solicitarTarjetaCredito(Cliente cliente) {
        if(!revisarSolicitud(cliente)){
            System.out.println("Tienes una solicitud pendiente.");
            return;
        }
        boolean bandS = true;
        boolean bandP = true;
        boolean bandO = true;
        Scanner leer = new Scanner(System.in);
        for (Credito credito : tarjetasCredito) {
            if (credito.getTipoCredito()==TiposCredito.simplicity) {
                bandS = false;
            }
            if (credito.getTipoCredito()==TiposCredito.platino) {
                bandP = false;
            }
            if (credito.getTipoCredito()==TiposCredito.oro){
                bandO = false;
            }
        }
        if (!bandS && !bandP && !bandO) {
            System.out.println("Tienes todas las tarjetas, ya no puedes solicitar mas.");
            return;
        }
        if(debito.getSaldo() >= 200000 && bandO){
            System.out.printf("Tu saldo es de $%.2f, puedes solicitar la tarjeta oro.\n", debito.getSaldo());
            System.out.println("Deseas solicitarla Y/N: ");
            String opcion = leer.nextLine();
            if(opcion.equalsIgnoreCase("Y")){
                new Solicitud(TiposCredito.oro,(Cliente)UsuarioActivo.getUsuarioActual());
                System.out.println("Tarjeta solicitada satisfactoriamente.");
            } else{
                System.out.println("Tarjeta no solicitada. ");
            }
        }
        else if(debito.getSaldo() >= 100000 && bandP){
            System.out.printf("Tu saldo es de $%.2f, puedes solicitar la tarjeta platino.\n", debito.getSaldo());
            System.out.println("Deseas solicitarla Y/N: ");
            String opcion = leer.nextLine();
            if(opcion.equalsIgnoreCase("Y")){
                new Solicitud(TiposCredito.platino,(Cliente)UsuarioActivo.getUsuarioActual());
                System.out.println("Tarjeta solicitada satisfactoriamente.");
            } else {
                System.out.println("Tarjeta no solicitada. ");
            }
        }
        else if (debito.getSaldo() >= 50000 && bandS){
            System.out.printf("Tu saldo es de $%.2f, puedes solicitar la tarjeta simplicity.\n", debito.getSaldo());
            System.out.println("Deseas solicitarla Y/N: ");
            String opcion = leer.nextLine();
            if(opcion.equalsIgnoreCase("Y")){
                new Solicitud(TiposCredito.simplicity,(Cliente)UsuarioActivo.getUsuarioActual());
                System.out.println("Tarjeta solicitada satisfactoriamente.");
            } else {
                System.out.println("Tarjeta no solicitada. ");
            }
        }
        else {
            System.out.println("Actualmente no puedes solicitar ninguna tarjeta de credito.");
        }
    }
    public boolean revisarSolicitud(Cliente cliente){
        boolean bandS = true;
        boolean bandP = true;
        boolean bandO = true;
        for (Solicitud solicitud: Solicitudes) {
            if (solicitud.getStatus().equals("En proceso")&&solicitud.getIdCliente()==cliente.getId()) {
                return false;
            }
        }
        return true;
    }
    public void imprimirIndiceTarjetaCredito(){
        for (Credito tarjeta : tarjetasCredito){
            System.out.println("[" + tarjetasCredito.indexOf(tarjeta) + "]");
            tarjeta.mostrarTarjeta();
        }
    }
    public void verSolicitudesTarjetaCreditoClientes(Cliente cliente) {
        if(!Solicitudes.isEmpty()){
            System.out.println("** Solicitudes de tarjeta **");
            for (Solicitud solicitud: Solicitudes){
               if(cliente.getId()==solicitud.getIdCliente()){
                   System.out.println("Numero de solicitud: "+ Solicitudes.indexOf(solicitud));
                solicitud.mostrarSolicitud(cliente);
                System.out.println();
               }
            }
        }else{
            System.out.println("No tiene solicitudes a la espera");
        }

    }

    public void verSolicitudesTarjetaCreditoEmpleados(Cliente cliente) {
        if(!Solicitudes.isEmpty()){
            System.out.println("** Solicitudes de tarjeta **");
            for (Solicitud solicitud: Solicitudes){
                if(solicitud.getStatus().equals("En proceso")&&cliente.getSucursales()==solicitud.getSucursal()){
                    System.out.println("Numero de solicitud: "+ Solicitudes.indexOf(solicitud));
                    solicitud.mostrarSolicitud(cliente);
                    System.out.println();
                }
            }
        }

    }

}