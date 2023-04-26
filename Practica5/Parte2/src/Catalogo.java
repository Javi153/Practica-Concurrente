import java.util.HashMap;
import java.util.Map;

public class Catalogo extends MonitorSem {
    private Map<String, Pelicula> catalogo;

    public Catalogo() { //Implementacion readers-writers con semaforos vista en clase para el acceso concurrente
                        //al catalogo de peliculas del servidor
        super();
        catalogo = new HashMap<>();
    }

    public void write(Pelicula p) {
        requestWrite();
        catalogo.put(p.getName(), p);
        releaseWrite();
    }

    public Pelicula read(String s) {
        requestRead();
        Pelicula aux = catalogo.get(s);
        Pelicula res;
        if(aux == null)
            res = null;
        else
            res = new Pelicula(aux);
        releaseRead();
        return res;
    }
}
