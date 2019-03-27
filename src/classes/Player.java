package classes;

public class Player {

    private Hand player_hand;
    /**
     * prinary constructor
     * @param player_hand
     */
    public Player(Hand player_hand) {
        this.player_hand = player_hand;
    }


    public Player(){this(null);}

    public Hand getPlayer_hand() {
        return player_hand;
    }

    public void setPlayer_hand(Hand player_hand) {
        this.player_hand = player_hand;
    }
}
