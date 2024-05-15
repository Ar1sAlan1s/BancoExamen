package Tarjetas;
import Tarjetas.Utils.TiposCredito;

import java.util.InputMismatchException;

public class Credito extends Tarjeta {
    TiposCredito tipoCredito;
    public Credito(String usuario, String password, TiposCredito tipo) {
        super(usuario, password, tipo);
        if (tipo == TiposCredito.simplicity) {
            this.setSaldo(60000.0);
        } else if (tipo == TiposCredito.platino) {
            this.setSaldo(150000.0);
        } else if (tipo == TiposCredito.oro) {
            this.setSaldo(40000.0);
        }
        this.tipoCredito = tipo;

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

    public void mostrarTarjeta(){
        System.out.println("Tipo: "+ getTipoCredito());
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

    public TiposCredito getTipoCredito() {
        return tipoCredito;
    }

    public void mostrarSaldo() {
        System.out.println("El saldo de su tarjeta de credito es: " + this.getSaldo());
    }
}

