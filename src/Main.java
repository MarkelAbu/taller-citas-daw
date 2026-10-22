import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
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
        class GestorFechas {

            // Constante con el patrón de formato español
            private static final String PATRON_FECHA = "dd/MM/yyyy HH:mm";

            // Formateador reutilizable configurado con la localización en español
            private static final DateTimeFormatter FORMATO_ESPANOL =
                    DateTimeFormatter.ofPattern(PATRON_FECHA, new Locale("es", "ES"));

            /**
             * Convierte un objeto LocalDateTime a una cadena con
             * formato español dd/MM/yyyy HH:mm
             *
             * @param fechaHora Objeto LocalDateTime a formatear
             * @return Cadena de texto formateada o mensaje de error si el parámetro es nulo
             */
            public static String formatearFechaEspanol(LocalDateTime fechaHora) {
                if (fechaHora == null) {
                    return "Fecha no disponible";
                }
                return fechaHora.format(FORMATO_ESPANOL);
            }

            /**
             * Pide al usuario una fecha y hora por consola, valida la entrada
             * y la devuelve como objeto LocalDateTime.
             *
             * @param scanner Objeto Scanner activo
             * @return LocalDateTime introducido por el usuario o la fecha actual si falla
             */
            public static LocalDateTime leerYValidarFecha(Scanner scanner) {
                System.out.print("Introduzca la fecha y hora de la cita (" + PATRON_FECHA + "): ");
                String entrada = scanner.nextLine().trim();

                try {
                    // Intenta convertir la cadena según el patrón definido
                    LocalDateTime fechaParseada =
                            LocalDateTime.parse(entrada, FORMATO_ESPANOL);
                    System.out.println("Fecha registrada correctamente.");
                    return fechaParseada;

                } catch (DateTimeParseException e) {
                    System.out.println("Formato incorrecto. Debe usar exactamente el formato: dd/MM/yyyy HH:mm");
                    System.out.println("Se asignará la fecha y hora actual por defecto.");
                    return LocalDateTime.now();
                }
            }

            /**
             * Ejemplo de prueba para invocar desde la opción correspondiente del menú.
             */
            public static void probarFormatoFecha(Scanner scanner) {
                System.out.println("\n--- CONSULTA Y REGISTRO DE FECHAS ---");

                // 1. Mostrar la fecha/hora actual formateada
                LocalDateTime ahora = LocalDateTime.now();
                String fechaActualFormateada = formatearFechaEspanol(ahora);
                System.out.println("Fecha y hora actual del sistema: " + fechaActualFormateada);

                // 2. Pedir una fecha al usuario y mostrar el resultado formateado
                LocalDateTime fechaCita = leerYValidarFecha(scanner);
                System.out.println("Cita fijada para el: " + formatearFechaEspanol(fechaCita));
            }
        }
        public static double calcularCosteServicio (int tipoServicio , String matricula ) {
            double precioBase ;

            switch ( tipoServicio ) {
                case 1:
                    precioBase = 50.0; // Cambio de aceite y filtro
                    break;
                case 2:
                    precioBase = 120.0; // Revision general / Pre -ITV
                    break;
                case 3:
                    precioBase = 200.0; // Cambio de frenos y discos
                    break;
                case 4:
                    precioBase = 350.0; // Reparacion de distribucion
                    break;
                default:
                    System.out.println("Opcion de servicio no valida. Se aplicara tarifa estandar.");
                            precioBase = 80.0;
                    break;
            }

            String matriculaLimpia = matricula .trim (). toUpperCase ();


            double porcentajeDescuento = 0.0;

            // Regla A: Vehiculos hibridos/ electricos o flotas ´especiales (ejemplo: terminan en "ECO ")
            if ( matriculaLimpia .endsWith("ECO")) {
                porcentajeDescuento = 0.20; // 20 % de descuento
                System.out.println("Aplicado 20 % de descuento por matricula ECO.");


                        // Regla B: Vehiculos antiguos o promocionales(ejemplo: contienen el numero "00")
            } else
            if ( matriculaLimpia .contains("00")) {
                porcentajeDescuento = 0.10; // 10 % de descuento
                System.out.println(" Aplicado 10 % de descuento por promocion especial (serie 00).");


                        // Regla C: Formato historico o especial (ejemplo: empieza por la letra ’H ’)
            } else if ( matriculaLimpia . startsWith ("H")) {
                porcentajeDescuento = 0.15; // 15 % de descuento
                System.out.println(" Aplicado 15 % de descuento para vehiculo historico .");
            } else {
                System.out.println("No se aplican descuentos a esta matricula .");
            }

            // 4. Calculo final del coste ´
            double descuento = precioBase * porcentajeDescuento ;
            double precioFinal = precioBase - descuento ;

            return precioFinal ;
        }


        public static void probarCalculoCoste (Scanner scanner) {
            System.out.println("\n--- CALCULO DE COSTE DE SERVICIO ---");
                    System.out.println("1. Cambio de aceite y filtro (50?)");
            System.out.println("2. Revision general (120?)");
            System.out.println("3. Cambio de frenos (200?)");
            System.out.println("4. Distribucion (350?)");
            System.out.print(" Seleccione el tipo de servicio : ");
            int opcionServicio = scanner.nextInt ();

            scanner.nextLine (); // Limpieza de bufer tras nextInt ()

            System.out.print(" Introduzca la matricula del vehiculo (ej. 1234 ECO , H1234BBB , 0012 KFX): ");
            String matriculaInput = scanner. nextLine ();

            double total = calcularCosteServicio (opcionServicio , matriculaInput );

            System.out.printf("El importe total a pagar es: %.2f ?\n", total);
        }
    }
    }
}