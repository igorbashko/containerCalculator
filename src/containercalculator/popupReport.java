/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package containercalculator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author igorbashka
 */
public class popupReport {
    
    private static Popup createPopup(String message){
       final Popup popup = new Popup();
       popup.setAutoHide(true);
       Label report = new Label(message);
       Button okBtn = new Button("Ok");
       okBtn.setOnAction(new EventHandler <ActionEvent>(){
        @Override
        public void handle(ActionEvent e){
           popup.hide();
       }
    });
       popup.getContent().add(report);
       popup.getContent().add(okBtn);
       return popup;
    }
    public void showPopup(String message, Stage stage){
        final Popup popup = createPopup(message);
        popup.setOnShown(new EventHandler <WindowEvent>(){

            @Override
            public void handle(WindowEvent event) {
                popup.setX(stage.getX()+stage.getWidth()/2-popup.getWidth()/2);
                popup.setY(stage.getY()+stage.getHeight()/2-popup.getHeight()/2);
            }
            
        });
        popup.show(stage);
    }
}
