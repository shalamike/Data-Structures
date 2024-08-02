package sortingalgorythms;

import java.util.Arrays;

public class Main {

    public static void main (String args[]){

        int[] arr = {64, 34, 25, 12, 22, 11, 90, 1, 4};

//        ArrayStructures.bubbleSort(arr);
        ArraySorts.insertionSort(arr);
        System.out.println(Arrays.toString(arr));

    }
}
