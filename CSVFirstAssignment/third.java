import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class third {
    public int[] totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord rec: fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
            }
            else {
                totalGirls += numBorn;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("total boys = " + totalBoys);
        System.out.println("total girls = " + totalGirls);
        int[] results = {totalBirths, totalBoys, totalGirls};
        return results;
    }
    
    public int getRank(int year, String name, String gender) {
        FileResource fr = new FileResource();
        CSVParser csvp = fr.getCSVParser(false);
        int rank = -1;
        int counter = 0;
        for (CSVRecord rec: csvp){
            if (rec.get(1).equals(gender)){
                counter += 1;
                if (rec.get(0).equals(name)){
                    rank = counter;
                    break;
                }
            }
        }
        return rank;
    }
    
    public String getName(int year, int rank, String gender){
        FileResource fr = new FileResource();
        CSVParser csvp = fr.getCSVParser(false);
        String retName = "NO NAME";
        int counter = 0;
        for (CSVRecord rec: csvp){
            if (rec.get(1).equals(gender)) {
                counter += 1;
                if (counter == rank){
                    retName = rec.get(0);
                }
            }
        }
        return retName;
    }
    
    public String whatIsNameInYear(String name, int year, int newYear, String gender){
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        String retString = name + " born " + year + " would be " + newName + " if born in " + newYear;
        return retString;
    }
    
    public String yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int currentRank = 999999999;
        String currentYear = null;
        for (File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser csvp = fr.getCSVParser(false);
            int rank = 999999999;
            int counter = 0;
            for (CSVRecord rec: csvp){
                if (rec.get(1).equals(gender)){
                    counter += 1;
                    if (rec.get(0).equals(name)){
                        rank = counter;
                        break;
                    }
                }
            }
            String fileName = f.getName();
            if (rank < currentRank){
                currentRank = rank;
                currentYear = fileName;
            }
        }
        return currentYear;
    }
    
    public double getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        double averageRank = -1.0;
        double counter = 0.0;
        int totalRanks = 0;
        for (File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser csvp = fr.getCSVParser(false);
            int rank = 0;
            int innercounter = 0;
            for (CSVRecord rec: csvp){
                if (rec.get(1).equals(gender)){
                    innercounter += 1;
                    if (rec.get(0).equals(name)){
                        rank = innercounter;
                        break;
                    }
                }
            }
            totalRanks += 1;
            counter += rank;
        }
        double retQuant = counter / totalRanks;
        if (retQuant > 0){
            averageRank = retQuant;
        }
        return averageRank;
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        FileResource fr = new FileResource();
        CSVParser csvp = fr.getCSVParser(false);
        int totalHigherRankedBirths = 0;
        for (CSVRecord rec: csvp){
            if (rec.get(1).equals(gender)){
                if (rec.get(0).equals(name)) {
                    break;
                }
                else{
                    int totNames = Integer.parseInt(rec.get(2));
                    totalHigherRankedBirths += totNames;
                }
            }
        }
        return totalHigherRankedBirths;
    }
    
    public void testGetTotalBirthsRankedHigher(){
        int x = getTotalBirthsRankedHigher(2012, "Ethan", "M");
        System.out.println(x);
    }
       
    public void testGetAverageRank(){
        double x = getAverageRank("Mason", "M");
        System.out.println(x);
    }
    
    public void testYearOfHighestRank(){
        String x = yearOfHighestRank("Mason", "M");
        System.out.println(x);
    }
    
    public void testGetRank(){
        int x = getRank(2012, "Mason", "M");
        System.out.println(x);
    }
    
    public void testTotalBirths(){
        FileResource fr = new FileResource();
        int[] x = totalBirths(fr);
    }
    
    public void testGetName(){
        String x = getName(2012, 3, "F");
        System.out.println(x);
    }
    
    public void testWhatIsNameInYear(){
        String x = whatIsNameInYear("Mason", 2012, 2013, "M");
        System.out.println(x);
    }
    
    public int totalNames(String gender){
        FileResource fr = new FileResource();
        CSVParser csvp = fr.getCSVParser(false);
        int tots = 0;
        for (CSVRecord rec: csvp){
            if (rec.get(1).equals(gender)){
                tots += 1;
            }
        }
        return tots;
    }
}
