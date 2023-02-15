package Ej1;

import java.util.Random;

public class Main {
    static final int N = 10;

    public static void main(String[] args) throws InterruptedException {
        Hilo[] hilo = new Hilo[N];
        Random r = new Random();
        for (int i = 0; i < N; ++i) {
            hilo[i] = new Hilo(i, r.nextLong(1000));
            hilo[i].start();
        }

        for (int i = 0; i < N; ++i) {
            hilo[i].join();
        }
        System.out.println("La ejecucion de los hilos ha finalizado");
    }
}