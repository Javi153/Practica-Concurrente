import java.io.Serializable;

public abstract class Mensaje implements Serializable {
    public abstract Tipos getTipo();
    //Clase abstracta de mensaje que se enviara. Implementa serializable para poder ser enviado por sockets

    public abstract String getOrigen();

    public abstract String getDestino();
}
