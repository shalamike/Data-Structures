package datastructures.heaps;

import java.util.*;

public class PriorityQueueProblems {
    public String[] findRelativeRanks(int[] score) {

        int totalAthletes = score.length;

        String[] allRanks = new String[totalAthletes];

//        PriorityQueue<int[]> Ranks = new PriorityQueue<>((a, b) -> b[1] - a[1]);

        PriorityQueue<int[]> athletRanksPriorityQueue = new PriorityQueue<>(new Comparator<int[]>() {

            @Override
            public int compare(int[] a, int[] b) {
                // We want descending order by score (so highest score first)
                return b[1] - a[1];
            }
        });

        for(int athlete = 0; athlete <  totalAthletes; athlete++){
            athletRanksPriorityQueue.offer(new int[] {athlete, score[athlete]} );
        }

        int rank = 1;
        while(!athletRanksPriorityQueue.isEmpty()){
            int[] currentAthlete = athletRanksPriorityQueue.poll();

            int rankIndex = currentAthlete[0];

            switch (rank){
                case 1:
                    allRanks[rankIndex] = "Gold Medal";
                    break;
                case 2:
                    allRanks[rankIndex] = "Silver Meda";
                    break;
                case 3:
                    allRanks[rankIndex] = "Bronze Meda";
                    break;
                default:
                    allRanks[rankIndex] = String.valueOf(rank);
                    break;
            }
            rank++;
        }

        return allRanks;
    }






//    public int[] avoidFlood(int[] rains) {
//
//        int numOfDays = rains.length;
//
//        int[] returnedArr = new int[numOfDays];
//
//        HashMap<Integer, Queue<Integer>> lakeToRains = new HashMap<>();
//
//        for (int day = 0; day < numOfDays; day++){
//            if(rains[day] > 0){
//                if (!lakeToRains.containsKey(rains[day])){
//                    int lakeItRainedOn = rains[day];
//                    Queue<Integer> daysLeft = new LinkedList<Integer>();
//                    lakeToRains.put(lakeItRainedOn,daysLeft);
//                }
//            }
//        }
//
//        HashSet<Integer> fullLakes = new HashSet<>();
//
//        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>();
//
//    }


}

class KthLargest{

    private int k; //the "k" in the kth largest which is the element we are tracking
    private PriorityQueue<Integer> kLargestElementsSoFar; // stores the k largest elements seen so far (min-heap)


    public KthLargest(int k, int[] nums){
        this.k = k;
        this.kLargestElementsSoFar = new PriorityQueue<>();

        for (int num: nums){
            add(num);
        }
    }

    private int add(int num) {
        if(kLargestElementsSoFar.size() < k){
            // if we have fewer element than k elements, we add another element in untill we get to k number of elemebnts
            kLargestElementsSoFar.offer(num);
        } else if (num > kLargestElementsSoFar.peek()){
            // otherwise the new value is larger than the smallest of our k number of elements at the root of the min heap
            // therefore we remove the smallest value and add the new one
            kLargestElementsSoFar.poll();
            kLargestElementsSoFar.offer(num);
        }

        // if val ≤ smallest in heap, it’s not in the top k, so ignore it
        // Therefore, smallest of our top k (heap root) is the kth largest overall
        return kLargestElementsSoFar.peek();
    }

}
