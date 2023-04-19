import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class OyenteServidor extends Thread{
    private Socket s;
    private Usuario usr;
    public OyenteServidor(Socket s, Usuario usr){
        this.s = s;
        this.usr = usr;
    }

    public void run(){
        ObjectOutputStream fout;
        ObjectInputStream fin;
        Mensaje m;
        try {
            fout = new ObjectOutputStream(s.getOutputStream());
            fin = new ObjectInputStream(s.getInputStream());
            fout.writeObject(new MenCon(usr));
            fout.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while(true){
            try {
                m = (Mensaje) fin.readObject();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            switch(m.getTipo()){

                case M_CONF_CONEXION -> {
                    System.out.println("Conexión realizada con éxito");
                }
                case M_CONF_LISTA_USR -> {
                    MenConfList aux = (MenConfList) m;
                    for(Usuario usr : aux.getLista()){
                        System.out.println(usr.toString());
                    }
                }
                case M_EMITIR_FICHERO -> {
                }
                case M_PREPARADO_SC -> {
                }
                case M_CONF_CERRAR_CONEXION -> {
                }
            }
        }
    }
}
