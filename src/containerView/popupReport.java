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
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author igorbashka
 */
public class popupReport {
    private static Stage stage;
    
    private static Popup createPopup(String message){
       final Popup popup = new Popup();
       //popup.setAutoHide(true);
       Label report = new Label(message);
       Button okBtn = new Button("Ok");
       okBtn.setOnAction(new EventHandler <ActionEvent>(){
        @Override
        public void handle(ActionEvent e){
           stage.close();
       }
    });
       popup.getContent().add(report);
       popup.getContent().add(okBtn);
       return popup;
    }
    public void showPopup(String message, Stage stage2){
        stage = stage2;
        final Popup popup = createPopup(message);
        StackPane root = new StackPane();
    //    root.getChildren().add(popup);
      // Scene scene2 = new Scene(root, 300, 250);
       //stage.setScene(scene2);
      //*/
        popup.setOnShown(new EventHandler <WindowEvent>(){

            @Override
            public void handle(WindowEvent event) {
                popup.setX(stage.getX()+stage.getWidth()/2-popup.getWidth()/2);
                popup.setY(stage.getY()+stage.getHeight()/2-popup.getHeight()/2);
            }
            
        });
        stage.show();
        popup.show(stage);
    }
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
}
