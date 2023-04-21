import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Receptor extends Thread{
    private String ip;
    private int port;
    private Usuario usr;
    private Flujo f;

    public Receptor(String ip, int port, Usuario usr, Flujo f){
        this.ip = ip;
        this.port = port;
        this.usr = usr;
        this.f = f;
    }

    public void run(){
        try {
            Socket s = new Socket(InetAddress.getByName(ip), port);
            ObjectInputStream fin = new ObjectInputStream(s.getInputStream());
            ObjectOutputStream fout = new ObjectOutputStream(s.getOutputStream());
            Pelicula peli = (Pelicula) fin.readObject();
            usr.addInfo(peli);
            f.getFout().writeObject(new MenActUsuario(usr.getId(), peli.toString(), port));
            s.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
