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
        
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.numOfItems = numOfItems;
        this.itemsInPack = itemsInPack;
        this.numOfPacks = numOfPacks;
        this.grossWeightOfPack = grossWeightOfPack;
        this.netWeightOfPack = netWeightOfPack;
        this.volumeOfPack = volumeOfPack;
        this.sumWeight = grossWeightOfPack * numOfPacks;
        this.sumVolume = volumeOfPack * numOfPacks; 
        this.ratio = grossWeightOfPack/volumeOfPack;
        this.sumNetWeight = netWeightOfPack*numOfPacks;
        this.sumPrice = itemPrice*numOfItems;
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
    public void setRationDiff(double freeSpaceRatio){
        this.ratioDiff = abs(ratio - freeSpaceRatio);
    }
    public double getRatioDiff(){
        return ratioDiff;
    }
    public double getNetWeightOfPack(){
        return netWeightOfPack;
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
