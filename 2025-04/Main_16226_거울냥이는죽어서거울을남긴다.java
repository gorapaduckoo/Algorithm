import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

class Component implements Comparable<Component>{
	int x;
	int y;
	boolean isCat;
	
	public Component(int x, int y, boolean isCat) {
		this.x = x;
		this.y = y;
		this.isCat = isCat;
	}
	
	public int compareTo(Component o) {
		return this.y - o.y;
	}
}

public class Main_16226_거울냥이는죽어서거울을남긴다 {
	static int N;
	static Map<Integer, List<Component>> rows;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		rows = new HashMap<>();
		
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			
			if(!rows.containsKey(x)) {
				rows.put(x, new ArrayList<>());
			}
			if (!rows.containsKey(x+1)) {
				rows.put(x+1, new ArrayList<>());
			}
			
			rows.get(x).add(new Component(x, y, true));
			rows.get(x+1).add(new Component(x+1, y, false));
		}
		
		int answer = 0;
		
		for (int row : rows.keySet()) {
			List<Component> components = rows.get(row);
			Collections.sort(components);
			
			boolean canSave = true;
			for (Component component : components) {
				if(!component.isCat) {
					canSave = true;
					continue;
				}
				
				if(canSave) {
					answer++;
					canSave = false;
				}
			}
		}
		
		System.out.println(answer);
	}
	
	
}
