package resource;
import com.beesion.ms.resource.FibonacciResource;
import com.beesion.ms.resource.SudokuValidatorResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * Tests unitarios para comprobar la funcionalidad de los dos casos documentados
 */
@QuarkusTest
public class SolutionTest {

    private final SudokuValidatorResource sudokuValidator = new SudokuValidatorResource();
    private final FibonacciResource fibonacciResource = new FibonacciResource();

    // =================== TESTS PARA SUDOKU VALIDATOR ===================

    @Test
    @DisplayName("Ejemplo 1 - Sudoku válido debe retornar true")
    public void testValidSudokuExample1() {
        // Ejemplo 1 del documento - tablero válido
        String[][] validBoard = {
                {"5", "3", "", "", "7", "", "", "", ""},
                {"6", "", "", "1", "9", "5", "", "", ""},
                {"", "9", "8", "", "", "", "", "6", ""},
                {"8", "", "", "", "6", "", "", "", "3"},
                {"4", "", "", "8", "", "3", "", "", "1"},
                {"7", "", "", "", "2", "", "", "", "6"},
                {"", "6", "", "", "", "", "2", "8", ""},
                {"", "", "", "4", "1", "9", "", "", "5"},
                {"", "", "", "", "8", "", "", "7", "9"}
        };

        System.out.println("🔍 PROBANDO SUDOKU EJEMPLO 1 (Válido):");
        printSudokuBoard(validBoard);

        boolean result = sudokuValidator.isSudokuValid(validBoard);

        System.out.println("✅ Resultado: " + (result ? "VÁLIDO" : "INVÁLIDO"));
        System.out.println("-------------------------------------------\n");

        assertTrue(result, "El tablero del Ejemplo 1 debería ser válido");
    }

    @Test
    @DisplayName("Ejemplo 2 - Sudoku inválido debe retornar false")
    public void testInvalidSudokuExample2() {
        // Ejemplo 2 del documento - tablero inválido (5 modificado a 8)
        String[][] invalidBoard = {
                {"8", "3", "", "", "7", "", "", "", ""},
                {"6", "", "", "1", "9", "8", "", "", ""},  // Aquí hay dos 8s en la fila
                {"", "9", "8", "", "", "", "", "6", ""},
                {"8", "", "", "", "6", "", "", "", "3"},
                {"4", "", "", "8", "", "3", "", "", "1"},
                {"7", "", "", "", "2", "", "", "", "6"},
                {"", "6", "", "", "", "", "2", "8", ""},
                {"", "", "", "4", "1", "9", "", "", "5"},
                {"", "", "", "", "8", "", "", "7", "9"}
        };

        System.out.println("🔍 PROBANDO SUDOKU EJEMPLO 2 (Inválido):");
        printSudokuBoard(invalidBoard);

        boolean result = sudokuValidator.isSudokuValid(invalidBoard);

        System.out.println("Resultado: " + (result ? "VÁLIDO" : "INVÁLIDO"));
        System.out.println("Razón: Hay dos 8s en la primera columna y segunda fila");
        System.out.println("-------------------------------------------\n");

        assertFalse(result, "El tablero del Ejemplo 2 debería ser inválido");
    }

    @Test
    @DisplayName("Sudoku con tablero nulo debe retornar false")
    public void testNullSudokuBoard() {
        boolean result = sudokuValidator.isSudokuValid(null);
        assertFalse(result, "Un tablero nulo debería ser inválido");
    }

    @Test
    @DisplayName("Sudoku con dimensiones incorrectas debe retornar false")
    public void testInvalidSudokuDimensions() {
        String[][] smallBoard = {
                {"1", "2"},
                {"3", "4"}
        };

        boolean result = sudokuValidator.isSudokuValid(smallBoard);
        assertFalse(result, "Un tablero con dimensiones incorrectas debería ser inválido");
    }

    @Test
    @DisplayName("Sudoku con números repetidos en columna debe retornar false")
    public void testSudokuWithRepeatedNumbersInColumn() {
        String[][] boardWithRepeatedColumn = {
                {"1", "2", "3", "4", "5", "6", "7", "8", "9"},
                {"1", "", "", "", "", "", "", "", ""},  // 1 repetido en primera columna
                {"", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", "", ""}
        };

        boolean result = sudokuValidator.isSudokuValid(boardWithRepeatedColumn);
        assertFalse(result, "Un tablero con números repetidos en columna debería ser inválido");
    }

    // =================== TESTS PARA FIBONACCI ===================

    @Test
    @DisplayName("Fibonacci con [0,1] y n=9 debe generar secuencia correcta")
    public void testFibonacciExample1() {
        // Ejemplo del documento: fibonacci([0, 1], 9) debería retornar [0, 1, 1, 2, 3, 5, 8, 13, 21]
        int[] initialNumbers = {0, 1};
        int n = 9;

        System.out.println("PROBANDO FIBONACCI EJEMPLO 1:");
        System.out.println("Entrada: números iniciales = " + java.util.Arrays.toString(initialNumbers) + ", n = " + n);

        List<Integer> result = fibonacciResource.fibonacci(initialNumbers, n);
        List<Integer> expected = List.of(0, 1, 1, 2, 3, 5, 8, 13, 21);

        System.out.println("Resultado: " + result);
        System.out.println("Esperado:  " + expected);
        System.out.println("Match: " + (result.equals(expected) ? "SÍ" : "NO"));
        System.out.println("-------------------------------------------\n");

        assertEquals(expected, result, "La secuencia de Fibonacci [0,1] con n=9 debería ser correcta");
        assertEquals(9, result.size(), "La secuencia debería tener exactamente 9 elementos");
    }

    @Test
    @DisplayName("Fibonacci con [2,3] y n=5 debe generar secuencia correcta")
    public void testFibonacciExample2() {
        // Ejemplo del documento: fibonacci([2, 3], 5) debería retornar [2, 3, 5, 8, 13]
        int[] initialNumbers = {2, 3};
        int n = 5;

        System.out.println("PROBANDO FIBONACCI EJEMPLO 2:");
        System.out.println("Entrada: números iniciales = " + java.util.Arrays.toString(initialNumbers) + ", n = " + n);

        List<Integer> result = fibonacciResource.fibonacci(initialNumbers, n);
        List<Integer> expected = List.of(2, 3, 5, 8, 13);

        System.out.println("Resultado: " + result);
        System.out.println("Esperado:  " + expected);
        System.out.println("Match: " + (result.equals(expected) ? "SÍ" : "NO"));
        System.out.println("-------------------------------------------\n");

        assertEquals(expected, result, "La secuencia de Fibonacci [2,3] con n=5 debería ser correcta");
        assertEquals(5, result.size(), "La secuencia debería tener exactamente 5 elementos");
    }

    @Test
    @DisplayName("Fibonacci con n=0 debe retornar lista vacía")
    public void testFibonacciWithZeroElements() {
        int[] initialNumbers = {0, 1};
        int n = 0;

        System.out.println("PROBANDO FIBONACCI CON n=0:");
        System.out.println("Entrada: números iniciales = " + java.util.Arrays.toString(initialNumbers) + ", n = " + n);

        List<Integer> result = fibonacciResource.fibonacci(initialNumbers, n);

        System.out.println("Resultado: " + result + " (tamaño: " + result.size() + ")");
        System.out.println("Esperado: [] (lista vacía)");
        System.out.println("-------------------------------------------\n");

        assertTrue(result.isEmpty(), "Fibonacci con n=0 debería retornar lista vacía");
    }

    // Método auxiliar para imprimir tableros de Sudoku de forma visual
    private void printSudokuBoard(String[][] board) {
        System.out.println("┌─────────┬─────────┬─────────┐");
        for (int i = 0; i < 9; i++) {
            if (i == 3 || i == 6) {
                System.out.println("├─────────┼─────────┼─────────┤");
            }
            System.out.print("│ ");
            for (int j = 0; j < 9; j++) {
                String cell = board[i][j];
                if (cell == null || cell.isEmpty()) {
                    System.out.print("·");
                } else {
                    System.out.print(cell);
                }
                if (j == 2 || j == 5) {
                    System.out.print(" │ ");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println("│");
        }
        System.out.println("└─────────┴─────────┴─────────┘");
    }

    @Test
    @DisplayName("Fibonacci con n=1 debe retornar solo el primer número")
    public void testFibonacciWithOneElement() {
        int[] initialNumbers = {5, 7};
        int n = 1;

        List<Integer> result = fibonacciResource.fibonacci(initialNumbers, n);

        assertEquals(1, result.size(), "Fibonacci con n=1 debería retornar 1 elemento");
        assertEquals(5, result.get(0), "El primer elemento debería ser el primer número inicial");
    }

    @Test
    @DisplayName("Fibonacci con n=2 debe retornar los dos números iniciales")
    public void testFibonacciWithTwoElements() {
        int[] initialNumbers = {10, 20};
        int n = 2;

        List<Integer> result = fibonacciResource.fibonacci(initialNumbers, n);

        assertEquals(2, result.size(), "Fibonacci con n=2 debería retornar 2 elementos");
        assertEquals(10, result.get(0), "El primer elemento debería ser el primer número inicial");
        assertEquals(20, result.get(1), "El segundo elemento debería ser el segundo número inicial");
    }

    @Test
    @DisplayName("Fibonacci con números iniciales nulos debe lanzar excepción")
    public void testFibonacciWithNullInitialNumbers() {
        assertThrows(IllegalArgumentException.class, () -> {
            fibonacciResource.fibonacci(null, 5);
        }, "Fibonacci con números iniciales nulos debería lanzar IllegalArgumentException");
    }

    @Test
    @DisplayName("Fibonacci con n negativo debe lanzar excepción")
    public void testFibonacciWithNegativeN() {
        int[] initialNumbers = {0, 1};

        assertThrows(IllegalArgumentException.class, () -> {
            fibonacciResource.fibonacci(initialNumbers, -1);
        }, "Fibonacci con n negativo debería lanzar IllegalArgumentException");
    }

    @Test
    @DisplayName("Fibonacci con arreglo inicial insuficiente debe lanzar excepción")
    public void testFibonacciWithInsufficientInitialNumbers() {
        int[] initialNumbers = {1}; // Solo un número

        assertThrows(IllegalArgumentException.class, () -> {
            fibonacciResource.fibonacci(initialNumbers, 5);
        }, "Fibonacci con menos de 2 números iniciales debería lanzar IllegalArgumentException");
    }
}