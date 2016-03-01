/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package containerView;

import java.util.List;
import javafx.scene.control.ListView;

/**
 *Modifies the list view with types of containers
 * @author igor
 */
public class ListModifier extends Observer {
    private ListView listView;
    
    public ListModifier(List<containerInList> typesList, ListView listView){
        this.containersTypeList = typesList;
        this.listView = listView;
   }

    @Override
    public void update() {
      //listView
    }
    
}
