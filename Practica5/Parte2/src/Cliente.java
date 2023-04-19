import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Inserte su nombre de usuario: ");
        String id_usr = sc.nextLine();
        InetAddress ip = InetAddress.getLocalHost();
        Socket s = new Socket(ip, 999);
        ObjectInputStream finC = new ObjectInputStream(s.getInputStream());
        ObjectOutputStream foutC = new ObjectOutputStream(s.getOutputStream());
        Usuario usr = new Usuario(id_usr, ip, new HashSet<Pelicula>());
        OyenteServidor o = new OyenteServidor(s, usr);
        o.start();
        //menu
        boolean terminar = false;
        Integer eleccion = -1;
        String st = "";
        while(!terminar){
            while(eleccion <= 0 || eleccion > 3){
                eleccion = sc.nextInt();
                if(eleccion == 2){
                  st = sc.nextLine();
                }
            }
            switch(eleccion){
                case 1 -> {foutC.writeObject(new MenList());}
                case 2 -> {foutC.writeObject(new MenPedirFich(new Pelicula(st), usr));}
                case 3 -> {foutC.writeObject(new MenCerrCon(usr));}
            }
        }
        try {
            o.wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        s.close();
    }
}
