import java.util.Queue;
import java.util.NoSuchElementException;
import java.util.Comparator;

/**
 * Class used to represent a priority queue.
 * 
 * @author Hammadullah Nasir
 *
 * @param <E> for generics
 */
public class PriorityTree<E> extends SimpleKTree<E> implements Queue<E> {

	/**
	 * Comparator to be used when adding into the queue.
	 */
	private Comparator<? super E> comp;

	/**
	 * Basic constructor for the class.
	 * 
	 * @param comp comparator
	 * @param k children
	 */
	public PriorityTree(Comparator<? super E> comp, int k) {
		// constructor which takes a comparator to be used later when adding/swapping
		// values in the queue and the k value of the k-ary tree

		// if you are unfamiliar with the Comparator interface, you can find it here:
		// see: http://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html
		// it's just a way of comparing objects using something other than their
		// "natural ordering"
		super(k);
		this.comp = comp;
	}

	/**
	 * Extra credit constructor.
	 * 
	 * @param comp comparator
	 * @param arrayTree to be heapified
	 * @param k children
	 */
	public PriorityTree(Comparator<? super E> comp, E[] arrayTree, int k) {
		//Takes an array representation of a tree and performs "heapify" to
		// create the queue
		// use the method which forms the heap from the bottom up (not the method that
		// repeatedly
		// adds elements). This may (or may not) be covered in all sections of the
		// class, so
		// it is not required, but encouraged for those interested.

		// DO NOT DELETE METHODS YOU DON'T COMPLETE.

		super(arrayTree, k);
		
	}

	/**
	 * Method used to add an item into the queue.
	 * 
	 * @param e item
	 * @return boolean
	 */
	public boolean add(E e) {
		int index;

		tree.add(e);
		index = tree.size() - 1;

		while (index > 0 && comp.compare(e, tree.get((index - 1) / children)) < 0) {
			tree.set(index, tree.get((index - 1) / children));
			index = (index - 1) / children;
			tree.set(index, e);
		}

		return true;
	}

	/**
	 * Method used to get the head of the queue.
	 * 
	 * @return element
	 */
	public E element() {
		if (tree.isEmpty()) {
			return null;
		}
		return tree.get(0);
	}

	/**
	 * Method used to add element into the queue if possible.
	 * 
	 * @param e element
	 * @return boolean
	 */
	public boolean offer(E e) {
		int index;

		tree.add(e);
		index = tree.size() - 1;

		while (index > 0 && comp.compare(e, tree.get((index - 1) / children)) < 0) {
			tree.set(index, tree.get((index - 1) / children));
			index = (index - 1) / children;
			tree.set(index, e);
		}

		return true;
	}

	/**
	 * Method used to see what the head of the queue is.
	 * 
	 * @return head
	 */
	public E peek() {
		if (tree.isEmpty()) {
			return null;
		}
		return tree.get(0);
	}

	/**
	 * Method used to remove and return the head of the queue.
	 * 
	 * @return head
	 */
	public E poll() {
		E returnValue = null;
		E tempValue = null;
		int highestValue;
		int index; 
		int c = 1;
		
		if (tree.isEmpty()) {
			return null;
		}
		else if (tree.size() == 1) {
			return tree.remove(0);
		}
		
		returnValue = peek();
		tree.set(0, tree.remove(tree.size() - 1));
		tempValue = tree.get(0);
		index = 0;
		
		while (index < tree.size()) {
			c = 1;
			highestValue = (children * index) + c;
			c += 1;
			while (c < children + 1) {
				if (highestValue >= tree.size() || (children * index) + c >= tree.size()) {
					break;
				}
				if (comp.compare(tree.get(highestValue), tree.get((children * index) + c)) > 0) {
					highestValue = (children * index) + c;
					c += 1;
				}
				else {
					c += 1;
				}
			}
			if (highestValue >= tree.size()) {
				break;
			}
			if (comp.compare(tempValue, tree.get(highestValue)) > 0) {
				tree.set(index, tree.get(highestValue));
				tree.set(highestValue, tempValue);
				index = highestValue;
			}
			else {
				break;
			}
		}
		
		return returnValue;
	}

	/**
	 * Method used to remove and return the head of the queue.
	 * 
	 * @return head
	 */
	public E remove() {
		E returnValue = null;
		E tempValue = null;
		int highestValue;
		int index; 
		int c = 1;
		
		if (tree.isEmpty()) {
			return null;
		}
		else if (tree.size() == 1) {
			return tree.remove(0);
		}
		
		returnValue = peek();
		tree.set(0, tree.remove(tree.size() - 1));
		tempValue = tree.get(0);
		index = 0;
		
		while (index < tree.size()) {
			c = 1;
			highestValue = (children * index) + c;
			c += 1;
			while (c < children + 1) {
				if (highestValue >= tree.size() || (children * index) + c >= tree.size()) {
					break;
				}
				if (comp.compare(tree.get(highestValue), tree.get((children * index) + c)) > 0) {
					highestValue = (children * index) + c;
					c += 1;
				}
				else {
					c += 1;
				}
			}
			if (highestValue >= tree.size()) {
				break;
			}
			if (comp.compare(tempValue, tree.get(highestValue)) > 0) {
				tree.set(index, tree.get(highestValue));
				tree.set(highestValue, tempValue);
				index = highestValue;
			}
			else {
				break;
			}
		}
		
		return returnValue;
	}
	
}