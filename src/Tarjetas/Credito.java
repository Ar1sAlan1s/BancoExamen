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
//    public void mostrarTarjeta(){
//        for (int i=0;cliente.getTarjetasCredito().size()>i; i++) {
//            System.out.println("Tipo: " + cliente.getTarjetasCredito().get(i).tipo);
//            System.out.println("Numero de tarjeta: " + cliente.getTarjetasCredito().get(i).getNumeroDeTarjeta());
//            System.out.println("Fecha creacion: " + cliente.getTarjetasCredito().get(i).getFechaDeCreacion());
//            System.out.println("Fecha vencimiento: " + cliente.getTarjetasCredito().get(i).getFechaDeVencimiento());
//            System.out.println("Saldo: " + cliente.getTarjetasCredito().get(i).getSaldo());
//            System.out.println("Clave interbancaria: " + cliente.getTarjetasCredito().get(i).getClabe());
//            System.out.println("CVV: " + cliente.getTarjetasCredito().get(i).getCVV());
//            System.out.println("Fecha ultimo movimiento: " + cliente.getTarjetasCredito().get(i).getFechaDeUltimoMovimiento());
//            System.out.println("Hora ultimo movimiento: " + cliente.getTarjetasCredito().get(i).getHoraDeUltimoMovimiento());
//            System.out.println();
//        }
//    }


    public void mostrarSaldo() {
        System.out.println("El saldo de su tarjeta de credito es: " + this.getSaldo());
    }
}

