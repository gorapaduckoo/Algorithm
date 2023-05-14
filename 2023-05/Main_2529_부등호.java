import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2529_부등호 {
	static int N;
	static char[] sequence;
	static int[] maxNum, minNum, maxAns, minAns;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		sequence = new char[N];
		maxNum = new int[N+1];
		minNum = new int[N+1];
		maxAns = new int[N+1];
		minAns = new int[N+1];
		Arrays.fill(minAns, 9);
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			sequence[i] = st.nextToken().charAt(0);
		}
		
		pickMax(0, new boolean[10]);
		pickMin(0, new boolean[10]);
		
		for (int i=0; i<=N; i++) {
			System.out.print(maxAns[i]);
		}
		System.out.println();
		for (int i=0; i<=N; i++) {
			System.out.print(minAns[i]);
		}
		System.out.println();
		
	}
	
	static void pickMax(int cnt, boolean[] picked) {
		if(cnt>0 && maxAns[cnt-1]>maxNum[cnt-1]) return;
		if(cnt>N) {
			if(compare(maxAns, maxNum)<0) copy(maxNum, maxAns);
			return;
		}
		if(cnt==0) {
			for (int i=9; i>=0; i--) {
				picked[i] = true;
				maxNum[cnt] = i;
				pickMax(cnt+1, picked);
				picked[i] = false;
			}
		} else if(sequence[cnt-1]=='<') {
			for (int i=9; i>=0; i--) {
				if(picked[i] || i<maxNum[cnt-1]) continue;
				
				picked[i] = true;
				maxNum[cnt] = i;
				pickMax(cnt+1, picked);
				picked[i] = false;
			}
		} else {
			for (int i=9; i>=0; i--) {
				if(picked[i] || i>maxNum[cnt-1]) continue;
				
				picked[i] = true;
				maxNum[cnt] = i;
				pickMax(cnt+1, picked);
				picked[i] = false;
			}
		}
	}
	
	static void pickMin(int cnt, boolean[] picked) {
		if(cnt>0 && minAns[cnt-1]<minNum[cnt-1]) return;
		if(cnt>N) {
			if(compare(minAns, minNum)>0) copy(minNum, minAns);
			return;
		}
		
		if(cnt==0) {
			for (int i=0; i<10; i++) {
				picked[i] = true;
				minNum[cnt] = i;
				pickMin(cnt+1, picked);
				picked[i] = false;
			}
		} else if(sequence[cnt-1]=='<') {
			for (int i=0; i<10; i++) {
				if(picked[i] || i<minNum[cnt-1]) continue;
				
				picked[i] = true;
				minNum[cnt] = i;
				pickMin(cnt+1, picked);
				picked[i] = false;
			}
		} else {
			for (int i=0; i<10; i++) {
				if(picked[i] || i>minNum[cnt-1]) continue;
				
				picked[i] = true;
				minNum[cnt] = i;
				pickMin(cnt+1, picked);
				picked[i] = false;
			}
		}
	}
	
	static int compare(int[] ans, int[] newNum) {
		for (int i=0; i<=N; i++) {
			if(ans[i] > newNum[i]) return 1;
			if(ans[i] < newNum[i]) return -1;
		}
		return 0;
	}
	
	static void copy(int[] from, int[] to) {
		for (int i=0; i<=N; i++) {
			to[i] = from[i];
		}
	}
}
