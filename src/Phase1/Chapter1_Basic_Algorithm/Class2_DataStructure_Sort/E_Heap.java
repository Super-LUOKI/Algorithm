package Phase1.Chapter1_Basic_Algorithm.Class2_DataStructure_Sort;

import util.Util;

import java.util.PriorityQueue;

public class E_Heap {
    /**
     * 使用数组实现的大根堆
     */
    static class MyMaxHeap {
        private Integer[] heap;
        private final int limit;
        private int heapSize;

        public MyMaxHeap(int limit) {
            this.heap = new Integer[limit];
            this.limit = limit;
            this.heapSize = 0;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        public void push(int value) {
            if (heapSize == limit) throw new RuntimeException("Heap is full.");
            heap[heapSize] = value;
            heapInsert(heap, heapSize++);
        }

        public int pop() {
            if (heapSize == 0) throw new RuntimeException("Heap is empty.");
            int ans = heap[0];
            Util.swap(heap, 0, --heapSize);
            heapify(heap, 0, heapSize);
            return ans;
        }

        /**
         * 插入时，大根堆调整
         *
         * @param arr
         * @param index
         */
        private static void heapInsert(Integer[] arr, int index) {
            while (arr[index] > arr[(index - 1) / 2]) {
                Util.swap(arr, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        /**
         * 取出最大元素时调整大根堆
         * 当我的孩子都不再比我大或者已经没有孩子了就停止
         *
         * @param arr
         * @param index
         * @param heapSize
         */
        private static void heapify(Integer[] arr, int index, int heapSize) {
            // 左孩子下标
            int left = index * 2 + 1;
            while (left < heapSize) {
                // 左右孩子最大值的下标
                int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
                // 孩子中最大值与父亲比，确定最大值的下标
                largest = arr[largest] > arr[index] ? largest : index;
                // 如果最大值下标与父亲下标相同则表明不需要调整了
                if (largest == index) break;
                // 否则继续调整
                Util.swap(arr, index, largest);
                index = largest;
                left = index * 2 + 1;
            }
        }

        /**
         * 堆排序
         * @param arr
         */
        public static void heapSort(Integer[] arr){
            if(arr == null || arr.length < 2) return;
            // 将数组调整为堆结构
            // for(int i = 0; i < arr.length; i++) heapInsert(arr, i); // O(N*logN)
            // 优化写法：将arr视为完全二叉树，然后调整为堆
            for (int i = arr.length - 1; i >= 0; i--) heapify(arr, i, arr.length); // O(N)
            // 将最大值放到最后
            int heapSize = arr.length;
            Util.swap(arr, 0, --heapSize);
            // <调整->最大值放最后>循环
            while (heapSize > 0){
                heapify(arr, 0, heapSize);
                Util.swap(arr, 0, --heapSize);
            }
        }

    }

    // public static void main(String[] args) {
    //     Integer[] arr = Util.generateRandomInteger(20, 10, 99);
    //     MyMaxHeap heap = new MyMaxHeap(100);
    //     Util.printArr(arr);
    //     for (int i : arr) heap.push(i);
    //     for (int i: arr) System.out.print(heap.pop()+ ", ");
    // }

    // public static void main(String[] args) {
    //     Integer[] arr = Util.generateRandomInteger(20, 10, 99);
    //     Util.printArr(arr);
    //     MyMaxHeap.heapSort(arr);
    //     Util.printArr(arr);
    //
    // }

    /**
     * 已知一个几乎有序的数组。几乎有序是指，如果把数组排好顺序的话，
     * 每个元素移动的距离一定不超过k，并且k相对于数组长度来说是比较小的
     * @param arr
     * @param k
     */
    public void sortedArrDistanceLessK(Integer[] arr, int k){
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        // 先把前 k + 1 个数组成小根堆
        for(;index <= Math.min(arr.length - 1, k); index++) heap.add(arr[index]); // TODO: 有个问题，这里应该是 <= 原来是 < 需要查看原视频
        // 循环出堆入堆
        int i = 0;
        for(; index < arr.length; i++, index++) {
            arr[i] = heap.poll();
            heap.add(arr[index]);
        }
        // 将剩余在最小堆内的数放到数组尾部
        while (!heap.isEmpty()) arr[i++] = heap.poll();
    }
}
