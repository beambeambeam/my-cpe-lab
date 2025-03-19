class Node {
  int key, height;
  Node left, right;

  Node(int d) {
    key = d;
    height = 1;
  }
}

class AVLTree {
  Node root;

  int height(Node N) {
    if (N == null)
      return 0;
    return N.height;
  }

  int max(int a, int b) {
    return (a > b) ? a : b;
  }

  Node rightRotate(Node y) {
    Node x = y.left;
    Node T2 = x.right;

    x.right = y;
    y.left = T2;

    y.height = max(height(y.left), height(y.right)) + 1;
    x.height = max(height(x.left), height(x.right)) + 1;

    return x;
  }

  Node leftRotate(Node x) {
    Node y = x.right;
    Node T2 = y.left;

    y.left = x;
    x.right = T2;

    x.height = max(height(x.left), height(x.right)) + 1;
    y.height = max(height(y.left), height(y.right)) + 1;

    return y;
  }

  int getBalance(Node N) {
    if (N == null)
      return 0;
    return height(N.left) - height(N.right);
  }

  Node insert(Node node, int key) {
    // 1. Perform the normal BST insert
    if (node == null) {
      return new Node(key);
    }

    if (key < node.key) {
      node.left = insert(node.left, key);
    } else if (key > node.key) {
      node.right = insert(node.right, key);
    } else {
      // Duplicate keys are not allowed in the AVL tree
      return node;
    }

    // 2. Update the height of this ancestor node
    node.height = 1 + max(height(node.left), height(node.right));

    // 3. Get the balance factor of this ancestor node to check whether
    // this node became unbalanced
    int balance = getBalance(node);

    // If this node becomes unbalanced, then there are 4 cases

    // Left Left Case
    if (balance > 1 && key < node.left.key) {
      return rightRotate(node);
    }

    // Right Right Case
    if (balance < -1 && key > node.right.key) {
      return leftRotate(node);
    }

    // Left Right Case
    if (balance > 1 && key > node.left.key) {
      node.left = leftRotate(node.left);
      return rightRotate(node);
    }

    // Right Left Case
    if (balance < -1 && key < node.right.key) {
      node.right = rightRotate(node.right);
      return leftRotate(node);
    }

    // return the (unchanged) node pointer
    return node;
  }

  void preOrder(Node node) {
    if (node != null) {
      System.out.print(node.key + " ");
      preOrder(node.left);
      preOrder(node.right);
    }
  }

  void inOrder(Node node) {
    if (node != null) {
      inOrder(node.left);
      System.out.print(node.key + " ");
      inOrder(node.right);
    }
  }

  void postOrder(Node node) {
    if (node != null) {
      postOrder(node.left);
      postOrder(node.right);
      System.out.print(node.key + " ");
    }
  }

  public void inOrder() {
    inOrder(root);
  }

  public void postOrder() {
    postOrder(root);
  }

  public void insert(int key) {
    root = insert(root, key);
  }

  public void preOrder() {
    preOrder(root);
  }
}

public class Main {
  public static void main(String[] args) {
    AVLTree tree = new AVLTree();

    tree.insert(10);
    tree.insert(20);
    tree.insert(30);
    tree.insert(40);
    tree.insert(50);
    tree.insert(25);

    System.out.println("Preorder traversal of constructed tree is : ");
    tree.preOrder();
    System.out.println();

    System.out.println("Inorder traversal of constructed tree is : ");
    tree.inOrder();
    System.out.println();

    System.out.println("Postorder traversal of constructed tree is : ");
    tree.postOrder();
    System.out.println();
  }
}
