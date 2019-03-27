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

import java.util.Random;
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
    private Label bookCounter_computer;

    @FXML
    private Button startGameBtn;

    @FXML
    private Label bookCounter_human;

    @FXML
    private Button InstructBtn;


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
        InstructBtn.setDisable(true);



        // renders original cards
        for (int i = 0; i < human_player.getPlayer_hand().getHandCounter(); i++) {
            Card cardNode = human_player.getPlayer_hand().getHand().returnNode(i).getData();
            renderHumanCards(cardNode);
        }

        for (int i = 0; i < computer_player.getPlayer_hand().getHandCounter(); i++) {
            renderComputerCards();
        }

        //TODO timeout


        // debug
//        computer_player.getPlayer_hand().showCards();

        if (human_player.getPlayer_hand().checkforBook())
            human_player.getPlayer_hand().checkforBook();

//        if (computer_player.getPlayer_hand().checkforBook())
//            computer_player.getPlayer_hand().checkforBook();


        // displays all the cards in play
        bookCounter_human.setText(Integer.toString(human_player.getPlayer_hand().getBooks()));
        HumanPanel.getChildren().clear();
        for (int i = 0; i < human_player.getPlayer_hand().getHandCounter(); i++) {
            Card cardNode = human_player.getPlayer_hand().getHand().returnNode(i).getData();
            renderHumanCards(cardNode);
        }

//        bookCounter_computer.setText(Integer.toString(computer_player.getPlayer_hand().getBooks()));
//        ComputerPanel.getChildren().clear();
//        for (int i = 0; i < computer_player.getPlayer_hand().getHandCounter(); i++) {
//            renderComputerCards();
//        }

        Alert.Alert("Turn", "Human go first", "close");

//          enable the button for submit
        submitBtn.setDisable(false);
        cardRequestBox.setDisable(false);
    }

    @FXML
    void submitCardRequest(ActionEvent event) {
        // player requests a card
        String cardRank = cardRequestBox.getText();

        process_request(cardRank, human_player);

        if (human_player.getPlayer_hand().getHandCounter() == 0){
            human_player.setPlayer_hand(createHand());
        }


        // in case there is a book after player had to go fish
        if (human_player.getPlayer_hand().checkforBook()) {
            human_player.getPlayer_hand().checkforBook();
        }

        // after all this computers turn
        // change to represent computer's turn
        System.out.println("Computer's Turn");

//        clear text box
        cardRequestBox.setText("");



//        updates gui
        bookCounter_human.setText(Integer.toString(human_player.getPlayer_hand().getBooks()));
        cardRemainingcount.setText(Integer.toString(cards.getCardsRemaining()));

        HumanPanel.getChildren().clear();
        for (int i = 0; i < human_player.getPlayer_hand().getHandCounter(); i++) {
            Card cardNode = human_player.getPlayer_hand().getHand().returnNode(i).getData();
            renderHumanCards(cardNode);
        }

        Alert.Alert("Turn", "Computer's Turn", "OK");
        computer_turn();

//        ComputerPanel.getChildren().clear();
//        for (int i = 0; i < computer_player.getPlayer_hand().getHandCounter(); i++) {
//            renderComputerCards();
//        }

        Alert.Alert("Turn", "Your Turn", "OK");
    }

    @FXML
    void instructions(ActionEvent event){
        Alert.Instructions();
    }


    private void computer_turn(){
        // in a turn player request card
        String card_requested = request_card(human_player.getPlayer_hand());


//          enable the button for submit
        submitBtn.setDisable(true);
        cardRequestBox.setDisable(true);

//        display card request
        Alert.Alert("Computer request", "The computer requested a " + card_requested, "OK");
        process_request(card_requested, computer_player);

        // check if computer hand is empty
        if (computer_player.getPlayer_hand().getHandCounter() == 0){
            for (int i = 0; i < 5; i++) {
                computer_player.getPlayer_hand().addCard(cards.dealCard());
            }
        }

        // check for a book
        if (computer_player.getPlayer_hand().checkforBook()){
            computer_player.getPlayer_hand().checkforBook();
        }

        // re render the books in computer hand
        bookCounter_computer.setText(Integer.toString(computer_player.getPlayer_hand().getBooks()));
        //Back to human player's turn
        // re enable the submit button on the GUI

        ComputerPanel.getChildren().clear();
        for (int i = 0; i < computer_player.getPlayer_hand().getHandCounter(); i++) {
            renderComputerCards();
        }

        System.out.println("Human's Turn");

//          enable the button for submit
        submitBtn.setDisable(false);
        cardRequestBox.setDisable(false);

    }

    private Hand createHand() {
        Hand new_hand = new Hand();
        // check if the deck is empty else null pointer exception

        for (int i = 0; i < 5; i++) {
            new_hand.addCard(cards.dealCard());
        }
        return new_hand;
    }

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
//                    computer_player.getPlayer_hand().showCards();
                    human_player.getPlayer_hand().setBooks();
                } else {
//                    player does not have card
                    Alert.Alert("Go Fishing", "", "GO FISH");

                    // go fish add card to the player hand
                    // re render
                    var card = cards.dealCard();
                    Alert.Alert("New Card", "You got " + card.getRank() + " of " + card.getSuite() + " from the deck ", "close");
                    human_player.getPlayer_hand().addCard(card);
                }
            }else{
                if (human_player.getPlayer_hand().getHand().search(rank)){

                    computer_player.getPlayer_hand().removeCard(rank);
                    human_player.getPlayer_hand().removeCard(rank);

                    // re render human hand
                    HumanPanel.getChildren().clear();
                    for (int i = 0; i < human_player.getPlayer_hand().getHandCounter(); i++) {
                        Card cardNode = human_player.getPlayer_hand().getHand().returnNode(i).getData();
                        renderHumanCards(cardNode);
                    }
                    //debug
//                    computer_player.getPlayer_hand().showCards();
                    computer_player.getPlayer_hand().setBooks();

                } else {
                    // player does not have card
                    Alert.Alert("Go Fishing", "", "Computer GO FISH");
                    // go fish add card to the player hand
                    // re render
                    var card = cards.dealCard();
//                    alert card recieved
                    computer_player.getPlayer_hand().addCard(card);
                }
            }
        }else{
            // handle card not being
            System.out.println("card is not in your hand");
            Alert.CardNotInHand();
            var card = cards.dealCard();
            playerhand.getPlayer_hand().addCard(card);
        }
    }


//HOW
//checks all the cards in the players hand finds a match and request that card
//checks all the cards in his hand
//does this a set amount of times and then messes up a set amount of time
//for every 2 victory request there are 3 failed
    private String request_card(Hand playerHand) {
//        loop through each node in the computers hand
        Node tmp_cpu = computer_player.getPlayer_hand().getHand().getHead();

        Random randomCard = new Random();
//        gets a random number for card
        int cardNumber = randomCard.nextInt(computer_player.getPlayer_hand().getHandCounter());


        while (tmp_cpu != null) {
            if (playerHand.getHand().search(tmp_cpu.getData().getRank())){
//                ask for card that returned true
                return tmp_cpu.getData().getRank();
            }
            if (cardNumber == 0){
                break;
            }

            cardNumber--;
            tmp_cpu = tmp_cpu.getNext();
        }
        return tmp_cpu.getData().getRank();
    }
}
