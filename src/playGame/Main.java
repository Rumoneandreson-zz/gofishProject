package playGame;

import classes.Deck;
import classes.Hand;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main {

//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("playGame.fxml"));
//        primaryStage.setTitle("Go Fish");
//        primaryStage.setScene(new Scene(root, 1000, 600));
//        primaryStage.setResizable(false);
//        primaryStage.show();
//    }


    public static void main(String[] args) {
//        launch(args);

        Deck cards = new Deck();

//        cards.Shuffle();

        Hand test_hand = new Hand();

        for (int i = 0; i < 5; i ++){
            test_hand.addCard(cards.dealCard());
        }

        test_hand.removeCard("King");

        test_hand.showCards();

        System.out.println("\n\n");
        cards.displayCards();
        System.out.println(cards.getCardsRemaining());
    }
}
