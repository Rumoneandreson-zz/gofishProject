package classes;

public class Card{

//	properties of the card
	private String suite;
	private String rank;
	
/**
 * Primary Constructors
 * @param suite
 * @param rank
 */
	public Card(String suite, String rank) {
		this.suite = suite;
		this.rank = rank;
	}
	
/**
 * Default Constructor
 */
	public Card() {this("", "");}
	
	
/**
 * Copy Constructor
 */
	public Card(Card _card) {
		this(_card.suite, _card.rank);
	}
	
	
	@Override
	public String toString() {
		return "Card [suite=" + suite + ", rank=" + rank + "]";
	}
	
/**
 * displays card information
 */
	public void display() {
		System.out.println(toString());
	}
}
