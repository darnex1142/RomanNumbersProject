import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Character, Integer> romanNumbers = populateMap();

        String userInput = "";
        int totalSum = 0;

        System.out.println("Please Enter the number:");
        userInput = scanner.next().toUpperCase();

        if (!inputVerifier(userInput,romanNumbers)) {
            System.out.println("Wrong character. Please try again.");
            return;
        }

        for (int i = 0; i < userInput.length() - 1; i++) {

            boolean sameChar = userInput.charAt(i) == userInput.charAt(i + 1);
            //Check if 10x may not precede any symbol larger than 10x+1.
            if (((romanNumbers.get(userInput.charAt(i)) * 10)  < (romanNumbers.get(userInput.charAt(i+1)))) && !sameChar) {
                System.out.println("Wrong number. " + userInput.charAt(i+ 1) +" cannot appear after " + userInput.charAt(i) );
                break;
            }

            //Check if both letters are the same;
            if (sameChar) {
                //if totalNum is empty then it will had both numbers
                if (totalSum == 0) {
                    totalSum += romanNumbers.get(userInput.charAt(i)) + romanNumbers.get(userInput.charAt(i+1));
                //else it will take only the last one and added to the sum
                } else {
                    totalSum += romanNumbers.get(userInput.charAt(i+1));
                }
            } else if (romanNumbers.get(userInput.charAt(i)) > romanNumbers.get(userInput.charAt(i + 1))) {
                //If totalSUm is 0 it will sum both numbers and then update totalSum
                if (totalSum != 0) {
                    totalSum += romanNumbers.get(userInput.charAt(i+1));
                } else{
                    totalSum += romanNumbers.get(userInput.charAt(i)) + romanNumbers.get(userInput.charAt(i+1));
                }
            } else if (romanNumbers.get(userInput.charAt(i)) < romanNumbers.get(userInput.charAt(i + 1))) {
                //If totalSUm is 0 it will subtract both numbers and then update totalSum
                if (totalSum != 0) {
                    totalSum = totalSum - romanNumbers.get(userInput.charAt(i));
                }
                totalSum = totalSum + (romanNumbers.get(userInput.charAt(i + 1)) - romanNumbers.get(userInput.charAt(i)));
            }
            //Updates the sum so far
            System.out.println(totalSum);
        }
    }
    //Populates the Map with the correct letters and values
    private static Map<Character, Integer> populateMap() {
        Map<Character, Integer> toReturn = new HashMap<>();
        toReturn.put('I',1);
        toReturn.put('V',5);
        toReturn.put('X',10);
        toReturn.put('L',50);
        toReturn.put('C',100);
        toReturn.put('D',500);
        toReturn.put('M',1000);

        return toReturn;
    }
    //Checks if the user entered right characters
    private static boolean inputVerifier(String userInput, Map<Character, Integer> map) {
        //Checks if there are characters out of the Map
        for (int i = 0; i < userInput.length(); i++) {
            if (!map.containsKey(userInput.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
