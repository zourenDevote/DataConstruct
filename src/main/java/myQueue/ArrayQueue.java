package myQueue;

import myArrays.Array;

/**
 * 队列实现，底层采用数组实现，但限制出队入队操作
 */
public class ArrayQueue<E> implements Queue<E>{

    /**
     * 存储数据的底层操作
     */
    private Array<E> elementData;

    /**
     * 有参构造器
     * @param Capacity 数组的容量
     */
    public ArrayQueue(int Capacity){
        elementData = new Array<>(Capacity);
    }

    /**
     * 无参构造器
     * 动态的申请一个容量为10的空间
     */
    public ArrayQueue(){
        this(10);
    }


    /**
     * 入队操作
     * @param e 需要入队的数据
     */
    @Override
    public void inQueue(E e) {
        elementData.addFirst(e);
    }

    /**
     * 出队操作
     * @return 返回出队元素的值
     */
    @Override
    public E deQueue() {
        return elementData.removeLast();
    }

    /**
     * 返回队首元素的值
     * @return
     */
    @Override
    public E getFront() {
        return elementData.getFirst();
    }

    /**
     * 返回队尾元素的值
     * @return
     */
    @Override
    public E getTail() {
        return elementData.getLast();
    }

    /**
     * 获取队列的长度
     * @return
     */
    @Override
    public int getSize() {
        return elementData.getSize();
    }

    /**
     * 判断队列是否为空
     * @return
     */
    @Override
    public boolean isEmpty() {
        return elementData.isEmpty();
    }

    /**
     * 打印操作
     */
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Queue: front[");
        int size = elementData.getSize();
        Array<E> data = elementData;
        for(int i = 0;i<size;i++){
            builder.append(data.get(i));
            if(i!=size-1){
                builder.append(" , ");
            }
        }
        builder.append(" ]tail");
        return builder.toString();
    }
}
