import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.*;
import java.util.HashSet;
import java.util.Set;

public class Cliente {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket sc = new Socket(InetAddress.getByName("192.168.56.1"), 999);
        ObjectOutputStream foutC = new ObjectOutputStream(sc.getOutputStream());
        foutC.flush();
        ObjectInputStream finC = new ObjectInputStream(sc.getInputStream());
        Set<String> s = new HashSet<>();
        for(int i = 0; i < 9; ++i) {
            s.add("algo " + i);
            for(String st : s)
                System.out.println(st);
            foutC.writeObject(s);
            foutC.reset();
        }
    }
}
