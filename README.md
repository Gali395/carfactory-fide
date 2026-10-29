\# CarFactory-Fide — Estructuras de datos propias



Proyecto del curso \*\*SC-304 Estructuras de Datos\*\* (Universidad Fidélitas).



Este repositorio contiene las estructuras de datos implementadas desde cero, tal como exige el

enunciado: \*\*sin `java.util`, sin `ArrayList` y sin arreglos de Java\*\*. Todas crecen nodo a nodo.



\## Estructuras implementadas



| Estructura | Política | Uso en el juego | Complejidad |

|---|---|---|---|

| `ListaEnlazada` | Secuencia | Líneas de producción, órdenes del HUB, materiales de una receta | inserción al final O(1); búsqueda y borrado O(n) |

| `ColaEnlazada` | FIFO | Cinta transportadora, cola de pedidos | encolar y desencolar O(1) |

| `PilaEnlazada` | LIFO | Basurero de materiales, historial de acciones | apilar y desapilar O(1) |



Los nodos (`NodoLista`, `NodoCola`, `NodoPila`) son clases separadas porque cada estructura necesita

su propio enlace; todas las clases viven en archivos independientes.



\## Estructura del proyecto



```

src/

&#x20; estructuras/

&#x20;   NodoLista.java        Nodo de la lista

&#x20;   ListaEnlazada.java    Lista enlazada simple

&#x20;   NodoCola.java         Nodo de la cola

&#x20;   ColaEnlazada.java     Cola FIFO

&#x20;   NodoPila.java         Nodo de la pila

&#x20;   PilaEnlazada.java     Pila LIFO

&#x20; pruebas/

&#x20;   PruebaEstructuras.java  38 verificaciones ejecutables

```



\## Compilar y ejecutar



Requiere JDK 8 o superior.



```bash

cd proyecto\_carfactory

javac -encoding UTF-8 -d bin src/estructuras/\*.java src/pruebas/\*.java

java -cp bin pruebas.PruebaEstructuras

```



Salida esperada: `=== RESULTADO: 38 pruebas correctas, 0 fallas ===`



\## Por qué estas estructuras y no las de la biblioteca



\- \*\*Lista enlazada:\*\* las tres líneas de producción se recorren constantemente buscando una libre;

&#x20; una lista permite además agregar líneas sin recompilar y sin reservar un tamaño fijo.

\- \*\*Cola:\*\* la cinta transportadora entrega los materiales en el mismo orden en que aparecen, que es

&#x20; exactamente FIFO. Con referencias al frente y al fin, insertar y extraer es O(1).

\- \*\*Pila:\*\* en el basurero interesa el último material desechado, y en el historial de acciones el

&#x20; último movimiento hecho; ambas cosas son LIFO.



\## Decisiones de implementación para la defensa



\- \*\*Se limpia la referencia al fin en la cola:\*\* al desencolar el último nodo, `fin` se pone en

&#x20; `null`. Si no se hiciera, quedaría apuntando a un nodo que ya no está en la estructura.

\- \*\*Se limpia la referencia a la cola en la lista:\*\* al eliminar el último nodo, `cola` pasa a ser

&#x20; el nodo anterior (o `null` si la lista quedó vacía).

\- \*\*`copiar()` y `comoLista()` evitan exponer los nodos internos:\*\* devuelven una lista nueva, así

&#x20; quien recorre la estructura no puede romper los enlaces.

\- \*\*Comparaciones con `equals` y tolerancia a `null`:\*\* se usa `equals`, no `==`, y se contempla el

&#x20; caso de datos nulos para no lanzar `NullPointerException`.

\- \*\*Genéricos `<T>`:\*\* las tres estructuras sirven para cualquier tipo, de modo que la misma cola

&#x20; transporta `Material` en la cinta o `OrdenConstruccion` en el HUB.



\## Restricciones respetadas



\- Sin `import java.util` en ningún archivo (verificable con una búsqueda en el repositorio).

\- Sin declaraciones de arreglos de datos.

\- Una clase por archivo, cada una en su propio `.java`.



