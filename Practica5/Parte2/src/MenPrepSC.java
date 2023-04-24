import java.io.Serializable;
import java.net.InetAddress;

public class MenPrepSC extends Mensaje {
    private String origen, destino, ip, peli;
    private int port;

    public MenPrepSC(String origen, String destino, String ip, String peli, int port){
        this.origen = origen;
        this.destino = destino;
        this.ip = ip;
        this.peli = peli;
        this.port = port;
    }
    //Mensaje del servidor que indica al receptor que esta listo

    @Override
    public Tipos getTipo() {
        return Tipos.M_PREPARADO_SC;
    }

    @Override
    public String getOrigen() {
        return origen;
    }

    @Override
    public String getDestino() {
        return destino;
    }

    public String getIP(){
        return ip;
    }

    public String getPelicula(){
        return peli;
    }

    public int getPort(){
        return port;
    }
}
