public class Part3 {
    public static boolean twoOccurences(String stringa, String stringb) {
        //returns true if stringa appears at least twice in stringb
        int ind = stringa.indexOf(stringb);
        int ind2 = stringa.lastIndexOf(stringb);
        if (ind2 > ind){
            return true;
            };
        return false;
        };
        
    public static String lastPart(String stringa, String stringb){
        int ind = stringa.indexOf(stringb);
        if (ind < 0) {
            return stringb;
        }
        String x = stringa.substring(ind + stringb.length() - 1);
        return x;
    }
    
    public static void testing() {
        String a, b, c, d, w, x, y, z;
        a = "banana";
        b = "kyla la granga";
        c = "she sells sea shell on the sea shore";
        d = "peter piper picked a pick of pickles";
        w = "na";
        x = "ga";
        y = "sea";
        z = "pick";
        String banana, na, zoo, forest;
        banana = "banana";
        zoo = "zoo";
        na = "na";
        forest = "forest";
        
        System.out.println(twoOccurences(a, w));
        System.out.println(twoOccurences(b, x));
        System.out.println(twoOccurences(c, y));
        System.out.println(twoOccurences(d, z));
        System.out.println(lastPart(banana, na));
        System.out.println(lastPart(zoo, forest));
    }
}
