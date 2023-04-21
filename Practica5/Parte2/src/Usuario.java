import java.io.Serializable;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Set;

public class Usuario implements Serializable {
    private String id;
    private InetAddress ip;
    private HashMap<String, Pelicula> info;

    public Usuario(String id, InetAddress ip, HashMap<String, Pelicula> info){
        this.id = id;
        this.ip = ip;
        this.info = info;
    }

    public String getId(){
        return id;
    }

    public InetAddress getIP(){
        return ip;
    }

    public HashMap<String, Pelicula> getInfo(){
        return info;
    }

    public void addInfo(Pelicula p){
        info.put(p.getName(), p);
    }

    public String toString(){
        String s = "Usuario: " + id + ", IP: " + ip.toString() + " posee las siguientes películas:\n";
        for(Pelicula p : info.values()){
            s = s.concat(p.toString() + "\n");
        }
        return s;
    }
}
