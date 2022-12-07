package graph.disjointset;

import graph.Graph;

import java.util.List;

public class NumberOfProvinces {
    public static void main(String[] args) {
        List<List<Integer>> graph = Graph.undirectedBuilder(7 + 1)
                .addEdge(1, 2)
                .addEdge(2, 3)
                .addEdge(4, 5)
                .addEdge(6, 7)
                .build();

        int result = numberOfProvinces(graph);
        System.out.println(result);
    }

    private static int numberOfProvinces(List<List<Integer>> graph) {
        DisjointSet disjointSet = new DisjointSet(graph.size());

        for (int node = 1; node < graph.size(); node++) {
            for (int adjacent : graph.get(node)) {
                disjointSet.union(node, adjacent);
            }
        }

        int result = 0;

        for (int node = 1; node < graph.size(); node++) {
            if (disjointSet.parentOf(node) == node) {
                result++;
            }
        }

        return result;
    }

}
