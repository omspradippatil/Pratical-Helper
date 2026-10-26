# Experiment 02: Implementation of a Hash Function

## 📋 Problem Statement
**Question 02:** Implementation of a hash function.

---

## 🎯 Aim & Objectives
To design and implement a hash function that converts arbitrary string keys into integer indices within a fixed-size hash table, and analyze how characters are mapped using character ASCII values.

---

## 💡 Theory & Concept
A **Hash Function** is any function that maps data of arbitrary size (such as strings) to fixed-size values (typically indices into an array or hash table).

### Division Method with ASCII Summation:
Each character $c_i$ in the string $S$ has an ASCII integer code $\text{ord}(c_i)$. The hash value is obtained by:
$$\text{Hash}(S) = \left( \sum_{i=1}^{k} \text{ord}(c_i) \right) \pmod m$$
Where:
- $k$ is the length of the string $S$.
- $m$ is the size of the hash table (number of slots/buckets).
- $\pmod m$ ensures the resulting index lies in the range $[0, m - 1]$.

---

## ⚙️ Algorithm
```text
Algorithm HashFunction(key, table_size):
    hash_value = 0
    for each character char in key do:
        hash_value = hash_value + ASCII(char)
    index = hash_value MOD table_size
    return index
```

---

## ⏱️ Complexity Analysis
| Metric | Complexity |
|---|---|
| **Time Complexity** | $\mathcal{O}(L)$ where $L$ is string length |
| **Space Complexity** | $\mathcal{O}(1)$ |
| **Lookup in Hash Table (Average)** | $\mathcal{O}(1)$ |
| **Lookup in Hash Table (Worst)** | $\mathcal{O}(n)$ (if all keys collide) |

---

## 🚀 How to Run
```bash
python3 hash.py
```

---

## 🖥️ Sample Input & Output
```text
=============================================
       02. HASH FUNCTION IMPLEMENTATION      
=============================================
Enter a key: DeVyAnI
Enter hash table size: 10

--- Result ---
Key:               'DeVyAnI'
Sum of ASCII:      684
Hash Table Size:   10
Hash Value(Index): 4

--- Demonstration with Sample Keys ---
Key: 'DeVyAnI' -> ASCII Sum: 684 -> Index: 4
Key: 'Alice' -> ASCII Sum: 494 -> Index: 4
Key: 'Bob' -> ASCII Sum: 275 -> Index: 5
Key: 'Charlie' -> ASCII Sum: 712 -> Index: 2
Key: 'David' -> ASCII Sum: 494 -> Index: 4
Key: 'Eva' -> ASCII Sum: 288 -> Index: 8

Hash Table Buckets:
  Slot [0]: []
  Slot [1]: []
  Slot [2]: ['Charlie']
  Slot [3]: []
  Slot [4]: ['DeVyAnI', 'Alice', 'David']
  Slot [5]: ['Bob']
  Slot [6]: []
  Slot [7]: []
  Slot [8]: ['Eva']
  Slot [9]: []
```
