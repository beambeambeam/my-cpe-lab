public class AVL extends BinTree {
  public AVL() {
    super();
  }

  @Override
  public void insertNode(int node) {
    root = insertNode(root, node);
  }

  private BinNode insertNode(BinNode current, int node) {
    if (current == null) {
      return new BinNode(node);
    }

    if (node < current.node) {
      current.leftChild = insertNode(current.leftChild, node);
    } else if (node > current.node) {
      current.rightChild = insertNode(current.rightChild, node);
    } else {
      return current;
    }

    return balance(current);
  }

  private int height(BinNode node) {
    if (node == null) {
      return 0;
    }
    return Math.max(height(node.leftChild), height(node.rightChild)) + 1;
  }

  private int getBalanceFactor(BinNode node) {
    if (node == null) {
      return 0;
    }
    return height(node.leftChild) - height(node.rightChild);
  }

  private BinNode balance(BinNode node) {
    int balanceFactor = getBalanceFactor(node);

    if (balanceFactor > 1) {
      if (getBalanceFactor(node.leftChild) < 0) {
        node.leftChild = rotateLeft(node.leftChild);
      }
      return rotateRight(node);
    }

    if (balanceFactor < -1) {
      if (getBalanceFactor(node.rightChild) > 0) {
        node.rightChild = rotateRight(node.rightChild);
      }
      return rotateLeft(node);
    }

    return node;
  }

  private BinNode rotateRight(BinNode y) {
    BinNode x = y.leftChild;
    BinNode T2 = x.rightChild;

    x.rightChild = y;
    y.leftChild = T2;

    return x;
  }

  private BinNode rotateLeft(BinNode x) {
    BinNode y = x.rightChild;
    BinNode T2 = y.leftChild;

    y.leftChild = x;
    x.rightChild = T2;

    return y;
  }
}
