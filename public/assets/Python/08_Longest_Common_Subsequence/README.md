# Experiment 08: Longest Common Subsequence (LCS)

## 📋 Problem Statement
**Question 08:** To implement the Longest Common Subsequence (LCS) algorithm for two input strings and output the longest matching subsequence using dynamic programming.

---

## 🎯 Aim & Objectives
To solve the **Longest Common Subsequence** problem using **Dynamic Programming**, constructing the 2D prefix table to compute optimal length and backtracking to reconstruct the actual matching subsequence.

---

## 💡 Theory & Concept
Given two sequences $X = \langle x_1, x_2, \dots, x_m \rangle$ and $Y = \langle y_1, y_2, \dots, y_n \rangle$, a subsequence of $X$ is formed by deleting zero or more characters without changing the relative order of the remaining characters.

An LCS is a subsequence common to both $X$ and $Y$ having maximum length.

### Optimal Substructure / DP Formulation:
Let $c[i, j]$ denote the length of an LCS of prefixes $X[1..i]$ and $Y[1..j]$:
$$c[i, j] = \begin{cases} 0 & \text{if } i = 0 \text{ or } j = 0 \\ c[i-1, j-1] + 1 & \text{if } i, j > 0 \text{ and } x_i = y_j \\ \max(c[i-1, j], c[i, j-1]) & \text{if } i, j > 0 \text{ and } x_i \ne y_j \end{cases}$$

---

## ⚙️ Algorithm
```text
Algorithm LCS-Length(X, Y):
    m = length(X), n = length(Y)
    Let c[0..m, 0..n] be a new array initialized to 0
    
    for i = 1 to m do:
        for j = 1 to n do:
            if X[i-1] == Y[j-1] then
                c[i, j] = c[i-1, j-1] + 1
            else
                c[i, j] = max(c[i-1, j], c[i, j-1])
                
    return c[m, n] and table c

Algorithm Print-LCS(c, X, Y, i, j):
    if i == 0 or j == 0 then return
    if X[i-1] == Y[j-1] then
        Print-LCS(c, X, Y, i-1, j-1)
        Print(X[i-1])
    else if c[i-1, j] >= c[i, j-1] then
        Print-LCS(c, X, Y, i-1, j)
    else
        Print-LCS(c, X, Y, i, j-1)
```

---

## ⏱️ Complexity Analysis
| Metric | Complexity |
|---|---|
| **Time Complexity** | $\mathcal{O}(m \cdot n)$ |
| **Space Complexity** | $\mathcal{O}(m \cdot n)$ for DP table |
| **Reconstruction Time** | $\mathcal{O}(m + n)$ |

*Where $m$ and $n$ are the lengths of strings $X$ and $Y$.*

---

## 🚀 How to Run
```bash
python3 lcs.py
```

---

## 🖥️ Sample Input & Output
```text
============================================================
      08. LONGEST COMMON SUBSEQUENCE (LCS) via DP       
============================================================
Enter first string X  (or press Enter for 'ABCBDAB'): 
Enter second string Y (or press Enter for 'BDCABA'):  

--- Inputs ---
String X: 'ABCBDAB' (length 7)
String Y: 'BDCABA' (length 6)

--- Dynamic Programming Matrix ---
            B   D   C   A   B   A
    |  0   0   0   0   0   0   0
  A |  0   0   0   0   1   1   1
  B |  0   1   1   1   1   2   2
  C |  0   1   1   2   2   2   2
  B |  0   1   1   2   2   3   3
  D |  0   1   2   2   2   3   3
  A |  0   1   2   2   3   3   4
  B |  0   1   2   2   3   4   4

--- LCS Result ---
Length of Longest Common Subsequence: 4
Actual Longest Common Subsequence:    'BCBA'
```
*(Note: 'BDAB' is also a valid LCS of length 4 for these sequences).*
