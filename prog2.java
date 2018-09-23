// Michael Merabi  -  ID# 109481201
// CS282 - Tuesday/Thursday Class
// Assignment #2   -  Assignment turned in on: 9/___/18

/* This is a project that contains an AVL tree and can traverse the tree in many different ways
 * including finding height, balancing and inserting nodes.  */



//Getters and setters, constructors
class StringAVLNode {
	private String item;
	private int balance;
	private StringAVLNode left, right; 
 
	public StringAVLNode(String str) {
		this.item=str;
		this.left=null;
		this.right=null;
	 }	

	public int getBalance() {
		return balance;
	}

	public void setBalance(int bal) {
	 this.balance=bal;
	}

	public String getItem() {
		return item;
	}

	public StringAVLNode getLeft() {
		return left;
	}

	public void setLeft(StringAVLNode pt) {
	 this.left=pt;
	}
	
	public StringAVLNode getRight() {
		return left; 
	}

	public void setRight(StringAVLNode pt) {
		this.right=pt;
	}
}   //End of constructor class

//===================================================================


// StringAVLNode
class StringAVLTree {		
	StringAVLNode root;
 
	public StringAVLTree() {
		this.root=null;
	}

 // Rotate the node to the right
	private static StringAVLNode rotateRight(StringAVLNode t) {
		return t; 
	}
 

 // Rotate the node to the left
	private static StringAVLNode rotateLeft(StringAVLNode t) {
		return t;
	}
 
// For these next four, be sure not to use any global variables
 // Return the height of the tree – not to be used anywhere in insert or delete
	public int height() {
		return 0; 
	}
 
 // Return the number of leaves in the tree
	public int leafCt() {
		return 0;	 
	}
 
 // Return the number of perfectly balanced AVL nodes
	public int balanced() {
		return 0;	 
	}
 
 // Return the inorder successor or null if there is none or str is not in the tree
	public String successor(String str) {
		return str; 
	}
 
	public void insert(String str) {
	 
	}
	
	private StringAVLNode insert(String str, StringAVLNode t) {
		return t; 
	}
 
 
	 public static String myName() {
		 return "Michael Merabi";
 		}
}