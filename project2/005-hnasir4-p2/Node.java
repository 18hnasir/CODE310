
/**
 * Class used to represent a node for linked list
 * implementation. 
 * 
 * @author Professor
 *
 * @param <T> for generics
 */
class Node<T> {
	
	/**
	 * Variable used to hold node value.
	 */
	private T value;
	
	/**
	 * Variable used to hold next node.
	 */
	private Node<T> next;
	
	/**
	 * Variable used to hold previous node. 
	 */
	private Node<T> prev;
	
	/**
	 * Basic constructor for the class that
	 * assigns node value. 
	 * 
	 * @param value to be assigned
	 */
	public Node(T value) {
		this.value = value;
	}
	
	/**
	 * Method used to return node value.
	 * 
	 * @return value
	 */
	public T getValue() {
		return value;
	}
	
	/**
	 * Method used to set Node value variable. 
	 * 
	 * @param value to be set
	 */
	public void setValue(T value) {
		this.value = value;
	}
	
	/**
	 * Method used to get next node. 
	 * 
	 * @return node
	 */
	public Node<T> getNext() {
		return this.next;
	}
	
	/**
	 * Method used to set the next node.
	 * 
	 * @param next node
	 */
	public void setNext(Node<T> next) {
		this.next = next;
	}
	
	/**
	 * Method used to get the previous
	 * node.
	 * 
	 * @return node
	 */
	public Node<T> getPrev() {
		return this.prev;
	}
	
	/**
	 * Method used to set previous node 
	 * value. 
	 * 
	 * @param prev node
	 */
	public void setPrev(Node<T> prev) {
		this.prev = prev;
	}
	
	/**
	 * Method used to list the stack as string.
	 * 
	 * @param head to go through
	 * @return string
	 */
	public static String listToString(Node<?> head) {
		StringBuilder ret = new StringBuilder();
		Node<?> current = head;
		while(current != null) {
			ret.append(current.value);
			ret.append(" ");
			current = current.getNext();
		}
		return ret.toString().trim();
	}
	
	/**
	 * Method used to list the string backwards.
	 * 
	 * @param head to go through
	 * @return String
	 */
	public static String listToStringBackward(Node<?> head) {
		Node<?> current = head;
		while(current.getNext() != null) {
			current = current.getNext();
		}
		
		StringBuilder ret = new StringBuilder();
		while(current != null) {
			ret.append(current.value);
			ret.append(" ");
			current = current.getPrev();
		}
		return ret.toString().trim();
	}
	
	/**
	 * Main method used for testing of the class.
	 * 
	 * @param args to be used
	 */
	public static void main(String[] args) {
		//main method for testing, edit as much as you want
		
		//make nodes
		Node<String> n1 = new Node<>("A");
		Node<String> n2 = new Node<>("B");
		Node<String> n3 = new Node<>("C");
		
		//connect forward references
		n1.setNext(n2);
		n2.setNext(n3);
		
		//connect backward references
		n3.setPrev(n2);
		n2.setPrev(n1);
		
		//print forward and backward
		System.out.println(Node.listToString(n1));
		System.out.println(Node.listToStringBackward(n1));
	}
}