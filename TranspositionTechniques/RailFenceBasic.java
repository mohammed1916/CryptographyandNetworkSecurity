import java.util.*;

class RailFenceBasic {
    int depth;

    String Encryption(String plainText, int depth) throws Exception {
        int len = plainText.length();
        int NCol = (int) Math.ceil((float) len / (float) depth);
        char mat[][] = new char[depth][NCol];
        int k = 0;
        String cipherText = "";
        for (int col = 0; col < NCol; col++) {
            for (int row = 0; row < depth; row++) {
                if (k != len)
                    mat[row][col] = plainText.charAt(k++);
                else
                    mat[row][col] = 'X';
            }
        }
        for (int row = 0; row < depth; row++) {
            for (int col = 0; col < NCol; col++) {
                cipherText += mat[row][col];
            }
        }
        return cipherText;
    }

    String Decryption(String cipherText, int depth) throws Exception {
        int len = cipherText.length();
        int NCol = len / depth;
        char mat[][] = new char[depth][NCol];
        int k = 0;
        String plainText = "";
        for (int row = 0; row < depth; row++) {
            for (int col = 0; col < NCol; col++) {
                mat[row][col] = cipherText.charAt(k++);
            }
        }
        for (int col = 0; col < NCol; col++) {
            for (int row = 0; row < depth; row++) {
                plainText += mat[row][col];
            }
        }
        return plainText;
    }
}

class RailFence2 {
    public static void main(String args[]) throws Exception {
        RailFenceBasic rf = new RailFenceBasic();
        Scanner scn = new Scanner(System.in);
        int depth;
        String plainText, cipherText, decryptedText;
        System.out.println("Enter plain text:");
        plainText = scn.nextLine();
        System.out.println("Enter depth for Encryption:");
        depth = scn.nextInt();
        cipherText = rf.Encryption(plainText, depth);
        System.out.println("Encrypted text is:\n" + cipherText);
        decryptedText = rf.Decryption(cipherText, depth);
        System.out.println("Decrypted text is:\n" + decryptedText);
    }
}