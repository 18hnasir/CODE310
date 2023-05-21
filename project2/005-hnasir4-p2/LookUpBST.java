/**
 * Class used to represent a binary search tree.
 * 
 * @author Hammadullah Nasir
 *
 * @param <K> for generics
 * @param <V> for generics
 */
class LookUpBST<K extends Comparable<K>, V> {

	/**
	 * Variable used to be root of BST.
	 */
	public Node<K, V> root;

	/**
	 * Variable that is the size of the tree, or number nodes in a BST.
	 */
	public int size;

	/**
	 * Method used to return the size of a BST.
	 * 
	 * @return size
	 */
	public int size() {
		return size;
	}

	/**
	 * Provided BST class.
	 * 
	 * @author Professor
	 * 
	 *
	 * @param <K> generics
	 * @param <V> generics
	 */
	public static class Node<K, V> {
		/**
		 * Variable used to represent key.
		 */
		K key;

		/**
		 * Variable used to represent value.
		 */
		V val;

		/**
		 * Left and right node used to in the BST.
		 */
		Node<K, V> left, right;

		/**
		 * Basic constructor for the BST node.
		 * 
		 * @param key to be set
		 * @param val to be set
		 */
		public Node(K key, V val) {
			this.key = key;
			this.val = val;
		}

		/**
		 * Another constructor that also assigns the left and right node.
		 * 
		 * @param key to be set
		 * @param val to be set
		 * @param l   to be set
		 * @param r   to be set
		 */
		public Node(K key, V val, Node<K, V> l, Node<K, V> r) {
			this.key = key;
			this.val = val;
			this.left = l;
			this.right = r;
		}
	}

	/**
	 * Method used to see if a BST contains a key.
	 * 
	 * @param key that needs to be found
	 * @return boolean
	 */
	public boolean contains(K key) {

		Node<K, V> temp = root;
		if (root == null || key == null) {
			return false;
		}
		if (temp.key.compareTo(key) == 0) { // if its the root
			return true;
		}
		while (temp != null) {
			if (temp.key.compareTo(key) == 0) {
				return true;
			} else if (temp.key.compareTo(key) < 0) { // if root is smaller then we go to the right
				temp = temp.right;
			} else {
				temp = temp.left; // or we go to the left
			}
		}

		return false;
	}

	/**
	 * Method used to get the value of the key.
	 * 
	 * @param key that needs to be found
	 * @return value of the key
	 */
	public V get(K key) {

		Node<K, V> temp = root;
		if (root == null || key == null) {
			return null;
		}
		if (contains(key) == true) {
			if (temp.key.compareTo(key) == 0) { // if its the root
				return temp.val;
			}
			while (temp != null) {
				if (temp.key.compareTo(key) == 0) {
					return temp.val;
				} else if (temp.key.compareTo(key) < 0) { // if root is smaller then we go to the right
					temp = temp.right;
				} else {
					temp = temp.left; // or we go to the left
				}
			}
		}

		return null;
	}

	/**
	 * Method used to put the key and value in the BST.
	 * 
	 * @param key that needs to be placed
	 * @param val that needs to be placed
	 * @return boolean
	 */
	public boolean put(K key, V val) {

		boolean set = false;
		Node<K, V> newNode = new Node<>(key, val);
		Node<K, V> temp = root; // current node
		Node<K, V> temp2 = null; // used to update the root

		if (key == null) {
			return false;
		} else if (root == null) {
			root = newNode;
			set = true;
		} else {
			while (set == false) {
				temp2 = temp;
				if (key.compareTo(temp.key) == 0) {
					temp.val = val;
					set = true;
				}
				if (key.compareTo(temp.key) < 0) {
					temp = temp.left;
					if (temp == null) {
						temp2.left = newNode;
						set = true;
					}
				} else {
					temp = temp.right;
					if (temp == null) {
						temp2.right = newNode;
						set = true;
					}
				}
			}
		}

		size += 1;
		return set;
	}

	/**
	 * Method used to find the biggest key in the BST.
	 * 
	 * @param <K> key for the node
	 * @param <V> value of the node
	 * @param t   Node that needs to be checked
	 * @return key pair
	 */
	public static <K, V> K findBiggestKey(Node<K, V> t) {

		Node<K, V> temp = t;

		if (t == null) {
			return null;
		} else {
			while (t != null) {
				if (t.right == null) {
					break;
				}
				t = t.right;
			}
			return t.key;
		}

	}

	/**
	 * Method used to find the height of a BST. Calls a recursive helper method for
	 * simplicity.
	 * 
	 * @return int
	 */
	public int height() {

		int returnValue = 0;

		if (root == null) {
			return -1;
		}

		returnValue = heightHelper(root);

		return returnValue - 1;
	}

	/**
	 * Height helper recursive method, check height for left and right side of the
	 * BST.
	 * 
	 * @param r root of the BST
	 * @return int
	 */
	private int heightHelper(Node<K, V> r) {
		int treeHeightLeft = 0;
		int treeHeightRight = 0;
		int returnValue = 1; // 0 because the root goes with the height

		if (r.left != null) {
			treeHeightLeft = heightHelper(r.left);
		}
		if (r.right != null) {
			treeHeightRight = heightHelper(r.right);
		}

		if (treeHeightLeft < treeHeightRight) {
			return returnValue += treeHeightRight;
		} else {
			return returnValue += treeHeightLeft;
		}

	}

	/**
	 * Method used to find the number of leaves in a BST. Calls a helper recursive
	 * method inside.
	 * 
	 * @return int
	 */
	public int numLeaves() {
		
		int total = 0;
		if (root == null) {
			return 0;
		}

		total = numLeavesHelper(root);

		return total;
	}

	/**
	 * Helper method that is used to find the number of leaves in a BST, it is used
	 * recursively to find the leaves of both sides of a BST.
	 * 
	 * @param r to be used
	 * @return total
	 */
	public int numLeavesHelper(Node<K, V> r) {
		int leavesLeft = 0;
		int leavesRight = 0;

		if (r.left == null && r.right == null) {
			return 1;
		} else {
			if (r.left != null) {
				leavesLeft = numLeavesHelper(r.left);
			}
			if (r.right != null) {
				leavesRight = numLeavesHelper(r.right);
			}
			return leavesLeft + leavesRight;
		}

	}

	/**
	 * Method used to represent the BST as a string, calls a helper string method to
	 * show the BST in IN-ORDER or LEVEL-ORDER representation.
	 * 
	 * @return string
	 */
	public String toString() {
		return toStringInOrder(this.root);
	}

	/**
	 * Method used to represent the BST in an inorder string representation.
	 * 
	 * @param t root
	 * @return string
	 */
	private String toStringInOrder(Node t) {
		// Follow IN-ORDER traversal to include data of all nodes.
		// Example 1: a single-node tree with the root data as "a:112":
		// toString() should return a:112
		//
		// Example 2: a tree with four nodes:
		// d:310
		// / \
		// a:112 p:367
		// /
		// f:330
		// toStringInOrder() should return a:112 d:310 f: 330 p:367

		if (t == null)
			return "";
		StringBuilder sb = new StringBuilder();
		sb.append(toStringInOrder(t.left));
		sb.append(t.key);
		sb.append(":");
		sb.append(t.val);
		sb.append(" ");
		sb.append(toStringInOrder(t.right));
		return sb.toString();
	}

	/**
	 * Method used to represent the BST in a level order representation.
	 * 
	 * @param t root
	 * @return string
	 */
	private String toStringLevelOrder(Node<K, V> t) {
		// Follow LEVEL-ORDER traversal to include data of all nodes.
		// Example: a tree with four nodes:
		// d:310
		// / \
		// a:112 p:367
		// /
		// f:330
		// toStringLevel() should return
		// d:310
		// a:112 p:367
		// null null f:330 null

		StringBuilder sb = new StringBuilder();
		int capacity = (int) Math.pow(2, size() + 1) - 1;
		Node<?, ?>[] list = new Node<?, ?>[capacity];
		list[0] = root;

		int count = 0;
		int level = 0;
		for (int i = 0; i < capacity; i++) {
			if (i == Math.pow(2, level + 1) - 1) {
				if (count == size)
					break;
				level++;
				sb.append("\n");
			}
			if (list[i] == null) {
				sb.append("null ");
			} else {
				count++;
				sb.append(list[i].key);
				sb.append(":");
				sb.append(list[i].val);
				sb.append(" ");
				if ((i * 2) + 1 < list.length) {
					list[(i * 2) + 1] = list[i].left;
					list[(i * 2) + 2] = list[i].right;
				}
			}
		}

		return sb.toString();
	}

}
