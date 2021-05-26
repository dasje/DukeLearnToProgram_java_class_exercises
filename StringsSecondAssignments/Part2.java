public class Part2 {
    public int howMany(String stringa, String stringb){
        int counter = 0;
        int index = 0;
        String copyb = stringb;
        while (true) {
            if (index < 0 || index > copyb.length()){
                break;
            }
            String toSearch = copyb.substring(index);
            int seenStringB = copyb.indexOf(stringa);
            if (seenStringB != -1){
                counter += 1;
                index = seenStringB + stringa.length() - 1;
                copyb = copyb.substring(index);
            }
            else {
                break;
            }
        }
        return counter;
    }
    
    public void testHowMany() {
        String x = "andxandxandxand x and x andxandx";
        System.out.println(howMany("and", x));
    }
}
