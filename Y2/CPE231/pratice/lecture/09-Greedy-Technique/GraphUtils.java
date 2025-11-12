public class GraphUtils {

    public static class Edge implements Comparable<Edge> {

        public int from;
        public int to;
        public int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }

        @Override
        public String toString() {
            return "(" + from + ", " + to + ", " + weight + ")";
        }
    }

    public static class UnionFind {

        private int[] parent;
        private int[] rank;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) {
                return false;
            }

            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }

            return true;
        }
    }

    public static class BinaryTreeNode<T> {

        public T data;
        public BinaryTreeNode<T> left;
        public BinaryTreeNode<T> right;

        public BinaryTreeNode(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        public BinaryTreeNode(
            T data,
            BinaryTreeNode<T> left,
            BinaryTreeNode<T> right
        ) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }
    }

    public static class WeightedBinaryTreeNode<T>
        implements Comparable<WeightedBinaryTreeNode<T>> {

        public T data;
        public int weight;
        public WeightedBinaryTreeNode<T> left;
        public WeightedBinaryTreeNode<T> right;

        public WeightedBinaryTreeNode(T data, int weight) {
            this.data = data;
            this.weight = weight;
            this.left = null;
            this.right = null;
        }

        public WeightedBinaryTreeNode(
            int weight,
            WeightedBinaryTreeNode<T> left,
            WeightedBinaryTreeNode<T> right
        ) {
            this.data = null;
            this.weight = weight;
            this.left = left;
            this.right = right;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        @Override
        public int compareTo(WeightedBinaryTreeNode<T> other) {
            return Integer.compare(this.weight, other.weight);
        }
    }
}
