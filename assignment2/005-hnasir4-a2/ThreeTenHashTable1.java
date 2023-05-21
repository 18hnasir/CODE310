/**
 * Class used to represent a linear probing hashtable.
 * 
 * @author Hammadullah Nasir
 *
 * @param <K> for generics
 * @param <V> for generics
 */
class ThreeTenHashTable1<K, V> {

	/**
	 * Used as storage of data for the hashtable.
	 */
	private TableEntry<K, V>[] storage;
	/**
	 * The number of elements in the hashtable.
	 */
	private int elements = 0;
	/**
	 * The number of slots in the hashtable.
	 */
	private int slots = 0;

	/**
	 * Constructor for the hashtable that takes a size.
	 * 
	 * @param size for the hashtable
	 */
	@SuppressWarnings("unchecked")
	public ThreeTenHashTable1(int size) {
		storage = (TableEntry<K, V>[]) new TableEntry[size];
		slots = size;
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

		if (position < 0) {
			position *= -1;
		}

		if (storage[position] == null || isTombstone(position)) {
			storage[position] = temp;
			elements += 1;
		} else if (storage[position].getKey().equals(k)) {
			storage[position] = temp;
		} else {
			while (storage[position] != null) {
				if (isTombstone(position)) {
					break;
				}
				if (position == slots - 1) {
					position = 0;
				} else {
					position += 1;
				}
			}
			storage[position] = temp;
			elements += 1;
		}

		if ((elements * 1.0 / slots) >= 0.8) {
			rehash(slots * 2);
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
		int position = k.hashCode() % slots;
		V valueReturned = null;

		if (position < 0) {
			position *= -1;
		}

		if (storage[position].getKey().equals(k)) {
			valueReturned = storage[position].getValue();
			storage[position] = new TableEntry<>(null, null);
			elements -= 1;
		} else {
			while (storage[position] != null) {
				if (storage[position].getKey().equals(k)) {
					valueReturned = storage[position].getValue();
					storage[position] = new TableEntry<>(null, null);
					elements -= 1;
					break;
				}
				if (position == slots - 1) {
					position = 0;
				} else {
					position += 1;
				}
			}
		}
		return valueReturned;
	}

	/**
	 * Method used to get the value associated with the key.
	 * 
	 * @param k the key
	 * @return value
	 */
	public V get(K k) {
		V returnValue = null;
		int position = k.hashCode() % slots;
		boolean isTomb = false;

		if (position < 0) {
			position *= -1;
		}

		if (isTombstone(position)) {
			isTomb = true;
			position += 1;
			if (position == slots) {
				position = 0;
			}
		}
		if (isTomb == false) {
			if (storage[position].getKey().equals(k)) {
				returnValue = storage[position].getValue();
				return returnValue;
			} else {
				while (storage[position] != null) {
					if (isTombstone(position)) {
						position += 1;
					} else if (storage[position].getKey().equals(k)) {
						returnValue = storage[position].getValue();
						break;
					}
					if (position == slots - 1) {
						position = 0;
					} else {
						position += 1;
					}
				}
			}
		} else {
			while (storage[position] != null) {
				if (isTombstone(position)) {
					position += 1;
				} else if (storage[position].getKey().equals(k)) {
					returnValue = storage[position].getValue();
					break;
				}
				if (position == slots - 1) {
					position = 0;
				} else {
					position += 1;
				}
			}
		}

		return returnValue;
	}

	/**
	 * Method used to see if an element in the hashtable is a tombstone.
	 * 
	 * @param loc the position
	 * @return true if it is
	 */
	public boolean isTombstone(int loc) {
		if (storage[loc] == null) {

		} else if (storage[loc].getKey() == null && storage[loc].getValue() == null) {
			return true;
		}

		return false;
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
		TableEntry<K, V>[] newStorage = (TableEntry<K, V>[]) new TableEntry[size];
		TableEntry<K, V>[] temp = storage;
		int tempSlots = slots;
		int tempElements = elements;

		if (size < elements + 1) {
			return false;
		}

		slots = size;
		storage = newStorage;

		for (int i = 0; i < tempSlots; i++) {
			elements = 0;
			if (temp[i] == null || (temp[i].getKey() == null && temp[i].getValue() == null)) {
				continue;
			}
			put(temp[i].getKey(), temp[i].getValue());
		}

		elements = tempElements;

		return true;
	}

	/**
	 * Provided method to help with debugging.
	 * 
	 * @return string
	 */
	public String toString() {
		// THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < storage.length; i++) {
			if (storage[i] != null && !isTombstone(i)) {
				s.append(storage[i]);
				s.append("\n");
			}
		}
		return s.toString().trim();
	}

	/**
	 * Proved method to help with debugging.
	 * 
	 * @return string
	 */
	public String toStringDebug() {
		// THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < storage.length; i++) {
			if (!isTombstone(i)) {
				s.append("[" + i + "]: " + storage[i] + "\n");
			} else {
				s.append("[" + i + "]: tombstone\n");
			}

		}
		return s.toString().trim();
	}
}