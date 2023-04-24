import java.io.Serializable;

public enum Tipos implements Serializable { //Tipo enumerado para los diferentes mensajes, simplifica la comprension de los switch
    M_CONEXION, M_CONF_CONEXION, M_LISTA_USR, M_CONF_LISTA_USR, M_PEDIR_FICHERO, M_EMITIR_FICHERO,
    M_PREPARADO_CS, M_PREPARADO_SC, M_CERRAR_CONEXION, M_CONF_CERRAR_CONEXION, M_ERROR, M_ACT_USUARIO
}