# Experiment 05: Breadth First Search (BFS)

## 📋 Problem Statement
**Question 05:** Implement Breadth First Search graph traversal.

---

## 🎯 Aim & Objectives
To implement the **Breadth First Search (BFS)** graph traversal algorithm using an adjacency matrix and a FIFO queue, visiting vertices level-by-level starting from a given source node.

---

## 💡 Theory & Concept
Breadth-First Search (BFS) is a graph exploration technique that starts at a designated source vertex and explores all neighboring nodes at the present depth level before moving on to the nodes at the next depth level.

### Key Characteristics:
- Uses a **Queue (FIFO)** data structure.
- Maintains a `visited` array to prevent revisiting nodes in cyclic graphs.
- Discovers the shortest path in unweighted graphs.

### Graph Representation:
An undirected graph with $V=5$ vertices and edges:
- `0 - 1`, `0 - 2`
- `1 - 3`
- `2 - 4`
- `3 - 4`

```text
       (0)
      /   \
    (1)   (2)
     |     |
    (3)---(4)
```

---

## ⚙️ Algorithm
```text
Algorithm BFS(Graph, source, V):
    Initialize visited[0..V-1] = False
    Create empty Queue Q
    
    visited[source] = True
    Enqueue(Q, source)
    
    while Q is not empty do:
        u = Dequeue(Q)
        Print(u)
        
        for each vertex v adjacent to u do:
            if not visited[v] then
                visited[v] = True
                Enqueue(Q, v)
```

---

## ⏱️ Complexity Analysis
| Metric | Complexity (Adjacency Matrix) | Complexity (Adjacency List) |
|---|---|---|
| **Time Complexity** | $\mathcal{O}(V^2)$ | $\mathcal{O}(V + E)$ |
| **Space Complexity** | $\mathcal{O}(V)$ (Queue & Visited array) | $\mathcal{O}(V)$ |

*Where $V$ is number of vertices and $E$ is number of edges.*

---

## 🚀 How to Run
```bash
python3 bfs.py
```

---

## 🖥️ Sample Input & Output
```text
=======================================================
      05. BREADTH FIRST SEARCH (BFS) TRAVERSAL      
=======================================================
Enter custom graph adjacency matrix? (y/n, default 'n'): y
Enter number of vertices: 5
Enter 5 rows of adjacency matrix (space-separated 0s and 1s):
0 1 1 0 0
1 0 0 1 0
1 0 0 0 1
0 1 0 0 1
0 0 1 1 0
Enter starting vertex (0 to 4): 0

BFS Traversal Order: 0 -> 1 -> 2 -> 3 -> 4
```
