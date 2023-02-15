package Ej3;

public class Main {
    private static final int N = 4;
    public static void main(String[] args) throws InterruptedException {
        int[][] M1 = {{7,8,1,2}, {8,6,1,4}, {9,3,2,1}, {6,2,1,4}};
        int[][] M2 = {{4,5,2,6},{7,1,2,5},{9,6,3,5},{4,1,8,5}};
        int[][] M3 = new int[N][N];

        Hilo[] hilos = new Hilo[N];

        for(int i = 0; i < N; ++i){
            hilos[i] = new Hilo(M1[i], M2, M3[i], N);
            hilos[i].start();
        }

        for(int i = 0; i < N; ++i){
            hilos[i].join();
        }

        for(int i = 0; i < N; ++i){
            for(int j = 0; j < N; ++j){
                System.out.print(M3[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
