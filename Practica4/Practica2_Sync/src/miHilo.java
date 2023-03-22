public class miHilo extends Thread{
    private int id;
    private miMonitorSync mon;
    private boolean inc;
    private int N;

    public miHilo(int id, miMonitorSync mon, boolean inc, int N){
        this.id = id;
        this.mon = mon;
        this.inc = inc;
        this.N = N;
    }

    public void run(){
            for(int i = 0; i < N; ++i) {
                mon.op(inc);
            }
    }
}
