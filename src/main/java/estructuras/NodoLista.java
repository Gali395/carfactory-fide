package estructuras;

/**
 * Nodo genérico de una lista enlazada simple.
 *
 * @param <T> tipo de dato almacenado
 */
public class NodoLista<T> {

    private T dato;
    private NodoLista<T> siguiente;

    public NodoLista(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoLista<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoLista<T> siguiente) {
        this.siguiente = siguiente;
    }
}

