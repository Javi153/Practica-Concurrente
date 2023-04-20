import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.HashSet;
import java.util.Set;

public class Servidor {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket ss = new ServerSocket(999);
        System.out.println(InetAddress.getLocalHost());
        System.out.println(ss.getLocalPort());
        Socket s = ss.accept();
        System.out.println("Conexion establecida");
        ObjectInputStream finC = new ObjectInputStream(s.getInputStream());
        ObjectOutputStream foutC = new ObjectOutputStream(s.getOutputStream());
        for(int i = 0; i < 9; ++i) {
            Set<String> m = (HashSet<String>) finC.readObject();
            for(String st : m)
                System.out.println(st);
        }
        s.close();
        ss.close();
    }
}