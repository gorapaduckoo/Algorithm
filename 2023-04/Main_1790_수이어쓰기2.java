import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1790_수이어쓰기2 {
	static int N, K;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int n = 1;
		int k = 0;
		while(k<K && n<=N) {
			k+=(int)(Math.log10(n)+1);
			n++;
		}
		if(k>=K) {
			StringBuilder sb = new StringBuilder(Integer.toString(n-1));
			System.out.println(sb.charAt(k-K));
			
		} else {
			System.out.println(-1);
		}
	}
}