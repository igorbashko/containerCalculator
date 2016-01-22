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
    
       public Container(double weightLimit, double volumeLimit){
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
}
