import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_2533_사회망서비스SNS {
	static int N, root = 0;
	static List<Integer>[] graph;
	static boolean[] visited;
	static int[][] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new ArrayList[N+1];
		visited = new boolean[N+1];
		dp = new int[N+1][2];
		for (int i=1; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		boolean[] isRoot = new boolean[N+1];
		Arrays.fill(isRoot, true);
		for (int i=0; i<N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			isRoot[e] = false;
			graph[s].add(e);
			graph[e].add(s);
		}
		
		for (int i=1; i<=N; i++) {
			if(isRoot[i]) {
				root = i;
			}
		}
		
		dfs(root);
		
		System.out.println(Math.min(dp[root][0], dp[root][1]));
	}
	
	static void dfs(int now) {
		visited[now] = true;
		if(graph[now].size()==1 && now!=root) {
			dp[now][0] = 0;
			dp[now][1] = 1;
			return;
		}
		
		dp[now][1] = 1;
		for (int next : graph[now]) {
			if(visited[next]) continue;
			dfs(next);
			dp[now][0] += dp[next][1];
			dp[now][1] += Math.min(dp[next][0], dp[next][1]);
		}
	}
}
