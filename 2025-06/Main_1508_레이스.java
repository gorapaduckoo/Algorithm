import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1508_레이스 {
	static int N, M, K;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[K];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<K; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("1");
		
		int dist = findDist();
		int prevJudge = arr[0];
		int cnt = 1;
		
		for (int i=1; i<K; i++) {
			if (cnt < M && arr[i] - prevJudge >= dist) {
				sb.append("1");
				prevJudge = arr[i];
				cnt++;
			} else {
				sb.append("0");
			}
		}
		
		System.out.println(sb.toString());
	
	}
	
	static int findDist() {
		int start = 0;
		int end = N;
		
		while (start <= end) {
			int mid = (start + end) / 2;
			
			if (canArrange(mid)) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		
		return end;
	}
	
	static boolean canArrange(int minDist) {
		int cnt = 1;
		int prevJudge = arr[0];
		
		for (int i=1; i<K; i++) {
			if (arr[i] - prevJudge >= minDist) {
				cnt++;
				prevJudge = arr[i];
			}
		}
		
		if (cnt >= M) {
			return true;
		}
		return false;
	}
}
