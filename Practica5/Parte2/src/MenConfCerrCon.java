public class MenConfCerrCon extends Mensaje{
    @Override
    public Tipos getTipo() {
        return Tipos.M_CONF_CERRAR_CONEXION;
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
