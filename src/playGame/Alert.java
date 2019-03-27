package playGame;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;


public class Alert {


    public static void CardNotInHand(){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle("Card not in your hand");

        window.setMinWidth(300);


        Label alert_message = new Label();
        alert_message.setText("You asked for a card that is not in your hand");

        Label alert_message_penalty = new Label();
        alert_message_penalty.setText("PENALTY: Card added to your hand");


        Button close = new Button("Accept Penalty");
        close.setOnAction(e -> window.close());


        VBox layout = new VBox(10);
        layout.getChildren().addAll(alert_message, alert_message_penalty ,close);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 500, 300);
        window.setScene(scene);
        window.showAndWait();
    }

    public static void Alert(String alert_title, String alert_msg, String btnText){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(alert_title);
        window.setMinWidth(300);


        Label alert_message = new Label();
        alert_message.setText(alert_msg);

        Button close = new Button(btnText);
        close.setOnAction(e -> window.close());


        VBox layout = new VBox(10);
        layout.getChildren().addAll(alert_message, close);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 200);
        window.setScene(scene);
        window.showAndWait();
    }


    // do instructions as a alert box
    public static void Instructions(){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Instructions");
        window.setMinWidth(300);

        Label Title = new Label();
        Title.setText("How to Play");
        Title.setFont(new Font(24));
        Title.setTextAlignment(TextAlignment.CENTER);

        Label alert_message = new Label();
        alert_message.setText("The player will have the opportunity to play Go Fish against the Computer. Each player will aim to get pairs (a pair consists of two cards of the same number eg. two seven’s (7) ) of cards either by asking the other player if a card they currently have is in the other player’s hand. If the card is in the other players hand, the player who asked will collect both cards and add the pair to their personal deck before asking the other player for another card. If the other player did not have the card requested the player must take a card from the top of the deck and place in their hand. If the card (takenfrom the deck) is the same card that the player just requested from the opponent, the player may add the pair of cards to their personal deck and ask the other player for another card. If the player has 2 cards which could make a pair in their hand, they may add this pair to their deck also.\n" +
                "\n" +
                "A player’s turn ends when the card they asked for is not in the other player’s hand and they did not draw the same card number from the deck. Whenever a player’s hand is empty, they are allowed to draw four(4) additional cards from the deck until it is empty.\n" +
                "\n" +
                "The computer AI should not simply keep requesting the first card in its hand but should choose randomly between the options in its hand.\n" +
                "\n" +
                "The player with the most personal sets of pairs (largest deck) at the end of game wins. When a game ends the user should be prompted if they would like to play again.");

        alert_message.setWrapText(true);
        alert_message.setMaxWidth(750);
        Button close = new Button("Let's Play");
        close.setOnAction(e -> window.close());


        VBox layout = new VBox(10);
        layout.getChildren().addAll(Title, alert_message, close);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 800, 400);
        window.setScene(scene);
        window.showAndWait();
    }

}
