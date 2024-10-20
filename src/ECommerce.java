/* Apellido y nombre :RUGNIA CARLOS MARTIN
DNI: 28337376 
TP3 : POO 
 */

import java.util.ArrayList;
import java.util.Scanner;
import Clases.*;

public class ECommerce {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Usuario usuario = null;
        Carrito carrito = null;

        // Lista de productos disponibles
        ArrayList<Producto> listaProductosDisponibles = new ArrayList<>();
        listaProductosDisponibles.add(new Producto(1, "Zapatilla Deportiva", "Nike", "Deportiva", 10, 320000.50));
        listaProductosDisponibles.add(new Producto(2, "Zapatilla Running", "Adidas", "Running", 15, 150000.60));
        listaProductosDisponibles.add(new Producto(3, "Zapatilla Casual", "Puma", "Casual", 8, 210000.80));
        listaProductosDisponibles.add(new Producto(4, "Zapatilla de Basquet", "Jordan", "Basquet", 5, 350000.60));
        listaProductosDisponibles.add(new Producto(5, "Zapatilla Skate", "Vans", "Skate", 12, 410000.80));
        listaProductosDisponibles.add(new Producto(6, "Zapatilla de Trekking", "Salomon", "Trekking", 6, 125650.90));
        listaProductosDisponibles.add(new Producto(7, "Zapatilla de Futbol", "Nike", "Futbol", 10, 388000.40));
        listaProductosDisponibles.add(new Producto(8, "Zapatilla Retro", "Reebok", "Retro", 7, 110000.75));
        listaProductosDisponibles.add(new Producto(9, "Zapatilla de CrossFit", "Under Armour", "CrossFit", 4, 165000.10));
        listaProductosDisponibles.add(new Producto(10, "Zapatilla de Correr", "New Balance", "Correr", 9, 180000.25));

        // Menú interactivo
        while (true) {
            System.out.println("-----------------------");
            System.out.println("----- Deport Line -----");
            System.out.println("-----------------------");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar sesion");
            System.out.println("3. Ver productos disponibles");
            System.out.println("4. Ver productos con descuento");
            System.out.println("5. Agregar productos al carrito");
            System.out.println("6. Ver carrito");
            System.out.println("7. Procesar pago");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opcion: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    // Registrar usuario
                    System.out.print("Ingrese su nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese su email: ");
                    String email = scanner.nextLine();
                    System.out.print("Ingrese su contrasena: ");
                    String contrasena = scanner.nextLine();
                    usuario = new Usuario(1, nombre, email, contrasena);
                    usuario.registrar();
                    break;

                case 2:
                    // Iniciar sesión
                    if (usuario != null) {
                        System.out.print("Ingrese su email: ");
                        email = scanner.nextLine();
                        System.out.print("Ingrese su contrasena: ");
                        contrasena = scanner.nextLine();
                        if (usuario.iniciarSesion(email, contrasena)) {
                            carrito = new Carrito();
                        }
                    } else {
                        System.out.println("Debe registrarse primero.");
                    }
                    break;

                case 3:
                    // Ver productos disponibles
                    System.out.println("=== Productos disponibles ===");
                    for (Producto p : listaProductosDisponibles) {
                        p.verDetalles();  // Mostrar detalles del producto, incluyendo precio con IVA
                    }
                    break;

                case 4:
                    // Ver productos con descuento
                    System.out.println("=== Productos con descuento ===");
                    for (Producto p : listaProductosDisponibles) {
                        double precioConDescuento = p.calcularDescuento();
                        System.out.println(p.getNombre() + " - Precio con descuento: $" + precioConDescuento);
                    }
                    break;

                case 5:
                    // Agregar productos al carrito
                    if (carrito != null) {
                        System.out.print("Ingrese el ID del producto que desea agregar: ");
                        int idProducto = scanner.nextInt();
                        Producto productoSeleccionado = null;
                        for (Producto p : listaProductosDisponibles) {
                            if (p.getId() == idProducto) {
                                productoSeleccionado = p;
                                break;
                            }
                        }
                        if (productoSeleccionado != null) {
                            carrito.agregarProducto(productoSeleccionado);
                            System.out.println("Producto agregado al carrito.");
                        } else {
                            System.out.println("Producto no encontrado.");
                        }
                    } else {
                        System.out.println("Debe iniciar sesion primero.");
                    }
                    break;

                case 6:
                    // Ver productos en el carrito
                    if (carrito != null) {
                        System.out.println("=== Productos en el Carrito ===");
                        for (Producto p : carrito.getProductos()) {
                            p.verDetalles();  // Mostrar detalles, incluyendo precio con IVA
                        }
                    } else {
                        System.out.println("Debe iniciar sesion primero.");
                    }
                    break;

                case 7:
                    // Procesar el pago
                    if (carrito != null && !carrito.getProductos().isEmpty()) {
                        System.out.println("Procesando pago...");
                        Pago pago = new PagoConTarjeta();
                        pago.procesarPago();

                        // Mostrar los productos comprados
                        System.out.println("Productos comprados:");
                        double totalGastado = 0;
                        for (Producto p : carrito.getProductos()) {
                            p.verDetalles();
                            totalGastado += p.getPrecio();
                        }

                        // Mostrar el total gastado
                        System.out.println("Total gastado: $" + totalGastado);
                        System.out.println("Pago procesado con exito.");

                        // Limpiar el carrito después del pago
                        carrito = new Carrito();
                    } else {
                        System.out.println("El carrito está vacio. No se puede procesar el pago.");
                    }
                    break;

                case 8:
                    // Salir
                    System.out.println("Gracias por usar Deport Line.");
                    return;

                default:
                    System.out.println("Opcion no válida. Intente de nuevo.");
            }
        }
    }
}
