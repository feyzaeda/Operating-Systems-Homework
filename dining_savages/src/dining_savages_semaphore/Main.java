package dining_savages_semaphore;

import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {

        int m = 3;  // porsiyon adedimiz
        Semaphore eat = new Semaphore(0);
        Semaphore mutex = new Semaphore(1);
        Semaphore yeniden_doldurma = new Semaphore(0);

        Tencere tencere = new Tencere(m);
        Cook cook = new Cook(yeniden_doldurma, eat, tencere);

        // 6 adet vahşimiz bulunmakta.
        Savage s0 = new Savage(0, mutex, eat, yeniden_doldurma, tencere);
        Savage s1 = new Savage(1, mutex, eat, yeniden_doldurma, tencere);
        Savage s2 = new Savage(2, mutex, eat, yeniden_doldurma, tencere);
        Savage s3 = new Savage(3, mutex, eat, yeniden_doldurma, tencere);
        Savage s4 = new Savage(4, mutex, eat, yeniden_doldurma, tencere);
        Savage s5 = new Savage(5, mutex, eat, yeniden_doldurma, tencere);


        cook.start();
        s0.start();
        s1.start();
        s2.start();
        s3.start();
        s4.start();
        s5.start();

    }

}
