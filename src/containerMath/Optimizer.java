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
    private double maxWeight; //weight capacity of containers list
    private double maxVolume; //volume capacity of container list
   
      
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
      this.maxWeight = sumWeight;
      this.maxVolume = sumVolume;
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
       * Initialize conts for further work
       */
      private List <Container> findNumbers(int max, double maxWeight, double maxVolume){
          List <Container> conts = new ArrayList();
          //List of containers for experimental iterations
          List <Container> contsEx = new ArrayList(conts); 
           for(int i=1; i<containers.size(); i++){
           //single replace item in both orders(write a method later)
    for (Container c : contsEx) 
        singleOrder(contsEx, conts, c, containers.get(i));
    contsEx =conts;
       for(Container c: new backIterator<Container>(contsEx))
           singleOrder(contsEx, conts, c, containers.get(i));
      }
    return containers;
   }
      /**
       * Checks if formed list of containers satisfies loading conditions
       * @param stockWeight Weight of all items in the stock
       * @param stockVolume Volume of all items in the stock
       * @param maxWeight weight capacity of the given list of containers
       * @param maxVolume volume capacity of the given list of containers
       * @param realWeight weight capacity of the formed weight of containers
       * @param realVolume volume capacity of the formed volume of containers
       * @return true if formed list satisfies loading conditions or 
       * false if not
       */
      private boolean checkLoad(double stockWeight, double stockVolume,
              double maxWeight, double maxVolume, double realWeight,
              double realVolume){
          if((realWeight <maxWeight && realVolume<maxVolume) && (realWeight>=
                  stockWeight && realVolume >= stockVolume))
              return true;
          else 
              return false;
      }
      /**
       * Implements single iteration over set of containers
       * Replace containers one by one and with only 1 item of container in
       * the containers list check if there optimal improvements
       * @param contsEx Experimental list of containers
       * @param conts list of containers with best optimal value
       * @param c iterated container
       * @param typeToChange container which type we checking
       */
      private void singleOrder(List<Container> contsEx, List <Container> conts,
              Container c, Container typeToChange){
          contsEx.remove(c);
          contsEx.add(typeToChange);
          //weight capacity of modified list
          double weightCap = getSumCapacity(contsEx, "weight");
          //volume capacity of modified list
          double volumeCap = getSumCapacity(contsEx, "volume");
          boolean checkLoad = checkLoad(stock.getWeight(), stock.getVolume(),
                  maxWeight, maxVolume, weightCap, volumeCap);
          if(checkLoad) {
              conts = contsEx;
              maxWeight = weightCap;
              maxVolume = volumeCap;
          }
          contsEx.remove(typeToChange);
          contsEx.add(c);
      }
      /**
       * Gets load volume and weight of the container list
       * @param list containers list
       * @param param weight or volume. Defines what to return weight or volume
       * @return weight or volume
       */
      private double getSumCapacity(List<Container> list, String param){
          double sumWeight = 0;
          double sumVolume = 0;
          for(Container c: list){
              sumVolume +=c.getVolumeLimit();
              sumWeight+=c.getWeightLimit();
          }
          if(param.trim() =="weight") return sumWeight;
          else if(param.trim() =="volume") return sumVolume;
          else return 0;
       }
}
