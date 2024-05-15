package Banco;

import Banco.Utils.Herramientas;
import Tarjetas.Credito;
import Tarjetas.Debito;
import Usuarios.Cliente;
import Usuarios.Empleado;
import Usuarios.Inversionista;
import Usuarios.Usuario;
import Usuarios.Utils.Rol;
import Usuarios.Utils.Sucursales;
import Usuarios.Utils.UsuarioActivo;

import java.time.LocalDate;
import java.util.Scanner;

public class Menu {
    private Banco banco = new Banco();
    private Scanner sc = new Scanner(System.in);
    private Credito credito;
    private String contraseñaSeguridad = "B@nc0";
    private Empleado empleado = new Empleado("", "", "", "", LocalDate.of(2004,8,30), "", "", "", "", "", Sucursales.Madero, Rol.Capturista, 30000, LocalDate.now());
    private Inversionista inversionista = new Inversionista("", "", "", "", LocalDate.of(2004,8,30), "", "", "", "", "", Sucursales.Madero, Rol.Inversionista, 30000);
    private Usuario usuarioActual;
    private Empleado Gerente;
    private Empleado Ejecutivo;
    private Empleado Capturista;
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
        do {
            System.out.println("MENU CLIENTE");
            System.out.println("1.-Debito.");
            System.out.println("2.-Credito.");
            System.out.println("3.-Cerrar sesión Actual.");
            System.out.println("Seleccione algo de la lista por favor: ");

            opcionCliente = Herramientas.nextInt();

            switch (opcionCliente) {
                case 1:
                    int opcionDeRetiro = 0;
                    do{
                        System.out.println("Que desea hacer con debito");
                        System.out.println("1.-Agregar a Debito");
                        System.out.println("2.-Retirar de Debito.");
                        System.out.println("3.-Mostrar datos.");
                        System.out.println("4.-Regresar al menú principal.");
                        System.out.println("Elija una de las opciones: ");

                        opcionDeRetiro = Herramientas.nextInt();

                        switch (opcionDeRetiro){
                            case 1:
                                cliente.getDebito().agregarDinero();
                                break;
                            case 2:
                                cliente.getDebito().retirarDinero();
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
                    int opcionDeAgrego = 0;
                    do{
                        System.out.println("Que desea hacer con debito");
                        System.out.println("1.-Agregar a Credito");
                        System.out.println("2.-Retirar de Credito.");
                        System.out.println("3.-Mostrar datos.");
                        System.out.println("4.-Ver solicitudes");
                        if(cliente.getDebito().getSaldo()>=50000){System.out.println("5.-Solicitar tarjeta");}
                        System.out.println("6.-Regresar al menú principal.");
                        System.out.println("Elija una de las opciones: ");

                        opcionDeAgrego= Herramientas.nextInt();

                        switch (opcionDeAgrego){
                            case 1:
                                credito.agregarDinero();
                                break;
                            case 2:
                                credito.retirarDinero();
                                break;
                            case 3:

                                break;
                            case 4:
                                cliente.versolicitudespropias();
                                break;
                            case 5:
                                cliente.solicitarTarjetaCredito();
                                break;
                            case 6:
                                System.out.println("Volviendo al menú principal. . .");
                                break;
                            default:
                                System.out.println("Opción no valida.");
                        }
                    }while(opcionDeAgrego != 3);
                    break;
                case 3:
                    System.out.println("Sesión cerrada...");
                    UsuarioActivo.getInstance().cerrarSesionActiva();
                    ejecutarMenuBanco();
                    break;
                default:
                    System.out.println("Opción no valida. Elija cualquiera en el rango de 1 a 2.");
            }
        } while (opcionCliente != 3);
    }
    private void seleccionarMenuGerente() {
        int opcionGerente = 0;
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
                        System.out.println("5.-Salir de la modificación.");
                        System.out.println("Seleccione una opción: ");

                        opcionRegistrar = Herramientas.nextInt();

                        switch (opcionRegistrar){
                            case 1:
                                Cliente.registrarClienteEnSucursal(usuarioActual);
                                break;
                            case 2:

                                break;
                            case 3:

                                break;
                            case 4:
                                System.out.println("Ingrese la contraseña de seguridad antes de continuar: ");
                                String contraseñaGerente = sc.nextLine();
                                if(contraseñaGerente.equals(contraseñaSeguridad)){
                                    System.out.println("Contraseña correcta.");

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
                        System.out.println("5.-Salir");
                        System.out.println("Seleccione una opcion: ");

                        opcionEliminar = Herramientas.nextInt();

                        switch (opcionEliminar){
                            case 1:
                                System.out.println("Ingrese el nombre de usuario del cliente: ");
                                String eliminarCliente = sc.nextLine();
                                Cliente.eliminarCliente(eliminarCliente);
                                break;
                            case 2:

                                break;
                            case 3:

                                break;
                            case 4:
                                System.out.println("Ingrese la contraseña de seguridad antes de continuar: ");
                                String contraseñaGerente = sc.nextLine();
                                if(contraseñaGerente.equals(contraseñaSeguridad)){
                                    System.out.println("Contraseña correcta.");
                                    // Su lógica para eliminar un inversionista aquí.
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
                        System.out.println("5.-Salir.");
                        System.out.println("Seleccione una opción: ");

                        opcionMostrar = Herramientas.nextInt();


                        switch (opcionMostrar){
                            case 1:
                                Cliente.MostrarClientes(UsuarioActivo.getInstance().getUsuarioActual().getSucursales());
                                break;
                            case 2:

                                break;
                            case 3:

                                break;
                            case 4:

                                break;
                            case 5:
                                System.out.println("\nSaliendo de mostrar en especifico. . .");
                                break;
                            default:
                                System.out.println("Opcion invalida. Vuelva a intentarlo");
                        }
                    }while(opcionMostrar != 5);
                    break;
                case 5:
                    empleado.revisarSolicitudes();
                    break;
                case 6:

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
            System.out.println("5.-Salir del Menu");
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
                case 5:
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

                    break;
                case 2:

                    break;
                case 3:

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