package myTree;

import myQueue.ArrayQueue;
import java.util.*;

/**
 *ƽ���������AVL����
 * AVL��ͬʱ���������������ƽ�������������
 * ���κ�һ���ڵ��ϣ�ƽ�����ӵľ���ֵ������1
 * ĳ���ڵ�ĸ߶�ֵ == max(�������ĸ߶ȣ��������ĸ߶ȣ�+1
 * ĳ���ڵ��ϵ�ƽ������ == ���ҽڵ�ĸ߶Ȳ�
 * @param <E>
 */
public class AVL<E extends Comparable<E>> {

    private class Node{
        E e;
        Node left;
        Node right;
        private int height;
        Node(E e){
            this.e = e;
            this.left = null;
            this.right = null;
            height++;
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

    public AVL(){
        root = null;
    }


    /**
     * �ж�������Ƿ���bst��
     * bst�������������һ����һ����С�������
     * ������һ���ص㣬�������������������뵽һ��ArrayList�У�Ȼ�����ArrayList�����Ƿ������С����
     */
    public boolean isBST(){
        ArrayList<E> temp = new ArrayList();
        midOrder(temp);
        for(int i=1;i<temp.size();i++){
            if(temp.get(i-1).compareTo(temp.get(i))>0){
                return false;
            }
        }
        return true;
    }

    /**
     * �ж�������Ƿ���ƽ�������
     */
    public boolean isAVL(){
        if(root==null){
            return true;
        }
        int ph = getPin(root);
        if(Math.abs(ph)>1) return false;
        return isBalence(root.left)&&isBalence(root.right);
    }

    private boolean isBalence(Node node) {
        if(node==null) return true;
        int bf = getPin(node);
        if(Math.abs(bf)>1) return false;
        return isBalence(node.left)&&isBalence(node.right);
    }

    /**
     * ����ýڵ��ƽ������
     * @param root
     * @return
     */
    private int getPin(Node root) {
        if(root==null) return 0;
        return getHeight(root.left)-getHeight(root.right);
    }

    /**
     * ����ýڵ�ĸ߶�
     * @param node
     * @return
     */
    private int getHeight(Node node) {
        if(node==null) return 0;
        return node.height;
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
        roots.height = 1+Math.max(getHeight(roots.left),getHeight(roots.right));
        int pin = getPin(roots);
        //RR���µĲ�ƽ��
        if(pin>1&&getPin(roots.left)>=0){
            roots = rightRotate(roots);
        }
        //LL���µĲ�ƽ��
        if(pin<-1&&getPin(roots.right)<=0){
            roots = leftRotate(roots);
        }
        //RL���µĲ�ƽ��
        if(pin>1&&getPin(roots.left)<0){
            roots.left = leftRotate(roots.left);
            return rightRotate(roots);
        }
        //LR���µĲ�ƽ��
        if(pin<-1&&getPin(roots.right)>0){
            roots.right = rightRotate(roots.right);
            return leftRotate(roots);
        }
        return roots;
    }

    /**
     * ����
     *           N                        B
     *          / \                      / \
     *         A   B       ����ƽ��       N  D
     *            / \      ----->      / \  \
     *           C   D                A  C   K
     *                \
     *                 k
     * @param roots
     */
    private Node rightRotate(Node roots) {
        Node t1 = roots.left;
        Node t2 = t1.right;
        t1.right = roots;
        roots.left = t2;
        roots.height = Math.max(getHeight(roots.right),getHeight(roots.left))+1;
        t1.height = Math.max(getHeight(t1.left),getHeight(t1.right))+1;
        return t1;
    }

    /**
     *       N                     A
     *      / \                   / \
     *     A   B      ������      C   N
     *    / \        ------>    /   / \
     *   C   D                 K    D  B
     *  /
     * K
     * @param roots
     */
    private Node leftRotate(Node roots) {
        Node t1 = roots.right;
        Node t2 = t1.left;
        t1.left = roots;
        roots.right = t2;
        roots.height = Math.max(getHeight(roots.right),getHeight(roots.left))+1;
        t1.height = Math.max(getHeight(t1.left),getHeight(t1.right))+1;
        return t1;
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
     * @param temp
     */
    public void midOrder(List temp){
        midOrder(root,temp);
    }

    /**
     *
     * @param root
     */
    private void midOrder(Node root,List temp) {
        if(root==null){
            return;
        }
        midOrder(root.left,temp);
        temp.add(root.e);
        midOrder(root.right,temp);

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
            ArrayQueue<Node> que = new ArrayQueue<>();
            que.inQueue(root);
            while (!que.isEmpty()){
                Node temp = que.deQueue();
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
        Node retNode;
        if(e.compareTo(root.e)<0){
            root.left = removeNode(root.left,e);
            retNode = root;
        }else if(e.compareTo(root.e)>0){
            root.right = removeNode(root.right,e);
            retNode = root;
        }else {
            if(root.left==null){
                retNode = root.right;
                root.right = null;
                SIZE--;
            }
            else if(root.right==null){
                retNode = root.left;
                root.left = null;
                SIZE--;
            }
            else {
                retNode = minNode(root.right);
                retNode.right = removeNode(root.right,retNode.e);
                retNode.left = root.left;
                root.left = root.right = null;
                SIZE--;

            }
        }
        if(retNode==null) return null;
        retNode.height = 1+Math.max(getHeight(retNode.left),getHeight(retNode.right));
        int pin = getPin(retNode);
        //RR���µĲ�ƽ��
        if(pin>1&&getPin(retNode.left)>=0){
            retNode = rightRotate(retNode);
        }
        //LL���µĲ�ƽ��
        if(pin<-1&&getPin(retNode.right)<=0){
            retNode = leftRotate(retNode);
        }
        //RL���µĲ�ƽ��
        if(pin>1&&getPin(retNode.left)<0){
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        //LR���µĲ�ƽ��
        if(pin<-1&&getPin(retNode.right)>0){
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }
        return retNode;
    }


    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("BST: "+getTree(root));
        return builder.toString();
    }

}