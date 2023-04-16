import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Point implements Comparable<Point>{
	int idx;
	int dist;
	public Point(int idx, int dist) {
		this.idx = idx;
		this.dist = dist;
	}
	@Override
	public int compareTo(Point o) {
		// TODO Auto-generated method stub
		return this.dist - o.dist;
	}
}

public class Main_1504_특정한최단경로 {
	static int N, E, INF = 987654321;
	static int V1, V2;
	static List<Point>[] graph;
	static Queue<Point> q;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N+1];
		for (int i=1; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i=0; i<E; i++ ) {
			st = new StringTokenizer(br.readLine());
			int p1 = Integer.parseInt(st.nextToken());
			int p2 = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			graph[p1].add(new Point(p2, dist));
			graph[p2].add(new Point(p1, dist));
		}
		
		st = new StringTokenizer(br.readLine());
		V1 = Integer.parseInt(st.nextToken());
		V2 = Integer.parseInt(st.nextToken());
		
		//　1 - V1 - V2 - N
		// 1 - V2 - V1 - N
		
		long d1 = dijkstra(1, V1);
		long d2 = dijkstra(1, V2);
		long d3 = dijkstra(V1, V2);
		long d4 = dijkstra(V1, N);
		long d5 = dijkstra(V2, N);
		
		long sum1 = d1+d3+d5;
		long sum2 = d2+d3+d4;
		
		if(sum1<0 && sum2<0) {
			System.out.println(-1);
		} else if (sum1<0) {
			System.out.println(sum2);
		} else if (sum2<0) {
			System.out.println(sum1);
		} else {
			System.out.println(Math.min(sum1, sum2));
		}
		
		
	}
	
	static long dijkstra(int S, int E) {
		q = new PriorityQueue<>();
		long[] dist = new long[N+1];
		Arrays.fill(dist, INF);
		
		dist[S] = 0;
		q.add(new Point(S, 0));
		while(!q.isEmpty()) {
			Point now = q.poll();
			for (Point next : graph[now.idx]) {
				int nextDist = now.dist + next.dist;
				if(nextDist < dist[next.idx]) {
					dist[next.idx] = nextDist;
					q.add(new Point(next.idx, nextDist));
				}
			}
		}
		
		if(dist[E] >= INF) return -INF;
		return dist[E];
	}
}
