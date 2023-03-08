package Parte1;

public class Entero {
    private volatile int n;

    public Entero(){
        n = 0;
    }

    public void incrementar(){
        n++;
    }

    public void decrementar(){
        n--;
    }

    public int get(){
        return n;
    }

    public void set(int n){
        this.n = n;
    }
}
