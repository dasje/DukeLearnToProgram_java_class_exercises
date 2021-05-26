import edu.duke.*;

public class Part1_2 {
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
                break;
            }
            startPos = stopCodonIndex;
            }
        return returnNo;
    } 
    
    public String findGene(String dna) {
        String dnaString = dna.toUpperCase();
        System.out.println("To uppercase " + dnaString);
        //find index of first start codon atg
        int startCodon = dnaString.indexOf("ATG");
        //return empty string if no start
        if (startCodon == -1) {
            return "";
        }
        //find first taa with above method
        int taa = findStopCodon(dnaString, startCodon, "TAA");
        //find first tag with above method
        int tag = findStopCodon(dnaString, startCodon, "TAG");
        //find first tga with above method
        int tga = findStopCodon(dnaString, startCodon, "TGA");
        //return closest
        //if no closest return empty string
        int ans[] = {taa, tag, tga};
        for (int x: ans){
            System.out.println(x);
        }
        int minst = Integer.MAX_VALUE;
        for (int elem : ans) {
            if (elem < minst) {
                minst = elem;
            }
        }
        if (minst == dna.length()) {
            return "";
        }
        String return_val = dnaString.substring(startCodon, minst + 3);
        return return_val;
    }
    
    public StorageResource getAllGenes(String x){
        StorageResource y = new StorageResource();
        String testString = x;
        String toSearch = testString.substring(0, testString.length());
        System.out.println(toSearch);
        String gene;
        while (true) {
            gene = findGene(toSearch);
            
            if (gene.isEmpty() == true) {
                if (toSearch.length() > 1){
                    toSearch = toSearch.substring(1);
                }
                else{
                    break;
                }
            }
            else {
                y.add(gene);
                System.out.println("Gene " + gene);
                System.out.println("indexof " + testString.indexOf(gene));
                System.out.println("length" + gene.length());
                toSearch = toSearch.substring(gene.length());
            }
        }
        return y;
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
        String a = "oneAtGMyGeneOneAATGGGGTAATGATAGAACCCGGYGGGGTAGGGCTGCCCATGendOneTAAnonCodingDnaTAGTGAZZZtaaTwoATGMyGeneTwoCATGGGGTAATGATAGCCatgCCCFalseStartTAATGATGendTwoTAGnonCodingDNATAACCCThreeATGMyGeneThreeATGGGGTAATGATAGATGccendThreeTAAnonCodingDNAccTAAfalsecccFourATGMyGeneFourATGGGGTAATGATAGCendFourTAGnonCodingdnaFiveAtgMyGeneFiveATGGGGTAATGATAGCendFiveTGAnonCodingdnaSixATGmyGeneSixATATGGGGTAATGATAGAendSixTAAnoncodingdnaSevenATGMyGeneSevenCcATGGGGTAATGATAGendSeventaAnoncodingdnaEightATGmyGeneEightATGGGGTAATGATAGGGendEighttaAnoncodingdnaCcccWrongtgaCtaaCtagCCcgNineATgmyGeneNineATGGGGTAATGATAGTaaAendNineTAAnonCodingDnaCcccTenATGmyGeneTenGATGGGGTAATGATAGCCHasFakeATGFAKEatgcendTentaanonCodingDnaCtagCtganonCodingDnaxxxElevenATGmyGeneElevenCATGGGGTAATGATAGTAAxxGeneATGTAACATGTAAATGCendElevenTAAnonCodingDnaxTAGxtgaTwelveATGmyGeneTwelveCATGGGGTAATGATAGCTheLastGeneIsATGTAGendTwelvetgaATGTAG";
        System.out.println(findGene(a));
    }
    
    public void testgetAllGenes(){
        String a = "ATGBTGAHGTGABATGBGHTAGBATGNMNNMNTAA";
        StorageResource x = getAllGenes(a);
        for (String q: x.data()){
            System.out.println(q);
        }
        
        String b = "oneAtGMyGeneOneAATGGGGTAATGATAGAACCCGGYGGGGTAGGGCTGCCCATGendOneTAAnonCodingDnaTAGTGAZZZtaaTwoATGMyGeneTwoCATGGGGTAATGATAGCCatgCCCFalseStartTAATGATGendTwoTAGnon";
        StorageResource y = getAllGenes(b);
        for (String q: y.data()){
            System.out.println(q);
        }
    }
}
