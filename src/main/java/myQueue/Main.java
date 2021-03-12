package myQueue;

public class Main {

    public static void main(String[] args){
        //测试用力
        int textNum = 100000;
        ListNodeQueue<Integer> arrayqueue = new ListNodeQueue<>();
        CycleQueue<Integer> loopqueue = new CycleQueue<>();
        double arrayTimes = textQueue(arrayqueue,textNum);
        double loopTimes = textQueue(loopqueue,textNum);

        System.out.println("单向队列测试用力所用的时间为 "+arrayTimes+" s");
        System.out.println("循环队列测试用力所用的时间为 "+loopTimes+" s");
    }

    /**
     * 测试队列的出入对震荡操作
     * @param data
     * @param textNum
     * @return 返回一个double值
     */
    public static double textQueue(Queue<Integer> data,int textNum){
        double a = System.currentTimeMillis();
        for(int i=0;i<textNum;i++){
            data.inQueue(i);
            if(i%3==0){
                data.deQueue();
            }
        }
        double b = System.currentTimeMillis();
        return b-a;
    }
}
