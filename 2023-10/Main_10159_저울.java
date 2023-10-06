import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_10159_저울 {
	static int N, M;
	static boolean[][] graph;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		graph = new boolean[N+1][N+1];
		for (int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a][b] = true;
		}
		
		for(int k=1; k<=N; k++) {
			for (int i=1; i<=N; i++) {
				for (int j=1; j<=N; j++) {
					if(graph[i][k] && graph[k][j]) graph[i][j] = true;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i=1; i<=N; i++) {
			int cnt = 0;
			for (int j=1; j<=N; j++) {
				if(i==j) continue;
				if(!graph[i][j] && !graph[j][i]) cnt++;
			}
			sb.append(cnt +"\n");
		}
		
		System.out.println(sb.toString());
		
	}
}
