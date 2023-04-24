import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
//Practica realizada por Javier Jimenez Arenas
public class Servidor {
    private static TablaUsuario tUsr;
    private static TablaFlujo tSock;
    private static Catalogo catalogo;
    private static Puertos puertos;

    public static void main(String[] args) throws IOException { //El servidor simplemente crea las tablas de informacion necesaria y espera nuevas conexiones indefinidamente
        tUsr = new TablaUsuario();
        tSock = new TablaFlujo();
        catalogo = new Catalogo();
        puertos = new Puertos();
        ServerSocket serverS = null;
        try {
            serverS = new ServerSocket(1025);
            System.out.println("Servidor activo. Dirección IP: " + InetAddress.getLocalHost().getHostAddress());
            while (true) {
                System.out.println("Esperando nueva conexión...");
                Socket s = serverS.accept();
                System.out.println("Conexión establecida con " + s.getInetAddress().getHostAddress());
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
