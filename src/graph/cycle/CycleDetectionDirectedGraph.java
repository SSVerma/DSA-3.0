package graph.cycle;

import graph.Graph;

import java.util.List;

public class CycleDetectionDirectedGraph {
    public static void main(String[] args) {
        List<List<Integer>> graph = Graph.directedBuilder(10 + 1)
                .addEdge(1, 2)
                .addEdge(2, 3)
                .addEdge(3, 4)
                .addEdge(3, 7)
                .addEdge(4, 5)
                .addEdge(5, 6)
                .addEdge(7, 5)
                .addEdge(8, 9)
                .addEdge(9, 10)
                .addEdge(10, 8)
                .build();

        boolean result = hasCycle(graph);
        System.out.println(result);
    }

    private static boolean hasCycle(List<List<Integer>> graph) {
        boolean[] visited = new boolean[graph.size()];
        boolean[] path = new boolean[graph.size()];

        for (int node = 0; node < graph.size(); node++) {
            if (!visited[node]) {
                if (hasCycle(node, graph, visited, path)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean hasCycle(int node, List<List<Integer>> graph, boolean[] visited, boolean[] path) {
        visited[node] = true;
        path[node] = true;

        for (int adjacent : graph.get(node)) {
            if (visited[adjacent] && path[adjacent]) {
                return true;
            }

            if (visited[adjacent]) {
                continue;
            }

            if (hasCycle(adjacent, graph, visited, path)) {
                return true;
            }

            path[node] = false;
        }

        return false;
    }

}
