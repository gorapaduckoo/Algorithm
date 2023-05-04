import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1325_효율적인해킹 {
	static int N, M;
	static boolean[] visited;
	static int[] count;
	static List<Integer>[] graph;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		count = new int[N+1];
		graph = new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			graph[s].add(e);
		}
		
		for (int i=1; i<=N; i++) {
			visited = new boolean[N+1];
			dfs(i);
		}
		
		int maxCount =  0;
		List<Integer> ans = new ArrayList<>();
		for (int i=1; i<=N; i++) {
			if(count[i]<maxCount) continue;
			else if(count[i]==maxCount) ans.add(i);
			else {
				maxCount = count[i];
				ans = new ArrayList<>();
				ans.add(i);
			}
		}
		
		Collections.sort(ans);
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<ans.size(); i++) {
			sb.append(ans.get(i) + " ");
		}
		System.out.println(sb.toString());
		
	}
	
	static void dfs(int now) {
		visited[now] = true;
		for (int next : graph[now]) {
			if(visited[next]) continue;
			count[next]++;
			dfs(next);
		}
	}
}
