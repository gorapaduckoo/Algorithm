import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Ball implements Comparable<Ball>{
	int idx;
	int color;
	int size;
	public Ball(int idx, int color, int size) {
		this.idx = idx;
		this.color = color;
		this.size = size;
	}
	@Override
	public int compareTo(Ball o) {
		if(this.size==o.size) return this.color - o.color;
		return this.size - o.size;
	}
	
}
public class Main_10800_컬러볼 {
	static int N;
	static int[] ans;
	static Ball[] balls;
	static Map<Integer, Integer> map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		ans = new int[N+1];
		balls = new Ball[N];
		map = new HashMap<>();
		
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int color = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			balls[i] = new Ball(i+1, color, size);
			map.put(color, 0);
		}
		Arrays.sort(balls);
		
		// sum = 4
		// tmp = 4
		// (1, 4) (1, 4) (2, 4)
		
		
		
		int sum = 0;
		int tmpSum = 0;
		for (int i=0; i<N; i++) {
			int idx = balls[i].idx;
			int color = balls[i].color;
			int size = balls[i].size;
			
			if(i>0 && size>balls[i-1].size) tmpSum = 0;
			
			if(i>0 && size==balls[i-1].size && color==balls[i-1].color) {
				int prevIdx = balls[i-1].idx;
				ans[idx]=ans[prevIdx];
			} else {
				ans[idx] = sum-tmpSum-map.get(color);
			}
			sum += size;
			tmpSum += size;
			map.put(color, map.get(color)+size);
		}
		
		for (int i=1; i<=N; i++) {
			bw.write(ans[i]+"\n");
		}
		bw.flush();
	}
}
