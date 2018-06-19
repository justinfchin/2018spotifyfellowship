// Question 1 - sortByStrings(s,t)

import java.util.*;


public class q1{

    /**
     * Sort s as they occur in t
     *
     * @ param s
     * @ param t
     * @ return special sorted string
     *
     * Idea : build string in order of t; first find count occurrence with a hashtable on s; then iter through t creating string based on value 
     */
    public static String sortByStrings(String s, String t){
        StringBuilder sb = new StringBuilder();
        HashMap<Character,Integer> hm = new HashMap<>();

        // populate hashmap with occurrences
        for (int i = 0; i < s.length(); i++){

            char curr = s.charAt(i);

            if (hm.containsKey(curr)){
                hm.put(curr, hm.get(curr)+1);
            } else {
                hm.put(curr, 1);
            }
        }

        // create new string in t order
        for (int i = 0; i < t.length(); i++){
            char curr = t.charAt(i);
            int value;
            // check if in hashtable
            if (hm.containsKey(curr)){
                value = hm.get(curr);
                for (int j = 0; j < value; j++){
                    sb.append(curr);
                }
            }
        }

        return sb.toString();
    }

    /**
     * For testing purposes
     */
    public static void test(){
        System.out.println("Testing...");
        String s = "weather", t = "therapyw";
        System.out.println(s+" "+t+" : " + sortByStrings(s,t));
        s = "good";t = "odg";
        System.out.println(s+" "+t+" : " + sortByStrings(s,t));
    }

    public static void main(String[] args){
       // uncomment the test() function below to run tests

       // test(); 
    }


}

