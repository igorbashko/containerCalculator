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
public class Container extends Stock {
private double weightLimit;
private double volumeLimit;
    
       public Container(double weightLimit, double volumeLimit){
           this.weightLimit = weightLimit;
           this.volumeLimit = volumeLimit;
       } 
            
}
