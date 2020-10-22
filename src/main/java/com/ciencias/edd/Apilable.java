package main.java.com.ciencias.edd;
import java.util.NoSuchElementException;

/**
 * <p> Interfaz para Pilas. Esta clase contiene las
 * operaciones elementales que debe tener el TAD Pila </p>
 * @author Luis Manuel Martinez Damaso <luismanuel@ciencias.unam.mx>
 * @version 1.0
 * @param <T>
 */
public interface Apilable<T>  {
    
    /**
     * Método para apilar un elemento a la pila.
     * @param elemento Objeto que se agregará a la pila.
     */
    public void push(T elemento) throws IllegalArgumentException;

    /**
     * Método para desapilar un elemento de la pila.
     * @return El elemento que fue sacado de la pila
     */
    public T pop() throws NoSuchElementException;
    
    /**
     * Método para mirar lo que hay al inicio de la Pila
     * @return El elemento que esta al inicio de la Pila
     */
    public T top() throws NoSuchElementException;
    
}
