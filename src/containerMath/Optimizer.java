/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package containerMath;

import java.io.File;
import static java.lang.Math.abs;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author igor
 */
public class Optimizer {
    private Stock stock;
    private File sourceFile;
    private List <Container> containers;
    //private List <Container> twenties;
    
    public Optimizer(File file){
        this.sourceFile = file;
    }
    /**
    * Match item in the stock to a particular
    * type of the container 
    * @return index of the type of the container 
    */
   private int matchContainer(double itemRatio, double [] cTypes ){
       double min = abs(itemRatio - cTypes[0]);
       int i = 0;
       for(double c:cTypes){
           double min2 =abs(itemRatio - cTypes[i]);
           if(min2<min) min = min2;
           i++;
       }
       return i;
   }
   /**
    * Disjoint items of one stock to several containers.
    * Each item goes to the container for which it fits best
    * @param stock Stock of items read from excel file
    * @param containers array with different types of containers
    * where all items will be sorted. Should be containers List
    * which we declared above
    */
   public void fitToTypes(Stock stock, List <Container> containers){
       double[] cTypes = new double[3];
       int i = 0;
       for(Container c : containers){
           cTypes[i] = c.getRatio();
           i++;
       }
       for(Item item:stock.getList()){
          int matchedType = matchContainer(item.getRatio(), cTypes);
          containers.get(matchedType).addItem(item);
       }
   }
   //stock shuld be another type of stock
   public void sortForContainer(){
       Collections.sort(stock.items, new stockComparator());
       
   }
}
