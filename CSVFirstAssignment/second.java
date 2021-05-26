import edu.duke.*;
import java.io.File;
import org.apache.commons.csv.*;

public class second {
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldest = null;
        for (CSVRecord record: parser){
            if (coldest == null){
                coldest = record;
            }
            //System.out.println(coldest);
            //System.out.println(coldest.get("TemperatureF"));
            double recTemp = Double.parseDouble(record.get("TemperatureF"));
            double coldTemp = Double.parseDouble(coldest.get("TemperatureF"));
            if (recTemp == -9999){
                ;
            }
            else if (recTemp < coldTemp){
                coldest = record;
            }
        }
        return coldest;
    }
    
    public String fileWithColdestTemperature() {
        String coldestFile = null;
        CSVRecord currentColdest = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser csvp = fr.getCSVParser();
            //call coldesthourinfile 
            CSVRecord coldRecord = coldestHourInFile(csvp);
            //if currentcoldest is null
            if (currentColdest == null){
                System.out.println("null");
                currentColdest = coldRecord;
                //change currentcoldest to new csvrecord
                //change coldestfile to name of this file
                coldestFile = f.getName();
                //else coldest hour is colder than coldest hour in currentcoldest
            } else {
                double first = Double.parseDouble(coldRecord.get("TemperatureF"));
                double second = Double.parseDouble(currentColdest.get("TemperatureF"));
                if (first < second){
                    //change currentcoldest to new csvrecord
                    currentColdest = coldRecord;
                    //change coldestfile to name of this file
                    coldestFile = f.getName();
                }
            }
        }
        return coldestFile;
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestHumidity = null;
        for (CSVRecord record: parser){
            if (lowestHumidity == null){
                lowestHumidity = record;
            }
            if (record.get("Humidity") == "N/A"){
                ;
            }
            else {
                int recHum = Integer.parseInt(record.get("Humidity"));
                int coldHum = Integer.parseInt(lowestHumidity.get("Humidity"));
                if (recHum == -9999){
                    ;
                }
                else if (recHum < coldHum){
                    lowestHumidity = record;
                }
            }
        }
        return lowestHumidity;
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestHumidity = null;
        for (File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentLowestHumidity = lowestHumidityInFile(fr.getCSVParser());
            if (lowestHumidity == null){
                lowestHumidity = currentLowestHumidity;
            }
            else {
                int curLowHum = Integer.parseInt(currentLowestHumidity.get("Humidity"));
                int lowHum = Integer.parseInt(lowestHumidity.get("Humidity"));
                if (curLowHum < lowHum){
                    lowestHumidity = currentLowestHumidity;
                }
            }
        }
        return lowestHumidity;
    }

    public double averageTemperatureInFile(CSVParser parser){
        int counter = 0;
        double temps = 0.0;
        for (CSVRecord record: parser){
            double temp = Double.parseDouble(record.get("TemperatureF"));
            temps += temp;
            counter += 1;
        }
        return temps / counter;
    }
    
    public double averageTemperatureWithHighHumidityFile(CSVParser parser, int value){
        double avTemp = 0;
        int counter = 0;
        for (CSVRecord record: parser){
            double hum = Double.parseDouble(record.get("Humidity"));
            if (hum >= value){
                double temp = Double.parseDouble(record.get("TemperatureF"));
                avTemp += temp;
                counter += 1;
            }
            else{
                ;
            }
        }
        return avTemp / counter;
    }
    
    public void testAverageTemperatureWithHighHumidity(){
        FileResource fr = new FileResource();
        CSVParser csvp = fr.getCSVParser();
        double ans = averageTemperatureWithHighHumidityFile(csvp, 80);
        if (Double.isNaN(ans) == true){
            System.out.println("No temperature with that humidity");
        }
        else { 
            System.out.println("Average temperature when high humidity is " + ans);
        }
    }
    
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser csvp = fr.getCSVParser();
        double averageTemp = averageTemperatureInFile(csvp);
        System.out.println("Average temperature in file is " + averageTemp);
    }
    
    public void testLowestHumidityInManyFiles(){
        CSVRecord csvrec = lowestHumidityInManyFiles();
        String hum = csvrec.get("Humidity");
        String time = csvrec.get("DateUTC");
        System.out.println("Lowest Humidity was " + hum + " at " + time);
    }
    
    public void testLowestHUmidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        String lowestHumidity = csv.get("Humidity");
        String time = csv.get("DateUTC");
        System.out.println("Lowest Humidity was " + lowestHumidity + " at " + time);
    }
    
    
    public void testFileWithColdestTemperature(){
        String file = fileWithColdestTemperature();
        //FileResource fr = new FileResource("
        System.out.println(file);
    }
    
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        CSVRecord csvRec = coldestHourInFile(parser);
        //String time = csvRec.get("TimeEST");
        String temp = csvRec.get("TemperatureF");
        //System.out.println("The coldest temperature was " + temp + " at " + time);
        System.out.println(temp);
    }
}
