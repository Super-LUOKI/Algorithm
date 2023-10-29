package Phase1.Chapter1_Basic_Algorithm.Class3_LinkedList;

import util.Util;

public class A_FastSlowPointer {

    public static Node arrToList(Integer[] arr){
        Node root = null;
        Node node = new Node(arr[0]);
        root = node;
        for(int i = 1; i < arr.length; i++) {
            Node no = new Node(arr[i]);
            node.next = no;
            node = no;
        }
        return root;
    }
    static class Node {
        public int value;
        public Node next;
        public Node(int v){
            value = v;
        }
    }
    // 1) 输入链表头节点，奇数长度返回中点，偶数长度返回上中点
    public static Node midOrUpMidNode(Node head){
        if(head == null || head.next == null || head.next.next == null) return head;
        // 至少有3个节点
        // 二者差一个 偶数情况是是上中点
        Node slow = head.next;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null){ // 因为fast最开始已经站到了链表的一个位置上，所以对于偶数链表，可以跳的范围是一个奇数
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    // public static void main(String[] args) {
    //     Integer[] arr = Util.generateRandomInteger(6, 10, 99);
    //     Util.printArr(arr);
    //     Node head = arrToList(arr);
    //     System.out.println(midOrUpMidNode(head).value);
    //
    //     Integer[] arr1 = Util.generateRandomInteger(7, 10, 99);
    //     Util.printArr(arr1);
    //     Node head1 = arrToList(arr1);
    //     System.out.println(midOrUpMidNode(head1).value);
    //
    // }

    // 2) 输入链表头节点，奇数长度返回中点，偶数长度返回下中点
    public static Node midOrDownMidNode(Node head){
        if(head == null || head.next == null) return head;
        // 二者相同是下中点
        Node slow = head.next;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // public static void main(String[] args) {
    //     Integer[] arr = Util.generateRandomInteger(6, 10, 99);
    //     Util.printArr(arr);
    //     Node head = arrToList(arr);
    //     System.out.println(midOrDownMidNode(head).value);
    //
    //     Integer[] arr1 = Util.generateRandomInteger(7, 10, 99);
    //     Util.printArr(arr1);
    //     Node head1 = arrToList(arr1);
    //     System.out.println(midOrDownMidNode(head1).value);
    //
    // }


    // 3) 输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
    public static Node midOrUpPreMidNode(Node head){
        if(head == null || head.next == null || head.next.next == null) return null;
        Node slow = head;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // public static void main(String[] args) {
    //     Integer[] arr = Util.generateRandomInteger(6, 10, 99);
    //     Util.printArr(arr);
    //     Node head = arrToList(arr);
    //     System.out.println(midOrUpPreMidNode(head).value);
    //
    //     Integer[] arr1 = Util.generateRandomInteger(7, 10, 99);
    //     Util.printArr(arr1);
    //     Node head1 = arrToList(arr1);
    //     System.out.println(midOrUpPreMidNode(head1).value);
    //
    // }

    // 4)输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
    public static Node midOrDownMidPreNode(Node head){
        if(head == null || head.next == null) return null;
        if(head.next.next == null) return head;
        Node slow = head;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        Integer[] arr = Util.generateRandomInteger(6, 10, 99);
        Util.printArr(arr);
        Node head = arrToList(arr);
        System.out.println(midOrDownMidPreNode(head).value);

        Integer[] arr1 = Util.generateRandomInteger(7, 10, 99);
        Util.printArr(arr1);
        Node head1 = arrToList(arr1);
        System.out.println(midOrDownMidPreNode(head1).value);

    }

}
