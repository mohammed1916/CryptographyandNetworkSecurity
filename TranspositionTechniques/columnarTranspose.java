import java.util.*;
import java.io.*;
import java.lang.*;

public class columnarTranspose {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String line = System.getProperty("line.separator");
        scan.useDelimiter(line);
        System.out.print("1. Encryt 2.Decrypt : ");
        int option = scan.nextInt();
        switch (option) {
            case 1:
                System.out.print("Enter String:");
                String text = scan.next();
                System.out.print("Enter Key:");
                String key = scan.next();
                System.out.println(encryptCT(key, text).toUpperCase());
                break;
            case 2:
                System.out.print("Enter Encrypted String:");
                text = scan.next();
                System.out.print("Enter Key:");
                key = scan.next();
                System.out.println(decryptCT(key, text));
                break;
            default:
                break;
        }
    }

    public static String encryptCT(String key, String text) {
        int[] arrange = arrangeKey(key);
        int NCOL = arrange.length;
        int lentext = text.length();
        int NROW = (int) Math.ceil((double) lentext / NCOL);
        char[][] grid = new char[NROW][NCOL];
        int z = 0;
        for (int row = 0; row < NROW; row++) {
            for (int col = 0; col < NCOL; col++) {
                if (lentext == z) {
                    // at random alpha for trailing null grid
                    grid[row][col] = RandomAlpha();
                    z--;
                } else {
                    grid[row][col] = text.charAt(z);
                }
                z++;
            }
        }
        String enc = "";
        for (int k = 0; k < NCOL; k++) {
            for (int col = 0; col < NCOL; col++) {
                if (k == arrange[col]) {
                    for (int row = 0; row < NROW; row++) {
                        enc = enc + grid[row][col];
                    }
                }
            }
        }
        return enc;
    }

    public static String decryptCT(String key, String text) {
        int[] arrange = arrangeKey(key);
        int NCOL = arrange.length;
        int lentext = text.length();
        int NROW = (int) Math.ceil((double) lentext / NCOL);
        String regex = "(?<=\\G.{" + NROW + "})";
        String[] get = text.split(regex);
        char[][] grid = new char[NROW][NCOL];
        for (int x = 0; x < NCOL; x++) {
            for (int col = 0; col < NCOL; col++) {
                if (arrange[x] == col) {
                    for (int row = 0; row < NROW; row++) {
                        grid[row][col] = get[arrange[col]].charAt(row);
                    }
                }
            }
        }
        String dec = "";
        for (int row = 0; row < NROW; row++) {
            for (int col = 0; col < NCOL; col++) {
                dec = dec + grid[row][col];
            }
        }
        return dec;
    }

    public static char RandomAlpha() {
        // generate random alpha for null space
        Random r = new Random();
        return (char) (r.nextInt(26) + 'a');
    }

    public static int[] arrangeKey(String key) {
        // arrange position of grid
        String[] keys = key.split("");
        Arrays.sort(keys);
        int[] num = new int[key.length()];
        for (int x = 0; x < keys.length; x++) {
            for (int y = 0; y < key.length(); y++) {
                if (keys[x].equals(key.charAt(y) + "")) {
                    num[y] = x;
                    break;
                }
            }
        }
        return num;
    }
}