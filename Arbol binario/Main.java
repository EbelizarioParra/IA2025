public class Main {
    public static void main(String[] args) {
        Arbol arbol = new Arbol();
        arbol.raiz = new Nodo("Juan");
        arbol.raiz.izquierda = new Nodo("Ana");
        arbol.raiz.derecha = new Nodo("Pedro");

        Nodo nodoEncontrado = arbol.buscarNodo("Ana");
        if (nodoEncontrado != null) {
            System.out.println("Nodo encontrado: " + nodoEncontrado.nombre);
        } else {
            System.out.println("Nodo no encontrado.");
        }
    }
}

class Arbol {
    Nodo raiz;

    public Arbol() {
        this.raiz = null;
    }

    public boolean vacio() {
        return raiz == null;
    }

    public Nodo buscarNodo(String nombre) {
        return buscarNodoRecursivo(raiz, nombre);
    }

    private Nodo buscarNodoRecursivo(Nodo nodo, String nombre) {
        if (nodo == null || nodo.nombre.equals(nombre)) {
            return nodo;
        }

        if (nombre.compareTo(nodo.nombre) < 0) {
            return buscarNodoRecursivo(nodo.izquierda, nombre);
        } else {
            return buscarNodoRecursivo(nodo.derecha, nombre);
        }
    }
}

class Nodo {
    String nombre;
    Nodo izquierda, derecha;

    public Nodo(String nombre) {
        this.nombre = nombre;
        this.izquierda = null;
        this.derecha = null;
    }
}