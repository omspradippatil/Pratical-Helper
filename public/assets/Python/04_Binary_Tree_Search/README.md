# Experiment 04: Binary Tree Construction, Traversals & Key Search

## 📋 Problem Statement
**Question 04:** Construct a Binary Tree and perform Traversals of the constructed binary tree also search for the given key.

---

## 🎯 Aim & Objectives
To construct a Binary Search Tree (BST), execute Pre-order, In-order, and Post-order traversals, and search for an element (key) in the tree while tracking the search path.

---

## 💡 Theory & Concept
A **Binary Search Tree (BST)** satisfies the binary search property:
- The left subtree of a node contains only keys lesser than the node's key.
- The right subtree of a node contains only keys greater than the node's key.
- Both left and right subtrees must also be binary search trees.

An **In-order traversal** of a BST yields elements in strictly ascending sorted order.

### Tree Structure (Default Example):
```text
          50
        /    \
      30      70
     /  \    /  \
   20   40  60   80
```

---

## ⚙️ Algorithm
```text
Algorithm InsertBST(root, key):
    if root is null then
        return new Node(key)
    if key < root.key then
        root.left = InsertBST(root.left, key)
    else if key > root.key then
        root.right = InsertBST(root.right, key)
    return root

Algorithm SearchBST(root, key):
    if root is null or root.key == key then
        return root
    if key < root.key then
        return SearchBST(root.left, key)
    else
        return SearchBST(root.right, key)
```

---

## ⏱️ Complexity Analysis
| Operation | Average Case | Worst Case (Skewed Tree) |
|---|---|---|
| **Insertion** | $\mathcal{O}(\log n)$ | $\mathcal{O}(n)$ |
| **Search** | $\mathcal{O}(\log n)$ | $\mathcal{O}(n)$ |
| **Traversals** | $\mathcal{O}(n)$ | $\mathcal{O}(n)$ |
| **Auxiliary Space** | $\mathcal{O}(\log n)$ | $\mathcal{O}(n)$ |

---

## 🚀 How to Run
```bash
python3 bst_search.py
```

---

## 🖥️ Sample Input & Output
```text
============================================================
   04. BINARY SEARCH TREE: TRAVERSALS & KEY SEARCH   
============================================================
Enter keys to insert separated by spaces (or press Enter for default): 50 30 70 20 40 60 80

--- Tree Traversals ---
Pre-order Traversal:  [50, 30, 20, 40, 70, 60, 80]
In-order Traversal:   [20, 30, 40, 50, 60, 70, 80]
Post-order Traversal: [20, 40, 30, 60, 80, 70, 50]

Enter key to search (default 40): 40

--- Search Result for Key: 40 ---
Search Path followed: 50 -> 30 -> 40
Result: Key 40 FOUND in the tree!
```
