package com.github.datastructure.stack;

/**
 * @Auther: ljq
 * @Date: 2020/1/16 17:01
 * @Description 链表模拟栈
 */
public class LinkedListStackDemo {

    public static void main(String[] args) {
        LinkedListStack linkedListStack = new LinkedListStack();
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);

        linkedListStack.pop();
        linkedListStack.show();

        linkedListStack.push(node1);
        linkedListStack.push(node2);
        linkedListStack.push(node3);
        linkedListStack.push(node4);
        linkedListStack.push(node5);

        linkedListStack.show();
        linkedListStack.pop();
        linkedListStack.show();
        linkedListStack.pop();
        linkedListStack.show();
        linkedListStack.pop();
        linkedListStack.show();
        linkedListStack.pop();
        linkedListStack.show();
        linkedListStack.pop();
        linkedListStack.show();
    }
}

class LinkedListStack{
    private Node head;

    public LinkedListStack(){
        this.head = new Node(-1);
    }

    public void push(Node node){
        node.setNext(head.getNext());
        head.setNext(node);
    }

    public boolean isEmpty(){
        return head.getNext() == null;
    }
    public void pop(){
        if(isEmpty()){
            System.out.println("栈空");
            return;
        }
        Node cur = head.getNext();
        head.setNext(cur.getNext());
        System.out.println("弹出的数据："+cur.getData());
    }

    public void show(){
        if(isEmpty()){
            System.out.println("栈空");
            return;
        }
        System.out.println("栈内元素如下：");
        Node cur = head.getNext();
        while(cur != null){
            System.out.println(cur.getData());
            cur = cur.getNext();
        }
    }
}

/**
 * 链表结构
 */
class Node{
    private int data;

    private Node next;

    public Node(int data){
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
