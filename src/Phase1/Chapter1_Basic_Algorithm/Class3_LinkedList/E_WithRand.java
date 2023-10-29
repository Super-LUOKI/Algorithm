package Phase1.Chapter1_Basic_Algorithm.Class3_LinkedList;

import java.util.HashMap;

public class E_WithRand {
    public static Node arrToList(Integer[] arr) {
        Node root = null;
        Node node = new Node(arr[0]);
        root = node;
        for (int i = 1; i < arr.length; i++) {
            Node no = new Node(arr[i]);
            node.next = no;
            node = no;
        }
        return root;
    }

    static class Node {
        public int value;
        public Node next;
        public Node rand;

        public Node(int v) {
            value = v;
        }
    }

    // 一种特殊的单链表节点类描述如下
    // class Node {
    //     int value;
    //     Node next;
    //     Node rand;
    //     Node(int val) { value = val; }
    // }
    // rand指针是单链表节点结构中新增的指针，rand可能指向链表中的任意一个节点，也可能指向null.
    // 给定一个由Node节点类型组成的无环单链表的头节点 head，请实现一个函数完成这个链表的复制，并返回复制的新链表的头节点
    // 【要求】时间复杂度O(N).额外空间复杂度O(1)

    /**
     * 简单方法：使用HashMap实现
     * @param head
     * @return
     */
    public static Node copyListWithRand1(Node head){
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        // 使用next指针将所有节点先复制一遍
        while (cur != null){
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        // 处理next与rand的连接
        while (cur != null){
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }

    /**
     * 复杂方法：不使用HashMap实现
     * 思路图见3-1
     */
    public static Node copyListWithRand2(Node head){
        if(head == null) return null;
        Node cur = head;
        Node next = null;
        // 复制节点并将复制的节点放到原来节点的后方
        while (cur != null){
            next = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = next;
            cur = next;
        }
        // 设置克隆节点的rand
        cur = head;
        Node curCopy = null;
        while (cur != null){
            next = cur.next.next; // 非克隆节点的下一个
            curCopy = cur.next; // 当前节点的克隆节点
            curCopy.rand = cur.rand != null ? cur.rand.next : null;
            cur = next;
        }
        // 将克隆节点与原节点分离
        Node res = head.next; // 最终克隆结果的头节点
        cur = head;
        while (cur != null){
            next = cur.next.next; // 非克隆节点的下一个
            curCopy = cur.next; // 当前克隆节点
            cur.next = next; // 原来节点连接复原
            curCopy.next = next != null ? next.next : null; // 连接克隆节点
            cur = next;
        }
        return res;
    }
    

}
