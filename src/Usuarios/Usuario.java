package Usuarios;

import Usuarios.Utils.Rol;
import Usuarios.Utils.Sucursales;
import java.time.LocalDate;

public class Usuario {
    protected String usuario;
    protected String password;
    protected String nombre;
    protected String apellidos;
    protected LocalDate fechaNacimiento;
    protected String ciudad;
    protected String estado;
    protected String RFC;
    protected String Curp;
    protected String direccion;
    protected Sucursales sucursales;
    protected Rol rol;

    public Usuario(String usuario, String password, String nombre, String apellidos, LocalDate fechaNacimiento, String ciudad, String estado, String RFC, String Curp, String direccion, Sucursales sucursales, Rol rol) {
        this.usuario = usuario;
        this.password = password;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.ciudad = ciudad;
        this.estado = estado;
        this.RFC = RFC;
        this.Curp = Curp;
        this.direccion = direccion;
        this.sucursales = sucursales;
        this.rol = rol;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public String getPassword() {
        return this.password;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public LocalDate getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public String getCiudad() {
        return this.ciudad;
    }

    public String getEstado() {
        return this.estado;
    }

    public String getRFC() {
        return this.RFC;
    }

    public String getCurp() {
        return this.Curp;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public Sucursales getSucursales() {
        return this.sucursales;
    }

    public Rol getRol() {
        return this.rol;
    }

    public String toString() {
        return String.format("Nombre:%s Apellidos:%s FechaDeNacimiento:%s Ciudad:%s Estado:%s, RFC:%s Curp:%s Direccion:%s ", this.nombre, this.apellidos, this.fechaNacimiento, this.ciudad, this.estado, this.RFC, this.Curp, this.direccion);
    }
}
