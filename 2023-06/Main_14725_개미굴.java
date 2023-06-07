import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

class Node implements Comparable<Node>{
	static StringBuilder sb = new StringBuilder();
	String name;
	Map<String, Node> child;
	
	Node(String name) {
		this.name = name;
		child = new TreeMap<>();
	}
	
	Node addChild(String name) {
		Node next = new Node(name);
		child.put(name, next);
		return next;
	}
	
	Node findChild(String name) {
		return child.get(name);
	}

	@Override
	public int compareTo(Node o) {
		return this.name.compareTo(o.name);
	}
	
	void printSubTree(int depth) {
		for(int i=0; i<depth; i++) {
			sb.append("--");
		}
		if(name!=null) sb.append(name +"\n");
		
		for (String next : child.keySet()) {
			child.get(next).printSubTree(depth+1);
		}
	}
}

public class Main_14725_개미굴 {
	static int N;
	static Node root = new Node(null);
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		for (int i=0; i<N; i++) {
			String info = br.readLine();
			getInfo(info);
		}
		
		root.printSubTree(-1);
		System.out.println(Node.sb.toString());
		
	}
	
	static void getInfo(String str) {
		StringTokenizer st = new StringTokenizer(str);
		int M = Integer.parseInt(st.nextToken());
		Node now = root;
		
		for (int i=0; i<M; i++) {
			String name = st.nextToken();
			Node next = now.findChild(name);
			if(next==null) next = now.addChild(name);
			now = next;
		}
	}
}
