package myListNode;

public interface ListNode<E>{


    /**
     * 添加数据操作
     * @param e;
     */
    void add(E e);
    /**
     * 取值操作，通过索引获取链表中的值
     * @param Index
     * @return 返回这个值
     */
    E get(int Index);

    /**
     * 查找操作
     * @param o
     * @return 返回一个布尔值
     * 判断链表中是否存在这个元素
     */
    boolean contains(Object o);

    /**
     * 插入操作
     * @param e 需要插入的元素
     * @param Index 插入的链表索引位置
     * 将链表节点插入到索引Index前成为一个新的Index索引处
     * @return 返回一个布尔值
     */
    boolean insert(int Index,E e);

    /**
     * 删除操作
     * @param Index
     * @return 返回被删除的值
     * 删除节点Index
     */
    E delet(int Index);

    /**
     * 改变链表某个节点出的值
     * @param Inedx 该节点的索引位置
     * @param e 改变后的的值
     * @return 返回改变前的值
     * 将节点Index位置的值改变为e
     */
    E change(int Inedx,E e);

    /**
     * 获取链表中存储的数据量
     * @return 返回一个整型值
     */
    int getSize();

    /**
     * 判断链表是否为空
     * @return 返回一个布尔值
     * 如果这个链表为空的话，返回true，否则的话返回false
     */
    boolean isEmpty();
}
