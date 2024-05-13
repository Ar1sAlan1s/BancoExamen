package Tarjetas;
import Tarjetas.Utils.TiposCredito;

import java.util.InputMismatchException;

public class Credito extends Tarjeta {
    public Credito(String usuario, String password, TiposCredito tipo) {
        super(usuario, password, tipo);
        if (tipo == TiposCredito.simplicity) {
            this.setSaldo(60000.0);
        } else if (tipo == TiposCredito.platino) {
            this.setSaldo(150000.0);
        } else if (tipo == TiposCredito.oro) {
            this.setSaldo(40000.0);
        }

    }
    public void agregarDinero() {
        while(this.band) {
            try {
                System.out.println("Ingresa el dinero a agregar:");
                double dineroAgregado = this.scan.nextDouble();
                this.setSaldo(this.getSaldo() + dineroAgregado);
                this.band = false;
            } catch (InputMismatchException var3) {
                System.out.println("Ingresa un numero de dinero");
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
                    this.band = false;
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

