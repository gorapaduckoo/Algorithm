import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_19948_음유시인영재 {
	static String content;
	static int spaceCnt;
	static int[] cnt = new int[26];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		content = br.readLine();
		spaceCnt = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<26; i++) {
			cnt[i] = Integer.parseInt(st.nextToken());
		}
		
		char prev = ' ';
		StringBuilder title = new StringBuilder();
		for (int i=0; i<content.length(); i++) {
			char c = content.charAt(i);
			if (c == prev) continue;
		
			if (c == ' ') {
				spaceCnt--;
				prev = c;
				continue;
			}
			
			if (prev == ' ') {
				title.append(Character.toUpperCase(c));
			}
			int idx = Character.toLowerCase(c) - 'a';
			cnt[idx]--;
			prev = c;
		}

		prev = ' ';
		for (int i=0; i<title.length(); i++) {
			char c = Character.toLowerCase(title.charAt(i));
			if (c == prev) continue;
			
			cnt[c - 'a']--;
			prev = c;
			continue;
		}
		
		if (spaceCnt < 0) {
			System.out.println("-1");
			return;
		}
		
		for (int i=0; i<26; i++) {
			if (cnt[i] < 0) {
				System.out.println("-1");
				return;
			}
		}
		
		System.out.println(title.toString());
	}
}
