/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package containerView;

/**
 * Stores names and capacities of the containers 
 * @author igor
 */
public class containerInList {
     
    private String name; // name of the container
    private int kg;
    private int m3;
    
 public containerInList(String name, int kg, int m3){
     this.name = name;
     this.kg = kg;
     this.m3 = m3;
 }
 public String getName(){
     return this.name;
  }
 public int getKg(){
     return this.kg;
 }
 public int getM3(){
     return this.m3;
 }
}
