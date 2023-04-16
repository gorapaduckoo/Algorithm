import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2629_양팔저울 {
	static int N, M, T=0;
	static int[] weight, marble;
	static boolean[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		weight = new int[N];
		dp = new boolean[N+1][15001];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			weight[i] = Integer.parseInt(st.nextToken());
		}
		check(0,0);
		
		M = Integer.parseInt(br.readLine());
		marble = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<M; i++) {
			marble[i] = Integer.parseInt(st.nextToken());
		}
		StringBuilder sb = new StringBuilder();
		while(T<M) {
			if(marble[T] > 15000) {
				sb.append("N ");
			} else if(dp[N][marble[T]]) {
				sb.append("Y ");
			} else {
				sb.append("N ");
			}
			T++;
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	
	static void check(int sum, int idx) {
		if(dp[idx][sum]) return;
		dp[idx][sum] = true;
		if(idx>=N) return;
		check(sum+weight[idx], idx+1);
		check(Math.abs(sum-weight[idx]), idx+1);
		check(sum, idx+1);
	}
}
