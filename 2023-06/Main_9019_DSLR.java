import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main_9019_DSLR {
	static class Register {
		int num;
		String cmd;
		
		public Register(int num, String cmd) {
			this.num = num;
			this.cmd = cmd;
		}
	}

	
	static final int MIN_NUM = 0, MAX_NUM = 10000, N=3;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T>0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			sb.append(bfs(A,B) + "\n");
			T--;
		}
		System.out.println(sb.toString());
	}
	
	static String bfs(int A, int B) {
		Queue<Register> q = new ArrayDeque<>();
		boolean[] visited = new boolean[MAX_NUM];
		
		q.add(new Register(A,""));
		visited[A] = true;
		while(!q.isEmpty()) {
			Register reg = q.poll();
			if(reg.num==B) {
				return reg.cmd;
			}
			
			int d = reg.num*2%(MAX_NUM);
			int s = reg.num-1;
			if(s<MIN_NUM) s+=(MAX_NUM);
			int l = (reg.num%1000)*10 + reg.num/1000;
			int r = (reg.num%10)*1000 + reg.num/10;
			
			if(!visited[d]) {
				q.add(new Register(d, reg.cmd+"D"));
				visited[d] = true;
			}
			if(!visited[s]) {
				q.add(new Register(s, reg.cmd+"S"));
				visited[s] = true;
			}
			if(!visited[l]) {
				q.add(new Register(l, reg.cmd+"L"));
				visited[l] = true;
			}
			if(!visited[r]) {
				q.add(new Register(r, reg.cmd+"R"));
				visited[r] = true;
			}
		}
		
		return "";
	}
	
}
