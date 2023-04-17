import java.net.InetAddress;

public class Usuario {
    private int id;
    private InetAddress ip;
    //Lista informacion compartida ??

    public Usuario(int id, InetAddress ip){
        this.id = id;
        this.ip = ip;
    }

    public int getId(){
        return id;
    }

    public InetAddress getIP(){
        return ip;
    }
}
