package Parte1;

public class Lock1 {
    private Entero turno1, turno2;

    public Lock1(Entero turno1, Entero turno2){
        this.turno1 = turno1;
        this.turno2 = turno2;
    }

    public void takeLock(boolean inc){
        if(inc){
            int t2 = turno2.get();
            turno1.set(t2 + 1);
            while(turno2.get() != 0 && turno1.get() > turno2.get());
        }
        else{
            int t1 = turno1.get();
            turno2.set(t1 + 1);
            while(turno1.get() != 0 && turno2.get() >= turno1.get());
        }
    }

    public void releaseLock(boolean inc){
        if(inc){
            turno1.set(0);
        }
        else{
            turno2.set(0);
        }
    }
}
