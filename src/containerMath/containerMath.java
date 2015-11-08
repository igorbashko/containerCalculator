/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package containerMath;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.openxml4j.opc.OPCPackage;
import java.io.File;
import java.io.IOException;
import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import java.lang.Math.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.AbstractList;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
/*
import weka.core.Instance;
import weka.core.Instances;
import weka.core.DenseInstance;
import weka.core.neighboursearch.LinearNNSearch;
*/
/**
 *
 * @author igorbashka
 */
public class containerMath {
    private List<Items> items;
   private List<Double> ratios;
   private int index; //index of a needed for sorting item
   private List<Items> sortedItems; 
   private double sumWeight; //sum weight of all matched elements
   private double sumVolume;// sum volume of all matched elements
   private double weightLeft;// left weight in the container
   private double volumeLeft; // left volume in the container
   private double weightCapacity; // weight capacity of the container
   private double volumeCapacity; // volume capacity of the container
   private int numberOfItems; // number of items to sort
    //private Instances itemsList;
    //private Cell cell;
    /*
    Reading excell file method
    */
    public void readFromExcel(){
        try {
            OPCPackage pkg;
            try {
            pkg = OPCPackage.open(new File("/home/igor/Documents/China/HDHardware/all the goods in  251018+251019-1+251019-2 обновление_change_4.xlsx"));
                            
            XSSFWorkbook book = new XSSFWorkbook(pkg);
            Sheet sheet1 = book.getSheetAt(0);
            items = new ArrayList<Items>();
            ratios = new ArrayList<Double>();
            //itemsList = new Instances();
            for (int n=0; n<96; n++){
            Row row = sheet1.getRow(n);
            Items item = new Items(row.getCell(2).toString(),row.getCell(3).getNumericCellValue(),
                    row.getCell(7).getNumericCellValue(), row.getCell(8).getNumericCellValue(),
            row.getCell(10).getNumericCellValue(), row.getCell(11).getNumericCellValue(),
            row.getCell(12).getNumericCellValue(), row.getCell(13).getNumericCellValue());
            items.add(item);
            ratios.add(item.getRatio());
            }
           numberOfItems = items.size();
           } catch (IOException ex) {
            ex.printStackTrace();
        }
            } catch (InvalidFormatException ex) {
                ex.printStackTrace();
            }
    }
    
    /*Writing output to system.out to test the method*/
    public void testWrite(){
        System.out.println(items.get(25).getName()+"  "+items.get(25).getSumVolume());
    }
    public void findClosest(){
        double ideal = 1.4;
        double dif;
        double min = ratios.get(0)-ideal;
        int n = 0;
        for(double ratio: ratios){
            dif = abs((ratio-ideal));
            n++;
         //min = min(min, dif);
            if(dif< min){
                min = dif;
                index = n;
             }
        }
        sumWeight = items.get(index).getSumWeight();
        sumVolume = items.get(index).getSumVolume();
    }
    
    /*Initializing the right list*/
    public void rightListInitialize(){
        sortedItems = new ArrayList<Items>(); 
    }
    
    /*Adding item to the right list*/    
    public void addToRightList(){
        sortedItems.add(items.get(index));
    }
    public void setWeightCapacity(double weightCapacity){
        this.weightCapacity = weightCapacity;
    }
    public void setVolumeCapacity(double volumeCapacity){
        this.volumeCapacity = volumeCapacity;
    }
   /*Method for getting left weight*/
    private double getLeftWeight(){
                this.weightLeft = weightCapacity - sumWeight;
                return weightLeft;
    }
    private double formula(Items item, int count){
        return (item.getRatio()+sumWeight/sumVolume)/count;
    }
    private double getLeftVolume(){
        this.volumeLeft = volumeCapacity - sumVolume;
        return volumeLeft;
    }
    /*Method for sorting items to fit in the container*/
    public void sortItems(){
        double diff = 0;
        double idealRatio = 80;
        double itemVolume;
        double itemWeight;
             for(int i=0; i<=numberOfItems; i++){
                 double min = abs(idealRatio - items.get(0).getRatio());
                 index = 0;
                 int count = 0;
                 for(Items item:items){
                     count ++;
                     diff = abs(idealRatio - item.getRatio());
                     if (diff<min && item.getSumWeight()<getLeftWeight() &&
                             item.getSumVolume()<getLeftVolume()){
                         
                         min = diff;
                         index = count-1;
                         itemWeight = item.getSumWeight();
                         itemVolume = item.getSumVolume();
                         }
                       }
                 
             }
    }
}
