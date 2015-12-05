/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package containerController;

/**
 *This class will be used in singeltone pattern
 * @author igor
 */
public class controller {
    private static controller cont = null;
    
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
   }
