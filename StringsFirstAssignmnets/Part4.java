import edu.duke.*;

public class Part4 {
    public static void urls(String url){
        URLResource x = new URLResource(url);
        String searchTerm = "youtube.com";
        for (String word : x.words()) {
            if (word.toLowerCase().contains(searchTerm)){
                int firstIndex = word.indexOf("\"");
                int lastIndex = word.lastIndexOf("\"") + 1;
                String toPrint = word.substring(firstIndex, lastIndex);
                System.out.println(toPrint);
            };
        };
    };
    
    public static void tester(){
        urls("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
    }
}
