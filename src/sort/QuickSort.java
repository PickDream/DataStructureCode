package sort;

public class QuickSort {

    private static void swap(int[] array,int x,int y){
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }

    /**
     * 对数组的[l,r]边界按照array[m]的值进行划分操作
     * 忽略对参数的检查
     * */
    private static int partition(int[] array,int l,int r,int m){
        int midValue = array[m];
        swap(array,m,r);
        int next = l;
        for (int i = l;i<r;i++){
            if (array[i]<midValue){
                swap(array,next++,i);
            }
        }
        swap(array,next,r);
        return next;
    }

    public static void quickSort(int[]array,int l,int r){
        if (r<=l) return;
        int m = l+(r-l)/2;
        int mid = partition(array,l,r,m);
        quickSort(array,l,mid-1);
        quickSort(array,mid+1,r);
    }
    //简单测试
    public static void main(String[] args) {
        int[] array = new int[]{1,5,9,8,2,3,4,0};
        quickSort(array,0,array.length-1);
        for (int i = 0;i<array.length;i++){
            System.out.println(array[i]);
        }
    }

}
