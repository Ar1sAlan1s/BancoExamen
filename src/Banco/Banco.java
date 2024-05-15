package Banco;
import Banco.Utils.HistorialTransaccion;
import Usuarios.Empleado;
import Usuarios.Usuario;
import Usuarios.Utils.Rol;
import Usuarios.Utils.Sucursales;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import Usuarios.Cliente;

public class Banco {
    public static Scanner leer = new Scanner(System.in);
    public static final ArrayList<Cliente> ListaClientes=new ArrayList<>();
    public static final HashMap<Rol, ArrayList<Usuario>> listaUsuarios = new HashMap<>(){{
        put(Rol.Gerente, new ArrayList<>());}};
    public static double FondoDedinero = 0;
    public Banco() {
        Empleado Gerente1 = new Empleado("Gerente1","23","Juan","Perez", LocalDate.now(),"Morelia","Mich","12222","12222","Av Tec de morelia", Sucursales.Acueducto, Rol.Gerente,300000, LocalDate.now());
        listaUsuarios.get(Rol.Gerente).add(Gerente1);
        Empleado Gerente2 = new Empleado("Gerente2","33","Martin","Medina", LocalDate.now(),"Morelia","Mich","172313","41241","Av Rosedal", Sucursales.Madero, Rol.Gerente,300000, LocalDate.now());
        listaUsuarios.get(Rol.Gerente).add(Gerente2);
    }
    public Usuario comprobarInicioSesion(String usuario, String contraseña) {
        for (ArrayList<Usuario> userList : listaUsuarios.values()) {
            for (Usuario usuarioActual : userList) {
                if (usuarioActual.getUsuario().equals(usuario) && usuarioActual.getPassword().equals(contraseña)) {
                    return usuarioActual;
                }
            }
        }
        return null;
    }
    public static Usuario buscarUsuarioPorNombreUsuario(String nombreUsuario) {
        // Itera sobre todas las listas de usuarios en el HashMap
        for (ArrayList<Usuario> userList : listaUsuarios.values()) {
            // Itera sobre los usuarios en la lista actual
            for (Usuario usuario : userList) {
                // Comprueba si el usuario actual tiene el nombre de usuario buscado
                if (usuario.getUsuario().equals(nombreUsuario)) {
                    return usuario; // Retorna el usuario si se encuentra
                }
            }
        }
        return null; // Retorna null si no se encuentra ningún usuario con ese nombre de usuario
    }
    public static void agregarFondos(double monto, Usuario usuario, LocalDate fecha) {
        if (usuario.getRol() == Rol.Inversionista) {
            FondoDedinero += monto; // Aumenta el fondo total del banco
            // Registra la transacción en el historial
            String nombreUsuario = usuario.getUsuario();
            String RFC = usuario.getRFC();
            String detalle = "Inversionista " + nombreUsuario + " agregó fondos por $" + monto;
            HistorialTransaccion.registrarTransaccion(detalle, nombreUsuario, RFC, fecha, usuario.getSucursales());
            System.out.println("Fondos agregados exitosamente.");
        } else {
            System.out.println("Solo los inversionistas pueden agregar fondos.");
        }
    }
}
