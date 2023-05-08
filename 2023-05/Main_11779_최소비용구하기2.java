import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;


class Edge implements Comparable<Edge>{
	int idx;
	int cost;
	public Edge(int idx, int cost) {
		this.idx = idx;
		this.cost = cost;
	}
	public int compareTo(Edge o) {
		return this.cost - o.cost;
	}
}

public class Main_11779_최소비용구하기2 {
	static int N, M, INF = 100000001;
	static int[] cost;
	static List<Edge>[] graph;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		cost = new int[N+1];
		graph = new ArrayList[N+1];
		for (int i=1; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph[s].add(new Edge(e, cost));
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		Stack<Integer> route = dijstra(start, end);
		
		System.out.println(cost[end]);
		System.out.println(route.size());
		StringBuilder sb = new StringBuilder();
		while(!route.isEmpty()) {
			sb.append(route.pop() + " ");
		}
		System.out.println(sb.toString());
		
	}
	
	static Stack<Integer> dijstra(int start, int end) {
		int[] prev = new int[N+1];
		Arrays.fill(cost, INF);
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		cost[start] = 0;
		pq.add(new Edge(start, 0));
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			int now = edge.idx;
			if(now==end) break;
			for(Edge nextEdge : graph[now]) {
				int next = nextEdge.idx;
				int newCost = edge.cost + nextEdge.cost;
				if(cost[next] <= newCost) continue;
				cost[next] = newCost;
				prev[next] = now;
				pq.add(new Edge(next, newCost));
			}
		}
		
		Stack<Integer> result = trace(prev, start, end);
		
		return result;
	}
	
	static Stack<Integer> trace(int[] prev, int start, int end) {
		Stack<Integer> st = new Stack<>();
		int now = end;
		while(now != start) {
			st.add(now);
			now = prev[now];
		}
		st.add(start);
		
		return st;
	}
}
