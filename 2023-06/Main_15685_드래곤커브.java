import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_15685_드래곤커브 {
	static int N, MAX = 100;
	static int[] dx = {1,0,-1,0}, dy = {0, -1, 0, 1};
	static boolean[][] onDragonCurve;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		onDragonCurve = new boolean[MAX+1][MAX+1];
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			drawDragonCurve(x, y, d, g);
		}
		
		int answer = countSquareOnDragonCurve();
		System.out.println(answer);
	}
	
	
	static void drawDragonCurve(int x, int y, int d, int g) {
		List<Integer> dirList = new ArrayList<>();
		onDragonCurve[x][y] = true;
		int nx = x+dx[d], ny = y+dy[d];
		onDragonCurve[nx][ny] = true;

		dirList.add(d);
		x = nx;
		y = ny;

		while(g>0) {
			int size = dirList.size();
			for (int i=size-1; i>=0; i--) {
				int dir = dirList.get(i);
				int nextDir = (dir+1)%4;
				nx = x+dx[nextDir];
				ny = y+dy[nextDir];
				onDragonCurve[nx][ny] = true;
				dirList.add(nextDir);
				x = nx;
				y = ny;
			}
			g--;
		}
	}
	
	static int countSquareOnDragonCurve() {
		int result = 0;
		for (int i=0; i<MAX; i++) {
			for (int j=0; j<MAX; j++) {
				if(isSquareOnDragonCurve(i, j)) result++;
			}
		}
		return result;
	}
	
	static boolean isSquareOnDragonCurve(int x, int y) {
		return onDragonCurve[x][y] && onDragonCurve[x+1][y] && onDragonCurve[x][y+1] && onDragonCurve[x+1][y+1];
	}
}
