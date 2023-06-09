import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_2143_두배열의합 {
	static int T;
	static int[] A, B;
	static Map<Integer, Integer> partitialA, partitialB;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		A = getArrayInput(br);
		B = getArrayInput(br);
		
		changeToPrefixSum(A);
		changeToPrefixSum(B);
		partitialA = getPartitialSumCountOf(A);
		partitialB = getPartitialSumCountOf(B);
		
		Set<Integer> keySet = partitialA.keySet();
		
		long answer = 0;
		for(int key : keySet) {
			long a = partitialA.get(key);
			long b = 0;
			if(partitialB.containsKey(T-key)) {
				b = partitialB.get(T-key);
			}
			answer += (a*b);
		}
		
		System.out.println(answer);
		
	}
	
	static int[] getArrayInput(BufferedReader br) throws NumberFormatException, IOException {
		int N = Integer.parseInt(br.readLine());
		int[] result = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			result[i] = Integer.parseInt(st.nextToken());
		}
		
		return result;
	}
	
	static void changeToPrefixSum(int[] arr) {
		for (int i=1; i<arr.length; i++) {
			arr[i]+=arr[i-1];
		}
	}
	
	static Map<Integer, Integer> getPartitialSumCountOf(int[] arr) {
		Map<Integer, Integer> result = new HashMap<>();
		
		for (int i=0; i<arr.length; i++) {
			if(!result.containsKey(arr[i])) result.put(arr[i], 0);
			result.put(arr[i], result.get(arr[i])+1);
			
			for (int j=0; j<i; j++) {
				int num = arr[i]-arr[j];
				if(!result.containsKey(num))result.put(num, 0);
				result.put(num, result.get(num)+1);
			}
		}
		
		return result;
	}
}
