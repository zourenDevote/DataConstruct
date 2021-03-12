package myArrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * @anthor ����
 * @introduction �Զ������飬ʵ�����ݵ���ɾ��ĵȻ�������
 * �Զ�����
 */
public class Array<E> {

    //��ʼ���ֶ�
    final private int STRATLENGTH = 10;

    //�洢���ݵ�Javaԭʼ����
    private Object data[];

    //����������
    private int SIZE;

    //������
    final private Object[] DefautArray = {};

    /**
     * ����һ���յ����飬��ʱ�洢
     */
     public Array(){
         this.data = DefautArray;
     }
    /**
     * �вι�����������һ��������ݵĿռ�
     * @param  Capacity ����ĳ�ʼ���ȣ���Ҫ����10
     */
    public Array(int Capacity){
        data = new Object[Capacity>STRATLENGTH?Capacity:STRATLENGTH];
    }

    /**
     * �������������Capacity
     * ʱ�临�Ӷ� O(1)
     */
    public int getCapacity(){
        return data.length;
    }

    /**
     * ��������Ĵ洢������
     * ʱ�临�Ӷ� O(1)
     */

    public int getSize(){
        return SIZE;
    }

    /**
     * �ж������Ƿ�洢������
     * ʱ�临�Ӷ� O(1)
     */
    public boolean isEmpty(){
        return SIZE==0;
    }

    /**
     * �ж������Ƿ��д洢����
     * ʱ�临�Ӷ� O(1)
     */
    public boolean isStoreCapacity(){
        return (data.length-SIZE)>0;
    }

    /**
     * ȷ�����黹��һ�����ݵĴ洢�������������Ļ�ֱ�ӷ��أ�û�������Ļ�����������
     * @param Capacity
     * @exception IllegalAccessException �������Ĳ���С��0�Ļ����׳��쳣
     * ʱ�临�Ӷ� O(n)
     */
    public void makeCapacity(int Capacity) throws IllegalAccessException {
        if(Capacity<0){
            throw new IllegalAccessException(Capacity+"<0");
        }
        if(data.length-SIZE>=Capacity){
            return;
        }else{
            Object temp[] = data;
            int newLength = Capacity+SIZE;
            data = Arrays.copyOf(temp,newLength);
        }
    }

    /**
     * ���ݻ��ƣ�����ﵽ�����length����������������STARTLENGTH��һ��
     * ʱ�临�Ӷ� O(n)
     */
     private void makeCapacity(){
         Object temp[] = data;
         int newLength = temp.length+Math.max(temp.length>>1,STRATLENGTH);
         data = Arrays.copyOf(temp,newLength);
     }

    /**
     * ���ݻ��Ƶ�SIZEС��length/2ʱ���������������
     * ʱ�临�Ӷ� O(n)
     */
    void fallCapacity(){
        Object[] temp = data;
        if(SIZE<(temp.length>>1)){
            data=Arrays.copyOf(temp,SIZE<<1);
        }else{
            return;
        }
    }
    /**
     * ��ȡ�׸�Ԫ�ص�ֵ
     * ʱ�临�Ӷ� O(1)
     */
    public E getFirst(){
        if(data.length==0||data==null){
            return null;
        }else {
            return (E)data[0];
        }
    }

    /**
     * ��ȡlastԪ��
     * ʱ�临�Ӷ� O(1)
     */
    public E getLast(){
        if(data.length==0||data==null){
            return null;
        }else {
            return (E)data[SIZE-1];
        }
    }

    /**
     * ��һ��Ԫ����ӵ�������ǰ��
     * @param e
     * ʱ�临�Ӷ� O(n)
     */
     public void addFirst(E e){
         if(!isEmpty()){
             set(0,e);
         }else {
             add(e);
         }

     }

    /**
     * ���һ������Ԫ��
     * ʱ�临�Ӷ� O(1)
     */
    public void addLast(E e){
        add(e);
    }

    /**
     * �Ƴ���һ��Ԫ��
     * @return E ���ر��Ƴ���ֵ
     * ʱ�临�Ӷ� O(n)
    */
    public E removeFirst(){
        E temp = (E)data[0];
        delet(0);
        return temp;
    }

    /**
     * �Ƴ����һ��Ԫ�ص�ֵ
     * @retrun E ���ر��Ƴ���ֵ
     * ʱ�临�Ӷ� O(1)
     */
    public E removeLast(){
        E temp = (E)data[SIZE-1];
        delet(SIZE-1);
        return temp;
    }


    /**
     * ���Ԫ��,�����ж��Ƿ���Ҫ����
     * @param e �����Ԫ�ص�ֵ
     * ʱ�临�Ӷ� O(1)
     */
    public void add(E e){
        if((SIZE+1)>data.length){
            makeCapacity();
            data[SIZE]=e;
            SIZE++;
        }else{
            data[SIZE]=e;
            SIZE++;
        }
    }

    /**
     * ����Ԫ�ص�ĳ������λ��
     * @param Index ����Ԫ�ص�ԭ������ 0�����һ��λ�ã���������
     * @param e ����Ԫ�ص�ֵ
     * ʱ�临�Ӷ� O(n)
     */
     public void set(int Index,E e){
         if (!isEmpty()){
             Objects.checkIndex(Index,SIZE);
             Object[] temp = data;
             if(SIZE+1>data.length){
                 makeCapacity();
                 System.arraycopy(temp,Index,data,Index+1,SIZE-Index);
                 data[Index] = e;
                 SIZE++;
             }else {
                 Objects.checkIndex(Index,SIZE);
                 System.arraycopy(temp,Index,data,Index+1,SIZE-Index);
                 data[Index] = e;
                 SIZE++;
             }
         }else {
             return;
         }

     }

    /**
     * �ı�ĳ��Ԫ�ص�ֵ
     * @param Index ���ı�λ�õ�����0�����һ��λ��
     * @param e �ı��ֵ
     * @Returns ����ԭ����λ�õ�ֵ
     * ʱ�临�Ӷ� O(1)
     */
    public E change(int Index,E e){

        Objects.checkIndex(Index,SIZE);
        E temp = (E)data[Index];
        data[Index] = e;
        return temp;
    }

    /**
     * ɾ��ĳ��Ԫ�ص�ֵ
     * @param Index ��ɾ��Ԫ�ص�����λ��
     * @Returns ���ر�ɾ����Ԫ�ص�ֵ
     * ʱ�临�Ӷ� O(n)
     */
    public E delet(int Index){
        Objects.checkIndex(Index,SIZE);
        E temp = (E)data[Index];
        Object[] temps = data;
        SIZE--;
        System.arraycopy(temps,Index+1,data,Index,SIZE-Index);
        fallCapacity();
        return temp;
    }

    /**
     * ��ȡԪ�ص�ֵ
     * @param Index ����������ȡ������λ�õ�Ԫ�أ�0�����һ��Ԫ��
     * @Returns ���ر����ҵ�Ԫ�ص�ֵ
     * ʱ�临�Ӷ� O(1)
     */
    public E get(int Index){
        Objects.checkIndex(Index,SIZE);
        return (E)data[Index];
    }

    /**
     * ����һ������
     * ʱ�临�Ӷ� O(n)
     */
    public E[] toArrays(){
        Object temp[] = new Object[SIZE];
        System.arraycopy(data,0,temp,0,SIZE);
        return (E[])temp;
    }

    /**
     * ��ѯ�������Ƿ��������o
     * @param o
     * ����һ������ֵ
     * ʱ�临�Ӷ� O(n)
     */
    public boolean contains(Object o){
        return indexOf(0)>0;
    }

    /**
     * ��ͷ�����������
     * @param o ��Ҫ���ҵ�����
     * ����������ҵ����ݣ��еĻ�����Index���޵Ļ�����-1
     * ʱ�临�Ӷ� O(n)
     */
    public int indexOf(Object o){
        Object[] temp = data;
        if(o==null){
            for(int i=0;i<temp.length;i++){
                if(o==temp[i]){
                    return i;
                }
            }
        }else{
            for(int i=0;i<temp.length;i++){
                if(o.equals(temp[i])){
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * ����ǰ��������
     * @param o ��Ҫ���ҵ�����
     * ����������ҵ����ݣ��еĻ�����Index���޵Ļ�����-1
     * ʱ�临�Ӷ� O(n)
     */

    public int lastIndexOf(Object o){
        Object[] temp = data;
        if(o==null){
            for(int i=temp.length-1;i>=0;i--){
                if(o==temp[i]){
                    return i;
                }
            }
        }else{
            for(int i=temp.length-1;i>=0;i--){
                if(o.equals(temp[i])){
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * ��дtoString����
     * ʱ�临�Ӷ�O(n)
     */
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("AutoArray: [ ");
        for(int i=0;i<SIZE;i++){
            builder.append(get(i));
            if(i<SIZE-1){
                builder.append(" , ");
            }
        }
        builder.append("]");
        return builder.toString();
    }


}
