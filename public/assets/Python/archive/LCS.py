def get_length(sequence):
    """Calculates the length of a sequence manually without len()."""
    count = 0
    for _ in sequence:
        count += 1
    return count

def lcs_length(X, Y):
    """Computes the LCS length table using basic iterations and conditionals."""
    m = get_length(X)
    n = get_length(Y)

    # Manually create a 2D matrix dp[m+1][n+1] initialized to 0
    dp = [None] * (m + 1)
    i = 0
    while i <= m:
        dp[i] = [0] * (n + 1)
        i += 1

    # Fill the DP matrix
    i = 1
    while i <= m:
        j = 1
        while j <= n:
            if X[i - 1] == Y[j - 1]:
                dp[i][j] = dp[i - 1][j - 1] + 1
            else:
                # Custom max logic instead of using max()
                val1 = dp[i - 1][j]
                val2 = dp[i][j - 1]
                if val1 >= val2:
                    dp[i][j] = val1
                else:
                    dp[i][j] = val2
            j += 1
        i += 1

    return dp[m][n], dp

def build_lcs(X, Y, dp):
    """Reconstructs the actual LCS sequence from the DP table."""
    i = get_length(X)
    j = get_length(Y)
    
    # Pre-allocate array space for the maximum possible length of LCS
    lcs_len = dp[i][j]
    result = [None] * lcs_len
    
    # We populate the result from back to front to simulate prepending
    write_index = lcs_len - 1

    while i > 0 and j > 0:
        if X[i - 1] == Y[j - 1]:
            result[write_index] = X[i - 1]
            write_index -= 1
            i -= 1
            j -= 1
        elif dp[i - 1][j] >= dp[i][j - 1]:
            i -= 1
        else:
            j -= 1

    # Convert the list of characters back into a string manually
    lcs_string = ""
    for char in result:
        if char is not None:
            lcs_string += char
            
    return lcs_string

def main():
    # Input sequences
    X = "ABCBDAB"
    Y = "BDCABA"

    length, dp_table = lcs_length(X, Y)
    actual_lcs = build_lcs(X, Y, dp_table)

    print("--- Longest Common Subsequence (Pure Python) ---")
    print("Sequence X: " + X)
    print("Sequence Y: " + Y)
    print("LCS Length: " + str(length))
    print("Actual LCS: " + actual_lcs)

if __name__ == "__main__":
    main()



## Pure Python Longest Common Subsequence (LCS)

### Sample Input
```text
Sequence X: "ABCBDAB"
Sequence Y: "BDCABA"
```

### Program Run Output
```text
--- Longest Common Subsequence (Pure Python) ---
Sequence X: ABCBDAB
Sequence Y: BDCABA
LCS Length: 4
Actual LCS: BCBA
```
