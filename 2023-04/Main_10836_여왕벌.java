import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_10836_여왕벌 {
	static int N, M;
	static int[] growth, edge;
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		arr = new int[M][M];
		growth = new int[3];
		edge = new int[2*M-1];
		
		
		while(N>0) {
			st = new StringTokenizer(br.readLine());
			int idx = 0;
			for (int i=0; i<3; i++) {
				growth[i] = Integer.parseInt(st.nextToken());
				while(growth[i] > 0) {
					edge[idx] += i;
					idx++;
					growth[i]--;
				}
			}
			N--;
		}
		
		int x=M-1, y=0;
		for (int i=0; i<2*M-1; i++) {
			arr[x][y] += (edge[i]+1);
			if(x>0) x--;
			else y++;
		}
		
		for (int i=0; i<M; i++) {
			for (int j=0; j<M; j++) {
				if(i>0 && j>0) {
					bw.write(arr[0][j] + " ");
				} else {
					bw.write(arr[i][j] + " ");
				}
			}
			bw.write('\n');
		}
		bw.flush();
	}
}
