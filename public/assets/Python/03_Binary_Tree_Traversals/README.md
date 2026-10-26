# Experiment 03: Binary Tree Construction & Traversals

## 📋 Problem Statement
**Question 03:** Construct a binary tree from user inputs and perform Pre-order, In-order, and Post-order traversals to visit nodes in different recursive orders.

---

## 🎯 Aim & Objectives
To construct a binary tree dynamically using recursive node insertion from user inputs (using sentinel value `-1` for `NULL`), and perform depth-first recursive traversals:
1. **Pre-order Traversal** (`Root -> Left -> Right`)
2. **In-order Traversal** (`Left -> Root -> Right`)
3. **Post-order Traversal** (`Left -> Right -> Root`)

---

## 💡 Theory & Concept
A **Binary Tree** is a hierarchical data structure in which each node has at most two children, referred to as the *left child* and the *right child*.

### Tree Structure Used in Example:
```text
          10
         /  \
        5    15
       / \
      3   7
```

### Traversal Definitions:
1. **Pre-order (`N-L-R`)**:
   - Visit current node $N$.
   - Recursively traverse left subtree $L$.
   - Recursively traverse right subtree $R$.
2. **In-order (`L-N-R`)**:
   - Recursively traverse left subtree $L$.
   - Visit current node $N$.
   - Recursively traverse right subtree $R$.
3. **Post-order (`L-R-N`)**:
   - Recursively traverse left subtree $L$.
   - Recursively traverse right subtree $R$.
   - Visit current node $N$.

---

## ⚙️ Algorithm
```text
Algorithm PreOrder(root):
    if root is not null then
        print(root.data)
        PreOrder(root.left)
        PreOrder(root.right)

Algorithm InOrder(root):
    if root is not null then
        InOrder(root.left)
        print(root.data)
        InOrder(root.right)

Algorithm PostOrder(root):
    if root is not null then
        PostOrder(root.left)
        PostOrder(root.right)
        print(root.data)
```

---

## ⏱️ Complexity Analysis
| Operation | Time Complexity | Auxiliary Space Complexity |
|---|---|---|
| **Tree Construction** | $\mathcal{O}(n)$ | $\mathcal{O}(h)$ (Recursion call stack) |
| **Pre-order Traversal** | $\mathcal{O}(n)$ | $\mathcal{O}(h)$ |
| **In-order Traversal** | $\mathcal{O}(n)$ | $\mathcal{O}(h)$ |
| **Post-order Traversal** | $\mathcal{O}(n)$ | $\mathcal{O}(h)$ |

*Where $n$ is total number of nodes, and $h$ is tree height ($\mathcal{O}(\log n)$ for balanced tree, $\mathcal{O}(n)$ worst case).*

---

## 🚀 How to Run
```bash
python3 bst.py
```

---

## 🖥️ Sample Input & Output

### Interactive Execution:
```text
=======================================================
      03. BINARY TREE CONSTRUCTION & TRAVERSALS     
=======================================================
Build tree interactively? (y/n, default 'y'): y

Enter tree nodes recursively (-1 indicates NULL):
Enter node data (-1 for NULL): 10
-> Enter left child of 10:
Enter node data (-1 for NULL): 5
-> Enter left child of 5:
Enter node data (-1 for NULL): 3
-> Enter left child of 3:
Enter node data (-1 for NULL): -1
-> Enter right child of 3:
Enter node data (-1 for NULL): -1
-> Enter right child of 5:
Enter node data (-1 for NULL): 7
-> Enter left child of 7:
Enter node data (-1 for NULL): -1
-> Enter right child of 7:
Enter node data (-1 for NULL): -1
-> Enter right child of 10:
Enter node data (-1 for NULL): 15
-> Enter left child of 15:
Enter node data (-1 for NULL): -1
-> Enter right child of 15:
Enter node data (-1 for NULL): -1

========================================
             TRAVERSAL RESULTS          
========================================
Pre-order Traversal  (Root, Left, Right): 10 5 3 7 15
In-order Traversal   (Left, Root, Right): 3 5 7 10 15
Post-order Traversal (Left, Right, Root): 3 7 5 15 10
```
