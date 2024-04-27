import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class RunClass {
	public static void main(String[] args) {
		// 1. HashSet
		HashSet<SaveBox> setBoxes = new HashSet<SaveBox>();
		
		SaveBox s1 = new SaveBox("돼지저금통");
		s1.deposit(10000);
		setBoxes.add(s1);
		
		SaveBox s2 = new SaveBox("토끼저금통");
		s2.deposit(5000);
		setBoxes.add(s2);
		
		SaveBox s3 = new SaveBox("돼지저금통");
		s3.deposit(1000);
		setBoxes.add(s3);
		
		System.out.println(setBoxes.toString()); // HashSet 출력
		
		
		// 2. HashMap
		HashMap<String, SaveBox> mapBoxes = new HashMap<String, SaveBox>();
		mapBoxes.put("돼지1", new SaveBox("돼지저금통"));
		mapBoxes.put("돼지2", new SaveBox("돼지저금통"));
		
		mapBoxes.get("돼지1").deposit(100);
		mapBoxes.get("돼지1").deposit(100);
		mapBoxes.get("돼지2").deposit(1000);
		mapBoxes.get("돼지1").deposit(100);
		
		System.out.println(mapBoxes); // HashMap 출력 (맵 안에 value로 넣는 SaveBox 객체는 toString() 메소드 오버라이드 해 줘야 함)
		
		
		// 3. Stack
		Stack<SaveBox> stackBoxes = new Stack<SaveBox>();
		stackBoxes.add(new SaveBox("돼지저금통"));
		stackBoxes.add(new SaveBox("돼지저금통"));
		stackBoxes.add(new SaveBox("돼지저금통"));
		stackBoxes.add(new SaveBox("토끼저금통"));
		
		SaveBox pick = stackBoxes.pop();
		System.out.println(pick);
		
		pick = stackBoxes.pop();
		System.out.println(pick);
		
		System.out.println(stackBoxes.size()); 
		
		System.out.println(stackBoxes); // Stack 출력
		
		
		// 4. Queue (Queue는 LinkedList로 만들어야 한다? 버전에 따라 다를 수도?)
		Queue<SaveBox> queueBoxes = new LinkedList<SaveBox>();
		queueBoxes.add(new SaveBox("돼지저금통"));
		queueBoxes.add(new SaveBox("돼지저금통"));
		queueBoxes.add(new SaveBox("돼지저금통"));
		queueBoxes.add(new SaveBox("토끼저금통"));
		
		SaveBox pick2 = queueBoxes.poll();
		System.out.println(pick2);
		
		pick2 = queueBoxes.poll();
		System.out.println(pick2);
		
		pick2 = queueBoxes.poll();
		System.out.println(pick2);
		
		pick2 = queueBoxes.poll();
		System.out.println(pick2); // 토끼저금통(0)
		
		System.out.println(queueBoxes); // Queue 출력
		
	}
}
