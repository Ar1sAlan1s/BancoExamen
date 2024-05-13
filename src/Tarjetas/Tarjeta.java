package Tarjetas;

import Tarjetas.Utils.TiposCredito;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Tarjeta {
    Scanner scan;
    boolean band;
    public ArrayList<String> numerosDeTarjeta;
    protected long numeroDeTarjeta;
    protected LocalDate fechaDeCreacion;
    protected double saldo;
    protected int CVV;
    protected long Clabe;
    protected LocalDate fechaDeVencimiento;
    protected LocalDate fechaDeUltimoMovimiento;
    protected LocalDate HoraDeUltimoMovimiento;
    Random rand;

    public Tarjeta(String usurio, String password, TiposCredito tipo) {
        this.scan = new Scanner(System.in);
        this.band = true;
        this.numerosDeTarjeta = new ArrayList();
        this.rand = new Random();
        this.numeroDeTarjeta = this.crearNumeroDeTarjeta();
        this.fechaDeCreacion = LocalDate.now();
        this.saldo = this.saldo;
        this.CVV = this.crearCVV();
        this.Clabe = this.crearClabe();
        this.fechaDeVencimiento = LocalDate.now().plusYears(5L);
        this.fechaDeUltimoMovimiento = this.fechaDeUltimoMovimiento;
        this.HoraDeUltimoMovimiento = this.HoraDeUltimoMovimiento;
    }

    public long crearNumeroDeTarjeta() {
        long numeroDeTarjeta = this.rand.nextLong(9000000000000000L) + 1000000000000000L;
        String numeroDeTarjetaStr = Long.toString(numeroDeTarjeta);

        for(int i = 0; i < this.numerosDeTarjeta.size(); ++i) {
            if (numeroDeTarjetaStr.equals(this.numerosDeTarjeta.get(i))) {
                numeroDeTarjeta = this.rand.nextLong(9000000000000000L) + 1000000000000000L;
                numeroDeTarjetaStr = Long.toString(numeroDeTarjeta);
                i = 0;
            }
        }

        this.numerosDeTarjeta.add(numeroDeTarjetaStr);
        return Long.parseLong(numeroDeTarjetaStr);
    }

    public int crearCVV() {
        return this.rand.nextInt(899) + 100;
    }

    public long crearClabe() {
        long numeroDeTarjeta = this.rand.nextLong(900000000000000000L) + 100000000000000000L;
        String numeroDeTarjetaStr = Long.toString(numeroDeTarjeta);

        for(int i = 0; i < this.numerosDeTarjeta.size(); ++i) {
            if (numeroDeTarjetaStr.equals(this.numerosDeTarjeta.get(i))) {
                numeroDeTarjeta = this.rand.nextLong(900000000000000000L) + 100000000000000000L;
                numeroDeTarjetaStr = Long.toString(numeroDeTarjeta);
                i = 0;
            }
        }

        this.numerosDeTarjeta.add(numeroDeTarjetaStr);
        return numeroDeTarjeta;
    }

    public long getNumeroDeTarjeta() {
        return this.numeroDeTarjeta;
    }

    public LocalDate getFechaDeCreacion() {
        return this.fechaDeCreacion;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public int getCVV() {
        return this.CVV;
    }

    public long getClabe() {
        return this.Clabe;
    }

    public LocalDate getFechaDeVencimiento() {
        return this.fechaDeVencimiento;
    }

    public LocalDate getFechaDeUltimoMovimiento() {
        return this.fechaDeUltimoMovimiento;
    }

    public LocalDate getHoraDeUltimoMovimiento() {
        return this.HoraDeUltimoMovimiento;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setFechaDeUltimoMovimiento(LocalDate fechaDeUltimoMovimiento) {
        this.fechaDeUltimoMovimiento = fechaDeUltimoMovimiento;
    }

    public void setHoraDeUltimoMovimiento(LocalDate horaDeUltimoMovimiento) {
        this.HoraDeUltimoMovimiento = horaDeUltimoMovimiento;
    }
}
