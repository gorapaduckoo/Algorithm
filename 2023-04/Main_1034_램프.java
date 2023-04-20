import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1034_램프 {
	static int N, M, K;
	static boolean[] visited;
	static int[] countZero;
	static String[] lamp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		visited = new boolean[N];
		countZero = new int[N];
		lamp = new String[N];
		for (int i=0; i<N; i++) {
			lamp[i] = br.readLine();
			for (int j=0; j<M; j++) {
				if(lamp[i].charAt(j)=='0') countZero[i]++;
			}
		}
		
		K = Integer.parseInt(br.readLine());
		
		int ans = 0;
		for (int i=0; i<N; i++) {
			if(visited[i] || countZero[i]>K) continue;
			if(countZero[i]%2 != K%2) continue;
			
			int cnt = 0;
			for (int j=i; j<N; j++) {
				if(lamp[i].equals(lamp[j])) {
					visited[j] = true;
					cnt++;
				}
			}
			ans = Math.max(ans, cnt);
			
		}
		System.out.println(ans);
	}
}
