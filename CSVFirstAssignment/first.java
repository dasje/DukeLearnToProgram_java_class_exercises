import edu.duke.*;
import org.apache.commons.csv.*;

public class first{
    
    public String countryInfo(CSVParser parser, String country){
        String retString = "";
        for (CSVRecord record: parser) {
            String cuntry = record.get("Country");
            if (cuntry.contains(country)){
                String exports = record.get("Exports");
                String value = record.get("Value (dollars)");
                retString = retString + country + ": " + exports + ": " + value;
            }
        }
        return retString;
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportitem1, String exportitem2){
        for (CSVRecord record: parser){
            String exports = record.get("Exports");
            if (exports.contains(exportitem1) && exports.contains(exportitem2)){
                System.out.println(record.get("Country"));
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportitem){
        int total = 0;
        for (CSVRecord record: parser){
            if (record.get("Exports").contains(exportitem)){
                total += 1;
            }
        }
        return total;
    }
    
    public void bigExporters(CSVParser parser, String amount){
        for (CSVRecord record: parser) {
            if (record.get("Value (dollars)").length() > amount.length()) {
                String retString = new String();
                retString = retString + record.get("Country") + " " + record.get("Value (dollars)");
                System.out.println(retString);
            }
        }
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        bigExporters(parser, "$999,999,999,999");
        //int x = numberOfExporters(parser, "cocoa");
        //System.out.println(x);
        //listExportersTwoProducts(parser, "cotton", "flowers");
    }
}
