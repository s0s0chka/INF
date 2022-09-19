package packages;

import java.util.*;

public class BaseConvert {
    private static final String NUMBERS_AS_LETTERS = "0123456789ABCDEF";

    private static String reverseString(String stringToReverse) {
        String result = "";
        for (int i = stringToReverse.length()-1; i >= 0; i--){
            result += stringToReverse.charAt(i);
        }
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

        String result = "";

        if (numberToConvert.contains(",")){ //check if fractional

            String[] parts = numberToConvert.split(",");

            int numberToConvertInteger = Integer.parseInt(parts[0]);
            double numberToConvertFractional = getFractionalPartFromString(parts[1]);

            while (numberToConvertInteger > 0) {
                result += NUMBERS_AS_LETTERS.charAt(numberToConvertInteger % newBase);
                numberToConvertInteger /= newBase;
            }

            result = reverseString(result) +  ",";

            while(numberToConvertFractional != 0){
                numberToConvertFractional *= newBase;
                double fractionalPart = numberToConvertFractional % 1;
                int integerPart = (int)(numberToConvertFractional - fractionalPart);
                result += NUMBERS_AS_LETTERS.charAt(integerPart);
                if (numberToConvertInteger >= 1){
                    numberToConvertFractional = fractionalPart;
                }
            }
        } else {
            int numberToConvertInteger = Integer.parseInt(numberToConvert);
            while (numberToConvertInteger > 0) {
                result += NUMBERS_AS_LETTERS.charAt(numberToConvertInteger % newBase);
                numberToConvertInteger /= newBase;
            }
            result = reverseString(result);
        }
        return result;
    }

    public static String convertToBaseTen(String numberToConvert, int OldBase){
        if (numberToConvert.contains(",")){
            double result = 0;
            int indexO = numberToConvert.indexOf(",");
            int integerPartrtLength = numberToConvert.substring(0, indexO).length();
            for(int i = 0; i < integerPartrtLength; i++){
                int CharAsBaseTenNumber = NUMBERS_AS_LETTERS.indexOf(numberToConvert.charAt(i));
                result += CharAsBaseTenNumber * Math.pow(OldBase, integerPartrtLength - i - 1);
            }
            int counter = -1;
            for (int i = indexO + 1; i < numberToConvert.length(); i++){
                int CharAsBaseTenNumber = NUMBERS_AS_LETTERS.indexOf(numberToConvert.charAt(i));
                result += CharAsBaseTenNumber * Math.pow(OldBase, counter);
                counter--;
            }
            return (Double.toString(result)).replace(".", ",");
        } else {
            int result = 0;
            for(int i = 0; i < numberToConvert.length(); i++){
                int CharAsBaseTenNumber = NUMBERS_AS_LETTERS.indexOf(numberToConvert.charAt(i));
                result += CharAsBaseTenNumber * Math.pow(OldBase, numberToConvert.length() - i - 1);
            }
            return Integer.toString(result);
        }
    }

    public static String convertFromBaseTenToBaseFib(String numberToConvert){
        String result = "";
        int numberToConvertInteger = Integer.parseInt(numberToConvert);
        List<Integer> fib = new ArrayList<Integer>();
        fib.add(1);
        fib.add(2);
        while (fib.get(fib.size() - 1) < numberToConvertInteger) {
            fib.add(fib.get(fib.size() - 1) + fib.get(fib.size()-2));
        }
        fib.remove(fib.size()-1);
        fib.sort(Comparator.reverseOrder());
        for (int i = 0; i < fib.size(); i++){
            if(numberToConvertInteger - fib.get(i) >= 0){
                result += "1";
                numberToConvertInteger -= fib.get(i);
            } else {
                result += "0";
            }
        }
        return result;
    }
}