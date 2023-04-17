public abstract class Mensaje {
    public abstract Tipos getTipo(); //Veo usar un enum mejor a la hora de hacer switches

    public abstract String getOrigen();

    public abstract String getDestino();
}
