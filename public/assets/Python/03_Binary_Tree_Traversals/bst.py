# Define Node class
class Node:
    def __init__(self, data):
        self.data = data
        self.left = None
        self.right = None


# Recursive function to insert nodes
def insertNode():
    data = int(input("Enter data (-1 for NULL): "))

    if data == -1:
        return None

    node = Node(data)

    print(f"Enter left child of {data}")
    node.left = insertNode()

    print(f"Enter right child of {data}")
    node.right = insertNode()

    return node


# Preorder Traversal (Root, Left, Right)
def preorder(root):
    if root is None:
        return
    print(root.data, end=" ")
    preorder(root.left)
    preorder(root.right)


# Inorder Traversal (Left, Root, Right)
def inorder(root):
    if root is None:
        return
    inorder(root.left)
    print(root.data, end=" ")
    inorder(root.right)


# Postorder Traversal (Left, Right, Root)
def postorder(root):
    if root is None:
        return
    postorder(root.left)
    postorder(root.right)
    print(root.data, end=" ")


# Main Program
print("Create Binary Tree:")
root = insertNode()

print("\nPreorder Traversal:")
preorder(root)

print("\nInorder Traversal:")
inorder(root)

print("\nPostorder Traversal:")
postorder(root)
