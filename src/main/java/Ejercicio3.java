
/*
 * Integrantes de grupo:
 * Andres Felipe Castro Cardona
 * Andres Felipe Garcia
 * Brayan Sriven Valencia Villa
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ejercicio3 {

    public void execEjercicio3() {
        System.out.println("Ejercicio 4.3: Palabras similares");
        Scanner scanner = new Scanner(System.in);
        int n = 0;
        boolean validInput = false;
        // Solicitar al usuario que ingrese un número válido de filas
        while (!validInput) {
            System.out.print("Ingrese la cantidad de filas que desea: ");
            if (scanner.hasNextInt()) {
                n = scanner.nextInt();
                if (n > 0) {
                    validInput = true;
                } else {
                    System.out.println("Por favor, ingrese un numero entero positivo.");
                }
            } else {
                System.out.println("Por favor, ingrese un numero entero válido.");
                scanner.next(); // Limpiar el buffer del scanner
            }
        }
        // Crea la matriz de dos columnas por las filas que haya digitado el usuario
        String[][] matrix = new String[n][2];

        // Pedir al usuario que ingrese palabras para cada posición de la matriz
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print("Ingrese una palabra para la posicion [" + i + "][" + j + "]: ");
                matrix[i][j] = scanner.next();
            }
        }

        // variables para calcular el tiempo de ejecución
        long startTime;
        long endTime;
        long duration;

        startTime = System.nanoTime();
        List<Integer> resultRec = findSimilarWordsRec(matrix);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("Arreglo de las posiciones de las palabras similares:");
        System.out.println(resultRec);
        System.out.println("La ejecucion del algoritmo recursivo tardo " + duration + " nanosegundos.");

        startTime = System.nanoTime();
        List<Integer> resultIt = findSimilarWordsIt(matrix);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("Arreglo de las posiciones de las palabras similares:");
        System.out.println(resultIt);
        System.out.println("La ejecucion del algoritmo iterativo tardo " + duration + " nanosegundos.");
        scanner.close();
    }

    // Algoritmo Recursivo
    private List<Integer> findSimilarWordsRec(String[][] matrix) {
        List<Integer> result = new ArrayList<>();
        findSimilarWordsRec(matrix, 0, result);
        return result;
    }
    // Algoritmo recursivo
    private void findSimilarWordsRec(String[][] matrix, int row, List<Integer> result) {
        if (row >= matrix.length) {
            return;
        }

        String word1 = matrix[row][0];
        String word2 = matrix[row][1];

        if (areSimilar(word1, word2)) {
            result.add(row);
        }

        findSimilarWordsRec(matrix, row + 1, result);
    }

    // Algoritmo Iterativo
    private List<Integer> findSimilarWordsIt(String[][] matrix) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++) {
            String word1 = matrix[i][0];
            String word2 = matrix[i][1];

            if (areSimilar(word1, word2)) {
                result.add(i);
            }
        }

        return result;
    }

    // Función para verificar si dos palabras son similares
    private boolean areSimilar(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }

        for (int i = 0; i < word1.length(); i++) {
            char c = word1.charAt(i);
            if (word2.indexOf(c) == -1) {
                return false;
            }
        }

        return true;
    }

}
