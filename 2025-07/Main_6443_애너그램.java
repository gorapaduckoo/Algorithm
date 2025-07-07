import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main_6443_애너그램 {
	static int N;
	static Set<String> words;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		while (N > 0) {
			words = new HashSet<String>();
			
			String word = br.readLine();
			char[] characters = word.toCharArray();
			int[] cnt = new int[26];
			for (int i=0; i<characters.length; i++) {
				cnt[characters[i]-'a']++;
			}
			
			makeAnagram(characters, cnt, new StringBuilder());
			
			String[] answer = words.toArray(new String[0]);
			Arrays.sort(answer);
			
			for (int i=0; i<answer.length; i++) {
				sb.append(answer[i] + "\n");
			}
			
			N--;
		}
		
		System.out.print(sb.toString());
	}
	
	static void makeAnagram(char[] arr, int[] cnt, StringBuilder sb) {
		if (sb.length() == arr.length) {
			words.add(sb.toString());
			return;
		}
		
		for (int i=0; i<26; i++) {
			if (cnt[i] <= 0) continue;
			
			cnt[i]--;
			makeAnagram(arr, cnt, sb.append((char)('a'+i)));
			cnt[i]++;
			sb.setLength(sb.length()-1);
		}
	}
}
