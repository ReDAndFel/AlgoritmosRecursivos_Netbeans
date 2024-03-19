
/*
 * Integrantes de grupo:
 * Andres Felipe Castro Cardona
 * Andres Felipe Garcia
 */
import java.util.Scanner;

public class Ejercicio1 {
    public void execEjercicio1() {
        System.out.println("Ejercicio 4.1: Matriz en espiral");
        Scanner scanner = new Scanner(System.in);
        int n = 0;
        boolean validInput = false;
        // Solicitar al usuario que ingrese un número válido
        while (!validInput) {
            System.out.print("Ingrese el tamaño de la matriz: ");
            if (scanner.hasNextInt()) {
                n = scanner.nextInt();
                if (n > 0) {
                    validInput = true;
                } else {
                    System.out.println("Por favor, ingrese un numero entero positivo.");
                }
            } else {
                System.out.println("Por favor, ingrese un numero entero valido.");
                scanner.next(); // Limpiar el buffer del scanner
            }
        }

        // se crean las matrices con las dimensiones que nos dio el usuario

        int[][] matrix1 = new int[n][n];
        int[][] matrix2 = new int[n][n];

        // variables para calcular el tiempo de ejecucion
        long startTime;
        long endTime;
        long duration;

        startTime = System.nanoTime();
        getMatrixIt(matrix1);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        printMatrix(matrix1);
        System.out.println("La ejecucion del algoritmo iterativo tardo " + duration + " nanosegundos.");

        startTime = System.nanoTime();
        getMatrixRec(matrix2, 0, n - 1, 0, n - 1, 1);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        printMatrix(matrix2);
        System.out.println("La ejecucion del algoritmo recursivo tardo " + duration + " nanosegundos.");
        scanner.close();
    }

    // Metodo iterativo
    private void getMatrixIt(int[][] matrix) {

        int num = 1;
        int leftColumn = 0, rightColumn = matrix.length - 1;
        int topRow = 0, bottomRow = matrix.length - 1;

        int totalElements = matrix.length * matrix.length;

        while (num <= totalElements) {
            // Llenar de arriba hacia abajo en la primera columna
            for (int i = topRow; i <= bottomRow; i++)
                matrix[i][leftColumn] = num++;
            leftColumn++;

            // Llenar de izquierda a derecha en la última fila
            for (int i = leftColumn; i <= rightColumn; i++)
                matrix[bottomRow][i] = num++;
            bottomRow--;

            // Llenar de abajo hacia arriba en la última columna
            for (int i = bottomRow; i >= topRow; i--)
                matrix[i][rightColumn] = num++;
            rightColumn--;

            // Llenar de derecha a izquierda en la primera fila
            for (int i = rightColumn; i >= leftColumn; i--)
                matrix[topRow][i] = num++;
            topRow++;
        }

    }

    // Metodo recursivo
    private void getMatrixRec(int[][] matrix, int startRow, int endRow, int startCol, int endCol, int num) {
        // Caso base: cuando la matriz se reduce a una sola fila o columna
        if (startRow > endRow || startCol > endCol) {

            return;
        }

        // Llenar de arriba hacia abajo en la columna izquierda
        for (int i = startRow; i <= endRow; i++) {
            matrix[i][startCol] = num++;
        }

        // Llenar de izquierda a derecha en la fila inferior
        for (int i = startCol + 1; i <= endCol; i++) {
            matrix[endRow][i] = num++;
        }

        // Llenar de abajo hacia arriba en la columna derecha
        for (int i = endRow - 1; i >= startRow; i--) {
            matrix[i][endCol] = num++;
        }

        // Llenar de derecha a izquierda en la fila superior
        for (int i = endCol - 1; i >= startCol + 1; i--) {
            matrix[startRow][i] = num++;
        }

        // Llamada recursiva con una matriz de tamaño reducido
        getMatrixRec(matrix, startRow + 1, endRow - 1, startCol + 1, endCol - 1, num);
    }

    // Imprime la matriz
    private void printMatrix(int[][] matrix) {
        // Recorre la matriz y la imprime
        for (int[] row : matrix) {
            for (int cell : row) {
                System.out.print(cell + "\t");
            }
            System.out.println();
        }
    }

}
