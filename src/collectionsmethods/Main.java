//package collectionsmethods;
//
//import java.io.UncheckedIOException;
//import java.lang.reflect.Method;
//import java.util.*;
//
//public class Main {
//
//    public static int secondOccurance(Integer[] inArray, int target){
//        int counter = 0;
//        Integer secondOccurance = null;
//        for (int i = 0; i < inArray.length; i++){
//            if (inArray[i] == target){
//                counter++;
//                if(counter == 2){
//                    secondOccurance =  i;
//                    break;
//                }
//            }
//        }
//        return secondOccurance;
//    }
//
//    public static int secondHighest(Integer[] intArray){
//        Arrays.sort(intArray);
//        return intArray[intArray.length-2];
//    }
//
//    public static Integer[] valuesWithDuplictas(Integer[] intArray){
//        HashSet<Integer> intSet = new HashSet<>();
//        for(int i = 0; i < intArray.length; i++){
//            for (int j = 0; j < intArray.length; j++){
//                if (Objects.equals(intArray[i], intArray[j]) && i != j){
//                    intSet.add(intArray[j]);
//                }
//            }
//        }
//        return intSet.toArray(new Integer[0]);
//    }
//
////    public static Integer[] valuesWithDuplictas(Integer[] intArray){
////        Arrays.sort(intArray);
////
////        for (int i = 0; i)
////    }
//
//    public static int secondHighest2(Integer[] intArray) {
////        int highest = Collections.max(Arrays.stream(intArray).toList());
////        List<Integer> listTosort = new ArrayList<>(Arrays.stream(intArray).toList());
////        Arrays.sort(intArray);
////        Collections.sort(listTosort);
//        int highest = intArray[0];
//        int secondHighest = intArray[0];
//
//        for (int num: intArray) {
//            if (highest < num) {
//                highest = num;
//            }
//        }
//
//        for (int num: intArray) {
//            if (secondHighest < num &&   highest > num) {
//                secondHighest = num;
//            }
//        }
//
//        return secondHighest;
//    }
//
//    public static Integer[] removeDuplicates(Integer[] intArray){
////        Set<Integer> intSet = new HashSet<>(Arrays.asList(intArray));
////        return intSet.toArray(new Integer[0]);
//        return new HashSet<>(Arrays.asList(intArray)).toArray(new Integer[0]);
//    }
//
//    public static Integer[] removeDuplicates2(Integer[] intArray){
//        int[] nums = {1,2,3,4};
//        int index = 0;
//
//
//        Set<Integer> intSet = new HashSet<>(Arrays.asList(intArray));
//
////        Set<Integer> intSet = new HashSet<>();
////        intSet.addAll(Arrays.asList(intArray));
//
////        for (Integer num: intArray) {
////            intSet.add(num);
////        }
//
//
////        ArrayList<Integer> result = new ArrayList<Integer>();
//
////        for (Integer num : intSet){
////            result.add(num);
////        }
////        result.addAll(intSet);
//        ArrayList<Integer> result = new ArrayList<Integer>(intSet);
//
//        return result.toArray(new Integer[0]);
//    }
//
//    public static Integer[] removeDuplicates3(Integer[] intArray) {
//        Set<Integer> intSet = new HashSet<>(Arrays.asList(intArray));
//        return intSet.toArray(new Integer[0]);
//    }
//
//    public static int sumOfAllNumbers(Integer[] intArray){
//        int sum = 0;
//        if (intArray == null){
//            return sum;
//        } else{
//            for(Integer num : intArray){
//                sum += num;
//            }
//        }
//        return sum;
//    }
//    public static int sumOfAllNumbers2(Integer[] intArray){
//        List<Integer> intList = Arrays.stream(intArray).toList();
//
//        return intList.stream().reduce(0, Integer::sum);
////        return intList.stream().reduce(0, (a,b) -> a + b);
//    }
//
//    public static int sumOfAllNumbers3(Integer[] intArray){
//        List<Integer> intList = Arrays.stream(intArray).toList();
//        return intList.stream().reduce(0, (a,b) -> a + b);
//    }
//
//    public static String removeALlDuplicatesAndOrder(String input){
////        String[] iputAsArrayList = input.split("");
//        StringBuilder outputBuilder = new StringBuilder();
//        HashSet<String> inputAsHashSet = new HashSet<>(List.of(input.split("")));
//        ArrayList<String> inputWithRemovedDuplicates = new ArrayList<>(inputAsHashSet);
//        Collections.sort(inputWithRemovedDuplicates);
//        for (String letter: inputWithRemovedDuplicates) {
//            outputBuilder.append(letter);
//        }
//        return outputBuilder.toString();
//    }
//
//
//    public static boolean twoNumsEqual(Integer[] intArray){
//        for(int i = 0; i <intArray.length -1; i++){
//            if ((Objects.equals(intArray[i], intArray[i + 1])) && intArray[i] == 100){
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public static List<Integer> findAllDuplicateNums(Integer[] intArray){
//        HashSet<Integer> intSet = new HashSet<>();
//        for (int i = 0; i < intArray.length ; i++){
//            for (int j = 0; j< intArray.length; j++){
//                if (intArray[i].equals(intArray[j]) && i != j){
//                    intSet.add(intArray[i]);
//                }
//            }
//        }
//        List<Integer> outputOfDuplicates = Arrays.asList(intSet.toArray(new Integer[0]));
//        return outputOfDuplicates;
//    }
//
//    public static String removeVowelsFromString(String word){
//        StringBuilder output = new StringBuilder();
//        for (int i =0; i< word.length(); i++){
//            if ((word.toLowerCase().charAt(i) != 'a') && (word.toLowerCase().charAt(i) != 'e') && (word.toLowerCase().charAt(i) != 'i') && (word.toLowerCase().charAt(i) != 'o') && (word.toLowerCase().charAt(i) != 'u'))
//                output.append(word.charAt(i));
//        }
//        return output.toString();
//    }
//
//    public static String[] removeAllDuplicates1(String s){
//        HashSet<Character> charSet = new HashSet<Character>();
//        for (int i = 0; i < s.length(); i++){
//            int counter = 0;
//            for (int j = 0; j < s.length(); j++){
//                if (s.charAt(i) == s.charAt(j) && i != j){
//                    counter++;
//                }
//            }
//            if (counter >=1 ){
//                charSet.add(s.charAt(i));
//            }
//        }
//        Character[] result = charSet.toArray(new Character[0]);
//
//        String[] returnedList = new String[result.length];
//
//        for (int i = 0; i< result.length; i++){
//            returnedList[i] = Character.toString(result[i]);
//        }
//        return returnedList;
//    }
//
//
//    public static void main(String[] args) {
//        Integer[] intArray = {57,112,53,45,190,27,170,116,91,29,191,58,48,122,26,145,131,119,128,187, 8,171,119,38,108,147,119,176,156,181,4,4,116,101,133,191,90,194,125,21,98,54,44,47,35,180,151,117,4,181,95,181,181,124,21,149,52,127,200,126,118,120,61,160,4,121,119,150,61,21,133,22,44,12,170,6,82,120,29,14,42,61,67,54,180,119,124,175,14,197,33,131,171,23,179,148,62,91,150,22,107,21,71,185,97,163};
//        String[] str = {"a","b","v","d","s","s","a","a","a","a","a","a","a"};
//        String str2 = "abcdfdfdfdfdfdf";
////        Integer[] intArrayWithDuplicates = {57,112,53,45,57,190,27,170,116,91,29,191,58,48,122,26,145,131,119,128,187, 8,171,119,38,108,147,119,176,156,4,116,101,133,191,90,194,125,21,98,54,44,47,35,180,151,117,181,95,124,21,149,52,127,200,126,118,120,61,160,121,119,150,61,21,133,22,44,12,170,6,82,120,29,14,42,61,67,54,180,119,124,175,14, 22,197, 175,33,131,171,23,179,148,62,91,150,22,107,21,71,185,97,163, 100, 100};
////        System.out.println(secondOccurance(intArrayWithDuplicates, 112));
////        System.out.println(secondHighest2(intArray));
////        System.out.println(intArrayWithDuplicates.length);
////        System.out.println(removeDuplicates(intArrayWithDuplicates).length);
////        System.out.println(valuesWithDuplictas(intArrayWithDuplicates).length);
////        System.out.println(Arrays.toString(Arrays.stream(valuesWithDuplictas(intArrayWithDuplicates)).toArray()));
////        System.out.println(sumOfAllNumbers2(intArray));
////        System.out.println(twoNumsEqual(intArrayWithDuplicates));
////        System.out.println(removeALlDuplicatesAndOrder("zbehfuyorhbu8oyr7yf8oybouyzgfjhzbdgStahdtcuiebyvds"));
////        System.out.println(sumOfAllNumbers3(intArray));
//        System.out.println(Arrays.toString(removeAllDuplicates1("abcdfdfdfdfdfdf")));
//        System.out.println(Arrays.toString(removeAllDuplicates1("gfgfggffgfgs")));
//        System.out.println(Arrays.toString(removeAllDuplicates1("asdfghjklqw:gertyuiod")));
//        System.out.println(Arrays.toString(removeAllDuplicates1("asdfghjklqw:gertyuiod dfdfdfdf dfdfdfdf")));
//
//
//    }
//
//}