package classes;
import java.util.Random;

public class Deck {

/**
 * Class defines a deck of cards
 * Deck is implemented as an array of cards
 * Each deck has the same cards
 */
	
	
//	Constants  suits and ranks are constants
	private static final String[] SUITS = {"Diamond", "Hearts", "Clubs", "Spade"};
	private static final String[] RANKS = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
	

	private Card[] deckOfCards;
	private int cardsRemaining;


/**
 * Creates a new Deck of cards
 * Calls the card class and creates an instance of the card class for each rank and suit
 * 
 */
	public Deck() {
        this.cardsRemaining = 52;
	    this.deckOfCards = new Card[this.cardsRemaining];
		int cardIndexController = 0;
		
//		loops through the suites
		for (int i = 0; i < SUITS.length; i++) {
//			loops through rank for each suit
			for (int j = 0; j < RANKS.length; j++) {
				deckOfCards[cardIndexController] = new Card(SUITS[i], RANKS[j]);
				cardIndexController++;
			}
		}
	}
	

/**
 * get deckOfCards
 * @return array of cards (DECK)
 */
	public Card[] getDeckOfCard() {
		return deckOfCards;
	}
	

	public int getCardsRemaining() {
		return cardsRemaining;
	}
	
	
	
//	Game Methods

/**
 * Deals a single card from the deck	
 * Card will always be from the top so the 0 index
 * @return card
 */
	public Card dealCard() {		
		return remove(cardsRemaining - 1);
	}
	

	private Card remove(int index) {

		Card tmp = deckOfCards[index];
//		for (int i = index; i < deckOfCards.length - 1; i++) {
//			deckOfCards[i] = deckOfCards[i+1];
//		}
//


		deckOfCards[index] = null;


		/*
		52 cards

		 Card c = cards[cards.length]
		  cards[cards.length] = null
		  return c;*/
        cardsRemaining--;
		return tmp;
	}
	
	
/*
 * Shuffles the deck of cards 
 */
	public void Shuffle() {
		Random randomCardIndex = new Random();
		
		for (int i = 0; i < deckOfCards.length; i++) {
			
//			generates a random number between 0 and the size of the deck 52
			int tmpIndex = randomCardIndex.nextInt(deckOfCards.length);
			Card tmp  = deckOfCards[i];
			deckOfCards[i] = deckOfCards[tmpIndex];
			deckOfCards[tmpIndex] = tmp;
		}
	}
	
	
/**
 * Display all the cards in the deck
 */
	public void displayCards() {
		for (Card card : deckOfCards) {
			if (card != null){
                card.display();
            }
		}
	}
}
