public class MenEmitirFich extends Mensaje{
    private Pelicula p;
    private Usuario usr;
    private Flujo f;

    public MenEmitirFich(Pelicula p, Usuario usr, Flujo f){
        this.p = p;
        this.usr = usr;
        this.f = f;
    }

    @Override
    public Tipos getTipo() {
        return Tipos.M_EMITIR_FICHERO;
    }

    @Override
    public String getOrigen() {
        return null;
    }

    @Override
    public String getDestino() {
        return usr.getId();
    }
}
