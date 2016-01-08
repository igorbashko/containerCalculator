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
import javafx.scene.control.TextField;
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
    private final Stage inputKeyStage;
    
    public popupReport(){
        activationStage = new Stage();
        inputKeyStage = new Stage();
        activationStage.initModality(Modality.APPLICATION_MODAL);
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
    private Button inputKeyBtn(){
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
    private Button verifyBtn(){
        Button verifyBtn = new Button("Activate");
        verifyBtn.setOnAction(new EventHandler <ActionEvent>(){
            public void handle(ActionEvent event){
               //write verification method in the controller
               //call it from here
            }
        });
        return verifyBtn;
    }
    /**
     * Success or not message window
     */
    public void successPopup(String message){
        StackPane success = new StackPane();
        Label successLabel = setPopupLabel(message);
        Stage successStage = new Stage();
        successStage.initOwner(inputKeyStage);
        successStage.initModality(Modality.WINDOW_MODAL);
        Button okBtn = createOkButton(successStage);
        success.getChildren().add(successLabel);
        success.getChildren().add(okBtn);
        Scene scene = new Scene(success, 300, 100);
        successStage.setScene(scene);
        successStage.show();
    }
    /**
     * Method for creating input key popup form
     */
    public void inputKeyWindow(){
      //  Stage inputKeyStage = new Stage();
        StackPane inputPane = new StackPane();
        TextField inputField = new TextField();
        Button verifyBtn = verifyBtn();
        inputPane.getChildren().add(inputField);
        inputPane.getChildren().add(verifyBtn);
        Scene scene = new Scene(inputPane);
        inputKeyStage.initOwner(activationStage);
        inputKeyStage.initModality(Modality.WINDOW_MODAL);
        inputKeyStage.setScene(scene);
        inputKeyStage.show();
       }
    /**
     * Constracting license activation warning window
     */
    public void activateWarningWindow(String message){
        StackPane warningPane = new StackPane();
        Label warningLabel = setPopupLabel(message);
        Button inputKeyButton =  inputKeyBtn();
        Button closeBtn = createOkButton(activationStage);
        warningPane.getChildren().add(warningLabel);
        warningPane.getChildren().add(inputKeyButton);
        warningPane.getChildren().add(closeBtn);
        Scene scene = new Scene(warningPane, 300, 200);
        activationStage.setScene(scene);
        activationStage.show();
    }
}
