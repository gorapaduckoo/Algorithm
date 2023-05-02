import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_25634_전구상태뒤집기 {
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[] bright = new int[N];
		boolean[] switchOn = new boolean[N];
		long[][] dp = new long[N][3];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			bright[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			int n = Integer.parseInt(st.nextToken());
			if(n==1) switchOn[i] = true;
		}

		if(switchOn[0]) {
			dp[0][0]=bright[0];
			dp[0][1]=0;
			dp[0][2]=0;
		} else {
			dp[0][0]=0;
			dp[0][1]=bright[0];
			dp[0][2]=0;
		}
		// 0. 토글 시작전
		// 1. 토글 진행중
		// 2. 토글 종료
		
		for (int i=1; i<N; i++) {
			if(switchOn[i]) {
				dp[i][0] = dp[i-1][0]+bright[i];
				dp[i][1] = Math.max(dp[i-1][0], dp[i-1][1]);
				dp[i][2] = Math.max(dp[i-1][1], dp[i-1][2])+bright[i];
			} else {
				dp[i][0] = dp[i-1][0];
				dp[i][1] = Math.max(dp[i-1][0]+bright[i], dp[i-1][1]+bright[i]);
				dp[i][2] = Math.max(dp[i-1][1], dp[i-1][2]);
			}
		}
		
		long ans = Math.max(dp[N-1][1], dp[N-1][2]);
		System.out.println(ans);
	}
}
