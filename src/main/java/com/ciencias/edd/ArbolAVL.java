package main.java.com.ciencias.edd;
/**
 * <p>
 * Clase para árboles AVL.</p>
 *
 * <p>
 * Un árbol AVL cumple que para cada uno de sus nodos, la diferencia entre la
 * áltura de sus subárboles izquierdo y derecho está entre -1 y 1.</p>
 *
 * @param <T>
 */
public class ArbolAVL<T extends Comparable<T>> extends ArbolBinarioBusqueda<T> {

    /**
     * Clase interna protegida para nodos de árboles AVL. La única diferencia
     * con los nodos de árbol binario, es que tienen una variable de clase para
     * la altura del nodo.
     */
    protected class NodoAVL extends ArbolBinario.Nodo {

        /**
         * La altura del nodo.
         */
        protected int altura;

        /**
         * Constructor único que recibe un elemento.
         *
         * @param elemento el elemento del nodo.
         */
        public NodoAVL(T elemento) {
            super(elemento);
        }
	
	/**
	 * Recomendamos usar este método auxiliar para que en el método público
	 * hagas el cast del objeto o, a NodoAVL y dejar el trabajo a este método.
  	 * Si no quieres usarlo, siéntete libre de eliminar esta firma.
	 */
        private boolean equals(NodoAVL v, NodoAVL v2) {
            //*Caso de Escape
            if (v == null && v2 == null)
                return true;
            
            if ((v != null && v2 == null) || (v == null && v2 != null))
                return false;
            
            if (!v.elemento.equals(v2.elemento))
                return false;
            
            if (v.altura != v2.altura)
                return false;

            //*Guardamos a los hijos
            NodoAVL izquierdoV = nodoAVL(v.izquierdo);
            NodoAVL izquierdoV2 = nodoAVL(v2.izquierdo);
            NodoAVL derechoV = nodoAVL(v.derecho);
            NodoAVL derechoV2 = nodoAVL(v2.derecho);
            
            //*Caso de Recursión:
            return equals(izquierdoV, izquierdoV2) && equals(derechoV, derechoV2);
        }

        /**
         * Compara el nodo con otro objeto. La comparación es
         * <em>recursiva</em>.
         *
         * @param o el objeto con el cual se comparará el nodo.
         * @return <code>true</code> si el objeto es instancia de la clase
         * {@link NodoAVL}, su elemento es igual al elemento de éste nodo, los
         * descendientes de ambos son recursivamente iguales, y las alturas son
         * iguales; <code>false</code> en otro caso.
         */
        @Override
        public boolean equals(Object o) {
            if (o == null) {
                return false;
            }
            if (getClass() != o.getClass()) {
                return false;
            }
            @SuppressWarnings("unchecked")
            NodoAVL nodo = (NodoAVL) o;
            
            return equals(this, nodo);
        }

        @Override
        public String toString() {
            String s = super.toString();
            return s += " alt=" + altura;
        }
    }

    public ArbolAVL() {

    }

    public ArbolAVL(Coleccionable<T> coleccion) {
        super(coleccion);
    }

    private void actualizaAltura(NodoAVL v) {
        if (v == null)
            return;
        v.altura = Math.max(getAltura(v.izquierdo), getAltura(v.derecho)) + 1;
    }

    //Método para calcular balances.
    private int balance(NodoAVL nodo) {
        int balance = getAltura(nodo.izquierdo) - getAltura(nodo.derecho);
        return balance;
    }

    private void rebalancea(NodoAVL nodo) {
        if (nodo == null)
            return;
        
        int balance = balance(nodo);

        //*Guardamos los nodos necesarios
        NodoAVL izquierdo = nodoAVL(nodo.izquierdo);
        NodoAVL derecho = nodoAVL(nodo.derecho);

        //*Caso 1: nodo tiene balance -2.
        if (balance == -2) {
            if (balance(derecho) == 1) {
                super.rotacionDerecha(derecho);
                actualizaAltura(derecho);
            }
            super.rotacionIzquierda(nodo);
        }
        //*Caso 2: nodo tiene balance 2.
        if (balance == 2) {
            if (balance(izquierdo) == -1) {
                super.rotacionIzquierda(izquierdo);
                actualizaAltura(izquierdo);
            }
            super.rotacionDerecha(nodo);
        }

        //*Actualizamos la altura del nodo y rebalanceamos al padre.
        actualizaAltura(nodo);
        rebalancea(nodoAVL(nodo.padre));
    }

    /**
     * Agrega un nuevo elemento al árbol. El método invoca al método {@link
     * ArbolBinarioBusqueda#agrega}, y después balancea el árbol girándolo como
     * sea necesario. La complejidad en tiempo del método es <i>O</i>(log
     * <i>n</i>) garantizado.
     *
     * @param elemento el elemento a agregar.
     */
    @Override
    public void agregar(T elemento) {
        NodoAVL nuevo = new NodoAVL(elemento);
        agregaNodo(this.raiz, nuevo);
        rebalancea(nuevo);
    }

    /**
     * Elimina un elemento del árbol. El método elimina el nodo que contiene el
     * elemento, y gira el árbol como sea necesario para rebalancearlo. La
     * complejidad en tiempo del método es <i>O</i>(log <i>n</i>) garantizado.
     *
     * @param elemento el elemento a eliminar del árbol.
     */
    @Override
    public void eliminar(T elemento) {
        Nodo eliminar = buscaNodo(this.raiz, elemento);
        NodoAVL eliminado = nodoAVL(eliminaNodo(eliminar));
        rebalancea(eliminado);
    }

    private int getAltura(Nodo nodo) {
        if (nodo == null)
            return 0;
        return nodoAVL(nodo).altura;
    }

    /**
     * Convierte el nodo (visto como instancia de {@link
     * Nodo}) en nodo (visto como instancia de {@link
     * NodoAVL}). Método auxililar para hacer este cast en un único lugar.
     *
     * @param nodo el nodo de árbol binario que queremos como nodo AVL.
     * @return el nodo recibido visto como nodo AVL.
     */
    protected NodoAVL nodoAVL(Nodo nodo) {
        return (NodoAVL) nodo;
    }
}