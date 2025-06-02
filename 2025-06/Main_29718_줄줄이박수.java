import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_29718_줄줄이박수 {
	static int N, M, K;
	static long[] sum;
	static int[][] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		sum = new long[M];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i=0; i<M; i++) {
			int result = 0;
			for (int j=0; j<N; j++) {
				result += arr[j][i];
			}
			if (i>0) {
				sum[i] = sum[i-1];
			}
			sum[i] += result;
		}
		
		K = Integer.parseInt(br.readLine());
		
		long answer = sum[K-1];
		for (int i=1; i<M-K+1; i++) { 
			answer = Math.max(answer, sum[i+K-1]-sum[i-1]);
		}
		
		System.out.println(answer);
	}
}
