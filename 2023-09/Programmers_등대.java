import java.util.ArrayList;
import java.util.List;

class Programmers_등대 {
    int answer = 0;
    boolean[] visited;
    List<Integer>[] graph;
    
    public int solution(int n, int[][] lighthouse) {
        visited = new boolean[n+1];
        graph = new ArrayList[n+1];
        for (int i=1; i<=n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i=0; i<lighthouse.length; i++) {
            int a = lighthouse[i][0];
            int b = lighthouse[i][1];
            graph[a].add(b);
            graph[b].add(a);
        }
        
        dfs(1);
        
        return answer;
    }
    
    int dfs(int now) {
        visited[now] = true;
        
        int sum = 0, cnt = 0;
        for (int next : graph[now]) {
            if(visited[next]) {
                cnt++;
                continue;
            }
            sum += dfs(next);
        }
        
        if(sum+cnt < graph[now].size()) {
            answer++;
            return 1;
        }
        return 0;
    }
}