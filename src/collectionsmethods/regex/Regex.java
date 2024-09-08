package collectionsmethods.regex;

public class Regex {

    /*
    * regular expressions are a special sequence of characthers that can help match, find or filter through other sets
    * through the ruleset that is determined by the special sequence of characters. the rules of regular expressions can
    * be seen below:
    *
    * [abc] - checks to see if there is a single character of either "a", "b" or "c"
    * [a-z] - a single charactrer in the alphabet from a - z (not capitals)
    * ^(a|b|c) - the cone hat means
    * */


    // examples ...............................................................................
    public static boolean checkFirstLetterisAorBorC(String s){
        String regex = "^[abc].*"; //checking to see if the first letter of a string is a
        /*
        ^ asserts the begining of the string
        [abc] matches either a, b, c
        .* means it can be any character or any length
         */

        return s.matches(regex);
    }

    public static boolean checkIfThe3LettersAfter3rdPlaceIsBCG(String s){

        String regex = "^.{2}bcg.*";

        /*
        ^ asserts the begining of the string
        .{2} matches any two characters (therefore skipping the begining of the string)
        bcg checks the literal substring "bcg"
        .* means that the string can be followed by any number of any character

        * */

        return s.matches(regex);
    }

    public static boolean beginsWithRegOrDrgMax10(String s){
        String regex = "^(reg|drg).{7}$";
        /*
        ^ checks the start of the sring
        (reg|drg) matches either "reg" or "drg"
        .{7} matches any 7 characters (as the first thre letters are reg or drg)
        $ ensures the end of the string
         */

        return s.matches(regex);
    }

    public static void main(String[] args){
//        System.out.println(checkFirstLetterisAorBorC("anectdote"));
//        System.out.println(checkIfThe3LettersAfter3rdPlaceIsBCG("xxbcg123"));
//        System.out.println(checkIfThe3LettersAfter3rdPlaceIsBCG("asbcgerergbffgsdfsdf"));
//        System.out.println(checkIfThe3LettersAfter3rdPlaceIsBCG("sdgfrfhstj"));

//        System.out.println(beginsWithRegOrDrgMax10("reg1234567"));
//        System.out.println(beginsWithRegOrDrgMax10("reggy"));
//        System.out.println(beginsWithRegOrDrgMax10("reggy12345"));
//        System.out.println(beginsWithRegOrDrgMax10("reggy1234512345"));

    }
}
