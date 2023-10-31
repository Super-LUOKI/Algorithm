package Practice;

import util.Util;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
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
                index = (index - 1) / 2; // 向上调整  向上调整 向上调整
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
            int left = index * 2 + 1;
//            走完整个堆
            while (left < heapSize) {
//                取头左右三者堆最大值堆索引
                int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
                largest = arr[largest] > arr[index] ? largest : index;
                if (largest == index) break; // 已经是大根堆
                Util.swap(arr, index, largest);
                index = largest; // 向下继续调整
                left = index * 2 + 1;
            }
        }

        /**
         * 堆排序
         *
         * @param arr
         */
        public static void heapSort(Integer[] arr) {
            if(arr == null || arr.length < 2) return;
            for(int i = arr.length - 1; i >= 0; i--) heapify(arr, i, arr.length); // 将原数组是视为完全二叉树，将其调整为最大堆
            // 不断从最大堆中出数据，填充到原来堆数组中，将最大堆数据放到堆尾，堆尾 -1 实现堆堆减小
            int heapSize = arr.length;
            Util.swap(arr, 0, --heapSize);
            while (heapSize > 0){
                heapify(arr, 0, heapSize);
                Util.swap(arr, 0, --heapSize);
            }
        }
    }



    public void sortedArrDistanceLessK(Integer[] arr, int k){
        
    }
}
