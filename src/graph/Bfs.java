package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Bfs {
    public static void main(String[] args) {
        List<List<Integer>> graph = Graph.undirectedBuilder(5)
                .addEdge(0, 1)
                .addEdge(0, 2)
                .addEdge(0, 3)
                .addEdge(2, 4)
                .build();

        List<Integer> result = traverse(graph);
        System.out.println(result);
    }

    public static List<Integer> traverse(List<List<Integer>> graph) {
        int nodeCount = graph.size();
        boolean[] visited = new boolean[nodeCount];
        List<Integer> result = new ArrayList<>();

        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        visited[0] = true;

        while (!q.isEmpty()) {
            int node = q.remove();
            result.add(node);

            for (int adjacent : graph.get(node)) {
                if (!visited[adjacent]) {
                    q.add(adjacent);
                    visited[adjacent] = true;
                }
            }
        }

        return result;
    }

}
