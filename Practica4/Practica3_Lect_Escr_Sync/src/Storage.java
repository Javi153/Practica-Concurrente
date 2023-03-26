public class Storage implements Almacen {
    private int[] productos;
    private int cap;

    public Storage(int n){
        productos = new int[n];
        cap = n;
    }

    @Override
    public synchronized void escribir(int producto, int pos) {
        productos[pos % cap] = producto;
    }

    @Override
    public synchronized int leer(int pos) {
        return productos[pos % cap];
    }

    public int getCap(){
        return cap;
    }
}
