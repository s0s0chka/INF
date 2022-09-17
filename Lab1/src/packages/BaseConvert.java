package src.packages;

public class BaseConvert {
    private static final String NUMBERS_AS_LETTERS = "0123456789ABCDEF";

    private static String ReverseString(String StrToReverse) {
        String result = "";
        for (int i = StrToReverse.length()-1; i >= 0; i--){
            result += StrToReverse.charAt(i);
        }
        return result;
    }

    private static double FractionalPartFromString(String FractionalPart){
        double result = 0.0;
        for (int i = 0; i < FractionalPart.length(); i++){
            result += Double.parseDouble(FractionalPart.substring(i, i+1)) * Math.pow(10, -(i+1));
        }
        return result;
    }

    public static String ConvertFromBaseTen(String NumberToConvert, int NewBase) {

        String result = "";

        if (NumberToConvert.contains(",")){ //check if fractional

            String[] parts = NumberToConvert.split(",");

            int NumberToConvert_Integer = Integer.parseInt(parts[0]);
            double NumberToConvert_Fractional = FractionalPartFromString(parts[1]);

            while (NumberToConvert_Integer > 0) {
                result += NUMBERS_AS_LETTERS.charAt(NumberToConvert_Integer % NewBase);
                NumberToConvert_Integer /= NewBase;
            }

            result = ReverseString(result) +  ",";

            while(NumberToConvert_Fractional != 0){
                NumberToConvert_Fractional *= NewBase;
                double FrPa = NumberToConvert_Fractional % 1;
                double IntPa = NumberToConvert_Fractional - FrPa;
                result += NUMBERS_AS_LETTERS.charAt((int)IntPa);
                if (IntPa >= 1){
                    NumberToConvert_Fractional = FrPa;
                }
            }
        } else {
            int NumberToConvert_Integer = Integer.parseInt(NumberToConvert);
            while (NumberToConvert_Integer > 0) {
                result += NUMBERS_AS_LETTERS.charAt(NumberToConvert_Integer % NewBase);
                NumberToConvert_Integer /= NewBase;
            }
            result = ReverseString(result);
        }
        return result;
    }

    public static String ConvertToBaseTen(String NumberToConvert, int OldBase){
        if (NumberToConvert.contains(",")){
            double result = 0;
            int indexO = NumberToConvert.indexOf(",");
            int IntPartLength = NumberToConvert.substring(0, indexO).length();
            for(int i = 0; i < IntPartLength; i++){
                char CurrentChar = NumberToConvert.charAt(i);
                int CharAsBaseTenNumber = NUMBERS_AS_LETTERS.indexOf(CurrentChar);
                result += CharAsBaseTenNumber * Math.pow(OldBase, IntPartLength - i - 1);
            }
            int counter = -1;
            for (int i = indexO + 1; i < NumberToConvert.length(); i++){
                char CurrentChar = NumberToConvert.charAt(i);
                int CharAsBaseTenNumber = NUMBERS_AS_LETTERS.indexOf(CurrentChar);
                result += CharAsBaseTenNumber * Math.pow(OldBase, counter);
                counter--;
            }
            return (Double.toString(result)).replace(".", ",");
        } else {
            int result = 0;
            for(int i = 0; i < NumberToConvert.length(); i++){
                char CurrentChar = NumberToConvert.charAt(i);
                int CharAsBaseTenNumber = NUMBERS_AS_LETTERS.indexOf(CurrentChar);
                result += CharAsBaseTenNumber * Math.pow(OldBase, NumberToConvert.length() - i - 1);
            }
            return Integer.toString(result);
        }
    }
}