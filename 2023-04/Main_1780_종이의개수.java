import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1780_종이의개수 {
	static int N;
	static int[] ans;
	static int[][] paper;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		paper = new int[N][N];
		ans = new int[3];
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cutting(0,0,N);
		
		for (int i=0; i<3; i++) {
			System.out.println(ans[i]);
		}
	}
	static void cutting(int x, int y, int n) {
		boolean flag = true;
		int num = paper[x][y];
		for (int i=x; i<x+n; i++) {
			for (int j=y; j<y+n; j++) {
				if(paper[i][j]!=num) {
					flag = false;
					break;
				}
			}
		}
		
		if(flag) {
			ans[num+1]++;
			return;
		}
		
		for (int nx=x; nx<x+n; nx+=(n/3)) {
			for (int ny=y; ny<y+n; ny+=(n/3)) {
				cutting(nx, ny, n/3);
			}
		}
		
	}
}
