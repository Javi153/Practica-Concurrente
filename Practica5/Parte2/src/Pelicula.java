import java.io.Serializable;

public class Pelicula implements Serializable {
    String nombre;

    public Pelicula(String nombre){
        this.nombre = nombre;
    }

    public String getName(){
        return nombre;
    }
}
