package Ej1;
public class Hilo extends Thread{
    int id;
    long T;

    public Hilo(int id, long T){
        this.id = id;
        this.T = T;
    }

    public void run(){

        System.out.println(id);
        try {
            Thread.sleep(T);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(id);
    }
}
