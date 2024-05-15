package Usuarios;
import Banco.Banco;
import Banco.Utils.DatosComun;
import Tarjetas.Debito;
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
    public ArrayList<String> Solicitudes = new ArrayList<>();
    public ArrayList<Tarjeta> tarjetasCredito;
    private ArrayList<Integer> idsGenerados = new ArrayList<>();

    public Cliente( String nombre, String apellidos, LocalDate fechaNacimiento,String usuario, String password, String ciudad, String estado, String RFC, String Curp, String direccion, Sucursales sucursales, Rol rol) {
        super(usuario, password, nombre, apellidos, fechaNacimiento, ciudad, estado, RFC, Curp, direccion, sucursales, Rol.Cliente);//Asigno de una vez el Rol Cliente
        this.rol = rol;
        this.tarjetasCredito = new ArrayList<>();
        this.debito = new Debito(usuario, password, TiposCredito.debito);
        this.id = generarId();
    }
    public Cliente( String nombre, String apellidos, LocalDate fechaNacimiento,String usuario, String password, String ciudad, String estado, String RFC, String Curp, String direccion, Sucursales sucursales, Rol rol,Debito debito) {
        super(usuario, password, nombre, apellidos, fechaNacimiento, ciudad, estado, RFC, Curp, direccion, sucursales, Rol.Cliente);//Asigno de una vez el Rol Cliente
        this.rol = rol;
        this.tarjetasCredito = new ArrayList<>();
        this.debito = debito;
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
            // Aquí deberías validar la contraseña

            Sucursales sucursal = usuario.getSucursales();

            Cliente cliente = new Cliente(nombre,apellido,fechaNacimiento,nombreUsuario,contraseña,ciudad,estado,RFC,Curp,direccion,sucursal,Rol.Cliente);
            if (!Banco.listaUsuarios.containsKey(Rol.Cliente)) {
                Banco.listaUsuarios.put(Rol.Cliente, new ArrayList<>());
            }
            Banco.listaUsuarios.get(Rol.Cliente).add(cliente);
            System.out.println("Cliente registrado exitosamente.");
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
                        solicitud = getStatus() + creadorSolicitudesString() + "Simplicity";
                    } else if (tarjeta.equals("2")) {
                        solicitud = getStatus() + creadorSolicitudesString() + "Platino";
                    } else {
                        solicitud = getStatus() + creadorSolicitudesString() + "Oro";
                    }
                    Solicitudes.add(solicitud);
                    band = false;
                } else {
                    System.out.println("Ingrese una opcion disponible");
                }
            } else if (debito.getSaldo() >= 100000) {
                System.out.println("1.-Simplicity");
                System.out.println("2.-Platino");
                System.out.println("Ingrese la tarjeta a solicitar:");
                String tarjeta = scan.nextLine();
                if (tarjeta.equals("1") || tarjeta.equals("2")) {
                    if (tarjeta.equals("1")) {
                        solicitud = getStatus() + creadorSolicitudesString() + "Simplicity";
                    } else {
                        solicitud = getStatus() + creadorSolicitudesString() + "Platino";
                    }
                    Solicitudes.add(solicitud);
                    band = false;
                } else {
                    System.out.println("Ingrese una opcion disponible");
                }
            } else if (debito.getSaldo() >= 50000) {
                System.out.println("1.-Simplicity");
                System.out.println("Ingrese la tarjeta a solicitar:");
                String tarjeta = scan.nextLine();
                if (tarjeta.equals("1")) {
                    solicitud = getStatus() + creadorSolicitudesString() + "Simplicity";
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

    public String creadorSolicitudesString() {
        return getUsuario() + LocalDate.now() + String.valueOf(debito.getSaldo()) + String.valueOf(getId());
    }

    public void versolicitudespropias() {
        System.out.println("Las solicitudes hechas son:");
        for (int i = 0; i < Solicitudes.size(); i++) {
            if (Solicitudes.get(i).contains(getUsuario())) System.out.println((i + 1) + " " + Solicitudes.get(i));
        }
    }

     public Debito getDebito() {
        return debito;
    }
}