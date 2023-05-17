import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main_2281_데스노트 {
	static int N, M;
	static int[] name;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		name = new int[N];
		dp = new int[N][M+2];
		for (int i=0; i<N; i++) {
			name[i] = Integer.parseInt(br.readLine());
		}
		
		
		
		for(int i=0; i<N; i++) {
			Arrays.fill(dp[i], -1);
		}
		int answer = write(0,0);
		System.out.println(answer);
	}
	
	static int write(int nowIdx, int used) {
		if(nowIdx>=N) return 0;
		if(dp[nowIdx][used]!=-1) {
			return dp[nowIdx][used];
		}
		
		int nowLeft = M-used+1;
		int result = nowLeft*nowLeft + write(nowIdx+1, name[nowIdx]+1);
		if(used+name[nowIdx]<=M) {
			result = Math.min(result, write(nowIdx+1, used+name[nowIdx]+1));
		}
		
		return dp[nowIdx][used] = result;
	}
	
}
