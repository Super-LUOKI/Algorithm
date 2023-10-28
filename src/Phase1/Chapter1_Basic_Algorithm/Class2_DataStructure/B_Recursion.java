package Phase1.Chapter1_Basic_Algorithm.Class2_DataStructure;

import util.Util;

public class B_Recursion {

    /**
     * 获取arr的最大值（无序数组）
     * 这类递归问题的时间复杂度是很容易确定的，见图2-2，2-3，2-4
     * @param arr
     * @return
     */
    public static int getMax(Integer[] arr){
        return process(arr, 0, arr.length - 1);
    }

    public static int process(Integer[] arr, int l, int r){
        if(l == r) return arr[l];
        int mid = l + ((r - l) >> 1);
        int leftMax = process(arr, l, mid);
        int rightMax = process(arr, mid + 1, r);
        return Math.max(leftMax, rightMax);
    }

    // public static void main(String[] args) {
    //     Integer[] arr = Util.generateRandomInteger(20, 10, 99);
    //     Util.printArr(arr);
    //     System.out.println(getMax(arr));
    // }
}
