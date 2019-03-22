package classes;

import dataStructures.LinkedList;

public class Hand {
/**
 * To represent the hand a linked list is used
 */
	
	private LinkedList hand;
		
	public Hand() {
		this.hand = new LinkedList();
	}

	
//	check for a book
	

    public boolean check_for_book(){

	    return false;
    }

/**
 * Add cards to the users hand
 * @param newCard
 * @return true if the card was added successfully and false if not
 */	
	public boolean addCard(Card newCard) {
		Node card = new Node(newCard);
		return hand.insert(card);
	}





	public Card removeCard(String card_to_remove) {
		Node newCard = hand.remove(card_to_remove);
		return newCard.getData();
	}

	public void showCards(){
//		call the display
		hand.display();
	}
}
