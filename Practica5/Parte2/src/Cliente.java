import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Scanner;

public class Cliente {

    private final static InetAddress serverIP;

    static {
        try {
            serverIP = InetAddress.getByName("192.168.56.1");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Inserte su nombre de usuario: ");
        String id_usr = sc.nextLine();
        InetAddress ip = InetAddress.getLocalHost();
        Socket s = new Socket(serverIP, 1025);
        ObjectOutputStream foutC = new ObjectOutputStream(s.getOutputStream());
        foutC.flush();
        ObjectInputStream finC = new ObjectInputStream(s.getInputStream());
        Usuario usr = new Usuario(id_usr, ip, new HashSet<Pelicula>());
        OyenteServidor o = new OyenteServidor(s, usr, finC, foutC);
        o.start();
        //menu
        boolean terminar = false;
        int eleccion = -1;
        String st = "";
        try{
        while(!terminar){
            System.out.println("Elija entre las siguientes opciones escribiendo el numero correspondiente: ");
            System.out.println("1. Mostrar lista de usuarios disponibles");
            System.out.println("2. Pedir una pelicula");
            System.out.println("3. Salir");
            while(eleccion < 1 || eleccion > 3){
                eleccion = sc.nextInt();
                sc.nextLine();
            }
            switch (eleccion) {
                case 1 -> {
                    foutC.writeObject(new MenList());
                }
                case 2 -> {
                    System.out.println("Inserte el nombre de la pelicula a descargar: ");
                    st = sc.nextLine();
                    foutC.writeObject(new MenPedirFich(new Pelicula(st), usr));
                }
                case 3 -> {
                    terminar = true;
                    foutC.writeObject(new MenCerrCon(usr));
                }
            }
            eleccion = -1;
        }
        o.join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finC.close();
        foutC.close();
        s.close();
    }
}
