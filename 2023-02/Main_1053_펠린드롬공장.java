import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_1053_펠린드롬공장 {
	static String str;
	static int N, ans = Integer.MAX_VALUE;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		N = str.length();
		// 삽입과 삭제는 같은 연산
		// 좌삽입 or 우삭제: dp[left][right-1]+1
		// 좌삭제 or 우삽입: dp[left+1][right]+1
		// 교환: dp[left+1][right-1]
		
		dp = new int[N][N];
		for (int k=0; k<N; k++) {
			Arrays.fill(dp[k], -1);
		}
		
		ans = makePalindrom(0, N-1, str.toCharArray());
		
		for (int i=0; i<N; i++) {
			for (int j=i+1; j<N; j++) {
				char[] string = str.toCharArray();
				char tmp = string[i];
				string[i] = string[j];
				string[j] = tmp;
				
				dp = new int[N][N];
				for (int k=0; k<N; k++) {
					Arrays.fill(dp[k], -1);
				}
				
				dp[0][N-1] = makePalindrom(0, N-1, string)+1;
				ans = Math.min(ans, dp[0][N-1]);
				
			}
		}
		System.out.println(ans);
	}
	
	public static int makePalindrom(int left, int right, char[] string) {
		if(dp[left][right] != -1) return dp[left][right];
		if(left>=right) return 0;
		
		int tmp = Integer.MAX_VALUE;
		if(string[left] == string[right]) tmp = makePalindrom(left+1, right-1, string);
		else tmp = makePalindrom(left+1, right-1, string)+1;
		return dp[left][right] = Math.min(Math.min(makePalindrom(left+1, right, string)+1, makePalindrom(left, right-1, string)+1), tmp);
		
	}
}
