package myQueue;

/**
 * ���ݽṹ---����
 * �����Ƚ��ȳ�����ȡ����ӿڵı�̷�ʽ
 * @param <E>
 */
public interface Queue<E>{

    /**
     * ��Ӳ���
     * @param e ��Ҫ��ӵ�����
     */
    void inQueue(E e);

    /**
     * ���Ӳ���
     * @returns ���س��ӵ����Ԫ�ص�ֵ
     */
    E deQueue();

    /**
     * ��ȡ����Ԫ�ز���
     * @returns ���ض���Ԫ�ص�ֵ
     */
    E getFront();

    /**
     * ��ȡ��βԪ�صĲ���
     * @returns ���ض�βԪ�ص�ֵ
     */

    E getTail();

    /**
     * ��ȡ���еĳ���
     * @returns ����һ��intֵ�����еĳ���
     */
    int getSize();

    /**
     * �ж϶����Ƿ�Ϊ��,����Ϊ�շ���false���ǿշ���true
     * @returns ����һ������ֵ
     */
    boolean isEmpty();


}
