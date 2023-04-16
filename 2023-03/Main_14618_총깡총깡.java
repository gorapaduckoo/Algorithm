import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge>{
	int next;
	int length;
	public Edge(int next, int length) {
		this.next = next;
		this.length = length;
	}
	@Override
	public int compareTo(Edge o) {
		return this.length - o.length;
	}
}

public class Main_14618_총깡총깡 {
	static int N, M, J, K, INF = 987654321;
	static boolean[] visited;
	static int[] house, dist;
	static List<Edge>[] graph;
	static PriorityQueue<Edge> pq;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		J = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		visited = new boolean[N+1];
		house = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<K; i++) {
			int n = Integer.parseInt(st.nextToken());
			house[n] = 1;
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<K; i++) {
			int n = Integer.parseInt(st.nextToken());
			house[n] = 2;
		}
		
		
		dist = new int[N+1];
		Arrays.fill(dist, INF);
		graph = new ArrayList[N+1];
		for (int i=1; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());

			graph[s].add(new Edge(e, l));
			graph[e].add(new Edge(s, l));
		}
		
		pq = new PriorityQueue<>();
		dist[J] = 0;
		pq.add(new Edge(J, 0));
		
		while(!pq.isEmpty()) {
			int now = pq.poll().next;
			if(visited[now]) continue;
			visited[now] = true;
			for(Edge e : graph[now]) {
				int next = e.next;
				if(dist[next] > dist[now]+e.length) {
					dist[next] = dist[now]+e.length;
					pq.add(new Edge(next, dist[next]));
				}
			}
		}
		
		int minA = INF;
		int minB = INF;
		for (int i=1; i<=N; i++) {
			if(house[i]==1) {
				minA = Math.min(minA, dist[i]);
			}
			else if(house[i]==2) {
				minB = Math.min(minB, dist[i]);
			}
		}
		
		
		if(minA==INF && minB==INF) {
			System.out.println(-1);
		} else if(minA > minB) {
			System.out.println("B");
			System.out.println(minB);
		} else {
			System.out.println("A");
			System.out.println(minA);
		}
	}
}
