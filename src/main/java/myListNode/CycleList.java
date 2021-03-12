package myListNode;

public class CycleList {

    class Node{
        int item;
        Node next;

        Node(int item){
            this.item = item;
            this.next = null;
        }

        Node(int item,Node next){
            this.item = item;
            this.next = next;
        }

    }

    private Node head;
    private Node tail;

    public CycleList(int[] value){
        if(value==null||value.length==0){
            throw new RuntimeException("Group is empty!");
        }else{
            head = new Node(value[0]);
            head.next = head;
            tail = head;
            for(int i=1;i<value.length;i++){
                tail.next = new Node(value[i]);
                tail = tail.next;
                tail.next = head;
            }
        }
    }

    /**
     * 重写toString方法
     */
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("CycleList");
        Node temp = head;
        while (temp!=tail){
            builder.append(temp.item+"-->");
            temp = temp.next;
        }
        builder.append(temp.item);
        return builder.toString();
    }

    /**
     * 约瑟夫问题
     * 有一群猴子，他们要选一个猴子王出来
     * 猴子王选择方式为，这群猴子围成一个圈，从第k个猴子开始，往后数m个
     * 第m个猴子退出选拔，然后从k+1个猴子开始，又数m个....
     * 最后一个留下来的猴子为猴子王
     * @param start 起始位置
     * @param nums  数多少个
     * @param length 环形链表的形式
     */

    public void changKings(int start,int nums,int length){
        if(start>length||start<1||head==null){

            throw new IllegalArgumentException("参数非法");
        }
        else {
            for(int i=1;i<start;i++){
                head = head.next;
                tail = tail.next;
            }
            while (tail!=head){
                for(int i=1;i<nums;i++){
                    head = head.next;
                    tail = tail.next;
                }
                System.out.println("猴子"+head.item+"退出选拔");
                head = head.next;
                tail.next = head;
            }
            System.out.println("猴子"+head.item+"为猴子王");
        }
    }

}
