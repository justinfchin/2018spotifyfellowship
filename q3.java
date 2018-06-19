// Question 3 - changePossibilities(amount,amount);

import java.util.*;

public class q3{

    /*
     * determine number of ways to make amount
     *
     * @param amount : amount of money 
     * @param arr : array of coin demonations, assumes this is in ascending order
     * @return number of ways to make the amount of money with coins of avail demoninations
     *
     * Idea: build a matrix of the solution
     * given more time, a possible enhancement is to calculate only what we need, start with sol = (highest arr-1, amount) + (highest arr, amount - highest arr);
     */
    public static int changePossibilities(int amount, int[] arr){

        int[][] sol = new int[arr.length+1][amount+1];
       
        //populate the first row and column which have header 0
        // java guarantees initial values are 0 so skip to the 0 col
        for (int i = 0; i < sol.length; i++){
            sol[i][0] = 1;
        }

        // populate the rest according to what we have
        for (int coin = 1; coin < sol.length; coin++){
            for (int amt = 1; amt < sol[0].length; amt++){
           
                // if coin > amount
                if (arr[coin-1] > amt){
                    sol[coin][amt] = sol[coin-1][amt];
                } else {
                    sol[coin][amt] = sol[coin-1][amt] + sol[coin][amt-arr[coin-1]];
                }
                
             
            }

        }

        return sol[arr.length][amount];
    }


    // for testing purposes
    public static void test(){
        int a1 = 4, a2 = 10, a3 = 5, a4 = 20;
        int[] arr1 = {1,2,3}, arr2 = {2,3,5,6}, arr3 = {1,2,3}, arr4 = {1,5,10,20};
        System.out.println("Testing....");
        System.out.println(a1+" "+Arrays.toString(arr1)+" : "+(changePossibilities(a1,arr1)==4));
        System.out.println(a2+" "+Arrays.toString(arr2)+" : "+(changePossibilities(a2,arr2)==5));
        System.out.println(a3+" "+Arrays.toString(arr3)+" : "+(changePossibilities(a3,arr3)==5));
        System.out.println(a4+" "+Arrays.toString(arr4)+" : "+(changePossibilities(a4,arr4)==10));
    }

    public static void main(String[] args){
        // uncomment the test() function below to run tests

        //test();

    }
}
