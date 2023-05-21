import java.util.Iterator;

/**
 * Class used to be a stack for the other classes.
 * 
 * @author Hammadullah Nasir
 *
 * @param <T> for generics
 */
class CallStack<T> implements Iterable<T> {

	/**
	 * Linked list node used to represent a stack.
	 */
	Node<T> stack;

	/**
	 * Variable used to keep up how many items are in the stack.
	 */
	int numItems = 0;

	/**
	 * Basic constructor for the class.
	 */
	public CallStack() {
		// setup what you need
	}

	/**
	 * Method used to push an item on the stack.
	 * 
	 * @param item that is used to be on the stack
	 */
	public void push(T item) {

		Node<T> temp = new Node<>(item);
		if (stack == null) {
			stack = temp;
			numItems += 1;
		} else {
			temp.setNext(stack);
			stack.setPrev(temp);
			stack = temp;
			numItems += 1;
		}

	}

	/**
	 * Method used to pop an item off the stack.
	 * 
	 * @return T
	 */
	public T pop() {

		if (stack == null) {
			return null;
		}
		Node<T> temp = stack;
		numItems -= 1;
		T returnValue = temp.getValue();

		stack = stack.getNext();
		if (numItems != 0) {
			stack.setPrev(null);
		}

		return returnValue;
	}

	/**
	 * Method used to see what the top stack value is.
	 * 
	 * @return T
	 */
	public T peek() {

		if (stack == null) {
			return null;
		}

		return stack.getValue();
	}

	/**
	 * Method used to turn the stack into a string representation.
	 * 
	 * @return String
	 */
	public String toString() {

		if (numItems == 0) {
			return "";
		}

		String returnValue = Node.listToStringBackward(stack);

		return returnValue;
	}

	/**
	 * Method used to clear that stack.
	 */
	public void clear() {

		stack = null;
		numItems = 0;
	}

	/**
	 * Method used to return the number of elements in the stack.
	 * 
	 * @return numItems
	 */
	public int size() {

		return numItems;
	}

	/**
	 * Method used to check if the stack is empty.
	 * 
	 * @return boolean
	 */
	public boolean isEmpty() {

		if (stack == null) {
			return true;
		}

		return false;
	}

	/**
	 * Method used to return an array of the stack.
	 * 
	 * @return array
	 */
	@SuppressWarnings("unchecked")
	public Object[] toArray() {

		Node<T> temp = stack;
		T[] returnArray = (T[]) new Object[numItems];
		int i = 0;

		while (temp != null) {
			returnArray[i] = temp.getValue();
			temp = temp.getNext();
			i += 1;
		}
		return returnArray;
	}

	/**
	 * Method used to make an interator that can traverse through the stack.
	 * 
	 * @return iterator
	 */
	public Iterator<T> iterator() {

		/**
		 * Inner class used to implement an iterator for the call stack.
		 * 
		 * @author Hammadullah Nasir
		 *
		 */
		class ReturnIterator implements Iterator<T> {

			/**
			 * Variable used to represent the current node.
			 */
			private Node<T> currentNode;

			/**
			 * Constructor for the class.
			 * 
			 * @param c to be used
			 */
			public ReturnIterator(CallStack<T> c) {
				currentNode = c.stack;
			}

			/**
			 * Method used to see if their is a next.
			 * 
			 * @return boolean
			 */
			@Override
			public boolean hasNext() {
				return currentNode != null;
			}

			/**
			 * Method used to get the next value.
			 * 
			 * @return value
			 */
			@Override
			public T next() {
				if (!hasNext()) {
					throw new NullPointerException("Next value is null!");
				}

				T returnValue = currentNode.getValue();
				currentNode = currentNode.getNext();
				return returnValue;
			}
		}

		return new ReturnIterator(this);
	}

}