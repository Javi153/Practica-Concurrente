public class MensajeBasico extends Mensaje{
    private Tipos tipo;
    private String origen, destino;

    public MensajeBasico(Tipos tipo, String origen, String destino){
        this.tipo = tipo;
        this.origen = origen;
        this.destino = destino;
    }

    @Override
    public Tipos getTipo() {
        return tipo;
    }

    @Override
    public String getOrigen() {
        return origen;
    }

    @Override
    public String getDestino() {
        return destino;
    }
}
