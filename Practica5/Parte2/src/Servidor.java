import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Servidor {
    private static Map<String, Usuario> tUsr;
    private static Map<String, Flujo> tSock;
    private static Map<String,Pelicula> catalogo;
    private static Puertos puertos;

    public static void main(String[] args) throws IOException {
        tUsr = new HashMap<>();
        tSock = new HashMap<>();
        catalogo = new HashMap<>();
        puertos = new Puertos();
        ServerSocket serverS = null;
        try {
            serverS = new ServerSocket(1025);
            while (true) {
                System.out.println("Servidor activo. Dirección IP: " + InetAddress.getLocalHost().getHostAddress());
                Socket s = serverS.accept();
                OyenteCliente o = new OyenteCliente(s, tUsr, tSock, catalogo, puertos);
                o.start();
            }
        } catch (Exception e) {
            System.out.println("Conexión interrumpida");
            if(serverS != null) {
                serverS.close();
            }
            throw new RuntimeException(e);
        }
    }
}
