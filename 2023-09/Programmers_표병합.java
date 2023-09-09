import java.util.ArrayList;
import java.util.List;

class Cell {
    int r;
    int c;
    
    public Cell(int r, int c) {
        this.r = r;
        this.c = c;
    }
}
public class Programmers_표병합 {
    static int N = 50;
    String[] table = new String[N*N+1];
    int[] parent = new int[N*N+1];
    
    public String[] solution(String[] commands) {
        List<String> answer = new ArrayList<>();
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                int idx = getIdx(i, j);
                parent[idx] = idx;
            }
        }
        
        for (int i=0; i<commands.length; i++) {
            String[] command = commands[i].split(" ");
            if(command[0].equals("UPDATE") && command.length==3) {
                updateValue(command[1], command[2]);
            } else if(command[0].equals("MERGE")) {
                int r1 = Integer.parseInt(command[1]);
                int c1 = Integer.parseInt(command[2]);
                int r2 = Integer.parseInt(command[3]);
                int c2 = Integer.parseInt(command[4]);
                merge(r1, c1, r2, c2);
            } else {
                int r = Integer.parseInt(command[1]);
                int c = Integer.parseInt(command[2]);
                if(command[0].equals("UPDATE")) {
                    updateCell(r, c, command[3]);
                } else if (command[0].equals("UNMERGE")) {
                    unmerge(r, c);
                } else {
                    answer.add(print(r, c));
                }
            }
        }
        return answer.toArray(new String[answer.size()]);
    }
    
    int getIdx(int r, int c) {
        return (r-1)*N + c;
    }
    
    int find(int x) {
        int root = parent[x];
        if(root == x) return x;
        
        return parent[x] = find(parent[x]);
    }
    
    void updateCell(int r, int c, String value) {
        int rootIdx = find(getIdx(r, c));
        table[rootIdx] = value;
    }
    
    void updateValue(String value, String updatedValue) {
        for (int i=1; i<=N*N; i++) {
            if(table[i]!=null && table[i].equals(value)) {
                table[i] = updatedValue;
            }
        }
    }
    
    void union (int x, int y) {
        x = find(x);
        y = find(y);
        if(y < x) parent[x] = y;
        else parent[y] = x;
    }
    void merge(int r1, int c1, int r2, int c2) {
        int x = find(getIdx(r1, c1));
        int y = find(getIdx(r2, c2));
        
        if(x==y) return;
        
        String value = table[x];
        if(table[x] == null) {
            value = table[y];
        }
        
        table[x] = null;
        table[y] = null;
        union(x, y);
        
        if(find(x) == x) {
            table[x] = value;
            table[y] = null;
        }
        else {
            table[x] = null;
            table[y] = value;
        }
    }
    
    void unmerge(int r, int c) {
        int root = find(getIdx(r, c));
        String value = table[root];
        List<Integer> unmergeCells = new ArrayList<>();
        for (int i=1; i<=N*N; i++) {
            if(find(i) == root) {
                unmergeCells.add(i);
            }
        }
        
        for (int cell : unmergeCells) {
            parent[cell] = cell;
            table[cell] = null;
        }
        int idx = getIdx(r, c);
        table[idx] = value;
    }
    
    String print(int r, int c) {
        int root = find(getIdx(r, c));
        String value = table[root];
        
        if(value==null) return "EMPTY";
        else return value;
    }
    
}
