package util;

public class Util {
    public static <T> void swap(T[] arr, int i, int j){
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static Integer[] generateRandomInteger(int length, int start, int end){
        Integer[] arr = new Integer[length];
        for (int i = 0; i < length; i++){
            int random = start + (int)(Math.random() * (end - start) + 1);
            arr[i] = random;
        }
        return arr;
    }

    public static <T> void printArr(T[] arr, String suffix){
        for (T t : arr) {
            System.out.print(t.toString() + suffix);
        }
    }

    public static <T> void printArr(T[] arr){
        printArr(arr, ", ");
        System.out.println();
    }
}
