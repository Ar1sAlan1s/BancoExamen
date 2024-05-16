package Tarjetas.Utils;

import Tarjetas.Credito;
import Usuarios.Cliente;
import Usuarios.Utils.Sucursales;

import java.time.LocalDate;

public class Solicitud {
    private Cliente cliente;
    private LocalDate fechaDeRealizacion;
    private TiposCredito tipoCredito;
    private int idCliente;
    private Sucursales sucursal;
    private double saldoDebito;
    private String status;
    public Solicitud(TiposCredito tipoCredito,Cliente cliente) {
        this.fechaDeRealizacion=LocalDate.now();
        this.tipoCredito=tipoCredito;
        this.idCliente=cliente.getId();
        this.sucursal=cliente.getSucursales();
        this.saldoDebito=cliente.debito.getSaldo();
        this.status="En revision";
        this.cliente=cliente;
        cliente.Solicitudes.add(this);
    }
    public void mostrarSolicitud(Cliente cliente) {
        System.out.println("Id del solicitante: "+cliente.getId());
        System.out.println("Usuario de solicitante: " + cliente.getUsuario());
        System.out.println("Nombre de solicitante: " + cliente.getNombre() + cliente.getApellidos());
        System.out.println("Estatus: " + status);
        System.out.println("Saldo en tarjeta de debito al generar solicitud: $" + saldoDebito);
        System.out.println("Tipo de tarjeta que solicita: " + tipoCredito);
        System.out.println("Fecha de solicitud: " + fechaDeRealizacion);
    }
    public void mostrarSolicitud(Sucursales sucursalEnviada) {
        if (cliente.getSucursales()==sucursalEnviada&&status.equals("En revision")) {

            System.out.println("Id del solicitante: " + cliente.getId());
            System.out.println("Usuario de solicitante: " + cliente.getUsuario());
            System.out.println("Nombre de solicitante: " + cliente.getNombre() + cliente.getApellidos());
            System.out.println("Estatus: " + status);
            System.out.println("Saldo en tarjeta de debito al generar solicitud: $" + saldoDebito);
            System.out.println("Tipo de tarjeta que solicita: " + tipoCredito);
            System.out.println("Fecha de solicitud: " + fechaDeRealizacion);
        }
    }

    public TiposCredito getTipoCredito() {
        return tipoCredito;
    }

    public double getSaldoDebito() {
        return saldoDebito;
    }

    public String getStatus() {
        return status;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public Sucursales getSucursal() {
        return sucursal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getFechaDeRealizacion() {
        return fechaDeRealizacion;
    }
    public void aprobarSolicitud() {
        cliente.getTarjetasCredito().add(new Credito(cliente.getNombre(), cliente.getApellidos(),tipoCredito ));
        this.status = "Aprobada";
    }

    public void rechazarSolicitud() {
        this.status = "Rechazada";
    }


}
