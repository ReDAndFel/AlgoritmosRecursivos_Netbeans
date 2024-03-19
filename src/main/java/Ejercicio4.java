
/*
 * Integrantes de grupo:
 * Andres Felipe Castro Cardona
 * Andres Felipe Garcia
 */
public class Ejercicio4 {

    public void execEjercicio4() {
        System.out.println("Ejercicio 4.4: Palabras encadenadas");

        // Se instancia la matriz planteada en el ejercicio.
        String[][] listWords = {
                { "Sien", "encima", "mapa" },
                { "Pata", "tapa", "papa" },
                { "Pato", "toma", "mama" }
        };

        System.out.println("Tenemos la siguiente matriz:");

        // imprime la matriz de palabras
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(listWords[i][j] + "\t");
            }
            System.out.println();
        }

        // Variables para calcular el tiempo de ejecucion
        long startTime;
        long endTime;
        long duration;

        startTime = System.nanoTime();
        boolean flagValidListIt = isValidListIt(listWords);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("La ejecucion del algoritmo iterativo tardo " + duration + " nanosegundos.");

        startTime = System.nanoTime();
        boolean flagValidListRec = isValidListRec(listWords);
        endTime = System.nanoTime();
        duration = endTime - startTime;

        // Se valida si ambos algoritmos dieron los mismos resultados.
        System.out.println("La ejecucion del algoritmo recursivo tardo " + duration
                + " nanosegundos.");

        if (flagValidListIt && flagValidListRec) {
            System.out.println("La lista de palabras es valida. Las palabras estan encadenadas");
        } else {
            System.out.println("La lista de palabras no es valida.");
        }

    }

    // Valida si dos palabras están encadenadas
    private boolean areChained(String word1, String word2) {
        String lastSyllableWord1 = word1.substring(word1.length() - 2).toLowerCase();
        String firstSyllableWord2 = word2.substring(0, 2).toLowerCase();
        return lastSyllableWord1.equals(firstSyllableWord2);
    }

    // Metodo iterativo
    private boolean isValidListIt(String[][] words) {
        // Verificar encadenamiento entre palabras consecutivas
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = 0; j < words[0].length - 1; j++) {
                String currentWord = words[i][j];
                String newWord = words[i][j + 1];
                if (!areChained(currentWord, newWord)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Metodo recursivo
    private boolean isValidListRec(String[][] words) {
        return isValidListRec(words, 0, 0);
    }

    private boolean isValidListRec(String[][] words, int startRow, int startCol) {
        if (startCol == words[startRow].length - 1) {
            // Si llegamos al final de la fila actual, pasamos a la siguiente fila
            if (startRow == words.length - 1) {
                return true; // Si estamos en la última fila, la lista es válida
            }
            // Reiniciamos la columna para verificar la próxima fila
            return isValidListRec(words, startRow + 1, 0);
        }

        String currentWord = words[startRow][startCol]; // Palabra actual
        String nextWord = words[startRow][startCol + 1]; // Palabra siguiente en la misma fila

        if (!areChained(currentWord, nextWord)) {
            return false; // Si las palabras no están encadenadas, la lista no es válida
        }

        // Llamada recursiva para la misma fila, siguiente columna
        return isValidListRec(words, startRow, startCol + 1);
    }
}
