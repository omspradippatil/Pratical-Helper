def floyd_warshall(graph, n):
    """
    Computes all-pairs shortest paths using the Floyd-Warshall algorithm.
    Written completely from scratch without any built-in functions.
    """
    # Define a large integer value manually to represent Infinity (INF)
    INF = 999999999

    # 1. Create a matrix dist[n][n] and copy the initial values from graph
    dist = [None] * n
    i = 0
    while i < n:
        dist[i] = [0] * n
        j = 0
        while j < n:
            dist[i][j] = graph[i][j]
            j += 1
        i += 1

    # 9. Find all-pairs shortest paths
    k = 0
    while k < n:
        i = 0
        while i < n:
            j = 0
            while j < n:
                # 12. Check if a shorter path exists via intermediate vertex k
                if dist[i][k] != INF and dist[k][j] != INF and (dist[i][k] + dist[k][j] < dist[i][j]):
                    dist[i][j] = dist[i][k] + dist[k][j]
                j += 1
            i += 1
        k += 1

    # 20. Display the shortest distance matrix
    print("--- Shortest Distance Matrix ---")
    i = 0
    while i < n:
        line_str = ""
        j = 0
        while j < n:
            if dist[i][j] == INF:
                cell = "INF"
            else:
                cell = str(dist[i][j])
            
            # Form tabbed separation manually
            line_str += cell + "\t"
            j += 1
        print(line_str)
        i += 1

def main():
    INF = 999999999
    
    # Example input matrix representing the graph weights
    # 0 means self-loop distance, INF means no direct edge connection
    graph = [
        [0, 5, INF, 10],
        [INF, 0, 3, INF],
        [INF, INF, 0, 1],
        [INF, INF, INF, 0]
    ]
    
    n = 4  # Number of vertices
    
    floyd_warshall(graph, n)

if __name__ == "__main__":
    main()



### Input Weighted Matrix
```text
[
  [  0,   5, INF,  10],
  [INF,   0,   3, INF],
  [INF, INF,   0,   1],
  [INF, INF, INF,   0]
]
```

### Program Run Output
```text
--- Shortest Distance Matrix ---
0	5	8	9	
INF	0	3	4	
INF	INF	0	1	
INF	INF	INF	0	
```
