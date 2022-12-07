package graph.shortestpath;

import graph.Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CheapestFlightsWithinKStops {
    public static void main(String[] args) {
        List<List<Graph.Node>> graph = Graph.directedWeightedBuilder(4)
                .addEdge(0, 1, 100)
                .addEdge(1, 2, 100)
                .addEdge(1, 3, 600)
                .addEdge(2, 0, 100)
                .addEdge(2, 3, 200)
                .build();

        int result = findCost(graph, 0, 3, 1);
        System.out.println(result);
    }

    private static int findCost(
            List<List<Graph.Node>> graph,
            int srcNode,
            int destNode,
            int maxStops
    ) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(srcNode, 0, 0));

        int[] costs = new int[graph.size()];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[srcNode] = 0;

        while (!q.isEmpty()) {
            Node node = q.remove();

            for (Graph.Node adjacent : graph.get(node.value)) {
                if (node.stops > maxStops) {
                    break;
                }

                int costNow = node.cost + adjacent.weight();
                int stopsNow = node.stops + 1;

                if (costNow < costs[adjacent.value()]) {
                    costs[adjacent.value()] = costNow;
                    q.add(new Node(adjacent.value(), stopsNow, costNow));
                }
            }
        }

        return costs[destNode];
    }

    record Node(int value, int stops, int cost) {
    }

}
