package main.java.com.ciencias.edd;
import java.util.Iterator;

/**
 * <p>
 * Clase para modelar árboles binarios de búsqueda genéricos.</p>
 *
 * <p>
 * Un árbol instancia de esta clase siempre cumple que:</p>
 * <ul>
 * <li>Cualquier elemento en el árbol es mayor o igual que todos sus
 * descendientes por la izquierda.</li>
 * <li>Cualquier elemento en el árbol es menor o igual que todos sus
 * descendientes por la derecha.</li>
 * </ul>
 *
 * @param <T>
 */
public class ArbolBinarioBusqueda<T extends Comparable<T>> extends ArbolBinario<T> {

    /* Clase privada para iteradores de árboles binarios ordenados. */
    private class Iterador implements Iterator<T> {

        /* Pila para recorrer los nodos por profundidad (DFS). */
        private Pila<Nodo> pila;

        /* Construye un iterador con el nodo recibido. */
        public Iterador() {
            pila = new Pila<>();
            if (raiz != null) {
                pila.push(raiz);
                Nodo izquierdoNodo = raiz.izquierdo;
                while (izquierdoNodo != null) {
                    pila.push(izquierdoNodo);
                    izquierdoNodo = izquierdoNodo.izquierdo;
                }
            }
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override
        public boolean hasNext() {
            return !pila.esVacia();
       	}

        /* Regresa el siguiente elemento en orden DFS in-order. */
        @Override
        public T next() {
            Nodo nodo = pila.pop();
            T elemento = nodo.elemento;
            if (nodo.derecho != null) {
                pila.push(nodo.derecho);
                Nodo izquierdoN = nodo.derecho.izquierdo; //Agregamos toda la rama izquierda
                while (izquierdoN != null) {
                    pila.push(izquierdoN);
                    izquierdoN = izquierdoN.izquierdo;
                }
            }
            return elemento;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Constructor que no recibe parámeteros. {@link ArbolBinario}.
     */
    public ArbolBinarioBusqueda() {

    }

    /**
     * Construye un árbol binario ordenado a partir de una colección. El árbol
     * binario ordenado tiene los mismos elementos que la colección recibida.
     *
     * @param coleccion la colección a partir de la cual creamos el árbol
     * binario ordenado.
     */
    public ArbolBinarioBusqueda(Coleccionable<T> coleccion) {
        super(coleccion);
    }

    /**
     * Método recursivo auxiliar que agrega un elemento contenido en el nodo nuevo.
     * Comienza las comparaciones desde el nodo n.
     *
     **/
    protected void agregaNodo(Nodo n, Nodo nuevo) {
        if (nuevo.elemento == null) 
            throw new IllegalArgumentException("No puedes agregar nulls al árbol binario");

        //*Si el árbol es vacío.
        if (esVacia()) {
            this.raiz = n = nuevo;
            this.tamanio++;
        //*Si el nodo actual es mayor que el que queremos agregar
        } else if (nuevo.elemento.compareTo(n.elemento) < 0) {
            if (n.izquierdo == null) {
                n.izquierdo = nuevo;
                nuevo.padre = n;
                this.tamanio++;
            } else {
                agregaNodo(n.izquierdo, nuevo);
            }
        //*Si el nodo actual es menor que el que queremos agregar.
        } else {
            if (n.derecho == null) {
                n.derecho = nuevo;
                nuevo.padre = n;
                this.tamanio++;
            } else {
                agregaNodo(n.derecho, nuevo);
            }
        }
    }

    /**
     * Agrega un nuevo elemento al árbol. El árbol conserva su orden in-order.
     *
     * @param elemento el elemento a agregar.
     */
    @Override
    public void agregar(T elemento) {
        Nodo nuevo = nuevoNodo(elemento);
        agregaNodo(raiz, nuevo);
    }

    /**
     * Método auxiliar que elimina el nodo n. Notemos que en este punto
     * ya tenemos una referencia al nodo que queremos eliminar.
     **/
    protected Nodo eliminaNodo(Nodo n) {
        if (n != null) {
            //*Caso 1: Tiene dos hijos distintos de null.
            if (n.izquierdo != null && n.derecho != null) {
                Nodo eliminar = intercambiaEliminable(n);
                return eliminaNodo(eliminar);
            //*Caso 2: Tiene el hijo izquierdo != null y el derecho == null.
            } else if (n.izquierdo != null && n.derecho == null) {
                //Si es la raiz.
                if (n.padre == null) {
                    this.raiz = n.izquierdo;
                    n.izquierdo.padre = null;
                //Si no es la raiz.
                } else {
                    //Si el Nodo a eliminar es hijo izquierdo de su padre.
                    if (n.padre.izquierdo == n)
                        n.padre.izquierdo = n.izquierdo;
                    //Si el Nodo a elimianr es hijo derecho de su padre.
                    else 
                        n.padre.derecho = n.izquierdo;
                    //Conectamos al hijo != null con el Padre del Nodo a eliminar.
                    n.izquierdo.padre = n.padre;
                }
                this.tamanio--;
            //*Caso 3: Tiene el hijo derecho != null y el izquierdo == null.
            } else if (n.izquierdo == null && n.derecho != null) {
                //Si es la raiz.
                if (n.padre == null) {
                    this.raiz = n.derecho;
                    n.derecho.padre = null;
                //Si no es la raiz.
                } else {
                    //Si el Nodo a eliminar es hijo izquierdo de su padre.
                    if (n.padre.izquierdo == n) 
                        n.padre.izquierdo = n.derecho;
                    //Si el Nodo a eliminar es hijo derecho de su padre.
                    else 
                        n.padre.derecho = n.derecho;
                    //Conectamos al hijo != null con el Padre del Nodo a eliminar.
                    n.derecho.padre = n.padre;    
                }
                this.tamanio--;
            //*Caso 4: Es una hoja.
            } else {
                //Si es la raiz.
                if (n.padre == null) {
                    this.raiz = null;
                //Si no es la raiz.
                } else {
                    //Si la Hoja (Nodo a eliminar) es hijo izquierdo.
                    if (n.padre.izquierdo == n)
                        n.padre.izquierdo = null;
                    //Si la Hoja (Nodo a eliminar) es hijo derecho.
                    else 
                        n.padre.derecho = null;
                }
                this.tamanio--;
            }
        }
        //*Retornamos el Nodo a eliminar.
        return n;
    }
    
    /**
     * Elimina un elemento. Si el elemento no está en el árbol, no hace nada; si
     * está varias veces, elimina el primero que encuentre (in-order). El árbol
     * conserva su orden in-order.
     *
     * @param elemento el elemento a eliminar.
     */
    @Override
    public void eliminar(T elemento) {
        Nodo n = buscaNodo(raiz, elemento);
        eliminaNodo(n);
    }

    /**
     * Método que encuentra el elemento máximo en el subárbol izquierdo
     **/
    private Nodo maximoEnSubarbolIzquierdo(Nodo n) {
        if (n.derecho == null) 
            return n;
        return maximoEnSubarbolIzquierdo(n.derecho);
    }

    /**
     * Método que intercambia los elementos de un Nodo que tenga dos hijos
     * distintos de null y de un Nodo que sea el máximo del subárbol izquierdo
     * que a la vez es una hoja (dos hijos igual a null).
     * @param nodo Nodo cuyos dos hijos son distintos de null.
     * @return Nodo Este Nodo es el máximo cuyo elemento es el a eliminar.
     */
    private Nodo intercambiaEliminable(Nodo nodo) {
        T elementoAEliminar = nodo.elemento;
        Nodo maximo = maximoEnSubarbolIzquierdo(nodo.izquierdo);
        //*Intercambiamos los elementos de los Nodos.
        nodo.elemento = maximo.elemento;
        maximo.elemento = elementoAEliminar;
        //*Retornamos el Máximo que ahora contiene el elemento a eliminar.
        return maximo;        
    }

    /**
     * Nos dice si un elemento está contenido en el arbol.
     *
     * @param elemento el elemento que queremos verificar si está contenido en
     * la arbol.
     * @return <code>true</code> si el elemento está contenido en el arbol,
     * <code>false</code> en otro caso.
     */
    @Override
    public boolean contiene(T elemento) {
        return buscaNodo(raiz, elemento) != null;
    }

    /**
     * Método que busca un a elemento en el árbol desde el nodo n
     **/
    protected Nodo buscaNodo(Nodo n, T elemento) {
        //*Clausula de Escape.
        if (n == null)
            return null;
        
        //*Caso en el que encontramos el nodo.
        if (n.elemento.equals(elemento))
            return n;
        
        return (n.elemento.compareTo(elemento)) > 0 ?
        buscaNodo(n.izquierdo, elemento): buscaNodo(n.derecho, elemento); 
    }

    /**
     * Rota el árbol a la derecha sobre el nodo recibido. Si el nodo no tiene
     * hijo izquierdo, el método no hace nada.
     *
     * @param nodo el nodo sobre el que vamos a rotar.
     */
    protected void rotacionDerecha(Nodo nodo) {
        //*Para girar a la derecha debe existir su hijo izquierdo.
        if (nodo != null && nodo.izquierdo != null) {
            Nodo izquierdoNodo = nodo.izquierdo;

            //*Primero hacemos que el padre del izquierdo sea ahora el padre del nodo.
            //*Y hacemos que el hijo del padre del nodo sea el izquiero.(Conectamos)
            izquierdoNodo.padre = nodo.padre;
            if (nodo.padre == null) {
                this.raiz = izquierdoNodo;
            } else {
                if (nodo.padre.izquierdo == nodo)
                    nodo.padre.izquierdo = izquierdoNodo;
                else 
                    nodo.padre.derecho = izquierdoNodo;
            }

            //*Hacemos el giro a la derecha.
            nodo.izquierdo = izquierdoNodo.derecho;
            if (izquierdoNodo.derecho != null) {
                izquierdoNodo.derecho.padre = nodo;
            }
            nodo.padre = izquierdoNodo;
            izquierdoNodo.derecho = nodo;
        }
    }

    /**
     * Rota el árbol a la izquierda sobre el nodo recibido. Si el nodo no tiene
     * hijo derecho, el método no hace nada.
     *
     * @param nodo el nodo sobre el que vamos a rotar.
     */
    protected void rotacionIzquierda(Nodo nodo) {
        //Debe existir el nodo derecho para hacer el giro a la izquierda.
        if (nodo != null && nodo.derecho != null) {
            Nodo derechoNodo = nodo.derecho;

            //*Primero hacemos que el padre del derecho sea ahora el padre del nodo.
            //*Y hacemos que el hijo del padre del nodo sea el derecho.(Conectamos)
            derechoNodo.padre = nodo.padre;
            if (nodo.padre == null) {
                this.raiz = derechoNodo;
            } else {
                if (nodo.padre.izquierdo == nodo)
                    nodo.padre.izquierdo = derechoNodo;
                else 
                    nodo.padre.derecho = derechoNodo;
            }

            //*Ahora procederemos al giro.
            nodo.derecho = derechoNodo.izquierdo;
            if (derechoNodo.izquierdo != null) {
                derechoNodo.izquierdo.padre = nodo;
            }
            nodo.padre = derechoNodo;
            derechoNodo.izquierdo = nodo;

        }
    }

    /**
     * Regresa un iterador para iterar el árbol. El árbol se itera en orden.
     *
     * @return un iterador para iterar el árbol.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterador();
    }

    /**Métodos para las pruebas */
    public void pruebaRotacionIzquierda(T elemento) {
        Nodo nodo = this.buscaNodo(raiz, elemento);
        this.rotacionIzquierda(nodo);
    }

    public void pruebaRotacionDerecha(T elemento) {
        Nodo nodo = this.buscaNodo(raiz, elemento);
        this.rotacionDerecha(nodo);
    }


}