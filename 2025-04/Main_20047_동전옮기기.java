import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20047_동전옮기기 {
	static int N, L, R;
	static String S, T;
	static char left, right;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		S = br.readLine();
		T = br.readLine();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		boolean [][] dp = new boolean[N][3];
		left = S.charAt(L);
		right = S.charAt(R);
		
		StringBuilder sb = new StringBuilder(S);
		sb.delete(R, R+1);
		sb.delete(L, L+1);
		sb.append("  ");
		S = sb.toString();
		
		if (S.charAt(0) == T.charAt(0)) {
			dp[0][0] = true;
		}
		
		if (left == T.charAt(0)) {
			dp[0][1] = true;
		}

		for (int i=1; i<N; i++) {
			if (dp[i-1][0]) {
				if (S.charAt(i) == T.charAt(i)) {
					dp[i][0] = true;
				}
				if (left == T.charAt(i)) {
					dp[i][1] = true;
				}
			}
			
			if (dp[i-1][1]) {
				if (S.charAt(i-1) == T.charAt(i)) {
					dp[i][1] = true;
				}
				if (right == T.charAt(i)) {
					dp[i][2] = true;
				}
			}
			
			if (dp[i-1][2] && S.charAt(i-2) == T.charAt(i)) {
				dp[i][2] = true;
			}
		}
		
		if (dp[N-1][2]) {
			System.out.println("YES");
			return;
		}
		System.out.println("NO");
	}
}
