def optimal_storage_tape(lengths):
    """
    Calculates the optimal storage order on a tape to minimize 
    total and average retrieval time using a greedy approach.
    """
    # Sort the files in ascending order (greedy choice)
    sorted_lengths = sorted(lengths)
    
    total_time = 0
    current_retrieval_time = 0
    
    # Calculate cumulative retrieval times
    for length in sorted_lengths:
        current_retrieval_time += length
        total_time += current_retrieval_time
        
    n = len(sorted_lengths)
    average_time = total_time / n if n > 0 else 0
    
    return sorted_lengths, total_time, average_time


def main():
    print("--- Optimal Storage on Tape ---")
    try:
        n = int(input("Enter number of files: "))
        lengths = []
        for i in range(n):
            length = int(input(f"Enter length of file {i + 1}: "))
            lengths.append(length)
            
        optimal_order, total_retrieval, average_retrieval = optimal_storage_tape(lengths)
        
        print("\n--- Results ---")
        print(f"Optimal order: {optimal_order}")
        print(f"Total Retrieval Time: {total_retrieval}")
        print(f"Average Retrieval Time: {average_retrieval:.2f}")
        
    except ValueError:
        print("Error: Please enter valid integers for file counts and lengths.")

if __name__ == "__main__":
    main()







## Example Usage

### Input
```text
--- Optimal Storage on Tape ---
Enter number of files: 3
Enter length of file 1: 5
Enter length of file 2: 10
Enter length of file 3: 3
```

### Output
```text
--- Results ---
Optimal order: [3, 5, 10]
Total Retrieval Time: 29
Average Retrieval Time: 9.67
```

### Explanation of Results
1. **Sorted Order**: The files are sorted from shortest to longest `[3, 5, 10]` to minimize waiting times.
2. **Retrieval Math**:
   - Retrieval time for File 1 (size 3) = `3`
   - Retrieval time for File 2 (size 5) = 3 + 5 = `8`
   - Retrieval time for File 3 (size 10) = 8 + 10 = `18`
   - **Total Retrieval Time**: 3 + 8 + 18 = **29**
   - **Average Retrieval Time**: 29 / 3 = **9.67**
