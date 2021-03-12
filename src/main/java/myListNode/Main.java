package myListNode;

public class Main {

    public static void main(String[] args){
        SingalList<Integer> data = new SingalList<>();
        for(int i=0;i<10;i++){
            data.add(i);
            System.out.println(data);
            if(i%3==0){
                data.delet(data.getSize()-1);
            }
            System.out.println(data);
        }
        data.rotateList();
        System.out.println(data);

    }
}
