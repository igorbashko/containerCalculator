/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package containerView;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Pop up window to show errors if user inputs wrong items
 * @author igor 
 */
public class errorMessage {
    private String message;
    
    public errorMessage(String message){
        this.message = message;
    }
    public void start(Stage stage){
        //main box with message
        VBox errorBox = new VBox();
        errorBox.minWidthProperty().set(200);
        errorBox.minHeight(100);
        Label errorLabel = new Label(message);
        Button closeBtn = new Button("OK");
        closeBtn.setOnAction(event->{
            stage.close();
        });
        errorBox.getChildren().add(errorLabel);
        errorBox.getChildren().add(closeBtn);
        Scene errorScene = new Scene(errorBox, 200, 100);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(errorScene);
        stage.show();
    }
}
