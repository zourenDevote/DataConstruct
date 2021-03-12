package myTree;

import myQueue.ListNodeQueue;

public class Main {

    public static void main(String[] args){
        AVL avl = new AVL();
        avl.addEle(20);
        avl.addEle(18);
        avl.addEle(27);
        avl.addEle(16);
        avl.addEle(19);
        avl.addEle(26);
        avl.addEle(17);
        avl.addEle(14);
        avl.removeNode(19);

        System.out.println(avl.isBST());
        System.out.println(avl.isAVL());
        avl.levelOrder();







    }
}
