package eg.edu.alexu.csd.datastructure.stack.cs;

/**
 * 
 * @author Katoos
 *
 */

public class LinkedListedStack implements IStack {
	/**
	 * 
	 * class for node which consists of 2 parts (data,next)
	 *
	 */
	public class Node {
		
		Object data;
		Node next;
		/**
		 * constructor for no input
		 */
		public Node () {
		
		}
		/**
		 * @param data to store data 
		 */
		public Node (Object data) {
			this.data = data;
			next = null;
		}
	}
	
	/**
	 * @param top pointer to refer to top element of the stack
	 * 
	 * @param size to store stack elements
	 */
	
	Node top;
	private int size;
	
	public LinkedListedStack () {
		this.top = null;
	}
	
	/**
	 * @return the top element of the stack
	 * 
	 * removing the top element 
	 */
	
	public Object pop () {
		if (top == null) {
			throw new RuntimeException();
		}
		else {
			Object temp;
			temp = top.data;
			top = top.next;	
			size--;
			return temp;
		}
	}
	
	/**
	 * add one element to the stack from the top
	 * 
	 * @param data
	 */
	
	public void push (Object data) {
		Node newNode = new Node(data);
		if (top == null) {
			top = newNode;
		}
		else {
			newNode.next = top;
			top = newNode;
		}
		size++;

	}
	
	/**
	 * @return the top element of the stack
	 */
	
	public Object peek () {
		if (top == null ) {
			throw new RuntimeException();
		}
		else {
			return top.data;
		}
	}
	
	/**
	 * @return no of elements of stack
	 */
	
	public int size () {
		return size;
	}
	
	/**
	 * @return true if stack is empty and false otherwise
	 */
	
	public boolean isEmpty() {
		if (top == null) {
			return true;
		}
		else {
			return false;
		}
	}
}
