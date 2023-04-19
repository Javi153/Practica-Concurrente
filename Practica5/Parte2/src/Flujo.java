import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Flujo {
    private ObjectInputStream fin;
    private ObjectOutputStream fout;

    public Flujo(ObjectInputStream fin, ObjectOutputStream fout){
        this.fin = fin;
        this.fout = fout;
    }

    public ObjectInputStream getFin(){
        return fin;
    }

    public ObjectOutputStream getFout(){
        return fout;
    }
}
