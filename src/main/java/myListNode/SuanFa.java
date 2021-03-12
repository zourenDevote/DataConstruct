package myListNode;

public class SuanFa {

    static class Node{
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
        Node(int[] nums){
            if(nums==null||nums.length==0){
                throw new RuntimeException("Group is empty");
            }
            else {
                this.item = nums[0];
                Node temp = this;
                for(int i=1;i<nums.length;i++){
                    temp.next = new Node(nums[i]);
                    temp = temp.next;
                }
            }
        }

        /**
         * ��дtoString�����������
         * @return
         */
        @Override
        public String toString(){
            StringBuilder builder = new StringBuilder();
            builder.append("List Null->");
            Node temp = this;
            while (temp!=null){
                builder.append(temp.item+"->");
                temp = temp.next;
            }
            builder.append("Null");
            return builder.toString();
        }
    }



    /**
     * �����ɾ������
     * ɾ�����������е�Ԫ��Ϊval��Ԫ��
     * ��whileʵ��
     */
    public static void removeVal(Node head, int val){
        if(head==null){
            throw new RuntimeException("Illegal data!");
        }
        while(head!=null&&head.item==val){
            head = head.next;
        }
        Node temp = head;
        while (temp.next!=null){
            if(temp.next.item==val){
              temp.next = temp.next.next;
              temp = temp.next;
            }else {
                temp = temp.next;
            }
        }
    }

    /**
     * ���õݹ��㷨����ɾ�������е�����Ԫ��
     * @param head ԭ����
     * @param val  ��Ҫɾ����ֵ
     */
    public static Node removeByDiGui(Node head,int val){
        if(head==null){
            return head;
        }
        if(head.item==val){
            head = head.next;
        }
        Node temp = removeByDiGui(head.next,val);
        if(temp==null){
            return head;
        }
        else {
            head.next = temp;
            return head;
        }
    }





    public static void main(String[] args){
        int[] temp = {1,2,3,4,7,5,7,6};
        Node data = new Node(temp);
        System.out.println(data);
        data = removeByDiGui(data,7);
        System.out.println(data);
        int[] value = {1,2,3,4,5,6,7,8};
        CycleList cyc = new CycleList(value);
        System.out.println(cyc);
        cyc.changKings(3,3,8);
        System.out.println(cyc);
    }


}
