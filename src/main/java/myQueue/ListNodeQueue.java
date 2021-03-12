package myQueue;

import java.util.Objects;

/**
 * @author ����
 * ��˫������ʵ�ֶ��У�ʵ�ּ򵥵���ɾ��Ĳ���
 */
public class ListNodeQueue<E> implements Queue<E> {

    /**
     * �����ֶΣ�����������˫������ڵ�һ��ָ����еĶ���-->front
     * һ��ָ����еĶ�β--tail
     */
    private Node<E> front;

    private Node<E> tail;

    private int SIZE;

    /**
     * �޲ι�����
     */
    public ListNodeQueue(){

    }

    /**
     * ��Ӳ���
     * @param e ��Ҫ��ӵ�����
     */
    @Override
    public void inQueue(E e) {
        Node<E> temp = tail;
        Node<E> newNode = new Node<>(temp,e,null);
        tail = newNode;
        if(temp==null){
            front = newNode;
        }else {
            temp.next = newNode;
        }
        System.out.println("ͷ��"+front);
        System.out.println(front.item);
        SIZE++;


    }

    /**
     * ���Ӳ���
     * @return ���س���Ԫ�ص�ֵ
     * @throws RuntimeException �������Ϊ�յĻ������׳��쳣
     */
    @Override
    public E deQueue() {
        if(isEmpty()){
            throw new RuntimeException("Queue is empty");
        }
        Node<E> tem = front;
        E temp = front.item;
        System.out.println("temp"+temp);
        if(tem.next!=null){
            front = front.next;
            front.prev = null;
            SIZE--;
            System.out.println("temp1"+temp);
            return temp;
        }else {
            front.item=null;
            SIZE--;
            System.out.println("temp2"+temp);
            return temp;
        }

    }

    @Override
    public E getFront() {
        return front.item;
    }

    @Override
    public E getTail() {
        return tail.item;
    }

    @Override
    public int getSize() {
        return SIZE;
    }

    @Override
    public boolean isEmpty() {
        return front==null&&tail==null?true:false;
    }

    /**
     * �����������Ҷ����е�Ԫ�أ��������Ĳ���
     *
     * @param index ���е����� �����һ��ֵ
     * @returns �������ֵ
     */
    public E node(int index){
        Objects.checkIndex(index,SIZE);
        if(index>SIZE>>1){
            Node<E> temp = tail;
            for(int i = 1;i<SIZE-index;i++){
                temp = temp.prev;
            }
            return temp.item;
        }else {
            Node<E> temp = front;
            for(int i = 0;i<index;i++){
                temp = temp.next;
            }
            return temp.item;
        }
    }

    /**
     * �������Ĳ��Ҵ�ͷ��ʼ����
     * ��������������Ԫ�صĻ������ض�Ӧ�������������û�еĻ�������-1
     * @param o �����ҵ�Ԫ��o
     */
    public boolean contains(E o){
        return indexOf(o)>0;
    }

    /**
     * �������Ĳ���--��β����ʼ����
     * ��������������Ԫ�صĻ������ض�Ӧ�������������û�еĻ�������-1
     * @param o �����ҵ�Ԫ��o
     */
    public boolean lastContains(E o){
        return lastIndexOf(o)>0;
    }


    /**
     * ��ͷ��ʼ�������в���Ԫ��o
     * @param o
     * @return
     */
    private int indexOf(E o) {
        Node<E> temp = front;
        if (o==null){
            int index = 0;
            while (temp!=null){
                if(o==temp.item){
                    return index;
                }else {
                    index++;
                }
            }
        }else {
            int index = 0;
            while (temp!=null){
                if(o.equals(temp.item)){
                    return index;
                }else {
                    index++;
                    temp = temp.next;
                }
            }
        }
        return -1;
    }

    /**
     * ��β����ʼ��������Ԫ��o
     * @param o ���ҵ�Ԫ��o
     */
    private int lastIndexOf(E o){
        Node<E> temp = tail;
        if(o==null){
            int index = SIZE-1;
            while (temp!=null){
                if(o==temp.item){
                    return index;
                }else {
                    index--;
                    temp = temp.prev;
                }
            }
        }else {
            int index = SIZE-1;
            while (temp!=null){
                if(o.equals(temp.item)){
                    return index;
                }else {
                    index--;
                    temp = temp.prev;
                }
            }
        }
        return -1;
    }


    /**
     * ˫��������
     */
    class Node<E>{
        E item;
        Node<E> prev; //��һ���ڵ�
        Node<E> next; //��һ���ڵ�

        //����һ��˫������
        public Node(Node<E> prev,E item,Node<E> next){
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    /**
     * ��дtoString����
     */
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Queue: front[");
        Node<E> temp = front;
        for (int i = 0;i<=SIZE;i++){
            builder.append(temp.item);
            if(i!=SIZE-1){
                builder.append(" , ");
            }
            temp = temp.next;
        }
        builder.append(" ]tail");
        return builder.toString();
    }

}
