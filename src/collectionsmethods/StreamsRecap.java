package collectionsmethods;

import java.util.*;
import java.util.stream.Collectors;

public class StreamsRecap {

    //map - this performs an operation on each value, and this is done through the lambda expression in the parameter
    //filter

//    public static void main(String args){
//        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1,2,3,4,5,6));
//        numbers.stream()
//                .map(integer -> integer * 10) //intermediary operation
//                .filter(integer -> integer >20 ) //another intermediary operation
//                .forEach(System.out::println); //terneray operation
//    }


    public static List<Integer> two2(List<Integer> nums){
        return nums
                .stream()
                .map(num -> num * 2)
                .filter(num -> num % 10 != 2 )
                .sorted()
                .collect(Collectors.toList());
    }

    public static Map<String, Integer> word0(String[] strings) {
        Map<String, Integer> returnedMap = new HashMap<>();
        for (String str: strings) {
            returnedMap.put(str, 0);
        }
        return returnedMap;
    }

    public static Map<String, Integer> word1(String[] strings) {
        return Arrays
                .stream(strings)
                .collect(Collectors
                        .toMap(key -> key, value -> 0,
                                (v1, v2) -> v2));
    }

    public Map<String, Integer> wordLen(String[] strings) {
        return Arrays
                .stream(strings)
                .collect(Collectors
                        .toMap(key -> key, String::length,
                                (v1, v2) -> v2));
    }

    public Map<String, Integer> wordCount(String[] strings) {
        return Arrays
                .stream(strings)
                .collect(Collectors
                        .toMap(key -> key, value -> 1,
                                (v1, v2) -> v1 + 1));
    }

    public static String[] wordsThatAreDupes(String[] words){

        Map<String, Integer> wordCounter = Arrays
                .stream(words)
                .map(word -> word.toLowerCase())
                .collect(Collectors
                        .toMap(key -> key, value -> 1,
                                (v1, v2) -> v1 + 1));

        ArrayList<String> arrayListToReturn = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : wordCounter.entrySet()) {
            if (entry.getValue() >=2){
                arrayListToReturn.add(entry.getKey());
            }
        }
        return arrayListToReturn.stream().toArray(n -> new String[n]);
    }

    public static Map<String, String> pairs(String[] strings){
        return Arrays
                .stream(strings)
                .collect(Collectors
                        .toMap(key -> Character.toString(key.charAt(0)),
                                value -> Character.toString(value.charAt(value.length()-1)), (v1,v2) -> v2));
    }

    public Map<String, String> firstChar(String[] strings) {
        return Arrays.stream(strings).collect(Collectors.toMap(key -> Character.toString(key.charAt(0)), value -> value, (v1, v2) -> v1 + v2));
    }

    public static List<String> convertToList (String[] strings){
        return Arrays.stream(strings).collect(Collectors.toList());
    }

    public static Set<String> convertToSet (String[] strings){
        return Arrays.stream(strings).collect(Collectors.toSet());
    }

    public static Set<String> convertToSet2 (List<String> strings){
        return strings.stream().collect(Collectors.toSet());
    }

    public static Set<String> convertToSetWithoutStreams (List<String> strings){
        HashSet<String> returnedSet = new HashSet<>(strings);
        return  returnedSet;
    }

    public static SortedSet<String> convertToSortedSet(List<String> strings){
        return strings.stream().collect(Collectors.toCollection(TreeSet::new));
    }

    public static SortedSet<String> convertToSortedSet3(List<String> strings){
        return strings.stream().collect(Collectors.toCollection(() -> new TreeSet<>()));
    }





    public static void main(String[] args){
//        ArrayList<String> names = new ArrayList<>(Arrays.asList("Manish", "Michael", "Lanya", "Chamra"));
//            Set<String> nameSet = names.stream()
//                    .filter(name -> name.contains("n"))
//                    .map(name -> name.toUpperCase())
//                    .peek(name -> System.out.println(name))
//                    .distinct()
//                    .collect(Collectors.toSet());

//            ArrayList<Integer> ints = new ArrayList<>(Arrays.asList(1,2,3,4,5,6));
//            two2(ints).stream().forEach(num -> System.out.println(num));

            String[] names = {"charlie", "michael", "lima", "charlie", "Michael"};
            String[] words = {"are", "codes", "and", "cods"};

            //System.out.println(pairs(words));
//        System.out.println(Arrays.stream(names).filter(name -> name.contains("m")).collect(Collectors.toList()));

        System.out.println(Arrays.toString(wordsThatAreDupes(names)));
        }
}
