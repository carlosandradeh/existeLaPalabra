package main.java.com.ciencias.edd;
import java.util.NoSuchElementException;

/**
 * <p> Interfaz para listas </p> <p>Esta clase contiene las
 * operaciones elementales que debe tener el TAD Lista </p>
 * @author Luis Manuel Martinez Damaso <luismanuel@ciencias.unam.mx>
 * @version 1.0
 * @param <T>
 */
public interface Listable<T> extends Coleccionable<T>{

    /**
     * Método para eliminar todos los elementos de una lista
     */
    public void vaciar();
    
    /**
     * Método para eliminar un elemento de la lista.
     */
    public void eliminarPrimero() throws NoSuchElementException;

    /**
     * Método para obtener el primer elemento de la lista.
     * @return 
     */
    public T getPrimero() throws NoSuchElementException;

    /**
     * Método que devuelve la posición en la lista que tiene la primera
     * aparición del <code> elemento</code>.
     * @param elemento El elemnto del cuál queremos saber su posición.
     * @return i la posición del elemento en la lista, -1, si no se encuentra en ésta.
     */
    public int indiceDe(T elemento) throws NoSuchElementException;

    /**
     * Método que nos devuelve el elemento que esta en la posición i
     * @param i La posición cuyo elemento deseamos conocer.
     * @return <code> elemento </code> El elemento que contiene la lista,
     * <code>null</code> si no se encuentra
     * @throws IndexOutOfBoundsException Si el índice es < 0 o >longitud()
     */
    public T getElemento(int i)throws IndexOutOfBoundsException;

    /**
     * Hace una copia de la lista agregando en orden inverso con respecto
     * a la lista original.
     * @return La reversa de una lista.
     */
    public Listable<T> reversa();

    /**
     * Método que devuelve una copia exacta de la lista
     * @return la copia de la lista.
     */
    public Listable<T> copia();

}