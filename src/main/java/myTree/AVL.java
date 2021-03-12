package myTree;

import myQueue.ArrayQueue;
import java.util.*;

/**
 *平衡二叉树（AVL树）
 * AVL树同时满足二叉搜索树和平衡二叉树的条件
 * 在任何一个节点上，平衡因子的绝对值不超过1
 * 某个节点的高度值 == max(左子树的高度，右子树的高度）+1
 * 某个节点上的平衡因子 == 左右节点的高度差
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
     * 属性
     * root--二叉搜索树的根节点
     * SIZE--树的节点个数
     */
    private Node root;

    private int SIZE;

    public AVL(){
        root = null;
    }


    /**
     * 判断这颗树是否是bst树
     * bst树进行中序遍历一定是一个从小到大的树
     * 利用这一个特点，将树的中序遍历结果放入到一个ArrayList中，然后遍历ArrayList，看是否满足从小到大
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
     * 判断这颗树是否是平衡二叉树
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
     * 计算该节点的平衡因子
     * @param root
     * @return
     */
    private int getPin(Node root) {
        if(root==null) return 0;
        return getHeight(root.left)-getHeight(root.right);
    }

    /**
     * 计算该节点的高度
     * @param node
     * @return
     */
    private int getHeight(Node node) {
        if(node==null) return 0;
        return node.height;
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
        roots.height = 1+Math.max(getHeight(roots.left),getHeight(roots.right));
        int pin = getPin(roots);
        //RR导致的不平衡
        if(pin>1&&getPin(roots.left)>=0){
            roots = rightRotate(roots);
        }
        //LL导致的不平衡
        if(pin<-1&&getPin(roots.right)<=0){
            roots = leftRotate(roots);
        }
        //RL导致的不平衡
        if(pin>1&&getPin(roots.left)<0){
            roots.left = leftRotate(roots.left);
            return rightRotate(roots);
        }
        //LR导致的不平衡
        if(pin<-1&&getPin(roots.right)>0){
            roots.right = rightRotate(roots.right);
            return leftRotate(roots);
        }
        return roots;
    }

    /**
     * 左旋
     *           N                        B
     *          / \                      / \
     *         A   B       左旋平衡       N  D
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
     *     A   B      右旋后      C   N
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
        //RR导致的不平衡
        if(pin>1&&getPin(retNode.left)>=0){
            retNode = rightRotate(retNode);
        }
        //LL导致的不平衡
        if(pin<-1&&getPin(retNode.right)<=0){
            retNode = leftRotate(retNode);
        }
        //RL导致的不平衡
        if(pin>1&&getPin(retNode.left)<0){
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        //LR导致的不平衡
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