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
        CSVParser csvp = fr.getCSVParser();
        int[] totals = totalBirths(fr);
        int rank = 0;
        for (CSVRecord rec: csvp){
            if (gender == "F"){
                if (rec.get(1) != "F"){
                    ;
                }
                rank += 1;
                if (rec.get(0) == name){
                    break;
                }
            }
            else if (gender == "M"){
                if (rec.get(0) != "M"){
                    ;
                }
                rank += 1;
                if (rec.get(0) == name){
                    break;
                }
            }
        }
        return rank;
    }
    
    public void testGetRank(){
        int x = getRank(2012, "Mason", "M");
        System.out.println(x);
    }
    
    public void testTotalBirths(){
        FileResource fr = new FileResource();
        int[] x = totalBirths(fr);
    }
}
