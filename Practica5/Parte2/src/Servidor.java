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
    private static Set<Usuario> tUsr;
    private static Map<String, Flujo> tSock;
    private static Puertos puertos;

    public static void main(String[] args) throws IOException {
        tUsr = new HashSet<>();
        tSock = new HashMap<>();
        puertos = new Puertos();
        ServerSocket serverS = null;
        try {
            serverS = new ServerSocket(1025);
            while (true) {
                System.out.println("Servidor activo. Dirección IP: " + InetAddress.getLocalHost());
                Socket s = serverS.accept();
                OyenteCliente o = new OyenteCliente(s, tUsr, tSock, puertos);
                o.start();
            }
        } catch (Exception e) {
            System.out.println("Conexión interrumpida");
            serverS.close();
            throw new RuntimeException(e);
        }
    }
}
