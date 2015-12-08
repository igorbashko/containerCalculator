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
import javafx.stage.Modality;
import containerController.controller;
/**
 *
 * @author igorbashka
 */
public class ContainerCalculator extends Application {
    
    private containerMath containerCalc = new containerMath();//container insatance
    private controller cont = controller.getController();
    private TextField ItemsField;
    private TextField NIField;
    private TextField ItemsInPackField;
    private TextField NofPacksField;
    private TextField netWeightField;
    private TextField sumNwField;
    private TextField grossWeightField;
    private TextField sumGwField;
    private TextField volumeOfPackField;
    private TextField sumVolumeField;
    private TextField namesField;
    private String names;
    private String rowsRange;
    private String itemsNumber;
    private String inPack;
    private String numberOfPacks;
    private String netWeight;
    private String sumNetWeight;
    private String grossWeight;
    private String sumGrossWeight;
    private String volumeOfPack;
    private String sumVolume;
    
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
        Label ItemsLabel = new Label("Choose rows with items, rows");
        grid.add(ItemsLabel, 0, 2);
        ItemsField = new TextField();
        grid.add(ItemsField, 1, 2);
        //Creating and adding column with names if items
        Label nameLabel = new Label("Write the column with names of items");
        namesField = new TextField();
        grid.add(nameLabel, 0, 3);
        grid.add(namesField, 1, 3);
        //Creating and adding number of items label and field
        Label NILabel = new Label("Number of items, column");
        grid.add(NILabel, 0, 4);
        NIField= new TextField();
        NIField.setText("123 vnhO,1,3  .");
        grid.add(NIField, 1, 4);
        //Cresting and adding number of items in pack label, field and checkBox
         Label ItemsInPackLabel = new Label("");
        grid.add(ItemsInPackLabel, 0, 5);
        ItemsInPackField = new TextField();
        grid.add(ItemsInPackField, 1, 5);
        CheckBox ItemsInPackcb = new CheckBox("Don,t use");
        grid.add(ItemsInPackcb, 2, 5);
        //Creating and adding number of packs label and field
        Label NofPacksLabel = new Label("Sign number of Packs, column");
        grid.add(NofPacksLabel, 0, 6);
        NofPacksField= new TextField();
        grid.add(NofPacksField, 1, 6);
        CheckBox NofPackscb = new CheckBox("Don't use");
        grid.add(NofPackscb, 2, 6);
        //Creating and adding net weight label and field
        Label netWeightLabel  = new Label("Choose net weight column");
        netWeightField = new TextField();
        CheckBox netWeightcb = new CheckBox("Don't use");
        grid.add(netWeightLabel, 0, 7);
        grid.add(netWeightField, 1, 7);
        grid.add(netWeightcb, 2, 7);
        //Creating and adding sum of net Weight
        Label sumNwLabel = new Label("choose sum Net Weight column");
        sumNwField = new TextField();
        CheckBox sumNwcb = new CheckBox("Don't use");
        grid.add(sumNwLabel, 0, 8);
        grid.add(sumNwField, 1, 8);
        grid.add(sumNwcb, 2, 8);
        //Creating and adding gross weight of pack column
        Label grossWeightOfPackLabel = new Label("Sign gross weight, column");
        grossWeightField = new TextField();
        CheckBox grossWeightOfPackcb = new CheckBox("Don't use");
        grid.add(grossWeightOfPackLabel, 0, 9);
        grid.add(grossWeightField, 1, 9);
        grid.add(grossWeightOfPackcb, 2, 9);
        //Creating and adding gross weight column
        Label sumGwLabel = new Label("Choose gross weight column");
        sumGwField = new TextField();
        grid.add(sumGwLabel, 0, 10);
        grid.add(sumGwField, 1, 10);
        //Creating and adding volume of pack 
        Label volumeOfPackLabel = new Label("Choose volume of pack column");
        volumeOfPackField = new TextField();
        CheckBox volumeOfPackcb = new CheckBox("Don't use it");
        grid.add(volumeOfPackLabel, 0, 11);
        grid.add(volumeOfPackField, 1, 11);
        grid.add(volumeOfPackcb, 2, 11);
        //Creating and adding sum volume if items credentials
        Label sumVolumeLabel = new Label("Choose volume of packs of an item, column");
        sumVolumeField = new TextField();
        grid.add(sumVolumeLabel, 0, 12);
        grid.add(sumVolumeField, 1, 12);
        //Creating and setting calculate button
         Button btn = new Button();
        btn.setText("Calculate");
        HBox calculateBtnH = new HBox();
        calculateBtnH.getChildren().add(btn);
        calculateBtnH.setAlignment(Pos.BOTTOM_RIGHT);
        grid.add(calculateBtnH, 2, 13);
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
                
               final Stage newStage = new Stage();
              newStage.initModality(Modality.WINDOW_MODAL);
              if(warningMessage()){
                  setInputs();
             containerCalc.readFromExcel(pathToSource.getText(), 
                     names, rowsRange, itemsNumber,
                     inPack, numberOfPacks, netWeight, sumNetWeight, grossWeight, 
                     sumGrossWeight, volumeOfPack, sumVolume);
              }
               containerCalc.setWeightCapacity(27000);
               containerCalc.setVolumeCapacity(27);
               containerCalc.setFourty(56);
               containerCalc.getNumberOfContainers();
               containerCalc.findContainers();
               containerCalc.sortItems();
               containerCalc.printUnsorted();
              showReport(newStage);
                
              //System.out.println(cont.formatedValue(NIField.getText()));
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
      // System.out.println(container.printSumVolume());
             */
    }
    
 private void showReport(Stage stage){
   // Stage secondStage = new Stage();
    String message = "containers loaded: " + 
            containerCalc.getNumOfContainers()+"\n"+
                    "Fourties: " + containerCalc.getFourties()+"\n"+
             "Number of twenties: "+containerCalc.getTwenties()+"\n"+
              "Volume left: "+ containerCalc.getVolumeLeft() + "\n"+
            "Weight left: " + containerCalc.getWeightLeft();
          popupReport report = new popupReport();
          report.createAndShowPopup(message, stage);
            
   }
 private boolean warningMessage(){
   popupReport warningReport = new popupReport();
   Stage warningStage = new Stage();
    if(cont.checkRowsField(ItemsField.getText())){
     warningReport.createAndShowPopup(cont.formMessage("ItemsField"), warningStage);
     return false;
    }else if(cont.formatedValue(NIField.getText()) == null){
        warningReport.createAndShowPopup(cont.formMessage("NIField"), warningStage);
        return false;
    }else if(cont.formatedValue(ItemsInPackField.getText())==null){
        warningReport.createAndShowPopup(cont.formMessage("inPack"), warningStage);
        return false;
    }else if(cont.formatedValue(NofPacksField.getText()) == null){
        warningReport.createAndShowPopup(cont.formMessage("numberOfPacks"), warningStage);
        return false;
    }else if(cont.formatedValue(netWeightField.getText()) == null){
        warningReport.createAndShowPopup("netWeight", warningStage);
        return false;
    }else if(cont.formatedValue(sumNwField.getText())==null){
        warningReport.createAndShowPopup(cont.formMessage("sumNet"), warningStage);
        return false;
    }else if(cont.formatedValue(grossWeightField.getText())== null){
        warningReport.createAndShowPopup(cont.formMessage("grossWeight"), warningStage);
        return false;
    }else if(cont.formatedValue(sumGwField.getText())==null){
        warningReport.createAndShowPopup("sumGrossWeight", warningStage);
        return false;
    }else if(cont.formatedValue(volumeOfPackField.getText())==null){
        warningReport.createAndShowPopup(cont.formMessage(("itemVolume")), warningStage);
        return false;
    }else if(cont.formMessage(sumVolumeField.getText()) == null){
        warningReport.createAndShowPopup("sumVolume", warningStage);
        return false;
    }else {
        return true;
    }
 }   
 /*Method for setting input variables from fields to global variables(Decision to
 put text from input fields to global variables was writtem just for
 convinience)*/
 private void setInputs(){
     names = namesField.getText();
     rowsRange = ItemsField.getText();
     itemsNumber = cont.formatedValue(NIField.getText());
     inPack = cont.formatedValue(ItemsInPackField.getText());
     numberOfPacks = cont.formatedValue(NofPacksField.getText());
     netWeight = cont.formatedValue(netWeightField.getText());
     sumNetWeight = cont.formatedValue(sumNwField.getText());
     grossWeight = cont.formatedValue(grossWeightField.getText());
     sumGrossWeight = cont.formatedValue(sumGwField.getText());
     volumeOfPack = cont.formatedValue(volumeOfPackField.getText());
     sumVolume = cont.formatedValue(sumVolumeField.getText());
 }
}
