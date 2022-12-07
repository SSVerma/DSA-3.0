package graph.topological;

import graph.Graph;

import java.util.*;

public class TopologicalSort {
    public static void main(String[] args) {
        List<List<Integer>> graph = Graph.directedBuilder(6)
                .addEdge(2, 3)
                .addEdge(3, 1)
                .addEdge(4, 0)
                .addEdge(4, 1)
                .addEdge(5, 0)
                .addEdge(5, 2)
                .build();

        int[] result = topologicalSortingByKahnAlgo(graph);
        System.out.println(Arrays.toString(result));
    }

    private static int[] topologicalSort(List<List<Integer>> graph) {
        boolean[] visited = new boolean[graph.size()];
        Stack<Integer> stack = new Stack<>();

        for (int node = 0; node < graph.size(); node++) {
            if (!visited[node]) {
                dfs(graph, node, visited, stack);
            }
        }

        int[] result = new int[graph.size()];

        int index = 0;
        while (!stack.isEmpty()) {
            result[index++] = stack.pop();
        }

        return result;
    }

    private static void dfs(List<List<Integer>> graph, int node, boolean[] visited, Stack<Integer> stack) {
        visited[node] = true;

        for (int adjacent : graph.get(node)) {
            if (!visited[adjacent]) {
                dfs(graph, adjacent, visited, stack);
            }
        }

        stack.push(node);
    }

    /*
       Uses BFS.

       1. compute in-degree
       2. place zero in-degree nodes in queue
       3. reduce in-degree of adjacent, put in queue if in-degree is zero.
     */
    private static int[] topologicalSortingByKahnAlgo(List<List<Integer>> graph) {
        int[] inDegrees = new int[graph.size()];

        // compute in-degree
        // 1 -> {2, 3}
        // 4 -> {2, 1}
        for (List<Integer> adjacent : graph) {
            for (int node : adjacent) {
                inDegrees[node]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();

        for (int node = 0; node < inDegrees.length; node++) {
            if (inDegrees[node] == 0) {
                q.add(node);
            }
        }

        int[] result = new int[graph.size()];
        int index = 0;

        while (!q.isEmpty()) {
            int node = q.remove();
            result[index++] = node;

            for (int adjacent : graph.get(node)) {
                inDegrees[adjacent]--;
                if (inDegrees[adjacent] == 0) {
                    q.add(adjacent);
                }
            }
        }

        return result;
    }

    private static boolean hasCycle(List<List<Integer>> graph) {
        int[] inDegrees = new int[graph.size()];

        // compute in-degree
        // 1 -> {2, 3}
        // 4 -> {2, 1}
        for (List<Integer> adjacent : graph) {
            for (int node : adjacent) {
                inDegrees[node]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();

        for (int node = 0; node < inDegrees.length; node++) {
            if (inDegrees[node] == 0) {
                q.add(node);
            }
        }

        int count = 0;

        while (!q.isEmpty()) {
            int node = q.remove();
            count++;

            for (int adjacent : graph.get(node)) {
                inDegrees[adjacent]--;
                if (inDegrees[adjacent] == 0) {
                    q.add(adjacent);
                }
            }
        }

        // if able to generate topological sort, no cycle, otherwise cycle exists.
        return count != graph.size();
    }

}
