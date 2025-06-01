
package Puzzle;

public class Nodo {
    public final Nodo padre;            // Nodo padre (paso anterior)
    public final int[][] matriz;        // Estado actual del tablero
    public final int x, y;              // Posición del espacio vacío (0)
    public final int nivel;             // Nivel del nodo (profundidad en el árbol de búsqueda)

    // Constructor
    public Nodo(int[][] matrizAnterior, int xAnterior, int yAnterior, int nuevoX, int nuevoY, int nivel, Nodo padre) {
        this.padre = padre;
        this.nivel = nivel;
        this.matriz = clonarYMover(matrizAnterior, xAnterior, yAnterior, nuevoX, nuevoY);
        this.x = nuevoX;
        this.y = nuevoY;
    }

    // Clonar la matriz anterior y realizar el movimiento (intercambio)
    private int[][] clonarYMover(int[][] original, int xAnterior, int yAnterior, int nuevoX, int nuevoY) {
        int[][] nuevaMatriz = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            nuevaMatriz[i] = original[i].clone();  // Clon profundo por filas
        }
        int temp = nuevaMatriz[xAnterior][yAnterior];
        nuevaMatriz[xAnterior][yAnterior] = nuevaMatriz[nuevoX][nuevoY];
        nuevaMatriz[nuevoX][nuevoY] = temp;
        return nuevaMatriz;
    }
}
