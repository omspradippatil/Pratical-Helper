# Experiment 10: Floyd-Warshall Algorithm (All-Pairs Shortest Paths)

## 📋 Problem Statement
**Question 10:** To implement the Floyd-Warshall algorithm for computing shortest paths between every pair of vertices in a weighted graph using an adjacency matrix input.

---

## 🎯 Aim & Objectives
To implement the **Floyd-Warshall algorithm** using **Dynamic Programming** to determine the length of the shortest path between all pairs of vertices in an edge-weighted directed graph.

---

## 💡 Theory & Concept
The Floyd-Warshall algorithm considers intermediate vertices sequentially. Let $D^{(k)}[i, j]$ be the weight of a shortest path from vertex $i$ to vertex $j$ for which all intermediate vertices are in the set $\{1, 2, \dots, k\}$.

### Recurrence Relation:
$$D^{(k)}[i, j] = \min \left( D^{(k-1)}[i, j], \; D^{(k-1)}[i, k] + D^{(k-1)}[k, j] \right)$$

### Base Case:
$D^{(0)}[i, j]$ is simply the edge weight $W[i, j]$ from the original adjacency matrix:
- $0$ if $i = j$
- $w(i, j)$ if edge $(i, j)$ exists
- $\infty$ if no direct edge exists

---

## ⚙️ Algorithm
```text
Algorithm FloydWarshall(W, n):
    Let dist be an n x n matrix of minimum distances initialized to W
    
    for k = 0 to n - 1 do:
        for i = 0 to n - 1 do:
            for j = 0 to n - 1 do:
                if dist[i][k] + dist[k][j] < dist[i][j] then:
                    dist[i][j] = dist[i][k] + dist[k][j]
                    
    return dist
```

---

## ⏱️ Complexity Analysis
| Metric | Complexity |
|---|---|
| **Time Complexity** | $\Theta(V^3)$ (Three nested loops of size $V$) |
| **Space Complexity** | $\Theta(V^2)$ (Matrix storage) |
| **Handles Negative Edges** | Yes (provided there are no negative weight cycles) |

---

## 🚀 How to Run
```bash
python3 fwa.py
```

---

## 🖥️ Sample Input & Output
```text
============================================================
     10. FLOYD-WARSHALL ALL-PAIRS SHORTEST PATHS     
============================================================
Using default 4x4 directed graph:

--- Initial Adjacency Matrix ---
       [0]:>6 [1]:>6 [2]:>6 [3]:>6
[0]:         0       5     INF      10 
[1]:       INF       0       3     INF 
[2]:       INF     INF       0       1 
[3]:       INF     INF     INF       0 

--- Shortest Distance Matrix (All Pairs) ---
       [0]:>6 [1]:>6 [2]:>6 [3]:>6
[0]:         0       5       8       9 
[1]:       INF       0       3       4 
[2]:       INF     INF       0       1 
[3]:       INF     INF     INF       0 
```
