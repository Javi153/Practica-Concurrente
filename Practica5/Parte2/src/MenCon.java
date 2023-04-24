import java.io.Serializable;

public class MenCon extends Mensaje {
    private Usuario usr;

    public MenCon(Usuario usr){
        this.usr = usr;
    } //Mensaje de solicitud de inicio de conexion
    @Override
    public Tipos getTipo() {
        return Tipos.M_CONEXION;
    }

    @Override
    public String getOrigen() {
        return usr.getId();
    }

    @Override
    public String getDestino() {
        return null;
    }

    public Usuario getUsr(){
        return usr;
    }
}
