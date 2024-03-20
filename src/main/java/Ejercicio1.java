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
                    System.out.println("Por favor, ingrese un número entero positivo.");
                }
            } else {
                System.out.println("Por favor, ingrese un número entero válido.");
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

        //Se instancian las variables para pasarle al metodo recursivo
        int sum = 1;
        char flag = 's'; //para que comience a iterar hacia abajo
        int i = 0;
        int j = 0;
        int startRow = 0;
        int startCol = 0;
        int endRow = matrix2.length - 1;
        int endCol = matrix2[0].length - 1;

        startTime = System.nanoTime();
        matrix2 = getMatrixRec(matrix2, i, j, startRow, startCol, endRow, endCol, sum, flag);
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
            for (int i = topRow; i <= bottomRow; i++) {
                matrix[i][leftColumn] = num++;
            }
            leftColumn++;

            // Llenar de izquierda a derecha en la última fila
            for (int i = leftColumn; i <= rightColumn; i++) {
                matrix[bottomRow][i] = num++;
            }
            bottomRow--;

            // Llenar de abajo hacia arriba en la última columna
            for (int i = bottomRow; i >= topRow; i--) {
                matrix[i][rightColumn] = num++;
            }
            rightColumn--;

            // Llenar de derecha a izquierda en la primera fila
            for (int i = rightColumn; i >= leftColumn; i--) {
                matrix[topRow][i] = num++;
            }
            topRow++;
        }

    }

    // Metodo recursivo
    private int[][] getMatrixRec(int[][] matrix, int i, int j, int startRow, int startCol, int endRow, int endCol, int sum, char flag) {
        // Caso base: cuando la matriz se reduce a una sola fila o columna
        if (sum > matrix.length * matrix[0].length) {
            return matrix;
        }
        // instancia el valor de incrementa en la posicion de la matriz
        matrix[i][j] = sum;

        //valida extremos de la matriz para cambiar el sentido del recorrido
        //Cambia el sentido de arriba a abajo a izquierda a derecha y disminuye la matriz por la izquierda
        if (i == endRow && flag == 's') {
            flag = 'e';
            startCol++;
        }
        //Cambia el sentido de izquierda a derecha a abajo a arriba y disminuye la matriz por abajo
        if (j == endCol && flag == 'e') {
            flag = 'n';
            endRow--;
        }
        //Cambia el sentido de abajo a arriba a derecha a izquierda y disminuye la matriz por la derecha
        if (i == startRow && flag == 'n') {
            flag = 'o';
            endCol--;
        }
        //Cambia el sentido de izquierda a derecha de abajo hacia arriba y disminuye la matriz por arriba
        if (j == startCol && flag == 'o') {
            flag = 's';
            startRow++;
        }

        // case que de acuerdo al sentido (flag) comienza a iterar
        switch (flag) {
            case 's':
                return getMatrixRec(matrix, i + 1, j, startRow, startCol, endRow, endCol, sum + 1, flag);

            case 'e':
                return getMatrixRec(matrix, i, j + 1, startRow, startCol, endRow, endCol, sum + 1, flag);
            case 'n':
                return getMatrixRec(matrix, i - 1, j, startRow, startCol, endRow, endCol, sum + 1, flag);
            default:
                return getMatrixRec(matrix, i, j - 1, startRow, startCol, endRow, endCol, sum + 1, flag);
        }
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
