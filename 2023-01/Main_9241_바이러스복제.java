import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_9241_바이러스복제 {
	static int left, right, N, M;
	static String beforeDNA, afterDNA;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		beforeDNA = br.readLine();
		afterDNA = br.readLine();
		N = beforeDNA.length();
		M = afterDNA.length();
		
		while((left<N)&&(left<M)) {
			if(beforeDNA.charAt(left) != afterDNA.charAt(left)) break;
			left++;
		}
		
		while((right<N)&&(right<M)) {
			if(beforeDNA.charAt(N-right-1) != afterDNA.charAt(M-right-1)) break;
			right++;
		}
		
		System.out.println(N + ", " + M);
		System.out.println(left +  ", " + right);
		
		int minLength = Math.min(N, M);
		if(M<left+right) {
			if(left==N) {
				System.out.println(M-left);
			} else {
				System.out.println(0);
			}
		} else {
			System.out.println(M-left-right);
		}
		
	}

}
