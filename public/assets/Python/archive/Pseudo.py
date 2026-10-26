MAX = 100

queue = [-1] * MAX
front = -1
rear = -1
visited = [0] * MAX


def enqueue(vertex):
    global front, rear

    if rear == MAX - 1:
        return  # Queue full

    if front == -1:
        front = 0

    rear += 1
    queue[rear] = vertex


def dequeue():
    global front, rear

    if front == -1:
        return -1  # Queue empty

    vertex = queue[front]

    if front >= rear:
        front = -1
        rear = -1
    else:
        front += 1

    return vertex


def bfs(graph, start_vertex, vertices):
    global visited

    # Initialize visited array
    for i in range(vertices):
        visited[i] = 0

    enqueue(start_vertex)
    visited[start_vertex] = 1

    print("BFS Traversal:", end=" ")

    while front != -1:
        current_vertex = dequeue()
        print(current_vertex, end=" ")

        for i in range(vertices):
            if graph[current_vertex][i] == 1 and visited[i] == 0:
                enqueue(i)
                visited[i] = 1

    print()


# MAIN
vertices = int(input("Enter number of vertices: "))

graph = []

print("Enter adjacency matrix:")

for i in range(vertices):
    row = list(map(int, input().split()))
    graph.append(row)

start_vertex = int(input("Enter starting vertex: "))

bfs(graph, start_vertex, vertices)   




# Input 
Enter number of vertices: 5
Enter adjacency matrix:
0 1 1 0 0
1 0 0 1 0
1 0 0 0 1
0 1 0 0 1
0 0 1 1 0
Enter starting vertex: 0

#Output 
BFS Traversal: 0 1 2 3 4
