package main.java.com.ciencias.edd;

public class Palabra implements Comparable<Palabra> {
    private String palabra;

    public Palabra(String palabra) {
        this.palabra = palabra;
    }

    @Override
    public int compareTo(Palabra p) {
        return this.palabra.compareTo(p.palabra);
    }

    public String getPalabra() {
        return this.palabra;
    }

    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        
        Palabra p = (Palabra) o;
        if (!this.palabra.equals(p.palabra))
            return false;
        
        return true;
    }

    public String toString() {
        return this.palabra;
    }
    
}