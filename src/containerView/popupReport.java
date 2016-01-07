/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package containerView;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author igorbashka
 */
public class popupReport {
    private final Stage activationStage;
    public popupReport(){
        activationStage = new Stage();
    }
    /**
     * Creates formatted label from specified string 
     * @return formattedLabel 
     */
    private Label setPopupLabel(String message){
        Label formattedLabel = new Label(message);
        formattedLabel.setWrapText(true);
        return formattedLabel;
    }/**
     * Method for creating ok(close) button
     * @return okButton 
     */
    private Button createOkButton(Stage stage){
        Button okBtn = new Button("OK");
        okBtn.setOnAction(new EventHandler <ActionEvent>(){
            public void handle(ActionEvent event){
                stage.close();
            }
        });
        return okBtn;
    } 
    /**
     * Method for creating button for license 
     * activation
     */
    private Button activateBtn(){
        Button activate = new Button("Activate");
        activate.setOnAction(new EventHandler <ActionEvent>(){
            public void handle(ActionEvent event){
                
            } 
        });
        return activate;
    }
    /**
     * Change later for correct appearance
     * @param message
     * @param stage 
     */
    public void createAndShowPopup(String message, Stage stage){
        StackPane popupPane = new StackPane();
        Label report = new Label(message);
        Button okBtn = new Button("Ok");
        okBtn.setOnAction(new EventHandler <ActionEvent>(){
            public void handle(ActionEvent event){
                stage.close();
            }
        });
        popupPane.getChildren().add(report);
        popupPane.getChildren().add(okBtn);
        Scene scene2 = new Scene(popupPane, 300, 250);
        stage.setScene(scene2);
        stage.show();
    }
    /**
     * Constracting license activation warning window
     */
    public void activateWarningWindow(){
        
    }
}
