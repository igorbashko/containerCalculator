/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package containerMath;

import java.io.File;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author igor
 */
public class Optimizer {
    private Stock stock;
    private List <Container> typesOfContainers;
    private Container minContainer;//container with minimum wasted spaces 
    //among all avialable containers
    private Stock minStock;//stock which corresponds to the minimum container
    private List <Container> finalContainers;
    private boolean notAdded; //variable to define if item was added into the 
    //container or not
    private boolean full;
    private Iterator <Item> iterator; //iterator over sorted items
    private List<Container> workContainers; //list of containers to work with
    private Stock workStock; //place where we store types of containers to add
    //items into them later
    
    public Optimizer(Stock stock){
        this.stock = stock;
        //this.typesOfContainers = typesOfContainers;
    }
    /**
     * Sets types of containers specified by user in the interface
     * @param setOfContainers List of types of containers specified by user
     */
    public void setCont(List<Container> setOfContainers){
        this.typesOfContainers = setOfContainers;
    }
      /**
       * Main sorting methods. Takes list of items to sort checks optimal
       * load against each type of containers and chooses container with
       * less wasted value of items. Adds final value in the final list
       * of containers
       * @param stock Our list of items
       * @param containers list of types of containers to sort
       */     
      public void sort(){
          this.finalContainers = new ArrayList();
          while(!stock.getList().isEmpty()){
          Map <Container, Stock> sortedContainers = new HashMap();
             this.workContainers = new ArrayList();
             for (Container c: typesOfContainers){
              Container cont = new Container(c.getWeightLimit(), c.getVolumeLimit(), 
                      new ArrayList<Item>());
              workContainers.add(cont);
          }
              for (Container c : workContainers){
                  List <Item> items = new ArrayList();
                  items.addAll(stock.getList());
                  workStock = new Stock(items);
                  full = false;
                  while(!workStock.getList().isEmpty() && !full ){
                      double idealRatio;
                      if(c.getFreeWeight() != c.getWeightLimit() && c.getFreeVoolume() != c.getVolumeLimit()){
                      idealRatio = c.getRatio();
                      }else
                      {
                      idealRatio = c.getFreeSpacetRatio();
                      }
                  for (Item item: workStock.getList()){
                      double itemRatio = item.getRatio();
                      item.setRationDiff(idealRatio,c.getRatio2(),c.size2(), itemRatio);
                  }
                  full = true;//if nothing to put in container anymore
                  notAdded = true; // check if item was added into container
                  Collections.sort(workStock.getList(), new stockComparator());
                  iterator = workStock.getList().iterator();
                  while(iterator.hasNext() && notAdded){ 
                  addItem(c, iterator, workStock);    
                  }//end of third while loop
                }//end of second while loop
                sortedContainers.put(c, workStock);
                minContainer = c;
                minStock = workStock;
               }//end of for loop
             findMinLoad(sortedContainers);
             finalContainers.add(minContainer);
             stock = minStock;
          }
      }
      /**
       * Method to add item into the container
       * @param cont Container where all items will go after sorting
       * @param item item which goes into the container
       * @param stock list of items to sort
       */
      private void addItem(Container cont, Iterator <Item> iterator, 
              Stock stock){
          Item currentItem = iterator.next(); //item in the iterator
          double freeVolume =cont.getVolumeLimit()-cont.getVolume();
          double freeWeight = cont.getWeightLimit() - cont.getWeight();
          if(currentItem.getSumVolume()<=freeVolume && currentItem.getSumWeight()<=
                  freeWeight){ 
              cont.addItem(currentItem);
              iterator.remove();
              full = false;
              notAdded = false;
            } else if(currentItem.getWeightOfPack()<=freeWeight && currentItem.getVolumeOfPack()<=
                    freeVolume){
                List <Item> splitedItem = new ArrayList(stock.splitItem(currentItem));
                List <Item> splitedItemsB = new ArrayList(); //items to go into
                while(currentItem.getWeightOfPack()<=freeWeight && currentItem.getVolumeOfPack()<=
                        freeVolume){
                    splitedItemsB.add(splitedItem.get(0));
                    splitedItem.remove(0);
                    freeWeight-= currentItem.getWeightOfPack();
                    freeVolume-= currentItem.getVolumeOfPack();
               }
               iterator.remove();
               cont.addItem(stock.sumItem(splitedItemsB));
               stock.addItem(stock.sumItem(splitedItem));
               full = false;
               notAdded = false;
            }
      }
      /**
       * Defines which of the sorted containers fits best for optimal loading
       * @param loadedVariants Map of sorted containers and stock with left items
       * @param container Best fit container
       * @param stock stock correspondes to best fit 
       */
      private void findMinLoad(Map <Container, Stock> loadedVariants){
          for(Map.Entry<Container, Stock> entry: loadedVariants.entrySet()){
             double weight = entry.getKey().getFreeWeight()/1000;
             double volume = entry.getKey().getFreeVoolume();
             double sum = weight + volume; //sum of free space against which 
             //to compare
             double minSum = minContainer.getFreeVoolume()+ 
                     minContainer.getFreeWeight()/1000;//current minimum sum of
             //free space
             if(sum<minSum){
             minContainer = entry.getKey();
             minStock= entry.getValue();
             }
         }
      }
      /**
       * Returns sorted stock, will be empty most of the times
       * @return sorted stock
       */
      public Stock getStock(){
          return stock;
      }
      /**
       * Returns list with sorted containers
       * @return list of sorted containers
       */
      public List<Container> getContainers(){
          return finalContainers;
      }
      
       }

