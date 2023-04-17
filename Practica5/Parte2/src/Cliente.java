import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) throws IOException {
        //leer nombre ??
        Socket s = new Socket(InetAddress.getByName("192.168.56.1"), 999);
        OyenteServidor o = new OyenteServidor(s);
        o.start();
        //menu
    }
}
