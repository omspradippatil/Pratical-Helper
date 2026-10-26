# Experiment 06: Optimal Storage on Tape

## 📋 Problem Statement
**Question 06:** Implement the Optimal Storage on Tape algorithm and compute the storage order for a set of program lengths so that the Mean Retrieval Time (MRT) is minimised.

---

## 🎯 Aim & Objectives
To apply the **Greedy Method** to find an optimal permutation of program lengths stored on a sequential tape medium, minimizing both the total retrieval time and the **Mean Retrieval Time (MRT)**.

---

## 💡 Theory & Concept
Suppose $n$ programs of lengths $l_1, l_2, \dots, l_n$ are stored on a sequential access tape in permutation $I = (i_1, i_2, \dots, i_n)$.

The retrieval time $t_j$ for program $i_j$ is the sum of lengths of all preceding programs plus its own length:
$$t_j = \sum_{k=1}^{j} l_{i_k}$$

The **Total Retrieval Time (TRT)** is:
$$\text{TRT} = \sum_{j=1}^{n} t_j = \sum_{j=1}^{n} \sum_{k=1}^{j} l_{i_k} = \sum_{k=1}^{n} (n - k + 1) \cdot l_{i_k}$$

The **Mean Retrieval Time (MRT)** is:
$$\text{MRT} = \frac{\text{TRT}}{n}$$

### Greedy Strategy:
To minimize the sum $\sum_{k=1}^{n} (n - k + 1) \cdot l_{i_k}$, the largest multiplier $(n - k + 1)$ must multiply the smallest program length. Therefore, sorting the program lengths in **non-decreasing order** ($l_1 \le l_2 \le \dots \le l_n$) yields the minimum MRT.

---

## ⚙️ Algorithm
```text
Algorithm OptimalStorageTape(L, n):
    // L is an array of n program lengths
    Sort L in non-decreasing order: L[1] <= L[2] <= ... <= L[n]
    
    total_time = 0
    current_prefix = 0
    
    for i = 1 to n do:
        current_prefix = current_prefix + L[i]
        total_time = total_time + current_prefix
        
    MRT = total_time / n
    return (L, total_time, MRT)
```

---

## ⏱️ Complexity Analysis
| Metric | Complexity |
|---|---|
| **Sorting (e.g. Quicksort / Timsort)** | $\mathcal{O}(n \log n)$ |
| **Prefix Sum Calculation** | $\mathcal{O}(n)$ |
| **Overall Time Complexity** | $\mathcal{O}(n \log n)$ |
| **Space Complexity** | $\mathcal{O}(n)$ (or $\mathcal{O}(1)$ in-place) |

---

## 🚀 How to Run

### Python (Original OST):
```bash
python3 ost.py
```

### Python (Converted from BFS.cp):
```bash
python3 bfs_converted.py
```

---

## 🖥️ Sample Input & Output
```text
============================================================
       06. OPTIMAL STORAGE ON TAPE (GREEDY METHOD)       
============================================================
Enter program lengths separated by spaces: 9 2 6 1 3 5 4 7 8 9

--- Optimal Storage Results ---
Number of programs: 10
Optimal Order:      [1, 2, 3, 4, 5, 6, 7, 8, 9, 9]

Breakdown of Retrieval Times:
  Program  1 (Length  1): Total Wait Time =   1
  Program  2 (Length  2): Total Wait Time =   3
  Program  3 (Length  3): Total Wait Time =   6
  Program  4 (Length  4): Total Wait Time =  10
  Program  5 (Length  5): Total Wait Time =  15
  Program  6 (Length  6): Total Wait Time =  21
  Program  7 (Length  7): Total Wait Time =  28
  Program  8 (Length  8): Total Wait Time =  36
  Program  9 (Length  9): Total Wait Time =  45
  Program 10 (Length  9): Total Wait Time =  54

Total Retrieval Time (Sum): 219
Mean Retrieval Time (MRT):  21.90
```
