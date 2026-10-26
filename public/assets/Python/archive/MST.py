def get_vertex_count(graph):
    """Calculates the number of vertices manually without len()."""
    count = 0
    for _ in graph:
        count += 1
    return count

def min_key(v_count, key, mst_set):
    """Finds the vertex with the minimum key value from the remaining vertices."""
    # Using a large manual value representing infinity
    min_val = 999999999 
    min_index = -1

    # Manual loop structure without range()
    v = 0
    while v < v_count:
        if not mst_set[v] and key[v] < min_val:
            min_val = key[v]
            min_index = v
        v += 1

    return min_index

def prim_mst(graph):
    """Computes and prints the MST using pure array manipulations."""
    v_count = get_vertex_count(graph)

    # Initialize fixed-size lists manually without append() or multipliers
    parent = [None] * v_count
    key = [None] * v_count
    mst_set = [None] * v_count
    
    i = 0
    while i < v_count:
        key[i] = 999999999
        mst_set[i] = False
        i += 1

    # Initialize root vertex
    key[0] = 0
    parent[0] = -1

    count = 0
    while count < v_count - 1:
        # Pick the minimum key vertex 
        u = min_key(v_count, key, mst_set)
        
        # Guard against disconnected components
        if u == -1:
            break

        # Add the picked vertex to the MST set
        mst_set[u] = True

        # Update adjacent vertices manually
        v = 0
        while v < v_count:
            # graph[u][v] > 0 means an edge exists
            if graph[u][v] > 0 and not mst_set[v] and graph[u][v] < key[v]:
                parent[v] = u
                key[v] = graph[u][v]
            v += 1
            
        count += 1

    # Print the constructed MST
    print("Edge \tWeight")
    idx = 1
    while idx < v_count:
        if parent[idx] is not None:
            # Formatted manual string creation
            print(str(parent[idx]) + " - " + str(idx) + " \t" + str(graph[idx][parent[idx]]))
        idx += 1

def main():
    # 5x5 Adjacency Matrix representing your graph
    graph = [,
 ,
 ,
 ,
        [0, 5, 7, 9, 0]
    ]

    print("--- Prim's Minimum Spanning Tree (Pure Python) ---")
    prim_mst(graph)

if __name__ == "__main__":
    main()





### Input Graph Matrix
```text
[,
 ,
 ,
 ,
    [0, 5, 7, 9, 0]
]
```

### Output
```text
--- Prim's Minimum Spanning Tree (Pure Python) ---
Edge 	Weight
0 - 1 	2
1 - 2 	3
0 - 3 	6
1 - 4 	5
```
