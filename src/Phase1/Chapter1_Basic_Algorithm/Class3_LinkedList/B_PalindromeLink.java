package Phase1.Chapter1_Basic_Algorithm.Class3_LinkedList;

import util.Util;

import java.util.Stack;

public class B_PalindromeLink {

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

        public Node(int v) {
            value = v;
        }
    }

    /**
     * 栈方法特别简单 (笔试用)
     * 给定一个单链表的头节点head，请判断该链表是否为回文结构。
     * （1）用栈实现，此处的栈大小和链表的大小一致。
     * （2）也可也使用快慢指针定位到一半，后半部分入栈，再与前半部分比较，这样额外空间少一半
     * （3）也可也找到中点的时候，让后半部分链表的指针反向往回指，然后开头和结尾两个指针往中间边走边比较
     * 这样不用申请额外空间，但是最后要把指针复原
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome1(Node head) {
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (head != null) {
            if (stack.pop().value != head.value) return false;
            head = head.next;
        }
        return true;
    }

    // public static void main(String[] args) {
    //     Integer[] arr1 = {1, 2, 3, 4, 4, 3, 2, 1};
    //     Util.printArr(arr1);
    //     boolean rst1 = isPalindrome1(arrToList(arr1));
    //     System.out.println(rst1);
    //
    //     Integer[] arr2 = {1, 2, 3, 4, 4, 3, 2, 1, 9};
    //     Util.printArr(arr2);
    //     boolean rst2 = isPalindrome1(arrToList(arr2));
    //     System.out.println(rst2);
    //
    // }



    /**
     *  改原链表的方法就需要注意边界了 (面试用)
     *  额外空间复杂度数 O(1)
     * @param head
     * @return
     */
    public static boolean isPalindrome2(Node head) {
        if (head == null || head.next == null) return true;
        Node n1 = head.next;
        Node n2 = head.next.next;
        // 快慢指针找中点 最终n1为中点（偶数链表则为左中点）
        while (n2.next != null && n2.next.next != null) {
            n1 = n1.next;
            n2 = n2.next.next;
        }

        n2 = n1.next; // 右半边部分第一个节点
        n1.next = null;
        // 右侧逆序（n3为辅助变量）
        Node n3 = null;
        while (n2 != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }
        // 逆序处理结束之后，n1指向最右侧的元素
        n3 = n1; // n3记住右半部分的开始节点（最后一个），便于之后恢复
        n2 = head;
        // 开始比较
        boolean res = true;
        while (n1 != null && n2 != null) {
            if (n1.value != n2.value) {
                res = false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }

        // 恢复节点顺序（n2为辅助变量）
        n1 = n3.next;
        n3.next = null;
        while (n1 != null) {
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return res;
    }


    public static void main(String[] args) {
        Integer[] arr1 = {1, 2, 3, 4, 4, 3, 2, 1};
        Util.printArr(arr1);
        boolean rst1 = isPalindrome2(arrToList(arr1));
        System.out.println(rst1);

        Integer[] arr2 = {1, 2, 3, 4, 4, 3, 2, 1, 9};
        Util.printArr(arr2);
        boolean rst2 = isPalindrome2(arrToList(arr2));
        System.out.println(rst2);

    }
}
