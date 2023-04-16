import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17610_양팔저울 {
	static int N, M=0, ans = 0;
	static int[] weight;
	static boolean[] isPossible;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		weight = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			weight[i] = Integer.parseInt(st.nextToken());
			M+=weight[i];
		}
		isPossible = new boolean[M+1];
		pick(0,0,0);
		
		for (int i=1; i<=M; i++) {
			if(!isPossible[i]) ans++;
		}
		System.out.println(ans);
		
	}
	
	static void pick(int now, int left, int right) {
		isPossible[left] = true;
		isPossible[right] = true;
		isPossible[Math.abs(right-left)] = true;

		if(now==N) return;		
		pick(now+1, left+weight[now], right);
		pick(now+1, left, right+weight[now]);
		pick(now+1, left, right);
		
	}
}
