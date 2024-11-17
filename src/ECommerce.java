/* Apellido y nombre :RUGNIA CARLOS MARTIN
DNI: 28337376 
TP4 : POO 
 */

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import Clases.*;
import InterfacePago.Pago;
import Exceptions.CaracteresInvalidosException;

public class ECommerce {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Usuario usuario = null;
        Carrito carrito = null;
        /* Gestor de productos para facilitar la actualizacíon a futuro en el caso que
haya modificiones o cambios de precio sin necesidad de modificar la clase principal.*/
        GestorProductos gestorProductos = new GestorProductos();

        /* Menú interactivo con un bloque try-catch para capturar la excepción por ingreso de 
        caracteres no deseados. */
        while (true) {
            try {
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
                int opcion;

                try {
                    opcion = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea

                    if (opcion < 1 || opcion > 8) {
                        throw new CaracteresInvalidosException("Opcion invalida. Por favor, seleccione una opcion entre 1 y 8.");
                    }
                    //Captura excepciones
                } catch (InputMismatchException e) {
                    scanner.nextLine(); // Limpiar el buffer
                    throw new CaracteresInvalidosException("Entrada invalida. Por favor, ingrese un numero valido.");
                }

                switch (opcion) {
                    case 1 -> {
                        // Registrar usuario
                        System.out.print("Ingrese su nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Ingrese su email: ");
                        String email = scanner.nextLine();
                        System.out.print("Ingrese su contrasena: ");
                        String contrasena = scanner.nextLine();

                        usuario = new Usuario(1, nombre, email, contrasena);
                        usuario.registrar();

                        // Guardar usuario en el archivo usuarios.txt
                        guardarUsuarioEnArchivo(usuario);
                    }

                    case 2 -> {
                        // Iniciar sesión
                        if (usuario != null) {
                            System.out.print("Ingrese su email: ");
                            String emailSesion = scanner.nextLine();
                            System.out.print("Ingrese su contrasena: ");
                            String contrasenaSesion = scanner.nextLine();
                            if (usuario.iniciarSesion(emailSesion, contrasenaSesion)) {
                                carrito = new Carrito();
                            }
                        } else {
                            System.out.println("Debe registrarse primero.");
                        }
                    }

                    case 3 -> {
                        // Ver productos disponibles llamando al gestor de productos
                        System.out.println("=== Productos disponibles ===");
                        for (Producto p : gestorProductos.getListaProductos()) {
                            p.verDetalles();
                        }
                    }

                    case 4 -> {
                        // Ver productos con descuento llamando al gestor de productos
                        System.out.println("=== Productos con descuento ===");
                        for (Producto p : gestorProductos.getListaProductos()) {
                            double precioConDescuento = p.calcularDescuento();
                            System.out.println(p.getNombre() + " - Precio con descuento: $" + precioConDescuento);
                        }
                    }

                    case 5 -> {
                        // Agregar productos al carrito
                        if (carrito != null) {
                            System.out.print("Ingrese el ID del producto que desea agregar: ");
                            int idProducto = scanner.nextInt();
                            Producto productoSeleccionado = gestorProductos.buscarProductoPorId(idProducto);
                            if (productoSeleccionado != null) {
                                carrito.agregarProducto(productoSeleccionado);
                                System.out.println("Producto agregado al carrito.");
                            } else {
                                System.out.println("Producto no encontrado.");
                            }
                        } else {
                            System.out.println("Debe iniciar sesion primero.");
                        }
                    }

                    case 6 -> {
                        // Ver productos en el carrito
                        if (carrito != null) {
                            System.out.println("=== Productos en el Carrito ===");
                            for (Producto p : carrito.getProductos()) {
                                p.verDetalles();
                            }
                        } else {
                            System.out.println("Debe iniciar sesion primero.");
                        }
                    }

                    case 7 -> {
                        // Procesar el pago
                        if (carrito != null && !carrito.getProductos().isEmpty()) {
                            System.out.println("Seleccione el metodo de pago:");
                            System.out.println("1. Tarjeta");
                            System.out.println("2. Efectivo");

                            try {
                                int metodoPago = scanner.nextInt();
                                scanner.nextLine();

                                Pago pago;

                                if (metodoPago == 1) {
                                    pago = new PagoConTarjeta();
                                } else if (metodoPago == 2) {
                                    pago = new PagoEnEfectivo();
                                } else {
                                    System.out.println("Metodo de pago no valido.");
                                    break;
                                }

                                double totalGastado = 0;
                                for (Producto p : carrito.getProductos()) {
                                    totalGastado += p.getPrecio();
                                }

                                if (pago.validarPago(totalGastado)) {
                                    pago.procesarPago();

                                    System.out.println("Productos comprados:");
                                    for (Producto p : carrito.getProductos()) {
                                        p.verDetalles();
                                    }

                                    System.out.println("Total gastado: $" + totalGastado);
                                    System.out.println("Pago procesado con exito.");

                                    carrito = new Carrito();
                                } else {
                                    System.out.println("El pago no pudo ser validado.");
                                }
                                //Captura de excepciones
                            } catch (InputMismatchException e) {
                                scanner.nextLine();
                                System.out.println("Entrada invalida. Seleccione una opcion numerica valida.");
                            }
                        } else {
                            System.out.println("Debe iniciar sesion y tener productos en el carrito para procesar el pago.");
                        }
                    }

                    case 8 -> {
                        // Salir
                        System.out.println("Gracias por usar Deport Line.");
                        return;
                    }

                    default ->
                        System.out.println("Opcion no valida.");
                }
                //Captura de excepciones
            } catch (CaracteresInvalidosException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /* Método para guardar usuarios en un archivo de forma ordena separados por salto 
    de lineas y delimitados por linea de guiones*/
    private static void guardarUsuarioEnArchivo(Usuario usuario) {
        String rutaArchivo = "usuarios.txt";
        try (FileOutputStream fos = new FileOutputStream(rutaArchivo, true)) {
            String datosUsuario = "Nombre: " + usuario.getNombre() + "\n"
                    + "Email: " + usuario.getEmail() + "\n"
                    + "Contraseña: " + usuario.getContrasena() + "\n"
                    + "-------------------------\n";
            fos.write(datosUsuario.getBytes());
            System.out.println("Usuario guardado en el archivo.");
            //Captura de excepciones por posibles errores al escribir el archivo.
        } catch (IOException e) {
            System.out.println("Error al guardar el usuario en el archivo: " + e.getMessage());
        }
    }
}
