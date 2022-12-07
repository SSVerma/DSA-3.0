package graph.disjointset;

import graph.Graph;

import java.util.ArrayList;
import java.util.List;

public class MinSpanningTree {
    public static void main(String[] args) {
        List<List<Graph.Node>> graph = Graph.undirectedWeightedBuilder(3)
                .addEdge(0, 1, 5)
                .addEdge(1, 2, 3)
                .addEdge(0, 2, 1)
                .build();

        int result = minSpanningTree(graph);
        System.out.println(result);
    }

    private static int minSpanningTree(List<List<Graph.Node>> graph) {
        List<Edge> edges = new ArrayList<>();

        for (int node = 0; node < graph.size(); node++) {
            for (Graph.Node adjacent : graph.get(node)) {
                edges.add(new Edge(node, adjacent.value(), adjacent.weight()));
            }
        }

        edges.sort((x, y) -> x.weight - y.weight);

        DisjointSet disjointSet = new DisjointSet(graph.size());

        int result = 0;

        for (Edge edge : edges) {
            boolean isInSameComponent = disjointSet.find(edge.from) == disjointSet.find(edge.to);

            if (!isInSameComponent) {
                disjointSet.union(edge.from, edge.to);
                result = result + edge.weight;
            }
        }

        return result;
    }

    record Edge(int from, int to, int weight) {
    }

}
