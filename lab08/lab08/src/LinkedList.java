
/**
 * Class LinkedList - implementation of a LinkedList
 * 
 * @author Alec Porter et al. (mostly et al.)
 */
public class LinkedList {

	private Node head;
	private Node tail;
	private String output;
	
	/**
	 * Add tem to LinkedList
	 * 
	 * @param item String item to add to LinkedList 
	 */
	public void add(String item) {
		
		Node newItem = new Node(item);
		
		// handles the case where the new item 
		// is the only thing in the list
		if (head == null) {
			head = newItem;
			tail = newItem;
			return;
		}
		
		tail.next = newItem;
		tail = newItem;
	}
	
	/**
	 * Print all the items in the LinkedList
	 */
	public void print() {
		Node current = head;
		while (current != null) {
			System.out.println(current.item);
			current = current.next;
		}
	}
	
	/**
	 * Return first item in the LinkedList
	 * 
	 * @return	String if there is at least one item in the LinkedList or null for no items
	 */
	public String getFirst(){
		if (this.head == null){  // if the head is null then no items have been added
			output = null;
		}
		else{ // the head is the entry point to a LinkedList so if there is an item in head then it is the first item
			output = head.item;
		}
		return output;
	}

	/**
	 * Return last item in the LinkedList
	 * 
	 * @return String if there is at least one item in the LinkedList or null for no items
	 */
	public String getLast(){
		if (this.head ==  null){ // if the head is null then no items have been added
			output = null;
		}
		else{
			output = tail.item; // the tail is the last node in the LinkedList so if there is an item in tail then it is the last item
		}
		return output;
	}

	/**
	 * Return the second to last item in the LinkedList
	 * 
	 * @return String if there there is a second to last item in the LinkedList or null
	 */
	public String getPenultimate() {
		Node current = head;	// set the current Node to head
		output = null;

		if (this.head == null){ // if the head is null there is no second to last item
			output = null;
		}
		else{
			while (current.next != null){ // start the loop at the item after head (i.e. second item)
				output = current.item; 	// set the output to the prior item in the loop
				current = current.next; // move to next item
			}
		}
		return output;
	}


	class Node {
		String item;
		Node next;
		
		public Node(String item) {
			this.item = item;
			this.next = null;
		}
	}
}
