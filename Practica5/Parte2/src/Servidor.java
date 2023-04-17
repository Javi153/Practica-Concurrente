import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverS = new ServerSocket(999);
        while(true){
            Socket s = serverS.accept();
            OyenteCliente o = new OyenteCliente(s);
            o.start();
        }
    }
}
