package Usuarios;

import Banco.Banco;
import Usuarios.Utils.Rol;
import Usuarios.Utils.Sucursales;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import Banco.ValidacionesYRegistros;

public class Inversionista extends Usuario {
    ValidacionesYRegistros validacionesYRegistros=new ValidacionesYRegistros();
    double DineroInvertido;
    private Banco banco = new Banco();
    public Inversionista(String usuario, String password, String nombre, String apellidos, LocalDate fechaNacimiento, String ciudad, String estado, String RFC, String Curp, String direccion, Sucursales sucursales, Rol rol, double DineroInvertido) {
        super(usuario, password, nombre, apellidos, fechaNacimiento, ciudad, estado, RFC, Curp, direccion, sucursales, rol);
        this.DineroInvertido = DineroInvertido;
    }

    public void RegistrarInversionista(Empleado gerente) {
        Banco banco = new Banco();
        ArrayList<String> datosComunes = banco.RegistrarDatosComunes();
        String nombre = (String)datosComunes.get(0);
        String apellido = (String)datosComunes.get(1);
        LocalDate fechaNacimiento = LocalDate.parse((CharSequence)datosComunes.get(2));
        String ciudad = (String)datosComunes.get(3);
        String estado = (String)datosComunes.get(4);
        String RFC = (String)datosComunes.get(5);
        String Curp = (String)datosComunes.get(6);
        String direccion = (String)datosComunes.get(7);
        String usuario = (String)datosComunes.get(8);
        String contraseña = (String)datosComunes.get(9);
        Sucursales sucursales = gerente.getSucursales();
        Inversionista inversionista = new Inversionista(usuario, contraseña, nombre, apellido, fechaNacimiento, ciudad, estado, RFC, Curp, direccion, sucursales, Rol.Inversionista, 0.0);
        banco.listaInversionista.put(inversionista.getUsuario(), inversionista);
        ((ArrayList)Banco.listaUsuarios.get(inversionista.getRol())).add(inversionista);
    }
    public Inversionista modificarNombre(String nuevoNombre) {
        nuevoNombre= validacionesYRegistros.validarNombre(nuevoNombre);
        this.nombre = nuevoNombre;
        return this;
    }

    public Inversionista modificarApellidos(String nuevosApellidos) {
        this.apellidos = nuevosApellidos;
        nuevosApellidos = validacionesYRegistros.validarApellido(nuevosApellidos);
        return this;
    }

    public Inversionista modificarFechaNacimiento() {
        this.fechaNacimiento = validacionesYRegistros.RegistrarFechaDeNacimiento();
        return this;
    }

    public Inversionista modificarCiudad(String nuevaCiudad) {
        this.ciudad = nuevaCiudad;
        nuevaCiudad=validacionesYRegistros.validarCiudad(nuevaCiudad);
        return this;
    }

    public Inversionista modificarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
        nuevoEstado=validacionesYRegistros.validarEstado(nuevoEstado);
        return this;
    }

    public Inversionista modificarCurp(String nuevaCurp) {
        this.Curp = nuevaCurp;
        return this;
    }

    public Inversionista modificarDireccion(String nuevaDireccion) {
        this.direccion = nuevaDireccion;
        nuevaDireccion=validacionesYRegistros.validarDireccion(nuevaDireccion);
        return this;
    }

    public Inversionista modificarUsuario(String nuevoUsuario) {
        this.usuario = nuevoUsuario;
        nuevoUsuario=validacionesYRegistros.validarUsuario(nuevoUsuario);
        return this;
    }

    public Inversionista modificarContraseña(String nuevaContraseña) {
        this.password = nuevaContraseña;
        return this;
    }
    public Inversionista buscarClientePorUsuarioYRFC (String Usuario, String RFC) {
        for (Inversionista Inversionista : banco.listaInversionista.values()) {
            if (Inversionista.getUsuario().equals(Usuario) && Inversionista.getRFC().equals(RFC) && Inversionista.getRol().equals(Rol.Cliente)) {
                return Inversionista;
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