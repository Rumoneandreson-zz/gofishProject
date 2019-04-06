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
	private boolean isFull() {
		Node tmp = new Node();
		if (tmp == null) {
			return true;
		} else {
			tmp = null;
			return false;
		}
	}

	/**
	 * Insert new card at the front of the list
	 * @param next
	 * @return true if added successfully false if it wasnt
	 */
	public boolean insert(Node next) {
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

	/**
	 * finds a node passed in and returns it
	 * @param item_to_find
	 * @return node found or null
	 */
	public boolean search(String item_to_find){
	    if (isEmpty()){
			//change the way this is handled
			System.out.println("There is nothing in the list to find");
		}else{
			Node tmp = head;
			while (tmp != null) {
				if (tmp.getData().getRank().toLowerCase().equals(item_to_find.toLowerCase()))
					return true;
				tmp = tmp.getNext();
			}
		}
		return false;
	}

	/**
	 * Removes a node from the list based on the string passed in
	 * @param cardRank
	 * @return
	 */
	public Node remove(String cardRank){
		if (!isEmpty()){
			Node tmp = head;
			Node prev = head;
			Node card_removed;

			if (head.getData().getRank().toLowerCase().equals(cardRank.toLowerCase())) {
				tmp = head.getNext();
				card_removed = head;
				head = null;
				head = tmp;
				return card_removed;
			} else {
				while (tmp != null){
					if (tmp.getData().getRank().toLowerCase().equals(cardRank.toLowerCase())){
						prev.setNext(tmp.getNext());
						card_removed = new Node(tmp.getData());
						tmp = null;
						return card_removed;
					}
					prev = tmp;
					tmp = tmp.getNext();
				}
			}
		}
		return null;
	}


	/**
	 * searches for pair of of the same rank different suit and if found then the pair is removed
	 * @return true if pair was found and cards removed false if vice versa
	 */
	public boolean isThereAPair(){
		// sets the current card to head
        Node current = head;

		// loop until another one of the same type
        while (current != null){
			// get the first element
			Node tmp = head;
            while (tmp != null){

                if (tmp.getData().getRank().toLowerCase().equals(current.getData().getRank().toLowerCase())) {
                    if (!tmp.getData().getSuite().toLowerCase().equals(current.getData().getSuite().toLowerCase())){
						// remove both cards since as the pair is found
                        remove(tmp.getData().getRank());
                        remove(current.getData().getRank());
                        return true;
                    }
                }
                tmp = tmp.getNext();
            }
            current = current.getNext();
        }


	    return false;
    }

	/**
	 * find the information at an index of the list
	 * @param index
	 * @return data at specified index
	 */
	public Node dataAtIndex(int index){
		Node tmp = head;
		int i = 0;
		Node node_card;
		while (tmp != null) {
			if (i == index) {
				node_card = tmp;
				return node_card;
			}
			i++;
			tmp = tmp.getNext();
		}
		return null;
	}

	// DEBUG
	// displays all the items in the list
	public void display() {
		Node tmp = head;
		while (tmp != null) {
			tmp.getData().display();
			tmp = tmp.getNext();
		}
	}

	/**
	 * counts all the nodes in the list
	 * @return
	 */
	public int countList(){
		int count = 0;
		Node tmp = head;
		while (tmp != null){
			count++;
			tmp = tmp.getNext();
		}
		return count;
	}
}
