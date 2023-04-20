import java.io.Serializable;

public class MenEmitirFich extends Mensaje {
    private Pelicula p;
    private Usuario usr;
    private Flujo f;
    private Puertos puertos;

    public MenEmitirFich(Pelicula p, Usuario usr, Flujo f, Puertos puertos){
        this.p = p;
        this.usr = usr;
        this.f = f;
        this.puertos = puertos;
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

    public Pelicula getPelicula(){
        return p;
    }

    public Puertos getPuertos(){
        return puertos;
    }
}
