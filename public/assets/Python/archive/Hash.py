
def hash_function(key, table_size ):
    hash_value = 0
    for char in key:
        hash_value += ord(char)  # Convert character to ASCII value
    return hash_value % table_size

# User input
key = input("Enter a key: ")
table_size = int(input("Enter hash table size: "))

# Calculate hash value
index = hash_function(key, table_size)

print("Hash value (index):", index)


# Output
Enter a key: DeVyAnI
Enter hash table size: 10
Hash value (index): 4

