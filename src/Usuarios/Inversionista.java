package Usuarios;

import Banco.Banco;
import Usuarios.Utils.Rol;
import Usuarios.Utils.Sucursales;
import java.time.LocalDate;
import java.util.ArrayList;

import Banco.ValidacionesYRegistros;
import Banco.Utils.DatosComun;

import static Banco.Banco.listaUsuarios;

public class Inversionista extends Usuario {
    double DineroInvertido;

    public Inversionista(String usuario, String password, String nombre, String apellidos, LocalDate fechaNacimiento, String ciudad, String estado, String RFC, String Curp, String direccion, Sucursales sucursales, Rol rol, double DineroInvertido) {
        super(usuario, password, nombre, apellidos, fechaNacimiento, ciudad, estado, RFC, Curp, direccion, sucursales, rol);
        this.DineroInvertido = DineroInvertido;
    }

    public void RegistrarInversionista(Usuario usuario) {

            ArrayList<String> datosComunes = DatosComun.RegistrarDatosComunes(Rol.Inversionista);

            String nombre = datosComunes.get(0);
            String apellido = datosComunes.get(1);
            LocalDate fechaNacimiento = LocalDate.parse(datosComunes.get(2));
            String ciudad = datosComunes.get(3);
            String estado = datosComunes.get(4);
            String RFC = datosComunes.get(5);
            String Curp = datosComunes.get(6);
            String direccion = datosComunes.get(7);
            String nombreUsuario = datosComunes.get(8);
            String contraseña = datosComunes.get(9);

            Sucursales sucursales = usuario.getSucursales();

            Inversionista inversionista = new Inversionista(nombreUsuario, contraseña, nombre, apellido, fechaNacimiento, ciudad, estado, RFC, Curp, direccion, sucursales, Rol.Inversionista, 0.0);
            if (!Banco.listaUsuarios.containsKey(Rol.Inversionista)) {
                Banco.listaUsuarios.put(Rol.Inversionista, new ArrayList<>());
            }
            Banco.listaUsuarios.get(Rol.Inversionista).add(inversionista);
            System.out.println("Cliente registrado exitosamente.");
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
                System.out.println("No se encontraron clientes en la sucursal " + sucursales + ".");
            }
        }
    }
    }
