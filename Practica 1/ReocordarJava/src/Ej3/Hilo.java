package Ej3;

public class Hilo extends Thread{
    private int[] M1;
    private int[][] M2;
    private int[] M3;
    private int N;

    public Hilo(int[] M1, int[][] M2, int M3[], int N){
        this.M1 = M1;
        this.M2 = M2;
        this.M3 = M3;
        this.N = N;
    }

    public void run(){
        for(int i = 0; i < N; ++i){
            int s = 0;
            for(int j = 0; j < N; ++j){
                s += M1[j] * M2[j][i];
            }
            M3[i] = s;
        }
    }
}

