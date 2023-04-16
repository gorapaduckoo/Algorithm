import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main_16472_고냥이 {
	static int N, ans = Integer.MIN_VALUE;
	static String str;
	static Map<Character, Integer> translator;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		str = br.readLine();
		translator = new HashMap<>();
		int left = 0, right = 1;
		translator.put(str.charAt(0), 1);
		
		while(left<right && right<str.length()) {
			if(translator.size()>N) {
				char key = str.charAt(left);
				int value = translator.get(key);
				
				if(value==1) translator.remove(key);
				else translator.put(key, value-1);
				left++;
				
			} else {
				ans = Math.max(ans, right-left);
				char key = str.charAt(right);
				if(translator.containsKey(key)) {
					translator.put(key, translator.get(key)+1);
				} else {
					translator.put(key, 1);
				}
				right++;
			}
		}
		if(translator.size()<=N) ans = Math.max(ans, right-left);
		System.out.println(ans);
	}
}
