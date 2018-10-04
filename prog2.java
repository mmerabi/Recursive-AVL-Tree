//Michael Merabi  -  ID# 109481201
//CS282 - Tuesday/Thursday Class
//Assignment #2   -  Assignment turned in on: 10/___/18

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
	
	public int height() { // Calculate height through the root recursively
	//per professor Wiegly's recommendation
		
		int lh = 0; // left height
		int rh = 0; //right height
		if (right!=null)
			rh = right.height();
			rh = rh + 1;
		int max = rh;
		
		if (left!=null)
			lh = left.height();
			lh = lh+1;
		if (lh > max)
			max = lh;
		return max;
	}
}   //End of constructor class

//===================================================================


//StringAVLNode
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

	//--------DONE ------------ MUST BE TESTED -----------
	public int height() {
		if (root == null)
			return 0;
		else
			return root.height();
	}
		
// ------------DONE ----------------MUST BE TESTED ---------
	public int leafCt() { // public method calls private recursive leafRecursive
		StringAVLNode leafCt = root;
		return leafRecursive(leafCt);
			}
			 
	 private int leafRecursive(StringAVLNode leaf) { //Called by leafCt to recursively count amount of leaves in tree
		 int recursiveCt;
		 if (leaf == null) {
			 recursiveCt = 0;
			    }
		 if(leaf.getLeft() == null && leaf.getRight() == null){// ask here for ++ or = 1? is it adding correctly?
			recursiveCt = 1;
		 	} else {
		 	recursiveCt = leafRecursive(leaf.getLeft()) + leafRecursive(leaf.getRight());
		 			}
		 	return recursiveCt;	
	 	}

// // ------------DONE ----------------MUST BE TESTED ---------
	public int balanced() { // return number of balanced AVL nodes by calling recursive balance
		int balanceHolder;
		   balanceHolder = balancedRecursive(root);
		   return balanceHolder;
		  }
		 
	private int balancedRecursive(StringAVLNode t) {
		 int balCount;
		 if(t == null){
		   balCount = 0;
		   }
		 else if (t.getBalance() == 0) {   	
			 balCount = balancedRecursive(t.getLeft()) + balancedRecursive(t.getRight()) + 1;
		     } else {
		     balCount = balancedRecursive(t.getLeft()) + balancedRecursive(t.getRight());
		     		}
		 return balCount;
	 }

// Return the in order successor or null if there is none or str is not in the tree
	public String successor(String str) {
		return str; 
	}

	public void insert(String str) {
	 root = insert(str, root);
	}
	
	private StringAVLNode insert(String str, StringAVLNode t) {
		if (t == null) { // if root is null, this is the first node
				t = new StringAVLNode(str);
				System.out.println("First node in tree added \n");
				
			} else if (t.getItem() == str) {
				System.out.println("This node is already in the tree \n");
				return t; //t is already in the tree
				
			} else if (t.getItem().compareTo(str) < 0) {
				t.setLeft(insert(str,t.getLeft()));
				
			} else {
				t.setRight(insert(str,t.getRight()));
				   }
		
		//must add rotate methods here
		return t; 
	}

	 public static String myName() {
		 return "Michael Merabi";
		}

	 //Main method starts here ===========
	 public static void main(String args[]){
		 System.out.printf("Hello World \n");
	 }
}