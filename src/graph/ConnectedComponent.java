package graph;

import java.util.ArrayList;
import java.util.List;

public class ConnectedComponent {
    public static void main(String[] args) {
        List<List<Integer>> graph = Graph.undirectedBuilder(8 + 1)
                .addEdge(1, 2)
                .addEdge(2, 3)
                .addEdge(4, 5)
                .addEdge(5, 6)
                .addEdge(7, 8)
                .build();

        int result = count(graph);
        System.out.println(result);
    }

    private static int count(List<List<Integer>> graph) {
        int nodeCount = graph.size();
        boolean[] visited = new boolean[nodeCount];

        int result = 0;

        for (int node = 1; node < nodeCount; node++) {
            if (!visited[node]) {
                Dfs.traverse(graph, node, visited, new ArrayList<>());
                result++;
            }
        }

        return result;
    }

}
