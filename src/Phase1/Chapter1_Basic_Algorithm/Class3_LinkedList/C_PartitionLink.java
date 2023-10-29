package Phase1.Chapter1_Basic_Algorithm.Class3_LinkedList;

import util.Util;

public class C_PartitionLink {

    // 将单向链表按某值划分成左边小、中间相等、右边大的形式

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
     * 把链表放入数组里，在数组上做partition (笔试用)
     *
     * @param head
     * @param pivot
     * @return
     */
    public static Node listPartition1(Node head, int pivot) {
        if (head == null) return null;
        // 获取链表总数
        int i = 0;
        Node cur = head;
        while (cur != null) {
            i++;
            cur = cur.next;
        }
        // 创建数组并把所有链表加入数组中
        Node[] nodes = new Node[i];
        for (i = 0, cur = head; cur != null; i++, cur = cur.next) nodes[i] = cur;
        // 荷兰过期方法分割
        arrPartition(nodes, pivot);
        // 将数组写回链表
        for (i = 1; i < nodes.length; i++) nodes[i - 1].next = nodes[i];
        nodes[i - 1].next = null;
        return nodes[0];
    }

    /**
     * 荷兰国旗方法将数组划分
     */
    public static void arrPartition(Node[] arr, int pivot) {
        if (arr == null || arr.length < 2) return;
        int lessBoundary = -1;
        int moreBoundary = arr.length;
        int index = 0;
        while (index < moreBoundary) {
            if (arr[index].value == pivot) index++;
            else if (arr[index].value < pivot) Util.swap(arr, ++lessBoundary, index++);
            else Util.swap(arr, --moreBoundary, index);
        }
    }

    // public static void main(String[] args) {
    //     Integer[] arr = {9, 2, 5, 6, 7, 10, 89, 1, 3, 2};
    //     Node head = listPartition1(arrToList(arr), 8);
    //     while (head != null) {
    //         System.out.print(head.value + ", ");
    //         head = head.next;
    //     }
    // }


    /**
     * 链表直接解法
     * 分成小、中、大三部分，再把各个部分之间串起来 (面试用)
     */
    public static Node listPartition2(Node head, int pivot) {
        if (head == null) return head;
        // 小于区域指针
        Node sH = null;
        Node sT = null;
        // 等于区域指针
        Node eH = null;
        Node eT = null;
        // 大于区域指针
        Node bH = null;
        Node bT = null;

        // 辅助指针
        Node next = null;
        // 遍历整个链表
        while (head != null) {
            next = head.next; // 记住下一个位置，为了让head右移
            head.next = null; // 删除到下一个的链接
            if (head.value < pivot) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = head;
                }
            } else if (head.value == pivot) {
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = head;
                }
            } else {
                if (bH == null) {
                    bH = head;
                    bT = head;
                } else {
                    bT.next = head;
                    bT = head;
                }
            }
            head = next;
        }
        // 三个区域之间连接，处理某个或者多个区域不存在的情况
        if(sH != null){
            sT.next = eH; // eH 可能为null，但是此处的连接不影响，看下一行
            // 当eH为null的时候，eT必然为null，那么此时eT被置为sT，
            // 那么在与大于区域连接的时候，相当于直接把小于区域的尾巴连到了大于区域的头部
            eT = eT != null ? eT : sT;
        }

        if(eT != null){
            eT.next = bH;
        }

        return sH != null ? sH : (eH != null ? eH : bH);

    }

    public static void main(String[] args) {
        Integer[] arr = {9, 2, 5, 6, 7, 10, 89, 1,9, 3, 2, 10};
        Node head = listPartition2(arrToList(arr), 8);
        while (head != null) {
            System.out.print(head.value + ", ");
            head = head.next;
        }
    }
}
