public class Storage implements Almacen {
    private int[] productos;
    private int ini, fin;
    private volatile int cap;

    public Storage(int n){
        productos = new int[n];
        ini = 0;
        fin = 0;
        cap = 0;
    }
    @Override
    public synchronized void almacenar(int producto) throws InterruptedException {
        while(cap == productos.length){
            wait();
        }
        productos[fin] = producto;
        fin = (fin + 1) % productos.length;
        cap++;
        notifyAll();
    }

    @Override
    public synchronized int extraer() throws InterruptedException {
        while(cap == 0){
            wait();
        }
        int res = productos[ini];
        ini = (ini + 1) % productos.length;
        cap--;
        notifyAll();
        return res;
    }
}

