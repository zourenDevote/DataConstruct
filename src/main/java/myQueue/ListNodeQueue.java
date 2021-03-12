package myQueue;

import java.util.Objects;

/**
 * @author 邹仁
 * 用双向链表实现队列，实现简单的增删查改操作
 */
public class ListNodeQueue<E> implements Queue<E> {

    /**
     * 创建字段，类中有两个双向链表节点一个指向队列的队首-->front
     * 一个指向队列的队尾--tail
     */
    private Node<E> front;

    private Node<E> tail;

    private int SIZE;

    /**
     * 无参构造器
     */
    public ListNodeQueue(){

    }

    /**
     * 入队操作
     * @param e 需要入队的数据
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
        System.out.println("头部"+front);
        System.out.println(front.item);
        SIZE++;


    }

    /**
     * 出队操作
     * @return 返回出队元素的值
     * @throws RuntimeException 如果队列为空的话，则抛出异常
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
     * 根据索引查找队列中的元素，有索引的查找
     *
     * @param index 队列的索引 代表第一个值
     * @returns 返回这个值
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
     * 无索引的查找从头开始查找
     * 如果队列中有这个元素的话，返回对应的索引处，如果没有的话，返回-1
     * @param o 被查找的元素o
     */
    public boolean contains(E o){
        return indexOf(o)>0;
    }

    /**
     * 无索引的查找--从尾部开始查找
     * 如果队列中有这个元素的话，返回对应的索引处，如果没有的话，返回-1
     * @param o 被查找的元素o
     */
    public boolean lastContains(E o){
        return lastIndexOf(o)>0;
    }


    /**
     * 从头开始遍历队列查找元素o
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
     * 从尾部开始遍历查找元素o
     * @param o 查找的元素o
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
     * 双向链表定义
     */
    class Node<E>{
        E item;
        Node<E> prev; //上一个节点
        Node<E> next; //下一个节点

        //创建一个双向链表
        public Node(Node<E> prev,E item,Node<E> next){
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    /**
     * 重写toString方法
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
