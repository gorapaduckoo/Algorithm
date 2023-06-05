import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_17779_게리맨더링2 {
	static int N, answer = Integer.MAX_VALUE;
	static int[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		for (int i=1; i<N; i++) {
			for (int j=1; j<N; j++) {
				for (int x=0; x+i+j<N; x++) {
					for (int y=i; y+j<N; y++) {
						divideArea(x, y, i, j);
					}
				}
			}
		}
		
		System.out.println(answer);
	}
	
	static void divideArea(int x, int y, int d1, int d2) {
		if(x+d1+d2>=N || y-d1<0 || y+d2>=N) return;
		
		int[][] areaNum = new int[N][N];
		int k=0;
		for (int i=0; i<x+d1; i++) {
			if(i>=x) k++;
			for (int j=0; j<=y-k; j++) {
				areaNum[i][j] = 1;
			}
		}
		
		k=0;
		for (int i=0; i<=x+d2; i++) {
			for (int j=y+k+1; j<N; j++) {
				areaNum[i][j] = 2;
			}
			if(i>=x) k++;
		}
		
		k=0;
		for (int i=x+d1; i<N; i++) {
			for (int j=0; j<y-d1+k; j++) {
				areaNum[i][j] = 3;
			}
			if(i<x+d1+d2) k++;
		}
		
		k=0;
		for (int i=x+d2+1; i<N; i++) {
			for (int j=N-1; j>=y+d2-k; j--) {
				areaNum[i][j] = 4;
			}
			if(i<=x+d1+d2) k++;
		}
		
		int[] result = getAreaPopulation(areaNum);
		Arrays.sort(result);
		answer = Math.min(answer, result[4]-result[0]);
	}
	
	static int[] getAreaPopulation(int[][] areaNum) {
		int[] result = new int[5];
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				int n = areaNum[i][j];
				result[n] += map[i][j];
			}
		}
	
		return result;
	}
	
}
