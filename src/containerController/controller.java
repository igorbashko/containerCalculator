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
       return "Empty string";
    }
   }
