import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Point implements Comparable<Point>{
	int idx;
	long dist;
	int cnt;
	
	public Point(int idx, long dist, int cnt) {
		this.idx = idx;
		this.dist = dist;
		this.cnt = cnt;
	}
	
	public int compareTo(Point o) {
		if (this.dist < o.dist) {
			return -1;
		}
		
		if (this.dist > o.dist) {
			return 1;
		}
		
		return 0;
	}
}

public class Main_16475_수학미로 {
	static int N, M, K, L, P, S, E;
	static boolean[] isTrap;
	static long[][][] dist;
	
	static List<Point>[] graph, reverseGraph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		isTrap = new boolean[N+1];
		dist = new long[N+1][2][P];
		graph = new ArrayList[N+1];
		reverseGraph = new ArrayList[N+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<K; i++) {
			int idx = Integer.parseInt(st.nextToken());
			isTrap[idx] = true;
		}
		
		for (int i=1; i<=N; i++) {
			graph[i] = new ArrayList<>();
			reverseGraph[i] = new ArrayList<>();
			for (int j=0; j<2; j++) {
				Arrays.fill(dist[i][j], Integer.MAX_VALUE);
			}
		}
		
		for (int i=0; i<M-L; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			graph[a].add(new Point(b, dist, 0));
			reverseGraph[a].add(new Point(b, dist, 0));
		}
		for (int i=0; i<L; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			graph[a].add(new Point(b, dist, 0));
			reverseGraph[b].add(new Point(a, dist, 0));
		}
		
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Point> q = new PriorityQueue<Point>();
		q.add(new Point(S, 0, 0));
		
		long answer = Long.MAX_VALUE;
		while(!q.isEmpty()) {
			Point now = q.poll();
			if (now.idx == E) {
				answer = Math.min(answer, now.dist);
				break;
			}
			
			if ((now.cnt/P)%2 == 0) {
				for (Point next : graph[now.idx]) {
					int nextCnt = now.cnt;
					if (isTrap[next.idx]) {
						nextCnt++;
					}
					
					if (dist[next.idx][0][nextCnt % P] <= now.dist + next.dist) continue;
					dist[next.idx][0][nextCnt % P] = now.dist + next.dist;
					q.add(new Point(next.idx, now.dist + next.dist, nextCnt));
				}
			} else {
				for (Point next : reverseGraph[now.idx]) {
					int nextCnt = now.cnt;
					if (isTrap[next.idx]) {
						nextCnt++;
					}
					
					if (dist[next.idx][1][nextCnt % P] <= now.dist + next.dist) continue;
					dist[next.idx][1][nextCnt % P] = now.dist + next.dist;
					q.add(new Point(next.idx, now.dist + next.dist, nextCnt));
				}
			}
		}

		if (answer == Long.MAX_VALUE) {
			answer = -1;
		}
		
		System.out.println(answer);
	}
}
