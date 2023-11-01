package Phase1.Chapter1_Basic_Algorithm.Class4_BinaryTree;

import Phase1.Chapter1_Basic_Algorithm.Class3_LinkedList.A_FastSlowPointer;

public class Tranverse {

    static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int v){
            value = v;
        }
    }

    /**
     * 递归序理解：每个节点都回经过n次（从递归节点上方来到或者从递归下方回到）
     * 每次回到的时候都能够执行动作，对于先序、中序、后序遍历，实质上就是在函数递归序的每一个中间点执行打印
     */

    /**
     * 递归先序遍历 递归序前面阶段打印
     * @param head
     */
    public static void recrusionPre(Node head){
        if(head == null) return;
        System.out.println(head.value);
        recrusionPre(head.left);
        recrusionPre(head.right);
    }

    /**
     * 递归中序遍历 递归序中间阶段打印
     * @param head
     */
    public static void inPre(Node head){
        if(head == null) return;
        recrusionPre(head.left);
        System.out.println(head.value);
        recrusionPre(head.right);
    }

    /**
     * 递归后序遍历 递归序最后阶段打印
     * @param head
     */
    public static void posPre(Node head){
        if(head == null) return;
        recrusionPre(head.left);
        recrusionPre(head.right);
        System.out.println(head.value);
    }

    /**
     * 非递归（）
     * @param head
     */
    public static void stackPre(Node head){

    }







}
