package sortingalgorythms;

public class ArraySorts {

    //the bubble sort algorythm
    /*
    the bubble sort array works by repeatedly checking each adjacent elements in an array and swapping them if they
    are in the wrong order as shown in the example below;

    [... <4, 2,> 3 ...] -->  [... <2, 4,> 3 ...]
    [... 2, <4, 3> ...] -->  [... 2, <3, 4> ...]

    As a result, in each iteration of the array the largest number will be pushed to the back of the array.
    As each loop will only push the largest element to the back of the list, numerous run throughs will be needed that
    can be determined by the size of the array.
    in the example below, the number of iterations in the nested loop reduces as the largest number gets pushed
    to the back of the array chronologically as demonstrated below:

   [...](j = arr length) --> [...,10](j=arr length -1) --> [...,9,10](j=arr length -2) --> [...,8,9,10](j=arr length -2)

   As a result, the algorythm splits the array with a sorted region at the end and the unsorted region at the begining.
   gradually the sorted region increases in size as the itterations progress as shown below:

            index of sorted
            region
                  |
                  V
   [............4,8,9,10]
                ^
             index of
             unsoted region

    as the itterations progress, the sorted region will get bigger and bigger until the list is sorted.

   It's called a bubble swap because we are looking at a bubble of two elements within an array.
     */
    public static void bubbleSort(int[] arr) {
        int arrLength = arr.length;
        for (int i = 0; i < arrLength - 1; i++) {  // this line determines how many times we will run through the list
            for (int j = 0; j < arrLength - i - 1; j++) { // this line represents our current loop through the list.
                if (arr[j] > arr[j + 1]) { // if the numbers are in the wrong order as shown above, the elements are swapped
                    // Swap arr[j] and arr[j + 1]
                    int temp = arr[j]; // creating a temporary int to store the value at arr[j]
                    arr[j] = arr[j + 1]; // setting the current value of arr[j] to the next value in the array j[j+1]
                    arr[j + 1] = temp; // setting the current value of [j+1] to our temp to complete the bubble swap
                }
            }
        }
    }

/*
the selectionSort algorythm works by repeatedly looping through the array and finding the smallest element in each loop.
during each loop, the location(index) of the current smallest element is saved to a temporary value as shown in the
example below:


  index of            index of
  first unsorted      current minimum
  value in array      value of array
     |                  |
     V                  V
    [10,.............., 1,......]

    once the index of the smallest value is found, the array swaps the position of the smallest value with the current
    first value as shown below:

    smallest value       previous location
    moved to the         of the smallest value
    front after          after the swap.
    swap
    |                        |
    V                        V
    [1,..................., 10,......]
   by doing this, the smallest values in the arrays will constantly be shifted foreward untill the array is sorted a
   s demonstrated below:

     index of            index of
  next unsorted      current minimum
  value in array      value of array
        |                   |
        V                   V
    [1, 7,.................,2,.....]

     index of          previous location
  next smallest       of the smallest value
  value in array      after the swap
        |                   |
        V                   V
    [1, 2,.................,7,.....]
        ^
        |
     current index of
     sorted region of the
     array

    As a result, after every run through with the algorythm, current smallest index can be removed as
    the algorythm splits the array into a sorted region and an unsorted region
    its called a selection sort as we are selecting a value in the array to swap with the current value
    at the front of the array that hasn't been sorted yet.
 */
    public static void selectionSort(int[] arr) {
        int arraySize = arr.length;
        for (int i = 0; i < arraySize - 1; i++) { // this line determines how many times we will run through the array
            int minIndex = i; // this int represents our pointer to the location of the current smallest element in the array
            //the next line represents our current run. note that after each run we remove the next element from the array
            for (int j = i + 1; j < arraySize; j++) { // as the front of the array has been sorted.
                if (arr[j] < arr[minIndex]) { // this statement checks to se if the current unsorted value at the front is smaller then the current value in the run
                    minIndex = j; //if so the index will be set to J
                }
            }
            // once the index of the smallest element is found, we swap it with the current unsorted element at the front
            //of the list
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    public static void insertionSort(int[] arr) {
        int arraySize = arr.length;
        //line below represents current size of sorted part of array
        for (int i = 1; i < arraySize; ++i) { //  also determines how many times we will run through array
            int valueToCompare = arr[i]; // saving the value to compare to temporary memory as it may get errased by a larger value in the array at its current position
            int j = i - 1; // setting the index of the current value of the array to be behind the value to compare
            int currentValue = arr[j];
            /*
            the while loop below will continuously execute as long as BOTH conditions are true,
            either that j is not less than 0 if the value we are comparing against is smaller then the
            very first value in the array and the index will be out of bounds
            AND
            the current value we are comparing against is greater then the current value in our array.
            if the current value is greater then the value that we are comparing against, the next value in the array
            will assume its current value and the index of our current value will be shifted back one. Otherwisw we will
            then enter the sorted region of the array and the loop will terminate.
             */
            while (j >= 0 && currentValue > valueToCompare) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            /*
            in this part, j represtents the index of the last value in our sorted region while current first is the next
            sorted value to be added to it.
             */
            int currentFirst = j+1;
            arr[currentFirst] = valueToCompare;
        }
    }


    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];
    }

}
