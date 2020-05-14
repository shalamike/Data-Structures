public class LinkedList {
    static Node head; //the head of the linked list
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

        // creating a method DealWithUnderflow wich simply checks if the list is empty will create a new node at index 1
        Node DealWithUnderflow(){
            return new LinkedList.Node(1);
        }
        
        // creating a method DealWithOverflow which simply sets the head to null and data to 0. therefore emptying the list down to the head
        void DealWithOverflow() {
            data = 0;
            next = null;
        }

    }
}
