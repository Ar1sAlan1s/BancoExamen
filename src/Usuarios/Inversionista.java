package Usuarios;

import Banco.Banco;
import Usuarios.Utils.Rol;
import Usuarios.Utils.Sucursales;
import java.time.LocalDate;
import java.util.ArrayList;
import Banco.ValidacionesYRegistros;
import Banco.Utils.DatosComun;

public class Inversionista extends Usuario {
    ValidacionesYRegistros validacionesYRegistros = new ValidacionesYRegistros();
    double DineroInvertido;

    public Inversionista(String usuario, String password, String nombre, String apellidos, LocalDate fechaNacimiento, String ciudad, String estado, String RFC, String Curp, String direccion, Sucursales sucursales, Rol rol, double DineroInvertido) {
        super(usuario, password, nombre, apellidos, fechaNacimiento, ciudad, estado, RFC, Curp, direccion, sucursales, rol);
        this.DineroInvertido = DineroInvertido;
    }

    public void RegistrarInversionista() {
        ArrayList<String> datosComunes = DatosComun.RegistrarDatosComunes(Rol.Inversionista);
        String nombre = (String) datosComunes.get(0);
        String apellido = (String) datosComunes.get(1);
        LocalDate fechaNacimiento = LocalDate.parse((CharSequence) datosComunes.get(2));
        String ciudad = (String) datosComunes.get(3);
        String estado = (String) datosComunes.get(4);
        String RFC = (String) datosComunes.get(5);
        String Curp = (String) datosComunes.get(6);
        String direccion = (String) datosComunes.get(7);
        String usuario = (String) datosComunes.get(8);
        String contraseña = (String) datosComunes.get(9);

        Inversionista inversionista = new Inversionista(usuario, contraseña, nombre, apellido, fechaNacimiento, ciudad, estado, RFC, Curp, direccion, sucursales, Rol.Inversionista, 0.0);

    }

    public Inversionista modificarNombre(String nuevoNombre) {
        nuevoNombre = validacionesYRegistros.validarNombre(nuevoNombre);
        this.nombre = nuevoNombre;
        return this;
    }

}