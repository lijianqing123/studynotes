package com.github.datastructure.stack;

/**
 * @Auther: ljq
 * @Date: 2020/1/16 16:03
 * @Description 数组模拟栈
 */
public class ArrayStackDemo {

    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(5);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.push(4);
        arrayStack.push(5);
        arrayStack.push(6);
        arrayStack.show();

        System.out.println(arrayStack.pop());
        arrayStack.push(6);
        arrayStack.show();

        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
  //      System.out.println(arrayStack.pop());

        arrayStack.show();


    }
}

class ArrayStack{
    //栈的最大容量
    private int maxSize;

    private int[] stack;

    private int top;

    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        this.stack = new int[maxSize];
        this.top = -1;
    }

    /**
     * 是否已满
     * @return
     */
    public boolean isFull(){
        return top == maxSize - 1;
    }
    /**
     * 是否为空
     */
    public boolean isEmpty(){
        return top == -1;
    }

    /**
     * 入栈
     * @param num
     */
    public void push(int num){
        if(isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = num;
    }

    /**
     * 出栈
     * @return
     */
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    /**
     * 遍历
     */
    public void show(){
        if(isEmpty()){
            System.out.println("栈空");
            return;
        }
        System.out.println("输出栈内元素");
        for(int i = top;i >= 0; i--){
            System.out.println(stack[i]);
        }
    }
}
