/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package containerController;

import containerView.view;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.stage.Stage;
import org.apache.poi.*;
import org.apache.poi.ss.util.CellReference;
    

/**
 *This class will be used in singeltone pattern
 * @author igor
 */
public class controller {
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
    private String licenseKey =new String();
    private String uniqueId = new String();
    
            private controller(){};
    public static controller getController(){
        if(cont == null){
            cont = new controller();
        }
        return cont;
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
    variable is returned otherwise true
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
        info2Path = new String(System.getenv("APPDATA")+"\\containerCalculator\\info1.txt");
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
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    /**
     * Writes content to the info file
     */
     public void writeInfo(String activated, String runTimes, String licenseKey){
      String message = new String(activated +"\\n"+runTimes +"\\n"+licenseKey+"\\n");
      /*String uniqueId = encryption.getUniqueId();
      message+=uniqueId+"\\n";
      */
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
       String decryptedData = encryption.decryptMessage(info1Path, info2Path);
       licenseInfo info = new licenseInfo(decryptedData);
       activated = info.getActivated();
       runTimes = info.getRunTimes();
       licenseKey = info.getKey();
       uniqueId = info.getId();
   }
   /**
    * Starting the application
    */
   public void startApp(Stage stage){
       view newView = new view();
       newView.start(stage);
   }
   /**
    * Define existence of configuration file and creating a new file 
    * if there is no config
    */
   private void startDemo(){
       if (infoExist()){
           
       }
   }
}
