/* Apellido y nombre :RUGNIA CARLOS MARTIN
DNI: 28337376 
TP3 : POO 
 */
package Clases;

public class Producto {

    private int id;
    private String nombre;
    private String marca;
    private String modelo;
    private int stock;
    private double precio;

    public Producto(int id, String nombre, String marca, String modelo, int stock, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.modelo = modelo;
        this.stock = stock;
        this.precio = precio;
    }

    public void verDetalles() {
        System.out.println("ID: " + id + ", Producto: " + nombre + ", Marca: " + marca + ", Modelo: " + modelo + ", Stock: " + stock + ", Precio: " + precio);
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getStock() {
        return stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void actualizarStock(int cantidad) {
        this.stock += cantidad;
        System.out.println("Stock actualizado. Nuevo stock: " + this.stock);
    }
}
