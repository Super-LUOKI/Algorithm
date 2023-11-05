package Phase1.Chapter1_Basic_Algorithm.Class4_BinaryTree;

public class C_BTQuestion {
    static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int v) {
            value = v;
        }

        public static Node newNode(String val){
            if(val == null) return null;
            return new Node(Integer.valueOf(val));
        }
    }

    /**
     * q2
     */
    public static void printTree(Node head){
        printInOrder(head, 0, "H", 17);
    }

    /**
     * 题目q2
     * @param head 二叉树头节点
     * @param height 树的高度（用于记录的中间变量）
     * @param to 节点打印标识符（纯展示）
     * @param len 每个打印的值占的字符数（不够用空格补0，纯展示）
     */
    public static void printInOrder(Node head, int height, String to, int len){
        if(head == null) return;
        // 整体打印顺序是 右头左 因为将一颗二叉树向左旋转90度后，观察从上到下，其实是 右左头 打印顺序的
        printInOrder(head.right, height + 1, "v", len);

        /** 根据递归序，中间打印   start--------------- */
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(val);
        /** 根据递归序，中间打印   end--------------- */

        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int len){
        String space = "";
        for(int i = 0; i < len; i++) space += " ";
        return space;
    }


    /**
     * q1
     * 使用中序遍历方式
     */
    public static Node getSuccessorNode(Node node){
        if(node == null) return null;
        // 以中序遍历的情况下，如果某个节点有右子树，那么其又子树最左边的就是其下一个节点
        if(node.right != null) return getLeftMost(node.right);
        Node parent = node.parent;
        // 如果没有右子树，因为其本身可能是某个节点的左节点，也可能是某个节点的右节点
        // 向上查找，当传入的node在某个父节点的左树上的时候，这个父节点就是其下一个节点（中序遍历）
        while (parent != null && parent.right != node){
            node = parent;
            parent = node.parent;
        }
        return parent;
    }

    public static Node getLeftMost(Node node){
        if(node == null) return null;
        while (node.left != null) node = node.left;
        return node;
    }

    public static void printAllFolds(int N){
        printProcess(1, N, true);
    }

    /**
     * 打印所有折痕
     * 中序遍历思想
     * @param i 当前节点层数（二叉树）
     * @param N 一共的节点层数（不变，用于结束）
     * @param down true: 第i层折痕为凹  false：第i层折痕为凸
     */
    public static void printProcess(int i, int N, boolean down){
        if(i > N) return;
        printProcess(i + 1, N, true);

        System.out.println(down ? '凹' : '凸');

        printProcess(i + 1, N, false);
    }
}
