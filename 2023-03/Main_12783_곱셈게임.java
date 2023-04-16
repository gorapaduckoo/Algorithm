import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_12783_곱셈게임 {
	static int T, N, M, K, ans, MAX = 100000001;
	static boolean[] num;
	static int[] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(T>0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			num = new boolean[10];
			for (int i=0; i<N; i++) {
				int n =Integer.parseInt(st.nextToken());
				num[n] = true;
			}
			
			M = Integer.parseInt(br.readLine());
			while(M>0) {
				K = Integer.parseInt(br.readLine());
				dp = new int[K+1];
				Arrays.fill(dp, -1);
				
				dp[K] = makeNum(K);
				if(dp[K]==MAX) sb.append(-1);
				else sb.append(dp[K]);
				sb.append('\n');
				M--;
			}
			T--;
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
		
		
	}
	
	static int makeNum(int x) {
		if(dp[x]!=-1) return dp[x];
		dp[x] = MAX;
		
		int k = x;
		boolean flag = true;
		while(k>0 && flag) {
			if(num[k%10]) k/=10;
			else flag = false;
		}
		
		if(flag) return dp[x]=0;
		
		for (int i=2; i<=Math.sqrt(x); i++) {
			if(x%i==0) {
				dp[x] = Math.min(dp[x], makeNum(i)+makeNum(x/i)+1);
			}
		}
		return dp[x];
	}
	

}
