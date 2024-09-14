import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Persona {
    String nombreUsuario;
    String contrasena;
    String nombreCompleto;
    String fechaNacimiento;

    public Persona(String nombreUsuario, String contrasena, String nombreCompleto, String fechaNacimiento) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.nombreCompleto = nombreCompleto;
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean esMayorDeEdad() {
        int anioNacimiento = Integer.parseInt(fechaNacimiento.split("/")[2]);
        int anioActual = 2024; // Asumiendo año actual
        return (anioActual - anioNacimiento) >= 18;
    }

    @Override
    public String toString() {
        return "Usuario: " + nombreUsuario + ", Nombre Completo: " + nombreCompleto + ", Edad: " + (esMayorDeEdad() ? "Mayor de edad" : "Menor de edad");
    }
}

public class RegistroUsuarios {
    static List<Persona> usuarios = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (usuarios.size() < 10) {
            System.out.println("** Registro de nuevo usuario **");

            System.out.print("Nombre de usuario: ");
            String nombreUsuario = scanner.nextLine();

            System.out.print("Contraseña: ");
            String contrasena = scanner.nextLine();

            while (!validarContrasena(contrasena)) {
                System.out.println("Contraseña débil. Debe tener más de 10 caracteres, al menos 2 letras mayúsculas y 3 números o símbolos.");
                System.out.print("Introduce una contraseña válida: ");
                contrasena = scanner.nextLine();
            }

            System.out.print("Nombre completo: ");
            String nombreCompleto = scanner.nextLine();

            System.out.print("Fecha de nacimiento (dd/mm/yyyy): ");
            String fechaNacimiento = scanner.nextLine();

            Persona nuevaPersona = new Persona(nombreUsuario, contrasena, nombreCompleto, fechaNacimiento);
            usuarios.add(nuevaPersona);

            System.out.println("\nUsuario registrado exitosamente.");
            mostrarUsuariosRegistrados();

            // Mensaje adicional según la edad
            if (nuevaPersona.esMayorDeEdad()) {
                System.out.println("Bienvenido " + nuevaPersona.nombreUsuario + "! Puedes pasar a la zona para mayores de 18 años.");
            } else {
                System.out.println("Lo siento " + nuevaPersona.nombreUsuario + ", no tienes acceso a la zona para mayores de 18 años.");
            }

            if (usuarios.size() >= 10) {
                System.out.println("Capacidad máxima de usuarios alcanzada.");
                break;
            }
        }
    }

    public static boolean validarContrasena(String contrasena) {
        if (contrasena.length() <= 10) return false;

        int mayusculas = 0, numerosSimbolos = 0;
        for (char c : contrasena.toCharArray()) {
            if (Character.isUpperCase(c)) mayusculas++;
            if (Character.isDigit(c) || "!@#$%^&*()".contains(Character.toString(c))) numerosSimbolos++;
        }

        return mayusculas >= 2 && numerosSimbolos >= 3;
    }

    public static void mostrarUsuariosRegistrados() {
        System.out.println("\nLista de usuarios registrados:");
        for (Persona usuario : usuarios) {
            System.out.println(usuario);
        }
    }
}

