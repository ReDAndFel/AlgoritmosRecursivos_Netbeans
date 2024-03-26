/*
 * Integrantes de grupo:
 * Andres Felipe Castro Cardona
 * Andres Felipe Garcia
 * Brayan Stiven Valencia Villa
 */
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejercicio5 {
    public void execEjercicio5() {
        System.out.println("Ejercicio 4.5: Numeros polidivisibles");
        Scanner scanner = new Scanner(System.in);

        int rows = 0;
        int cols = 0;

        // Pedir al usuario el número de filas y validar
        while (true) {
            try {
                System.out.print("Ingrese el numero de filas de la matriz: ");
                rows = scanner.nextInt();
                if (rows <= 0) {
                    System.out.println("El numero de filas debe ser mayor que cero.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingrese un numero entero valido para el numero de filas.");
                scanner.nextLine(); // Limpiar el buffer del scanner
            }
        }

        // Pedir al usuario el número de columnas y validar
        while (true) {
            try {
                System.out.print("Ingrese el numero de columnas de la matriz: ");
                cols = scanner.nextInt();
                if (cols <= 0) {
                    System.out.println("El numero de columnas debe ser mayor que cero.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingrese un numero entero valido para el numero de columnas.");
                scanner.nextLine(); // Limpiar el buffer del scanner
            }
        }

        // Crea la matriz con los parametros que digitó el usuario
        int[][] matrix = new int[rows][cols];

        // Pedir al usuario los números para cada posición de la matriz
        System.out.println("Ingrese los numeros para cada posicion de la matriz:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                while (true) {
                    try {
                        System.out.print("Ingrese el numero en la posicion [" + (i + 1) + "][" + (j + 1) + "]: ");
                        matrix[i][j] = scanner.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Ingrese un numero entero valido.");
                        scanner.nextLine(); // Limpiar el buffer del scanner
                    }
                }
            }
        }

        // Variables para calcular el tiempo de ejecucion
        long startTime;
        long endTime;
        long duration;

        startTime = System.nanoTime();
        ArrayList<Integer> polydivisiblesIt = findPolydivisiblesIt(matrix);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("Los numeros polidivisibles son: ");
        System.out.println(polydivisiblesIt);
        System.out.println("La ejecucion del algoritmo iterativo tardo " + duration + " nanosegundos.");

        startTime = System.nanoTime();
        ArrayList<Integer> polydivisiblesRec = findPolydivisiblesRec(matrix);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("Los numeros polidivisibles son: ");
        System.out.println(polydivisiblesRec);
        System.out.println("La ejecucion del algoritmo recursivo tardo " + duration + " nanosegundos.");
        scanner.close();
    }

    // Método iterativo para encontrar números polidivisibles en una matriz
    private ArrayList<Integer> findPolydivisiblesIt(int[][] matrix) {
        ArrayList<Integer> polydivisibles = new ArrayList<>();
        int rows = matrix.length;
        int cols = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (isPolydivisible(matrix[i][j])) {
                    polydivisibles.add(matrix[i][j]); // Indica que el número es cuadrado perfecto
                }
            }
        }

        return polydivisibles;
    }

    // Método recursivo para encontrar números polidivisibles en una matriz
    private ArrayList<Integer> findPolydivisiblesRec(int[][] matrix) {
        ArrayList<Integer> polydivisibles = new ArrayList<>();
        findPolydivisiblesRec(matrix, 0, 0, polydivisibles);
        return polydivisibles;
    }

    // Método recursivo para encontrar números polidivisibles en una matriz
    private void findPolydivisiblesRec(int[][] matrix, int startRow, int startCol,
            ArrayList<Integer> polydivisibles) {
        if (startRow == matrix.length) {
            return;
        }

        if (startCol == matrix[startRow].length) {
            findPolydivisiblesRec(matrix, startRow + 1, 0, polydivisibles);
            return;
        }

        if (isPolydivisible(matrix[startRow][startCol])) {
            polydivisibles.add(matrix[startRow][startCol]);
        }

        findPolydivisiblesRec(matrix, startRow, startCol + 1, polydivisibles);
    }

    // Método para verificar si un número es polidivisible
    private boolean isPolydivisible(int num) {
        String numStr = ("" + num);
        for (int i = 1; i <= numStr.length(); i++) {
            int subNum = Integer.parseInt(numStr.substring(0, i));
            if (subNum % i != 0) {
                return false;
            }
        }
        return true;
    }
}
