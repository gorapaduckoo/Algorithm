import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_12788_제2회IUPC는잘개최될수있을까 {
	static int N, M, K;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		Integer[] pen = new Integer[N];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			pen[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(pen, Collections.reverseOrder());
		
		int sum = 0;
		int idx = 0;
		while(idx<N && sum<M*K) {
			sum += pen[idx];
			idx++;
		}
		if(sum<M*K) {
			System.out.println("STRESS");
		} else {
			System.out.println(idx);
		}
	}
}
