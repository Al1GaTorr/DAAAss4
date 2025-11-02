package tests;
import graph.scc.*;
import org.example.Graph;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class SCCFinderTest {

    @Test
    public void testSimpleCycleSCC() {
        Graph g = new Graph(5);
        g.addEdge(0, 1, 1);
        g.addEdge(1, 2, 1);
        g.addEdge(2, 0, 1); // цикл
        g.addEdge(3, 4, 1); // цепочка

        SCCFinder finder = new SCCFinder();
        List<List<Integer>> sccs = finder.findSCC(g);

        // Ожидаем 3 SCC: [0,1,2], [3], [4]
        assertEquals(3, sccs.size());

        // Проверяем наличие компоненты [0,1,2]
        boolean found012 = sccs.stream().anyMatch(l -> l.containsAll(List.of(0, 1, 2)));
        assertTrue(found012);

        // Проверяем отдельные компоненты [3] и [4]
        boolean found3 = sccs.stream().anyMatch(l -> l.contains(3) && l.size() == 1);
        boolean found4 = sccs.stream().anyMatch(l -> l.contains(4) && l.size() == 1);
        assertTrue(found3);
        assertTrue(found4);
    }
}
