import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Point {
	int x;
	int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main_1513_경로찾기 {
	static int N, M, C;
	static int[][] arcade;
	static int[][][][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arcade = new int[N+1][M+1];
		dp = new int[N+1][M+1][C+1][C+1];
		
		
		dp[1][1][0][0] = 1;
		for (int i=1; i<=C; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			arcade[x][y] = i;
			if(x==1 && y==1) {
				dp[1][1][0][0] = 0;
				dp[1][1][i][1] = 1;
			}
		}
		// dp[i][j][k][l]: 현재 위치(i,j) 이고 l개의 오락실 방문. 방문한 오락실 중 최대 번호는 k
		for (int i=1; i<=N; i++) {
			for (int j=1; j<=M; j++) {
				if(arcade[i][j] > 0) {
					// arcade[i][j]: 현재 방문할 오락실 번호
					// k: 방문한 오락실 중 최대 오락실 번호
					// l 방문한 오락실 개
					if(i==1 && j==1) continue;
					for (int k=0; k<arcade[i][j]; k++) {
						for(int l=0; l<=k; l++) {
							dp[i][j][arcade[i][j]][l+1] += (dp[i-1][j][k][l]+dp[i][j-1][k][l]);
						}
					}
				} else {
					for (int k=0; k<=C; k++) {
						for (int l=0; l<=k; l++) {
							dp[i][j][k][l] += (dp[i-1][j][k][l]+dp[i][j-1][k][l]);
						}
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<=C; i++) {
			int sum = 0;
			for (int j=0; j<=C; j++) {
				sum += dp[N][M][j][i];
			}
			sb.append(sum + " ");
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
}
