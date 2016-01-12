/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package containerView;

import containerController.controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author igorbashka
 */
public class popupReport {
    private controller cont = controller.getController();
    private TextField inputField;
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
        formattedLabel.setFont(Font.font(16));
        formattedLabel.setWrapText(true);
        return formattedLabel;
    }/**
     * Method for creating ok(close) button
     * @return okButton 
     */
    private Button createOkButton(Stage stage){
        Button okBtn = new Button("Закрыть");
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
        Button activate = new Button("Активировать");
        activate.setOnAction(new EventHandler <ActionEvent>(){
            public void handle(ActionEvent event){
            cont.callInputWindow();
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
        Button okBtn = new Button("Закрыть");
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
        Button verifyBtn = new Button("Активировать");
        verifyBtn.setOnAction(new EventHandler <ActionEvent>(){
            public void handle(ActionEvent event){
               cont.seInputKey(inputField.getText());
               cont.verify();
            }
        });
        return verifyBtn;
    }
    /**
     * Success or not message window
     */
    public void successPopup(String message){
        VBox success = formatedVbox();
        Label successLabel = setPopupLabel(message);
        Stage successStage = new Stage();
        successStage.initOwner(inputKeyStage);
        successStage.initModality(Modality.WINDOW_MODAL);
        Button okBtn = createOkButton(successStage);
        success.getChildren().add(successLabel);
        success.getChildren().add(okBtn);
        Scene scene = new Scene(success, 500, 100);
        successStage.setScene(scene);
        successStage.show();
    }
    /**
     * Method for creating input key popup form
     */
    public void inputKeyWindow(){
        VBox inputBox = formatedVbox();
        Label inputLabel = setPopupLabel("Введи активационный ключ, полученный"
                + "по почте");
        inputField = new TextField();
        Button verifyBtn = verifyBtn();
        Button closeBtn = createOkButton(inputKeyStage);
        inputBox.getChildren().add(inputLabel);
        inputBox.getChildren().add(inputField);
        inputBox.getChildren().add(verifyBtn);
        inputBox.getChildren().add(closeBtn);
        Scene scene = new Scene(inputBox, 500, 120);
        inputKeyStage.initOwner(activationStage);
        inputKeyStage.initModality(Modality.WINDOW_MODAL);
        inputKeyStage.setScene(scene);
        inputKeyStage.show();
       }
    /**
     * Constracting license activation warning window
     */
    public void activateWarningWindow(String message){
        VBox warningBox = formatedVbox();
        Label warningLabel = setPopupLabel(message);
        Button inputKeyButton =  inputKeyBtn();
        Button closeBtn = createOkButton(activationStage);
        warningBox.getChildren().add(warningLabel);
        warningBox.getChildren().add(inputKeyButton);
        warningBox.getChildren().add(closeBtn);
        Scene scene = new Scene(warningBox,500,100);
        activationStage.setScene(scene);
        activationStage.show();
    }
    private VBox formatedVbox(){
        VBox box = new VBox();
        box.setSpacing(9);
        box.setAlignment(Pos.CENTER);
        return box;
    }
}
