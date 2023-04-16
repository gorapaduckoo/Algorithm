import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Wire implements Comparable<Wire> {
	int s, e;
	public Wire(int s, int e) {
		this.s=s;
		this.e=e;
	}
	@Override
	public int compareTo(Wire o) {
		return this.s - o.s;
	}
}
public class Main_2565_전깃줄 {
	static int N, ans;
	static int[] dp;
	static Wire[] wires;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N];
		wires = new Wire[N];
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			wires[i] = new Wire(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(wires);
		Arrays.fill(dp, 1);
		for (int i=1; i<N; i++) {
			int tmp = 0;
			for (int j=0; j<i; j++) {
				if(wires[j].e<wires[i].e) {
					tmp = Math.max(tmp, dp[j]);
				}
			}
			dp[i] = tmp+1;
		}
		
		ans=0;
		for (int i=0; i<N; i++) {
			ans = Math.max(ans, dp[i]);
		}
		System.out.println(N - ans);
		
	}
	

}
