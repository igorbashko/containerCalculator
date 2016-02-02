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
    private List <Container> containers;
    private double maxWeight; //weight capacity of containers list
    private double maxVolume; //volume capacity of container list
    private Container minContainer;
    private Stock minStock;
    private List <Container> finalContainers;
    private boolean notAdded; //variable to define if item was added into the 
    //container or not
    private boolean full;
    private Iterator <Item> iterator; //iterator over sorted items
    
    public Optimizer(Stock stock, List <Container> containers ){
        this.stock = stock;
        this.containers = containers;
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
          Map <Container, Stock> sortedContainers = new HashMap();
          this.finalContainers = new ArrayList();
          while(!stock.items.isEmpty()){
              for (Container c : containers){
                  Stock workStock = stock;
                  full = false;
                  while(!workStock.items.isEmpty() && !full ){
                  double idealRatio = c.getFreeSpacetRatio();
                  for (Item item: workStock.items){
                      item.setRationDiff(idealRatio);
                  }
                  full = true;//if nothing to put in container anymore
                  notAdded = true; // check if item was added into container
                  Collections.sort(workStock.items, new stockComparator());
                  iterator = workStock.items.iterator();
                  List <Item> removeItems = new ArrayList();
                  while(iterator.hasNext() && notAdded){
                  // removeItems.add(iterator.next());
                  //Item test = iterator.next();
                  addItem(c, iterator, workStock);    
                  }//end of third while loop
                }//end of second while loop
                sortedContainers.put(c, workStock);
                this.minContainer = c;
                this.minStock = workStock;
               }//end of for loop
             findMinLoad(sortedContainers, this.minContainer, this.minStock);
             finalContainers.add(this.minContainer);
             stock = this.minStock;
          }
      }
      /**
       * Method to add item into the container
       * @param cont Container where all items will go after sorting
       * @param item item which goes into the container
       * @param stock list of items to sort
       * @param full boolean variables defines if container is full or not
       * @param notAdded boolean variable defines if items was added into
       * the container or not 
       */
      private void addItem(Container cont, Iterator <Item> iterator, 
              Stock stock){
          Item currentItem = iterator.next(); //item in the iterator
          double freeVolume =cont.getVolumeLimit()-cont.getVolume();
          double freeWeight = cont.getWeightLimit() - cont.getWeight();
          if(currentItem.getSumVolume()<=freeVolume && currentItem.getSumWeight()<=
                  freeWeight){ 
              cont.addItem(currentItem);
              //stock.removeItem(item);
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
      private void findMinLoad(Map <Container, Stock> loadedVariants, Container 
              container, Stock stock){
         for(Map.Entry<Container, Stock> entry: loadedVariants.entrySet()){
             if(entry.getKey().getWeight()>minContainer.getWeight())
             container = entry.getKey();
             stock= entry.getValue();
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

