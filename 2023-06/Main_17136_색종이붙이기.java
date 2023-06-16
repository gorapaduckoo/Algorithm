import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17136_색종이붙이기 {
	static final int N = 10, MAX_LEN = 5, MAX_CNT = 5, INIT_ANS = 26;
	static int answer = INIT_ANS;
	static int[][] paper;
	public static void main(String[] args) throws IOException {
		init();
		
		boolean flag = true;
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if(paper[i][j]==1 && flag) {
					int length = 1;
					while(length<=MAX_LEN) {
						boolean[][] covered = new boolean[N][N];
						if(canCover(i,j,length, covered)) coverPaper(i, j, length, new int[MAX_LEN+1], covered);
						length++;
					}
					flag = false;
				}
			}
		}
		if(flag && answer == INIT_ANS) System.out.println(0);
		else if (answer == INIT_ANS) System.out.println(-1);
		else System.out.println(answer);
	}
	
	static void init() throws IOException {
		paper = new int[N][N];
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
	}
	
	
	static void coverPaper(int x, int y, int length, int[] paperCnt, boolean[][] covered) {
		
		int cnt = countUsedPaper(paperCnt);
		if(cnt >= answer) {
			return;
		}
		for (int i=x; i<x+length; i++) {
			for (int j=y; j<y+length; j++) {
				if(paper[i][j]!=1) return;
				covered[i][j] = true;
			}
		}
		paperCnt[length]++;
		
		boolean[][] newCovered = new boolean[N][N];
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				newCovered[i][j] = covered[i][j];
			}
		}
		for (int i=x; i<N; i++) {
			for (int j=0; j<N; j++) {
				if(paper[i][j]==1 && !covered[i][j]) {
					int newLength = 1;
					while(newLength<=MAX_LEN) {
						if(canCover(i,j,newLength, covered) && paperCnt[newLength]<MAX_CNT) coverPaper(i,j,newLength, paperCnt.clone(), newCovered);
						newLength++;
					}
					return;
					
				}
			}
		}
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if(paper[i][j]==1 && !covered[i][j]) return;
			}
		}
		answer = Math.min(answer, cnt+1);
	}
	
	static boolean canCover(int x, int y, int length, boolean[][] covered) {
		if(x+length>N || y+length>N) return false;
		for (int i=x; i<x+length; i++) {
			for (int j=y; j<y+length; j++) {
				if(paper[i][j]!=1 || covered[i][j]) return false;
			}
		}
		
		return true;
	}
	
	static int countUsedPaper(int[] paperCnt) {
		int cnt = 0;
		for (int i=1; i<=MAX_LEN; i++) {
			cnt += paperCnt[i];
		}
		return cnt;
	}
}
