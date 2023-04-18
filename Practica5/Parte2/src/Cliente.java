import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.ArrayList;

public class Cliente {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Inserte su nombre de usuario");
        String id_usr = sc.nextLine();
        InetAddress ip = InetAddress.getLocalHost();
        Socket s = new Socket(ip, 999);
        ObjectInputStream finC = new ObjectInputStream(s.getInputStream());
        ObjectOutputStream foutC = new ObjectOutputStream(s.getOutputStream());
        Usuario usr = new Usuario(id_usr, ip, new ArrayList<Pelicula>());
        foutC.writeObject(usr);
        foutC.flush();
        OyenteServidor o = new OyenteServidor(s);
        o.start();
        //menu

        //cerrar conexion CON CLOSE
        s.close();
    }
}
