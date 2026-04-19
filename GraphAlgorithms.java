import java.util.*;

public class GraphAlgorithms {

    // Warshall's Algorithm (Transitive Closure)
    static void warshall(int[][] graph, int n) {
        int[][] reach = new int[n][n];

        // Copy input matrix
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                reach[i][j] = graph[i][j];

        // Warshall's logic
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    reach[i][j] = (reach[i][j] == 1 ||
                                  (reach[i][k] == 1 && reach[k][j] == 1)) ? 1 : 0;
                }
            }
        }

        // Print transitive closure
        System.out.println("Transitive Closure (Reachability Matrix):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(reach[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Topological Sort using Kahn’s Algorithm
    static void topologicalSort(int[][] graph, int n) {
        int[] indegree = new int[n];

        // Compute indegree
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (graph[i][j] == 1)
                    indegree[j]++;

        Queue<Integer> queue = new LinkedList<>();

        // Add vertices with indegree 0
        for (int i = 0; i < n; i++)
            if (indegree[i] == 0)
                queue.add(i);

        List<Integer> topo = new ArrayList<>();

        while (!queue.isEmpty()) {
            int u = queue.poll();
            topo.add(u);

            for (int v = 0; v < n; v++) {
                if (graph[u][v] == 1) {
                    indegree[v]--;
                    if (indegree[v] == 0)
                        queue.add(v);
                }
            }
        }

        // Check for cycle
        if (topo.size() != n) {
            System.out.println("Graph has a cycle. Topological ordering not possible.");
        } else {
            System.out.println("Topological Order:");
            for (int v : topo)
                System.out.print(v + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) {

        int[][] graph = {
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1},
            {0, 0, 0, 0}
        };

        int n = graph.length;

        // Part 1: Warshall
        warshall(graph, n);

        // Part 2: Topological Sort
        topologicalSort(graph, n);
    }
}
