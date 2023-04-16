import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1806_부분합 {
	static int N, S, ans;
	static int MAX = Integer.MAX_VALUE;
	static int[] arr, sum;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		ans = MAX;
		arr = new int[N+1];
		sum = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum[i] = sum[i-1] + arr[i];
		}
		
		int l=0, r=0;
		while(l<=N && r<=N) {
			if(sum[r]-sum[l] < S) {
				r++;
			} else {
				ans = Math.min(r-l, ans);
				l++;
			}
			
		}
		
		if(ans==MAX) {
			System.out.println(0);
		} else {
			System.out.println(ans);
		}
		
	}
}
