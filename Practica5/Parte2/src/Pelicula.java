import java.io.Serializable;

public class Pelicula implements Serializable {
    private String nombre;

    public Pelicula(String nombre){ //Clase del fichero que se va a compartir
                                    //Se crea una clase en vez de usar String para posibles ampliaciones en el futuro
        this.nombre = nombre;
    }

    public Pelicula(Pelicula p){ //Constructor de deep copy para evitar problemas de concurrencia
        this.nombre = new String (p.getName());
    }

    public String getName(){
        return nombre;
    }

    public String toString(){
        return nombre;
    }
}
