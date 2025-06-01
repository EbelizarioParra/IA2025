
package Puzzle;

import java.util.*;

public class Puzzle {
    private static final int DIMENSION = 3;  // Tamaño del tablero (3x3)
    private static final int[] FILAS = {1, 0, -1, 0};  // Movimientos posibles en filas
    private static final int[] COLUMNAS = {0, -1, 0, 1};  // Movimientos posibles en columnas

    public void buscarSolucion(int[][] inicial, int[][] objetivo, int x, int y, String metodo) {
        Collection<Nodo> nodos = switch (metodo) {
            case "DFS" -> new Stack<>();
            case "BFS" -> new LinkedList<>();
            case "UCS" -> new PriorityQueue<>(Comparator.comparingInt(n -> n.nivel));
            default -> throw new IllegalArgumentException("Método no válido: " + metodo);
        };

        Set<String> visitados = new HashSet<>();
        Nodo raiz = new Nodo(inicial, x, y, x, y, 0, null);
        agregarNodo(nodos, raiz);
        visitados.add(Arrays.deepToString(inicial));

        while (!nodos.isEmpty()) {
            Nodo actual = obtenerNodo(nodos, metodo);
            if (calcularCosto(actual.matriz, objetivo) == 0) {
                imprimirCamino(actual);
                System.out.println("Solución encontrada en " + actual.nivel + " movimientos.");
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nuevoX = actual.x + FILAS[i];
                int nuevoY = actual.y + COLUMNAS[i];
                if (posicionValida(nuevoX, nuevoY)) {
                    Nodo hijo = new Nodo(actual.matriz, actual.x, actual.y, nuevoX, nuevoY, actual.nivel + 1, actual);
                    String estado = Arrays.deepToString(hijo.matriz);
                    if (visitados.add(estado)) {  // Solo si no se había visitado
                        agregarNodo(nodos, hijo);
                    }
                }
            }
        }
        System.out.println("No se encontró una solución.");
    }

    private void agregarNodo(Collection<Nodo> nodos, Nodo nodo) {
        if (nodos instanceof Stack<?> stack) {
            stack.push(nodo);
        } else if (nodos instanceof Queue<?> queue) {
            queue.add(nodo);
        } else if (nodos instanceof PriorityQueue<?> pq) {
            pq.add(nodo);
        }
    }

    private Nodo obtenerNodo(Collection<Nodo> nodos, String metodo) {
        if (nodos instanceof Stack<?> stack) {
            return stack.pop();
        } else if (nodos instanceof Queue<?> queue) {
            return ((Queue<Nodo>) queue).poll();
        } else if (nodos instanceof PriorityQueue<?> pq) {
            return pq.poll();
        }
        throw new IllegalStateException("Tipo de colección no válido para el método: " + metodo);
    }

    private boolean posicionValida(int x, int y) {
        return x >= 0 && x < DIMENSION && y >= 0 && y < DIMENSION;
    }

    private int calcularCosto(int[][] matriz, int[][] objetivo) {
        int costo = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (matriz[i][j] != 0 && matriz[i][j] != objetivo[i][j]) {
                    costo++;
                }
            }
        }
        return costo;
    }

    private void imprimirCamino(Nodo nodo) {
        if (nodo == null) return;
        imprimirCamino(nodo.padre);
        imprimirMatriz(nodo.matriz);
        System.out.println();
    }

    private void imprimirMatriz(int[][] matriz) {
        for (int[] fila : matriz) {
            for (int valor : fila) {
                System.out.print(valor + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] inicial = {
            {1, 8, 2},
            {0, 4, 3},
            {7, 6, 5}
        };
        int[][] objetivo = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 0}
        };
        int x = 1, y = 0;

        Puzzle puzzle = new Puzzle();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione el algoritmo de búsqueda:");
        System.out.println("1. DFS");
        System.out.println("2. BFS");
        System.out.println("3. UCS");
        System.out.print("Opción: ");
        int opcion = scanner.nextInt();

        String metodo = switch (opcion) {
            case 1 -> "DFS";
            case 2 -> "BFS";
            case 3 -> "UCS";
            default -> {
                System.out.println("Opción no válida");
                yield null;
            }
        };

        if (metodo != null) {
            puzzle.buscarSolucion(inicial, objetivo, x, y, metodo);
        }
    }
}
