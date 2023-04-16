import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.StringTokenizer;



public class Main_1713_후보추천하기 {
	static int N, M;
	static HashMap<Integer, Integer> frame;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		frame = new LinkedHashMap<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<M; i++) {
			int n = Integer.parseInt(st.nextToken());
			if(frame.containsKey(n)) {
				addLike(n);
			} else if(frame.size()<N) {
				addPhoto(n);
			} else {
				removePhoto();
				addPhoto(n);
			}
		}
		
		List<Integer> studentList = new ArrayList<>(frame.keySet());
		Collections.sort(studentList);
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<studentList.size(); i++) {
			sb.append(studentList.get(i) + " ");
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	
	static void addLike(int n) {
		frame.put(n, frame.get(n)+1);
	}
	
	static void addPhoto(int n) {
		frame.put(n, 1);
	}
	
	static void removePhoto() {
		int removeIdx = -1;
		for (int student : frame.keySet()) {
			if(removeIdx<0) {
				removeIdx = student;
				continue;
			}
			if(frame.get(removeIdx) > frame.get(student)) {
				removeIdx = student;
			}
		}
		
		frame.remove(removeIdx);
	}
}
