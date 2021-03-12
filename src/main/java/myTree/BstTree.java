package myTree;

import myQueue.ArrayQueue;
import myQueue.ListNodeQueue;
import myStacks.Stacks;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉搜索树,要求左子节e;
 * 点的值比当前节点的值小，右子节点的值比当前节点的值大
 * @param <E>
 */
public class BstTree<E extends Comparable<E>> {

    private class Node{
        E e;
        Node left;
        Node right;

        Node(E e){
            this.e = e;
            this.left = null;
            this.right = null;
        }

        Node(){
            this.e = null;
            this.left = null;
            this.right = null;
        }

        Node(E e,Node left,Node right){
            this.e = e;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString(){
            StringBuilder builder = new StringBuilder();
            builder.append("it is a node that value of "+e);
            return builder.toString();
        }
    }

    /**
     * 属性
     * root--二叉搜索树的根节点
     * SIZE--树的节点个数
     */
    private Node root;

    private int SIZE;

    public BstTree(){
       root = null;
    }

    /**
     * 返回节点个数
     */
    public int size(){
        return SIZE;
    }

    /**
     * 添加元素
     * @param e 需要添加的元素
     */
    public void addEle(E e){
        root = addEle(root,e);
    }

    /**
     * 将元素e插入到以root为根节点的树之后
     * @param roots
     * @param e
     */
    private Node addEle(Node roots, E e) {

        if(roots==null){
            SIZE++;
            return new Node(e);
        }
        if(e.compareTo(roots.e)<0){
            roots.left = addEle(roots.left,e);
        }else if(e.compareTo(roots.e)>0) {
            roots.right = addEle(roots.right,e);
        }
        return roots;
    }

    public Node search(E e){
        return search(root,e);
    }

    /**
     * 以root为根节点查找元素e
     * @param root
     * @param e
     * @return
     */
    private Node search(Node root, E e) {
        if(root==null){
            return null;
        }
        if(e.compareTo(root.e)==0){
            return root;
        }
        else if(e.compareTo(root.e)<0){
            return search(root.left,e);
        }
        else if(e.compareTo(root.e)>0){
            return search(root.right,e);
        }
        else {
            return null;
        }
    }


    public Node searchParant(E e){
        return searchParant(root,e);
    }

    /**
     * 查找节点roots的父节点
     * @param roots
     * @param e
     * @return
     */
    private Node searchParant(Node roots, E e) {
        if(roots==null||e.compareTo(roots.e)==0){
            return null;
        }
        if(roots.left!=null&&e.compareTo(roots.left.e)==0||roots.right!=null&&e.compareTo(roots.right.e)==0){
            return roots;
        }
        else {
            if(roots.left!=null&&e.compareTo(roots.e)<0){
                return searchParant(roots.left,e);
            }
            else if(roots.right!=null&&e.compareTo(roots.e)>0){
                return searchParant(roots.right,e);
            }
            else {
                return null;
            }
        }
    }

    /**
     * 二叉树的前序遍历
     * 先输出父节点，再输出左子树和右子树
     */
    public void prevOrder(){
        prevOrder(root);
    }

    /**
     *
     * @param root
     */
    private void prevOrder(Node root) {
        if(root==null){
            return ;
        }
        System.out.println(root.e);
        prevOrder(root.left);
        prevOrder(root.right);
    }

    /**
     * 中序遍历二叉树
     * 先遍历左子树，再输出父节点，再遍历右子树
     */
    public void midOrder(){
        midOrder(root);
    }

    /**
     *
     * @param root
     */
    private void midOrder(Node root) {
        if(root==null){
            return;
        }
        midOrder(root.left);
        System.out.println(root.e);
        midOrder(root.right);
    }

    /**
     * 后序遍历
     * 先遍历左子树，再遍历右子树，再输出父节点
     */
    public void nextOreder(){
        nextOreder(root);
    }

    /**
     *
     * @param root
     */
    private void nextOreder(Node root) {
        if(root==null){
            return;
        }
        nextOreder(root.left);
        nextOreder(root.right);
        System.out.println(root.e);
    }


    /**
     * 非递归前序遍历链表
     * 借助栈实现
     */
    public void prevOrderNo(){
        if(root==null){
            return;
        }
        else {
            Stack<Node> sta = new Stack<>();
            sta.push(root);
            while (!sta.isEmpty()){
                Node temp = sta.pop();
                System.out.println(temp.e);
                if(temp.right!=null){
                    sta.push(temp.right);
                }
                if(temp.left!=null){
                    sta.push(temp.left);
                }
            }
        }
    }

    /**
     * 层次遍历，广度遍历，用于求取最短路径的算法
     * 二叉树的层次遍历与队列有关系
     */
    public void levelOrder(){
        if(root==null){
            return;
        }
        else {
            ListNodeQueue<Node> que = new ListNodeQueue<>();
            que.inQueue(root);
            while (!que.isEmpty()){
                Node temp = que.deQueue();
                System.out.println(temp);
                System.out.println(temp.e);
                if(temp.left!=null){
                    que.inQueue(temp.left);
                }
                if(temp.right!=null){
                    que.inQueue(temp.right);
                }
            }
        }
    }


    /**
     * 将二叉搜索树变成一个字符串表示出来
     * @return
     */
    public String getTree(Node root){
        String str = "";
        if(root==null){
            return str;
        }else {
            str=str+root.e+" "+getTree(root.left)+getTree(root.right);
            return str;
        }
    }


    /**
     * 删除某个节点元素
     * 1、删除叶子节点，直接删除
     * 2、删除只有一棵子树的节点，删除该节点，子树上移
     * 3、删除有两棵子树的节点，将该节点右子树中最小的位置与之替换
     * @return
     */
    public void delNode(E e){
        if(root==null) return;
        else{
            Node temp = search(e);
            if(temp==null) return;
            Node parent = searchParant(e);
            if(temp.left==null&&temp.right==null){ //叶子节点
                if(parent.left!=null&&e.compareTo(parent.left.e)==0){
                    parent.left = null;
                    SIZE--;
                    return;
                }
                else if(parent.right!=null&&e.compareTo(parent.right.e)==0){
                    parent.right = null;
                    SIZE--;
                    return;
                }
            }else if(temp.left!=null&&temp.right!=null){  //两棵子树
                    Node newtemp = minNode(temp.right);
                    delNode(newtemp.e);
                    temp.e = newtemp.e;
                    return;
            }else {
                //只有一颗子树的情况 TODO 未考虑一棵子树为根节点的情况
                if(parent!=null){
                    //这里有一个问题，如果父节点存在的话，那么这个子节点实在父节点的左边还是右边？节点子树是添加在父节点的左边还是右边？
                    /**
                     * 当这个e元素与父节点的左子树节点比较compareTo==0的话，就添加在左节点
                     * 当这个e元素与父节点的右子树节点比较compareTo==0的话，就添加在右节点
                     */
                    if(temp.left!=null){
                        if(e.compareTo(parent.left.e)==0){
                            parent.left = temp.left;
                            SIZE--;
                        }else if(e.compareTo(parent.right.e)==0){
                            parent.right = temp.left;
                            SIZE--;
                        }
                    }else {
                        if(e.compareTo(parent.left.e)==0){
                            parent.left = temp.right;
                            SIZE--;
                        }else if(e.compareTo(parent.right.e)==0){
                            parent.right = temp.right;
                            SIZE--;
                        }
                    }


                }else { //是根节点
                   if(temp.left!=null){
                       temp = temp.left;
                       SIZE--;
                   }else {
                       temp = temp.right;
                       SIZE--;
                   }
                }
            }



        }
    }

    private Node minNode(Node right) {
        if(right.left==null) return right;
        return minNode(right.left);
    }

    /**
     * 求树的高度
     * @return 返回一个整型变量
     */
    public int treeHeight(){
        if(root==null){
            return 0;
        }else {
            return treeHeight(root);
        }
    }

    private int treeHeight(Node root) {
        int i = 1,j = 1;
        if(root.left==null&&root.right==null||root==null){
            return 0;
        }else {
            if(root.left!=null){
                i = i+treeHeight(root.left);
            }
            if(root.right!=null);
                j = i+treeHeight(root.right);
        }
        return Math.max(i,j);
    }

    /**
     * 通过递归的方式删除二叉搜索树的节点
     * 当被删除的节点是一个叶子节点时，直接删除
     *     t1           t1
     *    / \   --->   /
     *   t2 t3        t2
     * 当被删除的节点有一个子节时,删除节点，子节点上移
     *     t2              t2
     *    /     删除t3     /
     *   t3     ----->   t4
     *    \
     *     t4
     *  当被删除的节点有两个子节点时，则遍历该节点中右子树里最小的那个节点的值交换，然后该最小的节点删除
     *     t1                     t1
     *    / \                    / \
     *   t2 t3     删除节点t3    t2 t6
     *     / \     --------->     / \
     *    t4 t5                  t4 t5
     *   / \                      \
     *  t6 t7                     t7
     * @return 返回被删除的节点值
     */
    public Node removeNode(E e){
        if(root==null){
            return null;
        }else {
            return removeNode(root,e);
        }
    }

    /**
     * 删除从root开始的根节点之后元素为e的节点
     * @param root
     * @param e
     * @return 返回一个Node值
     */
    private Node removeNode(Node root, E e) {
        if(root==null){
            return null;
        }
        if(e.compareTo(root.e)<0){
            root.left = removeNode(root.left,e);
            return root;
        }else if(e.compareTo(root.e)>0){
            root.right = removeNode(root.right,e);
            return root;
        }else {
            if(root.left==null){
                Node newNode = root.right;
                root.right = null;
                SIZE--;
                return newNode;
            }
            else if(root.right==null){
                Node newNode = root.left;
                root.left = null;
                SIZE--;
                return newNode;
            }
            Node newNode = minNode(root.right);
            newNode.right = removeNode(root.right,newNode.e);
            newNode.left = root.left;
            root.left = root.right = null;
            SIZE--;
            return newNode;
        }
    }


    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("BST: "+getTree(root));
        return builder.toString();
    }

}
