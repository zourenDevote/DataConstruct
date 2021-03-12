package Thread;

public class Thr extends Thread{

    @Override
    public void run(){
        while (true){
            try {
                System.out.println("thr is doing...");
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
