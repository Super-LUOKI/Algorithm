package Phase1.Chapter1_Basic_Algorithm.Class2_DataStructure_Sort;


import java.util.HashMap;

public class F_TrieTree {
    static class Node {
        public int pass;
        public int end;
        public HashMap<Character, Node> nexts;

        public Node() {
            this.pass = 0;
            this.end = 0;
            this.nexts = new HashMap<>();
        }
    }

    /**
     * 前缀树实现1
     */
    static class Tire {
        private Node root;

        public Tire() {
            this.root = new Node();
        }

        public void insert(String word) {
            if (word == null) return;
            char[] chs = word.toCharArray();
            Node node = root;
            node.pass++; // root不存储pass，不存储end
            for (char ch : chs) {
                if (node.nexts.get(ch) == null) node.nexts.put(ch, new Node()); // root节点存储连接
                node = node.nexts.get(ch);
                node.pass++;
            }
            node.end++;
        }

        /**
         * 获取字符串出现了几次
         *
         * @param word
         * @return 出现次数
         */
        public int search(String word) {
            if (word == null) return 0;
            char[] chs = word.toCharArray();
            Node node = root;
            for (char ch : chs) {
                if (node.nexts.get(ch) == null) return 0;
                node = node.nexts.get(ch);
            }
            return node.end;
        }

        /**
         * 查找所有插入的字符中有几个是以prefix开头的
         *
         * @param prefix
         * @return
         */
        public int prefixNumber(String prefix) {
            if (prefix == null) return 0;
            char[] chs = prefix.toCharArray();
            Node node = root;
            for (char i : chs) {
                if (node.nexts.get(i) == null) return 0;
                node = node.nexts.get(i);
            }
            return node.pass;
        }

        /**
         * 从所有插入的字符串中删除word
         *
         * @param word
         */
        public void delete(String word) {
            if (word == null || search(word) == 0) return;
            char[] chs = word.toCharArray();
            Node node = root;
            node.pass--;
            for (char i : chs) {
                if (--node.nexts.get(i).pass == 0) { // 删除节点以后，如果pass为0则直接断开连接，不必继续删
                    node.nexts.put(i, null);
                    return;
                }
                node = node.nexts.get(i);
            }
            node.end--;
        }
    }


    public static void main(String[] args) {
        String[] strs = {
                "abc",
                "abc",
                "abc",
                "abc",
                "abcdef",
                "def",
                "amd",
                "intel",
                "abcdefgh",
                "abcdefghijk"
        };
        Tire tire = new Tire();
        for (String s : strs) tire.insert(s);
        for (String s : strs) {
            System.out.println("search[" + s + "]: " + tire.search(s));
            System.out.println("prefix[" + s + "]:" + tire.prefixNumber(s));
            System.out.println("delete[" + s + "]");
            tire.delete(s);
        }
        System.out.println("search[abc]: " + tire.search("abc"));


    }
}
