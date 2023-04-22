import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
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

    public Usuario(Usuario u) {
        this.id = new String(u.getId());
        try {
            this.ip = InetAddress.getByName(new String(u.getIP().getHostAddress().toCharArray()));
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        this.info = new HashMap<>();
        for(Pelicula p : u.getInfo().values()){
            this.info.put(p.getName(), new Pelicula(p));
        }
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
