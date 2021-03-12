package myStacks;

/**
 * 栈是一个先进后出的数据结构，采用面向接口的方法实现栈，底层存储可以用数组，也可以用链表
 * @param <E>
 */
public interface Stacks <E>{

    /**
     * 入栈操作
     * @param e 入栈元素值
     * @returns 返回空
     */
    void push(E e);

    /**
     * 出栈操作
     * @retruns 返回出栈的数值
     */
    void pop();

    /**
     * 获取栈的深度
     */

    int getDeep();

    /**
     * 获取栈顶元素
     */

    E stackTopElement();

    /**
     * 获取栈底元素
     */

    E stackBottomElement();

    /**
     * 判断栈是否为空
     */

    boolean isEmpty();


}
