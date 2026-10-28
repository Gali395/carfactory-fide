package estructuras;

/**
 * Nodo genérico de una cola enlazada.
 *
 * @param <T> tipo de dato almacenado
 */
public class NodoCola<T> {

    private final T dato;
    private NodoCola<T> siguiente;

    public NodoCola(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public T getDato() {
        return dato;
    }

    public NodoCola<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoCola<T> siguiente) {
        this.siguiente = siguiente;
    }
}

