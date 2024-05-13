package Banco;

import Banco.Utils.Herramientas;
import Usuarios.Usuario;
import Usuarios.Utils.UsuarioActivo;
import java.util.Scanner;

public class Menu {
    private Banco banco = new Banco();
    private Scanner sc;

    public Menu() {
        this.sc = new Scanner(System.in);
    }

    public void iniciarSesion() {
        boolean sonDatosCorrectos = false;

        do {
            System.out.println("**Bienvenido al Inicio de sesión del banco**");
            System.out.println("Inicie sesión para continuar.");
            System.out.println("Ingresa el nombre de usuario: ");
            String usuario = this.sc.nextLine();
            System.out.println("Ingresa el password: ");
            String password = this.sc.nextLine();
            Usuario usuarioActual = this.banco.comprobarInicioSesion(usuario, password);
            if (usuarioActual != null) {
                UsuarioActivo.getInstance().setUsuario(usuarioActual);
                sonDatosCorrectos = true;
                this.asignarMenu();
            } else {
                System.out.println("Usuario o contraseña incorrecta. Intenta de nuevo.");
            }
        } while(!sonDatosCorrectos);

    }

    public void asignarMenu() {
        switch (UsuarioActivo.getInstance().getUsuarioActual().getRol()) {
            case Gerente -> this.seleccionarMenuGerente();
            case Cliente -> this.seleccionarMenuCliente();
            case Ejecutivos -> this.seleccionarMenuEjecutivo();
            case Capturista -> this.seleccionarMenuCapturistas();
            case Inversionista -> this.seleccionarMenuInversionista();
        }

    }

    private void seleccionarMenuCliente() {
        int opcionCliente = false;

        int opcionCliente;
        do {
            System.out.println("MENU CLIENTE");
            System.out.println("1. Realizar una compra.");
            System.out.println("2. Solicitar Tarjeta de crédito.");
            System.out.println("3. Solicitar Tarjeta de debito.");
            System.out.println("4. Verificar estado de solicitud.");
            System.out.println("5. Cerrar sesión Actual.");
            System.out.println("Seleccione algo de la lista por favor: ");
            opcionCliente = Herramientas.nextInt();
            switch (opcionCliente) {
                case 1:
                case 2:
                case 3:
                case 4:
                    break;
                case 5:
                    System.out.println("Sesion cerrada...");
                    UsuarioActivo.getInstance().cerrarSesionActiva();
                    this.iniciarSesion();
                    break;
                default:
                    System.out.println("Opción no valida. Elija cualquiera en el rango de 1 a 5.");
            }
        } while(opcionCliente != 5);

    }

    private void seleccionarMenuGerente() {
        int opcionGerente = false;

        int opcionGerente;
        label86:
        do {
            System.out.println("MENU GERENTE");
            System.out.println("1.-Registrar Cliente, Capturista, Ejecutivo o Inversionista");
            System.out.println("2.-Eliminar Cliente, Capturista, Ejecutivo o Inversionista");
            System.out.println("3.-Modificar Cliente, Capturista, Ejecutivo o Inversionista");
            System.out.println("4.-Mostrar Clientes, Capturistas, Ejecutivos o Inversionistas");
            System.out.println("5.-Consultar solicitudes de tarjetas en el sistema.");
            System.out.println("6.-Consultar Inversiones en el banco.");
            System.out.println("7.-Cerrar sesión Actual.");
            System.out.println("Ingrese la opcion deseada: ");
            opcionGerente = Herramientas.nextInt();
            switch (opcionGerente) {
                case 1:
                    int opcionRegistrar = false;

                    while(true) {
                        System.out.println("Has elegido modificar");
                        System.out.println("¿A quien desea modificar?");
                        System.out.println("1.-Cliente.");
                        System.out.println("2.-Capturista.");
                        System.out.println("3.-Ejecutivo.");
                        System.out.println("4.-Inversionista.");
                        System.out.println("5.-Salir de la modificación.");
                        System.out.println("Seleccione una opción: ");
                        int opcionRegistrar = Herramientas.nextInt();
                        switch (opcionRegistrar) {
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                                break;
                            case 5:
                                System.out.println("\nSaliendo de la modificación. . .");
                                break;
                            default:
                                System.out.println("Opción invalida. Vuelva a intentarlo");
                        }

                        if (opcionRegistrar == 5) {
                            continue label86;
                        }
                    }
                case 2:
                    int opcionEliminar = false;

                    while(true) {
                        System.out.println("Has elegido eliminar");
                        System.out.println("¿A quien desea Eliminar?");
                        System.out.println("1.-Cliente.");
                        System.out.println("2.-Capturista.");
                        System.out.println("3.-Ejecutivo.");
                        System.out.println("4.-Inversionista.");
                        System.out.println("5.-Salir de la eliminación.");
                        System.out.println("Seleccione una opcion: ");
                        int opcionEliminar = Herramientas.nextInt();
                        switch (opcionEliminar) {
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                                break;
                            case 5:
                                System.out.println("\nSaliendo de la eliminación. . .");
                                break;
                            default:
                                System.out.println("Opcion invalida. Vuelva a intentarlo");
                        }

                        if (opcionEliminar == 5) {
                            continue label86;
                        }
                    }
                case 3:
                    int opcionModificar = false;

                    while(true) {
                        System.out.println("Has elegido modificar");
                        System.out.println("¿A quien desea modificar?");
                        System.out.println("1.-Cliente.");
                        System.out.println("2.-Capturista.");
                        System.out.println("3.-Ejecutivo.");
                        System.out.println("4.-Inversionista.");
                        System.out.println("5.-Salir de la modificación.");
                        System.out.println("Seleccione una opción: ");
                        int opcionModificar = Herramientas.nextInt();
                        switch (opcionModificar) {
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                                break;
                            case 5:
                                System.out.println("\nSaliendo de la modificación. . .");
                                break;
                            default:
                                System.out.println("Opción invalida. Vuelva a intentarlo");
                        }

                        if (opcionModificar == 5) {
                            continue label86;
                        }
                    }
                case 4:
                    int opcionMostrar = false;

                    int opcionMostrar;
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
                        switch (opcionMostrar) {
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                                break;
                            case 5:
                                System.out.println("\nSaliendo de mostrar en especifico. . .");
                                break;
                            default:
                                System.out.println("Opcion invalida. Vuelva a intentarlo");
                        }
                    } while(opcionMostrar != 5);
                case 5:
                case 6:
                    break;
                case 7:
                    System.out.println("\nCerrando la sesión actual. Redireccionando al inicio de sesión.");
                    UsuarioActivo.getInstance().cerrarSesionActiva();
                    this.iniciarSesion();
                    break;
                default:
                    System.out.println("Opción invalida. Vuelva a intentarlo");
            }
        } while(opcionGerente != 7);

    }

    public void seleccionarMenuEjecutivo() {
        int opcionEjecutivo = false;

        int opcionEjecutivo;
        do {
            System.out.println("**BIENVENIDO AL BANCO**");
            System.out.println("1.-Registrar Cliente");
            System.out.println("2.-Eliminar Cliente");
            System.out.println("3.-Modificar Cliente");
            System.out.println("4.-Mostrar Clientes");
            System.out.println("5.-Salir del Menu");
            System.out.println("Seleccione el proceso a realizar: ");
            opcionEjecutivo = Herramientas.nextInt();
            switch (opcionEjecutivo) {
                case 1:
                case 2:
                case 3:
                case 4:
                    break;
                case 5:
                    System.out.println("\nCerrando la sesión actual. Redireccionando al inicio de sesión.");
                    UsuarioActivo.getInstance().cerrarSesionActiva();
                    this.iniciarSesion();
                    break;
                default:
                    System.out.println("Opcion invalida. Vuelva a intentarlo");
            }
        } while(opcionEjecutivo != 5);

    }

    public void seleccionarMenuCapturistas() {
        int opcionCapturistas = false;

        int opcionCapturistas;
        do {
            System.out.println("**BIENVENIDO AL BANCO**");
            System.out.println("1.-Registrar a un nuevo ejecutivo.");
            System.out.println("2.-Modificar a un ejecutivo.");
            System.out.println("3.-Mostrar a todos los ejecutivos del sistema.");
            System.out.println("4.Salir del menu.");
            System.out.println("Seleccione la operación a realizar: ");
            opcionCapturistas = Herramientas.nextInt();
            switch (opcionCapturistas) {
                case 1:
                case 2:
                case 3:
                    break;
                case 4:
                    System.out.println("\nCerrando la sesión actual. Redireccionando al inicio de sesión.");
                    UsuarioActivo.getInstance().cerrarSesionActiva();
                    this.iniciarSesion();
                    break;
                default:
                    System.out.println("Ingrese una opción valida.");
            }
        } while(opcionCapturistas != 5);

    }

    public void seleccionarMenuInversionista() {
        int opcionInversionista = false;

        int opcionInversionista;
        do {
            System.out.println("**BIENVENIDO AL BANCO**");
            System.out.println("1.-Agregar fondos al banco.");
            System.out.println("2.-Cerrar sesión.");
            System.out.println("Elija una opción: ");
            opcionInversionista = Herramientas.nextInt();
            switch (opcionInversionista) {
                case 1:
                    break;
                case 2:
                    System.out.println("\nCerrando la sesión actual. Redireccionando al inicio de sesión.");
                    UsuarioActivo.getInstance().cerrarSesionActiva();
                    this.iniciarSesion();
                    break;
                default:
                    System.out.println("Opcion invalida. Vuelva a intentarlo");
            }
        } while(opcionInversionista != 2);

    }

    public void ejecutarMenuBanco() {
        boolean bandera = true;

        do {
            System.out.println("**BIENVENIDO AL SISTEMA DEL BANCO**");
            System.out.println("1. Iniciar Sesión.");
            System.out.println("2. Cerrar Sesión.");
            System.out.println("Elija su opción: ");
            int opcion = Herramientas.nextInt();
            switch (opcion) {
                case 1:
                    this.iniciarSesion();
                    break;
                case 2:
                    System.out.println("\nCerrando sesión. . .");
                    bandera = false;
                    break;
                default:
                    System.out.println("Esa no es una opción. Intente de nuevo.");
            }
        } while(bandera);

    }
}
