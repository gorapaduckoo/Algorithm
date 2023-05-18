import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2879_코딩은예쁘게 {
	static int N;
	static int[] now;
	 public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		now = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			now[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			int target = Integer.parseInt(st.nextToken());
			now[i] = target-now[i];
		}
		
		int answer = 0, i =0;
		while (i<N) {
			if(now[i]==0) {
				i++;
				continue;
			}
			
			int j=i+1;
			int change = Math.abs(now[i]);
			while(j<N) {
				if(now[j-1]*now[j]<0 || now[j]==0) {
					break;
				}
				change = Math.min(Math.abs(change), Math.abs(now[j]));
				j++;
			}
			i = fix(i, j, change);
			answer += Math.abs(change);
		}
		
		System.out.println(answer);
	}
	 
	static int fix(int start, int end, int change) {
		if(now[start]*change>0) {
			change = -change;
		}
		int result = start;
		for (int i=start; i<end; i++) {
			now[i]+=change;
		}
		for (int i=start; i<=end; i++) {
			if(now[i]!=0) {
				result = i;
				break;
			}
		}
		return result;
	}
}

// 3 3 3

// 2 -1 -2 -4
// 0 -1 -2 -4
// 0 0 -1 -3
// 0 0 0 -2
// 0 0 0 0


// -4 1 -5 -4
// 0 1 -5 -4
// 0 0 -5 -4
// 0 0 0 1
// 0 0 0 0