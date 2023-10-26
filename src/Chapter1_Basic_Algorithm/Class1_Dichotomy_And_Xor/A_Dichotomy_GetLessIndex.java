package Chapter1_Basic_Algorithm.Class1_Dichotomy_And_Xor;
import util.Util;

public class A_Dichotomy_GetLessIndex {

    /** 插入排序 */
    public static void selectionSort(Integer[] arr){
        if(arr == null || arr.length < 2) return;
        for(int i = 0; i < arr.length - 1; i++){
            int minIndex = i;
            for(int j = i + 1; j < arr.length; j++){
                minIndex = arr[j] < arr[minIndex] ? j : minIndex; // 注意不要写错，这里不要把minIndex写成了i
            }
            Util.swap(arr, i, minIndex);
        }
    }
    public static void main1(String[] args) {
        Integer[] arr = Util.generateRandomInteger(20, 1,99);
        Util.printArr(arr);
        selectionSort(arr);
        Util.printArr(arr);

    }

    // public static void main(String[] args) {
    //     Integer[] arr = Util.generateRandomInteger(20, 1,99);
    //     Util.printArr(arr);
    //     selectionSort(arr);
    //     Util.printArr(arr);
    //
    // }

    /**
     * 获取有序数组arr（从小到大）中小于中间数的数的最小索引（题意不清）
     * @param arr 有序数组
     * @return 最小索引
     */
    public static int getLessIndex(int[] arr){
        if(arr == null || arr.length == 0) return -1;
        if(arr.length == 1 || arr[0] < arr[1]) return 0;

        return  -1;
    }


}
