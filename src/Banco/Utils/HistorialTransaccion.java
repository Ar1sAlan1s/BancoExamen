package Banco.Utils;

import Usuarios.Usuario;
import Usuarios.Utils.Rol;
import Usuarios.Utils.Sucursales;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class HistorialTransaccion {
    private static final HashMap<Sucursales, ArrayList<String>> historialPorSucursal = new HashMap<>();

    // Método para registrar transacciones por sucursal.
    public static void registrarTransaccion(String detalle, String nombreUsuario, String RFC, LocalDate fecha, Sucursales sucursal) {
        String transaccion = fecha.toString() + ": " + detalle + " - Usuario: " + nombreUsuario + ", RFC: " + RFC;
        // Obtener el historial de transacciones de la sucursal actual o crear uno si no existe
        ArrayList<String> historial = historialPorSucursal.computeIfAbsent(sucursal, k -> new ArrayList<>());
        historial.add(transaccion);
    }

    // Método para mostrar el historial de transacciones de una sucursal específica.
    public static void mostrarHistorialPorSucursal(Sucursales sucursal, Usuario gerente) {
        if (gerente.getSucursales() == sucursal && gerente.getRol() == Rol.Gerente) {
            ArrayList<String> historial = historialPorSucursal.get(sucursal);
            if (historial != null) {
                System.out.println("Historial de transacciones de la sucursal " + sucursal + ":");
                for (String transaccion : historial) {
                    System.out.println(transaccion);
                }
            } else {
                System.out.println("No hay transacciones registradas para esta sucursal.");
            }
        } else {
            System.out.println("No tiene permiso para ver el historial de transacciones de esta sucursal.");
        }
    }
}

