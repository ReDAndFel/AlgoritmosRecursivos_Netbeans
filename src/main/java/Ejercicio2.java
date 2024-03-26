/*
 * Integrantes de grupo:
 * Andres Felipe Castro Cardona
 * Andres Felipe Garcia
 * Brayan Stiven Valencia Villa
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejercicio2 {
    public void execEjercicio2() {

        System.out.println("Ejercicio 4.2: Cuadrado perfecto");
        Scanner scanner = new Scanner(System.in);

        int rows = 0;
        int cols = 0;

        // Pedir al usuario el número de filas y validar
        while (true) {
            try {
                System.out.print("Ingrese el numero de filas de la matriz: ");
                rows = scanner.nextInt();
                if (rows <= 0) {
                    System.out.println("El número de filas debe ser mayor que cero.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingrese un numero entero valido para el número de filas.");
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
        // variables para calcular el tiempo de ejecucion
        long startTime;
        long endTime;
        long duration;

        startTime = System.nanoTime();
        int[][] finalMatrix1 = getMatrixIt(matrix);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("matriz resultante:");
        printMatrix(finalMatrix1);
        System.out.println("La ejecucion del algoritmo iterativo tardo " + duration + " nanosegundos.");

        startTime = System.nanoTime();
        int[][] finalMatrix2 = getMatrixRec(matrix, 0, 0);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("matriz resultante:");
        printMatrix(finalMatrix2);
        System.out.println("La ejecucion del algoritmo recursivo tardo " + duration + " nanosegundos.");
        scanner.close();
    }

    // Metodo iterativo
    private int[][] getMatrixIt(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] newMatrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (isSquarePerfect(matrix[i][j])) {
                    newMatrix[i][j] = 1; // Indica que el número es cuadrado perfecto
                } else {
                    newMatrix[i][j] = smallestPerfectSquareMultiple(matrix[i][j]);
                }
            }
        }
        return newMatrix;
    }

    // Metodo recursivo
    private int[][] getMatrixRec(int[][] matriz, int fila, int columna) {
        // Caso base: cuando hemos recorrido toda la matriz
        if (fila == matriz.length) {
            return matriz;
        }

        // Caso recursivo: procesar la fila actual y luego continuar con la siguiente
        // fila
        if (columna == matriz[0].length) {
            return getMatrixRec(matriz, fila + 1, 0);
        }

        // Procesar el elemento actual de la matriz
        if (isSquarePerfect(matriz[fila][columna])) {
            matriz[fila][columna] = 1; // Indica que el número es cuadrado perfecto
        } else {
            matriz[fila][columna] = smallestPerfectSquareMultiple(matriz[fila][columna]);
        }

        // Procesar el siguiente elemento en la misma fila
        return getMatrixRec(matriz, fila, columna + 1);
    }

    // Método para imprimir la matriz
    private void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // valida si es cuadrado perfecto
    private boolean isSquarePerfect(int num) {
        int sqrt = (int) Math.sqrt(num);
        return sqrt * sqrt == num;
    }

    // Método para encontrar el número más pequeño que al multiplicarlo por el
    // número dado produce un cuadrado perfecto
    private int smallestPerfectSquareMultiple(int num) {
        for (int i = 2; i <= num; i++) {
            if (num % i == 0 && isSquarePerfect(num / i)) {
                return i;
            }
        }
        return num;
    }
}
