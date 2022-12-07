package graph.shortestpath;

import graph.Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ShortestPath {
    public static void main(String[] args) {
        List<List<Integer>> graph = Graph.undirectedBuilder(9)
                .addEdge(0, 1)
                .addEdge(0, 3)
                .addEdge(1, 0)
                .addEdge(1, 2)
                .addEdge(1, 3)
                .addEdge(2, 1)
                .addEdge(2, 6)
                .addEdge(3, 0)
                .addEdge(3, 4)
                .addEdge(4, 3)
                .addEdge(4, 5)
                .addEdge(5, 4)
                .addEdge(5, 6)
                .addEdge(6, 2)
                .addEdge(6, 5)
                .addEdge(6, 7)
                .addEdge(6, 8)
                .addEdge(7, 6)
                .addEdge(7, 8)
                .addEdge(8, 6)
                .addEdge(8, 7)
                .build();

        int[] result = shortestPath(0, graph);
        System.out.println(Arrays.toString(result));
    }

    /*
       Using regular queue.
     */
    private static int[] shortestPath(int srcNode, List<List<Integer>> graph) {
        int[] distance = new int[graph.size()];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[srcNode] = 0;

        Queue<Integer> q = new LinkedList<>();
        q.add(srcNode);

        boolean[] visited = new boolean[graph.size()];
        visited[srcNode] = true;

        while (!q.isEmpty()) {
            int node = q.remove();

            for (int adjacent : graph.get(node)) {
                if (visited[adjacent]) {
                    continue;
                }

                int distanceNow = distance[node] + 1;

                if (distanceNow < distance[adjacent]) {
                    distance[adjacent] = distanceNow;
                }

                visited[adjacent] = true;
                q.add(adjacent);
            }
        }

        return distance;
    }

}
