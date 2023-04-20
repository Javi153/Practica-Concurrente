import java.io.Serializable;

public class MenError extends Mensaje {
    @Override
    public Tipos getTipo() {
        return Tipos.M_ERROR;
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
