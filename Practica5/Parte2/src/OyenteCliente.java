import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.Set;

public class OyenteCliente extends Thread{
    private Socket s;
    private Set<Usuario> tUsr;
    private Map<String, Flujo> tSock;
    private Puertos puertos;

    public OyenteCliente(Socket s, Set<Usuario> tUsr, Map<String, Flujo> tSock, Puertos puertos){
        this.s = s;
        this.tUsr = tUsr;
        this.tSock = tSock;
        this.puertos = puertos;
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
                switch (m.getTipo()) {
                    case M_CONEXION -> {
                        MenCon aux1 = (MenCon) m;
                        tUsr.add(aux1.getUsr());
                        tSock.put(aux1.getUsr().getId(), new Flujo(fin, fout));
                        fout.writeObject(new MenConfCon());
                    }
                    case M_LISTA_USR -> {
                        fout.writeObject(new MenConfList(tUsr));
                    }
                    case M_PEDIR_FICHERO -> {
                        MenPedirFich maux3 = (MenPedirFich) m;
                        Pelicula p = maux3.getFichero();
                        Usuario u = maux3.getUsr();
                        Usuario emisor = null;
                        for (Usuario usr : tUsr) {
                            if (usr != u && usr.getInfo().contains(p)) {
                                emisor = usr;
                                break;
                            }
                        }
                        if (emisor != null) {
                            Flujo f = tSock.get(emisor.getId());
                            f.getFout().writeObject(new MenEmitirFich(p, u, tSock.get(u.getId()), puertos));
                        } else {
                            fout.writeObject(new MenError());
                        }
                    }
                    case M_PREPARADO_CS -> {
                        MenPrepCS maux = (MenPrepCS) m;
                        Flujo f = tSock.get(maux.getDestino());
                        ObjectOutputStream foutDest = f.getFout();
                        foutDest.writeObject(new MenPrepSC(maux.getOrigen(), maux.getDestino(), maux.getIP(), maux.getPort()));
                    }
                    case M_CERRAR_CONEXION -> {
                        MenCerrCon aux = (MenCerrCon) m;
                        tUsr.remove(aux.getUsr());
                        tSock.remove(aux.getUsr().getId());
                        terminar = true;
                        fout.writeObject(new MenConfCerrCon());
                    }
                }
            } catch (IOException | ClassNotFoundException | ClassCastException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            fin.close();
            fout.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
