package classes;

import dataStructures.LinkedList;

public class Hand {
/**
 * To represent the hand a linked list is used
 */
	
	private LinkedList hand;
	private int books;

	public Hand() {
		this.hand = new LinkedList();
	}

    public int getBooks() {
        return books;
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
	public void addCard(Card newCard) {
		Node card = new Node(newCard);
		hand.insert(card);
	}

	public Node removeCard(String card_to_remove) {
		Node newCard = hand.remove(card_to_remove);
		return newCard;
	}

	public int countCards(){
		return hand.countList();
	}

    public boolean checkForBook(){
	    if (hand.isThereAPair()){
	        books++;
	        return true;
        }
	    return false;
    }
}
