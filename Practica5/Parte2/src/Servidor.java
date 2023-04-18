import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {
    private static ArrayList<Usuario> tUsr;
    private static ArrayList<Flujo> tSock;

    public Servidor(){
        tUsr = new ArrayList<Usuario>();
        tSock = new ArrayList<Flujo>();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverS = new ServerSocket(999);
        while (true) {
            Socket s = serverS.accept();
            ObjectInputStream finS = new ObjectInputStream(s.getInputStream());
            ObjectOutputStream foutS = new ObjectOutputStream(s.getOutputStream());
            Usuario usr = (Usuario) finS.readObject();
            tUsr.add(usr);
            Flujo f = new Flujo(usr.getId(), finS, foutS);
            tSock.add(f);
            OyenteCliente o = new OyenteCliente(s, tUsr, tSock);
            o.start();
        }
    }
}
