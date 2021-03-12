package myArrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * @anthor 邹仁
 * @introduction 自定义数组，实现数据的增删查改等基本操作
 * 自动扩容
 */
public class Array<E> {

    //初始化字段
    final private int STRATLENGTH = 10;

    //存储数据的Java原始数组
    private Object data[];

    //现有数据量
    private int SIZE;

    //空数组
    final private Object[] DefautArray = {};

    /**
     * 构造一个空的数组，延时存储
     */
     public Array(){
         this.data = DefautArray;
     }
    /**
     * 有参构造器、申请一个存放数据的空间
     * @param  Capacity 数组的初始长度，需要大于10
     */
    public Array(int Capacity){
        data = new Object[Capacity>STRATLENGTH?Capacity:STRATLENGTH];
    }

    /**
     * 返回数组的容量Capacity
     * 时间复杂度 O(1)
     */
    public int getCapacity(){
        return data.length;
    }

    /**
     * 返回数组的存储数据量
     * 时间复杂度 O(1)
     */

    public int getSize(){
        return SIZE;
    }

    /**
     * 判断数组是否存储了数据
     * 时间复杂度 O(1)
     */
    public boolean isEmpty(){
        return SIZE==0;
    }

    /**
     * 判断数组是否还有存储能力
     * 时间复杂度 O(1)
     */
    public boolean isStoreCapacity(){
        return (data.length-SIZE)>0;
    }

    /**
     * 确保数组还有一定数据的存储能力，有能力的话直接返回，没有能力的话，进行扩容
     * @param Capacity
     * @exception IllegalAccessException 如果传入的参数小于0的话，抛出异常
     * 时间复杂度 O(n)
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
     * 扩容机制，如果达到数组的length，会对数组进行扩容STARTLENGTH的一半
     * 时间复杂度 O(n)
     */
     private void makeCapacity(){
         Object temp[] = data;
         int newLength = temp.length+Math.max(temp.length>>1,STRATLENGTH);
         data = Arrays.copyOf(temp,newLength);
     }

    /**
     * 缩容机制当SIZE小于length/2时，进行数组的缩容
     * 时间复杂度 O(n)
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
     * 获取首个元素的值
     * 时间复杂度 O(1)
     */
    public E getFirst(){
        if(data.length==0||data==null){
            return null;
        }else {
            return (E)data[0];
        }
    }

    /**
     * 获取last元素
     * 时间复杂度 O(1)
     */
    public E getLast(){
        if(data.length==0||data==null){
            return null;
        }else {
            return (E)data[SIZE-1];
        }
    }

    /**
     * 将一个元素添加到数组最前面
     * @param e
     * 时间复杂度 O(n)
     */
     public void addFirst(E e){
         if(!isEmpty()){
             set(0,e);
         }else {
             add(e);
         }

     }

    /**
     * 添加一个最后的元素
     * 时间复杂度 O(1)
     */
    public void addLast(E e){
        add(e);
    }

    /**
     * 移除第一个元素
     * @return E 返回被移除的值
     * 时间复杂度 O(n)
    */
    public E removeFirst(){
        E temp = (E)data[0];
        delet(0);
        return temp;
    }

    /**
     * 移除最后一个元素的值
     * @retrun E 返回被移除的值
     * 时间复杂度 O(1)
     */
    public E removeLast(){
        E temp = (E)data[SIZE-1];
        delet(SIZE-1);
        return temp;
    }


    /**
     * 添加元素,首先判断是否需要扩容
     * @param e 被添加元素的值
     * 时间复杂度 O(1)
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
     * 插入元素到某个索引位置
     * @param Index 插入元素的原索引处 0代表第一个位置，依此类推
     * @param e 插入元素的值
     * 时间复杂度 O(n)
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
     * 改变某个元素的值
     * @param Index 被改变位置的索引0代表第一个位置
     * @param e 改变的值
     * @Returns 返回原来该位置的值
     * 时间复杂度 O(1)
     */
    public E change(int Index,E e){

        Objects.checkIndex(Index,SIZE);
        E temp = (E)data[Index];
        data[Index] = e;
        return temp;
    }

    /**
     * 删除某个元素的值
     * @param Index 被删除元素的索引位置
     * @Returns 返回被删除的元素的值
     * 时间复杂度 O(n)
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
     * 获取元素的值
     * @param Index 根据索引获取该索引位置的元素，0代表第一个元素
     * @Returns 返回被查找的元素的值
     * 时间复杂度 O(1)
     */
    public E get(int Index){
        Objects.checkIndex(Index,SIZE);
        return (E)data[Index];
    }

    /**
     * 返回一个数组
     * 时间复杂度 O(n)
     */
    public E[] toArrays(){
        Object temp[] = new Object[SIZE];
        System.arraycopy(data,0,temp,0,SIZE);
        return (E[])temp;
    }

    /**
     * 查询数组中是否包含数据o
     * @param o
     * 返回一个布尔值
     * 时间复杂度 O(n)
     */
    public boolean contains(Object o){
        return indexOf(0)>0;
    }

    /**
     * 从头往后遍历数据
     * @param o 需要查找的数据
     * 返回这个查找的数据，有的话返回Index，无的话返回-1
     * 时间复杂度 O(n)
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
     * 后往前遍历数据
     * @param o 需要查找的数据
     * 返回这个查找的数据，有的话返回Index，无的话返回-1
     * 时间复杂度 O(n)
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
     * 重写toString方法
     * 时间复杂度O(n)
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
