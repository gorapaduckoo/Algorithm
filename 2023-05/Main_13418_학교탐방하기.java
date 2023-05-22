import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Road{
	int idx;
	int isAscent;
	Road(int idx, int isAscent) {
		this.idx = idx;
		this.isAscent = isAscent;
	}
}

public class Main_13418_학교탐방하기 {
	static int N, M;
	static List<Road>[] graph;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N+1];
		for (int i=0; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i=0; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int isAscent = Integer.parseInt(st.nextToken());
			graph[s].add(new Road(e, isAscent));
			graph[e].add(new Road(s, isAscent));
		}
		
		int maxStress = findRoute(0);
		int minStress = findRoute(1);
		System.out.println(maxStress-minStress);
	}

	
	static int findRoute(int isBest) {
		int result = 0;
		PriorityQueue<Road> pq;
		if(isBest==0) {
			pq = new PriorityQueue<>((o1,o2) -> {
				return o1.isAscent - o2.isAscent;
			});
		} else {
			pq = new PriorityQueue<>((o1,o2) -> {
				return o2.isAscent - o1.isAscent;
			});
		}
		boolean[] visited = new boolean[N+1];
		visited[0] = true;
		for(Road road : graph[0]) {
			pq.add(road);
		}
		
		while(!pq.isEmpty()) {
			Road now = pq.poll();
			if(visited[now.idx]) continue;
			visited[now.idx] = true;
			if(now.isAscent==0) result++;
			
			for (Road next : graph[now.idx]) {
				if(visited[next.idx]) continue;
				pq.add(next);
			}
		}
		
		return result*result;
	}
}
