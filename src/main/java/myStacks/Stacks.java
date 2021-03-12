package myStacks;

/**
 * ջ��һ���Ƚ���������ݽṹ����������ӿڵķ���ʵ��ջ���ײ�洢���������飬Ҳ����������
 * @param <E>
 */
public interface Stacks <E>{

    /**
     * ��ջ����
     * @param e ��ջԪ��ֵ
     * @returns ���ؿ�
     */
    void push(E e);

    /**
     * ��ջ����
     * @retruns ���س�ջ����ֵ
     */
    void pop();

    /**
     * ��ȡջ�����
     */

    int getDeep();

    /**
     * ��ȡջ��Ԫ��
     */

    E stackTopElement();

    /**
     * ��ȡջ��Ԫ��
     */

    E stackBottomElement();

    /**
     * �ж�ջ�Ƿ�Ϊ��
     */

    boolean isEmpty();


}
