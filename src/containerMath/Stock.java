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
    //private double stockWeight;
    //private double stockVolume;
    //private double stockPrice;
    
    public Stock(){
    this.items = new ArrayList();
    }
    public void addItem(Item i){
        this.items.add(i);
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
}

    