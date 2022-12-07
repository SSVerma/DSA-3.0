package graph;

import java.util.ArrayList;
import java.util.List;

public class Dfs {
    public static void main(String[] args) {
        int nodeCount = 5;
        List<List<Integer>> graph = Graph.undirectedBuilder(nodeCount)
                .addEdge(0, 1)
                .addEdge(0, 2)
                .addEdge(0, 3)
                .addEdge(2, 4)
                .build();

        List<Integer> result = traverse(graph, 0, new boolean[nodeCount], new ArrayList<>());
        System.out.println(result);
    }

    public static List<Integer> traverse(
            List<List<Integer>> graph,
            int node,
            boolean[] visited,
            List<Integer> result
    ) {
        visited[node] = true;
        result.add(node);

        if (node == graph.size()) {
            return result;
        }

        for (int adjacent : graph.get(node)) {
            if (!visited[adjacent]) {
                traverse(graph, adjacent, visited, result);
            }
        }

        return result;
    }

}
