package myStacks;

public class Main {

    public static void main(String[] args){
        MyStacks<Integer> stacks = new MyStacks<Integer>();
        for(int i=0;i<10;i++){
            stacks.push(i);
            System.out.println(stacks.toString());
        }
        stacks.pop();
        System.out.println(stacks.toString());


    }
}
