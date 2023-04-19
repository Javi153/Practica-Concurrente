import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.Random;
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
        boolean terminar = false;
        while(!terminar){
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
                        Random r = new Random();
                        MenPedirFich maux = (MenPedirFich) m;
                        Pelicula p = maux.getFichero();
                        Usuario u = maux.getUsr();
                        Usuario emisor = null;
                        for(Usuario usr : tUsr){
                            if(usr != u && usr.getInfo().contains(p)){
                                emisor = usr;
                                break;
                            }
                        }
                        if(emisor != null){
                            Flujo f = tSock.get(emisor.getId());
                            f.getFout().writeObject(new MenEmitirFich(p, u, tSock.get(u.getId())));
                        }
                        else{
                            fout.writeObject(new MenError());
                        }
                    }
                    case M_PREPARADO_CS -> {
                    }
                    case M_CERRAR_CONEXION -> {
                        MenCerrCon aux = (MenCerrCon) m;
                        tUsr.remove(aux.getUsr());
                        tSock.remove(aux.getUsr().getId());
                        fout.writeObject(new MenConfCerrCon());
                        terminar = true;
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
