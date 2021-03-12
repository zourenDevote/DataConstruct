package Thread;

import java.util.stream.IntStream;



public class ThrMain {

    public static void main(String[] args){
        //main函数开始
        System.out.println("Thread main is start");
        Thr th = new Thr();
        th.setDaemon(true);  //设置th为守护线程
        th.start();
        //main函数结束
        System.out.println("Thread main is end");
    }
}
