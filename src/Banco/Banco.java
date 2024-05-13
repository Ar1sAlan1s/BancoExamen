package Banco;
import Usuarios.Cliente;
import Usuarios.Empleado;
import Usuarios.Inversionista;
import Usuarios.Usuario;
import Usuarios.Utils.Rol;
import Usuarios.Utils.Sucursales;
import Usuarios.Utils.UsuarioActivo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Random;


public class Banco {
    UsuarioActivo UsuarioActivo;
    static Scanner leer = new Scanner(System.in);
    static Random ran = new Random();
    public static final HashMap<Rol, ArrayList<Usuario>> listaUsuarios = new HashMap<>(){{
        put(Rol.Gerente, new ArrayList<>());
    }};
    public HashMap<String, Empleado> listaEmpleado = new HashMap<>();
    public HashMap<String, Cliente> listaCliente = new HashMap<>();
    public HashMap<String, Inversionista> listaInversionista = new HashMap<>();
    double FondoDedinero=0;

    public Banco() {
        Empleado Gerente1 = new Empleado("Gerente1","23","Juan","Perez", LocalDate.now(),"Morelia","Mich","12222","12222","Av Tec de morelia", Sucursales.Acueducto, Rol.Gerente,300000, LocalDate.now());
        this.ValidacionesYRegistros = new ValidacionesYRegistros();
        listaUsuarios.get(Rol.Gerente).add(Gerente1);
    }

    ValidacionesYRegistros ValidacionesYRegistros;
    public void insertarEmpleado(Empleado empleado) {
        listaUsuarios.get(empleado.getRol()).add(empleado);
    }

    public ArrayList<String> RegistrarDatosComunes(){
        System.out.println("ingrese su(s) nombre(s): ");
        String nombre=leer.nextLine();
        nombre= ValidacionesYRegistros.validarNombre(nombre);
        System.out.println("ingrese sus apellido: ");
        String apellido=leer.nextLine();
        apellido= ValidacionesYRegistros.validarApellido(apellido);
        System.out.println("ingrese su usuario: ");
        String usuario=leer.nextLine();
        usuario= ValidacionesYRegistros.validarUsuario(usuario);
        System.out.println("ingrese su contraseña: ");
        String contraseña=leer.nextLine();
        LocalDate fechaDeNacimiento= ValidacionesYRegistros.RegistrarFechaDeNacimiento();
        System.out.println("ingrese su ciudad: ");
        String ciudad=leer.nextLine();
        ciudad= ValidacionesYRegistros.validarCiudad(ciudad);
        System.out.println("ingrese su estado: ");
        String estado=leer.nextLine();
        estado= ValidacionesYRegistros.validarEstado(estado);
        String RFC= ValidacionesYRegistros.generarRFC(nombre,apellido,fechaDeNacimiento);
        System.out.println("Ingrese su Curp: ");
        String Curp=leer.nextLine();
        System.out.println("Ingrese su dirección (colonia, calle, número): ");
        String direccion = leer.nextLine();
        direccion = ValidacionesYRegistros.validarDireccion(direccion);
        ArrayList<String> DatosComun = new ArrayList<>();
        DatosComun.addAll(Arrays.asList(nombre,apellido, fechaDeNacimiento.toString(), ciudad, estado, RFC, Curp,direccion, usuario,contraseña));
        return DatosComun;
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

    public void MostrarClientes(UsuarioActivo usuarioActivo){
        for (int i = 0; i < listaCliente.size(); i++) {
            if (listaCliente.get(i).getSucursales().equals(usuarioActivo.getUsuarioActual().getSucursales())){
                System.out.println("nombre: "+listaCliente.get(i).getNombre()+" "+listaCliente.get(i).getUsuario());
                System.out.println("usuario: "+listaCliente.get(i).getUsuario());
                System.out.println("**************");
            }

        }
    }
    public void MostrarEmpleados(UsuarioActivo usuarioActivo){
        for (int i = 0; i < listaEmpleado.size(); i++) {
            if (listaEmpleado.get(i).getSucursales().equals(usuarioActivo.getUsuarioActual().getSucursales())){
                System.out.println("nombre: "+listaEmpleado.get(i).getNombre()+" "+listaEmpleado.get(i).getUsuario());
                System.out.println("usuario: "+listaEmpleado.get(i).getUsuario());
                System.out.println("************");
            }
        }
    }
    public void MostrarInversionistas(UsuarioActivo usuarioActivo){
        for (int i = 0; i < listaInversionista.size(); i++) {
            if (listaInversionista.get(i).getUsuario().equals(usuarioActivo.getUsuarioActual().getUsuario())){
                System.out.println("nombre: "+listaInversionista.get(i).getNombre()+" "+listaInversionista.get(i).getUsuario());
                System.out.println("usuario: "+listaInversionista.get(i).getUsuario());
                System.out.println("************");
            }
        }
    }


}