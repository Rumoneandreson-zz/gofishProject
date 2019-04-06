
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

//  default constructor
    public Player(){this(null);}
//  gets the player hand
    public Hand getPlayer_hand() {
        return player_hand;
    }
}
