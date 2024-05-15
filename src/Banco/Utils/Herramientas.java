package Banco.Utils;

import Usuarios.Usuario;

import java.util.ArrayList;
import java.util.Scanner;

public class Herramientas {
    private static Scanner sc = new Scanner(System.in);

    public Herramientas() {
    }

    public static int nextInt() {
        while(true) {
            String numero = sc.nextLine();

            try {
                int respuesta = Integer.parseInt(numero);
                return respuesta;
            } catch (Exception var3) {
                System.out.println("Por favor ingrese un número.");
            }
        }
    }

    public static double nextDouble() {
        while(true) {
            String numero = sc.nextLine();

            try {
                double respuesta = Double.parseDouble(numero);
                return respuesta;
            } catch (Exception var4) {
                System.out.println("Por favor ingrese un número.");
            }
        }
    }
}