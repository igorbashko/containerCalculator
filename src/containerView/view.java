/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package containerView;

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
import javafx.geometry.HPos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

/**
 *
 * @author igor
 */
public class view {
    
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
    
    
    public void start(Stage primaryStage){
          GridPane grid = new GridPane();
       grid.gridLinesVisibleProperty().setValue(Boolean.TRUE);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));
        //creating and adding heaging
        Text heading = new Text("Добро пожаловать в программу по расчету загруки товаров");
        heading.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(heading, 0, 0, 3, 1);
        //creating and adding file load label, filed and upload button
        Label loginLabel = new Label("Выберите исходный файл");
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
              System.out.println(sumVolumeField.getText());
              }
        });
       
       Scene scene = new Scene(setMainWindow(), 950, 700);
       primaryStage.setScene(scene);
       primaryStage.show();
       textInitialise();
    }
     /*Method for accessing file through the file dialog*/
private File openFile(File file){
    return file;
    }
 private void showReport(Stage stage){
   // Stage secondStage = new Stage();
    String message = "containers loaded: " + 
            containerCalc.getNumOfContainers()+"\n"+
                    "Fourties: " + containerCalc.getFourties()+"\n"+
             "Number of twenties: "+containerCalc.getTwenties()+"\n"+
              "Volume left: "+ containerCalc.getVolumeLeft() + "\n"+
            "Weight left: " + containerCalc.getWeightLeft();
          popupReport report = new popupReport(stage);
          report.generalWarning(message);
            
   }
 
 private boolean warningMessage(){
   popupReport warningReport = new popupReport(cont.getPopupStage());
   Stage warningStage = new Stage();
    if(cont.checkRowsField(ItemsField.getText())){
     warningReport.generalWarning(cont.formMessage("ItemsField"));
     return false;
    }else if(cont.formatedValue(NIField.getText()) == null){
        warningReport.generalWarning(cont.formMessage("NIField"));
        return false;
    }else if(cont.formatedValue(ItemsInPackField.getText())==null){
        warningReport.generalWarning(cont.formMessage("inPack"));
        return false;
    }else if(cont.formatedValue(NofPacksField.getText()) == null){
        warningReport.generalWarning(cont.formMessage("numberOfPacks"));
        return false;
    }else if(cont.formatedValue(netWeightField.getText()) == null){
        warningReport.generalWarning(cont.formMessage("netWeight"));
        return false;
    }else if(cont.formatedValue(sumNwField.getText())==null){
        warningReport.generalWarning(cont.formMessage("sumNet"));
        return false;
    }else if(cont.formatedValue(grossWeightField.getText())== null){
        warningReport.generalWarning(cont.formMessage("grossWeight"));
        return false;
    }else if(cont.formatedValue(sumGwField.getText())==null){
        warningReport.generalWarning(cont.formMessage("sumGrossWeight"));
        return false;
    }else if(cont.formatedValue(volumeOfPackField.getText())==null){
        warningReport.generalWarning(cont.formMessage("itemVolume"));
        return false;
    }else if(cont.formMessage(sumVolumeField.getText()) == null){
        warningReport.generalWarning(cont.formMessage("sumVolume"));
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
 /*The method to input text in text fields(only in debugging purposes)*/
 private void textInitialise(){
     this.namesField.setText("a");
     this.ItemsField.setText("1-118");
     this.NIField.setText("b");
     this.ItemsInPackField.setText("j");
     this.NofPacksField.setText("k");
     this.netWeightField.setText("l");
     this.sumNwField.setText("n");
     this.grossWeightField.setText("m");
     this.sumGwField.setText("o");
     this.volumeOfPackField.setText("p");
     this.sumVolumeField.setText("q");
 }/**
  * Sets Left part of the main window with source file,
  * output and input columns and rows range buttons
  */
 private VBox setLeftPartWindow(){
    VBox leftBox = new VBox(); //Left part Box
     // Set parts for upload file
     GridPane chooseFilesPane = new GridPane();
     chooseFilesPane.setHgap(10);
     chooseFilesPane.setVgap(10);
     Label sourceFileLabel = new Label("Исходный файл");
     chooseFilesPane.add(sourceFileLabel, 0, 0);//Adds choose file label
     TextField sourceTextField = new TextField();
     chooseFilesPane.add(sourceTextField, 1, 0);//Adds choose file text field
     Button setFileBtn = new Button("Выбрать");
     chooseFilesPane.add(setFileBtn, 2, 0);
     //Output file Box
    // HBox outputFileBox = new HBox();
     Label outputLabel = new Label("Файл результата");
     chooseFilesPane.add(outputLabel, 0, 1);
     TextField outputText = new TextField();
     chooseFilesPane.add(outputText, 1, 1);
     Button outputButton = new Button("Выбрать");
     chooseFilesPane.add(outputButton, 0, 2);
     //outputFileBox.getChildren().addAll(outputLabel, outputText, outputButton);
     leftBox.getChildren().addAll(chooseFilesPane, setColumns());
     return leftBox;
 }
 /**
  * Setting columns for Item parameters(Price, weight, etc)
  */
 private GridPane setColumns(){
    GridPane columns = new GridPane();
    columns.gridLinesVisibleProperty().setValue(Boolean.TRUE);
    columns.setHgap(10);
    columns.setVgap(10);
    //Adds label and text field with price column in excel
   Label price = new Label("Укажите колонку с количеством");
   TextField priceField = new TextField();
   columns.add(price, 0, 0); columns.add(priceField, 1, 0);
   //Adds label and text field with pieces in pack column in excel
   Label numInPacks = new Label("Столбец с количеством наименований в коробке");
   TextField itemsInPackF = new  TextField();
   columns.add(numInPacks, 0, 1); columns.add(itemsInPackF, 1, 1);
   // -//- number of packs
   Label numOfPacks = new Label("Столбец с количеством упаковок");
   TextField numOfPacksF = new TextField();
   columns.add(numOfPacks, 0, 2); columns.add(numOfPacksF, 1, 2);
   //net weight of pack
   Label netWeightOfPack = new Label("Столбец с весом нетто упаковки");
   TextField netWeightOfPackF = new TextField();
   columns.add(netWeightOfPack, 0, 3); columns.add(netWeightOfPackF, 1, 3);
   //gross weight of pack
   Label grossWeightOfPack = new Label("Столбец с весом брутто упаковки");
   TextField grossWeightF = new TextField();
   columns.add(grossWeightOfPack, 0, 4); columns.add(grossWeightF, 1, 4);
   //volume of pack
   Label volumeOfPack = new Label("Укажите объем коробки");
   TextField volumeF = new TextField();
   columns.add(volumeOfPack, 0, 5); columns.add(volumeF, 1, 5);
   return columns;
 }
 /**
  * Setting right part of the main window
  */
 private HBox setRightPartWindow(){
     HBox rightBox = new HBox();
     rightBox.getChildren().addAll(containersList(), addContainerForm());
     return rightBox;
 }/**
  * Creates containers list component
  * @return containersList
  */
 private TableView containersList(){
     TableView containersList = new TableView();
     TableColumn column = new TableColumn("Типы контейнеров");
     containersList.getColumns().add(column);
     return containersList;
 }
 /**
  * Creates adding container form to the right part of the window
  */
 private GridPane addContainerForm(){
     GridPane addContainerForm = new GridPane();
     addContainerForm.setHgap(10);
     addContainerForm.setVgap(10);
     //Name of the container
     Label containerName = new Label("Имя контейнера");
     TextField nameTextF = new TextField();
     addContainerForm.add(containerName, 0, 0); addContainerForm.add(nameTextF, 1, 0);
     //Weight capacity of the container
     Label containerWeight = new Label("кг");
     TextField containerWeightF = new TextField();
     addContainerForm.add(containerWeight, 0, 1); addContainerForm.add(containerWeightF, 1, 1);
     //Volume capacity of the container 
     Label containerVolume = new Label("м3");
     TextField containerVolumeF = new TextField();
     addContainerForm.add(containerVolume, 0, 2); addContainerForm.add(containerVolumeF, 1, 2);
     //Adds buttons add container and remove the container
     Button addCButton = new Button("+");
     Button removeButton = new Button("-");
     addContainerForm.add(addCButton, 0, 3); addContainerForm.add(removeButton, 1, 3);
     return addContainerForm;
 }
 
 private GridPane setMainWindow(){
 GridPane mainWindow = new GridPane();
 mainWindow.setHgap(10);
 mainWindow.setVgap(10);
 mainWindow.gridLinesVisibleProperty().setValue(Boolean.TRUE);
 Label welcome = new Label("Добро пожаловать в программу по загрузке контейнеров");
 GridPane.setHalignment(welcome, HPos.CENTER);
 welcome.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
 mainWindow.add(welcome, 0, 0, 2, 1);
 mainWindow.add(setLeftPartWindow(), 0, 1); mainWindow.add(setRightPartWindow(), 1, 1);
 TextArea reportWindow = new TextArea();
 reportWindow.setEditable(false);
 mainWindow.add(reportWindow, 0, 3, 2, 1);
 return mainWindow;
 }
}
