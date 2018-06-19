// Questoin 2 - decodeString(s)

import java.util.*;

public class q2{

    /**
     * decode a string
     *
     * @param s String
     * @return decoded string
     *
     * Idea: format is int[string int[string]], 
     */
    public static String decodeString(String s){
        StringBuilder sb = new StringBuilder();
        String temp = "";
        
        int lint = -1, fchar = -1,lchar = -1;
        //iter backwards
        for (int i = s.length()-1; i > -1 ; i--){
            
            char curr = s.charAt(i);
            if (Character.isLetter(curr)){
                if (lchar < 0){
                    // first time seeing char
                    lchar = i;
                } else {
                    // seen char before
                    if (lint > 0){
                        // working on int
                        // create subset
                        for (int j = 0; j < Integer.valueOf(s.substring(i+1, lint+1)); j++){
                            sb.append(s.substring(fchar,lchar+1)).append(temp);
                    
                        }
                        // store in temp
                        temp = sb.toString();
                        // clear 
                        sb.setLength(0);
                        lint = -1;
                        fchar = -1;
                        lchar = i;;

                    }
                }


            } else if (Character.isDigit(curr)){
                if (lint < 0){
                    // first time seeing int
                    lint = i;
                }


            } else if (curr == '['){
                // close char range
                fchar = i+1;
            }
        
        }

        // last bit
        for (int i = 0; i < Integer.valueOf(s.substring(0,lint+1)); i++){
            sb.append(s.substring(fchar,lchar+1)).append(temp);
        }

        return sb.toString();
    }
        

    // for testing purposes
    public static void test(){
        String s = "4[ab]", t = "2[b3[a]]", u = "2[ab3[cd]]", v = "12[a]", w="10[abc10[def10[efg]]]";
        System.out.println("Testing...");
        System.out.println(s+" : "+decodeString(s).compareTo("abababab"));
        System.out.println(t+" : "+decodeString(t).compareTo("baaabaaa"));
        System.out.println(u+" : "+decodeString(u).compareTo("abcdcdcdabcdcdcd"));
        System.out.println(v+" : "+decodeString(v).compareTo("aaaaaaaaaaaa"));
        
        String a= "abcdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgabcdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgabcdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgabcdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgabcdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgabcdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgabcdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgabcdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgabcdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgabcdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefgdefefgefgefgefgefgefgefgefgefgefg";
        System.out.println(w+" : "+decodeString(w).compareTo(a));
    }


    
    public static void main(String[] args){
        // uncomment the test() function below to run tests

        //test();
    }

}

