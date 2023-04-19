import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Servidor {
    private static Set<Usuario> tUsr;
    private static Map<String, Flujo> tSock;

    public Servidor(){
        tUsr = new HashSet<>();
        tSock = new HashMap<>();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverS = new ServerSocket(999);
        while (true) {
            Socket s = serverS.accept();
            /*ObjectInputStream finS = new ObjectInputStream(s.getInputStream());
            ObjectOutputStream foutS = new ObjectOutputStream(s.getOutputStream());
            Usuario usr = (Usuario) finS.readObject();
            tUsr.add(usr);
            Flujo f = new Flujo(usr.getId(), finS, foutS);
            tSock.add(f);*/
            OyenteCliente o = new OyenteCliente(s, tUsr, tSock);
            o.start();
        }
    }
}
