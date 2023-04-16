import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2381_최대거리 {
	static int N;
	static int maxDiff = Integer.MIN_VALUE, maxSum = Integer.MIN_VALUE;
	static int minDiff = Integer.MAX_VALUE, minSum = Integer.MAX_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int diff = x-y;
			int sum = x+y;
			
			maxDiff = Math.max(maxDiff, diff);
			minDiff = Math.min(minDiff, diff);
			maxSum = Math.max(maxSum, sum);
			minSum = Math.min(minSum, sum);
		}

		int diff = maxDiff - minDiff;
		int sum = maxSum - minSum;
		System.out.println(Math.max(diff, sum));
	}
}
