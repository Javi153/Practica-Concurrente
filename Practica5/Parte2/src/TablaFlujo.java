import java.util.HashMap;

public class TablaFlujo extends MonitorCond{
    private HashMap<String, Flujo> tabla;

    public TablaFlujo(){ //Tabla de flujos de usuario implementada como monitor con Lock y Condcional
        tabla = new HashMap<>();
        nw = 0;
        nr = 0;
    }

    public void write(String s, Flujo t, boolean remove){ //Implementamos el write para que sirva tanto para añadir flujo como para borrarlo
        requestWrite();
        if(!remove) {
            tabla.put(s, t);
        }
        else{
            tabla.remove(s);
        }
        releaseWrite();
    }

    public void write(String s, Flujo t){
        write(s, t, false);
    } //Si no se especifica se entiende que se añade

    public void remove(String s){
        write(s, null, true);
    } //Añadimos facilidad al usuario renombrando el write/remove:true como remove

    public Flujo read(String s) {
        requestRead();
        Flujo f = tabla.get(s);
        releaseRead();
        return f;
    }
}
