class Node:
    def __init__(self, key):
        self.key = key
        self.left = None
        self.right = None


def search(root, key):
    if root is None:
        return False

    if root.key == key:
        return True

    if key < root.key:
        return search(root.left, key)
    else:
        return search(root.right, key)


# Create BST
root = Node(50)
root.left = Node(30)
root.right = Node(70)
root.left.left = Node(20)
root.left.right = Node(40)
root.right.left = Node(60)
root.right.right = Node(80)

# Search
key = 40

if search(root, key):
    print("Key found")
else:
    print("Key not found")
