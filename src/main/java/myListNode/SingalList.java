package myListNode;

import java.util.ArrayList;
import java.util.Objects;

/**
 * 单链表
 * 链表的的特性：1、是一个真正的动态存储结构
 *            2、存储结构为链式存储结构
 *            3、相比于数组，链表节点除了存储数据，还会存储一个指针域，空间开销大
 *            4、不能随机存取，不能像数组那样快速获取节点上的值
 *            5、能在链表节点上的任意位置插入，修改、删除链表存储的数据
 * @author 邹仁
 * @param <E>  采用泛型编写程序
 */
public class SingalList<E> implements ListNode<E>{

    /**
     * 属性：
     * 1、指向链表的虚拟头节点visualHead
     * 2、标识链表数据量的整型值SIZE
     */
    private Node<E> visualHead;

    private int SIZE;

    /**
     * 构造器
     */
    public SingalList(){visualHead = new Node<>();}

    /**
     * 定义单链表
     */
    class Node<E>{
        E Item;//链表存储的数据
        Node<E> next; //下一个节点的指针域

        /**
         * 有参构造器
         * @param Item 链表节点存储的数据
         * @param next 链表下一个节点的指针域
         */
        public Node(E Item,Node<E> next){
            this.Item = Item;
            this.next = next;
        }
        /**
         * 无参构造器
         */
        public Node(){
            this(null,null);
        }

    }

    /**
     * 添加数据操作，在链表前head前添加数据
     * 在head前添加
     * @param e;
     */
    @Override
    public void add(E e) {
        insert(0,e);
    }

    /**
     * 获取链表索引位置的值
     * @param Index 0代表第一个元素
     * @return 返回这个值
     * @throws RuntimeException 如果链表是空的，抛出异常
     * @throws IndexOutOfBoundsException 如果索引位置大于或等于SIZE 抛出异常
     */

    @Override
    public E get(int Index) {
        if(isEmpty()){
            throw new RuntimeException("List is empty！");
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
     * 获取头部元素
     */
    public E getFirst(){
        return get(0);
    }

    /**
     * 获取尾部元素
     */
    public E getLast(){
        return get(SIZE-1);
    }


    /**
     * 判断链表里面是否含有o，如果包含则返回true，不包含则返回false
     * @param o
     * @return 返回一个布尔值
     * @throws RuntimeException 如果链表为空，则抛出异常
     */
    @Override
    public boolean contains(Object o) {
        if(isEmpty()){
            throw new RuntimeException("List is empty！");
        }else {
            //遍历链表
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
     * @param Index 插入的链表索引位置 0代表第一个位置的索引
     * 将链表节点插入到索引Index前成为一个新的Index索引处
     * @param e 需要插入的元素
     * @return 返回true
     * @throws RuntimeException 如果索引Index超过SIZE或者Index<0，抛出异常
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
     * 删除节点Index处的节点，同样是采用快慢启动遍历的方法
     * @param Index Index=0代表第一个节点
     * @return 返回true
     * @throws RuntimeException 当单链表为空时，抛出异常
     * @throws IndexOutOfBoundsException 当Index大于或者等于SIZE时，抛出异常
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
     * 删除首节点位置
     */
    public E removeFirst(){
        E data = getFirst();
        delet(0);
        return data;
    }

    /**
     * 删除尾节点位置
     */
    public E removeLast(){
        E data = getLast();
        delet(SIZE-1);
        return data;
    }

    /**
     * 删除元素o
     * @param o 需要删除的元素o
     */


    /**
     * 改变索引Index节点位置处的值
     * @param Inedx 该节点的索引位置
     * @param e 改变后的的值
     * @return 返回改变前索引Index位置处的值
     * @throws RuntimeException 当单链表为空时，抛出异常
     * @throws IndexOutOfBoundsException 当Index大于或者等于SIZE时，抛出异常
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
     * 获取单链表的数据量
     * @return 返回一个整型值
     */
    @Override
    public int getSize() {
        return SIZE;
    }

    /**
     * 判断单链表是否为空
     * @return  返回一个布尔值
     * 如果为空的话返回true，不为空的话返回false
     */
    @Override
    public boolean isEmpty() {
        return visualHead.next==null;
    }

    /**
     * 重写toString方法
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
     * 链表的翻转操作
     * 将链表进行翻转
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
