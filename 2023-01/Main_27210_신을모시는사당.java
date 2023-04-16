import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_27210_신을모시는사당 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int leftMax=0, rightMax=0;
		int[] arr = new int[N+1];
		int[][] dp = new int[N+1][2];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			// 2의 개수: i-sum[i]
			// dp[i]: i번째 원소로 끝나는 부분수열 중 최댓값
		}
		for (int i=1; i<=N; i++) {
			if(arr[i]==1) {
				dp[i][0] = Math.max(dp[i-1][0]+1, 1); // 오른쪽으로 향하는 불상 셈
				dp[i][1] = Math.max(dp[i-1][1]-1, 0); // 왼쪽으로 향하는 불상 셈
				rightMax = Math.max(rightMax, dp[i][0]);
			} else {
				dp[i][0] = Math.max(dp[i-1][0]-1, 0);
				dp[i][1] = Math.max(dp[i-1][1]+1, 1);
				leftMax = Math.max(leftMax, dp[i][1]);
			}
		}
		System.out.println(Math.max(rightMax, leftMax));
	}
}
