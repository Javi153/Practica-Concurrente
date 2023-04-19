import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Receptor extends Thread{
    private InetAddress ip;
    private int port;
    private Usuario usr;

    public Receptor(InetAddress ip, int port, Usuario usr){
        this.ip = ip;
        this.port = port;
        this.usr = usr;
    }

    public void run(){
        try {
            Socket s = new Socket(ip, port);
            ObjectInputStream fin = new ObjectInputStream(s.getInputStream());
            ObjectOutputStream fout = new ObjectOutputStream(s.getOutputStream());
            Pelicula peli = (Pelicula) fin.readObject();
            usr.addInfo(peli);
            s.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
