package Banco;
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
}
