package dining_savages_semaphore;

public class Tencere {
    private int available; // tencere içindeki porsiyona göre artan ya da azalan değişkenimiz.
    private int m;

    public Tencere(int m){
        this.available = 0;
        this.m = m;
    }

    public int getFood() {
        return this.available -= 1;
    }

    public void yeniden_doldurma(int m) {
        this.available += m;
    }

    public boolean hasFood() {
        if(this.available >0)
            return true;
        return false;
    }

    public int getM() {
        return m;
    }

    public int getAvailable() {
        return this.available;
    }

}
