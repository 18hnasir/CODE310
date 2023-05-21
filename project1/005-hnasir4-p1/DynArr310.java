/**
 * DynArr310 class used for 
 * implementation of a dynamic
 * array list. 
 * 
 * @author Hammadullah Nasir
 *
 * @param <T> for Generics
 */

public class DynArr310<T> {
	/**
	 * Private variables used for the class.
	 */
	private static final int DEFAULT_CAPACITY = 2;
	
	/**
	 * The main array that will be storing the data. 
	 */
	private T[] data;

	/**
	 * Private variable used to determine how many elements are in the data.
	 */
	private int elements = 0;
	
	/**
	 * Basic default constructor.
	 */
	@SuppressWarnings("unchecked")
	public DynArr310() { //DONE
		//initial capacity of the array should be DEFAULT_CAPACITY
		data = (T[])new Object[DEFAULT_CAPACITY];
	}

	/**
	 * Basic constructor with initialCapacity parameter.
	 * 
	 * @param initialCapacity capacity should be set to initialCapacity 
	 */
	@SuppressWarnings("unchecked")
	public DynArr310(int initialCapacity) { 
		if (initialCapacity < 1) {
			throw new IllegalArgumentException("Initial capicity is smaller than 1\n");
		}
		else {
			data = (T[])new Object[initialCapacity];
		}
	}
	

	/**
	 * Method used to return the number of elements in 
	 * the smart array.
	 * 
	 * @return elements
	 */
	public int size() {	
		return elements;
	}  
		
	/**
	 * Method used to return the max number of 
	 * elements before the next expansion. 
	 * 
	 * @return data.length
	 */
	public int capacity() {
		return data.length;
	}

	/**
	 * Method used to add values at the 
	 * specified index.
	 * 
	 * @param index position of the array
	 * @param value the value that needs to be placed
	 */
	@SuppressWarnings("unchecked")
	public void add(int index, T value) { 
		if (index < 0 || index > elements) {
			throw new IndexOutOfBoundsException("Invalid index\n");
		}
		else {
			if (index >= data.length || elements == data.length) { //Doubles the capacity if the index is not in the length
				setCapacity(data.length * 2);
			}
			if (index >= elements) { //determines if I have to shift or not
				for (int i = 0; i < data.length; i++) { //if not then I will add it at the specified index
					if (i == index) {
						data[i] = value;
						break;
					}
				}
				elements += 1;
			}
			else {
				for (int i = elements - 1; i >= index; i--) {
					data[i + 1] = data[i];
				}
				data[index] = value;
				elements += 1;
			}
		}
	} 
	
	/**
	 * Method used to obtain the value
	 * at the specified index. 
	 * 
	 * @param index position of the array
	 * @return T value
	 */
	public T get(int index) { 	
		if (index < 0 || index >= data.length) {
			throw new IndexOutOfBoundsException("Invalid index: " + index + "\n");
		}
		
		return data[index];
	}
	
	/**
	 * Method used to replace the value
	 * at the specified index. 
	 * 
	 * @param index position of the array
	 * @param value value that needs to be replaced
	 * @return oldItem
	 */
	public T replace(int index, T value) { 
		T oldItem = null; 
		
		if (index < 0 || index >= data.length) {
			throw new IndexOutOfBoundsException("Invalid index\n");
		}
		else {
			oldItem = data[index];
			data[index] = value;
		}
		
		return oldItem;
	}
	
	/**
	 * Method used to return the index 
	 * of the specified value.
	 * 
	 * @param value the value that needs to be found
	 * @return index
	 */
	public int firstIndexOf(T value) {
		int index = 0;
		boolean found = false; 
		for (int i = 0; i < elements; i++) {
			if (data[i] == value) {
				found = true;
				index = i;
				break;
			}
		}
		
		if (found == false) {
			index = -1;
		}
		
		return index;
	}

	/**
	 * Method used to delete the value 
	 * at the specified index. 
	 * 
	 * @param index position of the array
	 * @return returnValue
	 */
	@SuppressWarnings("unchecked")
	public T delete(int index) { 
		T returnValue = null;
		if (index < 0 || index >= data.length) {
			throw new IndexOutOfBoundsException("Invalid index\n");
		}
		else {
			if (index == elements - 1) {
				returnValue = data[index];
				data[index] = null;
				elements -= 1;
			}
			else {
				returnValue = data[index];
				elements -= 1;
				for (int i = index; i < elements; i++) {
					data[i] = data[i + 1];
				}
				data[elements] = null;
			}
		}
		
		if ((elements / data.length) < (1.0 / 4.0)) {
			setCapacity(data.length / 2);
		}
		
		
		return returnValue; 
	}  

	/**
	 * Method used to delete all the values equal
	 * to the provided one and return a count of 
	 * the amount that was deleted. 
	 * 
	 * @param value that needs to be deleted from the array
	 * @return numDeleted
	 */
	public int deleteAll(T value) { 
		int numDeleted = 0;
		
		for (int i = 0; i < elements; i++) {
			if (data[i].equals(value)) {
				delete(i);
				i = -1; //so it can start at the beginning of the loop again
				numDeleted += 1;
			}
		}
		
		return numDeleted;  
	}

	/**
	 * Method used to change the max number of items allowed before 
	 * the next expansion to the specified capacity.
	 * 
	 * @param newCapacity is the new size of the array
	 * @return set
	 */
	@SuppressWarnings("unchecked")
	public boolean setCapacity(int newCapacity) { 
		boolean set = false;
		T[] newData;
		
		if (newCapacity < DEFAULT_CAPACITY || newCapacity <= elements) {}
		else {
			newData = (T[])new Object[newCapacity];
			for(int i = 0; i < elements; i++) {
				newData[i] = data[i];
			}
			set = true;
			data = newData;
		}
		
		return set;
	}
	
	/**
	 * Method used to see if two DynArr310's are equal
	 * to each other. 
	 * 
	 * @return isTrue
	 */
	@Override
	public boolean equals(Object o) { 
		boolean isTrue = true;
		
		if (o instanceof DynArr310) {
			DynArr310<?> oject = (DynArr310<?>) o;
			
			if (oject.data.length != this.data.length) {
				return false;
			}
			for (int i = 0; i < oject.size(); i++) {
				if (this.data[i].equals(oject.data[i])) {
					
				}
				else {
					isTrue = false;
				}
			}
		}
		
		return isTrue; 
	}
	
	/**
	 * Method is used to make a clone of the DynArr310.
	 * Makes a copy of the array and its values.
	 * 
	 * @param <E> is a generic list type
	 * @param orig is the original array that is being cloned
	 * @return c object
	 */
	public static <E> DynArr310<E> clone(DynArr310<E> orig) { //DONE
		// makes a copy of the dynamic array and all its current
		// values don't forget to set the capacity!
		// O(N) where N is the number of elements in the array
	    
		// Note: this should create a deep copy of the original,
		// BUT make _shallow_ copies of any elements _in_ the
		// original. So don't just do this: return orig;
		// But don't worry about trying to deep-copy Es
		// (since E can be any type that's complicated).
		
		int size = orig.data.length;
		DynArr310<E> c = new DynArr310<>(size);
		
		for (int i = 0; i < size; i++) {
			c.data[i] = orig.data[i];
		}
		
		c.elements = orig.elements;
	    
		return c;
	}
	
	/**
	 * Method is used to determine if two DynArr310 arrays
	 * are the same.
	 * 
	 * @param <E> for generics
	 * @param arr1 array that is checked for being a clone
	 * @param arr2 another array that is checked for being a clone
	 * @return boolean value
	 */
	public static <E> boolean isClone(DynArr310<E> arr1, DynArr310<E> arr2) { //DONE
		// O(N) where N is the number of elements in the array
	    
		// remember that clones are different than equal lists,
		// clones have to have a shallow copy of elements
		// and not be at the same memory address
        
		if (arr1.size() != arr2.size()) { //not clones because they are not the same length
			return false;
		}
		
		int k = arr1.size();
		
		for (int i = 0; i < k; i++) {
			if (arr1.data[i] == arr2.data[i]) {}
			else {
				return false;
			}
		}
		if (arr1 == arr2) {
			return false;
		}
		
		return true;
	}
	
	// --------------------------------------------------------
	// DO NOT EDIT ANYTHING BELOW THIS LINE (except to add JavaDocs)
	// --------------------------------------------------------
	/**
	 * Method is used for testing.
	 * 
	 * @return string value
	 */
	public String toString() {
		if(size() == 0) return "";
		
		StringBuffer sb = new StringBuffer();
		sb.append(get(0));
		for(int i = 1; i < size(); i++) {
			sb.append(", ");
			sb.append(get(i));
		}
		return sb.toString();
	}
}