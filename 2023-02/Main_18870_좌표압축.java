import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;


public class Main_18870_좌표압축 {
	static int N;
	static Map<Integer, Integer> map;
	static Integer[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new HashMap<>();
		N = Integer.parseInt(br.readLine());
		arr = new Integer[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Set<Integer> set = new HashSet<>(Arrays.asList(arr));
		Integer[] sortedArr = set.toArray(new Integer[0]);
		Arrays.sort(sortedArr);
		
		for (int i=0; i<sortedArr.length; i++) {
			map.put(sortedArr[i], i);
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<N; i++) {
			sb.append(map.get(arr[i]));
			sb.append(" ");
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
}
