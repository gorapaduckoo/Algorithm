import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Island{
	int idx;
	int time;
	int cost;
	
	public Island(int next, int dist, int cost) {
		this.idx = next;
		this.time = dist;
		this.cost = cost;
	}
}

public class Main_10776_제국 {
	
	static int K, N, M, START, END, MAX = Integer.MAX_VALUE;
	static int[][] minTime;
	static List<Island>[] graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		minTime = new int[N+1][K+1];
		for (int i=1; i<=N; i++) {
			Arrays.fill(minTime[i], MAX);
		}
		
		graph = new List[N+1];
		for (int i=1; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			graph[a].add(new Island(b, dist, cost));
			graph[b].add(new Island(a, dist, cost));
		}
		
		st = new StringTokenizer(br.readLine());
		START = Integer.parseInt(st.nextToken());
		END = Integer.parseInt(st.nextToken());
		
		
		Queue<Island> q = new ArrayDeque<Island>();
		q.add(new Island(START, 0, 0));
		minTime[START][0] = 0;
		
		while(!q.isEmpty()) {
			Island now = q.poll();

			if (now.idx == END) {
				continue;
			}
			
			for (Island next : graph[now.idx]) {
				if (now.cost + next.cost >= K) continue;
				if (minTime[next.idx][now.cost + next.cost] <= now.time+next.time) continue;
				q.add(new Island(next.idx, now.time+next.time, now.cost+next.cost));
				minTime[next.idx][now.cost + next.cost] = now.time + next.time;
			}
		}
		
		int answer = MAX;
		for (int i=0; i<=K; i++) {
			answer = Math.min(answer, minTime[END][i]);
		}
		
		if (answer == MAX) {
			answer = -1;
		}
		System.out.println(answer);
	}
}
