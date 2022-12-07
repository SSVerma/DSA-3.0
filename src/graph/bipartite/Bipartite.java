package graph.bipartite;

import graph.Graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Bipartite {
    public static void main(String[] args) {
        List<List<Integer>> graph = Graph.undirectedBuilder(5 + 1)
                .addEdge(1, 2)
                .addEdge(2, 3)
                .addEdge(3, 4)
                .addEdge(4, 5)
                .addEdge(5, 1)
                .build();

        boolean result = isBipartiteGraph(1, graph);
        System.out.println(result);
    }

    /*
       if graph can be colored with 2 different colors
       and no 2 adjacent node has same colors.

     */
    private static boolean isBipartiteGraph(
            int srcNode,
            List<List<Integer>> graph
    ) {
        // 0 -> not colored
        int colorOne = 1;
        int colorTwo = 2;

        int[] colors = new int[graph.size()];
        colors[srcNode] = colorOne;

        Queue<Integer> q = new LinkedList<>();
        q.add(srcNode);

        while (!q.isEmpty()) {
            int node = q.remove();

            for (int adjacent : graph.get(node)) {
                if (colors[adjacent] == colors[node]) {
                    return false;
                }

                // not yet colored
                if (colors[adjacent] == 0) {
                    if (colors[node] == colorOne) {
                        colors[adjacent] = colorTwo;
                    } else {
                        colors[adjacent] = colorOne;
                    }
                    q.add(adjacent);
                }
            }
        }

        return true;
    }

    private static boolean isBipartiteGraph(
            int node,
            int color,
            List<List<Integer>> graph,
            int[] colors
    ) {
        colors[node] = color;

        for (int adjacent : graph.get(node)) {
            if (colors[adjacent] == colors[node]) {
                return false;
            }

            // visited
            if (colors[adjacent] != 0) {
                continue;
            }

            int nextColor = color == 1 ? 2 : 1;

            if (!isBipartiteGraph(adjacent, nextColor, graph, colors)) {
                return false;
            }
        }

        return true;
    }

}
