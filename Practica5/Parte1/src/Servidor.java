import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
public class Servidor {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket ss = new ServerSocket(999);
        System.out.println(InetAddress.getLocalHost());
        System.out.println(ss.getLocalPort());
        Socket s = ss.accept();
        System.out.println("Conexion establecida");

        ObjectInputStream finC = new ObjectInputStream(s.getInputStream());
        Object m = finC.readObject();

        ObjectOutputStream foutC = new ObjectOutputStream(s.getOutputStream());
        System.out.println(m);
        foutC.flush();
        ss.close();
    }
}