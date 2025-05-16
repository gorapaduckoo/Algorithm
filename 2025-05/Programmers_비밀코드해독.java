
public class Programmers_비밀코드해독 {
int N, M, K = 5, answer = 0;
    
    int[] matchCounts;
    boolean[] used;
    int[][] queries;
    
    public int solution(int n, int[][] q, int[] ans) {
        init(n, q, ans);
        
        pickCodeNumber(0, 1);
        return answer;
    }
    
    void init(int n, int[][] q, int[] ans) {
        N = n;
        M = q.length;
        matchCounts = ans;
        used = new boolean[N+1];
        queries = q;
    }
    
    void pickCodeNumber(int nowIdx, int start) {
        if (nowIdx == K) {
            if (canBeSecretCode()) answer++;
            return;
        }
        
        for (int i=start; i<=N; i++) {
            if (used[i]) continue;
            used[i] = true;
            pickCodeNumber(nowIdx+1, i);
            used[i] = false;
        }
    }
    
    boolean canBeSecretCode() {
        for (int i=0; i<M; i++) {
            if (countMatchNumber(queries[i]) != matchCounts[i]) {
                return false;
            }
        }
        
        return true;
    }
    
    int countMatchNumber(int[] query) {
        int matchCount = 0;
        
        for (int i=0; i<5; i++) {
            int num = query[i];
            if (used[num]) matchCount++;
        }
        
        return matchCount;
    }
}
