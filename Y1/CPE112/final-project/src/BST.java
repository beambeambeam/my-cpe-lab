public class BST extends BinTree {
  public BST() {
    super();
  }

  public void insertNode(int node) {
    this.root = insertRecursively(this.root, node);
  }

  private BinNode insertRecursively(BinNode current, int node) {
    if (current == null) {
      return new BinNode(node);
    }

    if (node < current.node) {
      current.leftChild = insertRecursively(current.leftChild, node);
    } else if (node > current.node) {
      current.rightChild = insertRecursively(current.rightChild, node);
    }

    return current;
  }
}
