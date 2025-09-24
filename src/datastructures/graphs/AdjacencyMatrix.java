package datastructures.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class AdjacencyMatrix {

    int[][] matrixArray;

    int numOfNodes;

    ArrayList<ArrayList<Integer>>matrixArrayList;

    boolean isDirected;

    public AdjacencyMatrix(int numOfNodes, boolean isDirected) {
        this.numOfNodes = numOfNodes;
        this.isDirected = isDirected;
        matrixArray = new int[numOfNodes][numOfNodes];
    }

    public AdjacencyMatrix(boolean isDirected){
        this.isDirected = isDirected;
        matrixArrayList = new ArrayList<>();
        numOfNodes = 0;
    }

    // adding edge to our matrix

    public void arrayAddEdge (int fromNode, int toNode){
        matrixArray[fromNode][toNode] = 1;
        if (!isDirected)
            matrixArray[toNode][fromNode] = 1;
    }

    public void arrayAddEdge(int fromNode, int toNode, int weight){
        matrixArray[fromNode][toNode] = weight;
        if (!isDirected)
            matrixArray[toNode][fromNode] = weight;
    }

    public boolean arrayHasEdge(int from, int to){
        return matrixArray[from][to] >= 1;
    }

    public void listCreateNode(){
        matrixArrayList.add(new ArrayList<>());
        this.numOfNodes++;
        //adding default values to edges to ensure the matrix is consistent in structure with array

        for (ArrayList<Integer> integers : matrixArrayList) {
            if (integers.size() < numOfNodes)
                for( int currentRowPointer= integers.size(); integers.size()< numOfNodes; currentRowPointer++){
                    integers.add(0);
                }
        }
    }

    public void listCreateEdge(int to, int from, int weight){
        matrixArrayList.get(to).set(from, weight);
        if (!isDirected){
            matrixArrayList.get(from).set(to, weight);
        }
    }


    public void printMatrixArray(){
        StringBuilder header = new StringBuilder();
        for (int currentNode =0; currentNode < numOfNodes; currentNode++){
            header.append("  ").append(currentNode);
        }
        System.out.println(header);
        for (int rowPointer =0; rowPointer < numOfNodes; rowPointer++){
            StringBuilder row = new StringBuilder();
            row.append(rowPointer);
            row.append("[");
            for (int edgePointer = 0; edgePointer < numOfNodes; edgePointer++){
                if (edgePointer < numOfNodes - 1){
                    row.append(matrixArray[rowPointer][edgePointer]);
                    row.append(", ");
                }
                else {
                    row.append(matrixArray[rowPointer][edgePointer]);
                }
            }
            row.append("]");
            System.out.println(row);
        }
    }

    public ArrayList<Integer> bfsIterative (int startNode){

        //creating an array of booleans where the index of the array represents the node
        boolean[] visited = new boolean[numOfNodes];

        //creating an array list to store the order in which nodes are visited
        ArrayList<Integer> visitedNodeOrder = new ArrayList<>();

        //creating a queue to store all the nodes that are yet to be visited.
        Queue<Integer> toBeVisitedQueue = new LinkedList<>();

        //adding our initial node to our queue
        toBeVisitedQueue.add(startNode);

        //starting the bfs traversal. this will end when there are no more nodes in our queue to be visited
        while (!toBeVisitedQueue.isEmpty()){
            //de-queueing the current first element of the queue and setting it to be our current node.
            //this will initially be our starting node
            int currentNode = toBeVisitedQueue.remove();

            //checking to see if our current node has not been visited. this will most likely be false by default
            if (!visited[currentNode]){
                System.out.println("currently on Node :" + currentNode);
                // adding our current node to our visited node order arraylist.
                visitedNodeOrder.add(currentNode);
                // now looping through all links to our current node to add the neigbours to the to be visited queue
                for (int neighbouringNode = 0; neighbouringNode < numOfNodes; neighbouringNode++){
                    //checking to see if the adjacency matrix is a 2d array
                    if (matrixArray != null){
                        //checking to see if our neighbouring node has a path to our current node
                        if (matrixArray[currentNode][neighbouringNode] >=1 ){
                            //checking to see if our neghbouring node has been visited
                            if(!visited[neighbouringNode]){
                                // as we are now visiting our current node, this value is set to true
                                visited[currentNode] = true;
                                //if our neighbouringNode hasn't been visited, it is added to our Queue of unvisited nodes
                                toBeVisitedQueue.add(neighbouringNode);

                            }
                        }
                    }
                    //otherwise its a 2d arrayList
                    else {
                        //checking to see if this neighbouring node has a path to our current node
                        if(matrixArrayList.get(currentNode).get(neighbouringNode) >= 1){
                            //checking to see if our neighbouring node has allready been visited
                            if (!visited[neighbouringNode]){
                                //adding the neighbouring node to the to be visited queue if it hasnt been visited.
                                toBeVisitedQueue.add(neighbouringNode);
                            }
                        }
                    }
                }
            }
        }
        return visitedNodeOrder;
    }
    public ArrayList<Integer> dfsIterative(int startNode){

        /*tracking the visited nodes to avoid revisiting. We use an array of booleans to keep track of our
        visited nodes where the index of our array is our node and the boolean value represents whether its visited
        or not. this allows for very fast access to our visited data as we will be directly using the index.
         
         */
        boolean[] visited = new boolean[numOfNodes];
        ArrayList<Integer> visitedNodeOrder = new ArrayList<>();

        //stack to manage the Depth first seach as its Last In First Out (LIFO)
        Stack<Integer> stack = new Stack<>();

        //start with any given node entered the method parameter
        stack.push(startNode);

        /*
        now there are nodes to process in the stack, the traversall algorythm can begin. as we will keep traversing
        the graph untill there are no more nodes to access in our stack. 
         */       
        
        while (!stack.isEmpty()){
            //first we pop our stack to get our current value thats being visited. 
            
            int currentNode = stack.pop();
            
            // if this item is not in our visited array, we add it to our visited array
            
            if (!visited[currentNode]){
                visited[currentNode] = true;
                System.out.println("currently on Node :" + currentNode);
                visitedNodeOrder.add(currentNode);
            }
            
            /*
            now we look through all possible neighbours in reverse order to try our best to visit the smallest 
            nodes first. This ordering process is entirely optional
             */
            
            for (int neighbouringNode = numOfNodes - 1; neighbouringNode >= 0; neighbouringNode--){
                //checking to see if our adjacency matrix is an array or arrayList
                if (matrixArray != null){
                    //checking to see if our neighbouring node has a path to our current node
                    if (matrixArray[currentNode][neighbouringNode] >=1 ){
                        //checking to see if our neghbouring node has been visitd
                        if(!visited[neighbouringNode]){
                            //if our neighbouringNode hasn't been visited, it is added to our stack
                            stack.push(neighbouringNode);
                        }
                    }
                }
                else {
                    //checking to see if our neighbouring node has a path to our current node
                    if (matrixArrayList.get(currentNode).get(neighbouringNode) >=1 ){
                        //checking to see if our neghbouring node has been visitd
                        if(!visited[neighbouringNode]){
                            //if our neighbouringNode hasn't been visited, it is added to our stack
                            stack.push(neighbouringNode);
                        }
                    }
                }
            }
        }
        return visitedNodeOrder;
    }
    
    public ArrayList<Integer> dfsRecursive(int startNode){

        ArrayList<Integer> visitedOrder = new ArrayList<>();
        /*
        create an array of booleans to keep track of visited nodes where the index of the array represents the node.
        This is done to rapidly access visited nodes rather then perform a search.
         */
        boolean[] visited = new boolean[numOfNodes];

        System.out.println("Recursive DFS starting from Node " + startNode + ":");
        // we begin the recursive process here
        dfsRecursiveHelper(startNode, visited, visitedOrder);

        return visitedOrder;
    }
    
    public void dfsRecursiveHelper(int currentNode, boolean[] visitedNodes, ArrayList<Integer> visitedOrder){
        //mark our current node as been visited
        visitedNodes[currentNode] = true;

        //carry out some process to our node, in this case, we are adding it to our visited list
        System.out.println("currently on Node: " + currentNode);
        visitedOrder.add(currentNode);

        /*
        visit all unvisited neighbours in the same order as our iterative approach (lowest to highest).
        unlikes our iterative approach, we start our neighboring node at 0 because we will immediately travel down it.
        Therefore, the DFS algorithm is naturally a recursive algorithm
         */
        for (int neighbouringNode = 0; neighbouringNode <= numOfNodes -1; neighbouringNode++){
            //checking if our current node has a path to our neighbouring node
            if (matrixArray != null){
                if (matrixArray[currentNode][neighbouringNode] >= 1){
                    //checking to see if our neighbouring node has been visited
                    if (!visitedNodes[neighbouringNode]){
                        //if its not been visited, we add traverse down that node recursively
                        dfsRecursiveHelper(neighbouringNode, visitedNodes, visitedOrder);
                    }
                }
            } else {
                if (matrixArrayList.get(currentNode).get(neighbouringNode)>= 1){
                    //checking to see if our neighbouring node has been visited
                    if (!visitedNodes[neighbouringNode]){
                        //if its not been visited, we add traverse down that node recursively
                        dfsRecursiveHelper(neighbouringNode, visitedNodes, visitedOrder);
                    }
                }
            }
        }
    }

    public void printMatrixList(){
        StringBuilder header = new StringBuilder();
        for (int currentNode =0; currentNode < numOfNodes; currentNode++){
            header.append("  ").append(currentNode);
        }
        System.out.println(header);
        for (int rowPointer =0; rowPointer < numOfNodes; rowPointer++){
            StringBuilder row = new StringBuilder();
            row.append(rowPointer);
            row.append("[");
            for (int edgePointer = 0; edgePointer < numOfNodes; edgePointer++){
                if (edgePointer < numOfNodes - 1){
                    row.append(matrixArrayList.get(rowPointer).get(edgePointer));
                    row.append(", ");
                }
                else {
                    row.append(matrixArrayList.get(rowPointer).get(edgePointer));
                }
            }
            row.append("]");
            System.out.println(row);
        }
    }

    public static void main (String args[]){

//        AdjacencyMatrix graph = new AdjacencyMatrix(4, true);
//
//        graph.arrayAddEdge(0,1);
//        graph.arrayAddEdge(0,2);
//        graph.arrayAddEdge(1,2);
//        graph.arrayAddEdge(2,0);
//        graph.arrayAddEdge(2,3);
//        graph.arrayAddEdge(3,3);
//
//
//        graph.printMatrixArray();
//        System.out.println();
//        graph = new AdjacencyMatrix(4, true);
//        graph.arrayAddEdge(0,1,1);
//        graph.arrayAddEdge(0,2,1);
//        graph.arrayAddEdge(1,2,2);
//        graph.arrayAddEdge(2,0,3);
//        graph.arrayAddEdge(2,3,2);
//        graph.arrayAddEdge(3,3,1);
//
//        graph.printMatrixArray();
//
        AdjacencyMatrix graph2 = new AdjacencyMatrix(true);

        graph2.listCreateNode();
        graph2.listCreateNode();
        graph2.listCreateNode();
        graph2.listCreateNode();

        graph2.listCreateEdge(0,1, 1);
        graph2.listCreateEdge(0,2, 1);
        graph2.listCreateEdge(1,2, 2);
        graph2.listCreateEdge(2,0, 3);
        graph2.listCreateEdge(2,3, 2);
        graph2.listCreateEdge(3,3, 1);


        System.out.println();
        graph2.printMatrixList();
        graph2.listCreateNode();
        graph2.listCreateEdge(4,1,2);
        graph2.listCreateEdge(2,4,5);
        System.out.println();
        graph2.printMatrixList();
        System.out.println();
        System.out.println("number of nodes " + graph2.numOfNodes);

        StringBuilder graph2Traversal = new StringBuilder();

        for (Integer node : graph2.dfsIterative(0)){
            graph2Traversal.append(node).append(" ");
        }

        System.out.println("graph 2 traversal order : " + graph2Traversal);
        System.out.println();
        AdjacencyMatrix graph3 = new AdjacencyMatrix(10,false);
        graph3.arrayAddEdge(0,1);
        graph3.arrayAddEdge(0,2);
        graph3.arrayAddEdge(1,4);
        graph3.arrayAddEdge(1,3);
        graph3.arrayAddEdge(3,5);
        graph3.arrayAddEdge(5,6);
        graph3.arrayAddEdge(5,7);
        graph3.arrayAddEdge(5,8);
        graph3.arrayAddEdge(8,9);

        graph3.printMatrixArray();

        System.out.println();

        StringBuilder visitedNodes = new StringBuilder();
        for (Integer node : graph3.dfsIterative(0)) {
            visitedNodes.append(node).append(" ");
        }
        System.out.println(visitedNodes);

//        System.out.println(graph3.numOfNodes);

        System.out.println();

        System.out.println("DFS recursively: ");

        StringBuilder dfsTraversal = new StringBuilder();
        for (Integer node : graph3.dfsIterative(0)){
            dfsTraversal.append(node).append(" ");
        }

        System.out.println(dfsTraversal);

        StringBuilder bfsTraversal = new StringBuilder();

        for (Integer node : graph3.bfsIterative(0)){
            bfsTraversal.append(node).append(" ");
        }
        System.out.println(bfsTraversal);
    }
}
