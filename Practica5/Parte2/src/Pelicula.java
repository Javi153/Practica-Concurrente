import java.io.Serializable;

public class Pelicula implements Serializable {
    private String nombre;

    public Pelicula(String nombre){
        this.nombre = nombre;
    }

    public Pelicula(Pelicula p){
        this.nombre = String.copyValueOf(p.getName().toCharArray());
    }

    public String getName(){
        return nombre;
    }

    public String toString(){
        return nombre;
    }
}
