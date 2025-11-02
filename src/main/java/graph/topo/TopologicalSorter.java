package graph.topo;

import org.example.Edge;
import org.example.Graph;
import utils.Metrics;
import java.util.*;

public class TopologicalSorter {
    private Metrics metrics;

    public TopologicalSorter() {} // если метрики не нужны
    public TopologicalSorter(Metrics metrics) { this.metrics = metrics; }

    public List<Integer> sort(Graph dag) {
        int n = dag.size();
        int[] indeg = new int[n];

        for (List<Edge> list : dag.getAdj().values())
            for (Edge e : list)
                indeg[e.to]++;

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++)
            if (indeg[i] == 0) {
                q.add(i);
                if (metrics != null) metrics.incKahnPush(); // ✅ push (добавление в очередь)
            }

        List<Integer> order = new ArrayList<>();
        while (!q.isEmpty()) {
            int v = q.poll();
            if (metrics != null) metrics.incKahnPop(); // ✅ pop (удаление из очереди)

            order.add(v);
            for (Edge e : dag.getAdj().get(v)) {
                if (--indeg[e.to] == 0) {
                    q.add(e.to);
                    if (metrics != null) metrics.incKahnPush(); // ✅ push
                }
            }
        }
        return order;
    }
}
