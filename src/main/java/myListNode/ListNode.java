package myListNode;

public interface ListNode<E>{


    /**
     * ������ݲ���
     * @param e;
     */
    void add(E e);
    /**
     * ȡֵ������ͨ��������ȡ�����е�ֵ
     * @param Index
     * @return �������ֵ
     */
    E get(int Index);

    /**
     * ���Ҳ���
     * @param o
     * @return ����һ������ֵ
     * �ж��������Ƿ�������Ԫ��
     */
    boolean contains(Object o);

    /**
     * �������
     * @param e ��Ҫ�����Ԫ��
     * @param Index �������������λ��
     * ������ڵ���뵽����Indexǰ��Ϊһ���µ�Index������
     * @return ����һ������ֵ
     */
    boolean insert(int Index,E e);

    /**
     * ɾ������
     * @param Index
     * @return ���ر�ɾ����ֵ
     * ɾ���ڵ�Index
     */
    E delet(int Index);

    /**
     * �ı�����ĳ���ڵ����ֵ
     * @param Inedx �ýڵ������λ��
     * @param e �ı��ĵ�ֵ
     * @return ���ظı�ǰ��ֵ
     * ���ڵ�Indexλ�õ�ֵ�ı�Ϊe
     */
    E change(int Inedx,E e);

    /**
     * ��ȡ�����д洢��������
     * @return ����һ������ֵ
     */
    int getSize();

    /**
     * �ж������Ƿ�Ϊ��
     * @return ����һ������ֵ
     * ����������Ϊ�յĻ�������true������Ļ�����false
     */
    boolean isEmpty();
}
