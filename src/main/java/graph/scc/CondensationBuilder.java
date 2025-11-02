package graph.scc;

import org.example.*;
import java.util.*;

public class CondensationBuilder {

    /**
     * Строит конденсационный граф (DAG) из исходного графа и списка SCC.
     * @param g исходный граф
     * @param sccs список сильно связанных компонент
     * @return новый граф (DAG), где каждая вершина = SCC
     */
    public Graph build(Graph g, List<List<Integer>> sccs) {
        // 1. Узел → номер компоненты
        Map<Integer, Integer> comp = new HashMap<>();
        for (int i = 0; i < sccs.size(); i++) {
            for (int v : sccs.get(i)) {
                comp.put(v, i);
            }
        }

        // 2. Создаём новый граф на количество SCC
        Graph dag = new Graph(sccs.size());
        Set<String> addedEdges = new HashSet<>(); // чтобы не дублировать рёбра

        // 3. Добавляем рёбра между компонентами
        for (var entry : g.getAdj().entrySet()) {
            int u = entry.getKey();
            for (Edge e : entry.getValue()) {
                int cu = comp.get(u);
                int cv = comp.get(e.to);
                if (cu != cv) {
                    String key = cu + "->" + cv;
                    if (!addedEdges.contains(key)) {
                        dag.addEdge(cu, cv, e.weight);
                        addedEdges.add(key);
                    }
                }
            }
        }

        return dag;
    }
}
