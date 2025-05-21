package sortingalgorythms;

import java.util.Arrays;

public class TwoPointer {

    /* in this scenario, we will be tackling the same problem with multiple different approaches using two pointer
    algorithms.

    The problem:

    given an array of numbers, find two values that add up to our target value n
    */

    //approach 1

    public static int[] targetNumbers1(int[] array, int target){
        int[] returnedArr = new int[2];
        //first pointer which will move slower than the second one.
        for (int pointer1 = 0; pointer1 < array.length; pointer1++){
            //second pointer which will move faster than pointer 1
            for (int pointer2 = pointer1; pointer2 < array.length; pointer2++){
                if (array[pointer1] + array[pointer2] == target){
                    returnedArr[0] = array[pointer1];
                    returnedArr[1] = array[pointer2];
                }
            }
        }
        return returnedArr;
    }

    /*
    the solution above may be the simplest solution, however as we are using a nested loop, it will take the longest to
    execute. an alternitive is use a binary search below:
     */

    public static int[] targetNumbers2(int[] nums, int target){
        int[] returnedArr = new int[2];
        Arrays.stream(nums).sorted();
        //here we create two pointers moving from the left and right of the array simultaniously
        int fromLeft = 0;
        int fromRight = nums.length-1;

        //this while loop will stop executing once either the pointers meet or the two numbers are found
        while(fromLeft<fromRight){
            //checking the sum of the numbers from the two pointers
            int sum = nums[fromLeft] + nums[fromRight];

            //if the sum matches the target, then we return the array;
            if (sum == target){
                returnedArr[0] = nums[fromLeft];
                returnedArr[1] = nums[fromRight];
                break;
            }
            //if the total sum is less than the target value, then we will incrent the left pointer;
            else if (sum <= target)
                fromLeft++;
            //otherwise if the total sum is greater than the target value, the right side will decrement.
            else
                fromRight--;
        }

        return returnedArr;
    }

    /*
    this method works by creating two pointers at either end of the sorted array as shown below


    pointer 1                               pointer 2
      |                                        |
      V                                        V
    { 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 , 10 , 11 }

    then we create a while loop which will terminate when either both pointers meet or they find two numbers
    that add up to our target.

    if the number is too small, pointer 1 will increment.

       pointer 1                            pointer 2
          |                                    |
          V                                    V
    { 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 , 10 , 11 }

    if the number is too large however, then pointer 2 will decrement

       pointer 1                        pointer 2
          |                                |
          V                                V
    { 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 , 10 , 11 }

    once either the target number is found or both pointer meets, the loop will terminate.

    As we are only looping through the array a maximum of one complete times, the time complexity of this two pointer
    method is much less than the regular method of brute forcing it in the first example which is very likely to
    loop over the array multiple times over as the maximum number of loops it will do is equal to the number of elements
    the array.

    here is another problem:

    You are given an integer array height of length n. There are n vertical lines drawn such that the
    two endpoints of the ith line are (i, 0) and (i, height[i]).

    Find two lines that together with the x-axis form a container, such that the container contains the most water.

    Return the maximum amount of water a container can store.

    you may not slant the container.

    https://leetcode.com/problems/container-with-most-water/description/
     */

    public static int maxArea(int[] height){
        int maxArea = 0;
        int leftPointer = 0;
        int rightPointer = height.length - 1;
        while(leftPointer<rightPointer){
            //calculating the total area of the current container
            int currentArea;
            int length = rightPointer - leftPointer;
            //checking which height is the shortest to base our calculations on
            if (height[leftPointer] > height[rightPointer]){
                currentArea = height[rightPointer] * length;
            } else {
                currentArea = height[leftPointer] * length;
            }
            //checking to see if our current maximum area has been beaten
            if (currentArea > maxArea)
                maxArea = currentArea;

            //incrementing the left pointer if its value/height is smaller than the right pointer
            if (height[leftPointer] < height[rightPointer])
                leftPointer++;
            //decrementing the right pointer if its value/height smaller than the left
            else
                rightPointer--;

        }
        return maxArea;
    }

    /*
    in the scenario above we have an array that represents multiple different heights that a container can take where
    the width is the distance (number of elemnets) between the two heights as shown below:

                              |
              |               |
              |   |           |
              |   |   |       |
          |   |   |   |       |
          |   |   |   |   |   |
      |   |   |   |   |   |   |
    { 1 , 3 , 6 , 5 , 4 , 2 , 7}

    and the volume a contaier of water could hold is only as much as the lowest height would allow as shown below:
                              |
                              |
           ___________________|
          |                   |
          |                   |
          |                   |
    { 1 , 3 , 6 , 5 , 4 , 2 , 7}

    In the code solving this problem above, first we create two pointers on our array

    pointer 1             pointer 2
      |                       |
      V                       V

                              |
              |               |
              |   |           |
              |   |   |       |
          |   |   |   |       |
          |   |   |   |   |   |
      |   |   |   |   |   |   |
    { 1 , 3 , 6 , 5 , 4 , 2 , 7}

    from both pointers we will then calculate the area using the smalles height.
    however, in this case, we will increment or decrement depending on the height.
    if the left pointers height is smaller than the right pointers, then the left will increment and if the right was
    smaller than the lefts the right will decrement. this process will repeat untill both pointers meet.

    once both pointers meet, the largest volume recorded will be returned.

     */

    public static int maxProfit(int[] prices){
        int maxProfit = 0;
        int leftPointer = 0;
        int rightPointer = prices.length - 1;

        while (leftPointer < rightPointer){
            int currentProfit = prices[rightPointer] - prices[leftPointer];

            if (currentProfit>=maxProfit) {
                leftPointer++;
                maxProfit = currentProfit;
            }
            else {
                rightPointer--;
            }
        }
        return maxProfit;
    }


    public static void main(String[] args){
        int[] prices = {2,1,4};

        System.out.println(maxProfit(prices));
    }
}
