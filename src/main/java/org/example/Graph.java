package org.example;
import java.util.*;

public class Graph {
    private final int n; // количество вершин
    private final Map<Integer, List<Edge>> adj; // список смежности

    public Graph(int n) {
        this.n = n;
        this.adj = new HashMap<>();
        for (int i = 0; i < n; i++) adj.put(i, new ArrayList<>());
    }

    // добавить ребро
    public void addEdge(int from, int to, int weight) {
        adj.get(from).add(new Edge(to, weight));
    }

    // получить список смежности
    public Map<Integer, List<Edge>> getAdj() {
        return adj;
    }

    // количество вершин
    public int size() {
        return n;
    }

    // получить список всех рёбер (нужно для алгоритмов)
    public List<Edge> getEdges() {
        List<Edge> allEdges = new ArrayList<>();
        for (var entry : adj.entrySet()) {
            int u = entry.getKey();
            for (Edge e : entry.getValue()) {
                allEdges.add(new Edge(u, e.to, e.weight));
            }
        }
        return allEdges;
    }
}
