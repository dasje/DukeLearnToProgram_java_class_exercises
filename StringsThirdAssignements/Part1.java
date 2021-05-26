import edu.duke.*;

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
                break;
            }
            startPos = stopCodonIndex;
            }
        return returnNo;
    }
    
    //ATGBTAABATGSHGTAGNTATGGHJATGHJKTAAN
    
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
    
    public StorageResource getAllGenes(String y){
        StorageResource x = new StorageResource();
        String testString = y.toUpperCase();
        String toSearch = testString.substring(0, testString.length());
        String gene;
        while (true) {
            gene = findGene(toSearch);
            if (gene.isEmpty() == true) {
                if (toSearch.length() > 1) {
                toSearch = toSearch.substring(1);
                }
                else {
                    break;
                }
            }
            else {
                x.add(gene);
                toSearch = toSearch.substring(toSearch.indexOf(gene) + gene.length());
            }
        }
        return x;
    }
    
    public double cgRatio(String dna) {
        int counter = 0;
        Character c = new Character('C');
        Character g = new Character('G');
        double len = dna.length();
        for (char cg: dna.toCharArray()){
            if (cg == c || cg == g){
                counter += 1;
            }
        }
        return counter / len;
    }
    
    public int countCTG(String dna){
        String[] x = dna.split("CTG");
        return x.length - 1;
    }

    public void testfindGene() {
        String x = "ATGBTAABATGSHGTAGNTATGGHJATGHJKTAAN";
        System.out.println(findGene(x));
    }
    
    public void testGetAllGenes() {
        StorageResource x = getAllGenes("ATGTAAATGTAAATGTAA");
        for (String y: x.data()) {
            System.out.println(y);
        }
        System.out.println(x.size());
        
        StorageResource a = getAllGenes("ATGBHBJHGTAANATGNHGTAGNATGBGHTGANHG");
        for (String y: a.data()) {
            System.out.println(y);
        }
        System.out.println(a.size());
        
        StorageResource b = getAllGenes("ATGBTAABATGSHGTAGNTATGGHJATGHJKTAAN");
        for (String y: b.data()){
            System.out.println(y);
        }
        System.out.println(b.size());
    }
    
    public void testCGRatio(){
        String a = "ABDGCBDGD";
        String b = "GSBCGSTGDBCDGHS";
        System.out.println(cgRatio(a));
        System.out.println(cgRatio(b));
    }
    
    public void testCountCTG(){
        String x = "NHBCTGCTGHNNDJKSNCTGNSJSNCHCTGHNH";
        System.out.println(countCTG(x));
    }
    
    public void processGenes(StorageResource sr){
        int geneCounter = sr.size();
        System.out.println("The number of genes is/are " + geneCounter);
        
        int lenCounter = 0;
        for (String x: sr.data()){
            if (x.length() > 60) {
                lenCounter += 1;
            }
        }
        System.out.println("The number of genes greater than 60 characters is/are " + lenCounter);
        
        int cgratioCounter = 0;
        for (String x: sr.data()){
            if (cgRatio(x) > 0.35) {
                cgratioCounter += 1;
            }
        }
        System.out.println("The number of genes with a cgratio above 0.35 is " + cgratioCounter);
        
        int ctgCounter = 0;
        for (String x: sr.data()) {
            ctgCounter += countCTG(x);
        }
        System.out.println("The number of CTGs in this string is " + ctgCounter);
        
        int maxGeneLength = 0;
        for (String x: sr.data()) {
            if (x.length() > maxGeneLength){
                maxGeneLength = x.length();
            }
        }
        System.out.println("The longest gene is " + maxGeneLength);
    }
    
    public void testProcessGenes(){
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        StorageResource x = getAllGenes(dna);
        processGenes(x);
        int ctgs = countCTG(dna);
        System.out.println(ctgs);
    }
}
