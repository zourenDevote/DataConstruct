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
     * ��дtoString����
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
     * Լɪ������
     * ��һȺ���ӣ�����Ҫѡһ������������
     * ������ѡ��ʽΪ����Ⱥ����Χ��һ��Ȧ���ӵ�k�����ӿ�ʼ��������m��
     * ��m�������˳�ѡ�Σ�Ȼ���k+1�����ӿ�ʼ������m��....
     * ���һ���������ĺ���Ϊ������
     * @param start ��ʼλ��
     * @param nums  �����ٸ�
     * @param length �����������ʽ
     */

    public void changKings(int start,int nums,int length){
        if(start>length||start<1||head==null){

            throw new IllegalArgumentException("�����Ƿ�");
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
                System.out.println("����"+head.item+"�˳�ѡ��");
                head = head.next;
                tail.next = head;
            }
            System.out.println("����"+head.item+"Ϊ������");
        }
    }

}
