package collectionsmethods.regex;

public class Regex {

    /*
    * regular expressions are a special sequence of characthers that can help match, find or filter through other sets
    * through the ruleset that is determined by the special sequence of characters. the rules of regular expressions can
    * be seen below:
    */

    /*
    BASIC ELEMENTS
     */

    // "." Dot matches any character
    public static boolean matchAnySingleCharacter(String s){
        String regex = ".";
        return s.matches(regex);
    }

    // "^" Caret checks the begining of a string
    public static boolean checkFirstCharacterOfStringIsA(String s){
        String regex = "^a";
        return s.matches(regex);
    }

    // $ checks the end of the string
    public static boolean checkLastCharacterOfStringIsG(String s){
        String regex = "g$";
        return s.matches(regex);
    }

    //[] the square brackets checks to see if the character matches any of the characters in the brackets
    public static boolean checkIfCharacterIsABC(String s){
        String regex = "[abc]";
        return s.matches(regex);
    }

    public static boolean CheckIfCharacterIsLetter(String s){
        String regex = "[a-z]";
        return s.matches(regex);
    }
    public static boolean CheckIfCharacterIsLetterCaseIgnore(String s){
        String regex = "[a-zA-Z]";
        return s.matches(regex);
    }

    //[^...] Caret inside a square brackets will match anything EXCEPT for the contents of the bracket
    public static boolean checkIfCharacterIsNotABC(String s){
        String regex = "[^abc]";
        return s.matches(regex);
    }

    /*
    QUANTIFIERS
     */

    // "*" matches 0 or more occurances of a character
    public static boolean checkIfStringIsAnyNumberOfAOrEmpty(String s){
        String regex = "a*";
        return s.matches(regex);
    }

    //"+" matches 1 or more occrances of a characters

    public static boolean CheckIfStringHasAtleastOneAandOnlyAs(String s){
        String regex = "a+";
        return s.matches(regex);
    }

    // {n} matches any number of ocuracnes of a character
    public static boolean checkIfStringIs3As(String s){
        String regex = "a{3}";
        return s.matches(regex);
    }

    //{n,} matches at least n occurances
    public static boolean checkIfStringIsAtleast2As(String s){
        String regex = "a{2,}";
        return s.matches(regex);
    }

    //{n,m} will match between n to m occurances i.e. {2,4} will match between 2 to 4 occurances
    public static boolean twoToFourGs(String s){
        String regex = "g{2,4}";
        return s.matches(regex);
    }

    /*
    ESCAPING SPECIAL CHARACTERS
    if we want to match characters like ".", "*", "+" etc. we will need to escape them using a backslash "\" so that we
    can search for them instead of using them to determine rules.

    However, In Java, the backslash itself is an escape character in strings so when doing regex in Java,
    a double backslash "\\" is needed to be the escape character.
    This is because the second backslash escapes the first one and acts as our rule and will be the case for anything
    requiring a "\"
     */
    public static boolean checkingIfCharacterIsDot(String s){
        String regex = "\\.";
        return s.matches(regex);
    }

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

    public static boolean checkingIfFirstLettersAreAAAand10(String s){
        String regex = "^(a{3}).{7}";

        /*
        ^ at the beging checks the beging of the string
        (a{3}) checks if there is 3 occurances of the letter a
        .{7} checks if the string is then followed by exactly 7 of any characters
         */

        return s.matches(regex);
    }

    public static boolean checkingIfTheThirdLetterIsADot(String s){
        String regex = "^.{2}(\\.).*";

        /*
        ^.{2} ensures that there is two of any character at the begining of the string
        (\\.) checks to see if there is a "."
        .* ensures that the string can be followed by any number of random characters or none at all
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
        ^ checks the start of the string
        (reg|drg) matches either "reg" or "drg"
        .{7} matches any 7 characters (as the first thre letters are reg or drg)
        $ ensures the end of the string
         */

        return s.matches(regex);
    }

    public static boolean checkingUrl1(String s){

        /*
        ^(w{3}) ensures the first three letters are w
        (\\.) ensures that the next character is a dot after the 3 w's
        [a-zA-Z0-9]{1,15} ensures that there is between 1 to 15 of any alphanumeric character
        (\\.) ensures that the next characer after the 1-15 characters is a dot "."
        ((co\\.uk)|(com) ensures that the next characters are either co.uk or com
         */

        String regex = "^(w{3})(\\.)[a-zA-Z0-9]{1,15}(\\.)((co\\.uk)|(com))";

        return s.matches(regex);
    }

    public static boolean checkingUrl2(String s){

        /*
        (https:\/\/)? checks if the there 0 or one occurance of https://
        (w{3}\\.)? checks if there is 0 or one occurance of www.
        [a-zA-Z0-9]+ checks if there is one or more alphanumeric characters after the optional https:// and www.
        (\\.) ensures that there is a dot followed by the alphanumeric characters
        ((co\.uk)|(com))$ ensures that the last characters in the string are either co.uk or com
         */
        String regex = "(https:\\/\\/)?(w{3}(\\.))?[a-zA-Z0-9]+(\\.)((co\\.uk)|(com))$";

        return s.matches(regex);
    }

    public static boolean checkingUrl3(String s){

        /*
        (http(s?):\/\/)? checks if the there 0 or one occurance of https:// where the "s" in https//: is optional
        (w{3}\\.)? checks if there is 0 or one occurance of www.
        [a-zA-Z0-9]+ checks if there is one or more alphanumeric characters after the optional https:// and www.
        (\\.) ensures that there is a dot followed by the alphanumeric characters
        ((co\.uk)|(com))$ ensures that the last characters in the string are either co.uk or com
        (\/[a-zA-Z0-9]+)* ensures that the url can be followed by foreward slash and an alphanumeric string
        sequence as many times as needed or none at all
        i.e. //com/hud/tv will be accepted but not comasddgs as there is no "/"

         */
        String regex = "(http(s?):\\/\\/)?(w{3}(\\.))?[a-zA-Z0-9]+(\\.)((co\\.uk)|(com))(\\/[a-zA-Z0-9]+)*";

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

//        System.out.println(checkingUrl1("www.cool345sch.com"));
//        System.out.println(checkingUrl1("www.cool345sch.co.uk"));
//        System.out.println(checkingUrl1("wwwcool345sch.com"));

//        System.out.println(checkingUrl2("www.cool345sch.com"));
//        System.out.println(checkingUrl2("https://www.cool345sch.com"));
//        System.out.println(checkingUrl2("https://cool345sch.com"));
//        System.out.println(checkingUrl2("cool345sch.com"));
//        System.out.println(checkingUrl2("https://ww.cool345sch.com"));
//        System.out.println(checkingUrl2("http://www.cool345sch.com"));

//        System.out.println(checkingUrl3("https://www.asdgsdgs.com"));
        System.out.println(checkingUrl3("https://www.asdgsdgs.com/dfs/sdf"));
        System.out.println(checkingUrl3("http://www.asdgsdgs.com/dfs/sdf"));
//        System.out.println(checkingUrl3("https://www.asdgsdgs.comdfssdf"));


    }
}
