import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14499_주사위굴리기 {
	static StringBuilder sb;
	static int N, M, x, y, K;
	static int[] dice, dx = {0,0,0,-1,1}, dy = {0,1,-1,0,0};
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		dice = new int[7];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		
		for (int i=0; i<K; i++) {
			int cmd = Integer.parseInt(st.nextToken());
			rollingDice(cmd);
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	
	static void rollingDice(int dir) {
		int nx = x+dx[dir];
		int ny = y+dy[dir];
		if(nx<0 || ny<0 || nx>=N || ny>=M) return;
		x = nx;
		y = ny;
		
		int[] before = dice.clone();
		if(dir==1) {
			dice[6] = before[3];
			dice[1] = before[4];
			dice[3] = before[1];
			dice[4] = before[6];
		} else if (dir==2) {
			dice[6] = before[4];
			dice[1] = before[3];
			dice[3] = before[6];
			dice[4] = before[1];
		} else if (dir==3) {
			dice[6] = before[2];
			dice[1] = before[5];
			dice[2] = before[1];
			dice[5] = before[6];
		} else {
			dice[6] = before[5];
			dice[1] = before[2];
			dice[2] = before[6];
			dice[5] = before[1];
		}
		
		if(map[x][y]==0) {
			map[x][y] = dice[6];
		} else {
			dice[6] = map[x][y];
			map[x][y] = 0;
		}
		
		sb.append(dice[1]);
		sb.append('\n');
	}
}
