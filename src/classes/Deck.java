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
		for (String suit: SUITS) {
//			loops through rank for each suit
			for (String rank: RANKS) {
				deckOfCards[cardIndexController] = new Card(suit, rank);
				cardIndexController++;
			}
		}

		Shuffle();
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

    /**
     * removes the last index in the array and returns it changes the Big o to constant from linear when removing from top
      * @param index
     * @return tmp the card
     */
	private Card remove(int index) {
		if (cardsRemaining != 0){
			Card tmp = deckOfCards[index];
			deckOfCards[index] = null;
			cardsRemaining--;
			return tmp;
		}
		return null;
	}
	

	/*
	 * Shuffles the deck of cards
	 */
	private void Shuffle() {
		Random randomCardIndex = new Random();
		
		for (int i = 0; i < deckOfCards.length; i++) {
			
			// generates a random number between 0 and the size of the deck 52
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
