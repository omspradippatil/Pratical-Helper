# Experiment 12: Hamiltonian Cycle (Backtracking)

## 📋 Problem Statement
**Question 12:** Implementation of Hamiltonian Cycle.

---

## 🎯 Aim & Objectives
To implement the **Hamiltonian Cycle** problem using **Backtracking**, determining whether a given undirected graph contains a simple closed loop that visits each vertex exactly once and returns to the initial vertex.

---

## 💡 Theory & Concept
A **Hamiltonian Path** in an undirected graph is a path that visits each vertex exactly once. A **Hamiltonian Cycle** (or Hamiltonian circuit) is a Hamiltonian path that is a cycle (starts and ends at the same vertex).

### Backtracking Strategy:
1. Fix vertex $0$ as the starting point: `path[0] = 0`.
2. For position $pos$ from $1$ to $V - 1$:
   - Try placing every candidate vertex $v \in \{1, \dots, V - 1\}$.
   - Verify feasibility with `is_safe(v, graph, path, pos)`:
     1. An edge exists from `path[pos - 1]` to $v$.
     2. Vertex $v$ is not already present in `path[0..pos-1]`.
3. When $pos == V$, check if an edge exists from `path[V - 1]` back to `path[0]`.
   - If yes, record the cycle.
4. If no valid vertex can be placed at $pos$, backtrack to $pos - 1$.

---

## ⚙️ Algorithm
```text
Algorithm HamiltonianCycle(Graph, V):
    Initialize path[0..V-1] = -1
    path[0] = 0
    FindCycles(Graph, path, 1, V)

Algorithm FindCycles(Graph, path, pos, V):
    if pos == V then:
        if Graph[path[V-1]][path[0]] == 1 then:
            Print path + path[0] as a valid cycle
        return
        
    for v = 1 to V - 1 do:
        if is_safe(v, Graph, path, pos) then:
            path[pos] = v
            FindCycles(Graph, path, pos + 1, V)
            path[pos] = -1 // Backtrack

Algorithm is_safe(v, Graph, path, pos):
    if Graph[path[pos-1]][v] == 0 then return False
    for i = 0 to pos - 1 do:
        if path[i] == v then return False
    return True
```

---

## ⏱️ Complexity Analysis
| Metric | Complexity |
|---|---|
| **Worst-Case Time Complexity** | $\mathcal{O}(V!)$ |
| **Space Complexity** | $\mathcal{O}(V)$ (Path array & recursion stack) |
| **Decision Problem Classification** | NP-Complete |

---

## 🚀 How to Run
```bash
python3 hamiltonian_cycle.py
```

---

## 🖥️ Sample Input & Output
```text
============================================================
      12. HAMILTONIAN CYCLE USING BACKTRACKING       
============================================================
Using default 5-vertex graph:
Adjacency Matrix:
  [0, 1, 0, 1, 0]
  [1, 0, 1, 1, 1]
  [0, 1, 0, 0, 1]
  [1, 1, 0, 0, 1]
  [0, 1, 1, 1, 0]

--- Results ---
Found 2 Hamiltonian Cycle(s):
  Cycle #1: 0 -> 1 -> 2 -> 4 -> 3 -> 0
  Cycle #2: 0 -> 3 -> 4 -> 2 -> 1 -> 0
```
