# Experiment 01: Implementation of Merge Sort

## 📋 Problem Statement
**Question 01:** Implementation of Merge Sort.

---

## 🎯 Aim & Objectives
To implement the **Merge Sort** algorithm using the **Divide and Conquer** paradigm, sort an array of numerical elements in ascending order, and analyze its time and space complexities.

---

## 💡 Theory & Concept
Merge Sort is an efficient, stable, comparison-based sorting algorithm. It operates on the principle of **Divide and Conquer**:
1. **Divide**: Divide the unsorted list of size $n$ into two sub-lists of size approximately $n/2$ until each sub-list contains a single element (base case).
2. **Conquer**: Recursively sort both sub-lists.
3. **Combine (Merge)**: Merge the two sorted sub-lists back into a single sorted list.

### Recurrence Relation:
$$T(n) = 2T(n/2) + \Theta(n)$$
By Master Theorem, $T(n) = \Theta(n \log n)$.

---

## ⚙️ Algorithm
```text
Algorithm MergeSort(A, low, high):
    if low < high then
        mid = (low + high) / 2
        MergeSort(A, low, mid)
        MergeSort(A, mid + 1, high)
        Merge(A, low, mid, high)

Algorithm Merge(A, low, mid, high):
    Create left subarray L = A[low..mid]
    Create right subarray R = A[mid+1..high]
    i = 0, j = 0, k = low
    while i < length(L) and j < length(R) do:
        if L[i] <= R[j] then
            A[k] = L[i]
            i = i + 1
        else
            A[k] = R[j]
            j = j + 1
        k = k + 1
    Copy remaining elements of L and R to A
```

---

## ⏱️ Complexity Analysis
| Metric | Complexity |
|---|---|
| **Best Case Time Complexity** | $\mathcal{O}(n \log n)$ |
| **Average Case Time Complexity** | $\mathcal{O}(n \log n)$ |
| **Worst Case Time Complexity** | $\mathcal{O}(n \log n)$ |
| **Space Complexity** | $\mathcal{O}(n)$ (Auxiliary space for merging) |
| **Stability** | Yes |

---

## 🚀 How to Run
```bash
python3 merge_sort.py
```

---

## 🖥️ Sample Input & Output

### Sample 1:
```text
=============================================
        01. MERGE SORT IMPLEMENTATION        
=============================================
Enter numbers separated by spaces: 38 27 43 3 9 82 10

Original array: [38, 27, 43, 3, 9, 82, 10]
Sorted array:   [3, 9, 10, 27, 38, 43, 82]
```

### Sample 2:
```text
Enter numbers separated by spaces: 64 25 12 22 11

Original array: [64, 25, 12, 22, 11]
Sorted array:   [11, 12, 22, 25, 64]
```
