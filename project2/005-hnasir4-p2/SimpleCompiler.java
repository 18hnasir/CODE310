
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/**
 * Class used to be a compiler for input file containing symbols to process.
 * 
 * @author Hammadullah Nasir
 *
 */
class SimpleCompiler {

	/**
	 * Method used to make a queue from a file input using a scanner and a linked
	 * list.
	 * 
	 * @param filename to read from
	 * @return Queue
	 * @throws IOException for exception handling
	 */
	public static Node<String> fileToQueue(String filename) throws IOException {
		// given a file name, open that file in a scanner and create a queue of nodes
		// the head of the queue of nodes should be the start of the queue
		// the values in the nodes should be the strings read in each time you call
		// next() on the scanner

		File readFile = new File(filename);
		Scanner scan = new Scanner(readFile);
		Node<String> queue = null;

		while (scan.hasNext()) {
			Node<String> temp = queue;
			if (queue == null) {
				queue = new Node<String>(scan.next());
				continue;
			}
			while (temp != null) {
				if (temp.getNext() == null) {
					temp.setNext(new Node<String>(scan.next()));
					break;
				}
				temp = temp.getNext();
			}
		}

		return queue;
	}

	/**
	 * Method used to compile the input into a stack and return what is left.
	 * 
	 * @param input      to read from
	 * @param numSymbols to see how many input needs to go in the stack
	 * @return Node
	 */
	public Node<String> compile(Node<String> input, int numSymbols) {
		// Given an input queue of symbols, process the number of symbols
		// specified (numSymbols) and update the callStack and symbols
		// variables appropriately to reflect the state of the "SimpleCompiler"
		// (see below the "do not edit" line for these variables).

		// Return the remaining queue items.

		// For example, if input is the head of a linked list 3 -> 2 -> +
		// and numSymbols=2, you would push 3 and push 2, then return the linked
		// list with just the + node remaining.

		Node<String> remainingItems = null;
		int i = 0; // to keep track of numSymbols
		int temp1 = 0;
		int temp2 = 0;
		int temp3 = 0;
		String temp4 = "";
		int newValue = 0;

		while (input != null) {
			if (i < numSymbols) {
				if (input.getValue().equals("+")) {
					if (symbols.contains((String) callStack.peek())) {
						temp1 = symbols.get((String) callStack.pop());
					} else {
						temp1 = Integer.parseInt((String) callStack.pop());
					}

					if (symbols.contains((String) callStack.peek())) {
						temp2 = symbols.get((String) callStack.pop());
					} else {
						temp2 = Integer.parseInt((String) callStack.pop());
					}

					newValue = temp1 + temp2;

					callStack.push(Integer.toString(newValue));
				} else if (input.getValue().equals("-")) {
					if (symbols.contains((String) callStack.peek())) {
						temp1 = symbols.get((String) callStack.pop());
					} else {
						temp1 = Integer.parseInt((String) callStack.pop());
					}

					if (symbols.contains((String) callStack.peek())) {
						temp2 = symbols.get((String) callStack.pop());
					} else {
						temp2 = Integer.parseInt((String) callStack.pop());
					}

					// temp1 = Integer.parseInt((String) callStack.pop());
					// temp2 = Integer.parseInt((String) callStack.pop());
					newValue = temp2 - temp1;

					callStack.push(Integer.toString(newValue));
				} else if (input.getValue().equals("*")) {
					if (symbols.contains((String) callStack.peek())) {
						temp1 = symbols.get((String) callStack.pop());
					} else {
						temp1 = Integer.parseInt((String) callStack.pop());
					}

					if (symbols.contains((String) callStack.peek())) {
						temp2 = symbols.get((String) callStack.pop());
					} else {
						temp2 = Integer.parseInt((String) callStack.pop());
					}

					newValue = temp1 * temp2;

					callStack.push(Integer.toString(newValue));
				} else if (input.getValue().equals("/")) {
					if (symbols.contains((String) callStack.peek())) {
						temp1 = symbols.get((String) callStack.pop());
					} else {
						temp1 = Integer.parseInt((String) callStack.pop());
					}

					if (symbols.contains((String) callStack.peek())) {
						temp2 = symbols.get((String) callStack.pop());
					} else {
						temp2 = Integer.parseInt((String) callStack.pop());
					}

					newValue = temp2 / temp1;

					callStack.push(Integer.toString(newValue));
				} else if (input.getValue().equals("=")) {
					temp3 = Integer.parseInt((String) callStack.pop()); // value
					temp4 = (String) callStack.pop(); // key

					symbols.put(temp4, temp3);
				} else if (input.getValue().equals("print")) {
					System.out.println(callStack.pop());
				} else {
					callStack.push(input.getValue());
				}
				i += 1;
			} else {
				break;
			}
			input = input.getNext();
		}

		remainingItems = input;

		return remainingItems;
	}

	/**
	 * Method used for testing purposes.
	 */
	public static void testThisCode() {
		// edit this as much as you want, if you use main without any arguments,
		// this is the method that will be run instead of the program
		// System.out.println("You need to put test code in testThisCode() to run
		// SimpleCompiler with no parameters.");

		/*
		 * String input =
		 * "C:\\Users\\18hna\\Documents\\Programming\\CS 310\\p2\\project2\\005-hnasir4-p2\\prog\\sample4.txt"
		 * ; Node<String> temp = null; Node<String> temp2 = null; SimpleCompiler sc =
		 * new SimpleCompiler(); try { temp = SimpleCompiler.fileToQueue(input); } catch
		 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 * 
		 * temp2 = sc.compile(temp, 4);
		 * 
		 * while (temp2 != null) { System.out.println("HERE: " + temp2.getValue());
		 * temp2 = temp2.getNext(); }
		 * 
		 */

	}

	/**
	 * Array that contains symbols needed for processing.
	 */
	public static final String[] INT_OPS = { "+", "-", "*", "/" };

	/**
	 * Array that contains additional symbols for testing.
	 */
	public static final String[] ASSIGN_OPS = { "=", "+=", "-=", "*=", "/=" };

	// or these...

	/**
	 * Used to be a stack for the compiler.
	 */
	public CallStack<Object> callStack = new CallStack<>();

	/**
	 * Used to be a BST for the compiler.
	 */
	public LookUpBST<String, Integer> symbols = new LookUpBST<>();

	/**
	 * Main method that contains code needed to run the program in the cmd.
	 * 
	 * @param args that will be used
	 */
	public static void main(String[] args) {
		// this is not a testing main method, so don't edit this
		// edit testThisCode() instead!

		if (args.length == 0) {
			testThisCode();
			return;
		}

		if (args.length != 2 || !(args[1].equals("false") || args[1].equals("true"))) {
			System.out.println("Usage: java SimpleCompiler [filename] [true|false]");
			System.exit(0);
		}

		try {
			(new SimpleCompiler()).runCompiler(args[0], args[1].equals("true"));
		} catch (IOException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}

	/**
	 * Method used for testinga and debugging if need be.
	 * 
	 * @param filename to be used
	 * @param debug mode
	 * @throws IOException for exception handling
	 */
	public void runCompiler(String filename, boolean debug) throws IOException {
		Node<String> input = fileToQueue(filename);
		System.out.println("\nProgram: " + Node.listToString(input));

		if (!debug) {
			while (input != null) {
				input = compile(input, 10);
			}
		} else {
			Scanner s = new Scanner(System.in);
			for (int i = 1; input != null; i++) {
				System.out.println("\n######### Step " + i + " ###############\n");
				System.out.println("----------Step Output----------");
				input = compile(input, 1);
				System.out.println("----------Lookup BST---------");
				System.out.println(symbols);
				System.out.println("----------Call Stack--------");
				System.out.println(callStack);
				if (input != null) {
					System.out.println("----------Program Remaining----");
					System.out.println(Node.listToString(input));
				}
				System.out.println("\nPress Enter to Continue");
				s.nextLine();
			}
		}
	}
}