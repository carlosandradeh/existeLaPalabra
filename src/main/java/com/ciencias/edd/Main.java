package main.java.com.ciencias.edd;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    /**
     * Método para leer un archivo txt y almacenar cada línea en un árbolAVL de Palabras.
     * @param arbol Árbol en el que se almacenará cada Palabra.
     * @param archivo Ruta del archivo a leer.
     */
    public static void leerAlmacenar(ArbolAVL<Palabra> arbol, String archivo) throws IOException {
        //*Lectores para diccionario.txt
        FileReader f = new FileReader(archivo);
        BufferedReader reader = new BufferedReader(f);

        //*String que va a alamacenar cada palabra de diccionario.txt
        String palabra;

        //*Almacenamos cada Palabra de diccionario.txt en el árbolAVL.
        while ((palabra = reader.readLine()) != null) {
            arbol.agregar(new Palabra(palabra));
        }
        //*Cerramos el lector.
        reader.close();
    }

    public static void main(String[] args) {
        //*Árbol en el que se almacenará cada palabra del archivo y ruta del árchivo.
        ArbolAVL<Palabra> arbol = new ArbolAVL<>();
        String ruta = "src/main/java/com/ciencias/edd/txt/diccionario.txt";

        //*Almacenamos cada palabra en el árbol.
        try {
            Main.leerAlmacenar(arbol, ruta);
        } catch (FileNotFoundException filenotfound) {
            System.out.println(filenotfound);
            System.exit(1);
        } catch (IOException ioexception) {
            System.out.println(ioexception);
            System.exit(1);
        }
    
        //*Pedimos la palabra a checar si está en el diccionario.
        Scanner scanner = new Scanner(System.in);
        System.out.println("¡Programa para ver si tu palabra está en el diccionario!\n");
        System.out.print("Ingresa tu palabra: ");
        String palabraAbuscar = scanner.nextLine().toLowerCase();
        Palabra palabra = new Palabra(palabraAbuscar);

        //*Checamos si está en el diccionario
        if (arbol.contiene(palabra))
            System.out.println("La palabra " + palabra.getPalabra() + " sí está contenida en el diccionario.\n");
        else 
            System.out.println("La palabra " + palabra.getPalabra() + " no está contenida en el diccionario.\n");
    }
}