/* Apellido y nombre :RUGNIA CARLOS MARTIN
DNI: 28337376 
TP3 : POO 
 */
package Clases;

public abstract class ProductoBase {

    protected String nombre;
    protected double precio;

    public ProductoBase(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public abstract double calcularDescuento();

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
