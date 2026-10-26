# Global board array and configuration variable (N)
# For N=4, arrays are allocated to handle positions up to index 4 (1-indexed mapping matching your pseudocode)
N = 4
board = [0] * (N + 1)
solution_count = 0

def absolute_value(val):
    """Calculates absolute value manually without using abs()."""
    if val < 0:
        return -val
    return val

def is_safe(row, col):
    """
    Checks if a queen can be safely placed at board[row] = col.
    Compares against previously placed queens in rows 1 to row-1.
    """
    i = 1
    while i < row:
        # Check if another queen is in the same column
        if board[i] == col:
            return False

        # Check if another queen is on the same diagonal
        # |board[i] - col| == |i - row|
        diff_col = absolute_value(board[i] - col)
        diff_row = absolute_value(i - row)
        if diff_col == diff_row:
            return False
        
        i += 1

    return True

def nqueen(row):
    """Recursively attempts to place queens row by row."""
    global solution_count
    
    # if row > N then print board
    if row > N:
        solution_count += 1
        print("Solution #" + str(solution_count) + ":")
        
        # Display the 1D board representation
        board_str = "["
        idx = 1
        while idx <= N:
            board_str += str(board[idx])
            if idx < N:
                board_str += ", "
            idx += 1
        board_str += "]"
        print("Board array state: " + board_str)
        
        # Print a visual 2D grid matrix representation
        r = 1
        while r <= N:
            row_visual = ""
            c = 1
            while c <= N:
                if board[r] == c:
                    row_visual += "Q "
                else:
                    row_visual += ". "
                c += 1
            print(row_visual)
            r += 1
        print("") # Blank line separator
        return

    # for col ← 1 to N do
    col = 1
    while col <= N:
        if is_safe(row, col):
            board[row] = col      # board[row] ← col
            nqueen(row + 1)       # NQUEEN(row + 1)
            board[row] = 0        # Backtrack: board[row] ← 0
        col += 1

def main():
    print("--- " + str(N) + "-Queens Backtracking Simulation ---")
    # Start placing queens from row 1
    nqueen(1)
    print("Total solutions found: " + str(solution_count))

if __name__ == "__main__":
    main()







### Configuration
* **N** = 4 (Grid Size: 4x4)

### Program Run Output
```text
--- 4-Queens Backtracking Simulation ---
Solution #1:
Board array state: [2, 4, 1, 3]
. Q . . 
. . . Q 
Q . . . 
. . Q . 

Solution #2:
Board array state: [3, 1, 4, 2]
. . Q . 
Q . . . 
. . . Q 
. Q . . 

Total solutions found: 2
```
