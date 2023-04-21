import java.io.Serializable;
import java.net.InetAddress;

public class MenPrepCS extends Mensaje {
    private String origen, destino, ip;
    private int port;

    public MenPrepCS(String origen, String destino, String ip, int port){
        this.origen = origen;
        this.destino = destino;
        this.ip = ip;
        this.port = port;
    }

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

    public int getPort(){
        return port;
    }
}
