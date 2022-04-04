package finalProject;

//helped with TST.java from sedgwick and wayne textbook

public class ternarySearch {
private int size;
private Node root;
private static class Node<Value> {
    private char c;                        // character
    private Node<Value> left, mid, right;  // left, middle, and right subtries
    private Value val;                     // value associated with string
}
/**
 * Initializes an empty string symbol table.
 */
public ternarySearch() {
}
/**
 * Returns the number of key-value pairs in this symbol table.
 * @return the number of key-value pairs in this symbol table
 */
public int size() {
    return n;
}

public ternarySearch(file){
	fileToTst(file);
}
private void fileToTst(String file) {
	int count=0;
	File file=new File(file);
	Scanner s=new Scanner(file);
	
}
/**
 * Returns the value associated with the given key.
 * @param key the key
 * @return the value associated with the given key if the key is in the symbol table
 *     and {@code null} if the key is not in the symbol table
 * @throws IllegalArgumentException if {@code key} is {@code null}
 */
public Value get(String key) {
    if (key == null) {
        throw new IllegalArgumentException("calls get() with null argument");
    }
    if (key.length() == 0) throw new IllegalArgumentException("key must have length >= 1");
    Node<Value> x = get(root, key, 0);
    if (x == null) return null;
    return x.val;
}

// return subtrie corresponding to given key
private Node<Value> get(Node<Value> x, String key, int d) {
    if (x == null) return null;
    if (key.length() == 0) throw new IllegalArgumentException("key must have length >= 1");
    char c = key.charAt(d);
    if      (c < x.c)              return get(x.left,  key, d);
    else if (c > x.c)              return get(x.right, key, d);
    else if (d < key.length() - 1) return get(x.mid,   key, d+1);
    else                           return x;
}

}
