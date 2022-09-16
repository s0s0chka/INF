import java.util.Scanner;
import src.packages.BaseConvert;

public class InfLab {

    public static String ConvertWithNumberBases(String NumberToConvert, int OldBase, int NewBase) {
        if (OldBase == 10){
            return BaseConvert.ConvertFromBaseTen(NumberToConvert, NewBase);
        } else if (OldBase != 10 && NewBase != 10) {
            String temp = BaseConvert.ConvertToBaseTen(NumberToConvert, OldBase);
            return BaseConvert.ConvertFromBaseTen(temp, NewBase);
        } else {
            return BaseConvert.ConvertToBaseTen(NumberToConvert, OldBase);
        }
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        String NumberToConvert = scn.nextLine();
        int OldBase = scn.nextInt();
        int NewBase = scn.nextInt();

        scn.close();
        
        System.out.println(ConvertWithNumberBases(NumberToConvert, OldBase, NewBase));
    }
}
