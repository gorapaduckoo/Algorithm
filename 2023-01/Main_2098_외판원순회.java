import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2098_외판원순회 {
	static int N, INF = 987654321;
	static int IMPOSSIBLE = 16000001;
	static int[][] map, dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new int[N][(1<<N)-1];
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i=0; i<N; i++) {
			Arrays.fill(dp[i], INF);
		}
		
		System.out.println(move(0,1));
	}
	
	static int move(int now, int visited) {
		if(visited == (1<<N)-1) {
			if(map[now][0] == 0) {
				return IMPOSSIBLE;
			}
			return map[now][0];
		}
		
		if(dp[now][visited]!=INF) {
			return dp[now][visited];
		}
		
		for(int i=0; i<N; i++) {
			if(map[now][i]==0) continue;
			if((visited&(1<<i)) != 0) continue;
			dp[now][visited] = Math.min(dp[now][visited], map[now][i]+move(i, visited|(1<<i)));
//			System.out.println("i: " + i + ", " + map[now][i]+move(i, visited|(1<<i)));
		}
//		System.out.println("3. move(" + now + ", " + visited + "): " + dp[now][visited]);
		return dp[now][visited];
	}
}
