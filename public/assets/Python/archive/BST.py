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


#Output 
Create Binary Tree:
Enter data (-1 for NULL): 10
Enter left child of 10
Enter data (-1 for NULL): 5
Enter left child of 5
Enter data (-1 for NULL): 3
Enter left child of 3
Enter data (-1 for NULL): -1
Enter right child of 3
Enter data (-1 for NULL): -1
Enter right child of 5
Enter data (-1 for NULL): 7
Enter left child of 7
Enter data (-1 for NULL): -1
Enter right child of 7
Enter data (-1 for NULL): -1
Enter right child of 10
Enter data (-1 for NULL): 15
Enter left child of 15
Enter data (-1 for NULL): -1
Enter right child of 15
Enter data (-1 for NULL): -1

Preorder Traversal:
10 5 3 7 15 
Inorder Traversal:
3 5 7 10 15 
Postorder Traversal:
3 7 5 15 10 
Process finished with exit code 0


print("\nInorder Traversal:")
inorder(root)

print("\nPostorder Traversal:")
postorder(root)
