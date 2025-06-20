import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Point implements Comparable<Point> {
	int idx;
	int dist;
	int washTime;
	
	public Point(int idx, int dist, int washTime) {
		this.idx = idx;
		this.dist = dist;
		this.washTime = washTime;
	}
	
	public int compareTo(Point o) {
		if (this.dist == o.dist) {
			return this.washTime - o.washTime;
		}
		return this.dist - o.dist;
	}
}

public class Main_16227_의약품수송 {
	static int N, K, THRESHOLD = 100, MAX = 987654321;
	static int[][] dist;
	static List<Point>[] graph;
	static PriorityQueue<Point> pq = new PriorityQueue<Point>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		dist = new int[N+2][THRESHOLD+1];
		for (int i=0; i<N+2; i++) {
			Arrays.fill(dist[i], MAX);
		}
		
		graph = new List[N+2];
		for (int i=0; i<N+2; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			if (dist > THRESHOLD) continue;
			graph[a].add(new Point(b, dist, 0));
		}
		
		pq.add(new Point(0, 0, 0));
		dist[0][0] = 0;
		
		while(!pq.isEmpty()) {
			Point now = pq.poll();
			
			for (Point next : graph[now.idx]) {
				int distSum = now.dist + next.dist;
				int washTimeSum = now.washTime + next.dist;
				
				// 모래 안씻는 경우
				if (washTimeSum <= THRESHOLD && dist[next.idx][washTimeSum] > distSum) {
					pq.add(new Point(next.idx, distSum, washTimeSum));
					dist[next.idx][washTimeSum] = distSum;
				}
				
				// 모래 씻는 경우
				distSum += 5;
				washTimeSum = next.dist;
				
				if (dist[next.idx][washTimeSum] <= distSum) continue;
				pq.add(new Point(next.idx, distSum, washTimeSum));
				dist[next.idx][washTimeSum] = distSum;
			}
		}
		
		int answer = MAX;
		for (int i=0; i<=THRESHOLD; i++) {
			answer = Math.min(answer, dist[N+1][i]);
		}
		System.out.println(answer);
	}
}
