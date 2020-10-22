package main.java.com.ciencias.edd;
import java.util.NoSuchElementException;

/**
 * <p> Interfaz para Colas. Esta clase contiene las
 * operaciones elementales que debe tener el TAD Cola </p>
 * @author Luis Manuel Martinez Damaso <luismanuel@ciencias.unam.mx>
 * @version 1.0
 * @param <T>
 */
public interface Encolable<T> {

    /**
     * Método para encolar un elemento a la cola.
     * @param elemento Objeto que se agregará a la cola.
     */
    public void queue(T elemento) throws IllegalArgumentException;

    /**
     * Método para desencolar un elemento.
     * @return El elemento que fue desencolado
     */
    public T dequeue() throws NoSuchElementException;
    
    /**
     * Método para mirar lo que hay al inicio de la Cola
     * @return El elemento que esta al inicio de la cola
     */
    public T peek() throws NoSuchElementException;


}
