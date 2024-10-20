/* Apellido y nombre :RUGNIA CARLOS MARTIN
DNI: 28337376 
TP3 : POO 
 */
package Clases;

public abstract class ProductoBase {

    private int id;
    private String nombre;
    private String marca;
    private String categoria;
    private int stock;
    private double precio;

    // Atributo estático final para el IVA (constante)
    private static final double IVA = 0.21; // 21% de IVA, no modificable

    // Constructor de ProductoBase
    public ProductoBase(int id, String nombre, String marca, String categoria, int stock, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.categoria = categoria;
        this.stock = stock;
        this.precio = precio;
    }

    // Método para calcular el precio con IVA
    public double getPrecioConIVA() {
        return this.precio * (1 + IVA);
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMarca() {
        return marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getStock() {
        return stock;
    }

    public double getPrecio() {
        return precio;
    }

    public static double getIVA() {
        return IVA;
    }
}
