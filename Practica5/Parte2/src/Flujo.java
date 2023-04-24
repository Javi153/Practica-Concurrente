import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Flujo implements Serializable { //Clase que contiene los flujos de entrada y salida
    private ObjectInputStream fin;
    private ObjectOutputStream fout;

    public Flujo(ObjectInputStream fin, ObjectOutputStream fout){ //A diferencia de otras clases no incluimos constructor de copia
                                                                  //pues esto puede dar problemas al crear varios flujos
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
