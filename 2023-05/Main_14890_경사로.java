import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14890_경사로 {
	static int N, L;
	static boolean[] newSlope;
	static boolean[][] slope;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		slope = new boolean[N][N];
		map = new int[N][N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int row = 0, col = 0;
		
		// 행 검사
		for (int i=0; i<N; i++) {
			boolean flag = true;
			int height = map[i][0];
			newSlope = new boolean[N];
			
			for (int j=0; j<N-1; j++) {
				if(!flag) break;
				if(height==map[i][j+1]) continue;
				
				if(height==map[i][j+1]-1) {
					flag = checkRow(i,j-L+1, map[i][j]);
					height++;
				} else if (map[i][j]==map[i][j+1]+1) {
					flag = checkRow(i,j+1, map[i][j+1]);
					height--;
					j+=(L-1);
				} else flag = false;
			}
			if(flag) {
				for (int j=0; j<N; j++) {
					slope[i][j] = newSlope[j];
				}
				row++;
			}
		}
		
		
		for (int j=0; j<N; j++) {
			boolean flag = true;
			int height = map[0][j];
			newSlope = new boolean[N];
			
			for (int i=0; i<N-1; i++) {
				if(!flag) break;
				if(height==map[i+1][j]) continue;
				
				if(height==map[i+1][j]-1) {
					flag = checkCol(i-L+1, j, map[i][j]);
					height++;
				} else if (height==map[i+1][j]+1) {
					flag = checkCol(i+1, j, map[i+1][j]);
					height--;
					i+=(L-1);
				} else flag = false;
			}
			
			if(flag) {
				for (int i=0; i<N; i++) {
					slope[i][j] = newSlope[i];
				}
				col++;
			}
		}
		
		System.out.println(row+col);
	}
	
	static boolean checkRow(int i, int startY, int height) {
		if(startY<0 || startY+L>N) return false;
		for (int j=startY; j<startY+L; j++) {
			if(map[i][j]!=height || newSlope[j]) return false;
		}
		
		for (int j=startY; j<startY+L; j++) {
			newSlope[j] = true;
		}
		return true;
	}
	
	static boolean checkCol(int startX, int j, int height) {
		if(startX<0 || startX+L>N) return false;
		for (int i=startX; i<startX+L; i++) {
			if(map[i][j]!=height || newSlope[i]) return false;
		}
		
		for (int i=startX; i<startX+L; i++) {
			newSlope[i] = true;
		}
		return true;
	}
}
