import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private static TablaUsuario tUsr;
    private static TablaFlujo tSock;
    private static Catalogo catalogo;
    private static Puertos puertos;

    public static void main(String[] args) throws IOException {
        tUsr = new TablaUsuario();
        tSock = new TablaFlujo();
        catalogo = new Catalogo();
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
