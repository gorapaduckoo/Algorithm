import java.util.*;

public class Programmers_셔틀버스 {
	int N, T, M;
    Queue<Integer> bus = new ArrayDeque<>();
    Queue<Integer> crew = new ArrayDeque<>();
    
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        
        init (n, t, m, timetable);
        
        while(bus.size() > 1) {
            int cnt = 0;
            System.out.println("탑승 명단");
            while (cnt < m && !crew.isEmpty() && bus.peek() >= crew.peek()) {
                System.out.println(intToTime(crew.peek()));
                crew.poll();
                cnt++;
            }
            System.out.println("출발: " + intToTime(bus.peek()));
            bus.poll();
        }
        
        if (crew.size() < m) {
            return intToTime(bus.poll());
        }
        
        int cnt = 0;
        while (cnt < m-1 && bus.peek() >= crew.peek()) {
            crew.poll();
            cnt++;
        }
        
        if (bus.peek() >= crew.peek()) {
            return intToTime(crew.peek() - 1);
        }
        
        return intToTime(bus.poll());
    }
    
    void init(int n, int t, int m, String[] timetable) {
        N = n;
        T = t;
        M = m;
        Arrays.sort(timetable);
        
        int busTime = timeToInt("09:00");
        bus.add(busTime);
        while(bus.size() < N) {
            busTime += T;
            bus.add(busTime);
        }
        
        for (String time : timetable) {
            crew.add(timeToInt(time));
        }
    }
    
    int timeToInt(String time) {
        String[] str = time.split(":");
        
        int hour = Integer.parseInt(str[0]);
        int minute = Integer.parseInt(str[1]);
        
        return (hour * 60) + minute;
    }
    
    String intToTime(int num) {
        String hour = Integer.toString(num / 60);
        String minute = Integer.toString(num % 60);
        
        if (hour.length() < 2) {
            hour = "0" + hour;
        }
        if (minute.length() < 2) {
            minute = "0" + minute;
        }
        
        return hour + ":" + minute;
    }
}
