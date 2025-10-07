package datastructures.hashtables;

import java.util.HashMap;

public class HashTables {

    public static int basicHashFunction(String value){
        int sum = 0;
//        System.out.println("the unicode for each character in the string " + value);
        for (int i = 0; i < value.length(); i++){
            int unicode = value.charAt(i);
//            System.out.println(unicode);
            sum += unicode;
        }
//        System.out.println("total sum of the unicodes added together");
//        System.out.println(sum);
        int hashcode = sum % 10;
        return hashcode;
    }

    public static void insertValue (String[] hashTable, String value){
        hashTable[basicHashFunction(value)] = value;
    }

    public static void deleteValue (String[] hashTable, String value){
        hashTable[basicHashFunction(value)] = null;
    }


    int findIndex (String value){
        return basicHashFunction(value);
    }


    public static void main(String[] args){
        String[] array = {"Pete", "Jones", "Lisa", "Bob", "Siri"};

//        HashMap<String, String> map = new HashMap<>();

        String[] hashSet = new String[10];

        String value = "bob";
//        System.out.println(value + " has hashcode " + hashFunction(value) );
        System.out.println(value + " has hashcode " + basicHashFunction(value) );

        for(String name : array){
            insertValue(hashSet, name);
        }

        for (String name : hashSet){
            if (name != null){
                System.out.println(name + " has index and hashcode of " + basicHashFunction(name));
            }
        }

    }
}
