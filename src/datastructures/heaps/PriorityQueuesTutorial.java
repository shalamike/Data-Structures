package datastructures.heaps;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueuesTutorial {

    /*
        A heap is a binary tree. However, unlikes a binary search tree, A heap orders its elements int two very
        distinct ways.

        Max-Heap:

        A Max-Heap orders its elements with the highest value as the root and every child node is smaller than its root
        as shown in the diagram below of a valid heap:

                  50
                /    \
              30      40
             /  \    /  \
           10   20  35   25

        A Max-Heap becomes invalid if any of the child elements are greater than its root as shown below.

                  50
                /    \
              30      60
             /  \    /  \
           10   40  35   25


         in this example, this max-heap is invalid because 60 is a child of 50 and is greater than 50. Also 40 is a
         child of 30 yet is greater than 30

       Min-Heap:

        A Min Heap Orders its elements with the lowest value at the root and every child node is greater than its root
        as shown in the diagram below:

                      10
                    /    \
                  20      15
                 /  \    /  \
               30   40  50   60

        The diagram below is still a valid min heap, while 25 may be less than 30, it is still greater than its parent
        and grand parent. therefore keeping it valid.
                      10
                    /    \
                  30      15
                 /  \    /  \
               60   40  25   60



        Therefore, an invalid min-heap is a heap where the children of a node is less than its parent node as shown below:

                      10
                    /    \
                  20      15
                 /  \    /  \
               30   5  50   60

        this min heap above is invalid because 5 is a child of 20 yet is less than 20.

        Note: A Heap must be a complete Binary tree, whether it is balanced or not. therefore, this is a valid heap despite being
        an unbalanced tree because it is still a binary tree.

                  5
                /   \
              10     15
             /  \
           20   25
          /
        30

        more examples of invalid heaps:

                      10
                    /    \
                  20      15
                 /  \    /  \
               30   40  50   60
                     \ /
                      60

     this example is invalid because there is a cycle from 10 --> 15 --> 50 --> 60 --> 40 --> 20 --> 10
     making this a cyclic graph rather than a heap.

                      10
                    /    \
                  20      15
                 /          \
               30           60

     This example is invalid because the binary tree is not complete because we left a gap on the child on 20 while
     adding a new node on the child of 15. Therefore, to make this a valid heap, we could move node 60 to be the child
     of 20
                      10
                    /    \
                  20      15
                 /  \
               30    60



     */

    // a custom comparator to reverse the order of a priorityQueue changing it from a max heap to a min heap
    public static Comparator<Integer> maxHeapComparator() {
        return new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b - a; // Reverse order
            }
        };
    }


    public static void main(String[] args) {

        /*
             Priority Queues:

     A Priority Queue is an abstract data type that functions similarly to a queue. However, unlikes a queue, it orders
     elements based on its priority i.e. ordering students based on their grades or ordering cars based on their
     release date.
     As Every element has a priority, the element with THE HIGHEST priority will always be served first. Therefore, a
     priority Queue is an implementation of the min heap binary tree.
         */

        PriorityQueue<Integer> numberPriorityQueue = new PriorityQueue<>();

        //adding elements to our priority queue in no particular order.
        numberPriorityQueue.add(50);
        /*
          current priority Queue:

                    50
         */
        numberPriorityQueue.add(20);
        /*
        now java compares 20 to 50. The smallest number being 20 will become the new parent and the larger number will
        become its new root.
          current priority Queue:

                    20
                  /
                 50

            note that because 20 is less than 50, it becomes the root
         */

        numberPriorityQueue.add(40);

                /*
          current priority Queue:

                    20
                   /  \
                 50   40

         */
        numberPriorityQueue.add(10);

        /*
          current priority Queue:

                    10
                   /  \
                  20  40
                 /
               50
         */


        numberPriorityQueue.add(30);

                /*
          current priority Queue:

                    10
                   /  \
                  20  40
                 /  \
               50    30
         */

        System.out.println("order of elements stored in this priority queue: " + numberPriorityQueue);

        System.out.println("peak at last element of priorty queue: " + numberPriorityQueue.peek());

        //removing all elemnts one by one
        while (!numberPriorityQueue.isEmpty()) {
            int removedElement = numberPriorityQueue.poll();
            System.out.println("removed element " + removedElement + " | remaining elements: " + numberPriorityQueue);
        }

        /*
        Notice that when we add elements to the priority queue, the priority queue will allways set the element as a
        child if its smaller than its parent. otherwise, it gets shifter up the tree and becomes a parent itself.
        this operation can be further viewed in the class MinHeap

        Now lets demonstrate this with a scenario where we have a class of students
         */

        //first we determine what the priority queue will order its elements by
        PriorityQueue<Student> studentPriorityQueue = new PriorityQueue<>(
//                (Student name1, Student name2) -> Integer.compare(name1.grade, name2.grade)
                Comparator.comparingInt((Student name) -> name.grade)
        );

        Student jake = new Student("jake", 50);
        Student alice = new Student("alice", 70);
        Student mark = new Student("mark", 40);
        Student john = new Student("john", 80);
        Student ahmed = new Student("ahmed", 90);
        Student abdul = new Student("abdul", 100);
        Student brexit = new Student("brexit", 10);

        studentPriorityQueue.add(jake);

        /*
        priority queue after adding jake

        (50 jake)

         */
        studentPriorityQueue.add(alice);
        /*
        priority queue after adding alice

                (50 jake)
                /
           (70 alice)

         */
        studentPriorityQueue.add(mark);
                /*
        priority queue after adding mark

                (50 jake)
                /       \
         (70 alice)   (40 mark)

         as mark has a lower grade than jake, mark becomes jake's parent and jake becomes mark's child

                 (40 mark)
                /       \
         (70 alice)   (50 jake)

         */

        studentPriorityQueue.add(john);
        /*
        priority queue after adding John


                  (40 mark)
                 /        \
          (70 alice)     (50 jake)
           /
        (80 john)


         */
        studentPriorityQueue.add(ahmed);

        /*
        priority queue after adding ahmed
                  (40 mark)
                 /        \
          (70 alice)     (50 jake)
           /      \
        (80 john) (90 ahmed)

         */
        studentPriorityQueue.add(abdul);
        /*
        priority queue after adding abdul
                  (40 mark)
                 /        \
          (70 alice)     (50 jake)
           /      \       /
        (80 john) (90 ahmed) (100 abdul)

         */

        studentPriorityQueue.add(brexit);

        /*

        priority queue after adding brexit
                           (10 brexit)
                       /              \
                  (70 alice)          (40 mark)
               /           \            /      \
            (80 john)   (90 ahmed)  (100 abdul)   (50 jake)


         */

        while (!studentPriorityQueue.isEmpty()) {
            System.out.println("student with current grade : " + studentPriorityQueue.poll());
        }

        // here we create a priority queue as a maxHeap using a built in comparator
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        maxHeap.add(10);
        maxHeap.add(5);
        maxHeap.add(20);

        System.out.println("Polling from max-heap:");
        while (!maxHeap.isEmpty()) {
            System.out.println(maxHeap.poll());

        }

        // Create a max-heap using a lambda comparator
        PriorityQueue<Integer> maxHeapWithLambda = new PriorityQueue<>((a, b) -> b - a);

        maxHeap.add(10);
        maxHeap.add(5);
        maxHeap.add(20);

        System.out.println("Polling from max-heap (lambda):");
        while (!maxHeap.isEmpty()) {
            System.out.println(maxHeap.poll());
        }
    }
}
