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
    private List<Item> items;
    private double  ratio;
        
    public Stock(List <Item> items){
    this.items = new ArrayList(items);
    }
    public void addItem(Item i){
        this.items.add(i);
    }
    public void addAll(List<Item> items){
        this.items.addAll(items);
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
    //Comment later
    public double sumRatio(){
        double sumRatio = 0;
        for (Item item: items)
            sumRatio+=item.getRatio();
        return sumRatio;
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
/**
 * Splits item by packs to add into the container one by one
 * @param item Item to split
 * @return array of items with multiple values of 1 item but with 1 pack
 */
public List<Item> splitItem(Item item){
    List<Item> splitedItem = new ArrayList();
    int numberOfItems = (int) item.getNumOfPacks();
   for( int i = numberOfItems; i>0; i--){
        Item itemNew = new Item(item.getName(), item.getPrice(),
        item.getItemsInPack(), item.getItemsInPack(), 1, item.getNetWeightOfPack(),
        item.getWeightOfPack(), item.getVolumeOfPack());
        splitedItem.add(itemNew);
        }
    return splitedItem;
  }
/**
 * Combines items of one type from array into 1 item(Back function after split func)
 * @param list list of items to sum
 * @return summed item
 */
public Item sumItem(List <Item> list){
    int numOfPacks = list.size();
Item firstItem = list.get(0);
Item item = new Item(firstItem.getName(),firstItem.getPrice(),
firstItem.getNumOfItems()*numOfPacks, firstItem.getItemsInPack(), numOfPacks, 
firstItem.getNetWeightOfPack(), firstItem.getWeightOfPack(),
firstItem.getVolumeOfPack());   
return item;   
   } 
public List<Item> getItems(){
    return this.items;
}
}
