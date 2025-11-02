package tests;

import graph.topo.*;
import graph.dagsp.*;
import org.example.Graph;
import org.junit.jupiter.api.Test;
import utils.Metrics;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class DAGShortestPathTest {
    private Metrics metrics;

    @Test
    public void testShortestAndLongestPath() {
        Graph g = new Graph(5);
        g.addEdge(0, 1, 3);
        g.addEdge(0, 2, 6);
        g.addEdge(1, 2, 4);
        g.addEdge(1, 3, 4);
        g.addEdge(2, 3, 8);
        g.addEdge(3, 4, 2);

        TopologicalSorter sorter = new TopologicalSorter();
        List<Integer> topo = sorter.sort(g);

        DAGShortestPath dsp = new DAGShortestPath(metrics);
        int[] shortest = dsp.shortestPath(g, 0, topo);
        int[] longest = dsp.longestPath(g, 0, topo);

        // Проверяем кратчайшие пути
        assertEquals(0, shortest[0]);
        assertEquals(3, shortest[1]);
        assertEquals(6, shortest[2]);
        assertEquals(7, shortest[3]);
        assertEquals(9, shortest[4]);

        // Проверяем, что длиннейшие пути всегда >= кратчайших
        for (int i = 0; i < g.size(); i++) {
            assertTrue(longest[i] >= shortest[i]);
        }

        // Проверяем самую длинную цепочку вручную:
        assertEquals(17, longest[4]);
    }
}
