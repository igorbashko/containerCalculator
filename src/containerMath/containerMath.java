/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package containerMath;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.openxml4j.opc.OPCPackage;
import java.io.File;
import java.io.FileNotFoundException;
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
import java.io.FileOutputStream;
import containerController.controller;
import org.apache.poi.hssf.util.CellReference;
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
   private controller cont = controller.getController();
   
   /*Method to get weight of all elements*/
    private void allWeight(){
        allWeight =0;
        for (Items item:items){
            allWeight = allWeight + item.getSumWeight();
        }
    } 
    /*Get volume of all elements*/
    private void allVolume(){
        allVolume = 0;
        for (Items item: items){
            allVolume =allVolume + item.getSumVolume();
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
      numOfContainers = numOfFourties + numOfTwenties; 
  }
    public void readFromExcel(String pathToFile,String name, String range, String 
            numberOfItems, String inPack, String numberOfPacks, 
            String netWeight, String sumNetWeight, String grossWeight,
            String sumGrossWeight, String volumeOfPack, String sumVolume){
   //{
        cont.rowsRangeProcessing(range);
        int itemName = CellReference.convertColStringToIndex(name);
        int itemsQuantity = CellReference.convertColStringToIndex(numberOfItems);
        int inPackIndex = CellReference.convertColStringToIndex(inPack);
        int numOfPacksIndex = CellReference.convertColStringToIndex(numberOfPacks);
        int netWeightIndex = CellReference.convertColStringToIndex(netWeight);
        int sumNetWeightIndex = CellReference.convertColStringToIndex(sumNetWeight);
        int grossWeightIndex = CellReference.convertColStringToIndex(grossWeight);
        int sumGrossWeightIndex = CellReference.convertColStringToIndex(sumGrossWeight);
        int volumeOfPackIndex = CellReference.convertColStringToIndex(volumeOfPack);
        int sumVolumeIndex = CellReference.convertColStringToIndex(sumVolume);
        try {
            OPCPackage pkg;
            try {
            pkg = OPCPackage.open(new File(pathToFile));
          // pkg = OPCPackage.open(new File("/home/igor/Documents/China/HDHardware/test.xlsx"));
                            
            XSSFWorkbook book = new XSSFWorkbook(pkg);
            Sheet sheet1 = book.getSheetAt(0);
            items = new ArrayList<Items>();
            //itemsList = new Instances();
            for (int n=cont.getFirstNumber(); n<cont.getSecondNumber(); n++){
            Row row = sheet1.getRow(n);
            Items item = new Items(row.getCell(itemName).toString(),
                    row.getCell(itemsQuantity).getNumericCellValue(),
                    row.getCell(inPackIndex).getNumericCellValue(), 
                    row.getCell(numOfPacksIndex).getNumericCellValue(),
            row.getCell(grossWeightIndex).getNumericCellValue(), 
                    row.getCell(sumGrossWeightIndex).getNumericCellValue(),
            row.getCell(volumeOfPackIndex).getNumericCellValue(), 
                    row.getCell(sumVolumeIndex).getNumericCellValue());
            items.add(item);
            }
           this.numberOfItems = items.size();
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
    private void writeOutput(List <Items> rightItems, Sheet sheet, int lastRow){
      sheet.setColumnWidth(0, 13000);
        this.lastRow = lastRow;
        int j=0;
        Row row1 = sheet.createRow(0);//creating headings
        Cell name = row1.createCell(0); name.setCellValue("Наименование"); 
        Cell quantity = row1.createCell(1); quantity.setCellValue("Количество");
        Cell inPacks = row1.createCell(2); inPacks.setCellValue("Количество в упаковке");
        Cell numOfPacks = row1.createCell(3); numOfPacks.setCellValue("Количество упаковок");
        Cell weightOfPack = row1.createCell(4); weightOfPack.setCellValue("Вес коробки");
        Cell weightPacks = row1.createCell(5); weightPacks.setCellValue("Суммарный вес");
        Cell volumeOfPack = row1.createCell(6); volumeOfPack.setCellValue("Объем коробки");
        Cell volumeOfPacks = row1.createCell(7); volumeOfPack.setCellValue("Суммарный объем");
        for(int i= 0; i<rightItems.size(); i++){
            Row rowN = sheet.createRow(lastRow+i);
            Cell nameN = rowN.createCell(0); nameN.setCellValue(rightItems.get(i).getName());
            Cell quantityN = rowN.createCell(1); quantityN.setCellValue(rightItems.get(i).getQuantity());
            Cell inPacksN = rowN.createCell(2); inPacksN.setCellValue(rightItems.get(i).getItemsInPack());
            Cell numOfPacksN = rowN.createCell(3); numOfPacksN.setCellValue(rightItems.get(i).getNumOfPacks());
            Cell weightOfPackN = rowN.createCell(4); weightOfPackN.setCellValue(rightItems.get(i).getWeightOfPack());
            Cell weightPacksN = rowN.createCell(5); weightPacksN.setCellValue(rightItems.get(i).getSumWeight());
            Cell volumeOfPackN = rowN.createCell(6); volumeOfPackN.setCellValue(rightItems.get(i).getVolumeOfPack());
            Cell volumeOfPacksN = rowN.createCell(7); volumeOfPacksN.setCellValue(rightItems.get(i).getSumVolume());
        j=lastRow+i;   
        }
       Row secondHeadings = sheet.createRow(j+2); //Headings
       Cell cellFinal = secondHeadings.createCell(0); cellFinal.setCellValue("Итого");
       Cell cellWeight = secondHeadings.createCell(5); cellWeight.setCellValue("Суммарный вес");
       Cell cellVolume = secondHeadings.createCell(7); cellVolume.setCellValue("Суммарный объем");
       Row sumData = sheet.createRow(j+3);
       Cell cellFinalWeight = sumData.createCell(5); cellFinalWeight.setCellValue(sumWeight);
       Cell cellFinalVolume = sumData.createCell(7); cellFinalVolume.setCellValue(sumVolume);
        Row rest = sheet.createRow(j+4); //row for rest space in the container
        Cell restFinal = rest.createCell(0); restFinal.setCellValue("Остаток");
        Cell restWeight = rest.createCell(5); restWeight.setCellValue("Остаток, кг");
        Cell restVolume = rest.createCell(7); restVolume.setCellValue("Остаток, м3");
        Row restNumbers = sheet.createRow(j+5);
        Cell restWeightNumber = restNumbers.createCell(5); restWeightNumber.setCellValue(weightLeft);
        Cell restVolumeNumber = restNumbers.createCell(7); restVolumeNumber.setCellValue(volumeLeft);
     }
    /*Writing output to system.out to test the method*/
     public void findClosest(double ideal){
         
        double dif;
        double min = items.get(0).getRatio()-ideal;
        int n = 0;
        for(Items item : items){
            dif = abs((item.getRatio()-ideal));
            n++;
         //min = min(min, dif);
            if(dif< min){
                min = dif;
                index = n;
             }
        }
        sumWeight = items.get(index).getSumWeight();
        sumVolume = items.get(index).getSumVolume();
        sortedItems.add(items.get(index));
        System.out.println(items.get(index).getName()+" "+items.get(index).getSumWeight()+" "+items.get(index).getSumVolume()+"\n");
    items.remove(index);
    numberOfItems --;
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
    public void sortItems(){
        XSSFWorkbook output = new XSSFWorkbook();
        Sheet s = output.createSheet();
        lastRow = 1;
        while(numOfContainers!=0 && items.size()>1){
            if(numOfTwenties != 0){
                numOfTwenties--;
            }else{
                this.volumeCapacity = capacityFourty;
            }
        rightListInitialize();
        double diff; // ratio difference
        double idealRatio = weightCapacity/volumeCapacity;
        
         findClosest(idealRatio);
        double itemVolume;
        double itemWeight;
        setLeftVolume();
        setLeftWeight();
        int numOfSortItems=2; // number of sorted items for calculation
        numberOfItems=items.size();
             for(int i=0; i<=numberOfItems; i++){
                 if(!items.isEmpty()){
                 itemVolume = items.get(0).getSumVolume();
                 itemWeight = items.get(0).getSumWeight();
                 double min = abs(idealRatio - formula(items.get(0), numOfSortItems));
                 index = 0;
                 int count = 0;
                 for(Items item:items){
                      // double b = item.getRatio();
                     diff = abs(idealRatio - formula(item, numOfSortItems));
                     if (diff<min && item.getSumWeight()<weightLeft &&
                             item.getSumVolume()<volumeLeft){
                         
                         min = diff;
                         index = count;
                         itemWeight = item.getSumWeight();
                         itemVolume = item.getSumVolume();
                         }
                      count ++;
                       }
                 if(itemVolume<volumeLeft && itemWeight<weightLeft){
                     sortedItems.add(items.get(index));
                     sumWeight = sumWeight + itemWeight;
                     sumVolume = sumVolume + itemVolume;
                     setLeftVolume();
                     setLeftWeight();
                     System.out.println(items.get(index).getName()+" "+items.get(index).getSumWeight()+" "+items.get(index).getSumVolume()+"\n");
                    items.remove(index);
                   }else if(items.get(index).getWeightOfPack()<weightLeft && 
                           items.get(index).getVolumeOfPack()<volumeLeft &&
                           items.get(index).getNumOfPacks()!=0 && !items.isEmpty()){
                      sortedItems.add(splitItem());
                      
                   }
                  }
                 }
             System.out.println(volumeLeft+" " + weightLeft+" " +sumWeight + " " +sumVolume);
             numOfContainers--;
            writeOutput(sortedItems, s, lastRow);
             lastRow = lastRow + sortedItems.size()+6;
        }
        try {         
          FileOutputStream write = new FileOutputStream("/home/igorbashka/Documents/ДокиМаша/testOutput.xlsx");
         // FileOutputStream write = new FileOutputStream("/home/igor/Documents/China/testOutput.xlsx");
            try {
                output.write(write);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
           } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
      
        
     }
    public void printUnsorted(){
        for(Items item: items){
            System.out.println(item.getName()+" "+item.getSumWeight()+" "+item.getSumVolume()+"\n");
        }
    }
        
        public double getSumVolume(){
            double sum = 0;
            for(Items item: sortedItems){
                sum = sum + item.getSumVolume();
                            }
            return sum;
       }
        
    public double getSumWeight(){
        return sumWeight;
    }
    public double getNumOfContainers(){
        return numOfContainers;
    }
    public int getFourties(){
        return numOfFourties;
    }
    public int getTwenties(){
        return numOfTwenties;
    }
    public double getVolumeLeft(){
        return volumeLeft;
    }
    public double getWeightLeft(){
        return weightLeft;
    }
    
    /*Method so slipt item on packs to fit in the container*/
    private Items splitItem(){
        double numberOfPacks = items.get(index).getNumOfPacks();
        double weightOfSorted = items.get(index).getWeightOfPack();
        double volumeOfSorted = items.get(index).getVolumeOfPack();
        double numberOfItems = items.get(index).getQuantity();
        while(numberOfPacks !=0 && (weightOfSorted+
                items.get(index).getWeightOfPack())<weightLeft && 
                (volumeOfSorted+
                items.get(index).getVolumeOfPack())<volumeLeft){
            weightOfSorted+= items.get(index).getWeightOfPack();
            volumeOfSorted+= items.get(index).getVolumeOfPack();
            numberOfPacks--;
            numberOfItems -= items.get(index).getItemsInPack();
        }
        Items splitItem = new Items(items.get(index).getName(),//the quantity of items to fit in the container 
                numberOfItems, items.get(index).getItemsInPack(), minus(items.get(index).getNumOfPacks(), numberOfPacks),
        items.get(index).getWeightOfPack(), weightOfSorted, 
        items.get(index).getVolumeOfPack(), volumeOfSorted);
        Items splitItem2 = new Items(items.get(index).getName(), 
                minus(items.get(index).getQuantity(), numberOfItems), items.get(index).getItemsInPack(),
       numberOfPacks , 
        items.get(index).getWeightOfPack(), minus(items.get(index).getSumWeight(),
                weightOfSorted), items.get(index).getVolumeOfPack(),
        minus(items.get(index).getSumVolume(), volumeOfSorted));
        Items checkItem = items.get(index);
        items.remove(index);
        items.add(splitItem2);//add splitted item back for further calculation
        sumWeight +=splitItem.getSumWeight();
        sumVolume +=splitItem.getSumVolume();
        setLeftWeight();
        setLeftVolume();
       return splitItem;
      }
    /*Method to compare quantities of splitted item and minus*/
    private double minus(double var1, double var2){
        return var1-var2;
              
    }
}
