package Tarjetas;

import Tarjetas.Utils.TiposCredito;
import Usuarios.Cliente;

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

    public void retirarDinero(Cliente cliente) {
        int intentos=0;

        while(this.band) {

            if (intentos==3){
                System.out.println("Intentos superados");
                System.out.println("Saliendo de retirar el dinero....");
                band=false;}
            try {
                System.out.println("Ingresa el dinero a retirar:");
                double dineroRetirado = this.scan.nextDouble();
                System.out.println("Ingresa el CVV:");
                int cvvIngresado = scan.nextInt();


                if (cliente.debito.getCVV()==cvvIngresado) {
                    if (dineroRetirado <= this.getSaldo()) {
                    this.setSaldo(this.getSaldo() - dineroRetirado);
                    break;
                } else {
                    System.out.println("No tienes el suficiente dinero");

                }
                }else{
                    System.out.println("CVV incorrecto");
                    intentos++;
                }

            } catch (InputMismatchException var3) {
                System.out.println("Ingresa un numero de dinero o en seu caso el CVV correcto");
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
