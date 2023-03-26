public class Storage implements Almacen {
    private int[] productos;
    private int cap, nw, nr;

    public Storage(int n){
        productos = new int[n];
        cap = n;
    }

    public synchronized void requestRead(){
        while(nw > 0){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        nr++;
    }

    public synchronized void releaseRead(){
        nr--;
        if(nr == 0){
            notifyAll();
        }
    }

    public synchronized void requestWrite(){
        while(nw > 0 || nr > 0){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        nw++;
    }

    public synchronized void releaseWrite(){
        nw--;
        notifyAll();
    }

    @Override
    public void escribir(int producto, int pos) {
        productos[pos % cap] = producto;
    }

    @Override
    public int leer(int pos) {
        return productos[pos % cap];
    }

    public int getCap(){
        return cap;
    }
}
