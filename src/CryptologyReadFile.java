import java.io.*;
import java.nio.*;
import java.util.*;

public class CryptologyReadFile {
    public static void main(String[] args) throws IOException {

        String textFile = "C:/Users/rmorris/IdeaProjects/FinalProject2/com/example/finalproject/demo.txt";
        String encryptedFile = "C:/Users/rmorris/IdeaProjects/FinalProject2/com/example/finalproject/encryptedfile.txt";
        String decryptedFile = "C:/Users/rmorris/IdeaProjects/FinalProject2/com/example/finalproject/decryptedfile.txt";

        char[] chrCheck = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
                'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ', '0', '1', '2', '3',
                '4', '5', '6', '7', '8', '9', '0', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '=',
                '+', '<', '>', ',', '.', '?', '/', ';' };

        char[] array = new char[2048];
        char[] dataArray = new char[2048];
        char[] arrayOut = new char[2048];
        char chrOut;

        int key = 7;
        int indx = 0;
        int indx2 = 0;
        int displacement = 0;

        displacement = chrCheck.length - key;

    //Get Encryption Choice
        System.out.println("Do you want to Encrypt or Decrypt?:");
        Scanner keyboard = new Scanner(System.in);
        String decrEncrChoice = keyboard.nextLine();

        try {
            // Creates a reader using the FileReader
            FileReader input = new FileReader(textFile);

            // Reads characters
            input.read(array);


            if (decrEncrChoice.equalsIgnoreCase("Encrypt")) {
            // Build Encrypted file array
                try {
                    File fileOut = new File(encryptedFile);

                //Create Encrypted file
                    if (fileOut.createNewFile()) {
                        System.out.println("File created: " + fileOut.getName());
                    } else {
                        System.out.println("File already exists.");
                        }

                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                //Encrypt each text character in file
                for (int i = 0; i < array.length; i++) {
                    for (int j = 0; j < chrCheck.length; j++) {

                        if (array[i] == chrCheck[j]) {
                            if (j <= displacement){
                                indx = (j + key) % chrCheck.length;
                                chrOut = chrCheck[indx];
                                arrayOut[i] = chrOut;

                            } else if (j > displacement){
                                indx = key - Math.abs(chrCheck.length - j);
                                chrOut = chrCheck[indx];
                                arrayOut[i] = chrOut;
                            }
                        }
                    }
                }
            } else if (decrEncrChoice.equalsIgnoreCase("Decrypt")) {

                // Decrypt each text character in file
                try {
                    // Creates a reader using the FileReader
                    FileReader encryptIn = new FileReader(encryptedFile);

                    // Reads characters
                    encryptIn.read(dataArray);

                    try {
                        File decryptOut = new File(decryptedFile);

                        //Create Decrypted file
                        if (decryptOut.createNewFile()) {
                            System.out.println("File created: " + decryptOut.getName());
                        } else {
                            System.out.println("File exists.");
                        }

                        // Build Decrypted file array
                        try {

                            FileWriter myWriter = new FileWriter(encryptedFile);

                        // Decryption char by char
                            for (int i = 0; i < dataArray.length -1; i++) {
                                for (int j = 0; j < chrCheck.length -1; j++) {

                                    if (dataArray[i] == chrCheck[j]) {
                                        if (j >= key){
                                            indx = (j-key) % chrCheck.length;
                                            chrOut = chrCheck[indx];
                                            arrayOut[i] = chrOut;

                                        } else if (j < key){
                                            indx = chrCheck.length - (key - j);
                                            chrOut = chrCheck[Math.abs(indx)];
                                            arrayOut[i] = chrOut;
                                        }
                                    }
                                }
                            }
                            myWriter.close();
                            System.out.println();

                            //Catch IOException
                        } catch (IOException e) {
                            System.out.println("An error occurred.");
                            e.printStackTrace();
                            }

                        // Closes the reader
                        input.close();
                    }
                    //Catch exception
                    catch (Exception e) {
                        e.getStackTrace();
                    }

                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        }  catch (Exception e) {
            e.getStackTrace();
            }
//  Lets write the output files
    if (decrEncrChoice.equalsIgnoreCase("Encrypt")) {

    //Output encrypted file

            System.out.println("");
            System.out.println("Writing to file");

            FileWriter myWriter = new FileWriter(encryptedFile);

            char[] encryptOut = arrayOut;

            //  Write txt char by char
            try {

                BufferedWriter writer = new BufferedWriter(myWriter);

                for ( int i = 0; i < encryptOut.length; i++){

                    if (encryptOut[i] != '\u0000') {
                        System.out.print(encryptOut[i]);
                        writer.write(encryptOut[i]);
                    }
                }
                System.out.println();
                System.out.println("Encrypting file complete");
                writer.close();

                } catch(IOException ex) {
                    ex.printStackTrace();
                    }
    } else if (decrEncrChoice.equalsIgnoreCase("Decrypt")){

    //Output Decrypted file

            System.out.println();
            System.out.println("Writing to file");

            FileWriter myWriter = new FileWriter(decryptedFile);

            char[] decryptOut = arrayOut;

            //  Write txt char by char
            try {

                BufferedWriter writer = new BufferedWriter(myWriter);

                for ( int i = 0; i < decryptOut.length - 1; i++)
                {
                    if (decryptOut[i] != '\u0000') {
                        System.out.print(decryptOut[i]);
                        writer.write(decryptOut[i]);
                    }

                }
                writer.close();

            } catch(IOException ex) {
                ex.printStackTrace();
                }
        System.out.println();
        System.out.println("Decrypting file complete");
        }
    }
}
