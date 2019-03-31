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

import java.awt.print.Book;
import java.util.Random;


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
    private static Deck cards;
    private static final int width = 80;
    private static final int height = 110;

    // create player Hand
    private Player human_player;

    // Assign computer Hand
    private Player computer_player;

    @FXML
    void startGame(ActionEvent event) {
        Start();
    }

    /**
     * submits card request from the text box
     * and starts the computer's turn
     * @param event
     */
    @FXML
    void submitCardRequest(ActionEvent event) {
        // determine a winner

        String cardRank = cardRequestBox.getText();
        process_request(cardRank, human_player);

        // renders card in human hand
        renderHands(human_player);
        // checks the hand for books
        Books(human_player.getPlayer_hand());
        renderHands(human_player);
        // add cards to human hand if there is none
        checkHand(human_player.getPlayer_hand());
        renderHands(human_player);
        bookCounter_human.setText(Integer.toString(human_player.getPlayer_hand().getBooks()));


        // after the player's turn finished completely then then its the computer's turn
        checkHand(computer_player.getPlayer_hand());
        renderHands(computer_player);

        System.out.println(human_player.getPlayer_hand().isHandEmpty());
        System.out.println(computer_player.getPlayer_hand().isHandEmpty());

        if (cards.getCardsRemaining() == 0 && human_player.getPlayer_hand().isHandEmpty() && computer_player.getPlayer_hand().isHandEmpty()){
            checkWinner();
        }


        computer_turn();

        checkHand(human_player.getPlayer_hand());
        renderHands(human_player);

        if (cards.getCardsRemaining() == 0 && human_player.getPlayer_hand().isHandEmpty() && computer_player.getPlayer_hand().isHandEmpty()){
            checkWinner();
        }
    }

    @FXML
    void instructions(ActionEvent event){
        Alert.Instructions();
    }

    /**
     * checks for book in the player's hand
     * @param playerHand
     */
    private void Books(Hand playerHand){
        while(playerHand.checkForBook())
            playerHand.checkForBook();

    }

    /**
     * checks if the player's hand is empty
     * @param playerHand
     */
    private void checkHand(Hand playerHand){
        if (playerHand.isHandEmpty()){
            refill_hand(playerHand);
        }
    }


    /**
     * renders to the ui all the cards in the players hands
     * @param player
     */
    private void renderHands(Player player) {

        Hand playerHand = player.getPlayer_hand();

        if (player.getClass() == Human.class){
            HumanPanel.getChildren().clear();
            for (int i = 0; i < playerHand.getHandCounter(); i++) {
                Card cardNode = playerHand.getHand().returnNode(i).getData();
                renderHumanCards(cardNode);
            }
        } else {
            ComputerPanel.getChildren().clear();
            for (int i = 0; i < computer_player.getPlayer_hand().getHandCounter(); i++) {
                renderComputerCards();
            }
        }
    }

    /**
     * Sets up the UI to begin game
     */
    private void Start(){
        cards = new Deck();
        human_player = new Human(createHand());
        computer_player = new Computer(createHand());
        bookCounter_human.setText(Integer.toString(human_player.getPlayer_hand().getBooks()));
        bookCounter_computer.setText(Integer.toString(computer_player.getPlayer_hand().getBooks()));

        // update card remaining counter
        cardRemainingcount.setText(Integer.toString(cards.getCardsRemaining()));
        InstructBtn.setDisable(true);
        startGameBtn.setDisable(true);

        // display the cards in the players hands
        renderHands(human_player);
        renderHands(computer_player);
        // shows alert to inform human player goes first
        Alert.Alert("Turn", "Human go first", "close");

        // enable the button for submit
        submitBtn.setDisable(false);
        cardRequestBox.setDisable(false);
    }


    private void checkWinner(){
        if (computer_player.getPlayer_hand().getBooks() > human_player.getPlayer_hand().getBooks()){
            // computer won
            Alert.Alert("Computer Won", "I won hahaha try again", "Play Again");
            Start();
        } else if (human_player.getPlayer_hand().getBooks() < human_player.getPlayer_hand().getBooks()){
            // human won
            Alert.Alert("You Won", "You beat me nigga well done", "Play Again");
            Start();
        } else {
            // its a draw
            Alert.Alert("Draw", "Looks like its a tie", "Play Again");
            Start();
        }
    }

    private void computer_turn(){
        // disable the button for submit
        submitBtn.setDisable(true);
        cardRequestBox.setDisable(true);

        // in a turn player request card
        String card_to_request = request_card(human_player.getPlayer_hand());

        // display card request
        Alert.Alert("Computer request", "The computer requested a " + card_to_request, "OK");
        process_request(card_to_request, computer_player);

        renderHands(computer_player);
        Books(computer_player.getPlayer_hand());
        renderHands(computer_player);
        checkHand(computer_player.getPlayer_hand());
        renderHands(computer_player);

        // re render the books in computer hand
        bookCounter_computer.setText(Integer.toString(computer_player.getPlayer_hand().getBooks()));
        renderHands(computer_player);

        // enable the button for submit
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


    private void refill_hand(Hand playerHand){
        // hand refill is done only if the hand is empty
        // assume that the hand is empty before being passed in
        if (cards.getCardsRemaining() >= 5){
            // re fill the hand
            for (int i = 0; i < 5; i++) {
                playerHand.addCard(cards.dealCard());
            }
        } else {
            // give the hand what is left
            for (int i = 0; i < cards.getCardsRemaining(); i++) {
                playerHand.addCard(cards.dealCard());
            }
        }
        cardRemainingcount.setText(Integer.toString(cards.getCardsRemaining()));
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


    private void process_request(String rank, Player player){

        if (player.getPlayer_hand().getHand().search(rank)){
            if (player.getClass() == Human.class){
                // search other player hand for the cards and remove it and increment your counter by one
                if (computer_player.getPlayer_hand().getHand().search(rank)) {
                    // if the card is found in the other players hand then the a book is automatically created
                    computer_player.getPlayer_hand().removeCard(rank);
                    human_player.getPlayer_hand().removeCard(rank);
                    human_player.getPlayer_hand().setBooks();

                } else {
                    // player does not have card
                    Alert.Alert("Go Fishing", "", "GO FISH");

                    var card = cards.dealCard();
                    if  (card != null){
                        Alert.Alert("New Card", "You got " + card.getRank() + " of " + card.getSuite() + " from the deck ", "close");
                        human_player.getPlayer_hand().addCard(card);
                    } else {
                        Alert.Alert("Deck empty", "Deck deck is empty", "close");
                    }
                }
            }else{
                if (human_player.getPlayer_hand().getHand().search(rank)){
                    computer_player.getPlayer_hand().removeCard(rank);
                    human_player.getPlayer_hand().removeCard(rank);
                    computer_player.getPlayer_hand().setBooks();
                } else {
                    // player does not have card
                    Alert.Alert("Go Fishing", "", "Computer GO FISH");

                    var card = cards.dealCard();
                    if (card != null){
                        computer_player.getPlayer_hand().addCard(card);
                    }
                }
            }
        }else{
            Alert.CardNotInHand();
            var card = cards.dealCard();
            if (card != null){
                Alert.Alert("New Card", "You got " + card.getRank() + " of " + card.getSuite() + " from the deck ", "close");
                player.getPlayer_hand().addCard(card);
            }
        }
        // number of cards in deck must be re rendered
        cardRemainingcount.setText(Integer.toString(cards.getCardsRemaining()));
    }


    /**
     * checks all the cards in the players hand finds a match and request that card
     * checks all the cards in his hand
     * does this a set amount of times and then messes up a set amount of time
     * for every 2 victory request there are 3 failed
     * @param playerHand
     * @return card to be requested
     */
    private String request_card(Hand playerHand) {
        // loop through each node in the computers hand
        Node tmp_cpu = computer_player.getPlayer_hand().getHand().getHead();

        Random randomCard = new Random();
        // gets a random number for card
        int cardNumber = randomCard.nextInt(computer_player.getPlayer_hand().getHandCounter());


        while (tmp_cpu != null) {
            if (playerHand.getHand().search(tmp_cpu.getData().getRank())){
                // ask for card that returned true
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
