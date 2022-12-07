package graph.shortestpath;

import graph.Graph;

import java.util.*;

public class DijkstraAlgoPriorityQueue {
    public static void main(String[] args) {
        List<List<Graph.Node>> graph = Graph.directedWeightedBuilder(5 + 1)
                .addEdge(1, 2, 2)
                .addEdge(2, 5, 5)
                .addEdge(2, 3, 4)
                .addEdge(1, 4, 1)
                .addEdge(4, 3, 3)
                .addEdge(3, 5, 1)
                .build();

        List<Integer> result = sourceToDestinationShortestPath(1, 5, graph);
        System.out.println(result);
    }

    private static int[] shortestPath(int srcNode, List<List<Graph.Node>> graph) {
        Queue<Pair> q = new PriorityQueue<>((x, y) -> x.distance - y.distance);
        q.add(new Pair(srcNode, 0));

        int[] distance = new int[graph.size()];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[srcNode] = 0;

        while (!q.isEmpty()) {
            Pair node = q.remove();

            for (Graph.Node adjacent : graph.get(node.value)) {
                // distance of adjacent from node after applying its weight
                // 1 --[4]-> 3
                // d = d[1] + 4
                int distanceNow = distance[node.value] + adjacent.weight();

                if (distanceNow < distance[adjacent.value()]) {
                    distance[adjacent.value()] = distanceNow;
                    q.add(new Pair(adjacent.value(), distanceNow));
                }
            }
        }

        return distance;
    }

    private static List<Integer> sourceToDestinationShortestPath(
            int source,
            int destination,
            List<List<Graph.Node>> graph
    ) {
        Queue<Pair> q = new PriorityQueue<>((x, y) -> x.distance - y.distance);
        q.add(new Pair(source, 0));

        int[] distance = new int[graph.size()];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        int[] parent = new int[graph.size()];

        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        while (!q.isEmpty()) {
            Pair node = q.remove();

            for (Graph.Node adjacent : graph.get(node.value)) {
                int distanceNow = distance[node.value] + adjacent.weight();

                if (distanceNow < distance[adjacent.value()]) {
                    distance[adjacent.value()] = distanceNow;
                    parent[adjacent.value()] = node.value;

                    q.add(new Pair(adjacent.value(), distanceNow));
                }
            }
        }

        int node = destination;
        LinkedList<Integer> result = new LinkedList<>();

        while (parent[node] != node) {
            result.addFirst(node);
            node = parent[node];
        }

        result.addFirst(source);
        return result;
    }

    record Pair(int value, int distance) {
    }

}
