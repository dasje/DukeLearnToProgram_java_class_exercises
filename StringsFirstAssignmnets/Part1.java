
public class Part1 {
    static String findSimpleGene(String theString) {
        String simpleGene = "";
        //find index position of ATG
        int atg = theString.indexOf("ATG");
        //if no ATG return empty string
        if (atg == -1) {
            return simpleGene;
        };
        //find index position of TAA after ATG
        int taa = theString.indexOf("TAA", atg);
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
                                "FGTATGHSGBGTAANHG"};
        //print each string
        //run findsimplegene on each string and print result
        for (String x : testStrings) {
            System.out.println("Next test:");
            System.out.println(x);
            String y = findSimpleGene(x);
            System.out.println(y);
        }
    }
}
