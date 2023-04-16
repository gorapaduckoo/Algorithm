import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;


public class Main_1339_단어수학 {
	static int N;
	static boolean[] used;
	static Map<Character, Integer> dict;
	static List<String> list;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		used = new boolean[10];
		dict = new HashMap<>();
		list = new ArrayList<>();
		
		for (int i=0; i<N; i++) {
			list.add(br.readLine());
		}
		
		for (String word : list) {
			Stack<Character> st = new Stack<>();
			for (int i=0; i<word.length(); i++) {
				st.push(word.charAt(i));
			}
			int tmp = 1;
			while(!st.isEmpty()) {
				Character c = st.pop();
				if(dict.containsKey(c)) dict.put(c, dict.get(c)+tmp);
				else dict.put(c, tmp);
				tmp*=10;
			}
			
		}
		
		List<Character> keySet = new ArrayList<>(dict.keySet());
		Collections.sort(keySet, (key1, key2)-> {
			return dict.get(key2) - dict.get(key1);
		});
		
		int ans = 0;
		for(Character c : keySet) {
			for (int i=9; i>=0; i--) {
				if(!used[i]) {
					used[i] = true;
					ans += (i*dict.get(c));
					break;
				}
			}
		}
		System.out.println(ans);
	}
	
	
}
