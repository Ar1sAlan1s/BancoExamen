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

    public static void RegistrarEjecutivo(Usuario usuarioactual) {
        ArrayList<String> datosComunEmpleado = DatosComun.RegistrarDatosComunes(Rol.Ejecutivos);
        String nombre = datosComunEmpleado.get(0);
        String apellido = datosComunEmpleado.get(1);
        LocalDate fechaNacimiento = LocalDate.parse(datosComunEmpleado.get(2));
        String ciudad = datosComunEmpleado.get(3);
        String estado = datosComunEmpleado.get(4);
        String RFC = datosComunEmpleado.get(5);
        String Curp = datosComunEmpleado.get(6);
        String direccion = datosComunEmpleado.get(7);
        String usuario = datosComunEmpleado.get(8);
        String contraseña = datosComunEmpleado.get(9);
        Sucursales sucursal= usuarioactual.getSucursales();
        double Salario = ValidacionesYRegistros.RegistrarSalario();
        Empleado Ejecutivo=new Empleado(usuario,contraseña,nombre,apellido,fechaNacimiento,ciudad,estado,RFC,Curp,direccion,sucursal,Rol.Ejecutivos,Salario,LocalDate.now());
        if (!Banco.listaUsuarios.containsKey(Rol.Ejecutivos)) {
            Banco.listaUsuarios.put(Rol.Ejecutivos, new ArrayList<>());
        }
        Banco.listaUsuarios.get(Rol.Ejecutivos).add(Ejecutivo);
        System.out.println("Ejecutivo registrado exitosamente.");

    }

    public static void RegistrarCapturista(Usuario usuarioActual) {
        ArrayList<String> datosComunEmpleado = DatosComun.RegistrarDatosComunes(Rol.Capturista);
        String nombre = datosComunEmpleado.get(0);
        String apellido = datosComunEmpleado.get(1);
        LocalDate fechaNacimiento = LocalDate.parse(datosComunEmpleado.get(2));
        String ciudad = datosComunEmpleado.get(3);
        String estado = datosComunEmpleado.get(4);
        String RFC = datosComunEmpleado.get(5);
        String Curp = datosComunEmpleado.get(6);
        String direccion = datosComunEmpleado.get(7);
        String usuario = datosComunEmpleado.get(8);
        String contraseña = datosComunEmpleado.get(9);
        Sucursales sucursal= usuarioActual.getSucursales();
        double Salario = ValidacionesYRegistros.RegistrarSalario();
        Empleado Ejecutivo=new Empleado(usuario,contraseña,nombre,apellido,fechaNacimiento,ciudad,estado,RFC,Curp,direccion,sucursal,Rol.Capturista,Salario,LocalDate.now());
        if (!Banco.listaUsuarios.containsKey(Rol.Capturista)) {
            Banco.listaUsuarios.put(Rol.Capturista, new ArrayList<>());
        }
        Banco.listaUsuarios.get(Rol.Capturista).add(Ejecutivo);
        System.out.println("Capturista registrado exitosamente.");

    }
    public static void MostrarEjecutivos(Sucursales sucursales) {
        synchronized (listaUsuarios) {
            boolean EjecutivosEncontrados = false;
            System.out.println("Ejecutivos de la sucursal " + sucursales + ":");
            for (ArrayList<Usuario> userList : listaUsuarios.values()) {
                for (Usuario usuario : userList) {
                    if (usuario instanceof Empleado) {
                        Empleado Ejecutivo = (Empleado) usuario;
                        if (Ejecutivo.getSucursales().equals(sucursales)) {
                            System.out.println("- Nombre: " + Ejecutivo.getNombre() + " " + Ejecutivo.getApellidos() +
                                    ", Usuario: " + Ejecutivo.getUsuario() + ", Contraseña: " + Ejecutivo.getPassword());
                            EjecutivosEncontrados= true;
                        }
                    }
                }
            }
            if (!EjecutivosEncontrados) {
                System.out.println("No se encontraron ejecutivos en la sucursal " + sucursales + ".");
            }
        }
    }
    public static void MostrarCapturistas(Sucursales sucursales) {
        synchronized (listaUsuarios) {
            boolean CapturistasEncontrados = false;
            System.out.println("Capturistas de la sucursal " + sucursales + ":");
            for (ArrayList<Usuario> userList : listaUsuarios.values()) {
                for (Usuario usuario : userList) {
                    if (usuario instanceof Empleado) {
                        Empleado capturista = (Empleado) usuario;
                        if (capturista.getSucursales().equals(sucursales) && capturista.getRol() == Rol.Capturista) {
                            System.out.println("- Nombre: " + capturista.getNombre() + " " + capturista.getApellidos() +
                                    ", Usuario: " + capturista.getUsuario() + ", Contraseña: " + capturista.getPassword());
                            CapturistasEncontrados = true;
                        }
                    }
                }
            }
            if (!CapturistasEncontrados) {
                System.out.println("No se encontraron capturistas en la sucursal " + sucursales + ".");
            }
        }
    }

    public static void MostrarTodosEmpleados(Sucursales sucursales) {
        synchronized (listaUsuarios) {
            boolean EmpleadosEncontrados = false;
            System.out.println("Empleados de la sucursal " + sucursales + ":");
            for (ArrayList<Usuario> userList : listaUsuarios.values()) {
                for (Usuario usuario : userList) {
                    if (usuario instanceof Empleado) {
                        Empleado empleado = (Empleado) usuario;
                        if (empleado.getSucursales().equals(sucursales)) {
                            System.out.println("- Nombre: " + empleado.getNombre() + " " + empleado.getApellidos() +
                                    ", Rol: " + empleado.getRol() +
                                    ", Usuario: " + empleado.getUsuario() + ", Contraseña: " + empleado.getPassword());
                            EmpleadosEncontrados = true;
                        }
                    }
                }
            }
            if (!EmpleadosEncontrados) {
                System.out.println("No se encontraron empleados en la sucursal " + sucursales + ".");
            }
        }
    }
}