package myArrays;

import java.util.ArrayList;

public class Text {

    public static void main(String[] args){
        Array<Integer> group = new Array<>();
        ArrayList<Integer> sysgroup = new ArrayList<>();
        double start = System.currentTimeMillis();
        for(int i=1;i<100000;i++){
            group.add(i);
            if(i%3==0){
                group.delet(0);
            }

        }
        double end_1 = System.currentTimeMillis();
        for(int i=1;i<100000;i++){
            sysgroup.add(i);
            if(i%3==0){
                sysgroup.remove(0);
            }
        }
        double end_2 = System.currentTimeMillis();
        int a =(int) (end_1-start);
        int b =(int)(end_2-end_1);
        System.out.println("�Ҵ������������õ�ʱ��Ϊ"+a);
        System.out.println("ϵͳ���������õ�ʱ��Ϊ"+b);



    }
}
