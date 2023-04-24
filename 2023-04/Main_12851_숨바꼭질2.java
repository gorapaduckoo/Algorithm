import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
	int idx;
	int time;
	public Point(int idx, int time) {
		this.idx = idx;
		this.time = time;
	}
}

public class Main_12851_숨바꼭질2 {
	static int N, K, MAX = 100000;
	static Queue<Point> q;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		q = new ArrayDeque<>();
		visited = new boolean[MAX+1];
		
		q.add(new Point(N, 0));
		int ans = Integer.MAX_VALUE;
		int cnt = 0;
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			visited[now.idx] = true;
			if(now.idx == K) {
				if(ans>=now.time) {
					ans = now.time;
					cnt++;
				}
			}
			int next = now.idx*2;
			int time = now.time+1;
			if(next<=MAX && !visited[next]) {
				q.add(new Point(next, now.time+1));
			}
			next = now.idx+1;
			if(next<=MAX && !visited[next]) {
				q.add(new Point(next, now.time+1));
			}
			next = now.idx-1;
			if(next>=0 && !visited[next]) {
				q.add(new Point(next, now.time+1));
			}
			
		}
		
		System.out.println(ans);
		System.out.println(cnt);
	}
	
}
