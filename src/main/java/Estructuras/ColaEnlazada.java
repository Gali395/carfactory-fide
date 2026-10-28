package estructuras;

/**
 * Cola enlazada de implementación propia, con política FIFO
 * (first in, first out): el primero que entra es el primero que sale.
 *
 * Se usa para la cinta transportadora y para el HUB de pedidos.
 * Inserción y extracción son O(1) porque se mantienen referencias al frente y al fin.
 *
 * @param <T> tipo de dato almacenado
 */
public class ColaEnlazada<T> {

    private NodoCola<T> frente;
    private NodoCola<T> fin;
    private int tamanio;

    public ColaEnlazada() {
        this.frente = null;
        this.fin = null;
        this.tamanio = 0;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public int tamanio() {
        return tamanio;
    }

    /** Inserta un elemento al final de la cola. */
    public void encolar(T dato) {
        NodoCola<T> nuevo = new NodoCola<>(dato);
        if (estaVacia()) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
        tamanio++;
    }

    /**
     * Retira y devuelve el elemento del frente.
     *
     * @return el elemento del frente, o null si la cola está vacía
     */
    public T desencolar() {
        if (estaVacia()) {
            return null;
        }
        T dato = frente.getDato();
        frente = frente.getSiguiente();
        if (frente == null) {
            fin = null;
        }
        tamanio--;
        return dato;
    }

    /** Devuelve el elemento del frente sin retirarlo, o null si está vacía. */
    public T frente() {
        return estaVacia() ? null : frente.getDato();
    }

    /** Copia los elementos en su orden de salida, sin modificar la cola. */
    public ListaEnlazada<T> comoLista() {
        ListaEnlazada<T> copia = new ListaEnlazada<>();
        NodoCola<T> actual = frente;
        while (actual != null) {
            copia.agregar(actual.getDato());
            actual = actual.getSiguiente();
        }
        return copia;
    }

    /**
     * Retira la primera ocurrencia del dato, conservando el orden del resto.
     * Usa una cola auxiliar para no alterar la secuencia FIFO.
     *
     * @return true si el elemento existía y fue retirado
     */
    public boolean retirar(T dato) {
        ColaEnlazada<T> auxiliar = new ColaEnlazada<>();
        boolean encontrado = false;

        while (!estaVacia()) {
            T actual = desencolar();
            if (!encontrado && sonIguales(actual, dato)) {
                encontrado = true;
            } else {
                auxiliar.encolar(actual);
            }
        }

        while (!auxiliar.estaVacia()) {
            encolar(auxiliar.desencolar());
        }

        return encontrado;
    }

    /** Cantidad de ocurrencias del dato, sin modificar la cola. */
    public int contar(T dato) {
        int total = 0;
        NodoCola<T> actual = frente;
        while (actual != null) {
            if (sonIguales(actual.getDato(), dato)) {
                total++;
            }
            actual = actual.getSiguiente();
        }
        return total;
    }

    public void limpiar() {
        frente = null;
        fin = null;
        tamanio = 0;
    }

    private boolean sonIguales(T a, T b) {
        if (a == null) {
            return b == null;
        }
        return a.equals(b);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("frente [");
        NodoCola<T> actual = frente;
        while (actual != null) {
            sb.append(actual.getDato());
            if (actual.getSiguiente() != null) {
                sb.append(", ");
            }
            actual = actual.getSiguiente();
        }
        return sb.append("] fin").toString();
    }
}

