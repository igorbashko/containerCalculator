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
    private Stage openStage = new Stage();
    private ObservableList<containerInList> list = FXCollections.observableList(new ArrayList<containerInList>()); 
    
    
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
       openStage.initOwner(primaryStage);
       Scene scene = new Scene(setMainWindow(), 960, 700);
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
   // columns.gridLinesVisibleProperty().setValue(Boolean.TRUE);
    columns.setHgap(10);
    columns.setVgap(10);
    //Adds label with Instructions which buttons to press
    Label hintLabel = new Label("Укажите столбцы, соответсвующие данным,"
            + "указанным внизу");
    hintLabel.setFont(Font.font("Tahome", FontWeight.NORMAL, 15));
    columns.add(hintLabel, 0, 0, 2, 1);
    //Text field and label with item Name
    Label nameLabel = new Label("Название товара");
    columns.add(nameLabel, 0, 1); columns.add(nameText, 1, 1);
    //Price
    Label price = new Label("Цена");
    columns.add(price, 0, 2); columns.add(priceField, 1, 2);
    //Adds label and text field with price column in excel
   Label numOfItems = new Label("Укажите колонку с количеством");
   columns.add(numOfItems, 0, 3); columns.add(numOfItemsField, 1, 3);
   //Adds label and text field with pieces in pack column in excel
   Label numInPacks = new Label("Столбец с количеством наименований в коробке");
   columns.add(numInPacks, 0, 4); columns.add(itemsInPackF, 1, 4);
   // -//- number of packs
   Label numOfPacks = new Label("Столбец с количеством упаковок");
   columns.add(numOfPacks, 0, 5); columns.add(numOfPacksF, 1, 5);
   //net weight of pack
   Label netWeightOfPack = new Label("Столбец с весом нетто упаковки");
   columns.add(netWeightOfPack, 0, 6); columns.add(netWeightOfPackF, 1, 6);
   //gross weight of pack
   Label grossWeightOfPack = new Label("Столбец с весом брутто упаковки");
   columns.add(grossWeightOfPack, 0, 7); 
   columns.add(grossWeightOfPackF, 1, 7);
   //volume of pack
   Label volumeOfPack = new Label("Укажите объем коробки");
   columns.add(volumeOfPack, 0, 8); columns.add(volumeOfPackF, 1, 8);
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
     //Adds actions for buttons
     addContainer(addCButton, setContainers(nameTextF.getText(), containerWeightF.getText(),
             containerVolumeF.getText()));
     removeContainer(removeButton);
     return addContainerForm;
 }
 /**
  * Sets main window view 
  * @return GridPane with main window
  */
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
//Report 
 Label reportLabel = new Label("Окно отчета");
 mainWindow.add(reportLabel, 0, 3, 2, 1);
 GridPane.setHalignment(reportLabel, HPos.CENTER);
 reportLabel.setFont(Font.font("Tahome", FontWeight.NORMAL, 20));
 TextArea reportWindow = new TextArea();
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
 public String getSourcePath(){
     return this.sourceTextField.getText();
 }

 private containerInList setContainers(String name, String kg, String m3){
       int weight = Integer.parseInt(kg);
       int volume = Integer.parseInt(m3);
       containerInList container = new containerInList(name, weight, volume);
       return container;
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
private void addContainer(Button addButton, containerInList type){
    addButton.setOnAction(new EventHandler<ActionEvent>(){
      public void handle(ActionEvent event){
            list.add(type);
        }
    });
}
/**
 * Method to program remove container type button
 * @param removeButton button for removing containers 
 */
private void removeContainer(Button removeButton){
    String selected = containersList.getSelectionModel().getSelectedItem().toString();
    for(containerInList type:list){
        if(type.getName().equals(selected))
            list.remove(type);
    else
    System.out.println("Choose item to delete");
 }
}
 }
