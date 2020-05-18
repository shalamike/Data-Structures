public class CircularlLinkedList {
     /*
     A circular linked list is like a linked list except for the fact that its end node points towarrds its head node
     as depicted in the diagram below

                   +----+------+     +----+------+     +----+------+
             ----->| 1  |  o-------->| 2  |  o-------->|  3 | null |-----
            |      +----+------+     +----+------+     +----+------+     |
            |                                                            |
             ------------------------------------------------------------

     */
     //creating the Node
     static class Node{
         // creating the Node of the linked list
         int data;
         Node next;
         // the constructor creates a new node
         // by default next is initiallised as null in the constructor
         Node (int d){
             data = d;
             next = null;
         }
     }
     // declaring the current head and tail pointers as null
     public Node head = null;
     public Node tail = null;
     
}
