public class MenActUsuario extends Mensaje{
    private String usr, p;
    private int port;

    public MenActUsuario(String usr, String p, int port){
        this.usr = usr;
        this.p = p;
        this.port = port;
    }
    @Override
    public Tipos getTipo() {
        return Tipos.M_ACT_USUARIO;
    }

    @Override
    public String getOrigen() {
        return usr;
    }

    @Override
    public String getDestino() {
        return null;
    }

    public String getPelicula(){
        return p;
    }

    public int getPort(){
        return port;
    }
}
