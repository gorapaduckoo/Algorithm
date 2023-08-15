import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

class Tree implements Comparable<Tree>{
	int x;
	int y;
	int age;
	
	public Tree(int x, int y, int age) {
		this.x = x;
		this.y = y;
		this.age = age;
	}

	@Override
	public int compareTo(Tree o) {
		return this.age - o.age;
	}
}

public class Main_16235_나무재테크 {
	static int N, M, K;
	static int[] dx = {-1,-1,-1,0,0,1,1,1}, dy = {-1,0,1,-1,1,-1,0,1};
	static int[][] A, nutrient;
	static Deque<Tree> trees = new ArrayDeque<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		A = new int[N][N];
		nutrient = new int[N][N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i=0; i<N; i++) {
			Arrays.fill(nutrient[i], 5);
		}
		
		List<Tree> treeList = new ArrayList<>();
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int age = Integer.parseInt(st.nextToken());
			treeList.add(new Tree(x, y, age));
		}
		
		Collections.sort(treeList);
		for (Tree tree : treeList) {
			trees.add(tree);
		}
		
		while(K>0) {
			List<Tree> deadTree = spring();
			summer(deadTree);
			fall();
			winter();
			K--;
		}
		
		System.out.println(trees.size());
		
	}
	
	public static List<Tree> spring() {
		List<Tree> deadTree = new ArrayList<>();
		
		int size = trees.size();
		for (int i=0; i<size; i++) {
			Tree tree = trees.poll();
			if(tree.age > nutrient[tree.x][tree.y]) {
				deadTree.add(tree);
				continue;
			}
			nutrient[tree.x][tree.y] -= tree.age;
			tree.age++;
			trees.add(tree);
		}
		
		return deadTree;
	}
	
	public static void summer(List<Tree> deadTree) {
		for (Tree tree : deadTree) {
			nutrient[tree.x][tree.y] += (tree.age/2);
		}
	}
	
	public static void fall() {
		List<Tree> copiedTree = new ArrayList<>();
		for (Tree tree : trees) {
			if(tree.age%5 == 0) {
				copiedTree.add(tree);
			}
		}
		
		for (Tree tree : copiedTree) {
			for (int i=0; i<8; i++) {
				int nx = tree.x+dx[i];
				int ny = tree.y+dy[i];
				if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
				trees.addFirst(new Tree(nx, ny, 1));
			}
		}
	}
	
	public static void winter() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				nutrient[i][j] += A[i][j];
			}
		}
	}
}
