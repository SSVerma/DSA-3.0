package graph.disjointset;

import graph.Graph;

import java.util.List;

public class MakeNetworkConnected {
    public static void main(String[] args) {
        List<List<Integer>> graph = Graph.undirectedBuilder(9)
                .addEdge(0, 1)
                .addEdge(0, 2)
                .addEdge(0, 3)
                .addEdge(1, 2)
                .addEdge(2, 3)
                .addEdge(4, 5)
                .addEdge(5, 6)
                .addEdge(7, 8)
                .build();

        int result = numberOfOperationNeeded(graph);
        System.out.println(result);
    }

    /*
        min operations needed to connect all components.

        0 - 1    3
         \   /
           2

        result:

         0 - 1 - 3
          \
           2

     */
    private static int numberOfOperationNeeded(List<List<Integer>> graph) {
        DisjointSet disjointSet = new DisjointSet(graph.size());

        int extraEdge = 0;

        for (int node = 0; node < graph.size(); node++) {
            for (int adjacent : graph.get(node)) {
                boolean inSameComponent = disjointSet.find(node) == disjointSet.find(adjacent);
                if (inSameComponent) {
                    extraEdge++;
                } else {
                    disjointSet.union(node, adjacent);
                }
            }
        }

        int numberOfComponents = 0;

        for (int node = 0; node < graph.size(); node++) {
            if (disjointSet.parentOf(node) == node) {
                numberOfComponents++;
            }
        }

        int edgesRequired = numberOfComponents - 1;

        return extraEdge >= edgesRequired ? edgesRequired : -1;
    }

}
