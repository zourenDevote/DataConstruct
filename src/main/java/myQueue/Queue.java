package myQueue;

/**
 * 数据结构---队列
 * 数据先进先出，采取面向接口的编程方式
 * @param <E>
 */
public interface Queue<E>{

    /**
     * 入队操作
     * @param e 需要入队的数据
     */
    void inQueue(E e);

    /**
     * 出队操作
     * @returns 返回出队的这个元素的值
     */
    E deQueue();

    /**
     * 获取队首元素操作
     * @returns 返回队首元素的值
     */
    E getFront();

    /**
     * 获取队尾元素的操作
     * @returns 返回队尾元素的值
     */

    E getTail();

    /**
     * 获取队列的长度
     * @returns 返回一个int值即队列的长度
     */
    int getSize();

    /**
     * 判断队列是否为空,队列为空返回false，非空返回true
     * @returns 返回一个布尔值
     */
    boolean isEmpty();


}
