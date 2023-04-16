import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1010_다리놓기 {
	static int T, N, M;
	static long[][] comb;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		comb = new long[31][31];
		for (int i=1; i<=30; i++) {
			comb[i][1] = i;
		}
		
		for (int i=2; i<=30; i++) {
			for (int j=2; j<=30; j++) {
				comb[i][j] = comb[i-1][j-1]+comb[i-1][j];
			}
		}
		StringBuilder sb = new StringBuilder();
		while(T>0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			sb.append(comb[N][M]);
			sb.append('\n');
			T--;
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	
	
}
