package graph.dagsp;

import org.example.Edge;
import org.example.Graph;
import utils.Metrics;
import java.util.*;

public class DAGShortestPath {
    private final Metrics metrics;

    public DAGShortestPath(Metrics metrics) {
        this.metrics = metrics;
    }

    public int[] shortestPath(Graph dag, int start, List<Integer> topoOrder) {
        int n = dag.size();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        for (int u : topoOrder) {
            if (dist[u] != Integer.MAX_VALUE) {
                for (Edge e : dag.getAdj().get(u)) {
                    int v = e.to;
                    int w = e.weight;
                    if (dist[v] > dist[u] + w) {
                        dist[v] = dist[u] + w;
                        if (metrics != null) metrics.incRelax();
                    }
                }
            }
        }
        return dist;
    }

    public int[] longestPath(Graph dag, int start, List<Integer> topoOrder) {
        int n = dag.size();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MIN_VALUE);
        dist[start] = 0;

        for (int u : topoOrder) {
            if (dist[u] != Integer.MIN_VALUE) {
                for (Edge e : dag.getAdj().get(u)) {
                    int v = e.to;
                    int w = e.weight;
                    if (dist[v] < dist[u] + w) {
                        dist[v] = dist[u] + w;
                        if (metrics != null) metrics.incRelax();
                    }
                }
            }
        }
        return dist;
    }
}
