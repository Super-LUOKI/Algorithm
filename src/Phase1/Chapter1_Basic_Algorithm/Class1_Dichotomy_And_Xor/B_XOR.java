package Phase1.Chapter1_Basic_Algorithm.Class1_Dichotomy_And_Xor;

public class B_XOR {

    /**
     * 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，找到并打印这种数
     *
     * @param arr
     */
    public static void printOddTimesNum1(Integer[] arr) {
        Integer eor = 0;
        for (int i : arr) eor ^= i; // 激活基本特征，无进位相加导致相同的消除得到0
        System.out.println(eor);
    }
    // public static void main(String[] args) {
    //     Integer[] arr = {1,1,2,2,3,3,4,4,6,7,7,8,8,9,9};
    //     printOddTimesNum1(arr);
    // }

    /**
     * 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，找到并打印这两种
     *
     * @param arr
     */
    public static void printOddTimesNum2(Integer[] arr) {
        int eor = 0;
        for (int i : arr) eor ^= i; // 遍历结束以后，eor为 两个出现奇数次数的异或结果 假设为 a ^ b
        // 这两种数不相等，所以我们从结果的二进制中最低位向最高位开始找第一个1
        int rightOne = eor & (~eor + 1); // 这一步是取到最低位的1(其余位上为0)
        // 接下来我们要找到 a ^ b 的结果中，与rightOne最低位相等的数
        // （因为异或特性，相同消除，所以 a ^ b为1的位上，在a和b中对应位只能是一个是1一个是0）
        int onlyOne = 0;
        // 将所有与rightOne的1的位上相同的所有数异或一次，相同消除，
        // 所有偶数次的数消除，对于a和b，在rightOne的1的位上只有一个会为1，所以必然这样就能得到在rightOne的1的位上为1的数
        for (int i : arr) if ((i & rightOne) != 0) onlyOne ^= i;
        System.out.println(onlyOne + "," + (eor ^ onlyOne));
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 1, 2, 2, 3, 3, 4, 4, 6, 7, 7, 8, 8, 9, 9, 10};
        printOddTimesNum2(arr);
    }
}
