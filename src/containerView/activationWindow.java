/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package containerView;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author igor
 */
public class activationWindow {
    private Stage stage;
    private TextField keyField = new TextField();
     /**
     * Method to create activation window
     * @param stage  
     * runs window to activate the software
     */
      private void createWindow(Stage stage){
      this.stage = stage;
      StackPane frame = new StackPane();
     
//Button to run verification process
    }
      private Button activateButton(){
          Button verifyBtn = new Button("Activate");
          verifyBtn.setOnAction(new EventHandler <ActionEvent>(){
          public void handle(ActionEvent event){
              
          }
      });
          return verifyBtn;
      }
    //Get the key from input field  
      private String getInput(){
          return keyField.getText();
      }
}
