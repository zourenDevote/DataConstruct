package myQueue;

import myArrays.Array;

/**
 * ����ʵ�֣��ײ��������ʵ�֣������Ƴ�����Ӳ���
 */
public class ArrayQueue<E> implements Queue<E>{

    /**
     * �洢���ݵĵײ����
     */
    private Array<E> elementData;

    /**
     * �вι�����
     * @param Capacity ���������
     */
    public ArrayQueue(int Capacity){
        elementData = new Array<>(Capacity);
    }

    /**
     * �޲ι�����
     * ��̬������һ������Ϊ10�Ŀռ�
     */
    public ArrayQueue(){
        this(10);
    }


    /**
     * ��Ӳ���
     * @param e ��Ҫ��ӵ�����
     */
    @Override
    public void inQueue(E e) {
        elementData.addFirst(e);
    }

    /**
     * ���Ӳ���
     * @return ���س���Ԫ�ص�ֵ
     */
    @Override
    public E deQueue() {
        return elementData.removeLast();
    }

    /**
     * ���ض���Ԫ�ص�ֵ
     * @return
     */
    @Override
    public E getFront() {
        return elementData.getFirst();
    }

    /**
     * ���ض�βԪ�ص�ֵ
     * @return
     */
    @Override
    public E getTail() {
        return elementData.getLast();
    }

    /**
     * ��ȡ���еĳ���
     * @return
     */
    @Override
    public int getSize() {
        return elementData.getSize();
    }

    /**
     * �ж϶����Ƿ�Ϊ��
     * @return
     */
    @Override
    public boolean isEmpty() {
        return elementData.isEmpty();
    }

    /**
     * ��ӡ����
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
