package Tarjetas;

import Tarjetas.Utils.TiposCredito;
import java.util.InputMismatchException;

public class Debito extends Tarjeta {
    public Debito(String usuario, String password, TiposCredito tipo) {
        super(usuario, password, TiposCredito.debito);
    }

    public void agregarDinero() {
        while (true) {
            try {
                System.out.println("Ingresa el dinero a agregar:");
                double dineroAgregado = this.scan.nextDouble();
                this.setSaldo(this.getSaldo() + dineroAgregado);
                break;
            } catch (InputMismatchException var3) {
                System.out.println("Ingresa un número de dinero.");
                this.scan.nextLine();
            }
        }
    }

    public void retirarDinero() {
        while(this.band) {
            try {
                System.out.println("Ingresa el dinero a retirar:");
                double dineroRetirado = this.scan.nextDouble();
                if (dineroRetirado <= this.getSaldo()) {
                    this.setSaldo(this.getSaldo() - dineroRetirado);
                    break;
                } else {
                    System.out.println("No tienes el suficiente dinero");
                }
            } catch (InputMismatchException var3) {
                System.out.println("Ingresa un numero de dinero");
                this.scan.nextLine();
            }
        }

    }

    public void mostrarSaldo() {
        System.out.println("El saldo de su tarjeta de credito es: " + this.getSaldo());
    }
}
