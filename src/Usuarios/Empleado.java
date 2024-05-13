package Usuarios;

import Banco.Banco;
import Tarjetas.Credito;
import Tarjetas.Tarjeta;
import Tarjetas.Utils.TiposCredito;
import Usuarios.Utils.Rol;
import Usuarios.Utils.Sucursales;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import Banco.ValidacionesYRegistros;


public class Empleado extends Usuario{
    Scanner scan=new Scanner(System.in);
    double Salario;
    LocalDate fechaDeInicioLaboral;
    Cliente cliente=new Cliente("","","","", LocalDate.now(),"","","", "","", Sucursales.Madero, Rol.Cliente);

    public Empleado(String usuario, String password, String nombre, String apellidos, LocalDate fechaNacimiento, String ciudad, String estado, String RFC, String Curp, String direccion, Sucursales sucursales, Rol rol, double Salario,LocalDate fechaDeInicioLaboral){
        super(usuario,password,nombre,apellidos,fechaNacimiento,ciudad,estado,RFC,Curp,direccion,sucursales,rol);
        this.Salario=Salario;
        this.fechaDeInicioLaboral=fechaDeInicioLaboral;
    }

    public void RegistrarEjecutivo(Empleado gerente){
        Banco banco=new Banco();
        ArrayList<String> datosComunes= banco.RegistrarDatosComunes();
        String nombre = datosComunes.get(0);
        String apellido = datosComunes.get(1);
        LocalDate fechaNacimiento = LocalDate.parse(datosComunes.get(2));
        String ciudad = datosComunes.get(3);
        String estado = datosComunes.get(4);
        String RFC = datosComunes.get(5);
        String Curp = datosComunes.get(6);
        String direccion = datosComunes.get(7);
        String usuario = datosComunes.get(8);
        String contraseña = datosComunes.get(9);
        Sucursales sucursales = gerente.getSucursales();
        double Salario= ValidacionesYRegistros.RegistrarSalario();

        Empleado empleado=new Empleado(usuario,contraseña,nombre,apellido,fechaNacimiento, ciudad,estado,RFC,Curp,direccion,sucursales,Rol.Ejecutivos,Salario,LocalDate.now());
        banco.listaEmpleado.put(empleado.getUsuario(), empleado);
        Banco.listaUsuarios.get(empleado.rol).add(empleado);
    }
    public void RegistrarCapturista(Empleado gerente){
        Banco banco=new Banco();
        ArrayList<String> datosComunes= banco.RegistrarDatosComunes();
        String nombre = datosComunes.get(0);
        String apellido = datosComunes.get(1);
        LocalDate fechaNacimiento = LocalDate.parse(datosComunes.get(2));
        String ciudad = datosComunes.get(3);
        String estado = datosComunes.get(4);
        String RFC = datosComunes.get(5);
        String Curp = datosComunes.get(6);
        String direccion = datosComunes.get(7);
        String usuario = datosComunes.get(8);
        String contraseña = datosComunes.get(9);
        Sucursales sucursales = gerente.getSucursales();
        double Salario= ValidacionesYRegistros.RegistrarSalario();

        Empleado empleado=new Empleado(usuario,contraseña,nombre,apellido,fechaNacimiento, ciudad,estado,RFC,Curp,direccion,sucursales,Rol.Capturista,Salario,LocalDate.now());
        banco.listaEmpleado.put(empleado.getUsuario(), empleado);
        Banco.listaUsuarios.get(empleado.rol).add(empleado);
    }

    public void revisarSolicitudes(){
        boolean band=true;
        System.out.println("Estas son las solicitudes hasta ahora");
        for (int i = 0; i < cliente.Solicitudes.size(); i++) {
            System.out.println((i+1)+" "+cliente.Solicitudes.get(i));
        }
        while(band) {
            try {


                System.out.print("Ingresa el numero de la solicitud a revisar:");
                int opcion = scan.nextInt();
                System.out.println("Esta fue la solicitud que escogiste:" + cliente.Solicitudes.get(opcion - 1));
                System.out.println("Si la apruebas ingresa 1 si no 2");
                int opcion2 = scan.nextInt();
                if (opcion == 1) {
                    String cadenaOriginal = cliente.Solicitudes.get(opcion - 1);
                    String subcadenaExtraida = cadenaOriginal.substring(0, 10); //
                    String cadenaModificada = cadenaOriginal.replace(subcadenaExtraida, "aprobada");
                    cliente.Solicitudes.set(opcion - 1, cadenaModificada);
                    if(cadenaModificada.equals("Simplicity")){Credito credito=new Credito(getUsuario(),getPassword(), TiposCredito.simplicity);
                        cliente.tarjetasCredito.add()}

                    band=false;
                }
                if (opcion == 2) {
                    String cadenaOriginal = cliente.Solicitudes.get(opcion - 1);
                    String subcadenaExtraida = cadenaOriginal.substring(0, 10); //
                    String cadenaModificada = cadenaOriginal.replace(subcadenaExtraida, "no aprobada");
                    cliente.Solicitudes.set(opcion - 1, cadenaModificada);
                    band = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingrese un numero");
            }
        }
    }




    //getters and Setters
    public double getSalario(){
        return Salario;
    }
    public void setSalario(double salario) {
        Salario = salario;
    }
}