package graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    public static UndirectedBuilder undirectedBuilder(int nodeCount) {
        return new UndirectedBuilder(nodeCount);
    }

    public static UndirectedWeightedBuilder undirectedWeightedBuilder(int nodeCount) {
        return new UndirectedWeightedBuilder(nodeCount);
    }

    public static DirectedBuilder directedBuilder(int nodeCount) {
        return new DirectedBuilder(nodeCount);
    }

    public static DirectedWeightedBuilder directedWeightedBuilder(int nodeCount) {
        return new DirectedWeightedBuilder(nodeCount);
    }

    public static class UndirectedBuilder {
        private final List<List<Integer>> adjacencyList = new ArrayList<>();

        public UndirectedBuilder(int nodeCount) {
            for (int i = 0; i < nodeCount; i++) {
                adjacencyList.add(new ArrayList<>());
            }
        }

        public UndirectedBuilder addEdge(int from, int to) {
            adjacencyList.get(from).add(to);
            adjacencyList.get(to).add(from);
            return this;
        }

        public List<List<Integer>> build() {
            return adjacencyList;
        }
    }

    public static class UndirectedWeightedBuilder {
        private final List<List<Node>> adjacencyList = new ArrayList<>();

        public UndirectedWeightedBuilder(int nodeCount) {
            for (int i = 0; i < nodeCount; i++) {
                adjacencyList.add(new ArrayList<>());
            }
        }

        public UndirectedWeightedBuilder addEdge(int from, int to, int weight) {
            adjacencyList.get(from).add(new Node(to, weight));
            adjacencyList.get(to).add(new Node(from, weight));
            return this;
        }

        public List<List<Node>> build() {
            return adjacencyList;
        }
    }

    public static class DirectedBuilder {
        private final List<List<Integer>> adjacencyList = new ArrayList<>();

        public DirectedBuilder(int nodeCount) {
            for (int i = 0; i < nodeCount; i++) {
                adjacencyList.add(new ArrayList<>());
            }
        }

        public DirectedBuilder addEdge(int from, int to) {
            adjacencyList.get(from).add(to);
            return this;
        }

        public List<List<Integer>> build() {
            return adjacencyList;
        }
    }

    public static class DirectedWeightedBuilder {
        private final List<List<Node>> adjacencyList = new ArrayList<>();

        public DirectedWeightedBuilder(int nodeCount) {
            for (int i = 0; i < nodeCount; i++) {
                adjacencyList.add(new ArrayList<>());
            }
        }

        public DirectedWeightedBuilder addEdge(int from, int to, int weight) {
            adjacencyList.get(from).add(new Node(to, weight));
            return this;
        }

        public List<List<Node>> build() {
            return adjacencyList;
        }
    }

    public static void display(List<List<Integer>> adjacencyList) {
        for (int i = 0; i < adjacencyList.size(); i++) {
            StringBuilder builder = new StringBuilder();
            builder.append(i).append(":");
            for (int adjacent : adjacencyList.get(i)) {
                builder.append("->").append(adjacent);
            }
            System.out.println(builder);
        }
    }

    public record Node(int value, int weight) {
    }

}
