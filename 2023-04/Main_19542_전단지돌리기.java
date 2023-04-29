import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_19542_전단지돌리기 {
	static int N, S, D, visitNode = 0;
	static List<Integer>[] graph;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N+1];
		visited = new boolean[N+1];
		for (int i=1; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			graph[s].add(e);
			graph[e].add(s);
		}
		
		dfs(S, 0);
		System.out.println(visitNode*2);
	}
	
	static int dfs(int now, int dist) {
		visited[now] = true;
		int maxDist = dist;
		for (int next : graph[now]) {
			if(visited[next]) continue;
			maxDist = Math.max(maxDist, dfs(next, dist+1));
		}
		
		int distToLeaf = maxDist-dist;
		if(now!=S && distToLeaf>=D) visitNode++; 
		
		return maxDist;
	}
}
