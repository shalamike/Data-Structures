package probemsnsolutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public static List<List<Integer>> threeSum(int[] nums) {

        //  Create a list to store all unique triplets that sum to zero.
        List<List<Integer>> result = new ArrayList<>();

        //  Step 1: Sort the array in ascending order.
        // Sorting allows us to use the two-pointer approach efficiently.
        Arrays.sort(nums);

        //  Step 2: Loop through each number as a potential first element (base) of the triplet.
        // We go up to length - 2 because we need at least 3 numbers for a triplet.
        for (int i = 0; i < nums.length - 2; i++) {

            //  Step 2.1: Skip duplicate base numbers.
            // If the current number is the same as the previous one, we’d form the same triplets again.
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            //  Step 3: Set up two pointers for the remaining part of the array.
            // 'left' starts just after 'i', 'right' starts at the end of the array.
            int left = i + 1;
            int right = nums.length - 1;

            //  Step 4: Move the two pointers inward while they don’t cross.
            while (left < right) {

                // ➕ Step 4.1: Calculate the sum of the three numbers.
                int sum = nums[i] + nums[left] + nums[right];

                // ✅ CASE 1: Found a triplet that sums to zero.
                if (sum == 0) {

                    //  Add the valid triplet to the result list.
                    // Arrays.asList() creates a fixed-size list from the three numbers.
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    //  Step 4.2: Skip over duplicate values for 'left' to avoid duplicate triplets.
                    while (left < right && nums[left] == nums[left + 1]) left++;

                    //  Step 4.3: Skip over duplicate values for 'right' as well.
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    // ↔ Step 4.4: Move both pointers inward to search for new combinations.
                    left++;
                    right--;
                }

                //  CASE 2: Sum is too small (negative) → we need a larger value.
                // Move 'left' right to increase the sum.
                else if (sum < 0) {
                    left++;
                }

                //  CASE 3: Sum is too large (positive) → we need a smaller value.
                // Move 'right' left to decrease the sum.
                else {
                    right--;
                }
            }
        }

        // 🎁 Step 5: Return the list of all unique triplets that sum to zero.
        return result;
    }


    public static void main(String[] args){
        int[] nums = {-1,0,1,2,-1,-4};

        System.out.println(threeSum(nums));

    }
}
