import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Programmers_단어변환 {
    static Map<String, Integer> possibleWord = new HashMap<>();
    static Queue<String> q = new ArrayDeque<>();
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        init(begin, words);
        if(!possibleWord.containsKey(target)) {
            return answer;
        }
        
        q.add(begin);
        while(!q.isEmpty()) {
            if(possibleWord.get(target)!=0) {
                answer = possibleWord.get(target);
                break;
            }
            String word = q.poll();
            int dist = possibleWord.get(word)+1;
            changeWord(word, dist);
        }
        
        return answer;
    }
    
    public void init(String begin, String[] words) {
        possibleWord.put(begin, 0);
        for (String word : words) {
            possibleWord.put(word, 0);
        }
    }
    
    public void changeWord(String word, int dist) {
        char[] wordArr = word.toCharArray();
        for(int i=0; i<wordArr.length; i++) {
            char origin = wordArr[i];
            for(char c='a'; c<='z'; c++) {
                if(word.charAt(i)==c) continue;
                wordArr[i] = c;
                
                String newWord = new String(wordArr);
                if(possibleWord.containsKey(newWord)) {
                    possibleWord.put(newWord, dist);
                    q.add(newWord);                }
            }
            wordArr[i] = origin;
        }
    }

}
