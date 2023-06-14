import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Node {
	int depth, col;
	int left, right;
	
	Node(int left, int right) {
		this.left = left;
		this.right = right;
	}
}

public class Main_2250_트리의높이와너비 {
	static int N, leftCnt = 0, MAX_DEPTH;
	static int answerDepth, maxWidth = -1;
	static int[] col;
	static Node[] nodes;
	static Map<Integer, Integer> minCol, maxCol;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nodes = new Node[N+1];
		minCol = new HashMap<>();
		maxCol = new HashMap<>();
		boolean[] isRoot = new boolean[N+1];
		Arrays.fill(isRoot, true);
		
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int	left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			nodes[idx] = new Node(left, right);
			
			if(left>0) isRoot[left] = false;
			if(right>0) isRoot[right] = false;
		}
		
		for (int i=1; i<=N; i++) {
			if(isRoot[i]) searchTree(i, 1);
		}
		findMaxWidth();
		System.out.println(answerDepth +" " + maxWidth);
	}
	
	static void searchTree(int nowIdx, int depth) {
		Node now = nodes[nowIdx];
		if(now.left>0) searchTree(now.left, depth+1);
		leftCnt++;
		now.col = leftCnt;
		now.depth = depth;
		if(now.right>0) searchTree(now.right, depth+1);
		
		if(!minCol.containsKey(now.depth) || minCol.get(now.depth) > now.col) minCol.put(now.depth, now.col);
		if(!maxCol.containsKey(now.depth)|| maxCol.get(now.depth) < now.col) maxCol.put(now.depth, now.col);
		MAX_DEPTH = Math.max(depth, MAX_DEPTH);
	}
	
	static void findMaxWidth() {
		for (int i=1; i<=MAX_DEPTH; i++) {
			int width = maxCol.get(i)-minCol.get(i)+1;
			if(maxWidth < width) {
				maxWidth = width;
				answerDepth = i;
			}
		}
	}
}
