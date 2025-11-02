package org.example;

import graph.scc.*;
import graph.topo.*;
import graph.dagsp.*;
import utils.*;

import java.io.*;


public class Main {
    public static void main(String[] args) {
        File dataDir = new File("src/main/data");
        if (!dataDir.exists()) {
            System.err.println("❌ Folder 'data' not found!");
            return;
        }

        File[] files = dataDir.listFiles((dir, name) -> name.endsWith(".json"));
        if (files == null || files.length == 0) {
            System.err.println("❌ No JSON files found in 'data/'");
            return;
        }

        System.out.println("=== Smart City Graph Analysis ===\n");

        try (PrintWriter writer = new PrintWriter("results.csv")) {
            writer.println("File;Vertices;Edges;SCC_Count;Topo_Size;Relax;DFS_Visits;DFS_Edges;Push;Pop;Time_ms");

            for (File f : files) {
                try {
                    Graph g = GraphLoader.loadFromJson(f.getPath());
                    SimpleMetrics metrics = new SimpleMetrics();

                    // === Анализ ===
                    metrics.startTimer();

                    SCCFinder sccFinder = new SCCFinder(metrics);
                    var sccs = sccFinder.findSCC(g);

                    TopologicalSorter sorter = new TopologicalSorter(metrics);
                    var order = sorter.sort(g);

                    DAGShortestPath dsp = new DAGShortestPath(metrics);
                    int[] dist = dsp.shortestPath(g, 0, order);

                    metrics.stopTimer();

                    // === Результаты ===
                    System.out.printf("📄 %s | SCC=%d | Topo=%d | Time=%.3f ms%n",
                            f.getName(), sccs.size(), order.size(), metrics.getTimeNs() / 1_000_000.0);

                    // Пример кратчайших путей
                    System.out.print("   Shortest paths: ");
                    for (int i = 0; i < Math.min(5, dist.length); i++) {
                        System.out.print((dist[i] == Integer.MAX_VALUE ? "∞" : dist[i]) + " ");
                    }
                    System.out.println("\n");

                    // === CSV ===
                    writer.printf(
                            "%s;%d;%d;%d;%d;%d;%d;%d;%d;%d;%.3f%n",
                            f.getName(),
                            g.size(),
                            g.getEdges().size(),
                            sccs.size(),
                            order.size(),
                            metrics.getRelax(),
                            metrics.getDfsVisits(),
                            metrics.getDfsEdges(),
                            metrics.getKahnPush(),
                            metrics.getKahnPop(),
                            metrics.getTimeNs() / 1_000_000.0
                    );

                } catch (Exception e) {
                    System.err.println("⚠️ Error processing " + f.getName() + ": " + e.getMessage());
                }
            }

            System.out.println("✅ Analysis complete!");
            System.out.println("📁 Results saved to results.csv");

        } catch (IOException e) {
            System.err.println("❌ Failed to write CSV: " + e.getMessage());
        }
    }
}
