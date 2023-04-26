import java.util.HashMap;
import java.util.HashSet;

public class TablaUsuario extends MonitorCond{
    private HashMap<String, Usuario> tabla;

    public TablaUsuario(){ //Tabla de usuarios con lista de peliculas implementado como monitor con Lock y Condicional
        tabla = new HashMap<>();
    }

    public void write(String s, Usuario t, boolean remove){ //Implementamos el write para que sirva tanto para añadir pelicula como para borrarla
        requestWrite();
        if(!remove){
            tabla.put(s, t);
        }
        else{
            tabla.remove(s);
        }
        releaseWrite();
    }

    public void write(String s, Usuario t){
        write(s, t, false);
    } //Si no se especifica se entiende que se añade

    public void remove(String s){
        write(s, null, true);
    } //Añadimos facilidad al usuario renombrando el write/remove:true como remove

    public Usuario read(String s) {
        requestRead();
        Usuario aux = new Usuario(tabla.get(s));
        releaseRead();
        return aux;
    }

    public HashSet<Usuario> valores(){
        requestRead();
        HashSet<Usuario> aux = new HashSet<>();
        for(Usuario u : tabla.values()){
            aux.add(new Usuario(u));
        }
        releaseRead();
        return aux;
    }

    public HashSet<String> claves(){
        requestRead();
        HashSet<String> aux = new HashSet<>();
        for(String s : tabla.keySet()){
            aux.add(new String(s));
        }
        releaseRead();
        return aux;
    }
}
