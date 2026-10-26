# Experiment 11: N-Queens Problem (Backtracking)

## 📋 Problem Statement
**Question 11:** To Place N queens on an NxN chessboard so that no two queens attack each other, using efficient backtracking to find valid configurations.

---

## 🎯 Aim & Objectives
To solve the classic **N-Queens Problem** using **Backtracking**, placing $N$ non-attacking queens on an $N \times N$ chessboard such that no two queens share the same row, column, or diagonal.

---

## 💡 Theory & Concept
In chess, a queen attacks any piece on the same row, column, or diagonal. 

### Formulation:
- Since every row must contain exactly one queen, we represent a solution as an array $X = [x_1, x_2, \dots, x_n]$, where $x_i$ represents the column position of the queen in row $i$.
- **Column constraint**: $x_i \ne x_j$ for all $i \ne j$.
- **Diagonal constraint**: Two queens at $(i, x_i)$ and $(j, x_j)$ are on the same diagonal if:
  $$|x_i - x_j| = |i - j|$$

### Backtracking Strategy:
- Place queens row by row.
- At row $k$, attempt to place a queen in column $col \in \{1, \dots, N\}$.
- If `is_safe(k, col)` returns True, proceed to row $k + 1$.
- If no column is safe, backtrack to row $k - 1$ and try the next column.

---

## ⚙️ Algorithm
```text
Algorithm PlaceQueen(row, n, board):
    if row > n then:
        Record board as a valid solution
        return
        
    for col = 1 to n do:
        if is_safe(row, col, board) then:
            board[row] = col
            PlaceQueen(row + 1, n, board)
            board[row] = 0 // Backtrack

Algorithm is_safe(row, col, board):
    for i = 1 to row - 1 do:
        if board[i] == col or |board[i] - col| == |i - row| then:
            return False
    return True
```

---

## ⏱️ Complexity Analysis
| Metric | Complexity |
|---|---|
| **Upper Bound Time Complexity** | $\mathcal{O}(N!)$ |
| **Pruned Search Space** | Drastically reduced by safe placement bounding |
| **Space Complexity** | $\mathcal{O}(N)$ (Recursion stack and 1D board array) |

---

## 🚀 How to Run
```bash
python3 n_queen.py
```

---

## 🖥️ Sample Input & Output
```text
============================================================
        11. N-QUEENS PROBLEM USING BACKTRACKING        
============================================================
Enter board size N (e.g., 4 or 8, default 4): 4

Searching for all valid configurations on 4x4 chessboard...

--- Found 2 Solution(s) for N = 4 ---

Solution #1:
Board column vector (row 1 to 4): [2, 4, 1, 3]
  . Q . .
  . . . Q
  Q . . .
  . . Q .

Solution #2:
Board column vector (row 1 to 4): [3, 1, 4, 2]
  . . Q .
  Q . . .
  . . . Q
  . Q . .

Total solutions found: 2
```
