import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.*;
public class Cliente {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket sc = new Socket(InetAddress.getByName("192.168.56.1"), 988);
        ObjectOutputStream foutC = new ObjectOutputStream(sc.getOutputStream());
        foutC.writeObject(new String("Hola q ase"));
        foutC.flush();
        sc.close();
    }
}
