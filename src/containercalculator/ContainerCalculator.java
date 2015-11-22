/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package containercalculator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import containerMath.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
/**
 *
 * @author igorbashka
 */
public class ContainerCalculator extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
       grid.gridLinesVisibleProperty().setValue(Boolean.TRUE);
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));
        //creating and adding heaging
        Text heading = new Text("Расчет оптимальной загрузки контейнера");
        heading.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(heading, 0, 0, 2, 1);
        //creating and adding file load label
        Label loginLabel = new Label("Выберит исходный файл");
        grid.add(loginLabel, 0, 1);
        //creating and adding file upload field
        TextField pathToSource = new TextField();
        grid.add(pathToSource, 1, 1);
        //Creating and adding rows of items label and field
        Label ItemsLabel = new Label("");
        grid.add(ItemsLabel, 0, 2);
        TextField ItemsField = new TextField();
        grid.add(ItemsField, 1, 2);
        //Creating and adding number of items label and field
        Label NILabel = new Label("");
        grid.add(NILabel, 0, 3);
        TextField NIField= new TextField();
        grid.add(NIField, 1, 3);
        /*
        Button btn = new Button();
        
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        */
        //Scene scene = new Scene(root, 300, 250);
        Scene scene = new Scene(grid, 500, 500);
       // primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        /*
        containerMath container = new containerMath();
        container.readFromExcel();
        container.setWeightCapacity(28000);
        container.setVolumeCapacity(27);
        container.setFourty(56);
        container.getNumberOfContainers();
        container.findContainers();
       //container.findClosest();
        container.sortItems();
        container.printUnsorted();
        System.out.println(container.printSumVolume());
                */
    }
    
}
