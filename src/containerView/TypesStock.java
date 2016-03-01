/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package containerView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author igor
 */
public class TypesStock<E> extends ArrayList<E>{
    
private List <Observer> observers = new ArrayList<Observer>();    
    
    public boolean add(E e){
    boolean result = super.add(e);
    notifyObserver(); 
    return result;
    }
    
   public boolean remove(Object o){
       boolean result = super.remove(o);
       notifyObserver();
      return result;
   }
   /**
    * Adds observer to the list of observers
    * @param observer 
    */
  public void attach(Observer observer){
      this.observers.add(observer);
  } 
   public void notifyObserver(){
        for (Observer observer: observers){
            observer.update();
        }
    }
}
