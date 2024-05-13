package Banco;

import Banco.Utils.Herramientas;
import Usuarios.Cliente;
import Usuarios.Usuario;
import Usuarios.Utils.UsuarioActivo;
import java.util.Scanner;


public class Menu {
    private Banco banco = new Banco();
    private Scanner sc = new Scanner(System.in);
    private Cliente cliente;
    private String contraseñaSeguridad = "B@nc0";
    //Verificar el inicio de sesión
    public void iniciarSesion() {
        boolean sonDatosCorrectos = false;

        do {
            System.out.println("**Bienvenido al inicio de sesión del banco**");
            System.out.println("Inicie sesión para continuar.");

            System.out.println("Ingresa el nombre de usuario: ");
            String usuario = sc.nextLine();

            System.out.println("Ingresa el password: ");
            String password = sc.nextLine();

            Usuario usuarioActual = banco.comprobarInicioSesion(usuario, password);

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
            System.out.println("1.-Realizar una compra.");
            System.out.println("2.-Verificar estado de solicitud.");
            if(cliente.debito.getSaldo()>=50000) {
                System.out.println("3.-Solicitar Tarjeta de crédito.");
            }
            System.out.println("4.-Cerrar sesión Actual.");
            System.out.println("Seleccione algo de la lista por favor: ");

            opcionCliente = Herramientas.nextInt();

            switch (opcionCliente) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:
                    System.out.println("Sesión cerrada...");
                    UsuarioActivo.getInstance().cerrarSesionActiva();
                    ejecutarMenuBanco();
                    break;
                default:
                    System.out.println("Opción no valida. Elija cualquiera en el rango de 1 a 5.");
            }
        } while (opcionCliente != 4);
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
                                    // Su lógica para agregar un inversionista aquí.
                                }else{
                                    System.out.println("\nContraseña incorrecta. Intente de nuevo.");
                                }
                                break;
                            case 5:
                                System.out.println("\nSaliendo de la modificación. . .");
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
                        System.out.println("5.-Salir de la eliminación.");
                        System.out.println("Seleccione una opcion: ");

                        opcionEliminar = Herramientas.nextInt();

                        switch (opcionEliminar){
                            case 1:

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
                        System.out.println("5.-Salir de la modificación.");
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
            System.out.println("**BIENVENIDO AL BANCO**");
            System.out.println("1.-Registrar Cliente");
            System.out.println("2.-Eliminar Cliente");
            System.out.println("3.-Modificar Cliente");
            System.out.println("4.-Mostrar Clientes");
            System.out.println("5.-Salir del Menu");
            System.out.println("Seleccione el proceso a realizar: ");

            opcionEjecutivo = Herramientas.nextInt();

            switch (opcionEjecutivo){
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

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
            System.out.println("**BIENVENIDO AL BANCO**");
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
            System.out.println("**BIENVENIDO AL BANCO**");
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
        System.out.println("**BIENVENIDO AL SISTEMA DEL BANCO**");
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
