package Phase1.Chapter1_Basic_Algorithm.Class2_DataStructure_Sort;

public class C_MergeSort {

    /**
     * 归并排序递归实现
     *
     * @param arr
     */
    public static void mergeSort1(Integer[] arr) {
        if (arr == null || arr.length == 1) return;
        process(arr, 0, arr.length - 1);
    }

    private static void process(Integer[] arr, int l, int r) {
        if (l == r) return;
        int mid = l + ((r - l) >> 1);
        process(arr, l, mid);
        process(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    private static void merge(Integer[] arr, int l, int mid, int r) {
        Integer[] help = new Integer[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= r) help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        while (p1 <= mid) help[i++] = arr[p1++];
        while (p2 <= r) help[i++] = arr[p2++];
        for (i = 0; i < help.length; i++) arr[l + i] = help[i];
    }

    /**
     * 归并排序 非递归实现
     *
     * @param arr
     */
    public static void mergeSort2(Integer[] arr) {
        if (arr == null || arr.length < 2) return;
        int N = arr.length;
        int mergeSize = 1; // 每组半部分的长度
        while (mergeSize < N) {
            int l = 0;
            // 进行当前粒度排序
            while (l < N) {
                // 不断分组归并
                int mid = l + mergeSize - 1;
                if (mid >= N) break; // 十分重要，这里不判断会角标越界
                int r = Math.min(mid + mergeSize, N - 1);
                merge(arr, l, mid, r); // 归并过程处理是真正的排序操作
                // 排序当前粒度的下一组
                l = r + 1;
            }
            // 此处逻辑上不加也可以，这里的主要作用是为了防止下方左翼操作，
            // 突破int能表示的最大范围而溢出
            if (mergeSize > N / 2) break;
            mergeSize <<= 1; // 当前粒度排序结束后，扩大排序粒度
        }
    }
    // public static void main(String[] args) {
    //     Integer[] arr = Util.generateRandomInteger(20, 10, 99);
    //     Util.printArr(arr);
    //     // mergeSort1(arr);
    //     mergeSort2(arr);
    //     Util.printArr(arr);
    // }

    /**
     * 归并排序实质：是把数分左右组，以一个组为中心，其中每一个数与另一组比较，然后再往外层的另一组比较
     * 适用问题：
     * 在数组中，某个数（所有数），左边或者右边比它大或小的所有数
     */

    /**
     * 求数的小和
     * 在个数组中，一个数左边比它小的数的总和，叫数的小和，所有数的小和累加起来，叫数组小和
     * 例子: [1,3,4,2,5]
     * 1左边比1小的数:没有
     * 3左边比3小的数:1
     * 4左边比4小的数:1、3
     * 2左边比2小的数:1
     * 5左边比5小的数:1、3、4、2
     * 所以数组的小和为1+1+3+1+1+3+4+2=16
     */
    public static int getSmallSummation(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        return processSm(arr, 0, arr.length - 1);
    }

    public static int processSm(int[] arr, int l, int r) {
        if (l == r) return 0;
        int mid = l + ((r - l) >> 1);
        return processSm(arr, l, mid) + processSm(arr, mid + 1, r) + mergeSm(arr, l, mid, r);
    }

    public static int mergeSm(int[] arr, int l, int mid, int r) {
        // 归并的过程中计算最小和
        Integer[] help = new Integer[r - l + 1];
        int res = 0;
        int i = 0;
        int p1 = l;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= r) {
            // 计算最小和
            res += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) help[i++] = arr[p1++];
        while (p2 <= r) help[i++] = arr[p2++];
        for (i = 0; i < help.length; i++) arr[l + i] = help[i];
        return res;
    }

    // public static void main(String[] args) {
    //     System.out.println(getSmallSummation(new int[]{1, 3, 4, 2, 5}));
    // }

    /**
     * 求逆序对个数
     * 如 [3, 1, 7, 0, 2]
     * 对于 3 无
     * 对于1 (3, 1)
     * 对于7 无
     * 对于0 (3, 0) (1, 0) (7, 0)
     * 对于2 (3, 2) (7, 2)
     * 所以逆序对为 (3, 1)  (3, 0) (1, 0) (7, 0) (3, 2) (7, 2)
     */
    public static int getReverseOrderPair(Integer[] arr) {
        if (arr == null || arr.length < 2) return 0;
        return processRO(arr, 0, arr.length - 1);
    }

    public static int processRO(Integer[] arr, int l, int r) {
        if (l == r) return 0;
        int mid = l + ((r - l) >> 1);
        return processRO(arr, l, mid) + processRO(arr, mid + 1, r) + mergeRO(arr, l, mid, r);
    }

    public static int mergeRO(Integer[] arr, int l, int mid, int r) {
        // 归并的过程中计算最小和
        Integer[] help = new Integer[r - l + 1];
        int res = 0;
        int i = 0;
        int p1 = l;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= r) {
            // 计算逆序对个数
            res += arr[p1] > arr[p2] ? (mid - p1 + 1) : 0;
            // if(arr[p1] > arr[p2]){
            //     // 打印所有逆序对
            //     for (int j = p1; j <= mid; j++) System.out.print("("+arr[j]+", "+arr[p2]+"), ");
            // }
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) help[i++] = arr[p1++];
        while (p2 <= r) help[i++] = arr[p2++];
        for (i = 0; i < help.length; i++) arr[l + i] = help[i];
        return res;
    }

    public static void main(String[] args) {
        System.out.println(getReverseOrderPair(new Integer[]{3, 1, 7, 0, 2}));
    }

}
