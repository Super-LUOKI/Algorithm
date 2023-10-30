package Practice;

import util.Util;

public class Main {
    public static int nearestIndex1(int[] sortedArr, int value){
        if(sortedArr == null || sortedArr.length == 0) return -1;
        int l = 0;
        int r = sortedArr.length - 1;
        int index = -1;
        while (l < r){
            int mid = l + ((r - l) >> 1);
            if(sortedArr[mid] >= value){
                index = mid;
                r = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        return index;
    }

    public static int nearestIndex(int[] arr, int value){
        if(arr == null || arr.length == 0) return -1;
        int l = 0;
        int r = arr.length -1;
        return -1;
    }
}
