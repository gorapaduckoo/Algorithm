import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Point{
	int x;
	int y;
	int num;
	public Point(int x, int y, int num) {
		this.x=x;
		this.y=y;
		this.num = num;
	}

}

public class Main_18405_경쟁적전염 {
	static int N, K, S, X, Y;
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static int[][] arr;
	static Queue<Point> q;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		q = new ArrayDeque<>();
		
		List<Point> initList = new ArrayList<>();
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j]!=0) {
					initList.add(new Point(i, j, arr[i][j]));
				}
			}
		}
		
		Collections.sort(initList, (p1, p2)-> {
			return p1.num - p2.num;
		});
		for (int i=0; i<initList.size(); i++) {
			q.add(initList.get(i));
		}
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken())-1;
		Y = Integer.parseInt(st.nextToken())-1;
		
		int time=0;
		while(time<S) {
			int size = q.size();
			while(size > 0) {
				Point now = q.poll();
				for (int i=0; i<4; i++) {
					int nx = now.x+dx[i];
					int ny = now.y+dy[i];
					if(nx<0 || ny<0 || nx>=N || ny>=N || arr[nx][ny]!=0) continue;
					arr[nx][ny] = now.num;
					q.add(new Point(nx, ny, now.num));
				}
				size--;
			}
			time++;
		}
		
		System.out.println(arr[X][Y]);
	}
}
