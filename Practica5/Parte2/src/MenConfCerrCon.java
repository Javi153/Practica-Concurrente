import java.io.Serializable;

public class MenConfCerrCon extends Mensaje {
    @Override
    public Tipos getTipo() {
        return Tipos.M_CONF_CERRAR_CONEXION;
    } //Mensaje de confirmacion de cierre de conexion destinado al cliente

    @Override
    public String getOrigen() {
        return null;
    }

    @Override
    public String getDestino() {
        return null;
    }
}
