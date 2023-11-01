package Phase1.Chapter1_Basic_Algorithm.Class4_BinaryTree;

import Phase1.Chapter1_Basic_Algorithm.Class3_LinkedList.A_FastSlowPointer;

import java.util.Stack;

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
     * 非递归（栈实现）
     * @param head
     */
    public static void stackPre(Node head){
        if(head == null) return;
        Stack<Node> stack = new Stack<>();
        stack.add(head);
        while (!stack.empty()){ // 因为先序遍历是 头左右，且栈是先进后出的逆序结构，所以想要先打印左节点就应该先入右孩子
            head = stack.pop();
            System.out.println(head.value);
            if(head.right != null) stack.push(head.right);
            if(head.left != null) stack.push(head.left);

        }
    }

    /**
     * 非递归后序遍历（栈实现）
     * 因为先序遍历是 头左右 观察发现，上方栈实现的先序遍历 头左右，只要改变下一下后两个顺序，变成  头右左，其反过来就是后续遍历顺序
     * @param head
     */
    public static void stackPos1(Node head){
        if(head == null) return;
        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();
        s1.push(head);
        while (!s1.empty()){
            head = s1.pop();
            s2.push(head); // s1出栈 s2就入栈
            if(head.left != null) s1.push(head.left);
            if(head.right != null) s1.push(head.right);
        }
        while (!s2.empty()) System.out.println(s2.pop().value);
    }

    /**
     * 非递归中序遍历
     * @param head
     */
    public static void stackIn(Node head){
        if(head == null) return;
        Stack<Node> stack = new Stack<>();
        while (!stack.empty() || head != null){
            if(head != null){
                stack.push(head);
                head = head.left; // 往左边一条路走到黑
            }else{
                head = stack.pop();
                System.out.println(head.value);
                head = head.right; // 如果左边的路不可走则打印再往右走
            }
        }
    }

    /**
     * 只用一个栈实现 非递归后序遍历
     * @param head
     */
    public static void stackPos2(Node head){
        if(head == null) return;
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        Node c;
        while (!stack.empty()){
            c = stack.peek();
            if(c.left != null && head != c.left && head != c.right){
                stack.push(c.left);
            }else if(c.right != null && head != c.right){
                stack.push(c.right);
            }else{
                System.out.println(stack.pop().value);
                head = c;
            }
        }
    }







}
