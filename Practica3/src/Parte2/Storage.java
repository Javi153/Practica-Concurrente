package Parte2;

public class Storage implements Almacen{
    private int n;

    public Storage(){
        n = 0;
    }
    @Override
    public void almacenar(int producto) {
        n = producto;
    }

    @Override
    public int extraer() {
        return n;
    }
}
