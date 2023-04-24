import java.io.Serializable;
import java.net.InetAddress;

public class MenPrepCS extends Mensaje {
    private String origen, destino, ip, peli;
    private int port;

    public MenPrepCS(String origen, String destino, String ip, String peli, int port){
        this.origen = origen;
        this.destino = destino;
        this.ip = ip;
        this.peli = peli;
        this.port = port;
    }
    //Mensaje que indica al servidor que el emisor esta preparado

    @Override
    public Tipos getTipo() {
        return Tipos.M_PREPARADO_CS;
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
