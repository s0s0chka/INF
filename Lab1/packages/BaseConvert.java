package packages;

public class BaseConvert {
    private static final String NUMBERS_AS_LETTERS = "0123456789ABCDEF";

    public static String ReverseString(String StrToReverse) {
        String result = "";
        for (int i = StrToReverse.length()-1; i >= 0; i--){
            result += StrToReverse.charAt(i);
        }
        return result;
    }

    public static String ConvertFromBaseTen(String NumberToConvert, int NewBase) {
        int NumberToConvert_Integer = Integer.parseInt(NumberToConvert);
        String result = "";
        while (NumberToConvert_Integer > 0) {
            result += Integer.toString(NumberToConvert_Integer % NewBase);
            NumberToConvert_Integer /= NewBase;
        }
        return ReverseString(result);
    }

    public static String ConvertToBaseTen(String NumberToConvert, int OldBase){
        int result = 0;
        for(int i = 0; i < NumberToConvert.length(); i++){
            char CurrentChar = NumberToConvert.charAt(i);
            int CharAsBaseTenNumber = NUMBERS_AS_LETTERS.indexOf(CurrentChar);
            result += CharAsBaseTenNumber * Math.pow(OldBase, NumberToConvert.length() - i - 1);
        }
        return Integer.toString(result);
    }
}