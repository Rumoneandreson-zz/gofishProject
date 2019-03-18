package dataStructures;

import classes.Node;

public class LinkedList {
	private Node head;	
	
	public LinkedList() {
		this.head = null;
	}	
	
	
	public boolean isEmpty() {
		return head == null;
	}
	
	@SuppressWarnings("unused")
	public boolean isFull() {
		Node tmp = new Node();
		
		if (tmp == null) {
			return true;
		} else {
			tmp = null;
			return false;
		}
	}
	
	public boolean insertAtFront(Node next) {
		if (isFull()) {
			System.out.println("The list is full");
		} else {
			if (isEmpty()) {
				head = next;
			} else {
				Node tmp = head;
				head = next;
				next.setNext(tmp);
			}
			return true;
		}
		return false;
	}
	
	
	public boolean insertAtBack(Node next) {
		if (isFull()) {
			System.out.println("The list is full");
		} else {
			if (isEmpty()) {
				head = next;
			} else {
				Node tmp = head;
				
				while (tmp.getNext() != null) {
					tmp = tmp.getNext();
				}
				
				tmp.setNext(next);
			}
			return true;
		}
		return false;
	}
	
	public Node removeFromFront() {
		
//		head to the next value after current head
		
		if (isEmpty()) {
			System.out.println("No item to be removed");
		} else {
			Node tmp = head;
			head = head.getNext();
			Node data = tmp;
			tmp = null;
			return data;
		}
		
		return null;
	}
	
	
/**
 * Search for and remove node from the list
 * @param Node
 * @return Node removed or null if nothing is removed
 */
	//TODO implement this method
	public Node  SearchAndRemove(){
		return null;
	}
	
	public void display() {
		Node tmp = head;
		while (tmp != null) {
			tmp.getData();
			tmp = tmp.getNext();
		}
	}
}
