import java.util.Scanner;
public class InfLab {
    public static boolean powerOfTwo(int number){
        int check = number & (number - 1);
        boolean result = check == 0 ? true : false;
        return result;
    }

    public static int chrToIntStr(String string, int index){
        return Character.getNumericValue(string.charAt(index));
    }

    public static String removeBits(String message){
        String newMessage = "";
        for (int i = 0; i < message.length(); i++){
            if (!powerOfTwo(i+1)){
                newMessage += message.charAt(i);
            }
        }
        return newMessage;
    }
    public static void main(String args[]){
        Scanner scn = new Scanner(System.in);
        String message = scn.nextLine();
        scn.close();

        int s1 = (chrToIntStr(message, 0) + chrToIntStr(message, 2) + chrToIntStr(message, 4) + chrToIntStr(message, 6)) % 2;
        int s2 = (chrToIntStr(message, 1) + chrToIntStr(message, 2) + chrToIntStr(message, 5) + chrToIntStr(message, 6)) % 2;
        int s3 = (chrToIntStr(message, 3) + chrToIntStr(message, 4) + chrToIntStr(message, 5) + chrToIntStr(message, 6)) % 2;

        int errIndex = s3 * 4 + s2 * 2 + s1;

        if (errIndex != 0){
            if (chrToIntStr(message, errIndex -1) == 0){
                message = message.substring(0, errIndex-1) + '1' + message.substring(errIndex);
            }else {
                message = message.substring(0, errIndex-1) + '0' + message.substring(errIndex);
            }
            System.out.println("Error in bit " + Integer.toString(errIndex));
            System.out.println("Fixed full message: " + message);
            message = removeBits(message);
            System.out.println("Fixed message with removed bits: " + message);
        } else {
            System.out.println("No errors");
            System.out.println("Full message: " + message);
            message = removeBits(message);
            System.out.println("Message with removed bits: " + message);
        }
    }
}