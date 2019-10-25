package sort.forkjoin;

import sort.QuickSort;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class QuickSortForkAndJoin {
    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool fjp = new ForkJoinPool();
        int[] array = new int[]{1,5,9,8,2,3,4,0};
        fjp.execute(new QuickSortFJ(array,0,array.length-1));
        Arrays.stream(array).forEach(System.out::println);
    }

    static class QuickSortFJ extends RecursiveTask<Void>{

        private int[] array;
        int l,r;
        private void swap(int x,int y){
            int temp = array[x];
            array[x] = array[y];
            array[y] = temp;
        }
        private int partition(int l,int r,int m){
            int midValue = array[m];
            swap(m,r);
            int next = l;
            for (int i = l;i<r;i++){
                if (array[i]<midValue){
                    swap(next++,i);
                }
            }
            swap(next,r);
            return next;
        }
        public QuickSortFJ(int[] array,int l,int r){
            this.array = array;
            this.l = l;
            this.r = r;
        }
        @Override
        protected Void compute() {
            if (r<=l) return null;
            int m = l+(r-l)/2;
            int mid = partition(l,r,m);
            //左边的开始分叉递归操作
            QuickSortFJ leftFj =  new QuickSortFJ(this.array,l,mid-1);
            //执行异步操作
            leftFj.fork();
            QuickSortFJ rightFj =  new QuickSortFJ(this.array,mid+1,r);
            //没有汇聚操作
            rightFj.fork();
            return null;
        }
    }
}
