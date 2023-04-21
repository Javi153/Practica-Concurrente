import java.io.Serializable;
import java.util.Set;

public class MenConfList extends Mensaje implements Serializable{
    private String tUsr;

    public MenConfList(String tUsr){
        this.tUsr = tUsr;
    }

    public String getLista(){
        return tUsr;
    }

    @Override
    public Tipos getTipo() {
        return Tipos.M_CONF_LISTA_USR;
    }

    @Override
    public String getOrigen() {
        return null;
    }

    @Override
    public String getDestino() {
        return null;
    }
}
