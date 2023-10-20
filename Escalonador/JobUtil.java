public class JobUtil {
    private int time;
    private int ciclo=0;

  public JobUtil(int time) {
        this.time = time;
    }

    public void espera() {
        try {
            Thread.sleep(time * 1000);
            ciclo = ciclo + 1;
        } catch (InterruptedException e) {
        }

    }

    public int getCiclo() {
        return this.ciclo - 1;
    }

}