package com.beesion.ms.resource;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.HashSet;
import java.util.Set;


@Path("/sudoku")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SudokuValidatorResource {

    /**
     * Endpoint para validar un tablero de Sudoku
     * @param board Tablero de Sudoku 9x9 representado como array bidimensional
     * @return Response con el resultado de la validación
     */
    @POST
    @Path("/validate")
    public Response validateSudoku(String[][] board) {
        try {
            boolean isValid = isSudokuValid(board);
            return Response.ok()
                    .entity(new ValidationResult(isValid))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse("Error validating Sudoku: " + e.getMessage()))
                    .build();
        }
    }

    /**
     * Valida si un tablero de Sudoku es válido
     * Aplica principio Open-Close: cerrado para modificación, abierto para extensión
     *
     * @param board Tablero 9x9 de Sudoku
     * @return true si el tablero es válido, false en caso contrario
     */
    public boolean isSudokuValid(String[][] board) {
        if (board == null || board.length != 9) {
            return false;
        }

        // Validar que cada fila tenga exactamente 9 elementos
        for (String[] row : board) {
            if (row == null || row.length != 9) {
                return false;
            }
        }

        // Validar filas, columnas y subcuadros
        return validateRows(board) && validateColumns(board) && validateSubBoxes(board);
    }

    /**
     * Valida que cada fila contenga dígitos 1-9 sin repetición
     * Aplica principio Single Responsibility
     */
    private boolean validateRows(String[][] board) {
        for (int i = 0; i < 9; i++) {
            if (!isValidGroup(getRow(board, i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Valida que cada columna contenga dígitos 1-9 sin repetición
     * Aplica principio Single Responsibility
     */
    private boolean validateColumns(String[][] board) {
        for (int j = 0; j < 9; j++) {
            if (!isValidGroup(getColumn(board, j))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Valida que cada subcuadro 3x3 contenga dígitos 1-9 sin repetición
     * Aplica principio Single Responsibility
     */
    private boolean validateSubBoxes(String[][] board) {
        for (int boxRow = 0; boxRow < 3; boxRow++) {
            for (int boxCol = 0; boxCol < 3; boxCol++) {
                if (!isValidGroup(getSubBox(board, boxRow, boxCol))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Obtiene una fila específica del tablero
     */
    private String[] getRow(String[][] board, int row) {
        return board[row];
    }

    /**
     * Obtiene una columna específica del tablero
     */
    private String[] getColumn(String[][] board, int col) {
        String[] column = new String[9];
        for (int i = 0; i < 9; i++) {
            column[i] = board[i][col];
        }
        return column;
    }

    /**
     * Obtiene un subcuadro 3x3 específico del tablero
     */
    private String[] getSubBox(String[][] board, int boxRow, int boxCol) {
        String[] subBox = new String[9];
        int index = 0;

        int startRow = boxRow * 3;
        int startCol = boxCol * 3;

        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                subBox[index++] = board[i][j];
            }
        }
        return subBox;
    }

    /**
     * Valida que un grupo (fila, columna o subcuadro) sea válido
     * Solo valida celdas completadas, ignora celdas vacías
     */
    private boolean isValidGroup(String[] group) {
        Set<String> seen = new HashSet<>();

        for (String cell : group) {
            // Ignorar celdas vacías (representadas como cadenas vacías o null)
            if (cell == null || cell.trim().isEmpty()) {
                continue;
            }

            // Verificar que sea un dígito válido (1-9)
            if (!isValidDigit(cell)) {
                return false;
            }

            // Verificar que no esté repetido
            if (seen.contains(cell)) {
                return false;
            }

            seen.add(cell);
        }

        return true;
    }

    /**
     * Verifica si una celda contiene un dígito válido (1-9)
     */
    private boolean isValidDigit(String cell) {
        if (cell == null || cell.length() != 1) {
            return false;
        }

        char c = cell.charAt(0);
        return c >= '1' && c <= '9';
    }

    // Clases para las respuestas JSON
    public static class ValidationResult {
        public boolean valid;

        public ValidationResult(boolean valid) {
            this.valid = valid;
        }
    }

    public static class ErrorResponse {
        public String error;

        public ErrorResponse(String error) {
            this.error = error;
        }
    }
}