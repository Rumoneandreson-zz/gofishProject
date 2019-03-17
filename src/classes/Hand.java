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
	
	
//	return card from hand	
//  remove card from the hand linked list	
//	add cards to list	

	
//	check for a book
	


/**
 * Add cards to the users hand
 * @param newCard
 * @return true if the card was added successfully and false if not
 */	
	public boolean addCard(Card newCard) {
		Node card = new Node(newCard);
		return hand.insertAtFront(card);
	}
	

/**
 * removes a card from the front of the list	
 * @return returns card
 */
	public Card removeCard() {
		Card newCard = new Card(hand.removeFromFront().getData());
		return newCard;
	}
}
