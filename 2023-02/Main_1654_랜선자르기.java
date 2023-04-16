import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1654_랜선자르기 {
	static int K, N;
	static long maxWire = 0;
	static int[] wire;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		wire = new int[K];
		for (int i=0; i<K; i++) {
			wire[i] = Integer.parseInt(br.readLine());
			maxWire = Math.max(maxWire, wire[i]);
		}
		
		long start = 0, end = maxWire+1;
		long mid = 0;
		while(start<end) {
			mid = (start+end)/2;
			long sum = 0;
			for (int i=0; i<K; i++) {
				sum += (wire[i]/mid);
			}
			System.out.println(start + ", " + mid + ", " + end +", " + sum);
			if(sum>=N) {
				start = mid+1;
			} else {
				end = mid;
			}
		}
		
		System.out.println(start-1);
	}
}
