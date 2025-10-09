package datastructures.heaps;

public class MinHeap {

    // Heap array
    int[] heap;
    int size;

    public MinHeap(int[] array) {
        this.heap = array;
        this.size = array.length;
    }

    // Heapify at index i in a Min-Heap
    void heapify(int i) {
        int smallest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // Check if left child is smaller than root
        if (left < size && heap[left] < heap[smallest]) {
            smallest = left;
        }

        // Check if right child is smaller than current smallest
        if (right < size && heap[right] < heap[smallest]) {
            smallest = right;
        }

        // If the smallest isn't the current node, swap and continue heapifying
        if (smallest != i) {
            swap(i, smallest);
            heapify(smallest);  // Recursively fix the affected subtree
        }
    }

    // Build the Min-Heap from the unsorted array
    void buildMinHeap() {
        // Start from the last non-leaf node and heapify each node
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(i);
        }
    }

    // Swap two elements in the heap
    void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // Print the heap as an array
    void printHeap() {
        for (int val : heap) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    public void heapSort(int[] arrToSort){

        //first we build our min heap based off our input array
        this.heap = arrToSort;
        this.size = arrToSort.length;
        buildMinHeap();

        for (int i = size - 1; i > 0; i--){
            swap(0, i);
            size--;
            heapify(0);
        }

    }

    public static void main(String[] args) {
        int[] array = {4, 10, 3, 5, 1, 2};

        /*
        Inital heap in array form

                 4
               /   \
             10     3
            /  \   /
           5   1  2
         */

        System.out.println("Original array:");
        for (int val : array) System.out.print(val + " ");
        System.out.println();

        MinHeap minHeap = new MinHeap(array);
        minHeap.buildMinHeap();

        /*
       our heap after calling heapify:

                 1
               /   \
              4     2
             / \   /
            5  10 3

         */

        System.out.println("\nMin-Heap after heapify:");
        minHeap.printHeap();
    }

}
