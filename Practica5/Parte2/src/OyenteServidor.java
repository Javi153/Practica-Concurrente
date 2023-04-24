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
                l.takeLock(1); //Evitamos entrelazado en el println
                switch (m.getTipo()) {

                    case M_CONF_CONEXION -> {
                        System.out.println("Conexión realizada con éxito");
                    }
                    case M_CONF_LISTA_USR -> { //Escribimos la lista recibida del servidor
                        MenConfList aux = (MenConfList) m;
                        System.out.println(aux.getLista());
                    }
                    case M_EMITIR_FICHERO -> {
                        MenEmitirFich aux = (MenEmitirFich) m;
                        System.out.println("Emitiendo fichero " + aux.getPelicula());
                        Emisor e = new Emisor(usr.getInfo().get(aux.getPelicula()), aux.getPuertos());
                        e.start(); //Iniciamos la emision de la pelicula especificada por el mensaje
                        fout.writeObject(new MenPrepCS(usr.getId(), aux.getDestino(), InetAddress.getLocalHost().getHostAddress(), aux.getPelicula(), aux.getPuertos()));
                        //Confirmamos la emision al servidor
                    }
                    case M_PREPARADO_SC -> {
                        MenPrepSC maux = (MenPrepSC) m;
                        System.out.println("Recibiendo el fichero " + maux.getPelicula() + " de " + maux.getOrigen());
                        Receptor r = new Receptor(maux.getIP(), maux.getPort(), usr, new Flujo(fin, fout)); //Iniciamos la recepcion del fichero
                        r.start();
                    }
                    case M_CONF_CERRAR_CONEXION -> {
                        System.out.println("Conexión cerrada con éxito");
                        terminar = true;
                    }
                    case M_ERROR -> {
                        MenError aux = (MenError) m; //Recibimos mensaje de errror y escribimos el error por pantalla
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
