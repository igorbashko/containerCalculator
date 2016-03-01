/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package containerView;

import java.util.List;

/**
 *
 * @author igor
 */
public abstract class Observer {
   protected List<containerInList> containersTypeList;
   public abstract void update();
}
