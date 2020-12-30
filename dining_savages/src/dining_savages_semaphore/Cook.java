package dining_savages_semaphore;
import java.util.concurrent.Semaphore;

public class Cook extends Thread {
    private Semaphore yeniden_doldurma, eat;
    private Tencere tencere;

    public Cook(Semaphore yeniden_doldurma, Semaphore eat, Tencere tencere){
        this.yeniden_doldurma = yeniden_doldurma;
        this.eat = eat;
        this.tencere = tencere;
    }

    // Tencerede yemek kalmazsa aşçının uyandırılıp yemek yapması gereken kısım.
    public void yeniden_doldurma(int m) {
        System.out.println("Aşçı uyandı!");
        this.tencere.yeniden_doldurma(m);
        System.out.println("Tencere tekrar yiyecek ile dolu.");
    }


    // Bu kısımda yeniden_doldurma elde edilip(acquire) tencereye M porsiyon yemek koyulduğu
    // ve en son release ile serbest bırakıldığı kısım.
    public void run() {
        while(true) {
            try {
                this.yeniden_doldurma.acquire();
                this.yeniden_doldurma(this.tencere.getM());
                this.eat.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
