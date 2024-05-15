package Tarjetas;

import Tarjetas.Utils.TiposCredito;
import java.util.InputMismatchException;

public class Debito extends Tarjeta {
    public Debito(String usuario, String password, TiposCredito tipo) {
        super(usuario, password, TiposCredito.debito);
    }

    public void agregarDinero() {
        while(band) {
            try {
                System.out.println("Ingresa el dinero a agregar:");
                double dineroAgregado = scan.nextDouble();
                this.setSaldo(this.getSaldo() + dineroAgregado);
                this.band = false;
            } catch (InputMismatchException e) {
                System.out.println("Ingresa un numero de dinero");
                this.scan.nextLine();
            }
        }

    }

    public void retirarDinero() {
        while(band) {
            try {
                System.out.println("Ingresa el dinero a retirar:");
                double dineroRetirado = scan.nextDouble();
                if (dineroRetirado <= getSaldo()) {
                    this.setSaldo(getSaldo() - dineroRetirado);
                    this.band = false;
                } else {
                    System.out.println("No tienes el suficiente dinero");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingresa un numero de dinero");
                this.scan.nextLine();
            }
        }

    }

    public void mostrarTarjeta(){
        System.out.println("Tipo: "+ TiposCredito.debito);
        System.out.println("Numero de tarjeta: "+ super.numeroDeTarjeta);
        System.out.println("Fecha creacion: "+ super.fechaDeCreacion);
        System.out.println("Fecha vencimiento: "+ super.fechaDeCreacion.toString());
        System.out.println("Saldo: "+super.saldo );
        System.out.println("Clave interbancaria: "+ super.getClabe());
        System.out.println("CVV: "+ super.CVV);
        System.out.println("Fecha ultimo movimiento: "+ super.fechaDeUltimoMovimiento.toString());
        System.out.println("Fecha ultimo movimiento: "+ super.getHoraDeUltimoMovimiento().toString());
        System.out.println();
    }

    public void mostrarSaldo() {
        System.out.println("El saldo de su tarjeta de credito es: " + this.getSaldo());
    }
}
