package myQueue;

public class Main {

    public static void main(String[] args){
        //��������
        int textNum = 100000;
        ListNodeQueue<Integer> arrayqueue = new ListNodeQueue<>();
        CycleQueue<Integer> loopqueue = new CycleQueue<>();
        double arrayTimes = textQueue(arrayqueue,textNum);
        double loopTimes = textQueue(loopqueue,textNum);

        System.out.println("������в����������õ�ʱ��Ϊ "+arrayTimes+" s");
        System.out.println("ѭ�����в����������õ�ʱ��Ϊ "+loopTimes+" s");
    }

    /**
     * ���Զ��еĳ�����𵴲���
     * @param data
     * @param textNum
     * @return ����һ��doubleֵ
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
