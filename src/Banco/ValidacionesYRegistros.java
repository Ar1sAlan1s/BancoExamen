package Banco;

import Usuarios.Usuario;
import Usuarios.Utils.Sucursales;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class ValidacionesYRegistros {
    Scanner leer;
    Random ran;
    Banco banco;

    public ValidacionesYRegistros() {
        this.leer = new Scanner(System.in);
        this.ran = new Random();
    }

    public String validarNombre(String nombre) {
        while(true) {
            boolean nombreValido = true;
            int contador = 0;

            for(int i = 0; i < nombre.length(); ++i) {
                if (!Character.isLetter(nombre.charAt(i)) && !Character.isWhitespace(nombre.charAt(i))) {
                    nombreValido = false;
                    break;
                }

                if (Character.isWhitespace(nombre.charAt(i))) {
                    ++contador;
                }
            }

            if (nombreValido && contador <= 1) {
                System.out.println("El nombre es válido.");
                return nombre;
            }

            System.out.println("El/los nombre(s) no es válido(s). Ingrese solo letras y como máximo un espacio entre nombres.");
            System.out.println("Ingrese su nombre nuevamente: ");
            nombre = this.leer.nextLine();
        }
    }

    public String validarApellido(String apellido) {
        while(true) {
            boolean apellidoValido = true;
            int cont = 0;

            for(int i = 0; i < apellido.length(); ++i) {
                if (!Character.isLetter(apellido.charAt(i)) && !Character.isWhitespace(apellido.charAt(i))) {
                    apellidoValido = false;
                    break;
                }

                if (Character.isWhitespace(apellido.charAt(i))) {
                    ++cont;
                }
            }

            if (apellidoValido && cont <= 1 && cont != 0) {
                System.out.println("el apellido es valido.");
                return apellido;
            }

            System.out.println("los apellidos no son validos no ingrese caracteres erroneos o coloque mas de 2 apellidos");
            System.out.println("Ingrese sus apellidos nuevamente: ");
            apellido = this.leer.nextLine();
        }
    }

    public String validarUsuario(String usuario) {
        boolean nombreUsuarioExistente = false;
        Iterator var3 = Banco.listaUsuarios.values().iterator();

        while(true) {
            while(true) {
                ArrayList usuarioExistente;
                do {
                    if (!var3.hasNext()) {
                        if (nombreUsuarioExistente) {
                            System.out.println("El nombre de usuario ya está en uso. Ingrese otro nombre de usuario: ");
                            usuario = this.leer.nextLine();
                            usuario = this.validarUsuario(usuario);
                        }

                        return usuario;
                    }

                    usuarioExistente = (ArrayList)var3.next();
                } while(usuarioExistente == null);

                Iterator var5 = usuarioExistente.iterator();

                while(var5.hasNext()) {
                    Usuario usuario1 = (Usuario)var5.next();
                    if (usuario1.getUsuario().equals(usuario)) {
                        nombreUsuarioExistente = true;
                        break;
                    }
                }
            }
        }
    }

    public LocalDate RegistrarFechaDeNacimiento() {
        boolean fechaValida = false;
        LocalDate fechaDeNacimiento = null;

        while(!fechaValida) {
            int año = false;

            int año;
            try {
                System.out.println("Ingrese su año de nacimiento: (ingrese el año como el siguiente ejemplo: 2004)");
                año = this.leer.nextInt();
            } catch (InputMismatchException var9) {
                System.out.println("Error: debe ingresar un número entero para el año.");
                this.leer.nextLine();
                continue;
            }

            int mes = false;

            while(true) {
                int mes;
                try {
                    System.out.println("Ingrese su mes de nacimiento: (ingrese el mes como el siguiente ejemplo: 08)");
                    mes = this.leer.nextInt();
                    if (mes < 1 || mes > 12) {
                        throw new IllegalArgumentException("Mes no válido. Debe ser un número entre 1 y 12.");
                    }
                } catch (InputMismatchException var10) {
                    System.out.println("Error: debe ingresar un número entero para el mes.");
                    this.leer.nextLine();
                    continue;
                } catch (IllegalArgumentException var11) {
                    IllegalArgumentException e = var11;
                    System.out.println(e.getMessage());
                    continue;
                }

                int dia = false;

                int dia;
                try {
                    System.out.println("Ingrese su día de nacimiento: ");
                    dia = this.leer.nextInt();
                } catch (InputMismatchException var8) {
                    System.out.println("Error: debe ingresar un número entero para el día.");
                    this.leer.nextLine();
                    break;
                }

                try {
                    fechaDeNacimiento = LocalDate.of(año, mes, dia);
                } catch (DateTimeException var7) {
                    System.out.println("Fecha no válida. Por favor, ingrese una fecha válida.");
                    break;
                }

                int edad = Period.between(fechaDeNacimiento, LocalDate.now()).getYears();
                if (edad >= 18) {
                    fechaValida = true;
                } else {
                    System.out.println("Lo siento, debe tener al menos 18 años para registrarse.");
                    fechaValida = false;
                }
                break;
            }
        }

        return fechaDeNacimiento;
    }

    public String validarCiudad(String ciudad) {
        while(true) {
            boolean ciudadValida = true;

            for(int i = 0; i < ciudad.length(); ++i) {
                if (!Character.isLetter(ciudad.charAt(i)) && !Character.isWhitespace(ciudad.charAt(i))) {
                    ciudadValida = false;
                    break;
                }
            }

            if (ciudadValida) {
                System.out.println("La ciudad es válida.");
                return ciudad;
            }

            System.out.println("La ciudad no es válida. Ingrese caracteres correctos.");
            System.out.println("Ingrese la ciudad nuevamente: ");
            ciudad = this.leer.nextLine();
        }
    }

    public String validarEstado(String estado) {
        while(true) {
            boolean EstadoValido = true;

            for(int i = 0; i < estado.length(); ++i) {
                if (!Character.isLetter(estado.charAt(i)) && !Character.isWhitespace(estado.charAt(i))) {
                    EstadoValido = false;
                    break;
                }
            }

            if (EstadoValido) {
                System.out.println("El estado es válida.");
                return estado;
            }

            System.out.println("El estado no es válida. Ingrese caracteres correctos.");
            System.out.println("Ingrese su estado nuevamente: ");
            estado = this.leer.nextLine();
        }
    }

    public String generarRFC(String nombre, String apellido, LocalDate fechaDeNacimiento) {
        String PrimeraletraApellidoMaterno = " ";

        int i;
        for(i = 0; i < apellido.length(); ++i) {
            if (apellido.charAt(i) == ' ') {
                PrimeraletraApellidoMaterno = apellido.substring(i + 1);
                break;
            }
        }

        PrimeraletraApellidoMaterno = PrimeraletraApellidoMaterno.toUpperCase();
        i = this.ran.nextInt(10);
        int numeroAleatorio2 = this.ran.nextInt(10);
        char letraAleatoria = (char)(this.ran.nextInt(27) + 65);
        int año = fechaDeNacimiento.getYear();
        int añoDosDigitos = año % 100;
        String añoFormatoDosDigitos = String.format("%02d", añoDosDigitos);
        String RFC = String.valueOf(apellido.charAt(0)) + String.valueOf(apellido.toUpperCase().charAt(1)) + String.valueOf(PrimeraletraApellidoMaterno.charAt(0)) + String.valueOf(nombre.charAt(0)) + String.valueOf(añoFormatoDosDigitos) + String.valueOf(fechaDeNacimiento.getMonthValue()) + String.valueOf(fechaDeNacimiento.getDayOfMonth()) + String.valueOf(i) + letraAleatoria + String.valueOf(numeroAleatorio2);
        System.out.println("Su RFC es: " + RFC);
        return RFC;
    }

    public String validarDireccion(String direccion) {
        String[] partesDireccion = direccion.split(",");
        if (partesDireccion.length != 3) {
            System.out.println("La dirección debe contener tres partes: colonia, calle y número.");
            System.out.println("Ingrese la dirección nuevamente en el formato correcto (colonia, calle, número): ");
            direccion = this.leer.nextLine();
            return this.validarDireccion(direccion);
        } else {
            String colonia = partesDireccion[0].trim();
            String calle = partesDireccion[1].trim();
            String numero = partesDireccion[2].trim();
            if (!colonia.isEmpty() && !calle.isEmpty() && !numero.isEmpty()) {
                System.out.println("La dirección es válida.");
                return direccion;
            } else {
                System.out.println("La dirección no puede contener partes vacías.");
                System.out.println("Ingrese la dirección nuevamente en el formato correcto (colonia, calle, número): ");
                direccion = this.leer.nextLine();
                return this.validarDireccion(direccion);
            }
        }
    }

    public Sucursales ElegirSucursal() {
        boolean SucursalValido = true;
        Sucursales sucursal = null;

        while(SucursalValido) {
            System.out.println("en que sucursal deseas Registrar al Cliente: ");
            System.out.println("ingrese \"1\" para seleccionar la sucursal Madero");
            System.out.println("ingrese \"2\" para seleccionar la sucursal Acueducto");
            int seleccion = this.leer.nextInt();
            if (seleccion == 1) {
                sucursal = Sucursales.Madero;
                SucursalValido = false;
                break;
            }

            if (seleccion == 2) {
                SucursalValido = false;
                sucursal = Sucursales.Acueducto;
            } else {
                System.out.println("ingrese un numero valido");
                SucursalValido = true;
            }
        }

        return sucursal;
    }

    public static double RegistrarSalario() {
        Scanner leer = new Scanner(System.in);
        double salario = 0.0;

        try {
            System.out.println("Ingrese el salario para el empleado");
            salario = leer.nextDouble();
        } catch (InputMismatchException var4) {
            System.out.println("Ingrese caracteres correctos");
        }

        return salario;
    }
}