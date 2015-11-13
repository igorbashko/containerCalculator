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
   private double allWeight; // weight of all items which we need to sort
   private double allVolume; // volume of all items which we need to sort
   private double numOfContainers; //number of containers to sort the items
   private int numOfFourties;
   private int numOfTwenties;
   private double capacityFourty; // capacity of 40ft container
   private int lastRow;
    //private Instances itemsList;
    //private Cell cell;
    /*
    Reading excell file method
    */
   /*Method to get weight of all elements*/
    private void allWeight(){
        for (Items item:items){
            allWeight = item.getSumWeight();
        }
    } 
    /*Get volume of all elements*/
    private void allVolume(){
        for (Items item: items){
            allVolume = item.getSumVolume();
        }
    }
  public void setFourty(double fourty){
      this.capacityFourty = fourty;
  }  
  public void findContainers(){
      if(allVolume/(numOfContainers*volumeCapacity)<=1){
          numOfTwenties = (int) numOfContainers;
      }else if(allVolume/(numOfContainers*capacityFourty) == 1){
          numOfFourties = (int) numOfContainers;
      }else if(allVolume/(numOfContainers*capacityFourty) >1){
          System.out.println("You need a vagon, not enough space");
      }
      else if(allVolume/(numOfContainers*volumeCapacity)>1 && allVolume/(numOfContainers*capacityFourty)<1){
          numOfFourties = 1;
          while(allVolume/(numOfContainers*volumeCapacity)>1){
              allVolume = allVolume - numOfFourties*capacityFourty;
              numOfContainers--;
              numOfFourties++;
          }
          numOfTwenties = (int) numOfContainers;
      }
  }
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
            if (item.getNumOfPacks()!=0){
            items.add(item);
            ratios.add(item.getRatio());
             }
            }
           numberOfItems = items.size();
           allWeight();
           allVolume();
           } catch (IOException ex) {
            ex.printStackTrace();
        }
            } catch (InvalidFormatException ex) {
                ex.printStackTrace();
            }
    }
    
    public void getNumberOfContainers(){
        if(allWeight % weightCapacity == 0){
            numOfContainers = allWeight/weightCapacity;
        }else{
            numOfContainers = (int) allWeight/weightCapacity+1;
        }
    }
    private Sheet writeOutput(List <Items> rightItems, Sheet sheet, int lastRow){
        this.lastRow = lastRow;
        Row row1 = sheet.createRow(0);//creating headings
        Cell name = row1.createCell(0); name.setCellValue("Наименование"); 
        Cell quantity = row1.createCell(1); quantity.setCellValue("Количество");
        Cell inPacks = row1.createCell(2); inPacks.setCellValue("Количество в упаковке");
        Cell numOfPacks = row1.createCell(3); numOfPacks.setCellValue("Количество упаковок");
        Cell weightOfPack = row1.createCell(4); weightOfPack.setCellValue("Вес коробки");
        Cell weightPacks = row1.createCell(5); weightPacks.setCellValue("Суммарный вес");
        Cell volumeOfPack = row1.createCell(6); volumeOfPack.setCellValue("Объем коробки");
        Cell volumeOfPacks = row1.createCell(7); volumeOfPack.setCellValue("Суммарный объем");
        for(int i= lastRow; i<=rightItems.size(); i++){
            Row rowN = sheet.createRow(i);
            name.setCellValue(rightItems.get(i).getName());
            quantity.setCellValue(rightItems.get(i).getQuantity());
            inPacks.setCellValue(rightItems.get(i).getItemsInPack());
            numOfPacks.setCellValue(rightItems.get(i).getNumOfPacks());
            weightOfPack.setCellValue(rightItems.get(i).getWeightOfPacks());
            weightPacks.setCellValue(rightItems.get(i).getSumWeight());
            volumeOfPacks.setCellValue(rightItems.get(i).getVolumeOfPack());
            volumeOfPack.setCellValue(rightItems.get(i).getSumVolume());
        }
        return sheet;
    }
    /*Writing output to system.out to test the method*/
     public void findClosest(){
        double ideal = 80;
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
        items.remove(index);
        System.out.println(items.get(index).getName()+" "+items.get(index).getSumWeight()+" "+items.get(index).getSumVolume()+"\n");
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
    private void setLeftWeight(){
         this.weightLeft = weightCapacity - sumWeight;
               
    }
    private double formula(Items item, double count){
        return (item.getRatio()+sumWeight/sumVolume)/count;
    }
    private void setLeftVolume(){
        this.volumeLeft = volumeCapacity - sumVolume;
    }
    /*Method for sorting items to fit in the container*/
    public void sortItems(int containerType){
        while(numOfContainers!=0){
            if(numOfTwenties != 0){
                numOfTwenties--;
            }else{
                this.volumeCapacity = capacityFourty;
            }
        rightListInitialize();
        double diff; // ratio difference
        double idealRatio = weightCapacity/volumeCapacity;
        double itemVolume;
        double itemWeight;
        setLeftVolume();
        setLeftWeight();
        int numOfSortItems = 2; // number of sorted items for calculation
             for(int i=0; i<=numberOfItems; i++){
                 itemVolume = items.get(0).getSumVolume();
                 itemWeight = items.get(0).getSumWeight();
                 double min = abs(idealRatio - formula(items.get(0), numOfSortItems));
                 index = 0;
                 int count = 0;
                 for(Items item:items){
                     count ++;
                     double b = item.getRatio();
                     diff = abs(idealRatio - formula(item, numOfSortItems));
                     if (diff<min && item.getSumWeight()<weightLeft &&
                             item.getSumVolume()<volumeLeft){
                         
                         min = diff;
                         index = count-1;
                         itemWeight = item.getSumWeight();
                         itemVolume = item.getSumVolume();
                         }
                       }
                 if(itemVolume<volumeLeft && itemWeight<weightLeft){
                     sortedItems.add(items.get(index));
                     sumWeight = sumWeight + items.get(index).getSumWeight();
                     sumVolume = sumVolume + items.get(index).getSumVolume();
                     items.remove(index);
                     setLeftVolume();
                     setLeftWeight();
                     numOfSortItems++;
                     System.out.println(items.get(index-1).getName()+" "+items.get(index-1).getSumWeight()+" "+items.get(index-1).getSumVolume()+"\n");
                  }
                 }
             System.out.println(volumeLeft+" " + weightLeft+" " +sumWeight + " " +sumVolume);
             numOfContainers--;
        }
    }
    public void printUnsorted(){
        for(Items item: items){
            System.out.println(item.getName()+" "+item.getSumWeight()+" "+item.getSumVolume()+"\n");
        }
    }
}
