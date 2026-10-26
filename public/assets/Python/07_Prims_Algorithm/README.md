# Experiment 07: Prim's Algorithm (Minimum Spanning Tree)

## 📋 Problem Statement
**Question 07:** Implement Prim's Algorithm.

---

## 🎯 Aim & Objectives
To implement **Prim's Algorithm** using a **Greedy Approach** to find a Minimum Spanning Tree (MST) for a connected, weighted, undirected graph represented by an adjacency matrix.

---

## 💡 Theory & Concept
A **Spanning Tree** of a connected graph $G = (V, E)$ is a subgraph that is a tree and includes all vertices of $G$. A **Minimum Spanning Tree (MST)** is a spanning tree with weight less than or equal to the weight of every other spanning tree.

### Prim's Greedy Logic:
1. Maintain two sets of vertices:
   - Vertices already included in the MST (`mst_set`).
   - Vertices not yet included.
2. Initialize key values of all vertices to $\infty$, except the root vertex (vertex $0$), which is set to $0$.
3. At each step, pick vertex $u$ with the minimum key value not yet in `mst_set`.
4. Include $u$ in `mst_set`.
5. For every adjacent vertex $v$ of $u$, if edge weight $(u, v)$ is less than current key value of $v$, update key value of $v$ to $(u, v)$ and set $parent[v] = u$.
6. Repeat until all vertices are included in the MST.

---

## ⚙️ Algorithm
```text
Algorithm PrimMST(graph, V):
    Initialize key[0..V-1] = INF, parent[0..V-1] = NIL, mstSet[0..V-1] = False
    key[0] = 0
    parent[0] = -1
    
    for count = 0 to V - 1 do:
        u = vertex with min key[u] among those where mstSet[u] is False
        mstSet[u] = True
        
        for each adjacent vertex v of u do:
            if graph[u][v] != 0 and mstSet[v] == False and graph[u][v] < key[v] then:
                parent[v] = u
                key[v] = graph[u][v]
                
    Print parent array edges and total weight
```

---

## ⏱️ Complexity Analysis
| Implementation Type | Time Complexity | Space Complexity |
|---|---|---|
| **Adjacency Matrix (Standard)** | $\mathcal{O}(V^2)$ | $\mathcal{O}(V)$ |
| **Adjacency List + Min-Heap** | $\mathcal{O}(E \log V)$ | $\mathcal{O}(V + E)$ |

---

## 🚀 How to Run
```bash
python3 mst.py
```

---

## 🖥️ Sample Input & Output
```text
=======================================================
       07. PRIM'S MINIMUM SPANNING TREE (MST)       
=======================================================
Using default 5x5 graph:
Adjacency Matrix:
  [0, 2, 0, 6, 0]
  [2, 0, 3, 8, 5]
  [0, 3, 0, 0, 7]
  [6, 8, 0, 0, 9]
  [0, 5, 7, 9, 0]

--- Minimum Spanning Tree Edges ---
Edge 		Weight
-------------------------
0 - 1 		2
1 - 2 		3
0 - 3 		6
1 - 4 		5
-------------------------
Total Minimum Spanning Tree Weight: 16
```
