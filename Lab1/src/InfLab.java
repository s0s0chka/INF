package src;
import java.util.Scanner;
import src.packages.BaseConvert;

public class InfLab {

    public static String convertNumberBase() {

        Scanner scn = new Scanner(System.in);

        System.out.print("Number to convert: ");
        String numberToConvert = scn.nextLine();
        System.out.print("Initial base: ");
        String oldBase = scn.nextLine();
        System.out.print("New base: ");
        String newBase = scn.nextLine();

        scn.close();

        if(newBase.equals("fib")) {
            return BaseConvert.convertFromBaseTenToBaseFib(numberToConvert);
        } else if (newBase.equals("fact")){
            return BaseConvert.convertFromBaseTenToBaseFact(numberToConvert);
        } else if (oldBase.contains("C")) {
            return BaseConvert.convertFromSymBaseToBaseTen(numberToConvert, Integer.parseInt(oldBase.substring(0, oldBase.length()-1))); 
        } else if (oldBase.equals("10")){
            return BaseConvert.convertFromBaseTen(numberToConvert, Integer.parseInt(newBase));
        } else if (newBase.equals("10")) {
            return BaseConvert.convertToBaseTen(numberToConvert, Integer.parseInt(oldBase));
        } else {
            String temp = BaseConvert.convertToBaseTen(numberToConvert, Integer.parseInt(oldBase));
            return BaseConvert.convertFromBaseTen(temp, Integer.parseInt(newBase));
        }
    }

    public static void main(String[] args) {
        System.out.printf("-- FINAL RESULT: %s --\n", convertNumberBase());
    }
}
