package myTree;

import myQueue.ArrayQueue;
import myQueue.ListNodeQueue;
import myStacks.Stacks;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * ����������,Ҫ�����ӽ�e;
 * ���ֵ�ȵ�ǰ�ڵ��ֵС�����ӽڵ��ֵ�ȵ�ǰ�ڵ��ֵ��
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
     * ����
     * root--�����������ĸ��ڵ�
     * SIZE--���Ľڵ����
     */
    private Node root;

    private int SIZE;

    public BstTree(){
       root = null;
    }

    /**
     * ���ؽڵ����
     */
    public int size(){
        return SIZE;
    }

    /**
     * ���Ԫ��
     * @param e ��Ҫ��ӵ�Ԫ��
     */
    public void addEle(E e){
        root = addEle(root,e);
    }

    /**
     * ��Ԫ��e���뵽��rootΪ���ڵ����֮��
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
     * ��rootΪ���ڵ����Ԫ��e
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
     * ���ҽڵ�roots�ĸ��ڵ�
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
     * ��������ǰ�����
     * ��������ڵ㣬�������������������
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
     * �������������
     * �ȱ�������������������ڵ㣬�ٱ���������
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
     * �������
     * �ȱ������������ٱ�������������������ڵ�
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
     * �ǵݹ�ǰ���������
     * ����ջʵ��
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
     * ��α�������ȱ�����������ȡ���·�����㷨
     * �������Ĳ�α���������й�ϵ
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
     * ���������������һ���ַ�����ʾ����
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
     * ɾ��ĳ���ڵ�Ԫ��
     * 1��ɾ��Ҷ�ӽڵ㣬ֱ��ɾ��
     * 2��ɾ��ֻ��һ�������Ľڵ㣬ɾ���ýڵ㣬��������
     * 3��ɾ�������������Ľڵ㣬���ýڵ�����������С��λ����֮�滻
     * @return
     */
    public void delNode(E e){
        if(root==null) return;
        else{
            Node temp = search(e);
            if(temp==null) return;
            Node parent = searchParant(e);
            if(temp.left==null&&temp.right==null){ //Ҷ�ӽڵ�
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
            }else if(temp.left!=null&&temp.right!=null){  //��������
                    Node newtemp = minNode(temp.right);
                    delNode(newtemp.e);
                    temp.e = newtemp.e;
                    return;
            }else {
                //ֻ��һ����������� TODO δ����һ������Ϊ���ڵ�����
                if(parent!=null){
                    //������һ�����⣬������ڵ���ڵĻ�����ô����ӽڵ�ʵ�ڸ��ڵ����߻����ұߣ��ڵ�����������ڸ��ڵ����߻����ұߣ�
                    /**
                     * �����eԪ���븸�ڵ���������ڵ�Ƚ�compareTo==0�Ļ������������ڵ�
                     * �����eԪ���븸�ڵ���������ڵ�Ƚ�compareTo==0�Ļ�����������ҽڵ�
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


                }else { //�Ǹ��ڵ�
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
     * �����ĸ߶�
     * @return ����һ�����ͱ���
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
     * ͨ���ݹ�ķ�ʽɾ�������������Ľڵ�
     * ����ɾ���Ľڵ���һ��Ҷ�ӽڵ�ʱ��ֱ��ɾ��
     *     t1           t1
     *    / \   --->   /
     *   t2 t3        t2
     * ����ɾ���Ľڵ���һ���ӽ�ʱ,ɾ���ڵ㣬�ӽڵ�����
     *     t2              t2
     *    /     ɾ��t3     /
     *   t3     ----->   t4
     *    \
     *     t4
     *  ����ɾ���Ľڵ��������ӽڵ�ʱ��������ýڵ�������������С���Ǹ��ڵ��ֵ������Ȼ�����С�Ľڵ�ɾ��
     *     t1                     t1
     *    / \                    / \
     *   t2 t3     ɾ���ڵ�t3    t2 t6
     *     / \     --------->     / \
     *    t4 t5                  t4 t5
     *   / \                      \
     *  t6 t7                     t7
     * @return ���ر�ɾ���Ľڵ�ֵ
     */
    public Node removeNode(E e){
        if(root==null){
            return null;
        }else {
            return removeNode(root,e);
        }
    }

    /**
     * ɾ����root��ʼ�ĸ��ڵ�֮��Ԫ��Ϊe�Ľڵ�
     * @param root
     * @param e
     * @return ����һ��Nodeֵ
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
