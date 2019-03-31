package dataStructures;

import classes.Node;

public class LinkedList {
	private Node head;	
	
	public LinkedList() {
		this.head = null;
	}

	public Node getHead() {
		return head;
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

	public Node remove(String cardRank){
		if (!isEmpty()){
			Node tmp = head;
			Node prev = head;
			Node card_removed = new Node();
			while (tmp != null){
				if (head.getData().getRank().equals(cardRank)) {
					tmp = head.getNext();
					card_removed = head;
					head = null;
					head = tmp;
					return card_removed;
				} else {
					if (tmp.getData().getRank().equals(cardRank)){
						prev.setNext(tmp.getNext());
						card_removed = tmp;
						tmp = null;
						return card_removed;
					}
				}
				prev = tmp;
				tmp = tmp.getNext();
			}
		}
		return null;
	}


	public boolean isThereAPair(){

//      sets the current card to head
        Node current = head;

//      loop until another one of the same type
        while (current != null){
//		    get the first element
			Node tmp = head;
            while (tmp != null){

                if (tmp.getData().getRank().equals(current.getData().getRank())) {
                    if (!tmp.getData().getSuite().equals(current.getData().getSuite())){
//                      remove both cards since as the pair is found
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

    public Node returnNode(int index){
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

	public void display() {
		Node tmp = head;
		while (tmp != null) {
			tmp.getData().display();
			tmp = tmp.getNext();
		}
	}
}
