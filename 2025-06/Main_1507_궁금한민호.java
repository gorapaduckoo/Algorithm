import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1507_궁금한민호 {
	static int N;
	static int[][] dist, road;
	static boolean[][] isReachable;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		dist = new int[N][N];
		road = new int[N][N];
		
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				dist[i][j] = Integer.parseInt(st.nextToken());
				road[i][j] = dist[i][j];
			}
		}
		
		for (int k=0; k<N; k++) {
			for(int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if (k == i || k == j || i == j) continue;
					if (dist[i][j] > dist[i][k] + dist[k][j]) {
						System.out.println("-1");
						return;
					}
					
					if (dist[i][j] == dist[i][k] + dist[k][j]) {
						road[i][j] = 0;
					}
				}
			}
		}
		
		int answer = 0;
		for (int i=0; i<N; i++) {
			for (int j=i; j<N; j++) {
				answer += road[i][j];
			}
		}
		
		System.out.println(answer);
	}
}
