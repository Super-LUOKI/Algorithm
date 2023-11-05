package Phase1.Chapter1_Basic_Algorithm.Class4_BinaryTree;

public class D_BtGeneralMethod {
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


    /** q1 */
    public static boolean isBalanced(Node head){
        return process2(head).isBalanced;
    }

    static class Info {
        public boolean isBalanced;
        public int height;

        public Info(boolean b, int h){
            isBalanced = b;
            height = h;
        }
    }

    public static Info process2(Node head){
        if(head == null) return new Info(true, 0); // 空树是平衡的且高度为0

        Info leftInfo = process2(head.left);
        Info rightInfo = process2(head.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1; // 加上自己本身贡献1的高度

        boolean isBalanced = true;
        if(!leftInfo.isBalanced || !rightInfo.isBalanced || Math.abs(leftInfo.height - rightInfo.height) > 1)
            isBalanced = false;
        return new Info(isBalanced, height);

    }
}
