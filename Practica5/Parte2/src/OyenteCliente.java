import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class OyenteCliente extends Thread{
    private Socket s;
    private Map<String, Usuario> tUsr;
    private Map<String, Flujo> tSock;
    private Map<String, Pelicula> catalogo;
    private Puertos puertos;

    public OyenteCliente(Socket s, Map<String, Usuario> tUsr, Map<String, Flujo> tSock, Map<String, Pelicula> catalogo, Puertos puertos){
        this.s = s;
        this.tUsr = tUsr;
        this.tSock = tSock;
        this.puertos = puertos;
        this.catalogo = catalogo;
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
                        tUsr.put(aux1.getUsr().getId(), aux1.getUsr());
                        tSock.put(aux1.getUsr().getId(), new Flujo(fin, fout));
                        for(Pelicula p : aux1.getUsr().getInfo().values()){
                            catalogo.put(p.getName(), p);
                        }
                        fout.writeObject(new MenConfCon());
                    }
                    case M_LISTA_USR -> {
                        String s = "Lista de usuarios conectados: \n";
                        for (Usuario u : tUsr.values()) {
                            s = s.concat(u.toString());
                        }
                        fout.writeObject(new MenConfList(s));
                    }
                    case M_PEDIR_FICHERO -> {
                        MenPedirFich maux3 = (MenPedirFich) m;
                        String p = maux3.getFichero();
                        if(!catalogo.containsKey(p)){
                            fout.writeObject(new MenError("Error al procesar la petición. La película seleccionada no forma parte del catálogo"));
                        }
                        else {
                            String u = maux3.getOrigen();
                            String emisor = null;
                            for (String s : tUsr.keySet()) {
                                if (!s.equals(u) && tUsr.get(s).getInfo().containsKey(p)) {
                                    emisor = s;
                                    break;
                                }
                            }
                            if (emisor != null) {
                                Flujo f = tSock.get(emisor);
                                int port = puertos.getPort();
                                f.getFout().writeObject(new MenEmitirFich(p, u, port));
                            } else {
                                fout.writeObject(new MenError("Error al procesar la petición. Ningún usuario en línea posee el material seleccionado"));
                            }
                        }
                    }
                    case M_PREPARADO_CS -> {
                        MenPrepCS maux = (MenPrepCS) m;
                        Flujo f = tSock.get(maux.getDestino());
                        ObjectOutputStream foutDest = f.getFout();
                        foutDest.writeObject(new MenPrepSC(maux.getOrigen(), maux.getDestino(), maux.getIP(), maux.getPort()));
                    }
                    case M_ACT_USUARIO -> {
                        MenActUsuario aux = (MenActUsuario) m;
                        Pelicula p = catalogo.get(aux.getPelicula());
                        tUsr.get(aux.getOrigen()).addInfo(p);
                        puertos.closePort(aux.getPort());
                    }
                    case M_CERRAR_CONEXION -> {
                        MenCerrCon aux = (MenCerrCon) m;
                        tUsr.remove(aux.getOrigen());
                        tSock.remove(aux.getOrigen());
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
