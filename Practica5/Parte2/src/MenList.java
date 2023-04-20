import java.io.Serializable;
import java.util.ArrayList;

public class MenList extends Mensaje implements Serializable{

    public MenList(){
    }

    @Override
    public Tipos getTipo() {
        return Tipos.M_LISTA_USR;
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
