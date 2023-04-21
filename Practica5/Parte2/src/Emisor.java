import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Emisor extends Thread{
    private Pelicula p;
    private int port;

    public Emisor(Pelicula p, int port){
        this.p = p;
        this.port = port;
    }

    public void run(){
        try {
            ServerSocket s = new ServerSocket(port);
            Socket sc = s.accept();
            ObjectOutputStream fout = new ObjectOutputStream(sc.getOutputStream());
            fout.flush();
            ObjectInputStream fin = new ObjectInputStream(sc.getInputStream());
            fout.writeObject(p);
            fout.close();
            fin.close();
            s.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
