import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1300_K번째수 {
	static int N, K;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		int right = K;
		int left = 1;
		
		while(left < right) {
			int mid = (left+right)/2;
			int sum = 0;
			for (int i=1; i<=N; i++) {
				int tmp = mid/i;
				if(tmp>N) {
					sum += N;
				} else {
					sum += tmp;
				}
			}
			if(sum>=K) {
				right = mid;
			} else {
				left = mid+1;
			}
		}
		
		System.out.println(left);
	}
}
