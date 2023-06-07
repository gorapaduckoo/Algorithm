import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

class Node {
	String name;
	List<Node> child;
	
	Node(String name) {
		this.name = name;
		child = new ArrayList<>();
	}
	
	Node addChild(String name) {
		Node next = new Node(name);
		child.add(next);
		return next;
	}
	
	Node findChild(String name) {
		for (Node next : child) {
			if(next.name.equals(name)) {
				return next;
			}
		}
		return null;
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
		
		printTree(root, -1);
		System.out.println(sb.toString());
		
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
	
	static void printTree(Node now, int depth) {
		for(int i=0; i<depth; i++) {
			sb.append("--");
		}
		if(now.name!=null) sb.append(now.name +"\n");
		Collections.sort(now.child, new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.name.compareTo(o2.name);
			}
		});;
		
		for (Node next : now.child) {
			printTree(next, depth+1);
		}
	}
}
