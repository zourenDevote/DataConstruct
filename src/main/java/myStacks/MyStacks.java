package myStacks;

import myArrays.Array;

public class MyStacks<E> implements Stacks<E> {

    /**
     * 栈的底层存储用自动封装数组实现
     */
    private Array<E> arrays;

    /**
     * 无参构造器
     */
    public MyStacks(){
        arrays = new Array<>();
    }

    /**
     *有参构造器
     */
    public MyStacks(int Capacity){
        arrays = new Array<>(Capacity);
    }

    /**
     * 入栈操作
     * @param e 入栈元素值
     */
    @Override
    public void push(E e) {
        arrays.add(e);
    }

    /**
     * 出栈操作
     */
    @Override
    public void pop() {
      arrays.removeLast();
    }

    /**
     * 获取栈的深度
     * @return
     */
    @Override
    public int getDeep() {
        return arrays.getSize();
    }

    /**
     * 获取栈底元素
     * @return
     */
    @Override
    public E stackTopElement() {
        return arrays.getFirst();
    }

    @Override
    public E stackBottomElement() {
        return arrays.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Stack : [");
        int deep = arrays.getSize();
        for(int i=0;i<deep;i++){
            builder.append(""+arrays.get(i));
            if(i!=deep-1){
                builder.append(" , ");
            }
        }
        builder.append("] top");
        return builder.toString();
    }
}
