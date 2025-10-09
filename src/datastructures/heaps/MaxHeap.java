package datastructures.heaps;

public class MaxHeap {

    // Heap array
    int[] heap;
    int size;

    // Constructor
    public MaxHeap(int[] array) {
        this.heap = array;
        this.size = array.length;
    }

    // Heapify at index i in a Max-Heap
    void heapify(int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // Check if left child is larger than root
        if (left < size && heap[left] > heap[largest]) {
            largest = left;
        }

        // Check if right child is larger than current largest
        if (right < size && heap[right] > heap[largest]) {
            largest = right;
        }

        // If largest isn't the current node, swap and continue heapifying
        if (largest != i) {
            swap(i, largest);
            heapify(largest); // Recursively fix the affected subtree
        }
    }

    // Build the Max-Heap from the unsorted array
    void buildMaxHeap() {
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

    // Perform heap sort using Max-Heap (produces ascending order)
    public void heapSort(int[] array) {
        this.heap = array;
        this.size = array.length;

        // Step 1: Build a Max-Heap
        buildMaxHeap();

        // Step 2: Repeatedly extract the largest element (root)
        for (int i = size - 1; i > 0; i--) {
            // Move current root to the end (largest → sorted position)
            swap(0, i);

            // Shrink the heap (ignore sorted part)
            size--;

            // Restore the max-heap property
            heapify(0);
        }
    }

    // Print the heap as an array
    void printHeap() {
        for (int val : heap) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    // Demo
    public static void main(String[] args) {
        int[] array = {4, 10, 3, 5, 1, 2};

        System.out.println("Original array:");
        for (int val : array) System.out.print(val + " ");
        System.out.println();

        MaxHeap maxHeap = new MaxHeap(array);

        maxHeap.heapSort(array);

        System.out.println("\nSorted array (ascending):");
        maxHeap.printHeap();
    }
}
