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
import java.util.List;

/**
 *
 * @author igor
 */
public class Optimizer {
    private Stock stock;
    private List <Container> containers;
      
    public Optimizer(Stock stock){
        this.stock = stock;
    }
    /**
     * Method for finding optimal number of containers for each type 
     * of the containers
     */
    public void findContainers(){
      // number of containers to fill the volume
      int nFvolume = rnc1(stock.getVolume() , containers.get(0).getVolumeLimit());
      //number of containers to fill the weight of the stock
      int nFweight = rnc1(stock.getWeight(), containers.get(0).getWeight());
      //Final number of containres 
      int nFi = rnc2(nFweight, nFvolume);
      double sumVolume = nFi*containers.get(0).getVolume();
      double sumWeight = nFi*containers.get(0).getWeight();
    }
    /**
     * Return number of containers to satisfy stock value(weight or volume)
     * @param stockCapacity capacity of all items in the stock
     * @param containerCapacity capacity of the container
     * @return number of containers to satisfy(volume or weight)
     * stock value. Defines containers according to the weight 
     * or volume capacity of the stock. Does not return final number
     * or the containers
     */
    private int rnc1(double stockCapacity, double containerCapacity){
        if(stockCapacity%containerCapacity == 0)
            return (int) (stockCapacity/containerCapacity);
        else
            return (int) (stockCapacity/containerCapacity)+1;
        }
    /**
     * Returns number of containers to upload everything there
     * @param contFweight containers to fill weight need
     * @param contFvolume containers to fill volume need
     * @return containers we need to fill the stock
     */
      private int rnc2(int contFweight, int contFvolume){
          if(contFweight > contFvolume)
              return contFweight;
          else
              return contFvolume;
      }
      /**
       * Find numbers of each type of containers
       * @param max number of containers of 1 type
       * @param maxWeight weight capacity which we get from 1 container
       * @param maxVolume volume capacity which we get from 1 container
       * @return list and number of types of containers which we need to empty our
       * stock with optimal sorting
       */
      private List <Container> findNumbers(int max, double maxWeight, double maxVolume){
          List <Container> conts = new ArrayList();
           for(int i=1; i<containers.size(); i++){
           int maxC =max; //variable for max compare to define optimal value of containers
           int n = 1;
           while(maxC != 0){
               //containers array should be changed
                double realWeight = maxWeight - n*conts.get(i-1).getWeight()+
                        conts.get(i).getWeight();
                double realVolume = maxVolume - n*conts.get(i-1).getVolume()+
                        conts.get(i).getVolume();
                
            }
       }
          return containers;
      }
      
      private boolean longCondition(double stockWeight, double stockVolume,
              double maxWeight, double maxVolume, double realWeight,
              double realVolume){
          if((realWeight <maxWeight && realVolume<maxVolume) && (realWeight>=
                  stockWeight && realVolume >= stockVolume))
              return true;
          else 
              return false;
      }
}
