public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        //returns index of first stop codon after start index 
        //and is mutliple of 3
        int startPos = startIndex;
        int returnNo = dna.length();
        while (true) {
            int stopCodonIndex = dna.indexOf(stopCodon, startPos + 1);
            if (stopCodonIndex == -1) {
                break;
            }
            if (((stopCodonIndex + 3) - startIndex) % 3 == 0) {
                returnNo = stopCodonIndex;
            }
            startPos = stopCodonIndex;
            }
        return returnNo;
    } 
    
    public String findGene(String dna) {
        //find index of first start codon atg
        int startCodon = dna.indexOf("ATG");
        //return empty string if no start
        if (startCodon == -1) {
            return "";
        }
        //find first taa with above method
        int taa = findStopCodon(dna, startCodon, "TAA");
        //find first tag with above method
        int tag = findStopCodon(dna, startCodon, "TAG");
        //find first tga with above method
        int tga = findStopCodon(dna, startCodon, "TGA");
        //return closest
        //if no closest return empty string
        int ans[] = {taa, tag, tga};
        int minst = Integer.MAX_VALUE;
        for (int elem : ans) {
            if (elem < minst) {
                minst = elem;
            }
        }
        if (minst == dna.length()) {
            return "";
        }
        String return_val = dna.substring(startCodon, minst + 3);
        return return_val;
    }
    
    public void testFindStopCodon() {
        String w = "ANDATGDHGSTHSGTTAAKSJ";
        if (findStopCodon(w, 3, "TAA") != 15){
            System.out.println("String w failed");
        };
        String x = "ANDATGHGSHGTAAHGT";
        if (findStopCodon(x, 3, "TAA") != 17){
            System.out.println("String x failed");
        };
        String y = "ANDATGGTAAJHGTATAANHG";
        if (findStopCodon(y, 3, "TAA") != 15){
            System.out.println("String y failed");
        };
        String z = "ATGTAA";
        if (findStopCodon(z, 0, "TAA") != 3){
            System.out.println("String z failed");
        };
        System.out.println("All other tests passed");
    }
    
    public void testFindGene () {
        String v = "ABCATGSHGTAAJSJS";
        System.out.println(v);
        System.out.println(findGene(v));
        String w = "AATGBHGTAGJHS";
        System.out.println(w);
        System.out.println(findGene(w));
        String x = "ATGNJHTGAJHG";
        System.out.println(x);
        System.out.println(findGene(x));
        String y = "NHBATGHSGJTAAHNHBTAAJ";
        System.out.println(y);
        System.out.println(findGene(y));
        String z = "BHATBHGGTAGVFTAGHB";
        System.out.println(z);
        System.out.println(findGene(z));
    }
    
    public void printAllGenes(){
        String testString = "BHGATGBHGBHGBHGTAABHGATGSHJTGABATGHGATAGHJ";
        String toSearch = testString.substring(0, testString.length());
        String gene;
        while (true) {
            gene = findGene(toSearch);
            if (gene.isEmpty() == true) {
                break;
            }
            else {
                System.out.println(gene);
                toSearch = testString.substring(testString.indexOf(gene) + gene.length());
            }
        }
    }
}
 