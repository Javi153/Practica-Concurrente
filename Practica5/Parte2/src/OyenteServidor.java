import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class OyenteServidor extends Thread{
    private Usuario usr; //Usuario actual
    private ObjectInputStream fin;
    private ObjectOutputStream fout; //Flujos de entrada y salida
    private miLock l; //Lock para evitar entrelazado en el println
    public OyenteServidor(Usuario usr, ObjectInputStream fin, ObjectOutputStream fout, miLock l){
        this.usr = usr;
        this.fin = fin;
        this.fout = fout;
        this.l = l;
    }

    public void run(){
        Mensaje m;
        try {
            fout.writeObject(new MenCon(usr)); //Al iniciar pedimos conexion con el servidor
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        boolean terminar = false;
        while(!terminar){
            try {
                m = (Mensaje) fin.readObject();
                switch (m.getTipo()) {

                    case M_CONF_CONEXION -> {
                        l.takeLock(1);
                        System.out.println("Conexión realizada con éxito");
                        l.releaseLock(1);
                    }
                    case M_CONF_LISTA_USR -> { //Escribimos la lista recibida del servidor
                        MenConfList aux = (MenConfList) m;
                        l.takeLock(1);
                        System.out.println(aux.getLista());
                        l.releaseLock(1);
                    }
                    case M_EMITIR_FICHERO -> {
                        MenEmitirFich aux = (MenEmitirFich) m;
                        Emisor e = new Emisor(usr.getInfo().get(aux.getPelicula()), aux.getPuertos());
                        e.start(); //Iniciamos la emision de la pelicula especificada por el mensaje
                        fout.writeObject(new MenPrepCS(usr.getId(), aux.getDestino(), InetAddress.getLocalHost().getHostAddress(), aux.getPelicula(), aux.getPuertos()));
                        l.takeLock(1);
                        System.out.println("Emitiendo fichero " + aux.getPelicula());
                        l.releaseLock(1);
                        //Confirmamos la emision al servidor
                    }
                    case M_PREPARADO_SC -> {
                        MenPrepSC maux = (MenPrepSC) m;
                        Receptor r = new Receptor(maux.getIP(), maux.getPort(), usr, new Flujo(fin, fout)); //Iniciamos la recepcion del fichero
                        r.start();
                        l.takeLock(1);
                        System.out.println("Recibiendo el fichero " + maux.getPelicula() + " de " + maux.getOrigen());
                        l.releaseLock(1);
                    }
                    case M_CONF_CERRAR_CONEXION -> {
                        terminar = true;
                        l.takeLock(1);
                        System.out.println("Conexión cerrada con éxito");
                        l.releaseLock(1);
                    }
                    case M_ERROR -> {
                        MenError aux = (MenError) m; //Recibimos mensaje de errror y escribimos el error por pantalla
                        l.takeLock(1);
                        System.out.println(aux.getMen());
                        l.releaseLock(1);
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
