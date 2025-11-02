🚦 Smart City Graph Analysis

A Java project for analyzing directed graphs through SCC detection, topological sorting, and shortest path computation — optimized for smart city network modeling.

🧭 Overview

This project simulates Smart City graph analysis by processing multiple datasets (in JSON format) and applying a chain of classical algorithms:

Kosaraju’s algorithm for Strongly Connected Components

Condensation DAG construction

Kahn’s Topological Sort

Shortest Path in DAG

All performance metrics (DFS visits, relaxations, time, etc.) are automatically logged and exported into a CSV report.

🧩 System Architecture
src/
│
├── org/example/
│   ├── Main.java                 # Entry point, orchestrates the analysis
│   ├── Graph.java / Edge.java    # Core graph model
│
├── graph/
│   ├── scc/SCCFinder.java        # Kosaraju’s SCC algorithm
│   ├── topo/TopologicalSorter.java # Kahn’s topological sort
│   ├── dagsp/DAGShortestPath.java  # Shortest path algorithm for DAGs
│
└── utils/
    ├── GraphLoader.java          # Loads JSON into Graph
    ├── Metrics.java / SimpleMetrics.java # Metrics tracking
    ├── CSVExporter.java          # Exports final results to results.csv

🚀 Features

✅ Detects Strongly Connected Components
✅ Builds Condensation DAGs dynamically
✅ Performs Topological Sorting using Kahn’s Algorithm
✅ Calculates Shortest Paths in DAGs
✅ Tracks performance metrics for each dataset
✅ Exports results to CSV with clean format
✅ Fully modular architecture with extendable components

🧪 Example Output

Console:

🗂 Running analysis on: medium_dense.json
SCCs found: 15
Topo order size: 15
Shortest path sample: 
0 1 2 1 3 
time(ns)=49500, dfsVisits=15, dfsEdges=16, push=15, pop=15, relax=16
---------------------------------
✅ All datasets processed successfully!
📁 Results saved to results.csv


CSV:

File	Vertices	Edges	SCC	Topo	Relax	DFS	Push	Pop	Time (ms)
large_dense.json	45	18	45	45	9	45	45	45	3.87
medium_dense.json	15	16	15	15	16	15	15	15	0.09
🧠 Algorithms Implemented
Algorithm	                  Class	                      Description	                              Complexity
Kosaraju’s Algorithm	      SCCFinder	                  Finds strongly connected components	      O(V + E)
Condensation Builder	      CondensationBuilder	Builds  DAG of SCCs                      	        O(V + E)
Kahn’s Algorithm	          TopologicalSorter	          Linear topological ordering        	      O(V + E)
Shortest Path (DAG)	        DAGShortestPath	            Computes min distances in DAG	            O(V + E)
📊 Metrics Tracked
Metric	Description
dfsVisits	Number of DFS node visits
dfsEdges	Number of edges explored
push/pop	Queue operations (Kahn’s algorithm)
relax	Edge relaxations in shortest path
time(ns)	Total runtime in nanoseconds
🧾 Data Format

Example JSON file (small_dense_dag.json):

{
  "vertices": 8,
  "edges": [
    { "u": 0, "v": 1, "w": 2 },
    { "u": 1, "v": 2, "w": 3 },
    { "u": 2, "v": 3, "w": 1 }
  ]
}

🛠️ How to Run
1️⃣ Compile
mvn clean install

2️⃣ Run
java -cp target/classes org.example.Main

3️⃣ Check Results

Console → algorithm output

results.csv → formatted metrics report

📈 Insights

Execution time grows roughly linearly with the number of vertices.

Sparse graphs have lower DFS and Relax counts.

Dense graphs exhibit more relaxations but remain efficient (O(V + E) complexity).

🔮 Future Improvements

Add Longest Path for DAGs

Add Dijkstra’s Algorithm for weighted cyclic graphs

Create graph visualizations using JGraphT or Graphviz

Integrate parallel computation for large-scale datasets

Auto-generate PDF reports with matplotlib-like charts

👨‍💻 Author

Qazybai Ali
🎓 Software Engineering Student @ Astana IT University
