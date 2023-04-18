import java.net.Socket;
import java.util.ArrayList;

public class OyenteCliente extends Thread{
    Socket s;
    private final ArrayList<Usuario> tUsr;
    private final ArrayList<Flujo> tSock;

    public OyenteCliente(Socket s, ArrayList<Usuario> tUsr, ArrayList<Flujo> tSock){
        this.s = s;
        this.tUsr = tUsr;
        this.tSock = tSock;
    }

    public void run(){
        while(true){

        }
    }
}
