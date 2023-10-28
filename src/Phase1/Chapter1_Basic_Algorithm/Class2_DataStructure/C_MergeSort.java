package Phase1.Chapter1_Basic_Algorithm.Class2_DataStructure;

import util.Util;

public class C_MergeSort {

    /**
     * 归并排序递归实现
     * @param arr
     */
    public static void mergeSort1(Integer[] arr){
        if(arr == null || arr.length == 1) return;
        process(arr, 0, arr.length - 1);
    }

    private static void process(Integer[] arr, int l, int r){
        if(l == r) return;
        int mid = l + ((r - l) >> 1);
        process(arr, l, mid);
        process(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    private static void merge(Integer[] arr, int l, int mid, int r){
        Integer[] help = new Integer[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= r) help[i++] =  arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        while (p1 <= mid) help[i++] = arr[p1++];
        while (p2 <= r) help[i++] = arr[p2++];
        for(i = 0; i < help.length; i++) arr[l + i] = help[i];
    }

    /**
     * 归并排序 非递归实现
     * @param arr
     */
    public static void mergeSort2(Integer[] arr){

    }
    public static void main(String[] args) {
        Integer[] arr = Util.generateRandomInteger(20, 10, 99);
        Util.printArr(arr);
        // mergeSort1(arr);
        Util.printArr(arr);
    }
}
