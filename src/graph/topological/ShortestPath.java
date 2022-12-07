package graph.topological;

import graph.Graph;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ShortestPath {
    public static void main(String[] args) {
        List<List<Graph.Node>> graph = Graph.directedWeightedBuilder(7)
                .addEdge(0, 1, 2)
                .addEdge(1, 3, 1)
                .addEdge(2, 3, 3)
                .addEdge(4, 0, 3)
                .addEdge(4, 2, 1)
                .addEdge(5, 4, 1)
                .addEdge(6, 4, 2)
                .addEdge(6, 5, 3)
                .build();

        int[] result = shortestPath(6, graph);
        System.out.println(Arrays.toString(result));
    }

    /*
       Using topological sort.
     */
    private static int[] shortestPath(int srcNode, List<List<Graph.Node>> graph) {
        boolean[] visited = new boolean[graph.size()];
        Stack<Integer> stack = new Stack<>();

        for (int node = 0; node < graph.size(); node++) {
            if (!visited[node]) {
                topologicalSort(graph, node, visited, stack);
            }
        }

        int[] distance = new int[graph.size()];
        Arrays.fill(distance, Integer.MAX_VALUE);

        distance[srcNode] = 0;

        while (!stack.isEmpty()) {
            int node = stack.pop();

            for (Graph.Node adjacent : graph.get(node)) {
                int distanceNow = distance[node] + adjacent.weight();
                if (distanceNow < distance[adjacent.value()]) {
                    distance[adjacent.value()] = distanceNow;
                }
            }
        }

        return distance;
    }

    private static void topologicalSort(
            List<List<Graph.Node>> graph,
            int node,
            boolean[] visited,
            Stack<Integer> stack
    ) {
        visited[node] = true;

        for (Graph.Node adjacent : graph.get(node)) {
            if (!visited[adjacent.value()]) {
                topologicalSort(graph, adjacent.value(), visited, stack);
            }
        }

        stack.push(node);
    }

}
