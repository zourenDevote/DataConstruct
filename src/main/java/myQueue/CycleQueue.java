package myQueue;

/**
 * ѭ�����У��ײ�������ʵ�֣�ʵ�ֶ�̬����
 * @param <E>
 */
public class CycleQueue<E> implements Queue<E>{

    /**
     * �ֶ�ֵ��Ĭ�ϵ����鳤��
     */
    final static int DefautLength = 10;
    /**
     * ���ԣ�һ��front������һ��tail����
     * �Լ�һ�����ڴ洢���ݵ�����
     * �����ڼ�¼��������SIZE
     */
    private int front;//����

    private int tail;//��β

    private Object[] ele;//���еײ�ʵ�ֽṹ������

    private int SIZE;//��¼������

    /**
     * ���������в���
     * ��ѭ�����ж��󴴽�ʱ����̬����һ���洢����ΪCapacity�������ڴ�ռ�
     * @param Capacity
     */
    public CycleQueue(int Capacity){
        ele = new Object[Capacity+1];
    }

    /**
     * �޲ι�����������һ��Ĭ�ϴ洢�����������ڴ�ռ�
     */
    public CycleQueue(){
        this(DefautLength);
    }

    /**
     * ���ݲ���
     * ÿ�����ݵ�ԭCapacity��1.5��
     */
    private void makeCapacity(){
        Object[] temp = ele;
        Object[] newele = new Object[temp.length+(temp.length>>1)+1];
        int oldLength = temp.length-1;
        int newLength = newele.length-1;
        int index = 0;
        for(int i=front;i!=tail;i++){
            newele[i%newLength] = temp[i%oldLength];
        }
        ele = newele;
    }

    /**
     * ���ݲ���
     * ÿ����С��ԭCapacity��һ��
     */
    private void fallCapacity(){
        Object[] temp = ele;
        Object[] newele = new Object[(temp.length>>1)+1];
        int oldLength = temp.length-1;
        int newLength = newele.length-1;
        int index = 0;
        for(int i=front;i!=tail;i++){
            newele[i%newLength] = temp[i%oldLength];
        }
        ele = newele;
    }

    /**
     * �Զ������ݲ���
     * @param Capacity �Զ�������ݳ���
     * ���Capacity<0 �׳��쳣
     * @throws RuntimeException
     */
    public void makeCapacity(int Capacity){
        if(Capacity<0){
            throw new RuntimeException(Capacity+"<0");
        }else {
            Object[] temp = ele;
            Object[] newele = new Object[temp.length+Capacity+1];
            int oldLength = getCapacity();
            int newLength = newele.length-1;
            int index = 0;
            for(int i=front;i!=tail;i++){
                newele[i%newLength] = temp[i%oldLength];
            }
            ele = newele;
        }
    }

    /**
     * ��ȡ���е�����
     */
    public int getCapacity(){
        return ele.length-1;
    }

    /**
     * ��Ӳ���
     * @param e ��Ҫ��ӵ�����
     */



    @Override
    public void inQueue(E e) {
        if(isFull()){
            makeCapacity();
            ele[tail%getCapacity()] = e;
            tail++;
            SIZE++;
        }else {
            ele[tail%getCapacity()] = e;
            tail++;
            SIZE++;
        }
    }

    /**
     * ���Ӳ���
     * @return ���س��ӵ����ݵ�ֵ
     */
    @Override
    public E deQueue() {
        E temps = (E)ele[front%getCapacity()];
        ele[front%getCapacity()] = null;
        front++;
        SIZE--;
        if(SIZE<getCapacity()/4&&getCapacity()/2!=0){
            fallCapacity();
        }
        return temps;
    }

    @Override
    public E getFront() {
        return (E) ele[front%getCapacity()];
    }

    @Override
    public E getTail() {
        return (E) ele[tail%getCapacity()];
    }

    @Override
    public int getSize() {
        return SIZE;
    }

    /**
     * �ж϶����Ƿ�Ϊ��
     * @return ����һ������ֵ
     */
    @Override
    public boolean isEmpty() {
        return tail%getCapacity()==front%getCapacity()?true:false;
    }

    /**
     * �ж϶����Ƿ�����
     * @return ����һ������ֵ
     */
    public boolean isFull(){
        return (tail+1)%getCapacity()==front%getCapacity()?true:false;
    }

    /**
     * ��дtoString����
     */
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Queue--SIZE:"+getSize()+"Capacity:"+getCapacity()+"\n");
        builder.append("front [ ");

        int length = getCapacity();
        int last = tail;
        for(int i=front;(i%length)!=(last%length);i++){
            builder.append(ele[i%length]);
            if((i%length)!=((last-1)%length)){
                builder.append(" , ");
            }
        }
        builder.append(" ] tail");

        return builder.toString();
    }

}
