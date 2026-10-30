package estructuras;

/**
 * Nodo genérico de una pila enlazada.
 *
 * @param <T> tipo de dato almacenado
 */
public class NodoPila<T> {

    private final T dato;
    private NodoPila<T> siguiente;

    public NodoPila(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public T getDato() {
        return dato;
    }

    public NodoPila<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoPila<T> siguiente) {
        this.siguiente = siguiente;
    }
}
