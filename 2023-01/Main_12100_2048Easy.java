import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_12100_2048Easy {
	static int N, M=5, ans = 0, MAX = 2048;
	static int[][] board;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		int max = 0;
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				max = Math.max(board[i][j], max);
			}
		}
		
		for (int i=0; i<4; i++) {
			move(i,0, max, board);
		}
		System.out.println(ans);
	}
	
	// 위 아래 좌 우
	static private void move(int dir, int cnt, int max, int[][] arr) {
		if(cnt==M) {
			ans = Math.max(ans, max);
			return;
		}
		if(max<<(M-cnt) <= ans) {
			return;
		}
		
		int[][] copyArr = new int[N][N];
		for (int i=0; i<N; i++) {
			copyArr[i] = arr[i].clone();
		}
		int res = 0;
		if(dir==0) {
			res = moveUp(copyArr);
		} else if(dir==1) {
			res = moveDown(copyArr);
		} else if(dir==2) {
			res = moveLeft(copyArr);
		} else {
			res = moveRight(copyArr);
		}
		
		
		
		max = Math.max(res, max);
		
		for (int i=0; i<4; i++) {
			move(i, cnt+1, max, copyArr);
		}
	}
	
	static private int moveUp(int[][] arr) {
		int res = 0;
		Queue<Integer> q = new ArrayDeque<>();
		for (int j=0; j<N; j++) {
			for (int i=0; i<N; i++) {
				if(arr[i][j]!=0) q.add(arr[i][j]);
				arr[i][j] = 0;
			}
			int i = 0;
			while(!q.isEmpty()) {
				if(arr[i][j] == 0) {
					arr[i][j] = q.poll();
				}else if(arr[i][j] == q.peek()) {
					arr[i][j]*=2;
					res = Math.max(arr[i][j], res);
					q.poll();
					i++;
				}else {
					i++;
				}
			}
		}
		return res;
	}
	
	static private int moveDown(int[][] arr) {
		int res = 0;
		Queue<Integer> q = new ArrayDeque<>();
		for (int j=0; j<N; j++) {
			for (int i=N-1; i>=0; i--) {
				if(arr[i][j]!=0) q.add(arr[i][j]);
				arr[i][j] = 0;
			}
			int i = N-1;
			while(!q.isEmpty()) {
				if(arr[i][j] == 0) {
					arr[i][j] = q.poll();
				}else if(arr[i][j] == q.peek()) {
					arr[i][j]*=2;
					res = Math.max(arr[i][j], res);
					q.poll();
					i--;
				}else {
					i--;
				}
			}
		}
		return res;
	}
	
	static private int moveLeft(int[][] arr) {
		int res = 0;
		Queue<Integer> q = new ArrayDeque<>();
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if(arr[i][j]!=0) q.add(arr[i][j]);
				arr[i][j] = 0;
			}
			int j = 0;
			while(!q.isEmpty()) {
				if(arr[i][j] == 0) {
					arr[i][j] = q.poll();
				}else if(arr[i][j] == q.peek()) {
					arr[i][j]*=2;
					res = Math.max(arr[i][j], res);
					q.poll();
					j++;
				}else {
					j++;
				}
			}
		}
		return res;
	}
	
	static private int moveRight(int[][] arr) {
		int res = 0;
		Queue<Integer> q = new ArrayDeque<>();
		for (int i=0; i<N; i++) {
			for (int j=N-1; j>=0; j--) {
				if(arr[i][j]!=0) q.add(arr[i][j]);
				arr[i][j] = 0;
			}
			int j = N-1;
			while(!q.isEmpty()) {
				if(arr[i][j] == 0) {
					arr[i][j] = q.poll();
				}else if(arr[i][j] == q.peek()) {
					arr[i][j]*=2;
					res = Math.max(arr[i][j], res);
					q.poll();
					j--;
				}else {
					j--;
				}
			}
		}
		return res;
	}
}
