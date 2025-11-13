package omar;

import omar.BST;

public class Main {
    public static void main(String[] args) {
        BST dictionary = new BST();
        
        // Test insertWordNode() method
        dictionary.insertWordNode("tennis");
        dictionary.insertWordNode("soccer");
        dictionary.insertWordNode("lacrosse");
        dictionary.insertWordNode(" quidage");
        dictionary.insertWordNode("softball");
        dictionary.insertWordNode("baseball");
        dictionary.insertWordNode("frisbie");
        
        // Assertions for insertion
        assert dictionary.root != null : "Root should not be null";
        assert dictionary.root.word.equals("tennis") : "Root should be 'tennis'";
        assert dictionary.root.left != null : "Left child should exist";
        assert dictionary.root.left.word.equals("soccer") : "Left child should be 'soccer'";
        assert dictionary.root.right != null : "Right child should exist";
        assert dictionary.root.right.word.equals("lacrosse") : "Right child should be 'lacrosse'";
        
        // Assertions for search
        assert dictionary.search(dictionary.root, "tennis") == true : "Should find 'tennis'";
        assert dictionary.search(dictionary.root, "soccer") == true : "Should find 'soccer'";
        assert dictionary.search(dictionary.root, "lacrosse") == true : "Should find 'lacrosse'";
        assert dictionary.search(dictionary.root, "softball") == true : "Should find 'softball'";
        assert dictionary.search(dictionary.root, "wrestling") == false : "Should not find 'wrestling'";
        
        // Assertions for spell check
        assert dictionary.spellCheck("softball") == true : "Spell check should find 'softball'";
        assert dictionary.spellCheck("wrestling") == false : "Spell check should not find 'wrestling'";
        
        // Assertions for BST property
        assert dictionary.root.left.word.compareTo(dictionary.root.word) < 0 : 
            "Left child should be less than parent";
        assert dictionary.root.right.word.compareTo(dictionary.root.word) > 0 : 
            "Right child should be greater than parent";
        
        // Test checkWord() method - deletion scenarios
        
        // Scenario (a): Try to delete a node that doesn't exist
        boolean result1 = dictionary.checkWord("wrestling");
        assert result1 == false : "Should return false when deleting non-existent word";
        
        // Scenario (b): Delete a node with no children
        boolean result2 = dictionary.checkWord("baseball");
        assert result2 == true : "Should successfully delete 'baseball'";
        assert dictionary.search(dictionary.root, "baseball") == false : "Should not find deleted 'baseball'";
        
        // Scenario (c): Delete a node with one child
        assert dictionary.search(dictionary.root, " quidage") == true : "Should find ' quidage' before deletion";
        boolean result3 = dictionary.checkWord(" quidage");
        assert result3 == true : "Should successfully delete ' quidage'";
        assert dictionary.search(dictionary.root, " quidage") == false : "Should not find deleted ' quidage'";
        
        // Scenario (d): Delete a node with two children
        assert dictionary.search(dictionary.root, "lacrosse") == true : "Should find 'lacrosse' before deletion";
        boolean result4 = dictionary.checkWord("lacrosse");
        assert result4 == true : "Should successfully delete 'lacrosse'";
        assert dictionary.search(dictionary.root, "lacrosse") == false : "Should not find deleted 'lacrosse'";
        
        // Verify tree structure is still valid
        assert dictionary.root != null : "Root should still exist";
        
        System.out.println("Successful");
    }
}







