import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

class Command {
	String cmd;
	int x;
	Command(String cmd, int x) {
		this.cmd = cmd;
		this.x = x;
	}
	Command(String cmd) {
		this.cmd = cmd;
	}
}

public class Main_3425_고스택 {
	static List<Command> commands;
	static int MAX = 1000000000;
	static String ERR = "ERROR";
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(getCommand(br)) {
			int N = Integer.parseInt(br.readLine());
			for (int i=0; i<N; i++) {
				int num = Integer.parseInt(br.readLine());
				String result = executeProgram(num);
				sb.append(result+"\n");
			}
			sb.append("\n");
			br.readLine();
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	
	static boolean getCommand(BufferedReader br) throws IOException {
		commands = new ArrayList<>();
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			Integer x = null;
			
			if(cmd.equals("END")) return true;
			if(cmd.equals("QUIT")) return false;
			if(cmd.equals("NUM")) x = Integer.parseInt(st.nextToken());
			
			if(x==null) commands.add(new Command(cmd));
			else commands.add(new Command(cmd, x));
		}
	}
	
	static String executeProgram(int num) {
		Stack<Integer> st = new Stack<>();
		st.add(num);

		for (int i=0; i<commands.size(); i++) {
			Command command= commands.get(i);
			String cmd = command.cmd;
			if(cmd.equals("NUM")) {
				if(command.x>MAX) return ERR;
				st.add(command.x);
			} else if (cmd.equals("POP")) {
				if(st.isEmpty()) return ERR;
				st.pop();
			} else if(cmd.equals("INV")) {
				if(st.isEmpty()) return ERR;
				int tmp = st.pop();
				st.add(-tmp);
			} else if (cmd.equals("DUP")) {
				if(st.isEmpty()) return ERR;
				st.add(st.peek());
			} else if (cmd.equals("SWP")) {
				if(st.size()<2) return ERR;
				int a = st.pop();
				int b = st.pop();
				st.add(a);
				st.add(b);
			} else if (cmd.equals("ADD")) {
				if(st.size()<2) return ERR;
				int a = st.pop();
				int b = st.pop();
				if(Math.abs(a+b)>MAX) return ERR;
				st.add(a+b);
			} else if (cmd.equals("SUB")) {
				if(st.size()<2) return ERR;
				int a = st.pop();
				int b = st.pop();
				if(Math.abs(b-a)>MAX) return ERR;
				st.add(b-a);
			} else if (cmd.equals("MUL")) {
				if(st.size()<2) return ERR;
				long a = st.pop();
				long b = st.pop();
				long result = a*b;
				if(Math.abs(result)>MAX) return ERR;
				st.add((int)result);
			} else if (cmd.equals("DIV")) {
				if(st.size()<2) return ERR;
				int a = st.pop();
				int b = st.pop();
				if(a==0 || Math.abs(b/a)>MAX) return ERR;
				st.add(b/a);
			} else if (cmd.equals("MOD")) {
				if(st.size()<2) return ERR;
				int a = st.pop();
				int b = st.pop();
				if(a==0 || Math.abs(b%a)>MAX) return ERR;
				st.add(b%a);
			} else return ERR;
		}
		
		if(st.size()>1 || st.isEmpty()) return ERR;
		return st.pop()+"";
	}
	
}
