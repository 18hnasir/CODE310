import java.util.Scanner;
import java.util.Comparator;

/**
 * Class that is a tester for the trees.
 * 
 * @author Hammadullah Nasir
 *
 */
public class PA3 {

	/**
	 * Method used to sort an array using the heapsort method.
	 * 
	 * @param <T> for generics
	 * @param values array
	 * @param comp comparator
	 */
	public static <T> void heapsort(T[] values, Comparator<T> comp) {
		// Your code here... you may add everything to a heap and then remove each item
		// or you may use the "heapify constructor", and then remove everything,
		// whichever
		// sounds more fun to you. Note that your code should modify the original array
		// of values.

		// Again, if you are unfamiliar with Comparators, see:
		// https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html

		// Note: Sorting in any way other than with a heap will result in a 0
		// for this method... you have been warned! This can be done in 3 lines
		// of code, but is more readable in 6-8.

		PriorityTree<T> temp = new PriorityTree<>(comp, 2);
		int index; // last index to start with
		int size = 0; // Elements in the array
		T tempValue;
		int tempIndex = 0;

		for (T value : values) {
			temp.add(value);
			size += 1;
		}

		for (int i = 0; i < size; i++) {
			values[i] = temp.remove();
		}

		index = size - 1;

		while (index > 0) {
			tempValue = values[index];
			values[index] = values[0];
			values[0] = tempValue;
			tempIndex = 0;

			index -= 1;
			for (T value : values) {
				if (tempIndex > index) {
					break;
				}
				temp.add(value);
				tempIndex += 1;
			}

			for (int i = 0; i < tempIndex; i++) {
				values[i] = temp.remove();
			}
		}
	}

	/**
	 * Main method used for testing of the program.
	 * @param arg for arguments
	 */
	public static void main(String[] arg) {
		/*
		 * Comparator<Integer> comp = new Comparator<Integer>() { public int
		 * compare(Integer x, Integer y) { return x.compareTo(y);} };
		 * 
		 * Integer[] temp1 = {49, 62, 88, 73, 20, 94, 68};
		 * 
		 * PA3.heapsort(temp1, comp);
		 */

		Comparator<Integer> revComp = new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
		};

		Comparator<Integer> compToUse = null;
		String usage = "Usage: java PA3 {min|max}";
		if (arg.length == 1) {
			if (arg[0].equals("max")) {
				compToUse = revComp;
			} else if (arg[0].equals("min")) {
				compToUse = new Comparator<Integer>() {
					public int compare(Integer o1, Integer o2) {
						return o1.compareTo(o2);
					}
				};
			} else {
				System.out.println(usage);
				return;
			}
		} else {
			System.out.println(usage);
			return;
		}

		// we _will_ test your priority trees with values other than 3
		// you may want to as well...
		PriorityTree<Integer> intTree = new PriorityTree<>(compToUse, 5);
		Scanner keyboard = null;
		try {
			keyboard = new Scanner(System.in);
		} catch (Exception e) {
			System.out.println("Error connecting to keyboard input:\n" + e.toString());
			return;
		}

		int choice = 4;
		String choiceString = "";
		do {
			System.out.print("\n1. Enqueue a number\n" + "2. Dequeue a number\n" + "3. Sort a list of numbers\n"
					+ "4. Exit\n\n");

			choice = forceIntChoice(keyboard, "Enter your choice: ", 1, 4);

			switch (choice) {
				case 1:
					int numToAdd = forceIntChoice(keyboard, "Enter a number to add: ", Integer.MIN_VALUE,Integer.MAX_VALUE);
					intTree.add(numToAdd);
					break;
				case 2:
					System.out.println("Removed: " + intTree.poll());
					break;
				case 3:
					System.out.print("Enter numbers to sort, separated by spaces: ");
					try {
						String numberString = keyboard.nextLine();
						String[] valueStrings = numberString.split(" ");
						Integer[] values = new Integer[valueStrings.length];
						for (int i = 0; i < valueStrings.length; i++) {
							values[i] = Integer.parseInt(valueStrings[i]);
						}
						PA3.heapsort(values, revComp);
						System.out.print("Sorted: ");
						for (Integer value : values)
							System.out.print(value + " ");
						System.out.println();
					} catch (Exception e) {
						System.out.println("Error: one or more values was not a valid integer.");
						System.out.println(e.toString());
						e.printStackTrace();
					}
					break;
			}	

			if (choice < 3) {
				System.out.println("\nCurrent tree (height " + intTree.height() + ")");
				System.out.println("Level order walk: " + intTree.toString());
				System.out.println("Pre order walk:   " + intTree.toStringPreOrder());
				System.out.println("Post order walk:  " + intTree.toStringPostOrder());
				System.out.println("Level Order with Level Breaks:\n" + intTree.toStringWithLevels());
			}
		} while (choice != 4);
	}

	/**
	 * Method used for testing program. 
	 * 
	 * @param input to be used
	 * @param prompt to be used
	 * @param min to be used
	 * @param max to be used
	 * @return int
	 */
	private static int forceIntChoice(Scanner input, String prompt, int min, int max) {
		int choice = -1;
		while (choice == -1) {
			try {
				System.out.print(prompt);
				choice = Integer.parseInt(input.nextLine());
				if (choice >= min && choice <= max) {
					return choice;
				}
				System.out.println("You must enter an integer between " + min + " and " + max + ".");
			} catch (RuntimeException e) {
			}
			System.out.println("You must enter a valid integer.");
		}
		return choice;

	}
}