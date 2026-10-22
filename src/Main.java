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