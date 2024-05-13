package Tarjetas;
import Tarjetas.Utils.TiposCredito;

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
}
