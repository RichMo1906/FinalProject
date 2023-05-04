import java.util.*;

public class Cryptography {
    public static void main(String[] args) {

        String plainText = "If you can trust yourself when all men doubt you, But make allowance for their doubting too;";
        String encrText =  "Pm6FvB6jhu6AyBzA6FvByzlsm6Dolu6hss6tlu6kvBiA6FvBd6IBA6thrl6hssvDhujl6mvy6Aolpy6kvBiApun6Avv";

        char[] chrCheck = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q','r',
                           's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                           'K','L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ', '0',
                           '1', '2', '3','4', '5', '6', '7', '8', '9','!', '@', '#', '$', '%', '^', '&', '*','(',
                           ')', '-', '_', '=','+', '<', '>', ',', '.', '?', '/' };

        char[] chrArray = plainText.toCharArray();
        char[] encrArray = encrText.toCharArray();
        char chrOut = ' ';
        String strCharOut;
        int key = 7;
        int indx = 0;

        //Get Encryption Choice
        System.out.println("Do you want to Encrypt or Decrypt?:");
        Scanner keyboard = new Scanner(System.in);
        String decrEncrChoice = keyboard.nextLine();

        ArrayList<String> chrsOut = new ArrayList<String>(); // Create an ArrayList object

        //Encryption
        if (decrEncrChoice.equalsIgnoreCase("Encrypt")){

            for (int i = 0; i < plainText.length(); i++) {
                for (int j = 0; j < chrCheck.length; j++) {

                    if ((chrArray[i] == chrCheck[j]) && ((j + key) <= chrCheck.length)) {
                        chrOut = chrCheck[j + key];
                        strCharOut = String.valueOf(chrOut);
                        chrsOut.add(strCharOut);
                        System.out.print(chrOut);

                    } else if ((chrArray[i] == chrCheck[j]) && ((j+key) > chrCheck.length)) {
                        indx = key - (chrCheck.length - j);
                        chrOut = chrCheck[indx];
                        System.out.print(chrOut);
                    }

                }
            }
        }

        // Decryption
        if (decrEncrChoice.equalsIgnoreCase("Decrypt")){

            for (int k = 0; k < encrText.length(); k++) {
                for (int l = 0; l < chrCheck.length; l++) {

                    if (encrArray[k] == chrCheck[l]) {  //&&  {
                        //System.out.print(encrArray[k] + "[" + k + "]");
                        //System.out.println(" = " + chrCheck[l] + "[" + l + "]");

                        if (l >= key) {
                            indx = l - key;
                            chrOut = chrCheck[indx];
                            System.out.print(chrOut);

                        } else if (l < key){
                            indx = (chrCheck.length) - Math.abs((l - key));
                            chrOut = chrCheck[Math.abs(indx)];
                            System.out.print(chrOut);
                        }
                    }
                }
            }
        }
    }
}
