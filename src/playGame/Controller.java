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
    private static final String[] ranks =  {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

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
        boolean continueGame = false;

        String cardRank = cardRequestBox.getText();

        for (String rank :
                ranks) {
            if (cardRank.equals(rank)){
                continueGame = true;
                break;
            }
        }

        if (continueGame){
            process_request(cardRank, human_player);

            // renders card in human hand
            renderHands(human_player);
            // checks the hand for books
            Books(human_player.getPlayer_hand());
            renderHands(human_player);
            // add cards to human hand if there is none
            checkHand(human_player.getPlayer_hand());
            renderHands(human_player);
            // if the hand was empty and the player got new cards there could be a book in his hand
            Books(human_player.getPlayer_hand());
            renderHands(human_player);

            bookCounter_human.setText(Integer.toString(human_player.getPlayer_hand().getBooks()));

            // after the player's turn finished completely then then its the computer's turn
            checkHand(computer_player.getPlayer_hand());
            renderHands(computer_player);

            // does the check at the end of each turn
            //if both hands and the deck is empty then the game is done
            if (cards.getCardsRemaining() == 0 && human_player.getPlayer_hand().countCards() == 0 && computer_player.getPlayer_hand().countCards() == 0){
                checkWinner();
            }

            computer_turn();

            checkHand(human_player.getPlayer_hand());
            renderHands(human_player);

            //if both hands and the deck is empty then the game is done
            if (cards.getCardsRemaining() == 0 && human_player.getPlayer_hand().countCards() == 0 && computer_player.getPlayer_hand().countCards() == 0){
                checkWinner();
            }
        }else{
            Alert.Alert("Invalid input", "The value entered is invalid as it is not a card", "close");
        }
    }

    @FXML
    void instructions(ActionEvent event){
        Alert.Instructions();
    }

    /**
     * checks for all book in the player's hand
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
        if (playerHand.getHand().isEmpty())
            refill_hand(playerHand);
    }


    /**
     * renders to the ui all the cards in the players hands
     * @param player
     */
    private void renderHands(Player player) {
        Hand playerHand = player.getPlayer_hand();
        if (player.getClass() == Human.class){
            HumanPanel.getChildren().clear();
            for (int i = 0; i < playerHand.countCards(); i++) {
                Card cardNode = playerHand.getHand().dataAtIndex(i).getData();
                renderHumanCards(cardNode);
            }
        } else {
            ComputerPanel.getChildren().clear();
            for (int i = 0; i < computer_player.getPlayer_hand().countCards(); i++) {
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
        } else if (computer_player.getPlayer_hand().getBooks() < human_player.getPlayer_hand().getBooks()){
            // human won
            Alert.Alert("You Won", "You beat me fam well done", "Play Again");
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
        String card_to_request = request_card();

        // display card request
        Alert.Alert("Computer request", "The computer requested a " + card_to_request, "OK");
        process_request(card_to_request, computer_player);

        renderHands(computer_player);
        Books(computer_player.getPlayer_hand());
        renderHands(computer_player);
        checkHand(computer_player.getPlayer_hand());
        renderHands(computer_player);
        Books(computer_player.getPlayer_hand());
        renderHands(computer_player);
        // re render the books in computer hand
        bookCounter_computer.setText(Integer.toString(computer_player.getPlayer_hand().getBooks()));
        renderHands(computer_player);

        // enable the button for submit
        submitBtn.setDisable(false);
        cardRequestBox.setDisable(false);
    }

    /**
     * Creates a Hand with 4 cards and returns it
     * @return new hand creates
     */
    private Hand createHand() {
        Hand new_hand = new Hand();
        // check if the deck is empty else null pointer exception
        for (int i = 0; i < 4; i++) {
            new_hand.addCard(cards.dealCard());
        }
        return new_hand;
    }

    /**
     * refills the hand if the hand runs out but the game is not
     * over
     * @param playerHand
     */
    private void refill_hand(Hand playerHand){
        //if the deck is not empty
        if (!(cards.getCardsRemaining() == 0)){
            if (cards.getCardsRemaining() >= 4){
                // re fill the hand
                for (int i = 0; i < 4; i++) {
                    playerHand.addCard(cards.dealCard());
                }
            } else {
                // give the hand what is left
                for (int i = 0; i < cards.getCardsRemaining(); i++) {
                    playerHand.addCard(cards.dealCard());
                }
            }
        }
        cardRemainingcount.setText(Integer.toString(cards.getCardsRemaining()));
    }

    /**
     * Displays the human cards with card information
     * @param card
     */
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

    /**
     * Displays computer cards without card information
     */
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

    /**
     * processes the card requested
     * If the player requests a card not in their hand then they will lose their turn
     * If the card requested is in the other player hand they automatically they automatically get a book and the card is remove from the deck
     * If the card requested is not in the other players hand then they get a card from the deck no books given
     * @param rank
     * @param player
     */
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
        }
        // number of cards in deck must be re rendered
        cardRemainingcount.setText(Integer.toString(cards.getCardsRemaining()));
    }


    /**
     * algorithm used for computer to choose the card it wants to request
     * @return the rank of the card it is requesting
     */
    private String request_card() {

        Random randomCard = new Random();
        // gets a random number for card

        // add error handling here because
        int cardNumber = 0;
        try{
            cardNumber = randomCard.nextInt(computer_player.getPlayer_hand().countCards());
        }catch (Exception e){
            Alert.Alert("Error", "The game ran into an error it needs to restart", "Restart");
            Start();
        }

        Node cardNode = computer_player.getPlayer_hand().getHand().dataAtIndex(cardNumber);

        return cardNode.getData().getRank();
    }
}
