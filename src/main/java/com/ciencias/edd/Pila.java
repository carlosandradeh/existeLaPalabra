package main.java.com.ciencias.edd;
import java.util.NoSuchElementException;

/**
 * <p> Clase concreta para modelar la estructura de datos Pila</p>
 * <p>Esta clase implementa una Pila genérica, es decir que es homogénea pero
 * puede tener elementos de cualquier tipo.
 * @author Luis Manuel Martinez Damaso <luismanuel@ciencias.unam.mx>
 * @version 1.0
 * @param <T> Tipo que tienen los objetos que guarda esta Pila.
 */
public class Pila<T> extends Lista<T> implements Apilable<T> {
    /**
     * Constructor por omisión de la clase.
     */
    public Pila() {
        //Aqui no hay que hacer nada,
        //ya que los valores por default nos sirven al crear un objeto.
    }

    /**
     * Constructor de la clase que recibe parámetros.
     * Crea una nueva Pila con los elementos de la estructura iterable que recibe como parámetro.
     * @param iterable El objeto que se recibe como parámetro.
     */
    public Pila(Iterable<T> iterable) {
        for (T elemento : iterable) {
            push(elemento);
        }

    }

    /**
     * Constructor de la clase que recibe parámetros.
     * Crea una nueva Pila con los elementos de la estructura iterable que recibe como parámetro.
     */
    public Pila(Pila<T> p) {
        for (T elemento : p) {
            push(elemento);
        }
    }

    /**
     * Agrega un elemento a la Pila.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     * <code>null</code>.
     */
    @Override
    public void push(T elemento) throws IllegalArgumentException{
        if (elemento == null) 
            throw new IllegalArgumentException("¡Los null no son permitidos en las Pilas!");
        
        //Agregamos al Inicio. PILA: First In Last Out.
        super.agregar(elemento);
    }

    /**
     * Elimina el elemento del inicio de la Pila y lo regresa.
     * @throws NoSuchElementException si la Pila es vacía
     * @return el elemento en el inicio de la Pila.
     */
    @Override
    public T pop() throws NoSuchElementException{
        if (esVacia())
            throw new NoSuchElementException("¡No se puede eliminar de Pila, está vacia!");
        
        //Eliminamos Inicio (Cabeza). PILA: First In Last Out.
        T eliminado = top();
        super.eliminarPrimero();
        return eliminado;
    }

    /**
     * Nos permite ver el elemento en el inicio de la Pila
     *
     * @return el siguiente elemento a desapilar.
     */
    @Override
    public T top() throws NoSuchElementException{
        if (esVacia())
            throw new NoSuchElementException("¡Lista vacia no se puede obtener el primer elemento!");

        return this.cabeza.elemento;
    }
    
    /**
     * Método para agregar un elemento a la Pila.
     * @param elemento Objeto que se agregará a la Pila.
     */
    @Override
    public void agregar(T elemento){
        throw new UnsupportedOperationException("No se puede hacer esta operación. Para agregar elementos a una pila usa el método push(elemento)");
    }
    
    /**
     * Método para agregar al final un elemento a la Pila. Este método
     * no debería ser llamado. Por eso va a devolver una excepción.
     * @param elemento Objeto que se agregará al inicio de la Pila.
     */
    @Override
    public void agregarAlFinal(T elemento) {
        throw new UnsupportedOperationException("No se puede hacer esta operación. Para agregar elementos a una pila usa el método push(elemento)");
    }
    
    /**
     * Método para obtener el primer elemento. Este método
     * no debería ser llamado. Por eso va a devolver una excepción.
     * @return 
     */
    @Override
    public T getPrimero() {
        throw new UnsupportedOperationException("No se puede hacer esta operación. Para ver el tope de la pila usa el método top()");
    }
    
    /**
     * Método para obtener el último elemento. Este método
     * no debería ser llamado. Por eso va a devolver una excepción.
     * @return 
     */
    @Override
    public T getUltimo() {
        throw new UnsupportedOperationException("No se puede hacer esta operación. Para ver el tope de la pila usa el método top()");
    }
    
    
    /**
     * Método para eliminar especifico de la Pila. Este método
     * no debería ser llamado. Por eso va a devolver una excepción.
     * @param elemento Objeto que se eliminara de la Pila.
     * todo
     */
    @Override
    public void eliminar(T elemento) {
        throw new UnsupportedOperationException("No se puede hacer esta operación. Para eliminar el tope de la pila el método pop()");
    }
    
    /**
     * Método para eliminar el primer elemento de la Pila. Este método
     * no debería ser llamado. Por eso va a devolver una excepción.
     */
    @Override
    public void eliminarPrimero() {
        throw new UnsupportedOperationException("No se puede hacer esta operación. Para eliminar el tope de la pila el método pop()");
    }
    
    /**
     * Método para eliminar el primer elemento de la Pila. Este método
     * no debería ser llamado. Por eso va a devolver una excepción.
     */
    @Override
    public void eliminarUltimo() {
        throw new UnsupportedOperationException("No se puede hacer esta operación. Para eliminar el tope de la pila el método pop()");
    }


    @Override
    public String toString() {
        String s = "[";
        Nodo n = this.cabeza;
        while (n != null) {
            if (n.siguiente == null) {
                s += n.elemento;
            } else {
                s += n.elemento + ",";
            }
            n = n.siguiente;
        }
        s += "]";
        return s;

    }
    
}
