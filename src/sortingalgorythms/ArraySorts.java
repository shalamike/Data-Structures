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

   [...](j = arr length) --> [...,10](j=arr length -1) --> [...,9,10](j=arr length -2) --> [...,8,9,10](j=arr length -3)

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




    public static void mergeSort(int inputArr[]){
        int inputLength = inputArr.length;

        /*
        as this is a divide and conquer algorythm, we will keep splitting the array untill we cant divide it anymore.
        therefore, lets make sure our array in its current state can still be split, if not, we will return and break
        out of the recursive loop
         */

        if (inputLength < 2)
            return;

        //get the haflway point of the array
        int midIndex = inputLength/2;

        //splitting our current array into two halves
        int[] leftHalf = new int[midIndex];
        int[] rightHalf = new int[inputLength - midIndex];
        // we take the right half in this way just incase the array has an odd number of elements.

        // populating the left half of our current array
        for (int i = 0; i < midIndex; i++)
            leftHalf[i] = inputArr[i];


        // populating the right half of our array
        for (int i = midIndex; i < inputLength; i++)
            rightHalf[i - midIndex] = inputArr[i];

        /*
        now that we have split our current array into two smaller arrays, we recursively call the mergeSort method on
        each half of our current array. This is demonstrated in the code and diagram below.
         */

        mergeSort(leftHalf);
        mergeSort(rightHalf);

        /*
        Now we have executed the "divide" part of the divide and conquer merge sort algorythm.
        To demonstrate the current operation, lets say our input array known as inputArr is [ 3, 6, 2, 4, 1, 5, 9].
        when we call mergSort on our input array as shown below:

                                        mergeSort(inputArr)

        it will split our input array into two halves.

                    leftHalf        rightHalf
                        |               |
                        |               |
                        V               V
                  [ 3, 6, 2]       [4, 1, 5, 9]

        where on lines 188 and 187, we create the two arrays that will store each half of our input array and on lines
        189 to 196 we populate each half of the array with half the values of our input array. Once we split our array
        we then recursively call our mergeSplit method on both halves of the array and repeat the process as shown below

                            inputArr[] int =  [ 3, 6, 2, 4, 1, 5, 9]
                                                       |
                                                       |  mergeSort(inputArr)
                                                       |
                                                       V
                  leftHalf[] int = [ 3, 6, 2 ]                         rightHalf[] int = [4, 1, 5, 9]
                                        |                                                    |
                                        | mergeSort(leftHalf)                                | mergeSort(rightHalf)
                                        |                                                    |
                                        |                                                    V ... continues splitting
                                        V

          leftHalf  =  [ 3 ]                   rightHalf = [6, 2]
                        |                              |
                        |mergeSort(leftHalf)           |  mergeSort(rightHalf)
                        |                              |
               no longer can be split                  |
              as there is only one element             |
                                                       V
                                     leftHalf = [6]         rightHalf = [2]
                                          |                      |
                                          |                      |
                                          |                      |
                              No Longer can be split as there is only one element in each array

     Eventually we will be left with multiple single element that will be called in our mergeSort method as shown below:

     mergeSort([3]), mergeSort([6]), mergeSort([2]), mergeSort[4]), mergeSort([1]), mergeSort(5]), mergeSort([9])

     As we have the exit statement at the begining of our method on lines 178 to 179 checking if there is less than 2
     elements of our array, we will no longer keep calling mergeSort.

     So now the "conquer" part of the "divide and conquer" merge sort algorythm. for this we will seperate the merge
     part of the algorythm into a seperate method.

         */

        merge(inputArr, leftHalf, rightHalf);

        /*
        on line 245, we call the "merge" method that merges (or "conquers") our merge sort algorythm.
        This method takes 3 parameters:

        inputArr = our current input array that will be sorted using the left and right halves.

        leftHalf = the sorted left half of the array

        rightHalf = the sorted right half of the array.

        The reason why the left and right halves will be sorted in our method call is because when we begin the merging
        process, the left and right half arrays will only have one element. Therefore, they will naturally be sorted
        arrays and as we keep sorting and merging, the left half and right half will be the resul of the sorted merged
        array. This is demonstrated in the diagram below:


        1st lets take the last 4 of the single elements that will be merged together and their inputs as we begin the merging
        process


               inputArr = [4,1]                                   inputArr = [5,9]
        leftHalf = [4]  right half[1]                     leftHalf =  [5]  rightHalf = [9]

        we then compare values in the left and right half of each array and update the value of the input arrays above
        as shown below:


                       input arr = [1,4]                        inputArr = [5,9]

        Now we have two sorted input arrays, as we call the merge method again it will take the following paramters

                 inputArr = [4,1,5,9]
        leftHalf = [1,4]         rightHalf = [5,9]

        with the two sorted input arrays, as shown in line 274, they become our leftHalf and rightHalf as shown in line
        279 and 280. we then compare values in our left and right array and sort the input array as shown below:

                inputArr = [1,4,5,9]

        Note that when we call the Merge, the leftHalf and rightHalf will always be sorted.
        This is because the merge operation that will take place in the method below will only be able to merge if the
        two arrays. As mentioned before, this is also why we divided the array down to single elements as
        single element arrays are always sorted by default.
         */

    }


    private static void merge(int[] toBeMerged, int[] leftHalf, int[] rightHalf){
        /*
        Now we are in the merge part of the mergeSort algorythm or the Conquer part of this divide and conquer algorythm.
        this part works by essentially comparing elements of both the left half and right half of the arrays and then
        adjusting the values for our current toBeMerged array which will become the sorted merged array.
         */

        //total size of our current left half to be merged
        int leftSize = leftHalf.length;
        //total size of our current right half to be merged
        int rightSize = rightHalf.length;

        //pointers that will keep track of our location on the left, right and input array to be merged and sorted.
        int leftHalfPointer = 0, rightHalfPointer = 0, toBeMergedPointer = 0;


        while (leftHalfPointer < leftSize && rightHalfPointer < rightSize){
            //checking to see if the current value in our left half is less than or equal to our right half.
            if (leftHalf[leftHalfPointer] <= rightHalf[rightHalfPointer]){
                //if it is, then we add that to our next element to the input array
                toBeMerged[toBeMergedPointer] = leftHalf[leftHalfPointer];
                //we then increment our leftPointer by 1
                leftHalfPointer++;
            }
            //Otherwise the value current value in our righthalf is bigger then the current value in the left half
            else {
                // Therefore our next value in our merged
                toBeMerged[toBeMergedPointer] = rightHalf[rightHalfPointer];
                //then we increment our rightPointer by 1
                rightHalfPointer++;
            }
            /*
            Now regardless of which value was bigger, after we record that value in our toBeMerged array, we need to
            increment our toBeMergedPointer by 1 so that we can record the next sorted value.
             */
            toBeMergedPointer++;
        }

        /*
        Note that in the condition of our while loop, it will terminate once, one of the pointers reach the end of one
        of the arrays. Therefore, there will still be one half that will have values still in it, these values will be
        the largest values of their current sorted array so we can just add those left over values to our
        arrayToBeMerged.
         */

        //if the left half still has values left over, we just add those remaining values.
        while (leftHalfPointer < leftSize){
            toBeMerged[toBeMergedPointer] = leftHalf[leftHalfPointer];
            leftHalfPointer++;
            toBeMergedPointer++;
        }

        //if the right half still has values left over, we just add those values instead.
        while (rightHalfPointer < rightSize){
            toBeMerged[toBeMergedPointer] = rightHalf[rightHalfPointer];
            rightHalfPointer++;
            toBeMergedPointer++;
        }

        /*
        Note that there is no need for a boolean to check if there is left over values as the initals while statement on
        line 311 would have terminated once one pointer reached the end of its array.
         */

    }

}
