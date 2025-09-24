package datastructures.graphs;

import java.lang.reflect.Array;
import java.util.*;

public class AdjacencyList {
    HashMap<Integer, LinkedList<Integer>> adjacencyList ;

    boolean isDirected;



    public AdjacencyList(){
        this.adjacencyList = new HashMap<>();
        this.isDirected = false;
    }

    public AdjacencyList(boolean isDirected){
        this.adjacencyList = new HashMap<>();
        this.isDirected = isDirected;
    }

    public void addEdge(int from, int to){
        adjacencyList.get(from).add(to);
        if (isDirected)
            adjacencyList.get(to).add(from);
    }

    public void addNode(){
        if (!adjacencyList.containsKey(adjacencyList.size())){
            int currentKey = adjacencyList.size();
            adjacencyList.put(currentKey, new LinkedList<>());
        }
    }

    public void displayAdjacencies(){
        int numberOfNodes = adjacencyList.size();
        String header = "Node : Connections";
        System.out.println(header);
        for(int rowPointer = 0; rowPointer < numberOfNodes; rowPointer++){
            StringBuilder row = new StringBuilder();
            row.append(rowPointer).append("    : ");
            LinkedList currentRow = adjacencyList.get(rowPointer);
            for (int currentEdge = 0; currentEdge < currentRow.size(); currentEdge++){
                row.append("[").append(currentRow.get(currentEdge)).append("]");
            }
            System.out.println(row);
        }
    }

    public ArrayList<Integer> dfsIterative(int startNode){
        // creating an arrayList that will store the traversal order of our nodes
        ArrayList<Integer> visitedNodeOrder = new ArrayList<>();
        //creating a stack to store all the neighbours of our current node that need to be visited.
        Stack<Integer> toBeVisitedStack = new Stack<>();
        // adding our initial node to our to be visited stack
        toBeVisitedStack.push(startNode);
        // begining the dfs traversal in this while loop.
        // this while loop will keep executing as long as there is a node that has yet to be visited.
        // Once all nodes have been visited, this while loop will terminate.
        while (!toBeVisitedStack.isEmpty()){
            //removing our currentNode from the stack
            int currentNode = toBeVisitedStack.pop();
            //adding our current node to the visited node order list
            visitedNodeOrder.add(currentNode);
            // getting the list of all our current nodes neighbouring nodes
            LinkedList<Integer> currentNodesNeighbours = adjacencyList.get(currentNode);
            // getting the total number of neighbouring nodes
            int numOfNeighbours = currentNodesNeighbours.size();
            // looping backwards through all our current nodes neighbouring nodes so that we add the highest value first
            // the reason for this is so that the lowest values will allways be removed first
            for(int currentNeighbourIndex = numOfNeighbours - 1; currentNeighbourIndex >= 0; currentNeighbourIndex--){
                //getting one of the neighbours from our list of neighbours
                int currentNeighbour = currentNodesNeighbours.get(currentNeighbourIndex);
                //checking to see if that neighbour has allready been added to the to be visited stack
                if (!toBeVisitedStack.contains(currentNeighbour))
                    // if it hasn't been added, we add it in the stack so that we can visit it later.
                    toBeVisitedStack.push(currentNeighbour);
                //otherwise we ignore it as we will be visiting it later anyways and no need to visit it twice
            }
        }
        //after all the nodes has been traversed, we return the order of traversal.
        return visitedNodeOrder;
    }

    public ArrayList<Integer> bfsIterative(int startNode){

        //creating an arrayList to store the order we visit our nodes
        ArrayList<Integer> visitedNodeOrder = new ArrayList<>();

        //creating a Queue to store all the nodes that are yet to be visited.
        Queue<Integer> toBeVisitedQueue = new LinkedList<>();

        //adding the startNode to our to be visited Queue
        toBeVisitedQueue.add(startNode);

        //beginng the bfs traversal in this while loop.
        // this while loop will keep executing as long as there is a node that has yet to be visited.
        // Once all nodes have been visited, this while loop will terminate.
        while (!toBeVisitedQueue.isEmpty()){
            //removing our currentNode from our to be visited Queue as its now being visited
            //and setting it to our current node.
            int currentNode = toBeVisitedQueue.remove();
            //adding our current node to our visitedNodeOrder ArrayList
            visitedNodeOrder.add(currentNode);
            //looping through all our current nodes neighbours
            for (int neighbouringNode : adjacencyList.get(currentNode)){
                //checking to see if our current neighbouring node has been added to the to be visited queue
                if (!toBeVisitedQueue.contains(neighbouringNode)){
                    //if it hasnt been added, we add it in
                    toBeVisitedQueue.add(neighbouringNode);
                }
                //otherwise no point adding it in twice as we will get duplicate data by visiting it twice
            }
        }
        //once all nodes has been visited, the while loop ends and then we return the list of nodes
        // in the order we visited them
        return visitedNodeOrder;
    }

    public String printArrayList(ArrayList<Integer> nodeOrder){
        StringBuilder allNodesInOrder = new StringBuilder();

        for(int node : nodeOrder){
            allNodesInOrder.append(node).append(" ");
        }

        return allNodesInOrder.toString();
    }

    public static void main(String args[]){

//        AdjacencyList graph = new AdjacencyList();
//        graph.addNode();
//        graph.addNode();
//        graph.addNode();
//        graph.addNode();
//
//
//        graph.addEdge(0,1);
//        graph.addEdge(0,2);
//        graph.addEdge(1,2);
//        graph.addEdge(2,0);
//        graph.addEdge(2,3);
//        graph.addEdge(3,3);
//
//        graph.displayAdjacencies();
//
//
//        StringBuilder nodeOrder = new StringBuilder();
//        for (int node : graph.dfsIterative(2)){
//            nodeOrder.append(node).append(" ");
//        }
//
//        System.out.println(nodeOrder);

        AdjacencyList graph2 = new AdjacencyList();

        graph2.addNode();
        graph2.addNode();
        graph2.addNode();
        graph2.addNode();
        graph2.addNode();
        graph2.addNode();
        graph2.addNode();
        graph2.addNode();
        graph2.addNode();
        graph2.addNode();

        graph2.addEdge(0,1);
        graph2.addEdge(0,2);
        graph2.addEdge(1,2);
        graph2.addEdge(1,3);
        graph2.addEdge(1,4);
        graph2.addEdge(3,5);
        graph2.addEdge(5,6);
        graph2.addEdge(5,7);
        graph2.addEdge(5,8);
        graph2.addEdge(7,8);
        graph2.addEdge(8,9);

//        StringBuilder nodeOrderDfs = new StringBuilder();
//        StringBuilder nodeOrderBfs = new StringBuilder();

//        for (int node : graph2.dfsIterative(0)){
//            nodeOrderDfs.append(node).append(" ");
//        };



        System.out.println("dfs order traversal: " + graph2.printArrayList(graph2.dfsIterative(0)));
        System.out.println("bfs order traversal: " + graph2.printArrayList(graph2.bfsIterative(0)));

//        for (int node : graph2.bfsIterative(0)){
//            nodeOrderBfs.append(node).append(" ");
//        }

//        System.out.println("bfs order traversal: " + nodeOrderBfs);

    }



}
