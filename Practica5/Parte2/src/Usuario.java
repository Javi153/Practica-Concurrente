import java.io.Serializable;
import java.net.InetAddress;
import java.util.ArrayList;

public class Usuario implements Serializable {
    private String id;
    private InetAddress ip;
    private ArrayList<Pelicula> info;

    public Usuario(String id, InetAddress ip, ArrayList<Pelicula> info){
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

    public ArrayList<Pelicula> getInfo(){
        return info;
    }

    public void addInfo(Pelicula p){
        info.add(p);
    }
}
