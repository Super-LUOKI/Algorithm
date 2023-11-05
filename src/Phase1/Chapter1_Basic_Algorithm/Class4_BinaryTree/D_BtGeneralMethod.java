package Phase1.Chapter1_Basic_Algorithm.Class4_BinaryTree;

import java.util.ArrayList;
import java.util.List;

public class D_BtGeneralMethod {
    static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int v) {
            value = v;
        }

        public static Node newNode(String val) {
            if (val == null) return null;
            return new Node(Integer.valueOf(val));
        }
    }


    /**
     * q1
     */
    public static boolean isBalanced(Node head) {
        return process2(head).isBalanced;
    }

    static class Info {
        public boolean isBalanced;
        public int height;

        public Info(boolean b, int h) {
            isBalanced = b;
            height = h;
        }
    }

    public static Info process2(Node head) {
        if (head == null) return new Info(true, 0); // 空树是平衡的且高度为0

        Info leftInfo = process2(head.left);
        Info rightInfo = process2(head.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1; // 加上自己本身贡献1的高度

        boolean isBalanced = true;
        if (!leftInfo.isBalanced || !rightInfo.isBalanced || Math.abs(leftInfo.height - rightInfo.height) > 1)
            isBalanced = false;
        return new Info(isBalanced, height);
    }


    static class Info1 {
        public int maxDistance;
        public int height;

        public Info1(int dis, int h) {
            maxDistance = dis;
            height = h;
        }
    }

    public static Info1 process(Node node) {
        if (node == null) return new Info1(0, 0);

        Info1 leftInfo = process(node.left);
        Info1 rightInfo = process(node.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int maxDis = Math.max(
                Math.max(leftInfo.maxDistance, rightInfo.maxDistance),
                leftInfo.height + rightInfo.height + 1
        );

        return new Info1(maxDis, height);
    }


    static class Info2 {
        // 整棵子树是否都是搜索二叉树
        public boolean isAllBST;
        public int maxSubBSTSize;
        /**
         * 左树的最大值
         */
        public int max;
        /**
         * 右树的最小值
         */
        public int min;

        public Info2(boolean is, int size, int mi, int ma) {
            isAllBST = is;
            maxSubBSTSize = size;
            max = ma;
            min = mi;
        }
    }

    public static Info2 processInfo2(Node node) {
        if (node == null) return null;

        Info2 leftInfo = processInfo2(node.left);
        Info2 rightInfo = processInfo2(node.right);

        int max = node.value;
        int min = node.value;

        if (node.left != null) {
            max = Math.max(leftInfo.max, max);
            min = Math.min(leftInfo.min, min);
        }
        if (node.right != null) {
            max = Math.max(rightInfo.max, max);
            min = Math.min(rightInfo.min, min);
        }

        int maxSubBSTSize = 0;
        if (leftInfo != null) {
            maxSubBSTSize = leftInfo.maxSubBSTSize;
        }
        if (rightInfo != null) {
            maxSubBSTSize = Math.max(maxSubBSTSize, rightInfo.maxSubBSTSize);
        }
        boolean isAllBST = false;
        if (
                (leftInfo == null || leftInfo.isAllBST) &&
                        (rightInfo == null || rightInfo.isAllBST) &&
                        (leftInfo == null || leftInfo.max < node.value) &&
                        (rightInfo == null || rightInfo.min > node.value)
        ) {
            int leftSize = leftInfo == null ? 0 : leftInfo.maxSubBSTSize;
            int rightSize = rightInfo == null ? 0 : rightInfo.maxSubBSTSize;
            maxSubBSTSize = leftSize + rightSize + 1;
            isAllBST = true;
        }


        return new Info2(isAllBST, maxSubBSTSize, min, max);
    }


    static class Employee {
        public int happy;
        public List<Employee> nexts;

        public Employee(int h){
            happy = h;
            nexts = new ArrayList<>();
        }
    }

    static class InfoEmp {
        public int yes; // 来的情况下最大快乐值
        public int no; // 不来的情况下最大快乐值

        public InfoEmp(int y, int n){
            yes = y;
            no = n;
        }
    }

    public static InfoEmp processEmp(Employee node){
        // 基层员工，来就快乐，不来就不快乐，不存在下级问题
        if(node.nexts.isEmpty()) return new InfoEmp(node.happy, 0);
        int yes = node.happy;
        int no = 0;
        for (Employee next : node.nexts){
            InfoEmp nextInfo = processEmp(next);
            yes += nextInfo.no; // 自己来，下级肯定不来
            // 如果自己不来，则看下级（整个子树）来或者不来，取最高快乐值
            no += Math.max(nextInfo.yes, nextInfo.no);
        }
        return new InfoEmp(yes, no);
    }


}
