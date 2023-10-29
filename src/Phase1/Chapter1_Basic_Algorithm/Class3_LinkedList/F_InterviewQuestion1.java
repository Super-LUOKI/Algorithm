package Phase1.Chapter1_Basic_Algorithm.Class3_LinkedList;

public class F_InterviewQuestion1 {

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


    // 给定两个可能有环也可能无环的单链表，头节点head1和head2
    // 请实现一个函数，如果两个链表相交，请返回相交的 第一个节点。
    // 如果不相交，返回null
    // [要求]：如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度 请达到O(1)。
    // 思路图：3-2、3-3


    /**
     * 找到链表第一个入环的节点，如果不存环则返回null
     * 使用快慢指针实现：
     * 原理（不用关心为什么是这样）：
     * 1. 快指针一次移动两步、慢指针一次移动一步
     * 2. 如果存在环那么必然二者会在一个地方相遇
     * a. 此时固定慢指针的位置，快指针回到开头
     * b. 继续快慢指针都一次只走一步，最终二者会在入环节点处相遇
     *
     * @param head
     * @return
     */
    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) return null;
        Node n1 = head.next; // 慢指针
        Node n2 = head.next.next; // 快指针
        while (n1 != n2) {
            if (n1 == null || n2 == null) return null;
            n1 = n1.next;
            n2 = n2.next.next;
        }

        // 重置快指针
        n2 = head;
        while (n1 != n2) {
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1;
    }

    /**
     * head1和head2都没有环，求如果二者相交则返回第一个相交的节点否则返回null
     * 注意：如果相交一定是Y结构，因为链表节点只会有一个next指向
     */
    public static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) return null;
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0; // 用于计算两条链表的长度差
        while (cur1.next != null) { // 注意此处是 cur1.next != null 而不是 cur1.next != null
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }
        if (cur1 != cur2) return null; // 两条链表都走到最后一个节点后，节点不相等，如果不相交那么链表不相交
        // 执行到这里还能继续说明必然相交，现在寻找第一个相交的节点
        cur1 = n > 0 ? head1 : head2; // 谁长谁为cur1
        cur2 = cur1 == head1 ? head2 : head1; // 谁长短谁为cur2

        // 先让cur1（长的链表）的位置距离Y型分叉点与短的等长的位置
        n = Math.abs(n);
        while (n-- != 0) cur1 = cur1.next;
        while (cur1 != cur1) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    /**
     * 两个有环链表，返回第一个相交的节点，如果不相交则返回null
     *
     * @param head1 链表1头节点
     * @param loop1 链表1的入环节点
     * @param head2 链表2头节点
     * @param loop2 链表2的入环节点
     * @return
     */
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = null;
        Node cur2 = null;

        if (loop1 == loop1) {
            // 如果二者入环节点相等，则此时判断交点和环无关，从Y型上判断就好（参考noLoop）
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n-- != 0) cur1 = cur1.next;
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            // 二者入环节点不同，则在环上相交，或者二者不相交
            cur1 = loop1.next;
            while (cur1 != loop1) {
                if (cur1 == loop2) return loop1; // 相交则返回其中一个入环节点即可
                cur1 = cur1.next;
            }
            // 不相交则直接返回null
            return null;

        }
    }

    /**
     * 主方法
     */
    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) return null;
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if (loop1 == null && loop1 == null) return noLoop(head1, head2);
        if (loop1 != null && loop2 != null) return bothLoop(head1, loop1, head2, loop2);
        return null; // 一个有环一个无~环不可能相交
    }

}
