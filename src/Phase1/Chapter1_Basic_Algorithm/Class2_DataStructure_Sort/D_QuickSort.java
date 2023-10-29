package Phase1.Chapter1_Basic_Algorithm.Class2_DataStructure_Sort;

import util.Util;

public class D_QuickSort {

    /**
     * 给定一个数组arr，和一个整数num。请把小于等于n的数放在数组的左边大于等于num的数放在数组的右边
     * num为arr[r]
     * 要求额外空间复杂度O(1)、时间复杂度O(N)
     *
     * @param arr
     * @param l
     * @param r
     * @return 中点num位置
     */
    public static int partition(Integer[] arr, int l, int r) {
        if (l > r) return -1;
        if (l == r) return l;
        // 左侧区域边界( <arr[r] )
        int lessBoundary = l - 1;
        int index = l;
        while (index < r) {
            if (arr[index] < arr[r]) Util.swap(arr, index, ++lessBoundary);
            index++;
        }
        Util.swap(arr, ++lessBoundary, r);
        return lessBoundary;
    }
    // public static void main(String[] args) {
    //     Integer[] arr = Util.generateRandomInteger(20, 10, 99);
    //     Util.printArr(arr);
    //     int rst = partition(arr, 0, arr.length - 1);
    //     System.out.println(rst);
    //     Util.printArr(arr);
    // }

    /**
     * 荷兰国旗问题：以arr[r]为界，将arr分割成 [(小于arr[r]), (等于arr[r]), (大于arr[r])]三部分
     *
     * @param arr
     * @param l
     * @param r
     * @return [(等于arr[r]的左边界index), (等于arr[r]的右边界index)]
     */
    public static int[] netherlandsFlag(Integer[] arr, int l, int r) {
        if (l > r) return new int[]{-1, -1};
        if (l == r) return new int[]{l, r};
        int lessBoundary = l - 1;
        int moreBoundary = r;
        int index = l;
        while (index < moreBoundary) {
            if (arr[index] == arr[r]) index++;
            else if (arr[index] < arr[r]) Util.swap(arr, ++lessBoundary, index++);
            else Util.swap(arr, --moreBoundary, index);
        }
        Util.swap(arr, moreBoundary, r);
        return new int[]{lessBoundary + 1, moreBoundary};
    }

    // public static void main(String[] args) {
    //     Integer[] arr = Util.generateRandomInteger(20, 1, 9);
    //     Util.printArr(arr);
    //     int[] rst = netherlandsFlag(arr, 0, arr.length - 1);
    //     System.out.println("[" + rst[0] + "," + rst[1] + "]");
    //     Util.printArr(arr);
    // }

    /**
     * 快速排序1.0
     * 每次递归确定一个中间数的位置
     *
     * @param arr
     */
    public static void quickSort1(Integer[] arr) {
        if (arr == null || arr.length < 2) return;
        process1(arr, 0, arr.length - 1);
    }

    public static void process1(Integer[] arr, int l, int r) {
        if (l >= r) return;
        int mid = partition(arr, l, r);
        process1(arr, l, mid - 1);
        process1(arr, mid + 1, r);
    }

    /**
     * 快速排序2.0
     * 中间相等的数的位置与最终结果的位置是一致的，所以一次性确定多个，提高效率
     * 最差时间复杂度O(N^2)
     *
     * @param arr
     */
    public static void quickSort2(Integer[] arr) {
        if (arr == null || arr.length < 2) return;
        process2(arr, 0, arr.length - 1);
    }

    public static void process2(Integer[] arr, int l, int r) {
        if (l >= r) return;
        int[] equalArea = netherlandsFlag(arr, l, r);
        process2(arr, l, equalArea[0] - 1);
        process2(arr, equalArea[1] + 1, r);
    }

    /**
     * 快速排序3.0
     * 在2.0的基础上概率选取中点，从统计上将时间复杂度将为 O(N*logN)
     *
     * @param arr
     */
    public static void quickSort3(Integer[] arr) {
        if (arr == null || arr.length < 2) return;
        process3(arr, 0, arr.length - 1);
    }

    public static void process3(Integer[] arr, int l, int r) {
        if (l >= r) return;
        Util.swap(arr,l +  (int)(Math.random() * (r - l + 1)), r);
        int[] equalArea = netherlandsFlag(arr, l, r);
        process3(arr, l, equalArea[0] - 1);
        process3(arr, equalArea[1] + 1, r);
    }

    public static void main(String[] args) {
        Integer[] arr = Util.generateRandomInteger(20, 10, 99);
        Util.printArr(arr);
        // quickSort1(arr);
        // quickSort2(arr);
        quickSort3(arr);
        Util.printArr(arr);
    }
}
