import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_23845_마트료시카 {
	static int N, MAX = 2*100_000 + 1;
	static int[] dolls;
	static long[] dollsCnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		dolls = new int[N];
		dollsCnt = new long[MAX];
		for (int i=0; i<N; i++) {
			dolls[i] = Integer.parseInt(st.nextToken());
			dollsCnt[dolls[i]]++;
		}
		
		Arrays.sort(dolls);
		
		if (dolls.length == 1) {
			System.out.println(dolls[0]);
			return;
		}
		
		
		
		long answer = 0;
		for (int i=1; i<=dolls[N-1]; i++) {
			while(dollsCnt[i] > 0) {
				long cnt = 0;
				long size = i;
				
				for (int j=i; j<=dolls[N-1]; j++) {
					if (dollsCnt[j] == 0) break;
					cnt++;
					size = j;
					dollsCnt[j]--;
				}
				
				answer += (size * cnt);
			}
		}
		
		System.out.println(answer);
	}
}
