import java.util.ArrayDeque;
import java.util.Deque;

public class Programmers_행렬과연산 {
	Deque<Integer> firstColumn, lastColumn;
    Deque<Deque<Integer>> rows;
    int N, M;
    
    public int[][] solution(int[][] rc, String[] operations) {
        
        init(rc);
        
        for(String command : operations) {
            if(command.equals("Rotate")) {
                rotate();
            } else {
                shiftRow();
            }
        }
        
        
        int[][] answer = getArray();
        
        return answer;
    }
    
    public void init(int[][] rc) {
        firstColumn = new ArrayDeque<>();
        lastColumn = new ArrayDeque<>();
        
        N = rc.length;
        M = rc[0].length;
        rows = new ArrayDeque<>();
        
        for (int i=0; i<N; i++) {
            firstColumn.add(rc[i][0]);
            lastColumn.add(rc[i][M-1]);
            
            Deque row = new ArrayDeque<>();
            for (int j=1; j<M-1; j++) {
                row.add(rc[i][j]);
            }
            rows.add(row);
        }
    }
    
    public void shiftRow() {
        firstColumn.addFirst(firstColumn.pollLast());
        lastColumn.addFirst(lastColumn.pollLast());
        rows.addFirst(rows.pollLast());
    }
    
    public void rotate() {
        Deque<Integer> firstRow = rows.peekFirst();
        Deque<Integer> lastRow = rows.peekLast();
        
        firstRow.addFirst(firstColumn.pollFirst());
        lastColumn.addFirst(firstRow.pollLast());
        lastRow.addLast(lastColumn.pollLast());
        firstColumn.addLast(lastRow.pollFirst());
    }
    
    public int[][] getArray() {
        int[][] result = new int[N][M];
        
        for (int i=0; i<N; i++) {
            result[i][0] = firstColumn.pollFirst();
            result[i][M-1] = lastColumn.pollFirst();
            
            Deque<Integer> row = rows.pollFirst();
            for (int j=1; j<M-1; j++) {
                result[i][j] = row.pollFirst();
            }
        }
        
        return result;
    }
}
