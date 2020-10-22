package main.java.com.ciencias.edd;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p> Clase concreta para modelar la estructura de datos Lista</p>
 * <p>Esta clase implementa una Lista genérica, es decir que es homogénea pero
 * puede tener elementos de cualquier tipo.
 * @author Luis Manuel Martinez Damaso <luismanuel@ciencias.unam.mx>
 * @version 1.0
 * @param <T>
 */
public class Lista<T> implements Listable<T>{

    /* Clase interna para construir la estructura */
    protected class Nodo{
        /* Referencias a los nodos anterior y siguiente */
        public Nodo anterior, siguiente;
        /* El elemento que almacena un nodo */
        public T elemento;

        /* Unico constructor de la clase */
        public Nodo(T elemento){
            this.elemento = elemento;
        }
        public boolean equals(Nodo n){
            return (this.elemento.equals(n.elemento));
        }
    }

    
    private class IteradorLista implements Iterator<T>{
        /* La lista a recorrer*/
        /* Elementos del centinela que recorre la lista*/
        private Lista<T>.Nodo siguiente;

        public IteradorLista(){
            //La cabeza de la lista será el primer elemento.
            this.siguiente = cabeza;
        }

        @Override
        public boolean hasNext() {
            return this.siguiente != null;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException("No hay siguiente!");
            
            T next = this.siguiente.elemento;
            this.siguiente = this.siguiente.siguiente;
            return next; 
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    
    /* Atributos de la lista */
    protected Nodo cabeza, cola;
    protected int longitud;

    /**
     *  Constructor por omisión de la clase, no recibe parámetros.
     *  Crea una nueva lista con longitud 0.
     **/
    public Lista(){
        this.cabeza = this.cola = null;
        this.longitud = 0;
    }

    /**
     *  Constructor copia de la clase. Recibe una lista con elementos.
     *  Crea una nueva lista exactamente igual a la que recibimos como parámetro.
     * @param l
     **/
    public Lista(Lista<T> l){
        for (T elemento : l) {
            agregarAlFinal(elemento);
        }
    }

    /**
     *  Constructor de la clase que recibe parámetros.
     *  Crea una nueva lista con los elementos de la estructura iterable que recibe como parámetro.
     * @param iterable
     **/
    public Lista(Iterable<T> iterable){
        Iterator<T> iterador = iterable.iterator();
        while (iterador.hasNext()) {
            agregarAlFinal(iterador.next());
        }
    }
    
    /**
     * Método que nos dice si las lista está vacía.
     * @return <code>true</code> si el conjunto está vacío, <code>false</code>
     * en otro caso.
     */
    @Override
    public boolean esVacia(){
        return this.cabeza == null;
    }
    
    /**
     * Método para eliminar todos los elementos de una lista
     */
    @Override
    public void vaciar(){
        this.cabeza = this.cola = null;
        this.longitud = 0;
    }
    
    /**
     * Método para obtener el tamaño de la lista
     * @return tamanio Número de elementos de la lista.
     **/
    @Override
    public int getTamanio(){
        return this.longitud;
    }
     
    /**
     * Método para agregar un elemento a la lista.
     * @param elemento Objeto que se agregará a la lista.
     */
    @Override
    public void agregar(T elemento){
        if (elemento == null)
            throw new IllegalArgumentException("Los null no son soportados en esta lista");
        
        //Nodo a agregar
        Nodo nuevo = new Nodo(elemento);

        if (esVacia()) {
            this.cabeza = this.cola = nuevo;
        } else {
            this.cabeza.anterior = nuevo;
            nuevo.siguiente = this.cabeza;
            this.cabeza = nuevo;
        }

        this.longitud++;
    }
    
    /**
     * Método para agregar al final un elemento a la lista.
     * @param elemento Objeto que se agregará al inicio de la lista.
     */
    public void agregarAlFinal(T elemento) throws IllegalArgumentException {
        if (elemento == null)
            throw new IllegalArgumentException("Los null no son soportados en esta lista");

        //Nodo a agregar.
        Nodo nuevo = new Nodo(elemento);

        if (esVacia()) {
            this.cabeza = this.cola = nuevo;
        } else {
            this.cola.siguiente = nuevo;
            nuevo.anterior = this.cola;
            this.cola = nuevo;
        }

        this.longitud++;
    }

    /**
     * Método para obtener el primer elemento.
     */
    @Override
    public T getPrimero() throws NoSuchElementException {
        if (esVacia())
            throw new NoSuchElementException("Lista Vacia.");
        
        return this.cabeza.elemento;
    }

    /**
     * Método para obtener el último elemento.
     */
    public T getUltimo() throws NoSuchElementException {
        if (esVacia())
            throw new NoSuchElementException("Lista Vacia.");
            
        return this.cola.elemento;
    }
    
    /**
     * Método recursivo para obtener un nodo.
     * @param nodo 
     * @param elemento
     * @return <code>Nodo</code> el nodo buscado que contiene el elemento y null en otro caso.
     */
    private Nodo buscaNodo(Nodo nodo, T elemento) {
        //Caso base:
        if (nodo == null)
            return null;

        //Si encontramos el nodo:
        if (nodo.elemento.equals(elemento))
            return nodo;
        
        //Caso Recursivo:
        return buscaNodo(nodo.siguiente, elemento);
    }

    /**
     * Método para verificar si un elemento pertenece a la lista.
     * @param elemento Objeto que se va a buscar en la lista.
     * @return <code>true</code> si el elemento esta en el lista y false en otro caso.
     */
    @Override
    public boolean contiene(T elemento) throws NoSuchElementException {
        if (esVacia())
            throw new NoSuchElementException("Lista Vacia");

        //Usamos nuestro método auxiliar, si es null es porque no contiene al elemento.
        return buscaNodo(this.cabeza, elemento) != null;
    }

    /**
     * Método para eliminar un elemento de la lista.
     * @param elemento Objeto que se eliminara de la lista.
     * todo
     */
    @Override
    public void eliminar(T elemento) throws NoSuchElementException {  
        if (esVacia())
            throw new NoSuchElementException("Lista Vacia");

        Nodo eliminar = buscaNodo(this.cabeza, elemento);
        
        if (eliminar != null) {
            //Caso 1: Hay solo un elemento en la lista.
            if (this.longitud == 1) {
                this.cabeza = this.cola = null;
            } else if (this.cabeza == eliminar) { //Caso 2: La cabeza es el nodo a eliminar.
                this.cabeza = this.cabeza.siguiente;
                this.cabeza.anterior = null;
            } else if (this.cola == eliminar) { //Caso 3: El rabo es el nodo a eliminar.
                this.cola = this.cola.anterior;
                this.cola.siguiente = null;
            } else { //Caso 4: El nodo a eliminar no es un extremo de la lista.
                eliminar.siguiente.anterior = eliminar.anterior;
                eliminar.anterior.siguiente = eliminar.siguiente;
            }

            this.longitud--;
        }
    }

    /**
     * Método para eliminar el primer elemento de la lista.
     */
    @Override
    public void eliminarPrimero() throws NoSuchElementException {
        if (esVacia())
            throw new NoSuchElementException("Lista Vacia.");
        
        if (this.longitud == 1) {
            this.cabeza = this.cola = null;
        } else {
            this.cabeza = this.cabeza.siguiente;
            this.cabeza.anterior = null;
        }
        this.longitud--;
    }

    /**
     * Método para eliminar el primer elemento de la lista.
     */
    public void eliminarUltimo() throws NoSuchElementException {
        if (esVacia())
            throw new NoSuchElementException("Lista Vacia");
        
        if (this.longitud == 1) {
            this.cabeza = this.cola = null;
        } else {
            this.cola = this.cola.anterior;
            this.cola.siguiente = null;
        }
        this.longitud--;
    }

    /**
     * Método que devuelve la posición en la lista que tiene la primera
     * aparición del <code> elemento</code>.
     * @param elemento El elemnto del cuál queremos saber su posición.
     * @return i la posición del elemento en la lista, -1, si no se encuentra en ésta.
     */
    @Override
    public int indiceDe(T elemento) throws NoSuchElementException {
        if (esVacia())  
            throw new NoSuchElementException("Lista Vacia");

        //Contador i.
        int i = 0;

        Nodo nodo = this.cabeza;
        while (nodo != null) {
            if (nodo.elemento.equals(elemento))
                return i;
            i++;
            nodo = nodo.siguiente;
        }
        //Si no lo encuentra entonces tiene indice -1.
        return -1;
    }

    /**
     * Método que nos devuelve el elemento que esta en la posición i
     * @param i La posición cuyo elemento deseamos conocer.
     * @return <code> elemento </code> El elemento que contiene la lista,
     * <code>null</code> si no se encuentra
     * @throws IndexOutOfBoundsException Si el índice es < 0 o >longitud()
     */
    @Override
    public T getElemento(int i)throws IndexOutOfBoundsException{
        if (i < 0 || i >= this.longitud) 
            throw new IndexOutOfBoundsException("Indice inválido");
        
        Nodo m = cabeza; 
        int j = 0;
        while (j++ < i) {
            m = m.siguiente;
        } 
        return m.elemento;
    }

    /**
     * Método que devuelve una copia de la lista, pero en orden inverso
     * @return Una copia con la lista l revés.
     */
    @Override
    public Lista<T> reversa(){
        Lista<T> reversa = new Lista<>();
        Nodo nodo = this.cabeza;
        while (nodo != null) {
            reversa.agregar(nodo.elemento);
            nodo = nodo.siguiente;
        }
        return reversa;
    }

    /**
     * Método que devuelve una copia exacta de la lista
     * @return la copia de la lista.
     */
    @Override
    public Lista<T> copia(){
        Lista<T> copia = new Lista<>();
        Nodo nodo = this.cabeza;
        while (nodo != null) {
            copia.agregarAlFinal(nodo.elemento);
            nodo = nodo.siguiente;
        }
        return copia;
    }

    /**
     * Método que nos dice si una lista es igual que otra.
     * @param o objeto a comparar con la lista.
     * @return <code>true</code> si son iguales, <code>false</code> en otro caso.
     */
    @Override
    public boolean equals(Object o){
        @SuppressWarnings("unchecked") Lista<T> lista = (Lista<T>) o;
        
        //Caso 1: Sus longitudes son distintas
        if (this.getTamanio() != lista.getTamanio())
            return false;
        
        //Caso 2: Ambas listas son vacias.
        if (this.esVacia() && lista.esVacia())
            return true;
        
        //Caso 3: Un elemento es diferente.
        Nodo n = this.cabeza;
        Nodo m = lista.cabeza;

        while (n != null) {
            if (!(n.elemento.equals(m.elemento))) 
                return false;
            n = n.siguiente;
            m = m.siguiente;    
        }

        //Si pasa todo lo anterior entonces es true.
        return true;
    }

    /**
     * Método que devuelve un iterador sobre la lista
     * @return java.util.Iterador -- iterador sobre la lista
     */
    @Override
    public java.util.Iterator<T> iterator(){
        return new IteradorLista();
    }


    @Override
    public String toString() {
        if (esVacia()) {
            return "[]";
        }
        Nodo nodo = cabeza;
        String cad = "[" + nodo.elemento;
        while (nodo.siguiente != null) {
            nodo = nodo.siguiente;
            cad += ", " + nodo.elemento;
        }
        return cad + "]";
    }
      
    
    /* Método auxiliar para obtener una referencia a un nodo con un elemento
    específico. Si no existe tal nodo, devuelve <code> null </code> */
    private Nodo getNodo(T elem) throws NoSuchElementException {
        return buscaNodo(this.cabeza, elem);
    }

}