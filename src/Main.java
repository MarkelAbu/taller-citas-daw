import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- GESTIÓN DE CITAS TALLER ---");
            System.out.println("1. Registrar nueva cita");
            System.out.println("2. Consultar citas por fecha");
            System.out.println("3. Calcular coste de servicio");
            System.out.println("4. Salir");
            System.out.println("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            if (opcion == 4) {
                salir = true;
                System.out.println("Saliendo del programa...");
            }
            if (opcion == 1) {
                registrarCita();
            }
        }
    }

    public static void registrarCita() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Marca del vehículo");
        String marca = sc.nextLine();
        System.out.println("Introduce la matrícula");
        String martricula = sc.nextLine();
        martricula = martricula.trim().toUpperCase();
        System.out.println("Introduce la fecha de adquisición (dd/MM/yyyy)");
        String fecha = sc.nextLine();
        validarFecha(fecha);
    }

    public static void validarFecha(String fecha) {
        DateTimeFormatter formato_fecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate fechaValida = LocalDate.parse(fecha, formato_fecha);
            // La fecha de compra del coche tiene que ser antes de mañana si o si, lo comprobamos también
            LocalDate fechaMañana = LocalDate.now().plusDays(1);
            if (fechaValida.isBefore(fechaMañana)){
                System.out.println("La fecha es válida: " + fechaValida);
            }

        } catch (DateTimeParseException e) {
            System.out.println("Error: El formato de la fecha es incorrecto o la fecha no existe.");
        }
    }
}