import java.io.Serializable;

public class MenError extends Mensaje {
    private String men;

    public MenError(String s){
        men = s;
    }
    @Override
    public Tipos getTipo() {
        return Tipos.M_ERROR;
    }

    public String getMen(){
        return men;
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
