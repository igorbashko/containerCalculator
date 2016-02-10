/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package containerMath;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author igor
 */
public class Container extends Stock {
private double weightLimit;
private double volumeLimit;
    
       public Container(double weightLimit, double volumeLimit, List <Item> items){
           super(items);
           this.weightLimit = weightLimit;
           this.volumeLimit = volumeLimit;
       } 
       public double getRatio(){
           return weightLimit/volumeLimit;
           
       }
       /**
        * Returns weight limit of the container
        * @return weight limit
        */
       public double getWeightLimit(){
           return weightLimit;
       }
       /**
        * Returns volume limit of the container
        * @return volume limit
        */
       public double getVolumeLimit(){
           return volumeLimit;
       }
       /**
        * Gets the ratio of free space in the container.
        * The value is used to find bets fit item
        * @return free space ratio
        */
       public double getFreeSpacetRatio(){
           double ratio = getRatio()*(this.getList().size()+1) - this.sumRatio();
                   
           /*double ratio = (this.weightLimit-this.getWeight())/
                   this.volumeLimit - this.getVolume();*/
           return ratio;
       }
       public double getRatio2(){
           return this.getWeight()/this.getVolume();
       }
       public int size2(){
           return this.getList().size()+1;
       }
       /**
        * Returns free kilos of the container
        * @return free kilos of the container
        */
       public double getFreeWeight(){
           return weightLimit - getWeight();
       }
       /**
        * Returns free volume into the container
        * @return free cubes in the container
        */
       public double getFreeVoolume(){
           return volumeLimit - getVolume();
       }
}
