package Thread;

import java.util.concurrent.TimeUnit;

public class TryConcurrency {


    public static void main(String[] args){
        new Thread(TryConcurrency::browsNews).start();
        enjoyMusic();
    }

    /**
     * ø¥–¬Œ≈
     */
    private static void browsNews(){
        for(;;){
            System.out.println("Uh-huh, the good news");
            sleep(1);
        }
    }

    /**
     * Ã˝“Ù¿÷
     */
    private static void enjoyMusic(){
        for(;;){
            System.out.println("Uh-huh,the nice music");
            sleep(1);
        }
    }

    private static void sleep(int i) {
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
