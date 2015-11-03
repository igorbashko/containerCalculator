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
public class Items {
    private String itemName;
    private double itemsQuantity;
    private double itemsInPack;
    private double numOfPacks;
    private double weightOfPack;
    private double sumWeight;
    private double volumeOfPack;
    private double sumVolume;

    public Items(String itemName, double itemsQuantity, double itemsInPack,
            double numOfPacks, double weightOfPacks, double sumWeight, 
            double volumeOfPacks, double sumVolume){
        
        this.itemName = itemName;
        this.itemsQuantity = itemsQuantity;
        this.itemsInPack = itemsInPack;
        this.numOfPacks = numOfPacks;
        this.weightOfPack = weightOfPacks;
        this.volumeOfPack = volumeOfPacks;
        this.sumVolume = sumVolume;
    }
    
    public String getName(){
        return itemName;
    }
    public double getQuantity(){
        return itemsQuantity;
    }
    public double getItemsInPack(){
        return itemsInPack;
    }
    public double getNumOfPacks(){
        return numOfPacks;
    }
    public double getWeightOfPacks(){
        return weightOfPack;
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
}
