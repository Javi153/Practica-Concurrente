import java.io.Serializable;

public class MenConfCon extends Mensaje {
    @Override
    public Tipos getTipo() {
        return Tipos.M_CONF_CONEXION;
    } //Mensaje de confirmacion de inicio de conexion destinado al cliente

    @Override
    public String getOrigen() {
        return null;
    }

    @Override
    public String getDestino() {
        return null;
    }
}
