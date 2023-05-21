
/**
 * MyAbacus class to set up 
 * representation of abacus.
 * 
 * @author Hammadullah Nasir
 */

public class MyAbacus implements Abacus {

	/**
	 * Base used for the base of the abacus.
	 */
	private int base;
	
	/**
	 * Places used for telling the columns that are in use. 
	 */
	private int places = 1; 
	
	/**
	 * BeadNumbersIndex used as an index for adding the MyAbacus objects in.
	 */
	private int beadNumbersIndex = 0;
	
	/**
	 * TopBeadNumbers array object used for the top bead positions.
	 */
	private DynArr310<Integer> topBeadNumbers = new DynArr310<>(); 
	
	/**
	 * BottomBeadNumbers array objects used for the top bead position.
	 */
	private DynArr310<Integer> bottomBeadNumbers = new DynArr310<>();
	
	/**
	 * Basic constructor for the class.
	 * 
	 * @param base that is going to be used for the abacus
	 */
	public MyAbacus(int base) {  
		if (base < 2 || base % 2 != 0) {
			throw new IllegalArgumentException("Base is invalid\n");
		}
		else {
			this.base = base;
		}
	}
	
	/**
	 * Gets the number base of the abacus
	 * and will never be less than 2.
	 * 
	 * @return base
	 */
	public int getBase() { 
		return this.base; 
	}

	/**
	 * Gets the current number of places in use
	 * to represent the number.
	 * 
	 * @return places in use
	 */
	public int getNumPlaces() { //DONE
		return places;
	}
	
	/**
	 * Gets the number of bead on the top 
	 * that are currently in use in the
	 * specific place.
	 * 
	 * @param place is the position of the abacus or column
	 * @return top bead in use
	 * @throws IndexOutOfBoundsException if invalid place
	 */
	public int getBeadsTop(int place) { //DONE
		if (place < 0 || place > getNumPlaces()) {
			throw new IndexOutOfBoundsException("Invalid Place\n");
		}
		if (topBeadNumbers.get(place) == null) {
			topBeadNumbers.add(0, 0);
		}
		//return topBeads; 
		return topBeadNumbers.get(place);
	}
	
	/**
	 * Gets the number of beads in the bottom area of the
	 * abacus which are in-use for a given place.
	 * 
	 * @param place is the position or column of the abacus
	 * @return bottom beads in use
	 * @throws IndexOutOfBoundsException if invalid place
	 */
	public int getBeadsBottom(int place) {
		if (place < 0 || place > getNumPlaces()) {
			throw new IndexOutOfBoundsException("Invalid Place\n");
		}
		if (bottomBeadNumbers.get(place) == null) {
			bottomBeadNumbers.add(0, 0);
		}
		//return bottomBeads; 
		return bottomBeadNumbers.get(place);
	}
	
	/**
	 * Method used to check if two MyAbacus objects are equal to each other.
	 * 
	 * @param m is the MyAbacus object that is to be compared
	 * @return boolean value
	 */
	public boolean equals(MyAbacus m) {
		
		if (this.getBase() != m.getBase()) {
			return false;
		}
		if (this.places != m.places) {
			return false;
		}
		for (int i = 0; i < places; i++) {
			if (this.getBeadsTop(i) != m.getBeadsTop(i)) {
				return false;
			}
			if (this.getBeadsBottom(i) != m.getBeadsBottom(i)) {
				return false;
			}
		}
		return true; 
	}
	
	/**
	 *  Adds the given string representation of a number to
	 *  the current value of the abacus. The abacus is updated
	 *  to this new position. It returns the steps to perform
	 *  the add (snap shots of the abacus at each step). The
	 *  abacus may be left in an "improper state" if the
	 *  provided arguments are invalid.
	 *  
	 *  @param value the string representation of the value to add
	 *  @return the different positions the abacus was in
	 *  @throws NumberFormatException if string is not correct for the base
	 */
	public DynArr310<Abacus> add(String value) {
		int v = Integer.parseInt(value);
		int i = 1; //index
		DynArr310<Abacus> x = new DynArr310<>(); //Array to hold the abacus inside
		MyAbacus c1 = new MyAbacus(this.getBase()); //Add the current abacus into the array
		x.add(0, c1);
		if (value.length() > 1) {
			MyAbacus c2 = new MyAbacus(this.getBase());
			c2.places = value.length();
			x.add(i, c2);
			i += 1;
		}
		
		for (int k = value.length(); k > 0; k--) {
			char temp1 = value.charAt(k - 1);
			String temp2 = Character.toString(temp1);
			Integer temp3 = Integer.parseInt(temp2);
			
			if (temp3 < (getBase() / 2)) {
				topBeadNumbers.add(beadNumbersIndex, 0);
				bottomBeadNumbers.add(beadNumbersIndex, temp3);
			}
			else if (temp3 >= (getBase() / 2)) {
				topBeadNumbers.add(beadNumbersIndex, temp3 / (getBase() / 2));
				bottomBeadNumbers.add(beadNumbersIndex, temp3 - (getBase() / 2));
			}
			beadNumbersIndex += 1;
		}
		
		x.add(i, new MyAbacus(this.getBase()));
		MyAbacus temp = (MyAbacus) x.get(i);
		temp.bottomBeadNumbers = bottomBeadNumbers;
		temp.topBeadNumbers = topBeadNumbers;
		temp.places = value.length();

		return x; 
	}

}