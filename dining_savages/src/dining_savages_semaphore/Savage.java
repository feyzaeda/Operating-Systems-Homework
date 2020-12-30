/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dining_savages_semaphore;

import java.util.concurrent.Semaphore;
import java.lang.Thread;

public class Savage extends Thread{
    private Semaphore mutex, eat, yeniden_doldurma;
    private Tencere tencere;
    private int id;

    public Savage(int id, Semaphore mutex, Semaphore eat, Semaphore yeniden_doldurma, Tencere tencere){
        this.id = id;
        this.mutex = mutex;
        this.eat = eat;
        this.yeniden_doldurma = yeniden_doldurma;
        this.tencere = tencere;
    }

    // Bu kısımda vahşi yemek yedikten sonra kaç porsiyon yemek kaldığını belirten metottur.
    // En son mutex 'i serbest bırakarak  diğer vahşiden geriye kalan porsiyon yazılır.
    public void eat() throws InterruptedException {
        int foodLeft = this.tencere.getFood();
        System.out.println("Vahşi " + this.id + " yemek yiyor. Tencerede " + foodLeft + " porsiyon yemek kaldı.");
        this.mutex.release();
        Thread.sleep(1000);
    }

    public void run() {
        while(true) {
            try {
                this.mutex.acquire();

                // Eğer tencerede yemek yoksa "Vahşi yemek bulamadı çıktısını verir!"
                // Release ile yeniden doldurma serbest bırakılır ve eat tutulur.
                if(!this.tencere.hasFood()) {
                    System.out.println("Vahşi " + this.id + " yemek bulamadı!");

                    this.yeniden_doldurma.release();
                    this.eat.acquire();
                }

                this.eat();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}