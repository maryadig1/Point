package omar;

public class BST {
    // Class Attribute
    public Node root;
    
    public BST() {
        root = null;
    }
    
    // Public method to insert a value into the binary search tree
    public void insertWordNode(String word) {
        if (root == null) {
            root = new Node(word);
        } else {
            insertWordNode(root, word);
        }
    }
    
    // Helper method for insertion
    private void insertWordNode(Node myRoot, String word) {
        int comparison = word.compareTo(myRoot.word);
        
        if (comparison == 0) {
            return; // Word already exists
        }
        if (comparison < 0) {
            if (myRoot.left == null) {
                myRoot.left = new Node(word);
            } else {
                insertWordNode(myRoot.left, word);
            }
        } else {
            if (myRoot.right == null) {
                myRoot.right = new Node(word);
            } else {
                insertWordNode(myRoot.right, word);
            }
        }
    }
    
    // Public method that removes a specified node from the Binary Search Tree
    public boolean checkWord(String word) {
        if (root == null) {
            return false; // Scenario (a): Node not in tree
        }
        
        if (root.word.equals(word)) {
            root = deleteNode(root);
            return true;
        }
        
        Node parent = findParent(root, word);
        if (parent == null) {
            return false; // Scenario (a): Node not found
        }
        
        if (parent.left != null && parent.left.word.equals(word)) {
            parent.left = deleteNode(parent.left);
        } else if (parent.right != null && parent.right.word.equals(word)) {
            parent.right = deleteNode(parent.right);
        }
        
        return true;
    }
    
    // Helper method to find parent of a node
    private Node findParent(Node myRoot, String word) {
        if (myRoot == null) {
            return null;
        }
        
        if (myRoot.left != null && myRoot.left.word.equals(word)) {
            return myRoot;
        }
        if (myRoot.right != null && myRoot.right.word.equals(word)) {
            return myRoot;
        }
        
        int comparison = word.compareTo(myRoot.word);
        if (comparison < 0) {
            return findParent(myRoot.left, word);
        } else {
            return findParent(myRoot.right, word);
        }
    }
    
    // Helper method to delete a node (handles all scenarios)
    private Node deleteNode(Node node) {
        // Scenario (b): Node has no children
        if (node.left == null && node.right == null) {
            return null;
        }
        
        // Scenario (c): Node has one child
        if (node.left == null) {
            return node.right;
        }
        if (node.right == null) {
            return node.left;
        }
        
        // Scenario (d): Node has two children
        Node successor = findMin(node.right);
        node.word = successor.word;
        node.right = deleteNodeRecursive(node.right, successor.word);
        return node;
    }
    
    // Helper method to find minimum node
    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    
    // Helper method to recursively delete
    private Node deleteNodeRecursive(Node myRoot, String word) {
        if (myRoot == null) {
            return null;
        }
        
        int comparison = word.compareTo(myRoot.word);
        if (comparison < 0) {
            myRoot.left = deleteNodeRecursive(myRoot.left, word);
        } else if (comparison > 0) {
            myRoot.right = deleteNodeRecursive(myRoot.right, word);
        } else {
            if (myRoot.left == null) {
                return myRoot.right;
            }
            if (myRoot.right == null) {
                return myRoot.left;
            }
            Node successor = findMin(myRoot.right);
            myRoot.word = successor.word;
            myRoot.right = deleteNodeRecursive(myRoot.right, successor.word);
        }
        return myRoot;
    }
    
    // Helper method to search for a word
    public boolean search(Node myRoot, String word) {
        if (myRoot == null) {
            return false;
        }
        int comparisonResult = word.compareTo(myRoot.word);
        if (comparisonResult == 0) {
            return true;
        } else if (comparisonResult < 0) {
            return search(myRoot.left, word);
        } else {
            return search(myRoot.right, word);
        }
    }
    
    // Public method to check if word exists
    public boolean spellCheck(String word) {
        return search(root, word);
    }
    
    // In-order traversal
    public void inOrder() {
        inOrderRecursive(root);
    }
    
    private void inOrderRecursive(Node myRoot) {
        if (myRoot != null) {
            inOrderRecursive(myRoot.left);
            System.out.println(myRoot.word);
            inOrderRecursive(myRoot.right);
        }
    }
    
    // Node class
    public static class Node {
        public String word;
        public Node left;
        public Node right;
        
        public Node(String word) {
            this.word = word;
            left = null;
            right = null;
        }
    }
}
