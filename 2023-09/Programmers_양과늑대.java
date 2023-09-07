import java.util.ArrayList;
import java.util.List;

public class Programmers_양과늑대 {
	
    List<Integer>[] graph;
    int N, M, ROOT = 0;
    boolean[] checked;
    int answer=0;
    
    public int solution(int[] info, int[][] edges) {
        
        N = info.length;
        M = edges.length;
        checked = new boolean[1<<N];
        graph = new ArrayList[N];
        for (int i=0; i<N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i=0; i<M; i++) {
            int a = edges[i][0];
            int b= edges[i][1];
            graph[a].add(b);
        }
        
        dfs(ROOT,0,0,1,info);
        
        return answer;
    }
    
    void dfs(int now, int wolf, int sheep, int visited, int[] info) {
        
    	if(checked[visited]) return;
        checked[visited] = true;
        
        if(info[now]==0) sheep++;
        else wolf++;
        
        if(sheep>=wolf) answer = Math.max(answer, sheep);
        if(wolf>=sheep) return;
        
        for (int i=0; i<N; i++) {
            if((visited&(1<<i)) == 0) continue;
            for (int next : graph[i]) {
                 dfs(next, wolf, sheep, visited|(1<<next), info);
            }
        }
    }
}