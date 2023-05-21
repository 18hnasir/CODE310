/**
 * Class used to represent a separate chaining hashtable.
 * 
 * @author Hammadullah Nasir
 *
 * @param <K> for generics
 * @param <V> for generics
 */
class ThreeTenHashTable2<K, V> {

	/**
	 * Used as storage of data for the hashtable.
	 */
	private Node<K, V>[] storage;

	/**
	 * The number of slots in the hasttable.
	 */
	private int slots;
	/**
	 * The number of elements in the table.
	 */
	private int elements = 0;

	/**
	 * Constructor for the hashtable that takes a size.
	 * 
	 * @param size for the hashtable
	 */
	@SuppressWarnings("unchecked")
	public ThreeTenHashTable2(int size) {
		slots = size;
		storage = (Node<K, V>[]) new Node[size];
	}

	/**
	 * Method used to get the slots of the hashtable.
	 * 
	 * @return slots
	 */
	public int getCapacity() {
		return slots;
	}

	/**
	 * Method used to return the total elements in the table.
	 * 
	 * @return elements
	 */
	public int size() {
		return elements;
	}

	/**
	 * Method used to place the key and value in the hashtable.
	 * 
	 * @param k the key
	 * @param v the value
	 */
	public void put(K k, V v) {
		TableEntry<K, V> temp = new TableEntry<>(k, v);
		int position = k.hashCode() % slots;
		Node<K, V> temp2;
		boolean replaced = false;

		if (position < 0) {
			position *= -1;
		}

		if (storage[position] == null) {
			storage[position] = new Node<>(temp, null);
			elements += 1;
		} else {
			temp2 = storage[position];
			if (temp2.entry.getKey().equals(k)) {
				temp2.entry = temp;
			} else {
				while (temp2.next != null) {
					if (temp2.entry.getKey().equals(k)) {
						temp2.entry = temp;
						replaced = true;
						break;
					}
					temp2 = temp2.next;
				}
				if (replaced == false) {
					temp2.next = new Node<>(temp, null);
					elements += 1;
				}
			}
		}

		if (slots == 1 && elements == 1) {

		} else if ((elements * 1.0 / slots) >= 0.8) {
			rehash(slots * 2);
			while ((elements * 1.0 /slots) >= 0.8) {
				rehash(slots * 2);
			}
		}

	}

	/**
	 * Method used to remove the key and value from the hashtable and place a
	 * tombstone.
	 * 
	 * @param k the key
	 * @return true if removed
	 */
	public V remove(K k) {
		V returnValue = null;
		int position = k.hashCode() % slots;
		Node<K, V> temp;
		Node<K, V> temp2;

		if (position < 0) {
			position *= -1;
		}

		temp = storage[position];

		if (temp == null) {
			return returnValue;
		}

		if (temp.entry.getKey().equals(k)) {
			returnValue = temp.entry.getValue();
			storage[position] = temp.next;
		} else {
			temp2 = temp; // keep track of previous node
			while (temp != null) {
				if (temp.entry.getKey().equals(k)) {
					returnValue = temp.entry.getValue();
					temp2.next = temp.next;
					break;
				}
				temp2 = temp;
				temp = temp.next;
			}
		}

		elements -= 1;

		return returnValue;
	}

	/**
	 * Method used to get the value associated with the key.
	 * 
	 * @param k the key
	 * @return value
	 */
	public V get(K k) {
		int position = k.hashCode() % slots;
		Node<K, V> temp;

		if (position < 0) {
			position *= -1;
		}

		temp = storage[position];

		if (temp == null) {
			return null;
		}

		if (temp.entry.getKey().equals(k)) {
			return temp.entry.getValue();
		} else {
			while (temp != null) {
				if (temp.entry.getKey().equals(k)) {
					return temp.entry.getValue();
				}
				temp = temp.next;
			}
		}

		return null;
	}

	/**
	 * Method used to rehash the hashtable for more space and ease of placing new
	 * elements.
	 * 
	 * @param size of the new hashtable
	 * @return true if done
	 */
	@SuppressWarnings("unchecked")
	public boolean rehash(int size) {
		Node<K, V>[] newStorage = (Node<K, V>[]) new Node[size];
		Node<K, V>[] tempStorage = storage;
		Node<K, V> temp;
		int tempSlots = slots;
		int tempElements = elements;

		if (size < 1) {
			return false;
		}

		storage = newStorage;
		slots = size;

		for (int i = 0; i < tempSlots; i++) {
			elements = 0;
			temp = tempStorage[i];
			if (temp == null) {
				continue;
			}
			while (temp != null) {
				elements = 0;
				put(temp.entry.getKey(), temp.entry.getValue());
				temp = temp.next;
			}
		}

		elements = tempElements;

		return true;
	}

	/**
	 * Class used to represent a node in the hashtable.
	 * 
	 * @author The Professor
	 *
	 * @param <K> for generics
	 * @param <V> for generics
	 */
	public static class Node<K, V> {
		/**
		 * An element in the node.
		 */
		public TableEntry<K, V> entry;
		/**
		 * Next node for linked list.
		 */
		public Node<K, V> next;

		/**
		 * Basic constructor for the node.
		 * 
		 * @param entry or table entry element
		 */
		public Node(TableEntry<K, V> entry) {
			this.entry = entry;
		}

		/**
		 * Constructor with entry and next parameters.
		 * 
		 * @param entry or table entry element
		 * @param next  node in the linked list
		 */
		public Node(TableEntry<K, V> entry, Node<K, V> next) {
			this(entry);
			this.next = next;
		}

		/**
		 * Method used to represent node as a string.
		 * 
		 * @return string
		 */
		public String toString() {
			return "[" + entry.toString() + "]->";
		}
	}

	/**
	 * Helper method used to debug.
	 * 
	 * @return string
	 */
	public String toString() {
		// THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < storage.length; i++) {
			Node<K, V> curr = storage[i];
			if (curr == null)
				continue;

			while (curr != null) {
				s.append(curr.entry.toString());
				s.append("\n");
				curr = curr.next;
			}
		}
		return s.toString().trim();
	}

	/**
	 * Helper method used to debug.
	 * 
	 * @return string
	 */
	public String toStringDebug() {
		// THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < storage.length; i++) {
			Node<K, V> curr = storage[i];

			s.append("[" + i + "]: ");
			while (curr != null) {
				s.append(curr.toString());
				curr = curr.next;
			}
			s.append("null\n");
		}
		return s.toString().trim();
	}
}