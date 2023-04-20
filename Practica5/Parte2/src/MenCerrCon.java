import java.io.Serializable;

public class MenCerrCon extends Mensaje {
    private Usuario usr;

    public MenCerrCon(Usuario usr){
        this.usr = usr;
    }
    @Override
    public Tipos getTipo() {
        return Tipos.M_CERRAR_CONEXION;
    }

    @Override
    public String getOrigen() {
        return null;
    }

    @Override
    public String getDestino() {
        return null;
    }

    public Usuario getUsr(){
        return usr;
    }
}
