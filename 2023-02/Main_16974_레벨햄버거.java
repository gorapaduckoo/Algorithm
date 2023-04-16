import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16974_레벨햄버거 {
	static int N;
	static long X, ans=0;
	static long[] burger, patty;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Long.parseLong(st.nextToken());
		
		burger = new long[N+1];
		burger[0] = 1;
		patty = new long[N+1];
		patty[0] = 1;
		
		for (int i=1; i<=N; i++) {
			burger[i] = burger[i-1]*2 + 3;
			patty[i] = patty[i-1]*2 + 1;
		} 
		
		eat(N,X);
		System.out.println(ans);
		
		
		
	}
	
	static void eat(int n, long x) {
		if(x==0) return;
		if(n==0) {
			ans++;
			return;
		}
		
		if (x < burger[n-1]+2) {
			eat(n-1,x-1);
			return;
		} else {
			ans += (patty[n-1]+1);
			eat(n-1, x-burger[n-1]-2);				
		}
	}
	
}
