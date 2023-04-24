import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Receptor extends Thread{
    private String ip; //IP del emisor
    private int port; //Puerto del emisor
    private Usuario usr; //Usuario actual
    private Flujo f; //Flujo de entrada y salida

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
            Pelicula peli = (Pelicula) fin.readObject(); //Recibimos el fichero
            usr.addInfo(peli); //Añadimos la pelicula al usuario
            f.getFout().writeObject(new MenActUsuario(usr.getId(), peli.toString(), port)); //Decimos al servidor que actualice la informacion del usuario
            s.close(); //Cerramos puerto
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
