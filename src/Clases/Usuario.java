/* Apellido y nombre :RUGNIA CARLOS MARTIN
DNI: 28337376 
TP3 : POO 
 */
package Clases;

public class Usuario {

    private int id;
    private String nombre;
    private String email;
    private String contrasena;

    public Usuario(int id, String nombre, String email, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
    }

    public void registrar() {
        System.out.println("Usuario registrado: " + nombre);
    }

    public boolean iniciarSesion(String email, String contrasena) {
        if (this.email.equals(email) && this.contrasena.equals(contrasena)) {
            System.out.println("Inicio de sesion exitoso");
            return true;
        } else {
            System.out.println("Usuario o contrasena incorrectos");
            return false;
        }
    }

    public void verHistorialCompras() {
        System.out.println("Mostrando historial de compras para: " + nombre);
    }
}
