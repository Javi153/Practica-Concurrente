import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class OyenteServidor extends Thread{
    private Socket s;
    private Usuario usr;
    public OyenteServidor(Socket s, Usuario usr){
        this.s = s;
        this.usr = usr;
    }

    public void run(){
        ObjectOutputStream fout;
        ObjectInputStream fin;
        Mensaje m;
        try {
            fout = new ObjectOutputStream(s.getOutputStream());
            fin = new ObjectInputStream(s.getInputStream());
            fout.writeObject(new MenCon(usr));
            fout.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        boolean terminar = false;
        while(!terminar){
            try {
                m = (Mensaje) fin.readObject();
            switch(m.getTipo()){

                case M_CONF_CONEXION -> {
                    System.out.println("Conexión realizada con éxito");
                }
                case M_CONF_LISTA_USR -> {
                    MenConfList aux = (MenConfList) m;
                    for(Usuario u : aux.getLista()){
                        System.out.println(u.toString());
                    }
                }
                case M_EMITIR_FICHERO -> {
                    MenEmitirFich aux = (MenEmitirFich) m;
                    int port = aux.getPuertos().getPort();
                    Emisor e = new Emisor(aux.getPelicula(), port);
                    e.start();
                    fout.writeObject(new MenPrepCS(usr.getId(), aux.getDestino(), InetAddress.getLocalHost(),port));
                }
                case M_PREPARADO_SC -> {
                    MenPrepSC maux = (MenPrepSC) m;
                    Receptor r = new Receptor(maux.getIP(), maux.getPort(), usr);
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
