import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Shortcut{
	int start;
	int end;
	int dist;
	public Shortcut(int start, int end, int dist) {
		this.start = start;
		this.end = end;
		this.dist = dist;
	}
	
}
public class Main_1446_지름길 {
	static int N, D;
	static int[] dp;
	static Shortcut[] shortcut;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		dp = new int[D+1];
		shortcut = new Shortcut[N];
		for (int i=0; i<=D; i++) {
			dp[i]=i;
		}
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			shortcut[i] = new Shortcut(start, end, dist);
		}
		
		
		for(int i=0; i<=D; i++) {
			if(i==0) continue;
			dp[i] = Math.min(dp[i-1]+1, dp[i]);
			for(Shortcut s : shortcut) {
				int start = s.start;
				int end = s.end;
				int dist = s.dist;
				if(i==end) {
					dp[i] = Math.min(dp[i], dp[start]+dist);
				}
			}
			
		}
		
		System.out.println(dp[D]);
	}
}
