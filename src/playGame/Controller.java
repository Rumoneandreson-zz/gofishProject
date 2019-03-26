package playGame;

import classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.Timer;
import java.util.concurrent.TimeUnit;


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
        bookCounter_human.setText(Integer.toString(human_player.getPlayer_hand().getBooks()));
        bookCounter_computer.setText(Integer.toString(computer_player.getPlayer_hand().getBooks()));

        // renders original cards

        HumanPanel.getChildren().clear();
        for (int i = 0; i < human_player.getPlayer_hand().getHandCounter(); i++) {
            Card cardNode = human_player.getPlayer_hand().getHand().returnNode(i).getData();
            renderHumanCards(cardNode);
        }

        ComputerPanel.getChildren().clear();
        for (int i = 0; i < computer_player.getPlayer_hand().getHandCounter(); i++) {
            renderComputerCards();
        }


        //TODO timeout

        computer_player.getPlayer_hand().showCards();

        /**
         * At the start of the game any player can get 2 books
         * max this will check if there is a book and if one is found
         * check for another
         */
        if (human_player.getPlayer_hand().checkforBook())
            human_player.getPlayer_hand().checkforBook();

        if (computer_player.getPlayer_hand().checkforBook())
            computer_player.getPlayer_hand().checkforBook();

        // check for book





        bookCounter_human.setText(Integer.toString(human_player.getPlayer_hand().getBooks()));
        HumanPanel.getChildren().clear();
        for (int i = 0; i < human_player.getPlayer_hand().getHandCounter(); i++) {
            Card cardNode = human_player.getPlayer_hand().getHand().returnNode(i).getData();
            renderHumanCards(cardNode);
        }


        bookCounter_computer.setText(Integer.toString(computer_player.getPlayer_hand().getBooks()));
        ComputerPanel.getChildren().clear();
        for (int i = 0; i < computer_player.getPlayer_hand().getHandCounter(); i++) {
            renderComputerCards();
        }


        // displays all the cards in play

    }

    @FXML
    void submitCardRequest(ActionEvent event) {
        // player requests a card
        String cardRank = cardRequestBox.getText();

        process_request(cardRank, human_player);


        // in case there is a book after player had to go fish
        if (human_player.getPlayer_hand().checkforBook()) {
            human_player.getPlayer_hand().checkforBook();
        }


        bookCounter_human.setText(Integer.toString(human_player.getPlayer_hand().getBooks()));
        cardRemainingcount.setText(Integer.toString(cards.getCardsRemaining()));

        ComputerPanel.getChildren().clear();
        for (int i = 0; i < computer_player.getPlayer_hand().getHandCounter(); i++) {
            renderComputerCards();
        }

        HumanPanel.getChildren().clear();
        for (int i = 0; i < human_player.getPlayer_hand().getHandCounter(); i++) {
            Card cardNode = human_player.getPlayer_hand().getHand().returnNode(i).getData();
            renderHumanCards(cardNode);
        }
        // search for card in computer's hand

        // if card is there return card
        // set returned card to null
        // set card in player hand to null
        // increment book counter


        // if the card is not in player hand draw from deck add to the list and check for book


        // after all this computers turn

        // computer completes turn and the action of requesting a card will need to happen again

    }

    private Hand createHand() {
        Hand new_hand = new Hand();
        for (int i = 0; i < 5; i++) {
            new_hand.addCard(cards.dealCard());
        }
        return new_hand;
    }


    //TODO look into why these methods dont work
//    private void displayHumanCards() {
//        HumanPanel.getChildren().clear();
//        for (int i = 0; i < human_player.getPlayer_hand().getHandCounter(); i++) {
//            Card cardNode = human_player.getPlayer_hand().getHand().returnNode(i).getData();
//            renderHumanCards(cardNode);
//        }
//    }
//
//    private void displayComputerCards() {
//        ComputerPanel.getChildren().clear();
//        for (int i = 0; i < computer_player.getPlayer_hand().getHandCounter(); i++) {
//            renderComputerCards();
//        }
//    }

    private void renderHumanCards(Card card) {

        Rectangle bg = new Rectangle(width, height);
        bg.setArcHeight(5);
        bg.setArcWidth(5);
        bg.setFill(Color.WHITE);

        Text card_face = new Text(card.getRank() + " of " + card.getSuite());
        card_face.setWrappingWidth(75);
        card_face.setTextAlignment(TextAlignment.CENTER);
        StackPane cardStack = new StackPane();
        cardStack.getChildren().addAll(bg, card_face);

        cardStack.setPadding(new Insets(10, 10, 10, 10));
        HumanPanel.getChildren().addAll(cardStack);
    }

    private void renderComputerCards() {

        Rectangle bg = new Rectangle(width, height);
        bg.setArcHeight(5);
        bg.setArcWidth(5);
        bg.setFill(Color.BURLYWOOD);
        StackPane cardStack = new StackPane();
        cardStack.getChildren().addAll(bg);

        cardStack.setPadding(new Insets(10, 10, 10, 10));
        ComputerPanel.getChildren().addAll(cardStack);
    }


    private void process_request(String rank, Player playerhand){
        //TODO fix error in checking rank

        if (playerhand.getPlayer_hand().getHand().search(rank)){
            if (playerhand.getClass() == Human.class){
                // search other player hand for the cards and remove it and increment your counter by one
                if (computer_player.getPlayer_hand().getHand().search(rank)) {

                    computer_player.getPlayer_hand().removeCard(rank);
                    human_player.getPlayer_hand().removeCard(rank);

                    //debug
                    computer_player.getPlayer_hand().showCards();
                    human_player.getPlayer_hand().setBooks();
                } else {
//                    player does not have card
                    System.out.println("Player does not have card go fish");

                    // go fish add card to the player hand
                    // re render
                    human_player.getPlayer_hand().addCard(cards.dealCard());
                }
            }else{
                //computer request tasks
            }
        }else{
            // handle card not being
            System.out.println("card is not in your hand");
        }
    }
}
