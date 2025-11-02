package tests;
import org.example.Graph;
import graph.topo.*;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class TopologicalSorterTest {

    @Test
    public void testTopoOrder() {
        Graph g = new Graph(4);
        g.addEdge(0, 1, 1);
        g.addEdge(1, 2, 1);
        g.addEdge(2, 3, 1);

        TopologicalSorter sorter = new TopologicalSorter();
        List<Integer> order = sorter.sort(g);

        assertEquals(List.of(0, 1, 2, 3), order);
    }
}
