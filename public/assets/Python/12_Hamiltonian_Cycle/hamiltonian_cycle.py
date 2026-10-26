def is_safe(v, graph, path, pos):
    if graph[path[pos - 1]][v] == 0:
        return False
    for i in range(pos):
        if path[i] == v:
            return False
    return True


def ham_cycle_util(graph, path, pos, n, cycles):
    if pos == n:
        if graph[path[pos - 1]][path[0]] == 1:
            cycles.append(list(path) + [path[0]])
        return

    for v in range(1, n):
        if is_safe(v, graph, path, pos):
            path[pos] = v
            ham_cycle_util(graph, path, pos + 1, n, cycles)
            path[pos] = -1


def find_hamiltonian_cycles(graph):
    n = len(graph)
    path = [-1] * n
    path[0] = 0
    cycles = []
    ham_cycle_util(graph, path, 1, n, cycles)
    return cycles


def main():
    graph = [
        [0, 1, 0, 1, 0],
        [1, 0, 1, 1, 1],
        [0, 1, 0, 0, 1],
        [1, 1, 0, 0, 1],
        [0, 1, 1, 1, 0],
    ]

    cycles = find_hamiltonian_cycles(graph)

    if not cycles:
        print("No Hamiltonian Cycle found")
    else:
        print(f"Total Hamiltonian Cycles found: {len(cycles)}")
        for i, cycle in enumerate(cycles, 1):
            print(f"Cycle {i}:", " -> ".join(map(str, cycle)))


if __name__ == "__main__":
    main()
