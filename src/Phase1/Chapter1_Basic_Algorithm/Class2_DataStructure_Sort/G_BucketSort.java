package Phase1.Chapter1_Basic_Algorithm.Class2_DataStructure_Sort;

import util.Util;

public class G_BucketSort {

    /**
     * 桶排序中的计数排序
     * 要求数据的范围比较小（从0到最大值比较集中）并且不为负数
     *
     * @param arr
     */
    public static void countSort(Integer[] arr) {
        if (arr == null || arr.length < 2) return;
        int max = Integer.MIN_VALUE;
        for (int i : arr) max = Math.max(i, max);
        int[] bucket = new int[max + 1];
        for (int i : arr) bucket[i]++; // 装桶
        for (int i = 0, j = 0; j < bucket.length; j++) if (bucket[j]-- > 0) arr[i++] = j;  // 出桶
    }

    // public static void main(String[] args) {
    //     Integer[] arr = Util.generateRandomInteger(20, 101, 300);
    //     Util.printArr(arr);
    //     countSort(arr);
    //     Util.printArr(arr);
    // }

    // 基数排序
    public static void radixSort(Integer[] arr) {
        if (arr == null || arr.length < 2) return;
        radixSort(arr, 0, arr.length - 1, maxBites(arr));
    }

    /**
     * 获取数组中最大的数的十进制位数 201 -> 3/98 -> 2/8 -> 1
     *
     * @param arr
     * @return
     */
    public static int maxBites(Integer[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i : arr) max = Math.max(max, i);
        int res = 0;
        for (; max != 0; max /= 10, res++) ;
        return res;
    }

    /**
     * 基数排序真正执行的函数
     *
     * @param arr   待排序数组
     * @param l     排序起始索引
     * @param r     排序结束索引
     * @param digit 最大10进制位数
     */
    public static void radixSort(Integer[] arr, int l, int r, int digit) {
        final int radix = 10; // 基数为10
        Integer[] help = new Integer[r - l + 1]; // 准备辅助空间
        int j = 0;
        // 有多少位就模拟进出桶多少次
        for (int d = 1; d <= digit; d++) {
            int[] count = new int[radix]; // count[x] 存储10进的d位上为x的数的数量
            for(int i = l; i <= r; i++) {
                j = getDigit(arr[i], d);
                count[j]++;
            }
            // count整理为前缀和，count[x] 存储10进的d位上为小于等于x的数的数量
            for(int i = 1; i < radix; i++) count[i] += count[i - 1];
            // 模拟当前位上出桶（从后往前取，保证每次取的都是桶排序概念中当前位上最后出桶的元素）
            for(int i = r; i >= l; i--) {
                j = getDigit(arr[i], d); // 获取当期位上的数
                help[count[j] - 1] = arr[i]; // 取到该数需要放入help数组中的位置并放入数组
                count[j]--;
            }
            // 当前位上桶排序结束，写回数组
            for (int i = 0; i < help.length; i++) arr[l + i] = help[i];
        }
    }

    /**
     * 获取num十进制 第i位 上的数，(789, 2) -> 8, (789, 1) -> 9
     *
     * @param num
     * @return
     */
    public static int getDigit(int num, int i) {
        if (num < 0 || i < 1) throw new RuntimeException("Invalid parameter num or i.");
        for (; i > 1; num /= 10, i--) ;
        return num % 10;
    }

    public static void main(String[] args) {
        Integer[] arr = Util.generateRandomInteger(20, 1, 9999);
        Util.printArr(arr);
        radixSort(arr);
        Util.printArr(arr);
    }
}
