import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class OyenteCliente extends Thread{
    private Socket s;
    private TablaUsuario tUsr;
    private TablaFlujo tSock;
    private Catalogo catalogo;
    private Puertos puertos;

    public OyenteCliente(Socket s, TablaUsuario tUsr, TablaFlujo tSock, Catalogo catalogo, Puertos puertos){
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
                        tUsr.write(aux1.getUsr().getId(), aux1.getUsr());
                        tSock.write(aux1.getUsr().getId(), new Flujo(fin, fout));
                        for(Pelicula p : aux1.getUsr().getInfo().values()){
                            catalogo.write(p);
                        }
                        fout.writeObject(new MenConfCon());
                    }
                    case M_LISTA_USR -> {
                        String s = "Lista de usuarios conectados: \n";
                        for (Usuario u : tUsr.valores()) {
                            s = s.concat(u.toString());
                        }
                        fout.writeObject(new MenConfList(s));
                    }
                    case M_PEDIR_FICHERO -> {
                        MenPedirFich maux3 = (MenPedirFich) m;
                        String p = maux3.getFichero();
                        if(catalogo.read(p) == null){
                            fout.writeObject(new MenError("Error al procesar la petición. La película seleccionada no forma parte del catálogo"));
                        }
                        else {
                            String u = maux3.getOrigen();
                            String emisor = null;
                            for (String s : tUsr.claves()) {
                                if (!s.equals(u) && tUsr.read(s).getInfo().containsKey(p)) {
                                    emisor = s;
                                    break;
                                }
                            }
                            if (emisor != null) {
                                Flujo f = tSock.read(emisor);
                                int port = puertos.getPort();
                                f.getFout().writeObject(new MenEmitirFich(p, u, port));
                            } else {
                                fout.writeObject(new MenError("Error al procesar la petición. Ningún usuario en línea posee el material seleccionado"));
                            }
                        }
                    }
                    case M_PREPARADO_CS -> {
                        MenPrepCS maux = (MenPrepCS) m;
                        Flujo f = tSock.read(maux.getDestino());
                        ObjectOutputStream foutDest = f.getFout();
                        foutDest.writeObject(new MenPrepSC(maux.getOrigen(), maux.getDestino(), maux.getIP(), maux.getPelicula(), maux.getPort()));
                    }
                    case M_ACT_USUARIO -> {
                        MenActUsuario aux = (MenActUsuario) m;
                        Pelicula p = catalogo.read(aux.getPelicula());
                        Usuario uaux = tUsr.read(aux.getOrigen());
                        uaux.addInfo(p);
                        tUsr.write(aux.getOrigen(), uaux);
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
