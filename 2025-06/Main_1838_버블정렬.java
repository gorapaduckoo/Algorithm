import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_1838_버블정렬 {
	static int N;
	static int[] arr;
	static Map<Integer, Integer> index = new HashMap<Integer, Integer>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			index.put(arr[i], i);
		}
		
		Arrays.sort(arr);
		int answer = 0;
		
		for (int i=0; i<N; i++) {
			answer = Math.max(answer, index.get(arr[i]) - i);
		}
		
		System.out.println(answer);
	}
}
