import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Flujo {
    private String id;
    private ObjectInputStream fin;
    private ObjectOutputStream fout;

    public Flujo(String id, ObjectInputStream fin, ObjectOutputStream fout){
        this.id = id;
        this.fin = fin;
        this.fout = fout;
    }

    public String getId(){
        return id;
    }

    public ObjectInputStream getFin(){
        return fin;
    }

    public ObjectOutputStream getFout(){
        return fout;
    }
}
