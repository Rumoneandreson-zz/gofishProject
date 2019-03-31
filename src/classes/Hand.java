package classes;

import dataStructures.LinkedList;

public class Hand {
/**
 * To represent the hand a linked list is used
 */
	
	private LinkedList hand;
	private int books;
	private int handCounter;

	public Hand() {
		this.handCounter = 0;
		this.hand = new LinkedList();
	}

    public int getBooks() {
        return books;
    }

	public int getHandCounter() {
		return handCounter;
	}

	public void setBooks() {
		books++;
	}

	public LinkedList getHand() {
		return hand;
	}

	/**
 * Add cards to the users hand
 * @param newCard
 * @return true if the card was added successfully and false if not
 */
	public boolean addCard(Card newCard) {
		Node card = new Node(newCard);
		handCounter++;
		return hand.insert(card);
	}

	public Node removeCard(String card_to_remove) {
		Node newCard = hand.remove(card_to_remove);
		handCounter--;
		return newCard;
	}

    public void showCards(){
        hand.display();
    }

    public boolean checkForBook(){
	    if (hand.isThereAPair()){
	    	handCounter -= 2;
	        books++;
	        return true;
        }
	    return false;
    }

    public boolean isHandEmpty() {
		return hand.isEmpty();
	}
}
