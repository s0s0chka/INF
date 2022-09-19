import java.util.Scanner;
import packages.BaseConvert;

public class InfLab {

    public static String convertNumberBase(String numberToConvert, String oldBase, String newBase) {
        if(newBase.equals("fib")) {
            return BaseConvert.convertFromBaseTenToBaseFib(numberToConvert);
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
        Scanner scn = new Scanner(System.in);

        String numberToConvert = scn.nextLine();
        String oldBase = scn.nextLine();
        String newBase = scn.nextLine();

        scn.close();
        
        System.out.println(convertNumberBase(numberToConvert, oldBase, newBase));

    }
}

/*
 * To do: 
 * Base -10 to 10
 * Base 9c to 10
 * Base 10 to fact
 */
