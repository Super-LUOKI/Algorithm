package Phase1.Chapter1_Basic_Algorithm.Class1_Dichotomy_And_Xor;

import util.Util;

public class A_Dichotomy_GetLessIndex {

    /**
     * 插入排序
     */
    public static void selectionSort(Integer[] arr) {
        if (arr == null || arr.length < 2) return;
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex; // 注意不要写错，这里不要把minIndex写成了i
            }
            Util.swap(arr, i, minIndex);
        }
    }

    // public static void main(String[] args) {
    //     Integer[] arr = Util.generateRandomInteger(20, 1, 99);
    //     Util.printArr(arr);
    //     selectionSort(arr);
    //     Util.printArr(arr);
    //
    // }

    /**
     * 冒泡排序
     */
    public static void bubbleSort(Integer[] arr) {
        if (arr == null || arr.length < 2) return;
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j <= i - 1; j++) { // j <= i - 1是因为下方下标会取到 j + 1
                if (arr[j] > arr[j + 1]) Util.swap(arr, j, j + 1);
            }
        }
    }

    // public static void main(String[] args) {
    //     Integer[] arr = Util.generateRandomInteger(20, 1,99);
    //     Util.printArr(arr);
    //     bubbleSort(arr);
    //     Util.printArr(arr);
    //
    // }


    /**
     * TODO: 算法有问题，请检查
     */
    /**
     * 二分：在sortedArr上找满足 >=value 最左边数的索引
     *
     * @param sortedArr
     * @param value     不满足则返回-1
     */
    public static int nearestIndex(int[] sortedArr, int value) {
        if (sortedArr == null || sortedArr.length == 0) return -1;
        int l = 0;
        int r = sortedArr.length - 1;
        int index = -1;
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (sortedArr[mid] >= value) {
                index = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return index;
    }

     public static void main(String[] args) {
         int[] arr = {1, 2, 9};
         System.out.println(nearestIndex(arr, 5)); // 3
     }

    /**
     * 寻找任意一个局部最小数的索引
     * 局部最小定义：如果num左边的数和右边的数都比num大，那么num就是局部最小
     * 边界情况：num为第一数或者是最后一个数，那么只需要与旁边的比较，如果num最小，那么它也是局部最小
     * @param arr 无序数组（相邻不等）
     * @return 最小索引
     */
    public static int getLessIndex(int[] arr) {
        if (arr == null || arr.length == 0) return -1;
        if(arr.length == 1 || arr[0] < arr[1]) return 0;
        if (arr[arr.length - 1] < arr[arr.length - 2]) return arr.length - 1;
        int l = 0;
        int r = arr.length - 1;
        while (l < r){
            int mid = l + ((r - l) >> 1);
            if(arr[mid] > arr[mid - 1]){
                // 中间的比左边大
                r = mid - 1;
                // 想法，让  r = mid 这样可以让极端情况下，让左边数组范围保持存在局部最小，用 r = mid - 1 会不会有问题呢？
                // 不会，见图 1-1
            }else if(arr[mid] > arr[mid + 1]){
                // 中间的比右边大
                l = mid + 1;
            }else{
                // 中间的比两边都小
                return mid;
            }
        }
        return l;
    }

//    public static void main(String[] args) {
//        int[] arr = {7, 2, 4, 5, 4, 2, 1, 5, 6, 8, 9, 90};
//        System.out.println(getLessIndex(arr)); // 3
//    }


}
