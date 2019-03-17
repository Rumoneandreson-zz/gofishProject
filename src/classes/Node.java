package classes;

public class Node {
//	Accept generic object so the linked list can be re used
	private Card Data;
	private Node next;
	
	
//	Primary constructor
	public Node(Card data, Node next) {
		this.Data = data;
		this.next = next;
	}
	
//	Default constructor
	public Node() {
		this(null, null);
	}
	
//	Copy constructor
	public Node(Node originalNode) {
		this(originalNode.Data, originalNode.next);
	}
	
	
	public Node(Card _data) {
		this(_data, null);
	}

	//	getters and setters
	public Card getData() {
		return Data;
	}

	public void setData(Card data) {
		Data = data;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	
//	Displays the content of the node
	@Override
	public String toString() {
		return "Node [Data=" + Data + ", next=" + next + "]";
	}	
	
	public void display() {
		System.out.println(toString());
	}	
}
