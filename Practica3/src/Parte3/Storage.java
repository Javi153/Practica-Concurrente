package Parte3;

public class Storage implements Almacen {
    private int[] productos;
    private int ini, fin;

    public Storage(int n){
        productos = new int[n];
        ini = 0;
        fin = 0;
    }
    @Override
    public void almacenar(int producto) {
        productos[fin] = producto;
        fin = (fin + 1) % productos.length;
    }

    @Override
    public int extraer() {
        ini = (ini + 1) % productos.length;
        return productos[ini];
    }
}
