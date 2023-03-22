public class miMonitorSync {
    private int e;

    public miMonitorSync(){
        e = 0;
    }
    public synchronized void op(boolean inc){
        if(inc){
            e++;
        }
        else{
            e--;
        }
    }

    public int get(){
        return e;
    }
}
