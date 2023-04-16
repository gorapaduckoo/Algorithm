import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_12891_DNA비밀번호 {
	static int S, P, ans = 0;;
	static char[] str;
	static int[] threshold, dnaCount;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		str = br.readLine().toCharArray();
		
		threshold = new int[4];
		dnaCount = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<4; i++) {
			threshold[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i=0; i<P; i++) {
			dnaCount[getDNA(str[i])]++;
		}
		if(isPassword()) ans++;

		
		int l=0, r=P;
		int a=0, c=0, g=0, t=0;
		while(r<S) {
			dnaCount[getDNA(str[r])]++;
			r++;
			dnaCount[getDNA(str[l])]--;
			l++;
			if(isPassword()) {
				ans++;
			}
		}
		
		System.out.println(ans);
		
	}
	
	public static int getDNA(char c) {
		if(c=='A') {
			return 0;
		} else if (c=='C') {
			return 1;
		} else if (c=='G') {
			return 2;
		} else return 3;
	}
	
	public static boolean isPassword() {
		for (int i=0; i<4; i++) {
			if(dnaCount[i] < threshold[i]) return false;
		}
		return true;
	}
}
