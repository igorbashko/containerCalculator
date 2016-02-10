/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package containerController;

import containerMath.Container;
import containerMath.Item;
import containerMath.Optimizer;
import containerMath.Stock;
import containerView.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import static java.lang.Math.abs;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidParameterSpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.stage.Stage;
import licensegenerator.Generator;
import org.apache.poi.*;
import org.apache.poi.ss.util.CellReference;
    

/**
 *This class will be used in singeltone pattern
 * @author igor
 */
public class controller{
    private static controller cont = null;
    private int firstNumber;
    private int secondNumber;
    private String info1Path; 
    private String info2Path; 
    private File info1;
    private File info2;
    private crypto encryption;
    private boolean activated = false;
    private int runTimes = 0;
    private String licenseKey;
    private File verification1;
    private File verification2;
    private String inputKey;
    private popupReport demoPopUp;
    private Stage popupStage; //Stage for initializing popup messages removed "= new Stage()"
    private Stock stock;
    private readWriter readWriter;//Stock variable where all of the items are sorted   
    private List<Container> finalContainers;
    private Stock sortedStock;
    private List <Container> containers;
            private controller(){};
    public static controller getController(){
        if(cont == null){
            cont = new controller();
        }
        return cont;
    }
    /**
     *Sets readWriter instance to read and write from excel files on the disk 
     * @param pathToInputFile Input file
     * @param sheetNumber number of excel sheet in input file
     * @param cellsNumbers range of rows with items info
     */
    private void setReadWriter(String pathToInputFile, int sheetNumber,
            String [] cellsNumbers){
        this.readWriter = new readWriter(pathToInputFile, sheetNumber,
        cellsNumbers);
    }
    /*Method for formatting input strings to remove all the unwanted characters
    and letters from small to capital
    */
    public String formatedValue(String input){
        String output = input.replaceAll("[^a-zA-Z0-9]", "");
        StringBuilder sb = new StringBuilder(output);
        if(sb !=null && !sb.toString().equals("")){
    for(int i =0; i<sb.length(); i++){
            char c = sb.charAt(i);
        if(Character.isLowerCase(c))
            sb.setCharAt(i, Character.toUpperCase(c));
    }
    return sb.toString();
  }else 
       return null;
    }
    /*Method for validating number of rows field. Rregular expression 
    pattern is used. If it does not satisfy the expression false 
    variable is returned true otherwise 
    */
    public boolean checkRowsField(String input){
        if(!input.matches("[0-9]+\\-[0-9]+")){
            return true;
        } else {
            return false;
        }
    }
    /*Method for forming warning text message is uer inputs wrong values
    in fields for specifing columns and rows in excel file*/
    public String formMessage(String flag){
        String message = new String("");
        if (flag.equals("ItemsField")){
            message = "Fill the Rows range field";
        }
        if(flag.equals("NIFfield")){
            message = "Fill the number of items field";
        }else if(flag.equals("inPack")){
            message = "Fill the intems in pack field";
        }else if(flag.equals("numberOfPacks")){
            message = "Fill the number of packs field";
        }else if(flag.equals("netWeight")){
            message = "Fill net weight field";
        }else if(flag.equals("sumNet")){
            message = "Fill the sum net weight field";
        }else if(flag.equals("grossWeight")){
            message = "Fill the gross weight field";
        }else if(flag.equals("sumGrossWeight")){
            message = "Fill the sum gross weight field";
        }else if(flag.equals("itemVolume")){
            message = "Fill the item volume field";
        }else if(flag.equals("sumVolume")){
            message = "Fill the sum volume field";
        }
            return message;
      }
    
    public void rowsRangeProcessing(String message){
        String[] split = new String[2] ;
               int i=0;
        for (String split2:message.trim().split("-")) {
            split[i] = split2;
            i++;
          }
      this.firstNumber = Integer.parseInt(split[0]);
      this.secondNumber = Integer.parseInt(split[1]);
    }
    public int getFirstNumber(){
        return this.firstNumber-1;
    }
    public int getSecondNumber(){
        return this.secondNumber-1;
    }
    /**
     * Check if the file with license info exists or not
     */
    public boolean infoExist(){
        info1Path = new String(System.getenv("APPDATA")+"\\containerCalculator\\info1.txt");
        info2Path = new String(System.getenv("APPDATA")+"\\containerCalculator\\info2.txt");
        info1 = new File(info1Path);
        info2 = new File(info2Path);
        if(info1.isFile() && info2.isFile())
            return true;
        else 
            return false;
    }
    /**
     * If the file with infoLicense does not exist create it with license information
     */
    public void createInfo(){
        try {
        info1.getParentFile().mkdir();
        info1.createNewFile();
        info2.createNewFile();
        verification1 = new File("license1.txt");
        verification2 = new File("license2.txt");
        verification1.createNewFile();
        verification2.createNewFile();
         }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    /**
     * Writes encrypted information of the computer unique identifier
     */
    private void writeEncryptedId(){
        try{
        FileOutputStream outputId = new FileOutputStream(verification1);
        FileOutputStream outputIv = new FileOutputStream(verification2);
        encryption.setPassphrase("testPas2");
        encryption.encryptMessage(encryption.getUniqueId());
        outputId.write(encryption.getEncrypted());
        outputId.close();
        outputIv.write(encryption.getIv());
        outputIv.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    /**
     * Writes content to the info file
     */
     public void writeInfo(String activated, String runTimes, String licenseKey){
      String message = new String(activated + runTimes + licenseKey);
      cryptoInitialize();
      encryption.setPassphrase("markiza2531");
      encryption.generateConf(info1, info2, message);
    }
    /**
     * Initialize crypto instance
     */
   public void cryptoInitialize(){
       this.encryption = new crypto();
   }
   /**
    * Method for reading license info from the file 
    */
   public void readInfo(){
       cryptoInitialize();
       encryption.setPassphrase("markiza2531");
       String decryptedData = encryption.decryptMessage(info1Path, info2Path);
       licenseInfo info = new licenseInfo(decryptedData);
       activated = info.getActivated();
       runTimes = info.getRunTimes();
       licenseKey = info.getKey();
      
   }
   /**
    * Starting the application
    */
   public void createView(Stage stage){
       view newView = new view();
       newView.start(stage);
   }
   /**
    * Generating unique indentifier 
    * and creating a key from it
    */
   private void setKey(){
       Generator generator = new Generator();
       this.licenseKey = new String();
           this.licenseKey = generator.returnKey(encryption.getUniqueId(), "testPas");
            
        
   }
   /**
    * Define existence of configuration file and creating a new file 
    * if there is no config
    */
   public void startApp(Stage primaryStage) {
       if (infoExist()){
          readInfo();
          processLicense(primaryStage);
      }else{
           createInfo();
           cryptoInitialize();
           setKey();
           writeInfo("FALSE\n", "0\n", licenseKey);
           writeEncryptedId();
           createView(primaryStage);
       }
   }
   /**
    * Work with info from the license file. 
    * Define if software was activated,
    * and how many times it was run if not.
    */
   private void processLicense(Stage primaryStage){
       popupStage.initOwner(primaryStage);
       if(activated){
          createView(primaryStage);
       }else{
           if(runTimes <4){
               int leftTimes = 4 -(runTimes+1);
               String demoMessage = new  String("Приложение запщено в "
                       + "демонстрационной версии\n"
                       + "Осталось "+Integer.toString(leftTimes)+" запусков");
               createView(primaryStage);
               demoPopUp = new popupReport(popupStage);
               demoPopUp.activateWarningWindow(demoMessage);
               writeInfo("FALSE\n", Integer.toString(runTimes+1)+"\n", null);
             }else{
               demoPopUp = new popupReport(popupStage);
               String warningMessage = new String("Ваша демо версия истелка,"
                       + " пожалуйста приобретите лицензию");
               demoPopUp.activateWarningWindow(warningMessage);
           }
       }
    }
   /**
    * Call window with input key Text Field
    */
   public void callInputWindow(){
       demoPopUp.inputKeyWindow();
   }
   /**
    * Method for verification key supplied by user
    * and generated in the system
    */
   public void verify(){
    setKey();
    if(inputKey.equals(licenseKey)){
        activated = true;
        writeInfo("TRUE\n", Integer.toString(runTimes+1)+"\n", licenseKey);
        demoPopUp.successPopup("Поздравляем. Активация прошла успешно");
    }else{
       demoPopUp.successPopup("Ошибка ! Вы ввели неверный ключ ! "
               + "Попробуйте еще раз");
    }   
   }
   /**
    * Setting inputKey variable variable from input key window
    */
   public void setInputKey(){
       inputKey = new String();
       for (int i = 0; i<=5; i++){
           this.inputKey +=demoPopUp.getkey(i);
           if (i!=5) inputKey +="-";
       }
   }
   public Stage getPopupStage(){
       return popupStage;
   }/**
    * Calls readWriter to read data from input file and sets
    * stock variable
    */
   private void readData(){
       //test data 
    //  String testPath = "/home/igorbashka/Documents/ДокиМаша/test.xlsx";
    String testPath = "/home/igor/Documents/China/HDHardware/test.xlsx";
    int sheetNumber = 0;
    //
    String [] cells = {"a", "g", "b", "j", "k", "l","m","p"};
    setReadWriter(testPath, sheetNumber, cells);
    this.stock = readWriter.readFile(0, 50);
  }
   /**
    * Sets list of types of containers that we want to use
    */
   private void setContainers(){
      containers = new ArrayList();
       //test data so fat
       Container cont1 = new Container(25000, 27, new ArrayList<Item>());
       Container cont2 = new Container(25000, 56, new ArrayList<Item>());
       //Container cont3 = new Container(6000, 15, new ArrayList<Item>());
       containers.add(cont1);
       containers.add(cont2);
       //containers.add(cont3);
   }/**
    * Runs sorting of the stock and matching them with the containers
    */
   private void runSorting(){
       Optimizer optimizer = new Optimizer(stock, containers);
       optimizer.sort();
       this.finalContainers = optimizer.getContainers();
       this.sortedStock = optimizer.getStock();
   }/**
    * Writes output into an excel file
    */
   private void writeOutput(){
       //test data
       //String output = "/home/igorbashka/Documents/ДокиМаша/testOutput2.xlsx";
       String output = "/home/igor/Documents/China/testOutput2.xlsx";
       readWriter.setContainers(finalContainers);
       readWriter.writeOutput(output);
   }
   public void testRun(){
       readData();
       setContainers();
       runSorting();
       writeOutput();
   }
}
