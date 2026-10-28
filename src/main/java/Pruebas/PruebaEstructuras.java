package pruebas;

import estructuras.ColaEnlazada;
import estructuras.ListaEnlazada;
import estructuras.PilaEnlazada;

/**
 * Pruebas de verificación de las estructuras propias.
 *
 * Este archivo es una herramienta de desarrollo: comprueba el comportamiento de
 * la lista, la cola y la pila sin depender de java.util ni de arreglos de
 * datos. Se ejecuta con: java -cp bin pruebas.PruebaEstructuras
 */
public class PruebaEstructuras {

    private static int ok = 0;
    private static int fallas = 0;

    public static void main(String[] args) {
        probarLista();
        probarCola();
        probarPila();

        System.out.println();
        System.out.println("=== RESULTADO: " + ok + " pruebas correctas, " + fallas + " fallas ===");
        if (fallas > 0) {
            System.exit(1);
        }
    }

    private static void probarLista() {
        System.out.println("=== LISTA ENLAZADA ===");
        ListaEnlazada<String> lista = new ListaEnlazada<>();

        verificar("inicia vacía", lista.estaVacia() && lista.tamanio() == 0);

        lista.agregar("sedan");
        lista.agregar("pickup");
        lista.agregar("maquinaria");
        verificar("tamaño 3 tras agregar", lista.tamanio() == 3);
        verificar("get(0) = sedan", "sedan".equals(lista.get(0)));
        verificar("get(2) = maquinaria", "maquinaria".equals(lista.get(2)));
        verificar("get fuera de rango devuelve null", lista.get(5) == null && lista.get(-1) == null);
        verificar("indiceDe(pickup) = 1", lista.indiceDe("pickup") == 1);
        verificar("contiene(maquinaria)", lista.contiene("maquinaria"));
        verificar("no contiene inexistente", !lista.contiene("xxx"));

        lista.agregarAlInicio("superauto");
        verificar("agregarAlInicio actualiza la cabeza",
                "superauto".equals(lista.get(0)) && lista.tamanio() == 4);

        lista.agregar("sedan");
        verificar("contar(sedan) = 2", lista.contar("sedan") == 2);
        verificar("eliminar(pickup)", lista.eliminar("pickup") && lista.tamanio() == 4);
        verificar("eliminar inexistente devuelve false", !lista.eliminar("zzz"));
        verificar("eliminarEn(0) = superauto",
                "superauto".equals(lista.eliminarEn(0)) && lista.tamanio() == 3);
        verificar("toString conserva el orden",
                "[sedan, maquinaria, sedan]".equals(lista.toString()));

        ListaEnlazada<String> copia = lista.copiar();
        copia.agregar("otro");
        verificar("copiar() es independiente de la original",
                lista.tamanio() == 3 && copia.tamanio() == 4);

        lista.limpiar();
        verificar("limpiar deja la lista vacía", lista.estaVacia() && lista.tamanio() == 0);
    }

    private static void probarCola() {
        System.out.println();
        System.out.println("=== COLA ENLAZADA (FIFO) ===");
        ColaEnlazada<String> cola = new ColaEnlazada<>();

        verificar("inicia vacía", cola.estaVacia());

        cola.encolar("Alice");
        cola.encolar("Benjamin");
        cola.encolar("Chloe");
        verificar("frente = Alice", "Alice".equals(cola.frente()));
        verificar("desencolar devuelve Alice", "Alice".equals(cola.desencolar()));
        verificar("nuevo frente = Benjamin", "Benjamin".equals(cola.frente()));
        verificar("tamaño 2", cola.tamanio() == 2);
        verificar("contar(Benjamin) = 1", cola.contar("Benjamin") == 1);
        verificar("retirar(Benjamin) respeta el orden", cola.retirar("Benjamin"));
        verificar("queda Chloe al frente",
                cola.tamanio() == 1 && "Chloe".equals(cola.frente()));
        verificar("al sacar el último se limpia el fin",
                "Chloe".equals(cola.desencolar()) && cola.estaVacia());
        verificar("desencolar en cola vacía devuelve null", cola.desencolar() == null);

        cola.encolar("A");
        cola.encolar("B");
        cola.encolar("C");
        verificar("reutilizable después de vaciarse",
                "A".equals(cola.desencolar()) && cola.tamanio() == 2);
        System.out.println("  estado actual: " + cola);
    }

    private static void probarPila() {
        System.out.println();
        System.out.println("=== PILA ENLAZADA (LIFO) ===");
        PilaEnlazada<String> pila = new PilaEnlazada<>();

        verificar("inicia vacía", pila.estaVacia());

        pila.apilar("A");
        pila.apilar("B");
        pila.apilar("C");
        verificar("cima = C", "C".equals(pila.cima()));
        verificar("desapilar devuelve C", "C".equals(pila.desapilar()));
        verificar("nueva cima = B", "B".equals(pila.cima()));
        verificar("tamaño 2", pila.tamanio() == 2);
        verificar("comoLista va de la cima al fondo",
                "[B, A]".equals(pila.comoLista().toString()));
        verificar("desapilar devuelve B", "B".equals(pila.desapilar()));
        verificar("desapilar devuelve A", "A".equals(pila.desapilar()));
        verificar("pila vacía devuelve null", pila.desapilar() == null && pila.cima() == null);

        pila.apilar("X");
        verificar("reutilizable después de vaciarse",
                "X".equals(pila.cima()) && pila.tamanio() == 1);

        pila.limpiar();
        verificar("limpiar deja la pila vacía", pila.estaVacia());
    }

    private static void verificar(String nombre, boolean condicion) {
        if (condicion) {
            ok++;
            System.out.println("  OK    " + nombre);
        } else {
            fallas++;
            System.out.println("  FALLA " + nombre);
        }
    }
}
