package Chapter1_Basic_Algorithm.Class1_Dichotomy_And_Xor;

public class A_Dichotomy_GetLessIndex {

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

    public static void main(String[] args) {
        System.out.println("Hello");
    }
}
