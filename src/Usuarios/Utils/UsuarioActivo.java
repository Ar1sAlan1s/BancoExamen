package Usuarios.Utils;

import Usuarios.Usuario;

public class UsuarioActivo {
    private static UsuarioActivo instance;
    private Usuario usuarioActual;

    public UsuarioActivo() {
    }

    public static UsuarioActivo getInstance() {
        if (instance == null) {
            instance = new UsuarioActivo();
        }

        return instance;
    }

    public Usuario getUsuarioActual() {
        return this.usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public void setUsuario(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public boolean existeUsuarioEnSesion() {
        return this.usuarioActual != null;
    }

    public void cerrarSesionActiva() {
        instance = null;
        this.usuarioActual = null;
    }
}
