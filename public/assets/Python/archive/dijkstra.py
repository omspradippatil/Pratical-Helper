def dijkstra(graph, source, n):
    """
    Computes single-source shortest paths using Dijkstra's algorithm.
    Written completely from scratch without any built-in functions.
    """
    # Define a large integer value manually to represent Infinity (INF)
    INF = 999999999

    # Initialize distance and visited arrays manually
    distance = [None] * n
    visited = [None] * n

    # for i ← 0 to n-1 do
    i = 0
    while i < n:
        distance[i] = INF
        visited[i] = False
        i += 1

    # distance[source] ← 0
    distance[source] = 0

    # for count ← 0 to n-1 do
    count = 0
    while count < n:
        
        # Find the unvisited vertex with the minimum distance
        u = -1
        min_dist = INF
        
        v_min = 0
        while v_min < n:
            if not visited[v_min] and distance[v_min] < min_dist:
                min_dist = distance[v_min]
                u = v_min
            v_min += 1

        # if u = -1 then break
        if u == -1:
            break

        # visited[u] ← true
        visited[u] = True

        # for v ← 0 to n-1 do
        v = 0
        while v < n:
            # Check edge conditions and relaxation rule
            if (not visited[v] and 
                graph[u][v] != 0 and 
                distance[u] + graph[u][v] < distance[v]):
                
                distance[v] = distance[u] + graph[u][v]
            v += 1

        count += 1

    return distance

def main():
    INF = 999999999

    # Example 5x5 adjacency matrix representation of a weighted graph
    # 0 means no direct edge or self-loop
    graph = [
        [0, 4, 2, INF, INF],
,
 ,
        [INF, 2, 4, 0, 1],
        [INF, 3, 5, 1, 0]
    ]

    n = 5          # Number of vertices
    source = 0     # Starting source vertex

    distances = dijkstra(graph, source, n)

    # Print results manually
    print("--- Dijkstra's Shortest Paths from Source " + str(source) + " ---")
    print("Vertex \tDistance from Source")
    
    idx = 0
    while idx < n:
        dist_str = "INF" if distances[idx] == INF else str(distances[idx])
        print(str(idx) + " \t\t " + dist_str)
        idx += 1

if __name__ == "__main__":
    main()








## Experiment 9: Dijkstra's Algorithm

### Input Graph Matrix (Source Vertex: 0)
```text
[
  [  0,   4,   2, INF, INF],
  [  4,   0,   3,   2,   3],
  [  2,   3,   0,   4,   5],
  [INF,   2,   4,   0,   1],
  [INF,   3,   5,   1,   0]
]
```

### Program Run Output
```text
--- Dijkstra's Shortest Paths from Source 0 ---
Vertex 	Distance from Source
0 		 0
1 		 4
2 		 2
3 		 6
4 		 7
```
