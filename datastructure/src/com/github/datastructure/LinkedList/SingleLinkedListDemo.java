package com.github.datastructure.LinkedList;

import java.util.Stack;

/**
 * @Auther: ljq
 * @Date: 2020/1/15 10:40
 * @Description 单向列表的实现
 *
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(6);
        Node node3 = new Node(3);

        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(node1);
//        singleLinkedList.add(node2);
//        singleLinkedList.add(node3);

        singleLinkedList.addByOrder(node3);
        singleLinkedList.addByOrder(node2);
        singleLinkedList.addByOrder(node1);
        //singleLinkedList.del(node1);
        //singleLinkedList.del(node3);

        singleLinkedList.show(singleLinkedList.getHead());

        singleLinkedList.reversePrint();

        System.out.printf("当前链表节点数量:%d",singleLinkedList.size(singleLinkedList.getHead()));
        System.out.println();
        //System.out.printf("倒数第%d个参数是%d",0,singleLinkedList.getLastIndexNode(singleLinkedList.getHead(),0).data);
       // singleLinkedList.reverse();
       // System.out.println("链表反转后的链表输出");
        //singleLinkedList.show(singleLinkedList.getHead());
    }
}

/**
 * 单向链表的数据维护
 */
class SingleLinkedList {
    //设定为带头节点的单项链表
    private Node head;

    public Node getHead(){
        return head;
    }

    public SingleLinkedList(){
        this.head = new Node(-1);
    }

    /**
     * 插入方法
     * @param node
     * 1）通过head节点，遍历找到最后的节点
     * 2）在最后的节点后边插入新增的节点
     * 3）需要临时变量进行指针的移动
     */
    public void add(Node node){
        Node temp = head;
        //如果当前节点的下一个节点不为空，指针后移一位，如果当前节点的下一个节点为空，当前节点为最后一个节点
        while (temp.next != null){
            temp = temp.next;
        }
        temp.next = node;
    }

    /**
     * 按顺序添加
     * @param node
     * 1）遍历链表 找到目标temp比当前节点小 但是temp.next比当前节点大的目标位置
     * 2）将要插入节点的next指向temp.next
     * 3)temp.next 指向插入节点
     */
    public void addByOrder(Node node){
        Node temp = head;
        //空链表 直接将要插入的元素插入返回
        if(temp.next == null){
            temp.next = node;
            return;
        }
        boolean flag = false;
        while (temp.next != null){
            if(temp.next.data == node.data){
                flag = true;
                break;
            }else if(temp.next.data > node.data){
                break;
            }
            temp = temp.next;

        }
        if(flag){
            System.out.printf("当前插入的元素:%d已经存在\n",node.data);
            return;
        }

        node.next = temp.next;
        temp.next = node;
    }

    /**
     * 删除元素
     * 1）遍历链表 找到要删除的元素的前一个元素
     * 2）将要删除的前一个元素的next 指向被删除元素的next
     */
    public void del(Node node){
        Node temp = head;
        boolean flag = false;
        while(temp.next != null){
            if(temp.next.data == node.data){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.next = temp.next.next;
        }
    }


    /**
     * 链表反转
     * 1）创建一个新的节点 newNode = new Node();
     * 2）遍历老链表，每取出一个节点，放入新节点的前一位
     * 3）将老节点的head指向新节点
     */
    public void reverse(){
        //空链表或者只有一个节点的链表
        if(head.next == null || head.next.next == null){

        }
        Node newNode = new Node(-1);
        Node cur = head.next;
        Node next = null;
        while (cur != null){
            next = cur.next;
            cur.next = newNode.next;
            newNode.next = cur;
            cur = next;
        }
        head.next = newNode.next;
    }

    /**
     * 获取当前链表节点个数
     * @param head
     * @return
     */
    public int size(Node head){
        Node temp = head.next;
        int count = 0;
        while(temp != null){
            count++;
            temp = temp.next;
        }
        return count;
    }
    /**
     * 遍历方法 顺序输出当前链表的数据
     */
    public void show(Node head){
        Node temp = head.next;
        if(temp == null){
            System.out.println("当前链表为空");
            return;
        }
        //如果当前节点的不为空，输出当前节点,指针后移一位
        System.out.println("输出当前链表的值为：");
        while (temp != null){
            System.out.printf("%d\t",temp.data);
            temp = temp.next;
        }
        System.out.println();
    }

    /**
     * 查找倒数第k个节点
     * 1）需要参数 head和index index表示倒数第几个
     * 2）得到链表总的长度size
     * 3）循环size-index次数 获取对应的元素
     */
    public Node getLastIndexNode(Node head,int index){
        if(head.next == null){
            System.out.println("链表为空");
            return null;
        }
        int size = size(head);
        if(index > size || index <= 0){
            System.out.println("传入的位数小于0或者超过链表最大个数");
            return null;
        }
        Node temp = head.next;
        for(int i = 0;i < size - index; i++){
            temp = temp.next;
        }
        return temp;
    }
    /**
     * 链表反向输出
     * 1）反转再遍历打印，会破坏原有结构 不建议使用
     * 2）使用栈，利用栈的先入后出实现反转打印
     * 这里使用栈的方式实现
     */
    public void reversePrint(){
        if(head.next == null){
            return;
        }
        Node cur = head.next;
        Stack<Node> stack = new Stack<>();
        while (cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        System.out.println("链表反转输出的打印：");
        while(!stack.empty()){
            System.out.print(stack.pop().data+"\t");
        }
    }
}

/**
 * 数据结构
 */
class Node{
    //数据实体
    public int data;
    //数据的下一个关联数据
    public Node next;

    public Node(int data){
        this.data = data;
    }
}