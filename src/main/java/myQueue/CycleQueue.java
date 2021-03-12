package myQueue;

/**
 * 循环队列，底层用数组实现，实现动态扩容
 * @param <E>
 */
public class CycleQueue<E> implements Queue<E>{

    /**
     * 字段值，默认的数组长度
     */
    final static int DefautLength = 10;
    /**
     * 属性，一个front索引和一个tail索引
     * 以及一个用于存储数据的数组
     * 和用于记录数据量的SIZE
     */
    private int front;//队首

    private int tail;//队尾

    private Object[] ele;//队列底层实现结构，数组

    private int SIZE;//记录数据量

    /**
     * 构造器，有参数
     * 在循环队列对象创建时，动态申请一个存储能力为Capacity的数组内存空间
     * @param Capacity
     */
    public CycleQueue(int Capacity){
        ele = new Object[Capacity+1];
    }

    /**
     * 无参构造器，申请一个默认存储能力的数组内存空间
     */
    public CycleQueue(){
        this(DefautLength);
    }

    /**
     * 扩容操作
     * 每次扩容到原Capacity的1.5倍
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
     * 缩容操作
     * 每次缩小到原Capacity的一半
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
     * 自定义扩容操作
     * @param Capacity 自定义的扩容长度
     * 如果Capacity<0 抛出异常
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
     * 获取队列的容量
     */
    public int getCapacity(){
        return ele.length-1;
    }

    /**
     * 入队操作
     * @param e 需要入队的数据
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
     * 出队操作
     * @return 返回出队的数据的值
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
     * 判断队列是否为空
     * @return 返回一个布尔值
     */
    @Override
    public boolean isEmpty() {
        return tail%getCapacity()==front%getCapacity()?true:false;
    }

    /**
     * 判断队列是否已满
     * @return 返回一个布尔值
     */
    public boolean isFull(){
        return (tail+1)%getCapacity()==front%getCapacity()?true:false;
    }

    /**
     * 重写toString方法
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
