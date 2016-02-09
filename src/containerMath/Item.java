/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package containerMath;

import static java.lang.Math.abs;
import java.util.Iterator;

/**
 *
 * @author igor
 */
/*Class for sorting items in a list from excel*/
public class Item implements Iterable {
    private String itemName;
    private double numOfItems;
    private double itemsInPack;
    private int numOfPacks;
    private double grossWeightOfPack;
    private double sumWeight;
    private double volumeOfPack;
    private double sumVolume;
    private double itemPrice;
    private double netWeightOfPack;
    private double sumNetWeight;
    private double sumPrice;
    private double ratio;
    private double ratioDiff;

    public Item(String itemName, double itemPrice, double numOfItems, double itemsInPack,
            int numOfPacks, double netWeightOfPack, double grossWeightOfPack,
            double volumeOfPack){
        this.itemName = checkName(itemName);
        this.itemPrice = checkRest(itemPrice);
        this.numOfItems = checkRest(numOfItems);
        this.itemsInPack = checkRest(itemsInPack);
        this.numOfPacks = checkNumberOfPacks(numOfPacks);
        this.grossWeightOfPack = checkRest(grossWeightOfPack);
        this.netWeightOfPack = checkRest(netWeightOfPack);
        this.volumeOfPack = checkRest(volumeOfPack);
        this.sumWeight = checkRest(grossWeightOfPack * numOfPacks);
        this.sumVolume = checkRest(volumeOfPack * numOfPacks); 
        this.ratio = checkRest(grossWeightOfPack/volumeOfPack);
        this.sumNetWeight = checkRest(netWeightOfPack*numOfPacks);
        this.sumPrice = checkRest(itemPrice*numOfItems);
    }
    
    public String getName(){
        return itemName;
    }
    public double getNumOfItems(){
        return numOfItems;
    }
    public double getItemsInPack(){
        return itemsInPack;
    }
    public int getNumOfPacks(){
        return numOfPacks;
    }
    public double getWeightOfPack(){
        return grossWeightOfPack;
    }
    public double getSumWeight(){
        return sumWeight;
    }
    public double getVolumeOfPack(){
        return volumeOfPack;
    }
    public double getSumVolume(){
        return sumVolume;
    }
    public double getRatio(){
        return ratio;
    }
    public double getPrice(){
        return itemPrice;
    }
    public double getSumNetWeight(){
        return sumNetWeight;
    }
    public double getItemsPrice(){
        return sumPrice;
    }
    public void setRationDiff(double idealRatio, double sumRatio, int size, double itemRatio){
        this.ratioDiff = abs(idealRatio-(itemRatio + sumRatio)/size);
    }
    public double getRatioDiff(){
        return ratioDiff;
    }
    public double getNetWeightOfPack(){
        return netWeightOfPack;
    }
    /**
     * Checks if there wrong parameter of the name of item
     * @param value name from input file
     * @return name 
     * or error message instead
     */
   private String checkName(String value){
    try{
        return value;
    }catch (Exception ex){
     return "Не удалост распознать имя позиции";
    }
}
   /**
    * Checks parameter in excel file 
    * @param number number to check
    * @return number in case if everything is fine 
    * or 0 instead
    */
   private int checkNumberOfPacks(int number){
     try{
     return number;
     }catch(Exception ex){
    return 0;
   }
}
   /**
    * Checks parameter in excel file 
    * @param number number to check
    * @return number in case if everything is fine 
    * or 0 instead
    */
   private double checkRest(double number){
       try{
           return number;
       }catch(Exception ex){
           return 0;
       }
   }
       @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * Matching cells with apache poi indexes
     * @param cells Array with cell names in excel file
     * The order of columns is following:
     * ----------------------------------
     * Item name                                      0
     * Item price                                     1 
     * Number of items                                2
     * Number of items in pack                        3
     * Number of packs                                4
     * Net weight of 1 pack                           5  
     * Gross weight of 1 pack                         6
     * Volume of 1 pack                               7
     * Sum net weight(will be calculated further)     4*5
     * Sum gross weight(will be calculated further)   4*6
     * Sum volume(will be calculated further)         4*7
     */
 }
