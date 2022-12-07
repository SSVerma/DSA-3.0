package graph.cycle;

import graph.Graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CycleDetectionUndirectedGraph {
    public static void main(String[] args) {
        List<List<Integer>> graph = Graph.undirectedBuilder(7 + 1)
                .addEdge(1, 2)
                .addEdge(1, 3)
                .addEdge(2, 5)
                .addEdge(3, 4)
                .addEdge(3, 6)
                .addEdge(5, 7)
//                .addEdge(6, 7)
                .build();

        boolean result = hasCycle(1, graph, new boolean[graph.size()], -1);
        System.out.println(result);
    }

    // visited and not parent -> cycle (Undirected graph)
    private static boolean hasCycle(
            int srcNode,
            List<List<Integer>> graph
    ) {
        boolean[] visited = new boolean[graph.size()];

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(srcNode, -1));
        visited[srcNode] = true;


        while (!q.isEmpty()) {
            Node node = q.remove();

            for (int adjacent : graph.get(node.value)) {
                if (visited[adjacent] && adjacent != node.parent) {
                    return true;
                }

                if (!visited[adjacent]) {
                    q.add(new Node(adjacent, node.value));
                    visited[adjacent] = true;
                }
            }
        }

        return false;
    }

    private static boolean hasCycle(
            int node,
            List<List<Integer>> graph,
            boolean[] visited,
            int parent
    ) {

        for (int adjacent : graph.get(node)) {
            if (visited[adjacent] && adjacent != parent) {
                return true;
            }

            if (!visited[adjacent]) {
                visited[adjacent] = true;

                if (hasCycle(adjacent, graph, visited, node)) {
                    return true;
                }
            }
        }

        return false;
    }

    record Node(int value, int parent) {
    }

}
