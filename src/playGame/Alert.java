package playGame;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
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

}
