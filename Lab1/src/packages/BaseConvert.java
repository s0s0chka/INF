package src.packages;
import java.util.*;

public class BaseConvert {
    private static final String NUMBERS_AS_LETTERS = "0123456789ABCDEF";

    private static String reverseString(String stringToReverse) {
        String result = "";
        System.out.println("Reversing");
        System.out.print("|");
        for (int i = stringToReverse.length()-1; i >= 0; i--){
            System.out.printf(" %c |", stringToReverse.charAt(i));
            result += stringToReverse.charAt(i);
        }
        System.out.println();
        return result;
    }

    private static double getFractionalPartFromString(String fractionalPart){
        double result = 0.0;
        for (int i = 0; i < fractionalPart.length(); i++){
            result += Double.parseDouble(fractionalPart.substring(i, i+1)) * Math.pow(10, -(i+1));
        }
        return result;
    }

    public static String convertFromBaseTen(String numberToConvert, int newBase) {
        System.out.println("______________________________");
        System.out.println("Converting from base ten:");

        String result = "";

        if (numberToConvert.contains(",")){ //check if fractional
            System.out.println("Number is fractional.");
            String[] parts = numberToConvert.split(",");

            int numberToConvertInteger = Integer.parseInt(parts[0]);
            double numberToConvertFractional = getFractionalPartFromString(parts[1]);
            System.out.println("Converting the integer part: ");
            //do the integer part first and then the fractional
            System.out.print("|");
            while (numberToConvertInteger > 0) {
                System.out.printf(" %c |",NUMBERS_AS_LETTERS.charAt(numberToConvertInteger % newBase));
                result += NUMBERS_AS_LETTERS.charAt(numberToConvertInteger % newBase);
                numberToConvertInteger /= newBase;
            } if (result == ""){
                result += "0";
            }
            System.out.println();
            result = reverseString(result) +  ",";
            System.out.println("Converting the fractional part: ");
            System.out.print("|");
            for(int i = 0; (i < 5) && (numberToConvertFractional != 0); i++){
                numberToConvertFractional *= newBase;
                double fractionalPart = numberToConvertFractional % 1;
                int integerPart = (int)(numberToConvertFractional - fractionalPart);
                System.out.printf(" %c |", NUMBERS_AS_LETTERS.charAt(integerPart));
                result += NUMBERS_AS_LETTERS.charAt(integerPart);
                if (integerPart >= 1){
                    numberToConvertFractional = fractionalPart;
                }
            }
            System.out.println();
        } else { //if not fractional
            System.out.println("Number is not fractional.");
            int numberToConvertInteger = Integer.parseInt(numberToConvert);
            System.out.println("Converting: ");
            System.out.print("|");
            while (numberToConvertInteger > 0) {
                System.out.printf(" %c |", NUMBERS_AS_LETTERS.charAt(numberToConvertInteger % newBase));
                result += NUMBERS_AS_LETTERS.charAt(numberToConvertInteger % newBase);
                numberToConvertInteger /= newBase;
            }
            System.out.println();
            result = reverseString(result);
        }
        System.out.printf("Result: %s\n", result);
        System.out.println("______________________________");
        return result;
    }

    public static String convertToBaseTen(String numberToConvert, int OldBase){
        System.out.println("______________________________");
        System.out.println("Converting to base ten");

        if (numberToConvert.contains(",")){ //if fractional
            System.out.println("Number is fractional.");

            double result = 0;
            int indexO = numberToConvert.indexOf(",");
            int integerPartrtLength = numberToConvert.substring(0, indexO).length();
            //do the integer part first and then the fractional
            System.out.println("Converting the integer part:");
            System.out.print("|");
            for(int i = 0; i < integerPartrtLength; i++){
                int CharAsBaseTenNumber = NUMBERS_AS_LETTERS.indexOf(numberToConvert.charAt(i));
                System.out.printf(" %.0f |", CharAsBaseTenNumber * Math.pow(OldBase, integerPartrtLength - i - 1));
                result += CharAsBaseTenNumber * Math.pow(OldBase, integerPartrtLength - i - 1);
            }
            System.out.println();
            System.out.println("Converting the fractional part: ");
            System.out.print("|");
            int counter = -1;
            for (int i = indexO + 1; i < numberToConvert.length(); i++){
                int CharAsBaseTenNumber = NUMBERS_AS_LETTERS.indexOf(numberToConvert.charAt(i));
                System.out.printf(" %.4f |", CharAsBaseTenNumber * Math.pow(OldBase, counter));
                result += CharAsBaseTenNumber * Math.pow(OldBase, counter);
                counter--;
            }
            System.out.println();
            System.out.printf("Result: %s\n", String.format("%.5f", result).replace(".", ","));
            System.out.println("______________________________");
            return (Double.toString(result)).replace(".", ",");
        } else { //if not fractional
            System.out.println("Number is not fractional");
            int result = 0;
            System.out.print("|");
            for(int i = 0; i < numberToConvert.length(); i++){
                int CharAsBaseTenNumber = NUMBERS_AS_LETTERS.indexOf(numberToConvert.charAt(i));
                System.out.printf(" %.0f |", CharAsBaseTenNumber * Math.pow(OldBase, numberToConvert.length() - i - 1));
                result += CharAsBaseTenNumber * Math.pow(OldBase, numberToConvert.length() - i - 1);
            }
            System.out.println();
            System.out.printf("Result: %s\n", result);
            System.out.println("______________________________");
            return Integer.toString(result);
        }
    }

    public static String convertFromBaseTenToBaseFib(String numberToConvert){
        System.out.println("______________________________");
        System.out.println("Converting from base ten to base fib");
        String result = "";
        int numberToConvertInteger = Integer.parseInt(numberToConvert);
        List<Integer> fib = new ArrayList<Integer>();
        fib.add(1);
        fib.add(2);
        //generate all the fib numbers that are smaller than the numberToConvert
        System.out.println("Generating all the fib numbers < number to convert: ");
        while (fib.get(fib.size() - 1) < numberToConvertInteger) {
            fib.add(fib.get(fib.size() - 1) + fib.get(fib.size()-2));
        }
        fib.remove(fib.size()-1);
        fib.sort(Comparator.reverseOrder());
        System.out.print("|");
        for(int i = 0; i < fib.size(); i++) {
            System.out.printf(" %4d |", fib.get(i));
        }
        System.out.println();

        System.out.println("Generating the actual number: ");
        System.out.print("|");
        for (int i = 0; i < fib.size(); i++){
            if(numberToConvertInteger - fib.get(i) >= 0){
                result += "1";
                System.out.printf(" %4d |", 1);
                numberToConvertInteger -= fib.get(i);
            } else {
                System.out.printf(" %4d |", 0);
                result += "0";
            }
        }
        System.out.println();
        System.out.printf("Result: %s\n", result);
        System.out.println("______________________________");
        return result;
    }

    public static String convertFromBaseTenToBaseFact(String numberToConvert){
        System.out.println("______________________________");
        System.out.println("Converting from base ten to base fact");
        String result = "";
        int numberToConvertInteger = Integer.parseInt(numberToConvert);
        int divCounter = 2;
        System.out.print("|");
        while (numberToConvertInteger > 0){
            System.out.printf(" %c |", NUMBERS_AS_LETTERS.charAt(numberToConvertInteger % divCounter));
            result += NUMBERS_AS_LETTERS.charAt(numberToConvertInteger % divCounter);
            numberToConvertInteger /= divCounter;
            divCounter++;
        }
        System.out.println();
        result = reverseString(result);
        System.out.printf("Result: %s\n", result);
        System.out.println("______________________________");
        return result;
    }

    public static String convertFromSymBaseToBaseTen(String numberToConvert, int oldBase){
        System.out.println("______________________________");
        System.out.printf("Converting from symmetrical base %d to base ten\n", oldBase);
        int result = 0;
        int currentNumber;
        String numberToConterWithoutUnderscores = (numberToConvert.replace("_", ""));
        System.out.print("|");
        for (int i = 0; i < numberToConterWithoutUnderscores.length(); i++){
            if (numberToConvert.charAt(i) == '_'){
                numberToConvert = numberToConvert.replaceFirst("_", "");
                currentNumber = -Character.getNumericValue(numberToConvert.charAt(i));
            } else{
                currentNumber = Character.getNumericValue(numberToConvert.charAt(i));
            }
            System.out.printf(" %.0f |", currentNumber * Math.pow(oldBase, numberToConterWithoutUnderscores.length() -1 - i));
            result += currentNumber * Math.pow(oldBase, numberToConterWithoutUnderscores.length() -1 - i);
        }
        System.out.println();
        System.out.printf("Result: %d\n", result);
        System.out.println("______________________________");
        return Integer.toString(result);
    }
}