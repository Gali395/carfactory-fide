package estructuras;

/**
 * Pila enlazada de implementación propia, con política LIFO
 * (last in, first out): el último que entra es el primero que sale.
 *
 * Se usa para el basurero de materiales y para el historial de acciones del jugador.
 * Todas las operaciones son O(1).
 *
 * @param <T> tipo de dato almacenado
 */
public class PilaEnlazada<T> {

    private NodoPila<T> cima;
    private int tamanio;

    public PilaEnlazada() {
        this.cima = null;
        this.tamanio = 0;
    }

    public boolean estaVacia() {
        return cima == null;
    }

    public int tamanio() {
        return tamanio;
    }

    /** Inserta un elemento en la cima de la pila. */
    public void apilar(T dato) {
        NodoPila<T> nuevo = new NodoPila<>(dato);
        nuevo.setSiguiente(cima);
        cima = nuevo;
        tamanio++;
    }

    /**
     * Retira y devuelve el elemento de la cima.
     *
     * @return el elemento de la cima, o null si la pila está vacía
     */
    public T desapilar() {
        if (estaVacia()) {
            return null;
        }
        T dato = cima.getDato();
        cima = cima.getSiguiente();
        tamanio--;
        return dato;
    }

    /** Devuelve el elemento de la cima sin retirarlo, o null si está vacía. */
    public T cima() {
        return estaVacia() ? null : cima.getDato();
    }

    /** Copia los elementos de la cima hacia el fondo, sin modificar la pila. */
    public ListaEnlazada<T> comoLista() {
        ListaEnlazada<T> copia = new ListaEnlazada<>();
        NodoPila<T> actual = cima;
        while (actual != null) {
            copia.agregar(actual.getDato());
            actual = actual.getSiguiente();
        }
        return copia;
    }

    public void limpiar() {
        cima = null;
        tamanio = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("cima [");
        NodoPila<T> actual = cima;
        while (actual != null) {
            sb.append(actual.getDato());
            if (actual.getSiguiente() != null) {
                sb.append(", ");
            }
            actual = actual.getSiguiente();
        }
        return sb.append("] fondo").toString();
    }
}

