public class MenFich extends Mensaje{
    private Pelicula p;
    public MenFich(Pelicula p){
        this.p = p;
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
}
