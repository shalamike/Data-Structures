package datastructures.graphs;

import java.util.*;

public class DijkstraAlgorythm {

    /*
    Here’s a list of steps to follow in order to solve the SPP with Dijkstra:

    Set distance to startNode to zero.
    Set all other distances to an infinite value.
    We add the startNode to the unsettled nodes set.
    While the unsettled nodes set is not empty we:
    Choose an evaluation node from the unsettled nodes set, the evaluation node should be the one with the lowest distance from the source.
    Calculate new distances to direct neighbors by keeping the lowest distance at each evaluation.
    Add neighbors that are not yet settled to the unsettled nodes set.
    These steps can be aggregated into two stages, Initialization and Evaluation. Although in the code below, there is
    a third stage that is not part of the algorythm but exists purely display the results of dijkstras algorythm
    is

    In short Djikstra's algorithm   Finds the shortest path from a single source node to all other nodes
   in a weighted graph. In this case, our graph is an adjacency matrix
     */


    public static void djikstraAdjacencyMatrix(AdjacencyMatrix graph, int startNode) {

        // --------------------------- INITIALIZATION STAGE ---------------------------

        // Retrieve the total number of nodes in the graph
        int totalNumberOfNodes = graph.numOfNodes;

        // Retrieve the adjacency matrix (2D array where each cell represents edge weight)
        int[][] adjacencyMatrix = graph.matrixArray;

        // Create an array to store the shortest known distance from the start node to every other node
        int[] shortestDistanceFromStart = new int[totalNumberOfNodes];

        // Create an array to track which nodes have been permanently visited (settled)
        boolean[] nodeVisited = new boolean[totalNumberOfNodes];

        // Create an array to track the path (which node led to this one)
        int[] previousNodeInPath = new int[totalNumberOfNodes];

        // 1. Set all distances to "infinity" initially, since no path is known yet
        Arrays.fill(shortestDistanceFromStart, Integer.MAX_VALUE);

        // Initialize all previous nodes as -1 (no parent yet)
        Arrays.fill(previousNodeInPath, -1);

        // 2. Distance to the starting node is always zero
        shortestDistanceFromStart[startNode] = 0;

        // 3. Initialize the unsettled nodes set (priority queue)
        // The queue will automatically sort nodes by their current known distance
        PriorityQueue<int[]> unvisitedNodesByDistance = new PriorityQueue<>(Comparator.comparingInt(entry -> entry[1]));

        // Add the start node with distance 0 into the queue
        unvisitedNodesByDistance.offer(new int[]{startNode, 0});

        // Print stage header
        System.out.println("=== INITIALIZATION COMPLETE ===");
        System.out.println("Start node set to " + startNode + " (distance = 0)");
        System.out.println("All other nodes initialized with distance = ∞\n");

        // --------------------------- EVALUATION STAGE ---------------------------

        // 4. While there are still nodes in the unsettled (unvisited) set
        while (!unvisitedNodesByDistance.isEmpty()) {

            // Extract (poll) the node with the smallest distance from the queue
            int[] currentClosestNode = unvisitedNodesByDistance.poll();

            int currentNodeIndex = currentClosestNode[0];
            int currentNodeDistance = currentClosestNode[1];

            // 5. Skip already visited nodes to prevent reprocessing
            if (!nodeVisited[currentNodeIndex]) {

                // Mark this node as visited (settled)
                nodeVisited[currentNodeIndex] = true;

                System.out.println("Visiting Node " + currentNodeIndex + " (Distance from start = " + currentNodeDistance + ")");

                // 6. Evaluate all direct neighbors of the current node
                for (int neighborNodeIndex = 0; neighborNodeIndex < totalNumberOfNodes; neighborNodeIndex++) {

                    // Retrieve the edge weight between the current node and its neighbor
                    int edgeWeight = adjacencyMatrix[currentNodeIndex][neighborNodeIndex];

                    // Check if an edge actually exists (weight > 0)
                    if (edgeWeight > 0) {

                        // Compute the new possible distance through this current node
                        int newPossibleDistance = currentNodeDistance + edgeWeight;

                        // Print diagnostic information for tracing updates
                        System.out.println("   ↳ Checking edge " + currentNodeIndex + " → " + neighborNodeIndex +
                                " (Weight = " + edgeWeight + ")");
                        System.out.println("     Current known distance to Node " + neighborNodeIndex + " = " +
                                (shortestDistanceFromStart[neighborNodeIndex] == Integer.MAX_VALUE ? "∞" : shortestDistanceFromStart[neighborNodeIndex]) +
                                ", New possible distance = " + newPossibleDistance);

                        // 7. Compare this new path distance to the best known one
                        if (newPossibleDistance < shortestDistanceFromStart[neighborNodeIndex]) {

                            // If this new path is shorter, update the shortest distance
                            shortestDistanceFromStart[neighborNodeIndex] = newPossibleDistance;

                            // Record the path (the node we came from)
                            previousNodeInPath[neighborNodeIndex] = currentNodeIndex;

                            // Add this neighbor to the unsettled (unvisited) nodes set for further evaluation
                            unvisitedNodesByDistance.offer(new int[]{neighborNodeIndex, newPossibleDistance});

                            System.out.println("      Updated shortest distance of Node " + neighborNodeIndex +
                                    " to " + newPossibleDistance + " via Node " + currentNodeIndex);
                        }
                    }

                    // Print the current queue contents for debugging
                    System.out.println("   Priority Queue Now: " + formatPriorityQueue(unvisitedNodesByDistance));
                    System.out.println("-------------------------------------------------------------");
                }

                // --------------------------- INTERMEDIATE RESULTS ---------------------------

                // Print the current state of shortest distances
                System.out.println("\n🏁 CURRENT SHORTEST DISTANCES FROM NODE " + startNode + ":");
                for (int nodeIndex = 0; nodeIndex < totalNumberOfNodes; nodeIndex++) {
                    if (shortestDistanceFromStart[nodeIndex] == Integer.MAX_VALUE) {
                        System.out.printf("Node %d: unreachable (∞)%n", nodeIndex);
                    } else {
                        System.out.printf("Node %d: %d%n", nodeIndex, shortestDistanceFromStart[nodeIndex]);
                    }
                }

                // Print currently known paths
                System.out.println("\nCURRENT SHORTEST PATHS FROM NODE " + startNode + ":");
                for (int targetNodeIndex = 0; targetNodeIndex < totalNumberOfNodes; targetNodeIndex++) {
                    if (shortestDistanceFromStart[targetNodeIndex] != Integer.MAX_VALUE) {
                        System.out.print("Path to " + targetNodeIndex + ": ");
                        printReconstructedPath(targetNodeIndex, previousNodeInPath);
                        System.out.println(" (Total cost: " + shortestDistanceFromStart[targetNodeIndex] + ")");
                    }
                }
            }
        }
    }

    public static void findShortestPathBetween(AdjacencyMatrix graph, int startNode, int targetNode) {

        // --------------------------- INITIALIZATION ---------------------------

        int totalNumberOfNodes = graph.numOfNodes;
        int[][] adjacencyMatrix = graph.matrixArray;

        int[] shortestDistanceFromStart = new int[totalNumberOfNodes];
        boolean[] nodeVisited = new boolean[totalNumberOfNodes];
        int[] previousNodeInPath = new int[totalNumberOfNodes];

        Arrays.fill(shortestDistanceFromStart, Integer.MAX_VALUE);
        Arrays.fill(previousNodeInPath, -1);
        shortestDistanceFromStart[startNode] = 0;

        PriorityQueue<int[]> unvisitedNodesByDistance = new PriorityQueue<>(Comparator.comparingInt(entry -> entry[1]));
        unvisitedNodesByDistance.offer(new int[]{startNode, 0});

        System.out.println("=== Finding Shortest Path from " + startNode + " to " + targetNode + " ===");

        // --------------------------- EVALUATION ---------------------------

        while (!unvisitedNodesByDistance.isEmpty()) {

            int[] currentClosestNode = unvisitedNodesByDistance.poll();
            int currentNodeIndex = currentClosestNode[0];
            int currentNodeDistance = currentClosestNode[1];

            //  Inverted condition — process only if node not yet visited
            if (!nodeVisited[currentNodeIndex]) {
                nodeVisited[currentNodeIndex] = true;
                System.out.println("Visiting Node " + currentNodeIndex + " (Distance = " + currentNodeDistance + ")");

                // Early exit optimization:
                // If we reached the target, no need to continue processing the entire graph.
                if (currentNodeIndex == targetNode) {
                    System.out.println("\n🏁 Target node " + targetNode + " reached. Stopping search.");
                    break;
                }

                // Explore all neighbors
                for (int neighborNodeIndex = 0; neighborNodeIndex < totalNumberOfNodes; neighborNodeIndex++) {

                    int edgeWeight = adjacencyMatrix[currentNodeIndex][neighborNodeIndex];

                    // Only proceed if an edge exists
                    if (edgeWeight > 0) {
                        int newPossibleDistance = currentNodeDistance + edgeWeight;

                        System.out.println("   ↳ Checking edge " + currentNodeIndex + " → " + neighborNodeIndex +
                                " (Weight = " + edgeWeight + ")");
                        System.out.println("     Current known distance to Node " + neighborNodeIndex + " = " +
                                (shortestDistanceFromStart[neighborNodeIndex] == Integer.MAX_VALUE ? "∞" : shortestDistanceFromStart[neighborNodeIndex]) +
                                ", New possible distance = " + newPossibleDistance);

                        // If the new distance is shorter, update it
                        if (newPossibleDistance < shortestDistanceFromStart[neighborNodeIndex]) {
                            shortestDistanceFromStart[neighborNodeIndex] = newPossibleDistance;
                            previousNodeInPath[neighborNodeIndex] = currentNodeIndex;
                            unvisitedNodesByDistance.offer(new int[]{neighborNodeIndex, newPossibleDistance});

                            System.out.println("     Updated distance of Node " + neighborNodeIndex +
                                    " to " + newPossibleDistance + " via Node " + currentNodeIndex);
                        }
                    }
                }

                // Print the current queue state
                System.out.println("   Priority Queue Now: " + formatPriorityQueue(unvisitedNodesByDistance));
                System.out.println("-------------------------------------------------------------");
            }
        }

        // --------------------------- RESULT OUTPUT ---------------------------

        if (shortestDistanceFromStart[targetNode] == Integer.MAX_VALUE) {
            System.out.println("⚠ Target node " + targetNode + " is unreachable from start node " + startNode + ".");
        } else {
            System.out.println("\n Shortest path from Node " + startNode + " to Node " + targetNode + ":");
            printReconstructedPath(targetNode, previousNodeInPath);
            System.out.println("\nTotal cost: " + shortestDistanceFromStart[targetNode]);
        }
    }


    /*
     * Formats the current contents of the priority queue for visual debugging.
     */

    private static String formatPriorityQueue(PriorityQueue<int[]> unvisitedNodesByDistance) {
        StringBuilder builder = new StringBuilder("[");
        for (int[] entry : unvisitedNodesByDistance) {
            builder.append("(Node ").append(entry[0])
                    .append(", Distance ").append(entry[1]).append("), ");
        }
        if (builder.length() > 1) {
            builder.setLength(builder.length() - 2); // Remove trailing comma and space
        }
        builder.append("]");
        return builder.toString();
    }

    /*
     * Reconstructs and prints the path from the start node to a target node
     * using the previousNodeInShortestPath array.
     */

    private static void printReconstructedPath(int targetNodeIndex, int[] previousNodeInShortestPath) {
        List<Integer> reconstructedPath = new ArrayList<>();
        for (int node = targetNodeIndex; node != -1; node = previousNodeInShortestPath[node]) {
            reconstructedPath.add(node);
        }
        Collections.reverse(reconstructedPath);

        for (int i = 0; i < reconstructedPath.size(); i++) {
            System.out.print(reconstructedPath.get(i));
            if (i < reconstructedPath.size() - 1) {
                System.out.print(" → ");
            }
        }
    }


    public static void main(String[] args){
        /*

        lets take this weighted graph
       (4)
  0 --------> 1
  \           |
 (2)\         |(10)
     \        v
      > 2 ---> 3 ---> 4
          (3)   (1)

         */

        AdjacencyMatrix weightedGraph = new AdjacencyMatrix(5, true);
        weightedGraph.arrayAddEdge(0, 1, 4);
        weightedGraph.arrayAddEdge(0, 2, 2);
        weightedGraph.arrayAddEdge(1, 2, 5);
        weightedGraph.arrayAddEdge(1, 3, 10);
        weightedGraph.arrayAddEdge(2, 3, 3);
        weightedGraph.arrayAddEdge(3, 4, 1);
        weightedGraph.arrayAddEdge(4, 0, 7);

        weightedGraph.printMatrixArray();

//        System.out.println("\n--- Running Dijkstra’s Algorithm to find all shortest paths---\n");
//        DijkstraAlgorythm.djikstraAdjacencyMatrix(weightedGraph, 0);


        System.out.println("\n--- Running Dijkstra’s Algorithm to find the shortest path between two points---\n");
        DijkstraAlgorythm.findShortestPathBetween(weightedGraph, 0, 3);

    }
}
