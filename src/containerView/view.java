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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.control.ListView;
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
    private Stage openStage = new Stage();
    private ObservableList<containerInList> list = FXCollections.observableList(new ArrayList<containerInList>()); 
    private containerInList currentContainer;
    private TextField containerName = new TextField();
    private TextField containerWeight = new TextField();
    private TextField containerVolume = new TextField();
    
    public void start(Stage primaryStage){
        
       Scene scene = new Scene(setMainWindow(), 960, 700);
       primaryStage.setScene(scene);
       setTextInColumns();
       primaryStage.show();
   }
    
 /**
  * Sets Left part of the main window with source file,
  * output and input columns and rows range buttons
  */
 private TextField sourceTextField = new TextField();//field with input file path
 private TextField outputFileField = new TextField();//field with ouptut file path
 /**
  *Get input file text field 
  */
 private VBox setLeftPartWindow(){
    VBox leftBox = new VBox(); //Left part Box
     // Set parts for upload file
     GridPane chooseFilesPane = new GridPane();
     chooseFilesPane.setHgap(10);
     chooseFilesPane.setVgap(10);
     chooseFilesPane.setPadding(new Insets(10, 10, 10, 10));
     Label sourceFileLabel = new Label("Исходный файл");
     chooseFilesPane.add(sourceFileLabel, 0, 0);//Adds choose file label
     sourceTextField.setMinWidth(250);
     chooseFilesPane.add(sourceTextField, 1, 0);//Adds choose file text field
     Button setFileBtn = new Button("Выбрать");
     chooseFilesPane.add(setFileBtn, 2, 0);
     setSourceFileButton(setFileBtn);//Programs button to show path to the source file
     //Output file Box
    // HBox outputFileBox = new HBox();
     Label outputLabel = new Label("Файл результата");
     chooseFilesPane.add(outputLabel, 0, 1);
     outputFileField = new TextField();
     chooseFilesPane.add(outputFileField, 1, 1);
     Button outputButton = new Button("Выбрать");
     chooseFilesPane.add(outputButton, 2, 1);
     //outputFileBox.getChildren().addAll(outputLabel, outputText, outputButton);
     leftBox.getChildren().addAll(chooseFilesPane, setColumns());
     return leftBox;
 }
 /**
  * Setting columns for Item parameters(Price, weight, etc)
  */
 private static final TextField rows = new TextField(); //Rows range
 private static final TextField sheetNumberF = new TextField(); //Excell sheet number
 private static final TextField nameText = new TextField(); //Name of the item
 private static final TextField priceField = new TextField(); //Price of the text field
 private static final TextField numOfItemsField = new TextField(); //number of the item
 private static final TextField itemsInPackF = new TextField();//items in pack
 private static final TextField numOfPacksF = new TextField(); // number of packs
 private static final TextField netWeightOfPackF = new TextField(); //net weight of pack
 private static final TextField grossWeightOfPackF = new TextField();// gross weight of pack
 private static final TextField volumeOfPackF = new TextField();
 
 private GridPane setColumns(){
    GridPane columns = new GridPane();
    columns.setPadding(new Insets(0,0,0,10));
   // columns.gridLinesVisibleProperty().setValue(Boolean.TRUE);
    columns.setHgap(10);
    columns.setVgap(10);
    //Experiment with lambda expression 
    leftColumn setColumn = (columnsN, label, field, rowNumber, labelText)->{
     label.setText(labelText);
     columnsN.add(label, 0, rowNumber);
     columnsN.add(field, 1, rowNumber);
    };
    //Adds label with Instructions which buttons to press
    Label hintLabel = new Label("Укажите столбцы, соответсвующие данным,"
            + "указанным внизу");
    hintLabel.setFont(Font.font("Tahome", FontWeight.NORMAL, 15));
    columns.add(hintLabel, 0, 0, 2, 1);
    //Text and field with label and field of rows
    Label rowsLabel = new Label();
    setColumn.addToColumn(columns, rowsLabel, rows, 1, "Диапазон строк в формате "
            + "(n-m)\n Например (1-200)");
    //Text and field with sheet number
    Label sheetNumber = new Label();
    setColumn.addToColumn(columns, sheetNumber, sheetNumberF, 2, "Номер листа в файле");
    //Text field and label with item Name
    Label nameLabel = new Label("Название товара");
    columns.add(nameLabel, 0, 3); columns.add(nameText, 1, 3);
    //Price
    Label price = new Label("Цена");
    columns.add(price, 0, 4); columns.add(priceField, 1, 4);
    //Adds label and text field with price column in excel
   Label numOfItems = new Label("Количество");
   columns.add(numOfItems, 0, 5); columns.add(numOfItemsField, 1, 5);
   //Adds label and text field with pieces in pack column in excel
   Label numInPacks = new Label("Количество в упаковке");
   columns.add(numInPacks, 0, 6); columns.add(itemsInPackF, 1, 6);
   // -//- number of packs
   Label numOfPacks = new Label("Количеством упаковок");
   columns.add(numOfPacks, 0, 7); columns.add(numOfPacksF, 1, 7);
   //net weight of pack
   Label netWeightOfPack = new Label("Вес нетто упаковки");
   columns.add(netWeightOfPack, 0, 8); columns.add(netWeightOfPackF, 1, 8);
   //gross weight of pack
   Label grossWeightOfPack = new Label("Вес брутто упаковки");
   columns.add(grossWeightOfPack, 0, 9); 
   columns.add(grossWeightOfPackF, 1, 9);
   //volume of pack
   Label volumeOfPack = new Label("Объем упаковки");
   columns.add(volumeOfPack, 0, 10); columns.add(volumeOfPackF, 1, 10);
   return columns;
 }
 /**
  * Setting right part of the main window
  */
 private ListView containersList = new ListView(); //window with types of containers
 private HBox setRightPartWindow(){
     HBox rightBox = new HBox();
     containersList();
     rightBox.getChildren().addAll(containersList, addContainerForm());
     rightBox.setSpacing(10);
     rightBox.setPadding(new Insets(8, 15, 15, 15));
     return rightBox;
 }
 /**
  * Configures containers list component
  */
  private void containersList(){
     containersList = new ListView<String>();
     containersList.setMaxHeight(330);
     containersList.setMaxWidth(240);
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
     addContainerForm.add(containerName, 0, 0); addContainerForm.add(this.containerName, 1, 0);
     //Weight capacity of the container
     Label containerWeight = new Label("кг");
     addContainerForm.add(containerWeight, 0, 1); 
     addContainerForm.add(this.containerWeight, 1, 1);
     //Volume capacity of the container 
     Label containerVolume = new Label("м3");
     addContainerForm.add(containerVolume, 0, 2); 
     addContainerForm.add(this.containerVolume, 1, 2);
     //Adds buttons add container and remove the container
     Button addCButton = new Button("+");
     Button removeButton = new Button("-");
     addContainerForm.add(addCButton, 0, 3); addContainerForm.add(removeButton, 1, 3);
     //Adds actions for buttons
     addContainer(addCButton);
     removeContainer(removeButton);
     return addContainerForm;
 }
 /**
  * Sets main window view 
  * @return GridPane with main window
  */
 TextArea reportWindow = new TextArea();
 
 private GridPane setMainWindow(){
 GridPane mainWindow = new GridPane();
 mainWindow.setHgap(10);
 mainWindow.setVgap(10);
 //mainWindow.gridLinesVisibleProperty().setValue(Boolean.TRUE);
 Label welcome = new Label("Добро пожаловать в программу по загрузке контейнеров");
 GridPane.setHalignment(welcome, HPos.CENTER);
 welcome.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
 mainWindow.add(welcome, 0, 0, 2, 1);
 mainWindow.add(setLeftPartWindow(), 0, 1); mainWindow.add(setRightPartWindow(), 1, 1);
 setListOfContainers();
//Calculate button
 Button calculateButton = new Button("Подсчитать");
 GridPane.setHalignment(calculateButton, HPos.RIGHT);
 mainWindow.add(calculateButton, 1, 2);
 runCalculate(calculateButton);
//Report 
 Label reportLabel = new Label("Окно отчета");
 mainWindow.add(reportLabel, 0, 3, 2, 1);
 GridPane.setHalignment(reportLabel, HPos.CENTER);
 reportLabel.setFont(Font.font("Tahome", FontWeight.NORMAL, 20));
 reportWindow.setEditable(false);
 mainWindow.add(reportWindow, 0, 4, 2, 1);
 return mainWindow;
 }
 /**
  * Sets path to sourceFile in the source Text Field
  */
 private void setPathToSource(){
     FileChooser chooseFile = new FileChooser();
    File path = chooseFile.showOpenDialog(openStage);
    if(path !=null)
        this.sourceTextField.setText(path.getAbsolutePath());
 }
 /**
  * Programs button to set path to the source file
  * @param btn
  */
 private void setSourceFileButton(Button btn){
     btn.setOnAction(new EventHandler<ActionEvent>(){
         public void handle(ActionEvent event){
             setPathToSource();
             setOutputText();
         }
     });
 }
 /**
  * Sets output text field
  */
 private void setOutputText(){
  this.outputFileField.setText(cont.setOutputText(this.sourceTextField.getText()));
 }
 /**
  * Returns letters of excel columns typed by the user
  * @return array with columns letters for passing them to reader
  */
 public String[] getColumns(){
     String itemName = nameText.getText();
     String itemPrice = priceField.getText();
     String numOfItems = numOfItemsField.getText();
     String itemsInPack = itemsInPackF.getText();
     String numOfPacks = numOfPacksF.getText();
     String netWeight = netWeightOfPackF.getText();
     String grossWeightOfPack = grossWeightOfPackF.getText();
     String volumeOfPack = volumeOfPackF.getText();
     String [] columns ={itemName, itemPrice, numOfItems, itemsInPack,
     numOfPacks, netWeight, grossWeightOfPack, volumeOfPack};
   return columns;  
 }
 /**
  * Returns path of the input file
  * @return path of the input file
  */
 private void setContainer(){
     String containerName= this.containerName.getText();
     int weight = Integer.parseInt(this.containerWeight.getText());
     int volume = Integer.parseInt(this.containerVolume.getText());
     this.currentContainer = new containerInList(containerName, weight, volume);  
  }
/**
 * Programs observable list of containers that reacts on any modifications 
 */
private void setListOfContainers(){
  list.addListener(new ListChangeListener(){
       @Override
         public void onChanged(ListChangeListener.Change change){
          containersList.getItems().clear();
          ObservableList<String> names = FXCollections.observableArrayList();
          for(containerInList type: list){
              names.add(type.getName());
          }
          containersList.setItems(names);
        }
     });
  }
/**
 * Method to program add container type button
 * @param addButton button for adding container type into the list
 * @param type type of the container to add
 */
private void addContainer(Button addButton){
    addButton.setOnAction(new EventHandler<ActionEvent>(){
         @Override
         public void handle(ActionEvent event){
             setContainer();
            list.add(currentContainer);
        }
    });
}
/**
 * Method to program remove container type button
 * @param removeButton button for removing containers 
 */
private void removeContainer(Button removeButton){
    removeButton.setOnAction(event->{
    String selected = containersList.getSelectionModel().getSelectedItem().toString();
    for(Iterator<containerInList> iterator = list.iterator(); iterator.hasNext();){
        if(iterator.next().getName().equals(selected)){
            iterator.remove();
            break;
        }
    else
    System.out.println("Choose item to delete");
 }   
    });
  }
/**
 * Experimental interface suppose to set left columns parameters block
 */
interface leftColumn{
    void addToColumn(GridPane grid, Label label, TextField textField,
             int row, String labelText);
}
/**
 * Runs calculation process after calculate button was clicked
 * @param calculateButton Run calculation button
 */
private void runCalculate(Button calculateButton){
     calculateButton.setOnAction((ActionEvent event) -> {
         int sheetNumber = Integer.parseInt(sheetNumberF.getText());
         //cont.rowsRangeProcessing(rows.getText());
         cont.readData(getColumns(), sourceTextField.getText(), sheetNumber);
         cont.setContainersTypes(list);
         cont.runSorting();
         setReport();
         cont.writeOutput(outputFileField.getText());
     });
   }
/**
 * Only for debugging purposes. 
 * Delete after use
 * 
 * private static final TextField rows = new TextField(); //Rows range
 private static final TextField sheetNumberF = new TextField(); //Excell sheet number
 private static final TextField nameText = new TextField(); //Name of the item
 private static final TextField priceField = new TextField(); //Price of the text field
 private static final TextField numOfItemsField = new TextField(); //number of the item
 private static final TextField itemsInPackF = new TextField();//items in pack
 private static final TextField numOfPacksF = new TextField(); // number of packs
 private static final TextField netWeightOfPackF = new TextField(); //net weight of pack
 private static final TextField grossWeightOfPackF = new TextField();// gross weight of pack
 private static final TextField volumeOfPackF = new TextField();
 */
private void setTextInColumns(){
 sheetNumberF.setText("0");
 nameText.setText("a");
 priceField.setText("g");
 numOfItemsField.setText("b");
 itemsInPackF.setText("j");
 numOfPacksF.setText("k");
 netWeightOfPackF.setText("l");
 grossWeightOfPackF.setText("m");
 volumeOfPackF.setText("p");
 }
private void setReport(){
    reportWindow.setText(cont.getReport());
}
}
