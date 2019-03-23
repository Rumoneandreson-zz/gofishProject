package classes;

public class Player {

    private Hand player_hand;
    private int cards_left;

    /**
     * prinary constructor
     * @param player_hand
     */
    public Player(Hand player_hand) {
        this.player_hand = player_hand;
        this.cards_left = 5;
    }


    public Player(){this(null);}

    public Hand getPlayer_hand() {
        return player_hand;
    }
}
