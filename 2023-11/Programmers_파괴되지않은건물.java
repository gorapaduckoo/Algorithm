
public class Programmers_파괴되지않은건물 {
	int N, M;
    int[][] sum;
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        
        init(board);
        
        for (int i=0; i<skill.length; i++) {
            int type = skill[i][0];
            
            if(type == 1) {
                useSkill(skill[i][1], skill[i][2], skill[i][3], skill[i][4], -skill[i][5]);
            } else {
                useSkill(skill[i][1], skill[i][2], skill[i][3], skill[i][4], skill[i][5]);
            }
        }
        
        for (int i=1; i<N; i++) {
            for (int j=0; j<M; j++) {
                sum[i][j] += sum[i-1][j];
            }
        }
        
        for (int j=1; j<M; j++) {
            for (int i=0; i<N; i++) {
                sum[i][j] += sum[i][j-1];
            }
        }
        
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                board[i][j] += sum[i][j];
                if(board[i][j] > 0) answer++;
            }
        }
        
        return answer;
    }
    
    void useSkill(int startX, int startY, int endX, int endY, int degree) {
        
        sum[startX][startY] += degree;
        if(endX<N-1 && endY<M-1) sum[endX+1][endY+1] += degree;
        if(endY<M-1) sum[startX][endY+1] += -degree;
        if(endX<N-1) sum[endX+1][startY] += -degree;
    }
    
    void init(int[][] board) {
        N = board.length;
        M = board[0].length;
        sum = new int[N][M];
    }
}
