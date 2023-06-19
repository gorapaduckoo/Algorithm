import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14888_연산자끼워넣기 {
	static int N, maxSum = Integer.MIN_VALUE, minSum = Integer.MAX_VALUE;
	static int[] number;
	static int[] operator;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		number = new int[N];
		operator = new int[4];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			number[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<4; i++) {
			operator[i] = Integer.parseInt(st.nextToken());
		}
		
		permutation(0,number[0]);
		System.out.println(maxSum);
		System.out.println(minSum);
	}
	
	static void permutation(int cnt, int sum) {
		if(cnt==N-1) {
			maxSum = Math.max(maxSum, sum);
			minSum = Math.min(minSum, sum);
			return;
		}
		
		for (int i=0; i<4; i++) {
			if(operator[i]==0) continue;
			operator[i]--;
			if(i==0) permutation(cnt+1, sum+number[cnt+1]);
			else if(i==1) permutation(cnt+1, sum-number[cnt+1]);
			else if(i==2) permutation(cnt+1, sum*number[cnt+1]);
			else permutation(cnt+1, sum/number[cnt+1]);
			operator[i]++;
		}
	}
}
