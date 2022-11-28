import java.io.*;
import java.util.*;

public class HillCipher {
    static float[][] decrypt = new float[3][1];
    static float[][] KEY = new float[3][3];
    static float[][] inverse_matrix = new float[3][3];
    static float[][] MESSAGE = new float[3][1];
    static float[][] res = new float[3][1];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        getkeymes();
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 1; col++)
                for (int k = 0; k < 3; k++) {
                    res[row][col] = res[row][col] + KEY[row][k] * MESSAGE[k][col];
                }
        System.out.print("\nEncrypted string is : ");
        for (int row = 0; row < 3; row++) {
            System.out.print((char) (res[row][0] % 26 + 97));
        }
        inverse();
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 1; col++)
                for (int k = 0; k < 3; k++) {
                    decrypt[row][col] = decrypt[row][col] + inverse_matrix[row][k] * res[k][col];
                }
        System.out.print("\nDecrypted string is : ");
        for (int row = 0; row < 3; row++) {
            System.out.print((char) (decrypt[row][0] % 26 + 97));
        }
        System.out.print("\n");
    }

    public static void getkeymes() throws IOException {
        System.out.println("Enter 3x3 matrix for key (It should be inversible): ");
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                KEY[row][col] = sc.nextFloat();
        System.out.print("\nEnter KEY 3 letter string: ");
        String msg = br.readLine();
        for (int row = 0; row < 3; row++)
            MESSAGE[row][0] = msg.charAt(row) - 97;
    }

    public static void inverse() {
        float p, q;
        float[][] c = KEY;
        // Identity Matrix
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++) {
                if (row == col)
                    inverse_matrix[row][col] = 1;
                else
                    inverse_matrix[row][col] = 0;
            }
        for (int k = 0; k < 3; k++) {
            for (int row = 0; row < 3; row++) {
                p = c[row][k];
                q = c[k][k];
                for (int col = 0; col < 3; col++) {
                    if (row != k) {
                        c[row][col] = c[row][col] * q - p * c[k][col];
                        inverse_matrix[row][col] = inverse_matrix[row][col] * q - p * inverse_matrix[k][col];
                    }
                }
            }
        }
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++) {
                inverse_matrix[row][col] = inverse_matrix[row][col] / c[row][row];
            }
        System.out.println("");
        System.out.println("\nInverse Matrix is : ");
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++)
                System.out.print(inverse_matrix[row][col] + " ");
            System.out.print("\n");
        }
    }
}