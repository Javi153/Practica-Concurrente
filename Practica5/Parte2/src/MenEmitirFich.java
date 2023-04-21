
public class MenEmitirFich extends Mensaje {
    private String p, usr;
    private Flujo f;
    private int puertos;

    public MenEmitirFich(String p, String usr, int puertos){
        this.p = p;
        this.usr = usr;
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
        return usr;
    }

    public String getPelicula(){
        return p;
    }

    public int getPuertos(){
        return puertos;
    }
}
