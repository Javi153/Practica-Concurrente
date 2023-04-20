import java.io.Serializable;

public abstract class Mensaje implements Serializable {
    public abstract Tipos getTipo(); //Veo usar un enum mejor a la hora de hacer switches

    public abstract String getOrigen();

    public abstract String getDestino();
}
