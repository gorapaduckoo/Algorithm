import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Point implements Comparable<Point> {
	int x;
	int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public int compareTo(Point o) {
		if(this.x == o.x) return this.y - o.y;
		return this.x - o.x;
	}
}
public class Main_14658_하늘에서별똥별이빗발친다 {
	static int N, M, L, K;
	static Point[] star; 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		star = new Point[K];
		
		for (int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			star[i] = new Point(x,y);
		}
		
		int maxDefense = 0;
		for(Point p1 : star) {
			for (Point p2 : star) {
				int x = p1.x;
				int y = p2.y;
				maxDefense = Math.max(maxDefense, locate(x,y));
			}
		}
		System.out.println(K - maxDefense);
	}

	static int locate (int x, int y) {
		int cnt = 0;
		for (Point p : star) {
			int w = p.x-x;
			int h = p.y-y;
			if(0<=w && w<=L && 0<=h && h<=L) cnt++;
		}
		return cnt;
	}
}
