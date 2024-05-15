package Usuarios.Utils;

import Usuarios.Usuario;

public class UsuarioActivo {
    private static UsuarioActivo instance;
    private static Usuario usuario;

    public UsuarioActivo() {}

    public static UsuarioActivo getInstance() {
        if (instance == null) {
            instance = new UsuarioActivo();
        }

        return instance;
    }

    public static Usuario getUsuarioActual() {
        return usuario;
    }


    public void setUsuario(Usuario usuario){
        UsuarioActivo.usuario = usuario;
    }

    public boolean existeUsuarioEnSesion(Usuario usuario) {
        return UsuarioActivo.usuario != null;
    }

    public void cerrarSesionActiva() {
        instance = null;
        usuario = null;
    }
}
