package Application;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Label;
public class AlertBox {
    public static void displayAlert(String title, String message) {
        Stage window = new Stage();
        Label label = new Label(message);
        Button yes = new Button("Yes");
        Button no  = new Button("No");
        //controller
        yes.setOnAction(_ -> Platform.exit());
        no.setOnAction(_ -> window.close());
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, yes, no);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);

        window.setScene(scene);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setResizable(false);
        window.setMinHeight(200);
        window.setMinWidth(300);
        window.showAndWait();

    }
}
