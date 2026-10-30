package estructuras;

/**
 * Lista enlazada simple de implementación propia.
 *
 * No utiliza java.util, ArrayList ni arreglos: el crecimiento es nodo a nodo y
 * no existe un tamaño fijo.
 *
 * @param <T> tipo de dato almacenado
 */
public class ListaEnlazada<T> {

    private NodoLista<T> cabeza;
    private NodoLista<T> cola;
    private int tamanio;

    public ListaEnlazada() {
        this.cabeza = null;
        this.cola = null;
        this.tamanio = 0;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public int tamanio() {
        return tamanio;
    }

    /** Agrega un elemento al final de la lista. */
    public void agregar(T dato) {
        NodoLista<T> nuevo = new NodoLista<>(dato);
        if (estaVacia()) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.setSiguiente(nuevo);
            cola = nuevo;
        }
        tamanio++;
    }

    /** Agrega un elemento al inicio de la lista. */
    public void agregarAlInicio(T dato) {
        NodoLista<T> nuevo = new NodoLista<>(dato);
        nuevo.setSiguiente(cabeza);
        cabeza = nuevo;
        if (cola == null) {
            cola = nuevo;
        }
        tamanio++;
    }

    /** Devuelve el elemento en la posición indicada, o null si está fuera de rango. */
    public T get(int indice) {
        if (indice < 0 || indice >= tamanio) {
            return null;
        }
        NodoLista<T> actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }
        return actual.getDato();
    }

    /** Índice de la primera ocurrencia del dato, o -1 si no está. */
    public int indiceDe(T dato) {
        NodoLista<T> actual = cabeza;
        int indice = 0;
        while (actual != null) {
            if (sonIguales(actual.getDato(), dato)) {
                return indice;
            }
            actual = actual.getSiguiente();
            indice++;
        }
        return -1;
    }

    public boolean contiene(T dato) {
        return indiceDe(dato) >= 0;
    }

    /** Cantidad de ocurrencias del dato en la lista. */
    public int contar(T dato) {
        int total = 0;
        NodoLista<T> actual = cabeza;
        while (actual != null) {
            if (sonIguales(actual.getDato(), dato)) {
                total++;
            }
            actual = actual.getSiguiente();
        }
        return total;
    }

    /**
     * Elimina la primera ocurrencia del dato.
     *
     * @return true si el elemento existía y fue eliminado
     */
    public boolean eliminar(T dato) {
        NodoLista<T> anterior = null;
        NodoLista<T> actual = cabeza;

        while (actual != null) {
            if (sonIguales(actual.getDato(), dato)) {
                if (anterior == null) {
                    cabeza = actual.getSiguiente();
                } else {
                    anterior.setSiguiente(actual.getSiguiente());
                }
                if (actual == cola) {
                    cola = anterior;
                }
                tamanio--;
                return true;
            }
            anterior = actual;
            actual = actual.getSiguiente();
        }
        return false;
    }

    /** Elimina y devuelve el elemento de la posición indicada, o null si es inválida. */
    public T eliminarEn(int indice) {
        if (indice < 0 || indice >= tamanio) {
            return null;
        }
        NodoLista<T> anterior = null;
        NodoLista<T> actual = cabeza;
        for (int i = 0; i < indice; i++) {
            anterior = actual;
            actual = actual.getSiguiente();
        }
        T dato = actual.getDato();
        if (anterior == null) {
            cabeza = actual.getSiguiente();
        } else {
            anterior.setSiguiente(actual.getSiguiente());
        }
        if (actual == cola) {
            cola = anterior;
        }
        tamanio--;
        return dato;
    }

    public void limpiar() {
        cabeza = null;
        cola = null;
        tamanio = 0;
    }

    /**
     * Copia los elementos de la lista en el mismo orden, dentro de otra lista.
     * Se usa para recorrer sin exponer los nodos internos.
     */
    public ListaEnlazada<T> copiar() {
        ListaEnlazada<T> copia = new ListaEnlazada<>();
        NodoLista<T> actual = cabeza;
        while (actual != null) {
            copia.agregar(actual.getDato());
            actual = actual.getSiguiente();
        }
        return copia;
    }

    /** Comparación tolerante a null, sin depender de objetos no nulos. */
    private boolean sonIguales(T a, T b) {
        if (a == null) {
            return b == null;
        }
        return a.equals(b);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        NodoLista<T> actual = cabeza;
        while (actual != null) {
            sb.append(actual.getDato());
            if (actual.getSiguiente() != null) {
                sb.append(", ");
            }
            actual = actual.getSiguiente();
        }
        return sb.append("]").toString();
    }
}
