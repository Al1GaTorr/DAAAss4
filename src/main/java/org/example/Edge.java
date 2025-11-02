package org.example;

public class Edge {
    public int u;       // from
    public int to;      // to
    public int weight;  // вес ребра

    public Edge(int u, int to, int weight) {
        this.u = u;
        this.to = to;
        this.weight = weight;
    }

    public Edge(int to, int weight) { // удобный вариант для хранения в списках
        this.to = to;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return u + " -> " + to + " (" + weight + ")";
    }
}
