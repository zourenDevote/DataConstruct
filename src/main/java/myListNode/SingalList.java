package myListNode;

import java.util.ArrayList;
import java.util.Objects;

/**
 * ������
 * ����ĵ����ԣ�1����һ�������Ķ�̬�洢�ṹ
 *            2���洢�ṹΪ��ʽ�洢�ṹ
 *            3����������飬����ڵ���˴洢���ݣ�����洢һ��ָ���򣬿ռ俪����
 *            4�����������ȡ�������������������ٻ�ȡ�ڵ��ϵ�ֵ
 *            5����������ڵ��ϵ�����λ�ò��룬�޸ġ�ɾ������洢������
 * @author ����
 * @param <E>  ���÷��ͱ�д����
 */
public class SingalList<E> implements ListNode<E>{

    /**
     * ���ԣ�
     * 1��ָ�����������ͷ�ڵ�visualHead
     * 2����ʶ����������������ֵSIZE
     */
    private Node<E> visualHead;

    private int SIZE;

    /**
     * ������
     */
    public SingalList(){visualHead = new Node<>();}

    /**
     * ���嵥����
     */
    class Node<E>{
        E Item;//����洢������
        Node<E> next; //��һ���ڵ��ָ����

        /**
         * �вι�����
         * @param Item ����ڵ�洢������
         * @param next ������һ���ڵ��ָ����
         */
        public Node(E Item,Node<E> next){
            this.Item = Item;
            this.next = next;
        }
        /**
         * �޲ι�����
         */
        public Node(){
            this(null,null);
        }

    }

    /**
     * ������ݲ�����������ǰheadǰ�������
     * ��headǰ���
     * @param e;
     */
    @Override
    public void add(E e) {
        insert(0,e);
    }

    /**
     * ��ȡ��������λ�õ�ֵ
     * @param Index 0�����һ��Ԫ��
     * @return �������ֵ
     * @throws RuntimeException ��������ǿյģ��׳��쳣
     * @throws IndexOutOfBoundsException �������λ�ô��ڻ����SIZE �׳��쳣
     */

    @Override
    public E get(int Index) {
        if(isEmpty()){
            throw new RuntimeException("List is empty��");
        }else {
            Objects.checkIndex(Index,SIZE);
            int length = SIZE;
            Node<E> temps = visualHead.next;
            for (int i = 1;i<Index+1;i++){
                temps = temps.next;
            }
            return temps.Item;
        }
    }

    /**
     * ��ȡͷ��Ԫ��
     */
    public E getFirst(){
        return get(0);
    }

    /**
     * ��ȡβ��Ԫ��
     */
    public E getLast(){
        return get(SIZE-1);
    }


    /**
     * �ж����������Ƿ���o����������򷵻�true���������򷵻�false
     * @param o
     * @return ����һ������ֵ
     * @throws RuntimeException �������Ϊ�գ����׳��쳣
     */
    @Override
    public boolean contains(Object o) {
        if(isEmpty()){
            throw new RuntimeException("List is empty��");
        }else {
            //��������
            if(o==null){
                Node<E> temp = visualHead.next;
                while (temp!=null){
                    if(o==temp.Item){
                        return true;
                    }else {
                        temp = temp.next;
                    }
                }
            }
            else {
                Node<E> temp = visualHead.next;
                while (temp!=null){
                    if(o.equals(temp.Item)){
                        return true;
                    }else {
                        temp = temp.next;
                    }
                }
            }
        }
        return false;
    }

    /**
     *
     * @param Index �������������λ�� 0�����һ��λ�õ�����
     * ������ڵ���뵽����Indexǰ��Ϊһ���µ�Index������
     * @param e ��Ҫ�����Ԫ��
     * @return ����true
     * @throws RuntimeException �������Index����SIZE����Index<0���׳��쳣
     */
    @Override
    public boolean insert(int Index, E e) {
        if(Index<0||Index>SIZE){
            throw new RuntimeException("Insert by Illgail Index");
        }
        else{
            Node<E> temp = visualHead;
            for(int i=0;i<Index;i++){
                temp = temp.next;
            }
            if(temp!=null){
                Node<E> newNode = new Node<>(e,temp.next);
                temp.next = newNode;
                SIZE++;
            }else {
                Node<E> newNode = new Node<>(e,null);
                visualHead.next = newNode;
                SIZE++;
            }
        }
        return true;
    }

    /**
     * ɾ���ڵ�Index���Ľڵ㣬ͬ���ǲ��ÿ������������ķ���
     * @param Index Index=0�����һ���ڵ�
     * @return ����true
     * @throws RuntimeException ��������Ϊ��ʱ���׳��쳣
     * @throws IndexOutOfBoundsException ��Index���ڻ��ߵ���SIZEʱ���׳��쳣
     */
    @Override
    public E delet(int Index) {
        if(isEmpty()){
            throw new RuntimeException("List is empty!");
        }else{
            Objects.checkIndex(Index,SIZE);
            Node<E> temp = visualHead;
            for(int i=0;i<Index;i++){
                temp = temp.next;
            }
            Node<E> next = temp.next;
            E data = next.Item;
            temp.next = next.next;
            next.next = null;
            next.Item = null;
            SIZE--;
            return data;
        }
    }

    /**
     * ɾ���׽ڵ�λ��
     */
    public E removeFirst(){
        E data = getFirst();
        delet(0);
        return data;
    }

    /**
     * ɾ��β�ڵ�λ��
     */
    public E removeLast(){
        E data = getLast();
        delet(SIZE-1);
        return data;
    }

    /**
     * ɾ��Ԫ��o
     * @param o ��Ҫɾ����Ԫ��o
     */


    /**
     * �ı�����Index�ڵ�λ�ô���ֵ
     * @param Inedx �ýڵ������λ��
     * @param e �ı��ĵ�ֵ
     * @return ���ظı�ǰ����Indexλ�ô���ֵ
     * @throws RuntimeException ��������Ϊ��ʱ���׳��쳣
     * @throws IndexOutOfBoundsException ��Index���ڻ��ߵ���SIZEʱ���׳��쳣
     */
    @Override
    public E change(int Inedx, E e) {
        if(isEmpty()){
            throw new RuntimeException("List is empty!");
        }else {
            Objects.checkIndex(Inedx,SIZE);
            Node<E> temp = visualHead.next;
            for(int i=0;i<Inedx;i++){
                temp = temp.next;
            }
            E oldData = temp.Item;
            temp.Item = e;
            return oldData;
        }
    }

    /**
     * ��ȡ�������������
     * @return ����һ������ֵ
     */
    @Override
    public int getSize() {
        return SIZE;
    }

    /**
     * �жϵ������Ƿ�Ϊ��
     * @return  ����һ������ֵ
     * ���Ϊ�յĻ�����true����Ϊ�յĻ�����false
     */
    @Override
    public boolean isEmpty() {
        return visualHead.next==null;
    }

    /**
     * ��дtoString����
     * @return
     */
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("SingalList NULL->");
        Node<E> temp = visualHead.next;
        for(int i=0;i<SIZE;i++){
            builder.append(temp.Item+"->");
            temp = temp.next;
        }
        builder.append("NULL");
        return builder.toString();
    }

    /**
     * ����ķ�ת����
     * ��������з�ת
     */
    public void rotateList(){
        Node<E> newList = new Node<>();
        Node<E> temps = visualHead.next;
        Node<E> newNext = newList.next;
        Node<E> tem;
        for(int i=0;i<SIZE;i++){
            tem = temps.next;
            visualHead.next = tem;
            temps.next= newNext;
            newList.next = temps;
            newNext = temps;
            temps = tem;
        }
        visualHead = newList;
    }




}
