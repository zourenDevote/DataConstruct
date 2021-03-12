package myStacks;

import myArrays.Array;

public class MyStacks<E> implements Stacks<E> {

    /**
     * ջ�ĵײ�洢���Զ���װ����ʵ��
     */
    private Array<E> arrays;

    /**
     * �޲ι�����
     */
    public MyStacks(){
        arrays = new Array<>();
    }

    /**
     *�вι�����
     */
    public MyStacks(int Capacity){
        arrays = new Array<>(Capacity);
    }

    /**
     * ��ջ����
     * @param e ��ջԪ��ֵ
     */
    @Override
    public void push(E e) {
        arrays.add(e);
    }

    /**
     * ��ջ����
     */
    @Override
    public void pop() {
      arrays.removeLast();
    }

    /**
     * ��ȡջ�����
     * @return
     */
    @Override
    public int getDeep() {
        return arrays.getSize();
    }

    /**
     * ��ȡջ��Ԫ��
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
