public class MenPedirFich extends Mensaje{
    private Pelicula p;
    private Usuario usr;
    public MenPedirFich(Pelicula p, Usuario usr){
        this.p = p;
        this.usr = usr;
    }
    @Override
    public Tipos getTipo() {
        return Tipos.M_PEDIR_FICHERO;
    }

    @Override
    public String getOrigen() {
        return null;
    }

    @Override
    public String getDestino() {
        return null;
    }

    public Pelicula getFichero(){
        return p;
    }

    public Usuario getUsr(){
        return usr;
    }
}
