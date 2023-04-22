import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class OyenteServidor extends Thread{
    private Socket s;
    private Usuario usr;
    private ObjectInputStream fin;
    private ObjectOutputStream fout;
    private miLock l;
    public OyenteServidor(Socket s, Usuario usr, ObjectInputStream fin, ObjectOutputStream fout, miLock l){
        this.s = s;
        this.usr = usr;
        this.fin = fin;
        this.fout = fout;
        this.l = l;
    }

    public void run(){
        Mensaje m;
        try {
            fout.writeObject(new MenCon(usr));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        boolean terminar = false;
        while(!terminar){
            try {
                m = (Mensaje) fin.readObject();
                l.takeLock(1);
                switch (m.getTipo()) {

                    case M_CONF_CONEXION -> {
                        System.out.println("Conexión realizada con éxito");
                    }
                    case M_CONF_LISTA_USR -> {
                        MenConfList aux = (MenConfList) m;
                        System.out.println(aux.getLista());
                    }
                    case M_EMITIR_FICHERO -> {
                        MenEmitirFich aux = (MenEmitirFich) m;
                        System.out.println("Emitiendo fichero " + aux.getPelicula());
                        Emisor e = new Emisor(usr.getInfo().get(aux.getPelicula()), aux.getPuertos());
                        e.start();
                        fout.writeObject(new MenPrepCS(usr.getId(), aux.getDestino(), InetAddress.getLocalHost().getHostAddress(), aux.getPuertos()));
                    }
                    case M_PREPARADO_SC -> {
                        MenPrepSC maux = (MenPrepSC) m;
                        Receptor r = new Receptor(maux.getIP(), maux.getPort(), usr, new Flujo(fin, fout));
                        r.start();
                    }
                    case M_CONF_CERRAR_CONEXION -> {
                        System.out.println("Conexión cerrada con éxito");
                        terminar = true;
                    }
                    case M_ERROR -> {
                        MenError aux = (MenError) m;
                        System.out.println(aux.getMen());
                    }
                }
                l.releaseLock(1);
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
