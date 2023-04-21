import java.io.Serializable;

public class MenPedirFich extends Mensaje {
    private String p;
    private String usr;
    public MenPedirFich(String p, String usr){
        this.p = p;
        this.usr = usr;
    }
    @Override
    public Tipos getTipo() {
        return Tipos.M_PEDIR_FICHERO;
    }

    @Override
    public String getOrigen() {
        return usr;
    }

    @Override
    public String getDestino() {
        return null;
    }

    public String getFichero(){
        return p;
    }
}
