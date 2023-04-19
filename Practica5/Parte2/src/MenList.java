import java.util.ArrayList;

public class MenList extends Mensaje{

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
