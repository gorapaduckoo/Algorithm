import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Island implements Comparable<Island>{
	int idx;
	int weight;
	public Island (int idx, int weight) {
		this.idx = idx;
		this.weight = weight;
	}
	
	public int compareTo(Island o) {
		return o.weight - this.weight;
	}
}

public class Main_1939_중량제한 {
	static int N, M, S, E, ans = 0;
	static List<Island>[] graph; 
	static boolean[] visited;
	static PriorityQueue<Island> pq;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N+1];
		visited = new boolean[N+1];
		pq = new PriorityQueue<>();
		for (int i=1; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[s].add(new Island(e, w));
			graph[e].add(new Island(s, w));
		}
		
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		pq.add(new Island(S,Integer.MAX_VALUE));
		while(!pq.isEmpty()) {
			Island now = pq.poll();
			if(now.idx==E) {
				ans = Math.max(ans, now.weight);
			}
			if(visited[now.idx]) continue;
			
			visited[now.idx] = true;
			for (Island next : graph[now.idx]) {
				if(visited[next.idx]) continue;
				pq.add(new Island(next.idx, Math.min(now.weight, next.weight)));
			}
		}
		
		System.out.println(ans);
	}
}
