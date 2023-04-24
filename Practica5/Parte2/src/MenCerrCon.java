import java.io.Serializable;

public class MenCerrCon extends Mensaje {
    private String usr;

    public MenCerrCon(String usr){
        this.usr = usr;
    } //Mensaje de solicitud de cierre de conexion
    @Override
    public Tipos getTipo() {
        return Tipos.M_CERRAR_CONEXION;
    }

    @Override
    public String getOrigen() {
        return usr;
    }

    @Override
    public String getDestino() {
        return null;
    }
}
