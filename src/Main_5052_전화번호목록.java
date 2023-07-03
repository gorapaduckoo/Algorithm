import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Node {
	int cnt;
	Map<Integer, Node> map;
	public Node() {
		this.cnt = 0;
		this.map = new HashMap<>();
	}
}

public class Main_5052_전화번호목록 {
	static int T, N;
	static Node root;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		while(T>0) {
			root = new Node();
			N = Integer.parseInt(br.readLine());
			String[] phoneNumbers = new String[N];
			for (int i=0; i<N; i++) {
				phoneNumbers[i] = br.readLine();
			}
			Arrays.sort(phoneNumbers);
			boolean result = true;
			for (int i=0; i<N; i++) {
				result = isConsistentPhoneNumber(phoneNumbers[i]);
				if(!result) break;
			}
			
			if(result) sb.append("YES\n");
			else sb.append("NO\n");
			T--;
		}
		System.out.println(sb.toString());
	}
	
	static boolean isConsistentPhoneNumber(String numbers) {
		Node now = root;
		for (int i=0; i<numbers.length(); i++) {
			if(now.cnt!=0) return false;
			int n = numbers.charAt(i) - '0';
			if(!now.map.containsKey(n)) {
				now.map.put(n, new Node());
			}
			now = now.map.get(n);
		}
		now.cnt++;
		return true;
	}
}
