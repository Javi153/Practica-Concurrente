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
    public OyenteServidor(Socket s, Usuario usr, ObjectInputStream fin, ObjectOutputStream fout){
        this.s = s;
        this.usr = usr;
        this.fin = fin;
        this.fout = fout;
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
                        System.out.println("Error en la conexión, no pudo procesarse la petición");
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
