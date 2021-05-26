public class Part2 {
    static String findSimpleGene(String theString, String startCodon, 
    String stopCodon) {
        String simpleGene = "";
        //find index position of ATG
        String x = theString.toUpperCase();
        int atg = x.indexOf(startCodon.toUpperCase());
        //if no ATG return empty string
        if (atg == -1) {
            return simpleGene;
        };
        //find index position of TAA after ATG
        int taa = x.indexOf(stopCodon.toUpperCase(), atg);
        //if no TAA return empty string
        if (taa == -1) {
            return simpleGene;
        };
        //if length of string between ATG and TAA is multiple of 3, return substring
        if (((taa + 3) - atg) %  3 == 0) {
            simpleGene = theString.substring(atg, taa + 3);
        }
        return simpleGene;
    }
    
    public static void main(String[] args) {
        //five dna string: not atg, no taa, no atg or taa
        //atg taa and substring % 3, atg taa substring mod ! 3
        String[] testStrings = {"ASGHSGTAAJSH",
                                "ASGATGHSJTRE",
                                "FGHDBSHDGTSG",
                                "FGHTATGHSGTAAKJH",
                                "fghatgfrghsgtaajhg",
                                "FGTATGHSGBGTAANHG"};
        //print each string
        //run findsimplegene on each string and print result
        for (String x : testStrings) {
            System.out.println("Next test:");
            System.out.println(x);
            String y = findSimpleGene(x, "atg", "TAA");
            System.out.println(y);
        }
    }
}
