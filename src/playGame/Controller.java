package playGame;

import classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


public class Controller {
    @FXML
    private HBox HumanPanel;

    @FXML
    private HBox ComputerPanel;

    @FXML
    private TextField cardRequestBox;

    @FXML
    private Button submitBtn;

    @FXML
    private Label cardRemainingtext;

    @FXML
    private Label cardRemainingcount;

    @FXML
    private Label determine_Turn;

    @FXML
    private Label bookCounter_computer;

    @FXML
    private Button startGameBtn;

    @FXML
    private Label bookCounter_human;


// initializes deck
    private static final Deck cards = new Deck();
    private static final int width = 80;
    private static final int height = 110;

    // create player Hand
    private Player human_player = new Human(createHand());

    // Assign computer Hand
    private Player computer_player = new Computer(createHand());

    @FXML
    void startGame(ActionEvent event) {


        // update card remaining counter
        cardRemainingcount.setText(Integer.toString(cards.getCardsRemaining()));


        // computer continues game
        // Computer AI Exist in its class

        // check for book
        human_player.getPlayer_hand().checkforBook();
        computer_player.getPlayer_hand().checkforBook();

        // displays all the cards in play
        displayHumanCards();
        displayComputerCards();

        System.out.println("\nHuman Cards");
        human_player.getPlayer_hand().showCards();

        System.out.println("\nComputer Cards");
        computer_player.getPlayer_hand().showCards();



    }

    @FXML
    void submitCardRequest(ActionEvent event) {
        // player requests a card

        // search for card in computer's hand

        // if card is there return card
        // set returned card to null
        // set card in player hand to null
        // increment book counter


        // if the card is not in player hand draw from deck add to the list and check for book


        // after all this computers turn

        // computer completes turn and the action of requesting a card will need to happen again

    }

    private Hand createHand(){
        Hand new_hand = new Hand();
        for (int i = 0; i < 5; i++) {
            new_hand.addCard(cards.dealCard());
        }
        return new_hand;
    }


    private void displayHumanCards(){
        System.out.println("\nHuman Cards");
        human_player.getPlayer_hand().showCards();

        for (int i = 0; i < human_player.getPlayer_hand().getHandCounter(); i++) {
            Card cardNode = human_player.getPlayer_hand().getHand().returnNode(i).getData();
            renderHumanCards(cardNode);
        }
    }

    private void displayComputerCards(){
        System.out.println("\nComputer Cards");
        computer_player.getPlayer_hand().showCards();

        for (int i = 0; i < computer_player.getPlayer_hand().getHandCounter(); i++) {
            Card cardNode = computer_player.getPlayer_hand().getHand().returnNode(i).getData();
            renderComputerCards(cardNode);
        }
    }

    private void renderHumanCards(Card card){
        Rectangle bg = new Rectangle(width, height);
        bg.setArcHeight(5);
        bg.setArcWidth(5);
        bg.setFill(Color.WHITE);

        Text card_face = new Text(card.getRank() + " of " + card.getSuite());
        card_face.setWrappingWidth(75);
        card_face.setTextAlignment(TextAlignment.CENTER);
        StackPane cardStack = new StackPane();
        cardStack.getChildren().addAll(bg, card_face);

        cardStack.setPadding(new Insets(10, 10, 10,10));
        HumanPanel.getChildren().addAll(cardStack);
    }

    private void renderComputerCards(Card card){
        Rectangle bg = new Rectangle(width, height);
        bg.setArcHeight(5);
        bg.setArcWidth(5);
        bg.setFill(Color.BLUE);
        StackPane cardStack = new StackPane();
        cardStack.getChildren().addAll(bg);

        cardStack.setPadding(new Insets(10, 10, 10,10));
        ComputerPanel.getChildren().addAll(cardStack);
    }
}
