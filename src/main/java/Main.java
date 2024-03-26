
/*
 * Integrantes de grupo:
 * Andres Felipe Castro Cardona
 * Andres Felipe Garcia
 * Brayan Stiven Valencia Villa
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Menu de ejercicios");
        Scanner scanner = new Scanner(System.in);
        int n = 0;
        boolean validInput = false;
        // Solicitar al usuario que ingrese un número válido
        while (!validInput) {
            System.out.println(
                    "Ingrese el ejercicio deseado:\n\n1) Ejercicio 4.1: Matriz en espiral.\n2) Ejercicio 4.2: Cuadrado perfecto.\n3) Ejercicio 4.3: Palabras similares.\n4) Ejercicio 4.4: Palabras encadenadas.\n5) Ejercicio 4.5: Numeros polidivisibles.");
            if (scanner.hasNextInt()) {
                n = scanner.nextInt();
                if (n > 0 && n < 6) {
                    validInput = true;
                } else {
                    System.out.println("Por favor, ingrese una opcion valida. Las opciones aceptadas son numeros del 0 al 5");
                }
            } else {
                System.out.println("Por favor, ingrese una opcion valida. Solo se aceptan numeros enteros");
                scanner.next(); // Limpiar el buffer del scanner
            }
        }

        switch (n) {
            case 1:
                Ejercicio1 ejercicio1 = new Ejercicio1();
                ejercicio1.execEjercicio1();
                break;
            case 2:
                Ejercicio2 ejercicio2 = new Ejercicio2();
                ejercicio2.execEjercicio2();
                break;
            case 3:
                Ejercicio3 ejercicio3 = new Ejercicio3();
                ejercicio3.execEjercicio3();
                break;
            case 4:
                Ejercicio4 ejercicio4 = new Ejercicio4();
                ejercicio4.execEjercicio4();
                break;

            default:
                Ejercicio5 ejercicio5 = new Ejercicio5();
                ejercicio5.execEjercicio5();
                break;
        }
        scanner.close();
    }
}
