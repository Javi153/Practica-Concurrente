import java.io.Serializable;
import java.net.InetAddress;

public class MenPrepSC extends Mensaje {
    private String origen, destino;
    private InetAddress ip;
    private int port;

    public MenPrepSC(String origen, String destino, InetAddress ip, int port){
        this.origen = origen;
        this.destino = destino;
        this.ip = ip;
        this.port = port;
    }

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

    public InetAddress getIP(){
        return ip;
    }

    public int getPort(){
        return port;
    }
}
