import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17182_우주탐사선 {
	static int N, K, answer = Integer.MAX_VALUE;
	static int[][] dist;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		dist = new int[N][N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				dist[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int k=0; k<N; k++) {
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if(dist[i][j] > dist[i][k] + dist[k][j]) {
						dist[i][j] = dist[i][k]+dist[k][j];
					}
				}
			}
		}
		
		visited = new boolean[N];
		visited[K] = true;
		visit(K, 0, 1);
		
		System.out.println(answer);
		
	}
	
	static void visit(int now, int time, int cnt) {
		if(cnt==N) {
			answer = Math.min(time, answer);
			return;
		}
		if(time>answer) return;
		
		for (int next=0; next<N; next++) {
			if(visited[next]) continue;
			visited[next] = true;
			visit(next, time+dist[now][next], cnt+1);
			visited[next] = false;
		}
	}
}
