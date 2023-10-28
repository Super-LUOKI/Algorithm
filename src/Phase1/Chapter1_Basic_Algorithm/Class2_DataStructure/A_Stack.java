package Phase1.Chapter1_Basic_Algorithm.Class2_DataStructure;

import util.Util;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class A_Stack {

    /**
     * 实现一个特殊的栈，在基本功能的基础上，再实现返回栈中最小元素的功能
     * 要求：pop/push/getMin时间复杂度为O(1)
     * 见图 2-1
     */
    static class MyStack{
        private Stack<Integer> dataStack;
        private Stack<Integer> minStack;

        public MyStack(){
            this.dataStack = new Stack<>();
            this.minStack = new Stack<>();
        }

        public void push(int num){
            if(minStack.empty() || num < minStack.peek()){
                minStack.push(num);
            }else{
                minStack.push(this.minStack.peek());
            }
            dataStack.push(num);
        }

        public int pop(){
            if(dataStack.empty()) throw new RuntimeException("Your Stack is Empty.");
            minStack.pop();
            return dataStack.pop();
        }

        public int getMin(){
            if(minStack.empty()) throw new RuntimeException("Your Stack is Empty.");
            return this.minStack.peek();
        }


    }

    // public static void main(String[] args) {
    //     Integer[] arr = Util.generateRandomInteger(10, 1, 20);
    //     MyStack ms = new MyStack();
    //     System.out.println("入栈");
    //     for (int i : arr) {
    //         ms.push(i);
    //         System.out.print("----i = "+i +", min = "+ms.getMin());
    //     }
    //     System.out.println("\n弹栈");
    //     for(int i = 0; i < arr.length; i++){
    //         System.out.print("----i = "+ms.pop() +", min = "+(i < arr.length - 1 ? ms.getMin() : "empty"));
    //     }
    // }

    /**
     * 双栈实现队列
     * 一个栈为push栈，一个栈为pop栈
     * 执行入队列操作时：1,2,3,4,5 进入push栈
     * 执行出队列操作时：把push的数据导到pop中，从pop栈进行出栈以实现队列的效果
     * 注意：从push栈到pop栈导数据的时候，一定要保证以下两点：
     * 1、pop栈为空才能导
     * 2、push栈导入到pop时，一定要一次性导完
     */
    static class TwoStackQueue {
        private Stack<Integer> pushStack;
        private Stack<Integer> popStack;

        public TwoStackQueue(){
            pushStack = new Stack<>();
            popStack = new Stack<>();
        }

        private void pushToPop(){
            if(popStack.empty())
                while (!pushStack.empty()) popStack.add(pushStack.pop());
        }

        public void add(int num){
            pushStack.push(num);
            pushToPop();
        }

        public int poll(){
            if(popStack.empty() && pushStack.empty()) throw new RuntimeException("Queue is Empty.");
            pushToPop();
            return popStack.pop();
        }
    }

    // public static void main(String[] args) {
    //     Integer[] arr = Util.generateRandomInteger(10, 10, 99);
    //     TwoStackQueue queue = new TwoStackQueue();
    //     for(int i: arr) queue.add(i);
    //     Util.printArr(arr);
    //     System.out.println();
    //     for (int i: arr) System.out.print(queue.poll()+ ", ");
    // }


    /**
     * 双队列实现栈
     * 一个队列作为data队列，一个队列作为help队列
     * 当入栈操作执行时：1,2,3,4,5 进入data
     * 当出栈操作执行时：data导到help中，只留最后一个在data中，然后data出队列返回
     * 交换data和help
     * 如此往复
     */
    static class TwoQueueStack {
        private Queue<Integer> dataQueue;
        private Queue<Integer> helpQueue;

        public TwoQueueStack(){
            this.dataQueue = new LinkedList<>();
            this.helpQueue = new LinkedList<>();
        }

        public void push(int num){
            dataQueue.add(num);
        }

        public int pop(){
            // 导数据
            if(dataQueue.isEmpty()) throw new RuntimeException("Stack is Empty.");
            while (dataQueue.size() != 1) helpQueue.add(dataQueue.poll());
            int rst = dataQueue.poll();
            Queue<Integer> temp = dataQueue;
            dataQueue = helpQueue;
            helpQueue = temp;
            return rst;
        }

    }

    // public static void main(String[] args) {
    //     Integer[] arr = Util.generateRandomInteger(10, 10, 99);
    //     TwoQueueStack stack = new TwoQueueStack();
    //     for(int i: arr) stack.push(i);
    //     Util.printArr(arr);
    //     System.out.println();
    //     for (int i: arr) System.out.print(stack.pop()+ ", ");
    // }



}
