# Experiment 09: Dijkstra's Shortest Path Algorithm

## 📋 Problem Statement
**Question 09:** To find the shortest paths from a single source vertex to all others in a weighted graph using Dijkstra’s algorithm.

---

## 🎯 Aim & Objectives
To implement **Dijkstra's Algorithm** for finding the shortest paths from a designated single source vertex to all other vertices in a directed/undirected graph with non-negative edge weights.

---

## 💡 Theory & Concept
Dijkstra's Algorithm is a **Greedy Algorithm** that solves the Single-Source Shortest Path (SSSP) problem. It maintains a set of visited vertices and repeatedly selects the unvisited vertex with the minimum tentative distance.

### Edge Relaxation:
For edge $(u, v)$ with weight $w(u, v)$:
$$\text{if } \text{dist}[u] + w(u, v) < \text{dist}[v] \implies \text{dist}[v] = \text{dist}[u] + w(u, v)$$

### Restrictions:
- Edge weights must be non-negative ($w(u, v) \ge 0$).
- For graphs with negative weights, the Bellman-Ford algorithm is used instead.

---

## ⚙️ Algorithm
```text
Algorithm Dijkstra(Graph, source, V):
    Initialize dist[0..V-1] = INF, visited[0..V-1] = False
    dist[source] = 0
    
    for count = 0 to V - 1 do:
        u = unvisited vertex with minimum dist[u]
        visited[u] = True
        
        for each neighbor v of u do:
            if not visited[v] and Graph[u][v] != 0 and Graph[u][v] != INF then:
                if dist[u] + Graph[u][v] < dist[v] then:
                    dist[v] = dist[u] + Graph[u][v]
                    parent[v] = u
                    
    return dist, parent
```

---

## ⏱️ Complexity Analysis
| Implementation | Time Complexity | Space Complexity |
|---|---|---|
| **Adjacency Matrix** | $\mathcal{O}(V^2)$ | $\mathcal{O}(V)$ |
| **Adjacency List + Min-Heap** | $\mathcal{O}((V + E) \log V)$ | $\mathcal{O}(V + E)$ |
| **Fibonacci Heap** | $\mathcal{O}(E + V \log V)$ | $\mathcal{O}(V + E)$ |

---

## 🚀 How to Run
```bash
python3 dijkstra.py
```

---

## 🖥️ Sample Input & Output
```text
============================================================
       09. DIJKSTRA'S SHORTEST PATH ALGORITHM       
============================================================
Using default 5x5 weighted graph:
Adjacency Matrix (INF = no direct edge):
  [0, 4, 2, 'INF', 'INF']
  [4, 0, 3, 2, 3]
  [2, 3, 0, 4, 5]
  ['INF', 2, 4, 0, 1]
  ['INF', 3, 5, 1, 0]
Source Vertex: 0

--- Shortest Paths from Source Vertex 0 ---
Destination 	Distance 	Path
---------------------------------------------
Vertex 0 	       0 	0
Vertex 1 	       4 	0 -> 1
Vertex 2 	       2 	0 -> 2
Vertex 3 	       6 	0 -> 1 -> 3
Vertex 4 	       7 	0 -> 1 -> 3 -> 4
```
