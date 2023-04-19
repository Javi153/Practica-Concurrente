public class MenConfCon extends Mensaje{
    @Override
    public Tipos getTipo() {
        return Tipos.M_CONF_CONEXION;
    }

    @Override
    public String getOrigen() {
        return null;
    }

    @Override
    public String getDestino() {
        return null;
    }
}
