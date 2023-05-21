/**
 * Class used to represent an element in the hashtable.
 * 
 * @author The Professor
 *
 * @param <K> for generics
 * @param <V> for generics
 */
class TableEntry<K, V> {
	/**
	 * Key for the table entry.
	 */
	private K key;
	/**
	 * Value for the the table entry.
	 */
	private V value;

	/**
	 * Basic constructor for the class.
	 * 
	 * @param key   for the table entry
	 * @param value for the table entry
	 */
	public TableEntry(K key, V value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * Method used to get the key.
	 * 
	 * @return key
	 */
	public K getKey() {
		return key;
	}

	/**
	 * Method used to get the value.
	 * 
	 * @return value
	 */
	public V getValue() {
		return value;
	}

	/**
	 * To string method for the table entry.
	 * @return string
	 */
	public String toString() {
		return key.toString() + ":" + value.toString();
	}
}