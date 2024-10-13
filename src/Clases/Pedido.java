/* Apellido y nombre :RUGNIA CARLOS MARTIN
DNI: 28337376 
TP3 : POO 
 */
package Clases;

import java.util.List;

public class Pedido {

    private int id;
    private int idUsuario;
    private List<Producto> listaProductos;
    private double total;
    private String estado;

    public Pedido(int id, int idUsuario, List<Producto> listaProductos, double total) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.listaProductos = listaProductos;
        this.total = total;
        this.estado = "Pendiente";
    }

    public void realizarPago() {
        System.out.println("********************************");
        System.out.println("Pago realizado por el monto de: $" + total);
        System.out.println("********************************");
        this.estado = "Pagado";
    }

    public void actualizarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
        System.out.println("Estado actualizado a: " + nuevoEstado);
    }
}
