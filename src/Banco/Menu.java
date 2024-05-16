package Banco;

import Banco.Utils.Herramientas;
import Banco.Utils.HistorialTransaccion;
import Tarjetas.Credito;
import Tarjetas.Debito;
import Tarjetas.Utils.Solicitud;
import Usuarios.Cliente;
import Usuarios.Empleado;
import Usuarios.Inversionista;
import Usuarios.Usuario;
import Usuarios.Utils.Rol;
import Usuarios.Utils.UsuarioActivo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private Banco banco = new Banco();
    private Scanner sc = new Scanner(System.in);
    private Debito debito;
    private String contraseñaSeguridad = "B@nc0";
    private Usuario usuarioActual;
public ArrayList<Solicitud>todassolis=new ArrayList<>();
    //Verificar el inicio de sesión
    public void iniciarSesion() {
        boolean sonDatosCorrectos = false;

        do {
            System.out.println("*Bienvenido al inicio de sesión del banco*");
            System.out.println("Inicie sesión para continuar.");

            System.out.println("Ingresa el nombre de usuario: ");
            String usuario = sc.nextLine();

            System.out.println("Ingresa el password: ");
            String password = sc.nextLine();

            usuarioActual = banco.comprobarInicioSesion(usuario, password);

            if (usuarioActual != null) {
                UsuarioActivo.getInstance().setUsuario(usuarioActual);
                sonDatosCorrectos = true;
                System.out.println("¡Bienvenido! " + usuarioActual.getRol());
                asignarMenu();
            }else{
                System.out.println("Usuario o contraseña incorrecta. Intenta de nuevo.");
            }
        } while (!sonDatosCorrectos);
    }
    //Función Lambda para los menus de cada rol
    public void asignarMenu() {
        switch (UsuarioActivo.getInstance().getUsuarioActual().getRol()) {
            case Gerente -> seleccionarMenuGerente();
            case Cliente -> seleccionarMenuCliente();
            case Ejecutivos -> seleccionarMenuEjecutivo();
            case Capturista -> seleccionarMenuCapturistas();
            case Inversionista -> seleccionarMenuInversionista();
        }
    }

    private void seleccionarMenuCliente() {
        int opcionCliente = 0;
        Cliente cliente = (Cliente) UsuarioActivo.getUsuarioActual();

        do {
            System.out.println("1.-Debito.");
            System.out.println("2.-Credito.");
            System.out.println("3.-Ver solicitudes de credito");
            System.out.println("4.-hacer solicitud de credito");
            System.out.println("5.-Cerrar sesión Actual.");
            System.out.println("Seleccione algo de la lista por favor: ");

            opcionCliente = Herramientas.nextInt();

            switch (opcionCliente) {
                case 1:
                    int opcionDeRetiro = 0;
                    do{
                        System.out.println("Que desea hacer con debito");
                        System.out.println("1.-Agregar a Debito");
                        System.out.println("2.-Retirar de Debito.");
                        System.out.println("3.-Mostrar datos de la tarjeta de debito.");
                        System.out.println("4.-Regresar al menú principal.");
                        System.out.println("Elija una de las opciones: ");

                        opcionDeRetiro = Herramientas.nextInt();

                        switch (opcionDeRetiro){
                            case 1:
                                cliente.debito.agregarDinero();
                                cliente.debito.setHoraDeUltimoMovimiento(LocalTime.now());
                                cliente.debito.setFechaDeUltimoMovimiento(LocalDate.now());
                                break;
                            case 2:
                                cliente.debito.retirarDinero(cliente);
                                cliente.debito.setHoraDeUltimoMovimiento(LocalTime.now());
                                cliente.debito.setFechaDeUltimoMovimiento(LocalDate.now());
                                break;
                            case 3:
                                cliente.debito.mostrarTarjeta();
                                break;
                            case 4:
                                System.out.println("Volviendo al menú principal. . .");
                                break;
                            default:
                                System.out.println("Opción no valida.");
                        }
                    }while(opcionDeRetiro != 4);
                    break;
                case 2:
                    int opcionDetarjeta=0;
                    int opcion = 0;
                    if (cliente.tarjetasCredito.isEmpty()){break;}
                    do {

                        cliente.imprimirIndiceTarjetaCredito();
                        System.out.println();
                        System.out.println("4.-Si quiere salir al menu");
                        System.out.println("Elija una de las opciones: ");
                        opcionDetarjeta = Herramientas.nextInt();
                        do {
                            System.out.println("Que deseas hacer con esta tarjeta de credito");
                            System.out.println("1.-Pagar con esta tarjeta");
                            System.out.println("2.-Abonar a la tarjeta");
                            System.out.println("3.-Mostrar datos de la tarjeta");
                            System.out.println("4.- salir del menu");
                            opcion=Herramientas.nextInt();
                            switch (opcion) {
                                case 1:
                                    int intentos=0;
                                    boolean band2=true;
                                    while(band2) {

                                        if (intentos==3){
                                            System.out.println("Intentos superados");
                                            System.out.println("Saliendo de retirar el dinero....");
                                            band2=false;}
                                        try {
                                            System.out.println("Ingresa el dinero a retirar:");
                                           double dineroRetirado = sc.nextDouble();
                                            System.out.println("Ingresa el CVV:");
                                            int cvvIngresado = sc.nextInt();


                                            if (cliente.tarjetasCredito.get(opcionDetarjeta).getCVV()==cvvIngresado) {
                                                if (dineroRetirado <= cliente.tarjetasCredito.get(opcionDetarjeta).getSaldo()) {
                                                    cliente.tarjetasCredito.get(opcionDetarjeta).setSaldo(cliente.tarjetasCredito.get(opcionDetarjeta).getSaldo()-dineroRetirado);
                                                    break;
                                                } else {
                                                    System.out.println("No tienes el suficiente dinero");

                                                }
                                            }else{
                                                System.out.println("CVV incorrecto");
                                                intentos++;
                                            }

                                        } catch (InputMismatchException var3) {
                                            System.out.println("Ingresa un numero de dinero o en seu caso el CVV correcto");
                                            sc.nextLine();
                                        }
                                    }
                                    break;
                                case 2:
                                    int intentos2=0;
                                    boolean band3=true;
                                    while(band3) {

                                        if (intentos2==3){
                                            System.out.println("Intentos superados");
                                            System.out.println("Saliendo de retirar el dinero....");
                                            band2=false;}
                                        try {
                                            System.out.println("Ingresa el dinero a pagar:");
                                            double dineroRetirado = sc.nextDouble();
                                            System.out.println("Ingresa el CVV:");
                                            int cvvIngresado = sc.nextInt();


                                            if (cliente.tarjetasCredito.get(opcionDetarjeta).getCVV()==cvvIngresado) {

                                                cliente.tarjetasCredito.get(opcionDetarjeta).setSaldo(cliente.tarjetasCredito.get(opcionDetarjeta).getSaldo()+dineroRetirado);
                                                break;



                                            }else{
                                                System.out.println("CVV incorrecto");
                                                intentos2++;
                                            }

                                        } catch (InputMismatchException var3) {
                                            System.out.println("Ingresa un numero de dinero o en seu caso el CVV correcto");
                                            sc.nextLine();
                                        }
                                    }
                                    break;
                                    case 3:
                                        cliente.tarjetasCredito.get(opcionDetarjeta).mostrarTarjeta();
                                        break;
                                    case 4:
                                        break;
                            }


                        }while (opcion !=4);
                    } while (opcionDetarjeta != 4);

                    break;
                case 3:
                        cliente.verSolicitudesTarjetaCreditoClientes(cliente);
                    break;
                case 4:
                   Solicitud soli= cliente.solicitarTarjetaCredito(cliente);
                    if (soli != null) {
                        todassolis.add(soli);
                    }

                    break;
                case 5:
                    System.out.println("Sesión cerrada...");
                    UsuarioActivo.getInstance().cerrarSesionActiva();
                    ejecutarMenuBanco();
                    break;
                default:
                    System.out.println("Opción no valida. Elija cualquiera en el rango de 1 a 5.");
            }
        } while (opcionCliente != 5);
    }
    private void seleccionarMenuGerente() {
        int opcionGerente = 0;
        Usuario usuario=usuarioActual;
        do{
            System.out.println("MENU GERENTE");
            System.out.println("1.-Registrar Cliente, Capturista, Ejecutivo o Inversionista");
            System.out.println("2.-Eliminar Cliente, Capturista, Ejecutivo o Inversionista");
            System.out.println("3.-Modificar Cliente, Capturista, Ejecutivo o Inversionista");
            System.out.println("4.-Mostrar Clientes, Capturistas, Ejecutivos o Inversionistas");
            System.out.println("5.-Consultar solicitudes de tarjetas en el sistema.");
            System.out.println("6.-Consultar Inversiones en el banco.");
            System.out.println("7.-Cerrar sesión Actual.");
            System.out.println("Ingrese la opción deseada: ");

            opcionGerente = Herramientas.nextInt();

            switch (opcionGerente){
                case 1:
                    int opcionRegistrar = 0;
                    do {
                        System.out.println("Has elegido registrar");
                        System.out.println("¿A quien desea registrar?");
                        System.out.println("1.-Cliente.");
                        System.out.println("2.-Capturista.");
                        System.out.println("3.-Ejecutivo.");
                        System.out.println("4.-Inversionista.");
                        System.out.println("5.-Salir.");
                        System.out.println("Seleccione una opción: ");

                        opcionRegistrar = Herramientas.nextInt();

                        switch (opcionRegistrar){
                            case 1:
                                Cliente.registrarClienteEnSucursal(usuarioActual);
                                break;
                            case 2:
                                Empleado.RegistrarCapturista(usuarioActual);
                                break;
                            case 3:
                                Empleado.RegistrarEjecutivo(usuarioActual);
                                break;
                            case 4:
                                System.out.println("Ingrese la contraseña de seguridad antes de continuar: ");
                                String contraseñaGerente = sc.nextLine();
                                if(contraseñaGerente.equals(contraseñaSeguridad)){
                                    System.out.println("Contraseña correcta.");
                                    Inversionista.RegistrarInversionista(usuarioActual);
                                }else{
                                    System.out.println("\nContraseña incorrecta. Intente de nuevo.");
                                }
                                break;
                            case 5:
                                System.out.println("\nSaliendo del Registro. . .");
                                break;
                            default:
                                System.out.println("Opción invalida. Vuelva a intentarlo");
                        }
                    }while(opcionRegistrar != 5);
                    break;
                case 2:
                    int opcionEliminar = 0;
                    do {
                        System.out.println("Has elegido eliminar");
                        System.out.println("¿A quien desea Eliminar?");
                        System.out.println("1.-Cliente.");
                        System.out.println("2.-Capturista.");
                        System.out.println("3.-Ejecutivo.");
                        System.out.println("4.-Inversionista.");
                        System.out.println("5.-Salir.");
                        System.out.println("Seleccione una opcion: ");

                        opcionEliminar = Herramientas.nextInt();

                        switch (opcionEliminar){
                            case 1:
                                System.out.println("Ingrese el nombre de usuario del cliente: ");
                                String eliminarCliente = sc.nextLine();
                                Cliente.eliminarCliente(eliminarCliente);
                                break;
                            case 2:
                                System.out.println("Ingrese el nombre de usuario del capturista: ");
                                String eliminarCapturista = sc.nextLine();
                                Empleado.eliminarCapturista(eliminarCapturista, usuarioActual);
                                break;
                            case 3:
                                System.out.println("Ingresa el nombre de usuario del ejecutivo: ");
                                String eliminarEjecutivo = sc.nextLine();
                                Empleado.eliminarEjecutivo(eliminarEjecutivo, usuarioActual);
                                break;
                            case 4:
                                System.out.println("Ingrese la contraseña de seguridad antes de continuar: ");
                                String contraseñaGerente = sc.nextLine();
                                if(contraseñaGerente.equals(contraseñaSeguridad)){
                                    System.out.println("Contraseña correcta.");

                                    System.out.println("\nProporcione el nombre de usuario del inversionista a eliminar: ");
                                    String eliminarInversionista = sc.nextLine();
                                    Inversionista.eliminarInversionista(eliminarInversionista, usuarioActual);

                                }else{
                                    System.out.println("\nContraseña incorrecta. Intente de nuevo.");
                                }
                                break;
                            case 5:
                                System.out.println("\nSaliendo de la eliminación. . .");
                                break;
                            default:
                                System.out.println("Opcion invalida. Vuelva a intentarlo");

                        }
                    }while(opcionEliminar != 5);
                    break;
                case 3:
                    int opcionModificar = 0;
                    do {
                        System.out.println("Has elegido modificar");
                        System.out.println("¿A quien desea modificar?");
                        System.out.println("1.-Cliente.");
                        System.out.println("2.-Capturista.");
                        System.out.println("3.-Ejecutivo.");
                        System.out.println("4.-Inversionista.");
                        System.out.println("5.-Salir.");
                        System.out.println("Seleccione una opción: ");

                        opcionModificar = Herramientas.nextInt();

                        switch (opcionModificar){
                            case 1:

                                break;
                            case 2:

                                break;
                            case 3:

                                break;
                            case 4:

                                break;
                            case 5:
                                System.out.println("\nSaliendo de la modificación. . .");
                                break;
                            default:
                                System.out.println("Opción invalida. Vuelva a intentarlo");

                        }
                    }while(opcionModificar != 5);
                    break;
                case 4:
                    int opcionMostrar = 0;
                    do {
                        System.out.println("Has elegido Mostrar");
                        System.out.println("¿A quienes desea mostrar en especifico?");
                        System.out.println("1.-Cliente.");
                        System.out.println("2.-Capturista.");
                        System.out.println("3.-Ejecutivo.");
                        System.out.println("4.-Inversionista.");
                        System.out.println("5.- Mostrar todos los empleados.");
                        System.out.println("6.-Salir.");
                        System.out.println("Seleccione una opción: ");

                        opcionMostrar = Herramientas.nextInt();

                        switch (opcionMostrar){
                            case 1:
                                UsuarioActivo.getInstance();
                                Cliente.MostrarClientes(UsuarioActivo.getUsuarioActual().getSucursales());
                                break;
                            case 2:
                                Empleado.MostrarCapturistas(UsuarioActivo.getInstance().getUsuarioActual().getSucursales());
                                break;
                            case 3:
                                Empleado.MostrarEjecutivos(UsuarioActivo.getInstance().getUsuarioActual().getSucursales());
                                break;
                            case 4:
                                Inversionista.MostrarInversionistas(UsuarioActivo.getInstance().getUsuarioActual().getSucursales());
                                break;
                            case 5:
                                Empleado.MostrarTodosEmpleados(UsuarioActivo.getInstance().getUsuarioActual().getSucursales());
                                break;
                            case 6:
                                System.out.println("\nSaliendo de mostrar en especifico. . .");
                                break;
                            default:
                                System.out.println("Opcion invalida. Vuelva a intentarlo");
                        }
                    }while(opcionMostrar != 6);
                    break;
                case 5:
                        for (Solicitud solicitud:todassolis){
                            int i=0;
                            System.out.println("No de solicitud:"+i);
                            solicitud.mostrarSolicitud(UsuarioActivo.getUsuarioActual().getSucursales());
                            i++;
                        }
                    System.out.println("Ingrese el indice que de la solicitud a revisar,Si no tiene opcion Ingrese el siguiente numero 1212");
                    int indice = sc.nextInt();
                    if(indice == 1212){
                        System.out.println("\nSaliendo de revisar la solicitud. . .");
                        break;
                    }
                    sc.nextLine();
                    System.out.println("Ingrese A si la APRUEBA o R si la RECHAZA");
                    String opcion= sc.nextLine();
                    if(opcion.equals("A")){
                        todassolis.get(indice).aprobarSolicitud();
                    }else{
                        todassolis.get(indice).rechazarSolicitud();
                    }
                    break;
                case 6:
                    HistorialTransaccion.mostrarHistorialPorSucursal(usuarioActual.getSucursales(), usuarioActual);
                    break;
                case 7:
                    System.out.println("\nCerrando la sesión actual. Redireccionando al inicio de sesión.");
                    UsuarioActivo.getInstance().cerrarSesionActiva();
                    ejecutarMenuBanco();
                    break;
                default:
                    System.out.println("Opción invalida. Vuelva a intentarlo");
            }
        }while(opcionGerente != 7);
    }

    public void seleccionarMenuEjecutivo(){
        int opcionEjecutivo = 0;
        do {
            System.out.println("*BIENVENIDO AL BANCO*");
            System.out.println("1.-Registrar Cliente");
            System.out.println("2.-Eliminar Cliente");
            System.out.println("3.-Modificar Cliente");
            System.out.println("4.-Mostrar Clientes");
            System.out.println("5.-Aceptar solicitudes");
            System.out.println("6.-Salir del Menu");
            System.out.println("Seleccione el proceso a realizar: ");

            opcionEjecutivo = Herramientas.nextInt();

            switch (opcionEjecutivo){
                case 1:
                    Cliente.registrarClienteEnSucursal(usuarioActual);
                    break;
                case 2:
                    System.out.println("Ingrese el nombre de usuario del cliente: ");
                    String eliminarCliente = sc.nextLine();
                    Cliente.eliminarCliente(eliminarCliente);
                    break;
                case 3:

                    break;
                case 4:
                    Cliente.MostrarClientes(UsuarioActivo.getInstance().getUsuarioActual().getSucursales());
                    break;
                case 6:
                    System.out.println("\nCerrando la sesión actual. Redireccionando al inicio de sesión.");
                    UsuarioActivo.getInstance().cerrarSesionActiva();
                    ejecutarMenuBanco();
                    break;
                default:
                    System.out.println("Opcion invalida. Vuelva a intentarlo");

            }
        }while(opcionEjecutivo != 5);
    }
    public void seleccionarMenuCapturistas(){
        int opcionCapturistas = 0;
        do{
            System.out.println("*BIENVENIDO AL BANCO*");
            System.out.println("1.-Registrar a un nuevo ejecutivo.");
            System.out.println("2.-Modificar a un ejecutivo.");
            System.out.println("3.-Mostrar a todos los ejecutivos del sistema.");
            System.out.println("4.-Salir del menu.");
            System.out.println("Seleccione la operación a realizar: ");

            opcionCapturistas = Herramientas.nextInt();

            switch(opcionCapturistas){
                case 1:
                    Empleado.RegistrarEjecutivo(usuarioActual);
                    break;
                case 2:

                    break;
                case 3:
                    Empleado.MostrarTodosEmpleados(UsuarioActivo.getInstance().getUsuarioActual().getSucursales());
                    break;
                case 4:
                    System.out.println("\nCerrando la sesión actual. Redireccionando al inicio de sesión.");
                    UsuarioActivo.getInstance().cerrarSesionActiva();
                    ejecutarMenuBanco();
                    break;
                default:
                    System.out.println("Ingrese una opción valida.");
            }

        }while(opcionCapturistas != 5);
    }
    public void seleccionarMenuInversionista(){
        int opcionInversionista = 0;
        do{
            System.out.println("*BIENVENIDO AL BANCO*");
            System.out.println("1.-Agregar fondos al banco.");
            System.out.println("2.-Cerrar sesión.");
            System.out.println("Elija una opción: ");

            opcionInversionista = Herramientas.nextInt();

            switch (opcionInversionista){
                case 1:
                    System.out.println("Ingrese los fondos que desea proporcionar al banco: ");
                    double fondosInversionista = Herramientas.nextDouble();
                    LocalDate fecha = LocalDate.now();
                    Banco.agregarFondos(fondosInversionista, usuarioActual, fecha);
                    break;
                case 2:
                    System.out.println("\nCerrando la sesión actual. Redireccionando al inicio de sesión.");
                    UsuarioActivo.getInstance().cerrarSesionActiva();
                    ejecutarMenuBanco();
                    break;
                default:
                    System.out.println("Opcion invalida. Vuelva a intentarlo");
            }
        }while(opcionInversionista != 2);
    }

    //-------------------------------------------------MENU GENERAL-------------------------------------------------
    public void ejecutarMenuBanco(){
        boolean bandera = true;
        do{
            System.out.println("*BIENVENIDO AL SISTEMA DEL BANCO*");
            System.out.println("1. Iniciar Sesión.");
            System.out.println("2. Cerrar Menu.");
            System.out.println("Elija su opción: ");

            int opcion = Herramientas.nextInt();

            switch(opcion) {
                case 1:
                    iniciarSesion();
                    break;
                case 2:
                    System.out.println("\nCerrando el Banco. . .");
                    bandera = false;
                    break;
                default:
                    System.out.println("Esa no es una opción. Intente de nuevo.");
            }
        }while(bandera);
}
}
