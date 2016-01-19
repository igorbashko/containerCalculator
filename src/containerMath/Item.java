/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package containerMath;

/**
 *
 * @author igor
 */
/*Class for sorting items in a list from excel*/
public class Item {
    private String itemName;
    private double numOfItems;
    private double itemsInPack;
    private double numOfPacks;
    private double grossWeightOfPack;
    private double sumWeight;
    private double volumeOfPack;
    private double sumVolume;
    private double itemPrice;
    private double netWeightOfPack;
    private double sumNetWeight;
    private double sumPrice;
    private double ratio;

    public Item(String itemName, double itemPrice, double numOfItems, double itemsInPack,
            double numOfPacks, double grossWeightOfPacks, double netWeightOfPack,
            double volumeOfPack){
        
        this.itemName = itemName;
        this.numOfItems = numOfItems;
        this.itemsInPack = itemsInPack;
        this.numOfPacks = numOfPacks;
        this.grossWeightOfPack = grossWeightOfPacks;
        this.netWeightOfPack = netWeightOfPack;
        this.volumeOfPack = volumeOfPack;
        this.itemPrice = itemPrice;
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
    public double getNumOfPacks(){
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
}
