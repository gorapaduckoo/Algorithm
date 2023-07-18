import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main_2981_검문 {
	static int N;
	static int[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
		
		int gcd = arr[1]-arr[0];
		for (int i=2; i<N; i++) {
			gcd = findGCD(gcd, arr[i]-arr[i-1]);
		}
		List<Integer> answer = findDivisor(gcd);
		StringBuilder sb = new StringBuilder();
		for (int n : answer) {
			sb.append(n + " ");
		}
		System.out.println(sb.toString());
	}
	
	static int findGCD(int a, int b) {
		if(b%a==0) return a;
		return findGCD(b%a, a);
	}
	
	static List<Integer> findDivisor(int n) {
		List<Integer> result = new ArrayList<>();
		
		for (int i=2; i<=Math.sqrt(n); i++) {
			if(n%i==0) {
				result.add(i);
				if(n/i!=i) result.add(n/i);
			}
		}
		result.add(n);
		
		Collections.sort(result);
		return result;
	}
}
