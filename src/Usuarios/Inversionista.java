package Usuarios;

import Banco.Banco;
import Usuarios.Utils.Rol;
import Usuarios.Utils.Sucursales;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

import Banco.Utils.DatosComun;

import static Banco.Banco.listaUsuarios;

public class Inversionista extends Usuario {
    double DineroInvertido;
    private static Scanner sc = new Scanner(System.in);

    public Inversionista(String nombre, String apellidos, LocalDate fechaNacimiento, String usuario, String password, String ciudad, String estado, String RFC, String Curp, String direccion, Sucursales sucursales, Rol rol, double DineroInvertido) {
        super(usuario, password, nombre, apellidos, fechaNacimiento, ciudad, estado, RFC, Curp, direccion, sucursales, rol);
        this.DineroInvertido = DineroInvertido;
    }

    public static void RegistrarInversionista(Usuario usuario) {

            ArrayList<String> datosInversionista = DatosComun.RegistrarDatosComunes(Rol.Inversionista);

        String nombre = datosInversionista.get(0);
        String apellido = datosInversionista.get(1);
        LocalDate fechaNacimiento = LocalDate.parse(datosInversionista.get(2));
        String nombreUsuario = datosInversionista.get(3);
        String contraseña = datosInversionista.get(4);
        String ciudad = datosInversionista.get(5);
        String estado = datosInversionista.get(6);
        String RFC = datosInversionista.get(7);
        String Curp = datosInversionista.get(8);
        String direccion = datosInversionista.get(9);

            Sucursales sucursales = usuario.getSucursales();

            Inversionista inversionista = new Inversionista(nombre,apellido,fechaNacimiento,nombreUsuario,contraseña,ciudad,estado,RFC,Curp,direccion,sucursales,Rol.Inversionista, 0.0);
            if (!Banco.listaUsuarios.containsKey(Rol.Inversionista)) {
                Banco.listaUsuarios.put(Rol.Inversionista, new ArrayList<>());
            }
            Banco.listaUsuarios.get(Rol.Inversionista).add(inversionista);
            System.out.println("Inversionista registrado exitosamente.");
        }

    public static void MostrarInversionistas(Sucursales sucursales) {
        synchronized (listaUsuarios) {
            boolean inversionistasEncontrados = false;
            System.out.println("Inversionistas de la sucursal " + sucursales + ":");
            for (ArrayList<Usuario> userList : listaUsuarios.values()) {
                for (Usuario usuario : userList) {
                    if (usuario instanceof Inversionista) {
                        Inversionista inversionista = (Inversionista) usuario;
                        if (inversionista.getSucursales().equals(sucursales)) {
                            System.out.println("- Nombre: " + inversionista.getNombre() + " " + inversionista.getApellidos() +
                                    ", Usuario: " + inversionista.getUsuario() + ", Contraseña: " + inversionista.getPassword());
                           inversionistasEncontrados = true;
                        }
                    }
                }
            }
            if (!inversionistasEncontrados) {
                System.out.println("No se encontraron inversionistas en la sucursal " + sucursales + ".");
            }
        }
    }
    public static void eliminarInversionista(String nombreUsuario, Usuario usuarioActivo) {
        if (usuarioActivo.getRol() == Rol.Gerente) {
            Sucursales sucursalGerente = usuarioActivo.getSucursales();
            ArrayList<Usuario> inversionistas = Banco.listaUsuarios.get(Rol.Inversionista);
            // Buscar al inversionista por su nombre de usuario
            Optional<Usuario> inversionistaOptional = inversionistas.stream()
                    .filter(inversionista -> inversionista.getUsuario().equals(nombreUsuario) && inversionista.getSucursales() == sucursalGerente)
                    .findFirst();

            if (inversionistaOptional.isPresent()) {
                Usuario inversionista = inversionistaOptional.get();
                // Eliminar al inversionista
                inversionistas.remove(inversionista);
                System.out.println("El inversionista ha sido eliminado correctamente.");
            } else {
                System.out.println("No se encontró ningún inversionista con el nombre de usuario proporcionado en la sucursal del gerente.");
            }
        } else {
            System.out.println("Solo el gerente puede eliminar inversionistas.");
        }
    }
}
