public class JobUtil {
    private int time;

    public void espera() {
        int tempoAtraso = 1;
        try {
            Thread.sleep(tempoAtraso * 1000);
            time = time + tempoAtraso;
        } catch (InterruptedException e) {
        }

    }

    public int getTime() {
        return this.time - 1;
    }

}