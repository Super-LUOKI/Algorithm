package Phase1.Chapter1_Basic_Algorithm.Class4_BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class Serialize {
    static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }

        public static Node newNode(String val){
            if(val == null) return null;
            return new Node(Integer.valueOf(val));
        }
    }

    // 二叉树序列化（二叉树与别的形式的数据互转）（先序遍历）
    public static Queue<String> preSerial(Node head) {
        Queue<String> ans = new LinkedList<>();
        pres(head, ans);
        return ans;
    }

    /**
     * 序列化实现方法
     * 先序遍历方式
     * @param head
     * @param ans
     * @return
     */
    public static void pres(Node head, Queue<String> ans) {
        if (head == null) {
            ans.add(null);
        } else {
            // 递归序，先序添加到队列中
            ans.add(String.valueOf(head.value));
            pres(head.left, ans);
            pres(head.right, ans);
        }
    }

    /**
     * 将 Queue<String> 反序列化（先序遍历方式）为 二叉树
     */
    public static Node buildByPreQueue(Queue<String> prelist){
        if(prelist == null || prelist.size() == 0) return null;
        return preb(prelist);
    }

    /**
     * 反序列化实现（先序遍历方式）
     * @param prelist
     * @return
     */
    public static Node preb(Queue<String> prelist){
        String value = prelist.poll();
        if(value == null) return null;
        Node head = new Node(Integer.valueOf(value));
        head.left = preb(prelist);
        head.right = preb(prelist);
        return head;
    }

    /**
     * 层序遍历序列化实现
     * 这是宽度优先搜索（队列实现），与深度不一样（递归实现）
     */
    public static Queue<String> levelSerial(Node head){
        Queue<String> ans = new LinkedList<>();
        if(head == null){
            return null;
        } else{
            ans.add(String.valueOf(head.value));
            Queue<Node> queue = new LinkedList<>();
            queue.add(head);
            while (!queue.isEmpty()){
                head = queue.poll();
                if(head.left != null){
                    ans.add(String.valueOf(head.left.value));
                    queue.add(head.left);
                }else{
                    ans.add(null);
                }

                if(head.right != null){
                    ans.add(String.valueOf(head.right.value));
                    queue.add(head.right);
                }else{
                    ans.add(null);
                }
            }
        }
        return ans;
    }

    /**
     * 宽度优先遍历逆序列化
     */
    public static Node buildByLevelQueue(Queue<String> levelList){
        if(levelList == null || levelList.size() == 0) return null;
        Node head = Node.newNode(levelList.poll());
        Queue<Node> queue = new LinkedList<>();
        if(head != null) queue.add(head);
        Node node = null;
        while (!queue.isEmpty()){
            node = queue.poll();
            node.left = Node.newNode(levelList.poll());
            node.right = Node.newNode(levelList.poll());
            if(node.left != null) queue.add(node.left);
            if(node.right != null) queue.add(node.right);
        }
        return head;
    }

}
