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
import java.io.File;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import javafx.stage.FileChooser;
/**
 *
 * @author igorbashka
 */
public class ContainerCalculator extends Application {
    
    private containerMath containerCalc = new containerMath();
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
        //creating and adding file load label, filed and upload button
        Label loginLabel = new Label("Choose the source file");
        grid.add(loginLabel, 0, 1);
        TextField pathToSource = new TextField();
        grid.add(pathToSource, 1, 1);
        HBox uploadBtnH = new HBox();
        uploadBtnH.setAlignment(Pos.BOTTOM_RIGHT);
        Button uploadBtn = new Button("Upload File");
        uploadBtnH.getChildren().add(uploadBtn);
        grid.add(uploadBtnH, 2, 1);
        FileChooser chooseFile = new FileChooser();
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
        //Cresting and adding number of items in pack label, field and checkBox
         Label ItemsInPackLabel = new Label("");
        grid.add(ItemsInPackLabel, 0, 4);
        TextField ItemsInPackField = new TextField();
        grid.add(ItemsInPackField, 1, 4);
        CheckBox ItemsInPackcb = new CheckBox("Don,t use");
        grid.add(ItemsInPackcb, 2, 4);
        //Creating and adding number of packs label and field
        Label NofPacksLabel = new Label("Sign number of Packs, column");
        grid.add(NofPacksLabel, 0, 5);
        TextField NofPacksField= new TextField();
        grid.add(NofPacksField, 1, 5);
        CheckBox NofPackscb = new CheckBox("Don't use");
        grid.add(NofPackscb, 2, 5);
        //Creating and adding net weight label and field
        Label netWeightLabel  = new Label("Choose net weight column");
        TextField netWeightField = new TextField();
        CheckBox netWeightcb = new CheckBox("Don't use");
        grid.add(netWeightLabel, 0, 6);
        grid.add(netWeightField, 1, 6);
        grid.add(netWeightcb, 2, 6);
        //Creating and adding sum of net Weight
        Label sumNwLabel = new Label("choose sum Net Weight column");
        TextField sumNwField = new TextField();
        CheckBox sumNwcb = new CheckBox("Don't use");
        grid.add(sumNwLabel, 0, 7);
        grid.add(sumNwField, 1, 7);
        grid.add(sumNwcb, 2, 7);
        //Creating and adding gross weight of pack column
        Label grossWeightOfPackLabel = new Label("Sign gross weight, column");
        TextField grossWeightOfPackField = new TextField();
        CheckBox grossWeightOfPackcb = new CheckBox("Don't use");
        grid.add(grossWeightOfPackLabel, 0, 8);
        grid.add(grossWeightOfPackField, 1, 8);
        grid.add(grossWeightOfPackcb, 2, 8);
        //Creating and adding gross weight column
        Label sumGwLabel = new Label("Choose gross weight column");
        TextField sumGwField = new TextField();
        grid.add(sumGwLabel, 0, 9);
        grid.add(sumGwField, 1, 9);
        //Creating and adding volume of pack 
        Label volumeOfPackLabel = new Label("Choose volume of pack column");
        TextField volumeOfPackField = new TextField();
        CheckBox volumeOfPackcb = new CheckBox("Don't use it");
        grid.add(volumeOfPackLabel, 0, 10);
        grid.add(volumeOfPackField, 1, 10);
        grid.add(volumeOfPackcb, 2, 10);
        //Creating and adding sum volume if items credentials
        Label sumVolumeLabel = new Label("Choose volume of packs of an item, column");
        TextField sumVolumeField = new TextField();
        grid.add(sumVolumeLabel, 0, 11);
        grid.add(sumVolumeField, 1, 11);
        //Creating and setting calculate button
         Button btn = new Button();
        btn.setText("Calculate");
        HBox calculateBtnH = new HBox();
        calculateBtnH.getChildren().add(btn);
        calculateBtnH.setAlignment(Pos.BOTTOM_RIGHT);
        grid.add(calculateBtnH, 2, 12);
        //Actions on buttons
        //Choose source file button
        uploadBtn.setOnAction(new EventHandler <ActionEvent>(){
            @Override
            public void handle(ActionEvent event ){
                File selectedFile = chooseFile.showOpenDialog(primaryStage);
                if(selectedFile != null){
                    pathToSource.setText(openFile(selectedFile).getAbsolutePath());
                }
            }
        });
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        /*
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        */
        //Scene scene = new Scene(root, 300, 250);
        Scene scene = new Scene(grid, 500, 500);
       // primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /*Method for accessing file through the file dialog*/
private File openFile(File file){
    return file;
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
    
 private void showReport(){
    String message = "containers loaded: "; 
          //  containerCalc.getNumberOfContainers().;
            
   }
         
}
