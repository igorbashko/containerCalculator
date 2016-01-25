/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package containerMath;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author igor
 */
public class Stock {
    protected List<Item> items;
    private double  ratio;
    //private double stockWeight;
    //private double stockVolume;
    //private double stockPrice;
    
    public Stock(){
    this.items = new ArrayList();
    }
    public void addItem(Item i){
        this.items.add(i);
    }
    public void removeItem(Item i){
        this.items.remove(i);
    }
    public int getSize(){
        return this.items.size();
    }
     public List<Item> getList(){
      return items;
    }
    public double getWeight(){
        double sumWeight = 0;
        for(Item i:items)
       sumWeight += i.getSumWeight();
        return sumWeight;
    }
    public double getVolume(){
        double stockVolume = 0;
        for (Item i:items){
            stockVolume +=i.getSumVolume();
        }
        return stockVolume;
    }
    public double getStockPrice(){
        double stockPrice = 0;
        for (Item i:items){
            stockPrice += i.getItemsPrice();
        }
        return stockPrice;
    }
public void setRatio(double ratio){
    this.ratio = ratio;
   }
//convert num of items to integer in order to avoid errors
public List<Item> splitItem(Item item){
    List<Item> splitedItem = new ArrayList();
    for(double i = item.getNumOfPacks(); i<=0; i--){
        Item itemNew = new Item(item.getName(), item.getPrice(),
        1, item.getItemsInPack(), item.getNumOfPacks(), item.getNetWeightOfPack(),
        item.getWeightOfPack(), item.getVolumeOfPack());
        splitedItem.add(itemNew);
        }
    return splitedItem;
  }
//Conbining items back
public Item sumItem(List <Item> list){
    int numOfPacks = list.size();
Item firstItem = list.get(0);
Item item = new Item(firstItem.getName(),firstItem.getPrice(),
firstItem.getNumOfItems()*numOfPacks, firstItem.getItemsInPack(), numOfPacks, 
firstItem.getNetWeightOfPack(), firstItem.getWeightOfPack(),
firstItem.getVolumeOfPack());   
return item;   
} 
}
