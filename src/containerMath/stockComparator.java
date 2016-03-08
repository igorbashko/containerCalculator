/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package containerMath;

import java.util.Comparator;

/**
 *
 * @author igor
 */
public class stockComparator implements Comparator <Item>{

    @Override
    public int compare(Item i1, Item i2) {
        if(i1.getRatioDiff()< i2.getRatioDiff())
            return -1;
        else if(i1.getRatioDiff() > i2.getRatioDiff())
            return 1;
        else 
            return 0;
        /*
        return i1.getRatioDiff()< i2.getRatioDiff() ? -1:
                i1.getRatioDiff()  == i2.getRatioDiff() ? 0:1;*/
    }
    
}
