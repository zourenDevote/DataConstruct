package Thread;

import java.util.stream.IntStream;



public class ThrMain {

    public static void main(String[] args){
        //main������ʼ
        System.out.println("Thread main is start");
        Thr th = new Thr();
        th.setDaemon(true);  //����thΪ�ػ��߳�
        th.start();
        //main��������
        System.out.println("Thread main is end");
    }
}
