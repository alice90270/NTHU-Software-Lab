import java.io.*;
import java.util.Arrays;

public class Block {
	public static void main(String[] args) throws Exception {
		System.out.print("Please enter your sequence: ");
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in)); 
		String line = buf.readLine();
		String[] strNum = line.split(" ");
		int[] num = new int[strNum.length];
		for (int i = 0; i < strNum.length; i++)
			num[i] = Integer.parseInt(strNum[i]);
			
		int[][] table = new int[num.length][num.length];
		int[] longest = null;
		
		for (int i = 0; i < num.length; i++)
			table[i][i] = 1;
			
		for (int i = 0; i <= num.length - 2; i++)
			if (num[i] == num[i+1]) {
				table[i][i+1] = 1;
				longest = Arrays.copyOfRange(num, i, i+2);
			}
			
		for (int l = 3; l <= num.length; l++)
			for (int i = 0; i < num.length-l; i++) {
				int j = i + l - 1;
				if (num[i] == num[j]) {
					table[i][j] = table[i+1][j-1];
					if (table[i][j] == 1 && (longest == null || l > longest.length))
						longest = Arrays.copyOfRange(num, i, j+1);
				} else {
					table[i][j] = 0;
				}
			}
		byte[] bin = {(byte)0xa2, (byte)0x68};
		String str = new String(bin, "Big5");
		for (int i = 0; i < longest.length; i++) {
			for (int j = 0; j < longest[i]; j++)
				System.out.print(str);
			System.out.println("");
		}
			
	}
}