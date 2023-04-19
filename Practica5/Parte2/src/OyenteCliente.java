import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.Set;

public class OyenteCliente extends Thread{
    Socket s;
    private final Set<Usuario> tUsr;
    private final Map<String, Flujo> tSock;

    public OyenteCliente(Socket s, Set<Usuario> tUsr, Map<String, Flujo> tSock){
        this.s = s;
        this.tUsr = tUsr;
        this.tSock = tSock;
    }

    public void run(){
        ObjectInputStream fin;
        ObjectOutputStream fout;
        try {
            fin = new ObjectInputStream(s.getInputStream());
            fout = new ObjectOutputStream(s.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while(true){
            try {
                Mensaje m = (Mensaje) fin.readObject();
                switch(m.getTipo()){

                    case M_CONEXION -> {
                        MenCon aux = (MenCon) m;
                        tUsr.add(aux.getUsr());
                        tSock.put(aux.getUsr().getId(), new Flujo(fin, fout));
                        fout.writeObject(new MenConfCon());
                    }
                    case M_LISTA_USR -> {
                        MenConfList aux = new MenConfList(tUsr);
                        fout.writeObject(aux);
                    }
                    case M_PEDIR_FICHERO -> {
                    }
                    case M_PREPARADO_CS -> {
                    }
                    case M_CERRAR_CONEXION -> {
                        MenCerrCon aux = (MenCerrCon) m;
                        tUsr.remove(aux.getUsr());
                        tSock.remove(aux.getUsr().getId());
                        fout.writeObject(new MenConfCerrCon());
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
