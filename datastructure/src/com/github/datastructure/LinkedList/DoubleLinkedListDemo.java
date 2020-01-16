package com.github.datastructure.LinkedList;

import sun.rmi.server.Activation$ActivationSystemImpl_Stub;

/**
 * @Auther: ljq
 * @Date: 2020/1/15 16:35
 * @Description
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        Node2 node1 = new Node2(1);
        Node2 node2 = new Node2(6);
        Node2 node3 = new Node2(3);
        Node2 node4 = new Node2(3);

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        //doubleLinkedList.add(node1);
        //doubleLinkedList.add(node2);
        //doubleLinkedList.add(node3);

        //doubleLinkedList.show();
        /*doubleLinkedList.del(node2);
        doubleLinkedList.del(node1);
        doubleLinkedList.del(node3);
        System.out.println("删除后的链表数据");
        doubleLinkedList.show();*/

        doubleLinkedList.addByOrder(node1);
        doubleLinkedList.addByOrder(node2);
        doubleLinkedList.addByOrder(node3);
        doubleLinkedList.addByOrder(node4);
        doubleLinkedList.show();

    }
}


/**
 * 模拟双向链表的实现
 */
class DoubleLinkedList{
    private Node2 head;
    public DoubleLinkedList(){
        this.head = new Node2(-1);
    }

    /**
     * 新增方法
     * 1）遍历到当前链表的最后一个节点cur cur.next == null
     * 2)cur.next = newNode
     * 3)newNode.pre = cur
     */
    public void add(Node2 node2){
        Node2 cur = head;
        while(cur.next != null){
            cur = cur.next;
        }
        node2.pre = cur;
        cur.next = node2;
    }

    /**
     * 删除方法
     * 1）找到要删除的节点本身 cur
     * 2) cur.pre.next = cur.next
     * 3) cur.next.pre = cur.pre
     */
    public void del(Node2 node2){
        if(head.next == null){
            return;
        }
        Node2 cur = head.next;
        boolean flag = false;
        while(cur != null){
            if(cur.data == node2.data){
                flag = true;
                break;
            }
            cur = cur.next;
        }
        if(flag){
            if(cur.next != null){
                cur.next.pre = cur.pre;
            }
            if(cur.pre != null){
                cur.pre.next = cur.next;
            }
        }
    }

    /**
     * 按顺序插入
     * 1)如果要插入的节点编号已经存在 直接return
     * 2)找到cur节点的下一个节点 cur.next 如果 cur.next > 插入数据 要插入的数据位置为cur 和cur.next之间
     * 3）newNode2.next = cur.next
     * 4) cur.next.pre = newNode2
     * 5) cur.next = newNode2
     * 6) newNode2.pre = cur
     * @param node2
     */
    public void addByOrder(Node2 node2){
        Node2 cur = head;
        boolean flag = false;
        while(cur.next != null){
            if(cur.next.data == node2.data){
                System.out.printf("数据%d已经存在不能新增\n",node2.data);
                flag = true;
                break;
            }
            if(cur.next.data > node2.data){
                break;
            }
            cur = cur.next;
        }
        if(flag){
           return;
        }
        node2.next = cur.next;
        if(cur.next != null){
            cur.next.pre = node2;
        }
        node2.pre = cur;
        cur.next = node2;
    }

    /**
     * 遍历
     */
    public void show(){
        Node2 temp = head.next;
        System.out.println("当前链表数据为：");
        while (temp != null){
            System.out.printf(temp.data+"\t");
            temp = temp.next;
        }
    }

}

/**
 * 数据结构 相对于单向链表 多了pre节点
 */
class Node2{
    //数据实体
    public int data;
    //数据的下一个关联数据
    public Node2 next;
    //数据的上一个关联数据
    public Node2 pre;

    public Node2(int data){
        this.data = data;
    }
}