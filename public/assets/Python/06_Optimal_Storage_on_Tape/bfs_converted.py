def main():
    n = int(input("Enter the number of programs: "))
    lengths = list(map(int, input(f"Enter the lengths of the {n} programs separated by spaces: ").split()))

    lengths.sort()

    print("\nOptimal ordering of program lengths: ", end="")
    for x in lengths:
        print(x, end=" ")
    print()

    total_retrieval_time = 0
    current_prefix_sum = 0

    print("\nBreakdown of Retrieval Times:")
    for i in range(n):
        current_prefix_sum += lengths[i]
        total_retrieval_time += current_prefix_sum
        print(f"  Program {i + 1} (Length {lengths[i]}): Total wait time = {current_prefix_sum}")

    mrt = total_retrieval_time / n

    print(f"\nTotal Retrieval Time (Sum): {total_retrieval_time}")
    print(f"Mean Retrieval Time (MRT): {mrt:.2f}")

if __name__ == "__main__":
    main()
