package Phase1.Chapter1_Basic_Algorithm.Class4_BinaryTree;

import Phase1.Chapter1_Basic_Algorithm.Class3_LinkedList.A_FastSlowPointer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
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

    /**
     * 层序遍历
     * 使用队列实现层序遍历
     * @param head
     */
    public static void level(Node head){
        if(head == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            System.out.println(cur.value);
            if(cur.left != null) queue.add(cur.left);
            if(cur.right != null) queue.add(cur.right);
        }
    }

    /**
     * 获取树中，具有节点最多的一层的节点数
     * 重点在于：知道某层啥时候开始或者啥时候结束
     *
     * @param head
     * @return
     */
    public static int level1(Node head){
        if(head == null) return -1;
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        HashMap<Node, Integer> levelMap = new HashMap<>(); // key 节点 value 节点坐在层数
        levelMap.put(head, 1);
        int curLevel = 1; // 当前所在的层
        int currLevelNodes = 0; // 当前层的节点数
        int max = 0; // 最大多节点层的节点数
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            int curNodeLevel = levelMap.get(cur);
            if(cur.left != null) {
                levelMap.put(cur.left, curNodeLevel + 1);
                queue.add(cur.left);
            }
            if(cur.right != null){
                levelMap.put(cur.right, curNodeLevel + 1);
                queue.add(cur.right);
            }
            if(curNodeLevel == curLevel){
                currLevelNodes++; // 如果取出来的节点所在的层数与当前记录的遍历所在的层数一致，则增加节点数量
            }else{
                // 当某层最后一个节点遍历完，下一层第一个节点出队列的时候，辉触发这个分支
                // 也就是说，我们知道这个时候上一层已经结束。这一层已经开始
                max = Math.max(max, currLevelNodes);
                curLevel++; // 当前层数 + 1
                currLevelNodes = 1; // 将当前层的节点数记录为1，因为此时新层已经有一个节点出队列了
            }
        }
        max = Math.max(max, currLevelNodes); // 上方的逻辑中，会缺失最后一层的比较，所以这里要补上
        return max;

    }

    /**
     * 获取树中，具有节点最多的一层的节点数
     * 这个方法相较于上一个方法，不需要使用HashMap,因为算法结果并不要求记住每个节点在那一层
     * 只需要标记当前层结束或者开始即可
     * @param head
     * @return
     */
    public static int level3(Node head){
        if(head == null) return -1;
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node curEnd = head; // 当前层结束最后一个节点
        Node nextEnd = null; // 下一层最后一个节点
        int max = 0;
        int curLevelNodes = 0; // 当前层节点数
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            if(cur.left != null){
                queue.add(cur.left);
                nextEnd = cur.left; // 不断更新记录值，最终结果就是下一层的最后一个节点值
            }
            if(cur.right != null){
                queue.add(cur.right);
                nextEnd = cur.right;
            }
            curLevelNodes++; // 出栈了，所以当前层节点 + 1
            if(cur == curEnd){
                max = Math.max(max, curLevelNodes);
                curLevelNodes = 0; // 重置层的节点数
                curEnd = nextEnd;
            }
        }
        return max;
    }

}
