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
public class stockComparator implements Comparator<Item> {

    @Override
    public int compare(Item item1, Item item2) {
        return item1.getRatioDiff()<item2.getRatioDiff() ? -1:
               item1.getRatioDiff()==item2.getRatioDiff() ? 0:1;
    }
   
}
