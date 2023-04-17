import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class OyenteServidor extends Thread{
    private Socket s;
    public OyenteServidor(Socket s){
        this.s = s;
    }

    public void run(){
        while(true){
            ObjectOutputStream fout;
            ObjectInputStream fin;
            Mensaje m;
            try {
                fout = new ObjectOutputStream(s.getOutputStream());
                fin = new ObjectInputStream(s.getInputStream());
                m = (MensajeBasico) fin.readObject();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            switch(m.getTipo()){

                case M_CONEXION -> {
                }
                case M_CONF_CONEXION -> {
                }
                case M_LISTA_USR -> {
                }
                case M_CONF_LISTA_USR -> {
                }
                case M_PEDIR_FICHERO -> {
                }
                case M_EMITIR_FICHERO -> {
                }
                case M_PREPARADO_CS -> {
                }
                case M_PREPARADO_SC -> {
                }
                case M_CERRAR_CONEXION -> {
                }
                case M_CONF_CERRAR_CONEXION -> {
                }
            }
        }
    }
}
