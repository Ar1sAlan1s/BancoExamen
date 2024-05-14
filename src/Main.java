import Banco.Banco;
import Usuarios.Cliente;
import Usuarios.Empleado;
import Usuarios.Utils.Rol;
import Usuarios.Utils.Sucursales;
import java.time.LocalDate;
import Banco.Menu;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();
        Menu menu = new Menu();
        Cliente cliente=new Cliente("","","","", LocalDate.now(),"","","", "","", Sucursales.Madero, Rol.Cliente);
        Empleado empleado1=new Empleado("dorian2k", "dsfosdn", "dorian ferreira", "calderon cla", LocalDate.of(2004,8,30), "morelia", "momomo", "fjfn", "njnjvnsj", "ndjfjnndf", Sucursales.Madero, Rol.Capturista, 30000, LocalDate.now());
        menu.ejecutarMenuBanco(); //Aquí ejecuto el menu del banco
        cliente.RegistrarCliente(empleado1);
    }
}