package graph.scc;
import org.example.Edge;
import org.example.Graph;
import utils.Metrics;

import java.util.*;

public class SCCFinder {
    private Metrics metrics;
    private int time = 0;
    private Stack<Integer> stack;
    private boolean[] onStack;
    private int[] disc, low;
    private List<List<Integer>> sccList;

    public SCCFinder() {} // пустой конструктор (если без метрик)
    public SCCFinder(Metrics metrics) { this.metrics = metrics; }

    public List<List<Integer>> findSCC(Graph g) {
        int n = g.size();
        stack = new Stack<>();
        onStack = new boolean[n];
        disc = new int[n];
        low = new int[n];
        sccList = new ArrayList<>();
        Arrays.fill(disc, -1);

        for (int v = 0; v < n; v++)
            if (disc[v] == -1)
                dfs(v, g);
        return sccList;
    }

    private void dfs(int v, Graph g) {
        disc[v] = low[v] = ++time;
        stack.push(v);
        onStack[v] = true;

        // ✅ Подсчёт визита вершины
        if (metrics != null) metrics.incDFSVisit();

        for (Edge e : g.getAdj().get(v)) {
            // ✅ Подсчёт обхода ребра
            if (metrics != null) metrics.incDFSEdge();

            int to = e.to;
            if (disc[to] == -1) {
                dfs(to, g);
                low[v] = Math.min(low[v], low[to]);
            } else if (onStack[to]) {
                low[v] = Math.min(low[v], disc[to]);
            }
        }

        if (low[v] == disc[v]) {
            List<Integer> comp = new ArrayList<>();
            int x;
            do {
                x = stack.pop();
                onStack[x] = false;
                comp.add(x);
            } while (x != v);
            sccList.add(comp);
        }
    }
}
