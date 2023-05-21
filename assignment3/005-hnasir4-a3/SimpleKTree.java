import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Class used to represent a simple k ary tree.
 * 
 * @author Hammadullah Nasir
 *
 * @param <E> for generics
 */
public class SimpleKTree<E> implements Collection<E> {

	/**
	 * Arraylist used to keep k ary tree data.
	 */
	protected ArrayList<E> tree = new ArrayList<>();
	/**
	 * Value use to keep the k value or max number of children.
	 */
	protected int children;

	/**
	 * Basic constructor that has k value for the tree.
	 * 
	 * @param k children
	 */
	public SimpleKTree(int k) {
		if (k < 2) {
			throw new InvalidKException();
		}
		children = k;
	}

	/**
	 * Constructor that created a k ary tree from the array passed in along with the
	 * k value.
	 * 
	 * @param arrayTree to be used
	 * @param k children
	 */
	public SimpleKTree(E[] arrayTree, int k) {
		if (k < 2) {
			throw new InvalidKException();
		}
		children = k;

		for (E item : arrayTree) {
			tree.add(item);
		}
	}

	/**
	 * Method used to return the height of the k ary tree.
	 * 
	 * @return height
	 */
	public int height() {
		if (tree.size() == 0) {
			return -1;
		}
		return (int) (Math.log10((children - 1) * size()) / Math.log10(children));
	}

	/**
	 * Method use to clear the k ary tree.
	 */
	public void clear() {
		tree.clear();
	}

	/**
	 * Method used to get the number of elements in the tree.
	 * 
	 * @return int
	 */
	public int size() {
		return tree.size();
	}

	/**
	 * Method used to see if the tree is empty.
	 * 
	 * @return boolean
	 */
	public boolean isEmpty() {
		return tree.isEmpty();
	}

	/**
	 * Method used to return the tree in level order.
	 * 
	 * @return string
	 */
	public String toString() {
		String returnString = "";

		for (E item : tree) {
			returnString += item + " ";
		}

		return returnString;
	}

	/**
	 * Method used to return preorder representation of the tree.
	 * 
	 * @return string
	 */
	public String toStringPreOrder() {
		String returnString = "";
		ArrayList<E> temp = new ArrayList<>();

		preOrderHelper(0, temp);

		for (E item : temp) {
			returnString += item + " ";
		}

		return returnString;
	}

	/**
	 * Method used as helper for preorder method.
	 * 
	 * @param index to be used
	 * @param list to be used
	 */
	private void preOrderHelper(int index, ArrayList<E> list) {
		int c = 1;
		if (index >= tree.size()) {
			return;
		}
		list.add(tree.get(index));
		while (c < children + 1) {
			preOrderHelper((children * index) + c, list);
			c += 1;
		}
	}

	/**
	 * Method used to return post order representation of the tree.
	 * 
	 * @return string
	 */
	public String toStringPostOrder() {
		String returnString = "";
		ArrayList<E> temp = new ArrayList<>();

		postOrderHelper(0, temp);

		for (E item : temp) {
			returnString += item + " ";
		}
		return returnString;
	}

	/**
	 * Method used to be a helper for post order method.
	 * 
	 * @param index to be used
	 * @param list to be used
	 */
	public void postOrderHelper(int index, ArrayList<E> list) {
		int c = 1;
		if (index >= tree.size()) {
			return;
		}
		while (c < children + 1) {
			postOrderHelper((children * index) + c, list);
			c += 1;
		}

		list.add(tree.get(index));
	}

	/**
	 * Method that is optional for the project.
	 * 
	 * @return string
	 */
	public String toStringWithLevels() {
		// creates a string representation of the current tree with line breaks
		// after each level of the tree

		// Examples for the k=2 and k=3 trees from toString():

		// k=2:
		// 1
		// 2 3
		// 4 5 6 7
		// In string form, that is: "1 \n2 3 \n4 5 6 7 "
		// (a space after each element, a \n for each new level), the space at the end
		// is optional

		// k=3:
		// 1
		// 2 3 4
		// 5 6 7 8 9 10
		// In string form, that is: "1 \n2 3 4 \n5 6 7 8 9 10 "
		// (a space after each element, a \n for each new level), the space at the end
		// is optional

		// NOTE: Any values not in the heap are not printed (no printing nulls for
		// incomplete levels!)

		// HINT 1: Again, heaps are already in level order, so for this you just need to
		// determine
		// when you're at a new level and add a line break.

		// Hint 2: If you know how to get the height of a nearly complete tree of
		// a given size... you can find when items are on the next "level"
		// in the same way in O(1) time.
		return null;
	}

	/**
	 * Class used to represent a custom exception.
	 * 
	 * @author The Professor
	 *
	 */
	public static class InvalidKException extends RuntimeException {
	};

	/**
	 * Method not used.
	 * 
	 * @param e not used
	 * @return boolean
	 */
	public boolean add(E e) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Method not used.
	 * 
	 * @param c not used
	 * @return boolean
	 */
	public boolean addAll(Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Method not used.
	 * 
	 * @param o not used
	 * @return boolean
	 */
	public boolean contains(Object o) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Method not used.
	 * 
	 * @param c not used
	 * @return boolean
	 */
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Method not used.
	 * 
	 * @param o not used
	 * @return boolean
	 */
	public boolean equals(Object o) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Method not used.
	 * 
	 * @return int
	 */
	public int hashCode() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Method not used.
	 * 
	 * @return iterator
	 */
	public Iterator<E> iterator() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Method not used.
	 * 
	 * @param o not used
	 * @return boolean
	 */
	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Method not used.
	 * 
	 * @param c not used
	 * @return boolean
	 */
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Method not used.
	 * 
	 * @param c not used
	 * @return boolean
	 */
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Method not used.
	 * 
	 * @return object
	 */
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Method not used.
	 * 
	 * @param a not used
	 * @param <T> not used
	 * @return T
	 */
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}
}