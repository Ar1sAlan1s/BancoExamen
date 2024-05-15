package Banco.Utils;

import Banco.ValidacionesYRegistros;
import Usuarios.Utils.Rol;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class DatosComun {
    public static Scanner leer = new Scanner(System.in);
    public static ValidacionesYRegistros validaciones = new ValidacionesYRegistros();

    public static ArrayList<String> RegistrarDatosComunes(Rol rol) {
        ArrayList<String> DatosComun = new ArrayList();
        System.out.println("ingrese su(s) nombre(s): ");
        String nombre = leer.nextLine();
        nombre = validaciones.validarNombre(nombre);
        System.out.println("ingrese sus apellido: ");
        String apellido = leer.nextLine();
        apellido = validaciones.validarApellido(apellido);
        LocalDate fechaDeNacimiento = validaciones.RegistrarFechaDeNacimiento();
        System.out.println("ingrese su usuario: ");
        String usuario = leer.nextLine();
        usuario = validaciones.validarUsuario(usuario);
        System.out.println("ingrese su contraseña: ");
        String contraseña = leer.nextLine();
        System.out.println("ingrese su ciudad: ");
        String ciudad = leer.nextLine();
        ciudad = validaciones.validarCiudad(ciudad);
        System.out.println("ingrese su estado: ");
        String estado = leer.nextLine();
        estado = validaciones.validarEstado(estado);
        String RFC = validaciones.generarRFC(nombre, apellido, fechaDeNacimiento);
        System.out.println("Ingrese su Curp: ");
        String Curp = leer.nextLine();
        System.out.println("Ingrese su dirección (colonia, calle, número): ");
        String direccion = leer.nextLine();
        direccion = validaciones.validarDireccion(direccion);
        DatosComun.add(nombre);
        DatosComun.add(apellido);
        DatosComun.add(fechaDeNacimiento.toString());
        DatosComun.add(usuario);
        DatosComun.add(contraseña);
        DatosComun.add(ciudad);
        DatosComun.add(estado);
        DatosComun.add(RFC);
        DatosComun.add(Curp);
        DatosComun.add(direccion);
        return DatosComun;
    }
    public static LocalDate preguntarFecha() {
        while(true) {
            System.out.println("Mes: ");
            int mes = Herramientas.nextInt();
            System.out.println("Dia: ");
            int dia = Herramientas.nextInt();
            System.out.println("Año: ");
            int año = Herramientas.nextInt();
            leer.nextLine();
            if (mes >= 1 && mes <= 12 && dia >= 1 && dia <= 31) {
                return LocalDate.of(año, mes, dia);
            }
            System.out.println("Fecha de nacimiento mal ingresada.");
            System.out.println("Vuelva a ingresar la fecha de nacimiento.");
        }
    }
}
