import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1051_숫자정사각형 {
	static int N, M;
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for (int i=0; i<N; i++) {
			String str = br.readLine();
			for (int j=0; j<M; j++) {
				arr[i][j] = str.charAt(j)-'0';
			}
		}
		
		int ans = 0;
		
		int n = Math.min(N, M);
		boolean flag = false;
		for (int k=n-1; k>=0; k--) {
			if(flag) break;
			for (int i=0; i+k<N; i++) {
				if(flag) break;
				for (int j=0; j+k<M; j++) {
					int p1 = arr[i][j];
					int p2 = arr[i][j+k];
					int p3 = arr[i+k][j];
					int p4 = arr[i+k][j+k];
					if((p1==p2) && (p2==p3) && (p3==p4)) {
						ans = (k+1)*(k+1);
						flag = true;
						break;
					}
				}
			}
		}
		
		System.out.println(ans);
	}
}
