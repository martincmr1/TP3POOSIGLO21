/* Apellido y nombre :RUGNIA CARLOS MARTIN
DNI: 28337376 
TP3 : POO 
 */
package Clases;

public class Producto extends ProductoBase {

    // Constructor de Producto
    public Producto(int id, String nombre, String marca, String categoria, int stock, double precio) {
        super(id, nombre, marca, categoria, stock, precio);
    }

    public double calcularDescuento() {
        // Aplicar un 10% de descuento
        return this.getPrecio() * 0.9;
    }

    // Mostrar los detalles del producto, incluyendo el precio con IVA
    public void verDetalles() {
        System.out.println("ID: " + this.getId());
        System.out.println("Nombre: " + this.getNombre());
        System.out.println("Marca: " + this.getMarca());
        System.out.println("Categoria: " + this.getCategoria());
        System.out.println("Stock: " + this.getStock());
        System.out.println("Precio sin IVA: $" + this.getPrecio());
        System.out.println("Precio con IVA: $" + this.getPrecioConIVA());
    }
}
