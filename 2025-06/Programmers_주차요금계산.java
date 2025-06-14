import java.util.*;

public class Programmers_주차요금계산 {
	int basicTime, basicFee, unitTime, unitFee;
    Map<String, List<Integer>> ins, outs;
    Map<String, Integer> totalTime, totalFee;
    
    public int[] solution(int[] fees, String[] records) {
        init(fees, records);
        
        List<String> carNames = new ArrayList<>();
        for (String car : ins.keySet()) {
            int fee = calculateTotalFee(car);
            carNames.add(car);
            totalFee.put(car, fee);
        }
        
        Collections.sort(carNames);
        int[] answer = new int[carNames.size()];
        
        for (int i=0; i<carNames.size(); i++) {
            String car = carNames.get(i);
            answer[i] = totalFee.get(car);
        }
        
        return answer;
    }
    
    void init(int[] fees, String[] records) {
        basicTime = fees[0];
        basicFee = fees[1];
        unitTime = fees[2];
        unitFee = fees[3];
        
        ins = new HashMap<>();
        outs = new HashMap<>();
        totalTime = new HashMap<>();
        totalFee = new HashMap<>();
        
        for (String record : records) {
            String[] str = record.split(" ");
            
            int time = convertToInt(str[0]);
            String name = str[1];
            
            if (!ins.containsKey(name)) {
                ins.put(name, new ArrayList<>());
            }
            if (!outs.containsKey(name)) {
                outs.put(name, new ArrayList<>());
            }
            
            if (str[2].equals("IN")) {
                ins.get(name).add(time);
            } else {
                outs.get(name).add(time);
            }
        }
        
        addLeftCars();
    }
    
    int convertToInt(String time) {
        String[] str = time.split(":");
        
        int hour = Integer.parseInt(str[0]);
        int minute = Integer.parseInt(str[1]);
        
        return hour * 60 + minute;
    }
    
    void addLeftCars() {
        int outTime = convertToInt("23:59");
        
        for (String car : ins.keySet()) {
            if (ins.get(car).size() != outs.get(car).size()) {
                outs.get(car).add(outTime);
            }
        }
    }
    
    int calculateTotalFee(String car) {
        int totalTime = 0;
        List<Integer> inTimes = ins.get(car);
        List<Integer> outTimes = outs.get(car);
        
        for (int i=0; i<inTimes.size(); i++) {
            totalTime += (outTimes.get(i) - inTimes.get(i));
        }
        
        return calculateFee(totalTime);
    }
    
    int calculateFee(int time) {
        if (time <= basicTime) {
            return basicFee;
        }
        
        time -= basicTime;
        int fee = basicFee;
    
        if (time % unitTime != 0) {
            time += unitTime;
        }
        return fee + (time / unitTime * unitFee);
    }
}
