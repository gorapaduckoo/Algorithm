import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1796_신기한키보드 {
	static int N = 'z'-'a'+1;
	static boolean[] appeared;
	static int[][] dp, range;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		appeared = new boolean[N];
		dp = new int[N][2];
		range = new int[N][2];
		for (int i=0; i<N; i++) {
			range[i][0] = Integer.MAX_VALUE;
			range[i][1] = -1;
		}
		
		int startIdx = N;
		for (int i=0; i<str.length(); i++) {
			char c = str.charAt(i);
			int idx = c-'a';
			startIdx = Math.min(startIdx, idx);
			
			appeared[idx] = true;
			range[idx][0] = Math.min(range[idx][0], i);
			range[idx][1] = Math.max(range[idx][1], i);
		}
		
		int answer = str.length();
		dp[startIdx][0] = range[startIdx][1] + (range[startIdx][1]-range[startIdx][0]);
		dp[startIdx][1] = range[startIdx][1];
		
		int lastIdx = startIdx;
		for (int i=0; i<N; i++) {
			if(!appeared[i] || i==startIdx) continue;
			
			int diff = range[i][1]-range[i][0];
			
			int lr = Math.abs(range[lastIdx][0]-range[i][1]);
			int rr = Math.abs(range[lastIdx][1]-range[i][1]);
			dp[i][0] = Math.min(dp[lastIdx][0]+lr, dp[lastIdx][1]+rr) + diff;
			
			int ll = Math.abs(range[lastIdx][0]-range[i][0]);
			int rl = Math.abs(range[lastIdx][1]-range[i][0]);
			dp[i][1] = Math.min(dp[lastIdx][0]+ll, dp[lastIdx][1]+rl) + diff;
			lastIdx = i;
		}
		
		System.out.println(answer + Math.min(dp[lastIdx][0], dp[lastIdx][1]));
	}
}
