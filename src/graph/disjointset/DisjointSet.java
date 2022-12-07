package graph.disjointset;

public class DisjointSet {
    private final int[] size;
    private final int[] parent;

    public DisjointSet(int nodeCount) {
        this.size = new int[nodeCount + 1];
        this.parent = new int[nodeCount + 1];

        for (int i = 0; i <= nodeCount; i++) {
            this.parent[i] = i;
            this.size[i] = 1;
        }
    }

    // find ultimate parent of node
    // and then compress path
    public int find(int node) {
        if (parent[node] == node) {
            return node;
        }

        int ultimateParent = find(parent[node]);
        parent[node] = ultimateParent;

        return ultimateParent;
    }

    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);

        if (p1 == p2) {
            return;
        }

        if (size[p1] > size[p2]) {
            // attach p2 to p1
            parent[p2] = p1;
            size[p1] = size[p1] + size[p2];
        } else {
            // attach p1 to p2
            parent[p1] = p2;
            size[p2] = size[p1] + size[p2];
        }
    }

    public int parentOf(int node) {
        return parent[node];
    }

    public int sizeOf(int node) {
        return size[node];
    }
}
