import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Scanner;
//Practica realizada por Javier Jimenez Arenas
public class Cliente {

    private final static InetAddress serverIP;

    static {
        try {
            serverIP = InetAddress.getByName("172.25.240.1");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Inserte su nombre de usuario: ");
        String id_usr = sc.nextLine(); //Guardamos el nombre de usuario, supondremos que todos son distintos sin comprobarlo
        InetAddress ip = InetAddress.getLocalHost(); //Guardamos la IP del cliente
        Socket s = new Socket(serverIP, 1025); //Establecemos conexion con el servidor
        ObjectOutputStream foutC = new ObjectOutputStream(s.getOutputStream()); //Creamos flujo de salida
        foutC.flush();
        ObjectInputStream finC = new ObjectInputStream(s.getInputStream()); //Creamos flujo de entrada
        HashMap<String, Pelicula> hm = new HashMap<>(); //Lista de peliculas que tiene el cliente
        hm.put("Star Wars", new Pelicula("Star Wars"));
        Usuario usr = new Usuario(id_usr, ip, hm); //Creamos el usuario con la informacion que tenemos
        miLock l = new miLockTicket(2); //Crearemos un lock para agrupar los println por bloques
        OyenteServidor o = new OyenteServidor(usr, finC, foutC, l);
        o.start();
        boolean terminar = false;
        int eleccion;
        String st = "";
        try{
        while(!terminar){ //menu
            l.takeLock(0);//tomamos el lock para que no se entrelacen lineas de texto en el menu
            System.out.println("=================P2P MOVIESHARE========================");
            System.out.println("Elija entre las siguientes opciones escribiendo el numero correspondiente: ");
            System.out.println("    1. Mostrar lista de usuarios disponibles");
            System.out.println("    2. Pedir una pelicula");
            System.out.println("    3. Salir");
            System.out.println("=======================================================");
            l.releaseLock(0);
            do{
                l.takeLock(0); //Tambien esperamos a q el usuario escriba la opcion correctamente
                System.out.println("Opción elegida(1,2,3): ");
                eleccion = sc.nextInt();
                sc.nextLine();
                if(eleccion < 1 || eleccion > 3)
                    System.out.println("Opción incorrecta, vuelva a intentarlo");
                l.releaseLock(0);
            }while(eleccion < 1 || eleccion > 3);
            switch (eleccion) {
                case 1 -> {
                    foutC.writeObject(new MenList()); //Pedimos la lista de usuarios y sus peliculas
                }
                case 2 -> {
                    l.takeLock(0);
                    System.out.println("Inserte el nombre de la pelicula a descargar: "); //Supondremos que el usuario pedira una pelicula que no tuviese antes
                                                                                          //En caso contrario se abrirá la conexión y se actualizará su película
                                                                                          //siempre que sea posible
                    l.releaseLock(0);
                    st = sc.nextLine();
                    foutC.writeObject(new MenPedirFich(st, usr.getId())); //Pedimos la pelicula especificada
                }
                case 3 -> {
                    terminar = true;
                    foutC.writeObject(new MenCerrCon(usr.getId())); //Mandamos mensaje de cierre de conexion
                }
            }
            try{
                Thread.sleep(500); //Hacemos que el menu espere medio segundo por posible respuesta del oyenteservidor
            }
            catch(InterruptedException e){
                System.out.println("Cliente interrumpido");
            }
        }
        o.join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finC.close();
        foutC.close();
        s.close(); //Cerramos flujo y socket
    }
}
