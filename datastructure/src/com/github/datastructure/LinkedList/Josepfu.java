package com.github.datastructure.LinkedList;

/**
 * @Auther: ljq
 * @Date: 2020/1/16 11:48
 * @Description 约瑟夫问题-丢手绢
 */
public class Josepfu {

    public static void main(String[] args) {
        CircleSingleLinkList circleSingleLinkList = new CircleSingleLinkList();
        circleSingleLinkList.create(5);
        circleSingleLinkList.show();
        circleSingleLinkList.pop(1,2);
    }
}

class CircleSingleLinkList{
    /**
     * 默认设置的第一个小孩标志
     */
    private Child first;

    /**
     * 创建环形单链表的方法 nums为要创建的节点个数
     * @param nums
     */
    public void create(int nums){
        if(nums < 2){
            System.out.println("传入人少过少无法创建");
        }
        Child cur = first;
        for(int i = 1; i <= nums; i++){
            Child child = new Child(i);
            //插入第一个小孩的时候 当前节点指向第一个小孩 first指向第一个小孩  当前小孩的下一个节点指向first
            if(i == 1){
                cur = child;
                first = child;
                cur.setNext(child);
            }else {
                cur.setNext(child);
                child.setNext(first);
            }
            cur = cur.getNext();
        }
    }

    /**
     * 出圈方法
     * * 1）需要一个辅助节点 last 表示链表的最后一个节点
     * * 2）当小孩报数时，让 first和last 同步移动 startNo-1下 移动到开始报数的位置  1 2 3  从3号开始  移动 1》2 2》3 两次
     * * 3）每次报数count  例如 count = 2 1》2 移动count-1次
     * * 4）如果last==first 说明圈中只有一个小孩 跳出
     * * 5）出圈 ，first 指向 first.next
     * * 6）last.next 指向重新定向后的first
     * @param startNo 从第几个开始
     * @param count 每次数几个人
     */
    public void pop(int startNo,int count){
        int sums = size();
        if(startNo < 1 || startNo > sums){
            System.out.println("开始号码不存在");
        }
        Child last = first;
        while(last != null){
            if(last.getNext() == first){
                break;
            }
            last = last.getNext();
        }
        for(int i = 1;i < startNo; i++){
            first = first.getNext();
            last = last.getNext();
        }
        while(true){
            if(last == first){
                break;
            }
            for(int i = 0;i < count-1; i++){
                first = first.getNext();
                last = last.getNext();
            }
            System.out.println("当前出圈的小孩是"+first.getNo());
            first = first.getNext();
            last.setNext(first);
        }
        System.out.println("最后出圈的小孩是"+first.getNo());
    }

    /**
     * 显示当前链表的小孩
     */
    public void show(){
        Child cur = first;
        while (cur != null){
            System.out.printf("当前小孩的编号是 %d \n",cur.getNo());
            cur = cur.getNext();
            if(cur == first){
                break;
            }
        }
    }

    /**
     * 获取总个数
     */
    public int size(){
        Child cur = first;
        int size = 0;
        while (cur != null){
            size ++;
            cur = cur.getNext();
            if(cur == first){
                break;
            }
        }
        return size;
    }
}
/**
 * 创建Child 小孩节点
 * */
class Child{
    private int no;
    private Child next;

    public Child(int no){
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Child getNext() {
        return next;
    }

    public void setNext(Child next) {
        this.next = next;
    }
}
